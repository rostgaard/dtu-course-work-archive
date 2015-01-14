// Code from Hansen and Rischel: Functional Programming using F#     1       6/12 2012
// Chapter 13: Asynchronous and parallel computations.          Revised MRH 25/11 2013 
// Code from Section 13.5: 13.5 Reactive programs.


// Prelude
open System 
open System.Net 
open System.Threading 
open System.Windows.Forms 
open System.Drawing 


// An asynchronous event queue kindly provided by Don Syme 
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



// The window part
let window =
  new Form(Text="Web Source Length", Size=Size(525,225))

let urlBox =
  new TextBox(Location=Point(50,25),Size=Size(400,25))

let ansBox =
  new TextBox(Location=Point(150,150),Size=Size(200,25))

let startButton =
  new Button(Location=Point(50,65),MinimumSize=Size(100,50),
              MaximumSize=Size(100,50),Text="START")

let clearButton =
  new Button(Location=Point(200,65),MinimumSize=Size(100,50),
              MaximumSize=Size(100,50),Text="CLEAR")

let cancelButton =
  new Button(Location=Point(350,65),MinimumSize=Size(100,50),
              MaximumSize=Size(100,50),Text="CANCEL")

let disable bs = 
    for b in [startButton;clearButton;cancelButton] do 
        b.Enabled  <- true
    for (b:Button) in bs do 
        b.Enabled  <- false

// An enumeration of the possible events 
type Message =
  | Start of string | Clear | Cancel | Web of string | Error | Cancelled 

//exception UnexpectedMessage

// The dialogue automaton 
let ev = AsyncEventQueue()
let rec ready() = 
  async {urlBox.Text <- "http://"
         ansBox.Text <- ""

         disable [cancelButton]
         let! msg = ev.Receive()
         match msg with
         | Start url -> return! loading(url)
         | Clear     -> return! ready()
         | _         -> failwith("ready: unexpected message")}
  
and loading(url) =
  async {ansBox.Text <- "Downloading"
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

         disable [startButton; clearButton]   
         let! msg = ev.Receive()
         match msg with
         | Web html ->
             let ans = "Length = " + String.Format("{0:D}",html.Length)
             return! finished(ans)
         | Error   -> return! finished("Error")
         | Cancel  -> ts.Cancel()
                      return! cancelling()
         | _       -> failwith("loading: unexpected message")}

and cancelling() =
  async {ansBox.Text <- "Cancelling"
         
         disable [startButton; clearButton; cancelButton]
         let! msg = ev.Receive()
         match msg with
         | Cancelled | Error | Web  _ ->
                   return! finished("Cancelled")
         | _    ->  failwith("cancelling: unexpected message")}

and finished(s) =
  async {ansBox.Text <- s
         
         disable [startButton; cancelButton]
         let! msg = ev.Receive()
         match msg with
         | Clear -> return! ready()
         | _     ->  failwith("finished: unexpected message")}

// Initialization
window.Controls.Add urlBox
window.Controls.Add ansBox
window.Controls.Add startButton
window.Controls.Add clearButton
window.Controls.Add cancelButton
startButton.Click.Add (fun _ -> ev.Post (Start urlBox.Text))
clearButton.Click.Add (fun _ -> ev.Post Clear)
cancelButton.Click.Add (fun _ -> ev.Post Cancel)

// Start
Async.StartImmediate (ready())
Application.Run(window)
// window.Show()

