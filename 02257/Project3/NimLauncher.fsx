module NimLauncher

#load "NimGame.fsx"

open System 
open System.Net 
open System.Threading 
open System.Windows.Forms 
open System.Drawing 

open NimGame
open NimGUI

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

let dwnClickFunction x y z = ev.Post (Download (x,y,z));;
let splitString (text : string) =
  let lines  = text.Split [|' '|]
  let filter = Array.filter (fun (elem:string) -> elem.Length > 0) lines
  List.ofArray filter


let startGame (stringBuffer : string) = let gameUI = NimGame.create (splitString stringBuffer)
                                        printf "Starting game %s\n" stringBuffer
                                        (NimGame.window gameUI).Show();;
                                        // Application.Run (NimGame.window gameUI)


// StateMachine
let rec ready() = 
  async {NimGUI.status.Text <- "Ready to download a game."
         let! msg = ev.Receive()
         match msg with
         | Download (url,launchButton, cancelButton) -> return! loading(url, launchButton)
         | Cancel       -> return! ready()
         | _            -> failwith("ready: unexpected message")}
  
and loading(url, launchButton) =
  async {NimGUI.status.Text <- "Downloading"
         use ts = new CancellationTokenSource()
         NimGUI.cancelButton.Enabled <- true
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
         NimGUI.cancelButton.Enabled <- false
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
  async {NimGUI.status.Text <- "Cancelling"
         let! msg = ev.Receive()
         match msg with
         | Cancelled | Error | Web  _ ->
                   return! finished("Cancelled")
         | _    ->  failwith("cancelling: unexpected message")}

and finished(s) =
  async {NimGUI.status.Text <-  s.Replace("\n", " ")
         NimGUI.disable [NimGUI.cancelButton]
         let! msg = ev.Receive()
         match msg with
         | Cancel -> return! ready()
         | Download (url, launchButton, cancelButton) -> return! loading(url, launchButton)
         | _      ->  failwith("finished: unexpected message")}

let create urls = L (urls, ev);;
//let show   : NimLancher   -> NimLancher
let rec window l =
    match l with 
    | L (urlList, ev) -> let panel =  new Panel (Location = Point(0,0), Size = Size(NimGUI.winX, NimGUI.winY), BackgroundImage = NimGUI.bgImage, AutoScroll = true)
                         let buttons = NimGUI.generateBtns 0 urlList
                         let (dwBtns : Control list, lncBtns, y) = List.unzip3 buttons
                         NimGUI.generateClickOptions dwnClickFunction NimGUI.cancelButton buttons
                         NimGUI.disable ([NimGUI.cancelButton] @ lncBtns)
                         NimGUI.downloadButton.Click.Add (fun (_) -> ev.Post (Download (NimGUI.urlBox.Text, NimGUI.launchButton, NimGUI.cancelButton)))
                         panel.Controls.Add NimGUI.status
                         
                         panel.Controls.AddRange (List.toArray dwBtns)
                         panel.Controls.AddRange (List.toArray lncBtns)
                         panel.Controls.Add NimGUI.cancelButton
                         panel.Controls.Add NimGUI.urlBox
                         panel.Controls.Add NimGUI.downloadButton
                         panel.Controls.Add NimGUI.launchButton
                         NimGUI.form.Controls.Add panel
                         Async.StartImmediate (ready())
                         form