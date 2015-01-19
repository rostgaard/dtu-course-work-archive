module NimLauncher

#load "NimGame.fsx"

open System 
open System.Net 
open System.Threading 
open System.Windows.Forms 
open System.Drawing 

open NimGame

System.IO.Directory.SetCurrentDirectory __SOURCE_DIRECTORY__;;

type AsyncEventQueue<'T>() = 
    let mutable cont = None 
    let queue = System.Collections.Generic.Queue<'T>()
    let tryTrigger() = 
        match queue.Count, cont with 
        | _, None -> ()
        | 0, _ -> ()
        | _, Some d -> 
            cont <- None
            d (queue.Dequeue())

    let tryListen(d) = 
        if cont.IsSome then invalidOp "multicast not allowed"
        cont <- Some d
        tryTrigger()

    member x.Post msg = queue.Enqueue msg; tryTrigger()
    member x.Receive() = 
        Async.FromContinuations (fun (cont,econt,ccont) -> 
            tryListen cont)

// An enumeration of the possible events 
type Message =
  | Download of string * Control * Control| Cancel | Web of string | Error | Cancelled 

// Global event queue.
let ev = AsyncEventQueue<Message>();;
 
type NimLauncher = L of List<string> * AsyncEventQueue<Message>;;

let splitString (text : string) =
  let lines  = text.Split [|' '|]
  let filter = Array.filter (fun (elem:string) -> elem.Length > 0) lines
  List.ofArray filter


let startGame (stringBuffer : string) = let gameUI = NimGame.create (splitString stringBuffer)
                                        printf "FIXME: Starting game %s\n" stringBuffer
                                        Application.Run (NimGame.window gameUI)


let winX = 640;;
let winY = 800;;
// The window part
let matchW = 50;;
let matchH = 80;;

let margin = 100;;

let form =
  new Form(Text="Nim game launcher", Size=Size(winX,winY), AutoScroll = true)


let urlBox =
  new TextBox(Location=Point(30,30),Size=Size(200,25));;

let downloadButton = 
  new Button(Location=Point(30, 70),MinimumSize=Size(60,35),
              MaximumSize=Size(90,35),Text="Download your game");;

let launchButton =
  new Button(Location=Point(110,70),MinimumSize=Size(60,35),
              MaximumSize=Size(90,35),Text="Launch your game");;
let status  =
  new Label (Location=Point(30,margin+30),Size=Size(200,30))

let makeDownloadButton x y text = 
  new Button(Location=Point(x,y),MinimumSize=Size(60,35),
              MaximumSize=Size(90,35),Text=text)

let makeLaunchButton x y = 
  new Button(Location=Point(x,y),MinimumSize=Size(60,35),
              MaximumSize=Size(90,35),Text="Launch");;

let cancelButton =
  new Button(Location=Point(250,margin+30),MinimumSize=Size(60,30),
              MaximumSize=Size(90,30),Text="Abort download")

let disable bs = 
    for (b : Control) in bs do 
        b.Enabled  <- false

// StateMachine
let rec ready() = 
  async {status.Text <- "Ready to download a game."
         let! msg = ev.Receive()
         match msg with
         | Download (url,launchButton, cancelButton) -> return! loading(url, launchButton, cancelButton)
         | Cancel       -> return! ready()
         | _            -> failwith("ready: unexpected message")}
  
and loading(url, launchButton, cancelButton) =
  async {status.Text <- "Downloading"
         use ts = new CancellationTokenSource()
         cancelButton.Enabled <- true
          // start the load
         Async.StartWithContinuations
             (async { let webCl = new WebClient()
                      let! html = webCl.AsyncDownloadString(Uri url)
                      return html },
              (fun html -> ev.Post (Web html)),
              (fun _ -> ev.Post Error),
              (fun _ -> ev.Post Cancelled),
              ts.Token)
         let! msg = ev.Receive()
         cancelButton.Enabled <- false
         match msg with
         | Web html ->
             let _ = html
             let ans = html
             launchButton.Enabled <- true;
             launchButton.Click. Add (fun (_) -> startGame html);

             return! finished(ans)
         | Error   -> return! finished("Error")
         | Cancel  -> ts.Cancel()
                      return! cancelling()
         | _       -> failwith("loading: unexpected message")}

and cancelling() =
  async {status.Text <- "Cancelling"
         let! msg = ev.Receive()
         match msg with
         | Cancelled | Error | Web  _ ->
                   return! finished("Cancelled")
         | _    ->  failwith("cancelling: unexpected message")}

and finished(s) =
  async {status.Text <-  s.Replace("\n", " ")
         disable [cancelButton]
         let! msg = ev.Receive()
         match msg with
         | Cancel -> return! ready()
         | Download (url, launchButton, cancelButton) -> return! loading(url, launchButton, cancelButton)
         | _      ->  failwith("finished: unexpected message")}

let rec generateBtns n = function 
 | []   -> []
 | x::xs -> let dwBtn = (upcast (makeDownloadButton 30 ((n+1)*40+margin+25)  ("Download game" + string (n+1))) : Control)
            let lncBtn = (upcast (makeLaunchButton 115 ((n+1)*40+margin+25)) : Control)
            (dwBtn, lncBtn, x) :: generateBtns (n+1) xs

let rec generateClickOptions cancelButton = function
    | []    -> ()
    | (dwBtn : Control, lncBtn, x) :: xs -> dwBtn.Click.Add (fun (_) -> printf "%s" x
                                                                        ev.Post (Download (x, lncBtn, cancelButton)))
                                            generateClickOptions cancelButton xs;;
let bgImage = Image.FromFile("kitty_cover.jpg");;
let create urls = L (urls, ev);;
//let show   : NimLancher   -> NimLancher
let rec window l =
    match l with 
    | L (urlList, ev) -> let panel =  new Panel (Location = Point(0,0), Size = Size(winX, winY), BackgroundImage = bgImage)
                         let buttons = generateBtns 0 urlList
                         let (dwBtns : Control list, lncBtns, y) = List.unzip3 buttons
                         generateClickOptions cancelButton buttons
                         disable ([cancelButton] @ lncBtns)
                         downloadButton.Click.Add (fun (_) -> ev.Post (Download (urlBox.Text, launchButton, cancelButton)))
                         panel.Controls.Add status
                         //cal a function with urlList argument that generates buttons and applies click function and generates 

                         panel.Controls.AddRange (List.toArray dwBtns)
                         panel.Controls.AddRange (List.toArray lncBtns)
                         panel.Controls.Add cancelButton
                         panel.Controls.Add urlBox
                         panel.Controls.Add downloadButton
                         panel.Controls.Add launchButton
                         form.Controls.Add panel
                         Async.StartImmediate (ready())
                         form