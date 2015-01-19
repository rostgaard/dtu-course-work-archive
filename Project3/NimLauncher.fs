module NimLauncher

open System 
open System.Net 
open System.Threading 
open System.Windows.Forms 
open System.Drawing 

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
  | Download of string * Button| Cancel | Web of string | Error | Cancelled 

// Global event queue.
let ev = AsyncEventQueue<Message>();;
 
type NimLauncher = L of List<string> * AsyncEventQueue<Message>;;

let startGame stringBuffer = printf "FIXME: Starting game %s\n" stringBuffer

// The window part
let maxMatches matches = List.max matches;;
let matchW = 50;;
let matchH = 80;;
let totalMatchW = (5 + 1) * matchW;;
let totalMatchH = (5 + 1) * matchH;;
let buttonW = 200;;
let buttonH = 400;;
let matchIcon = Image.FromFile("Match_Icon_small.png");;

let form =
  new Form(Text="Nim game launcher", Size=Size(400,600))

let status  =
  new Label (Location=Point(30,30),Size=Size(200,30))

let button1 =
  new Button(Location=Point(30,60),MinimumSize=Size(60,30),
              MaximumSize=Size(90,30),Text="Download Game 1")

let button1launch =
  new Button(Location=Point(120,60),MinimumSize=Size(60,30),
              MaximumSize=Size(90,30),Text="Launch")

let button2 =
  new Button(Location=Point(30,90),MinimumSize=Size(60,30),
              MaximumSize=Size(90,30),Text="Download Game 2")

let button2launch =
  new Button(Location=Point(120,90),MinimumSize=Size(60,30),
              MaximumSize=Size(90,30),Text="Launch")

let button3 =
  new Button(Location=Point(30,120),MinimumSize=Size(60,30),
              MaximumSize=Size(90,30),Text="Download Game 3")

let button3launch =
  new Button(Location=Point(120,120),MinimumSize=Size(60,30),
              MaximumSize=Size(90,30),Text="Launch")

let cancelButton =
  new Button(Location=Point(30,150),MinimumSize=Size(60,30),
              MaximumSize=Size(90,30),Text="Abort download")

let disable bs = 
    for b in [button1;button2;button3] do 
        b.Enabled  <- true
    for (b:Button) in bs do 
        b.Enabled  <- false

let downloadButton (x : int) (y : int) z = 
  new Button(Location = Point(x,y), MinimumSize=Size(25,matchH),
              MaximumSize=Size(20,100),Text= z, BackColor = Color.Black, Image = matchIcon, FlatStyle = FlatStyle.Flat)

// StateMachine
let rec ready() = 
  async {status.Text <- "Ready to download a game."
         let! msg = ev.Receive()
         match msg with
         | Download (url,launchButton) -> return! loading(url, launchButton)
         | Cancel       -> return! ready()
         | _            -> failwith("ready: unexpected message")}
  
and loading(url, launchButton) =
  async {status.Text <- "Downloading"
         use ts = new CancellationTokenSource()

          // start the load
         Async.StartWithContinuations
             (async { let webCl = new WebClient()
                      let! html = webCl.AsyncDownloadString(Uri url)
                      return html },
              (fun html -> ev.Post (Web html)),
              (fun _ -> ev.Post Error),
              (fun _ -> ev.Post Cancelled),
              ts.Token)
         disable [button1; button2; button3]   
         let! msg = ev.Receive()
         match msg with
         | Web html ->
             let _ = html
             let ans = html
             launchButton.Enabled <- true;
             launchButton.Click.Add (fun (_) -> startGame html);

             return! finished(ans)
         | Error   -> return! finished("Error")
         | Cancel  -> ts.Cancel()
                      return! cancelling()
         | _       -> failwith("loading: unexpected message")}

and cancelling() =
  async {status.Text <- "Cancelling"
         
         disable [button1; button2; button3]   
         let! msg = ev.Receive()
         match msg with
         | Cancelled | Error | Web  _ ->
                   return! finished("Cancelled")
         | _    ->  failwith("cancelling: unexpected message")}

and finished(s) =
  async {status.Text <- s
         disable [cancelButton]
         let! msg = ev.Receive()
         match msg with
         | Cancel -> return! ready()
         | Download (url, launchButton) -> return! loading(url, launchButton)
         | _      ->  failwith("finished: unexpected message")}



let create urls = L (urls, ev);;
//let show   : NimLancher   -> NimLancher
let rec window l =
    match l with 
    | L (urlList, ev) -> let panel =  new Panel (Location = Point(0,0), Size = Size(200, 300))
                         //let buttons = List.fo map (fun url -> url) urlList
                         disable [cancelButton; button1launch; button2launch; button3launch]

                         panel.Controls.Add status

                         button1.Click.Add (fun (_) -> ev.Post (Download ((Array.get (Array.ofList urlList) 0), button1launch)))
                         button2.Click.Add (fun (_) -> ev.Post (Download ((Array.get (Array.ofList urlList) 1), button2launch)))
                         button3.Click.Add (fun (_) -> ev.Post (Download ((Array.get (Array.ofList urlList) 2), button3launch)))

                         panel.Controls.Add button1
                         panel.Controls.Add button2
                         panel.Controls.Add button3
                         panel.Controls.Add cancelButton
                         panel.Controls.Add button1launch
                         panel.Controls.Add button2launch
                         panel.Controls.Add button3launch
                         form.Controls.Add panel
                         Async.StartImmediate (ready())
                         form