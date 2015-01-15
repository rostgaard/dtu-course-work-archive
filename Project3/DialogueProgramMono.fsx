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

let numberOfHeaps = 5;;
let matches = [2;3;4;5;6];;


// The window part
let maxMatches matches = List.max matches;;
let matchW = 50;;
let matchH = 50;;
let totalMatchW = ((maxMatches matches) + 1) * matchW;;
let totalMatchH = (numberOfHeaps + 1) * matchH;;
let buttonW = 200;;
let buttonH = 400;;
let window =
  new Form(Text="Web Source Length", Size= Size(totalMatchW + buttonW, (max totalMatchH buttonH)), AutoScroll = true);;
  
let panel = new Panel(Location = Point(0,0), Size = Size(totalMatchW + buttonW, (max totalMatchH buttonH)), BackColor = Color.Black);;

let matchButton (x : int) (y : int) z = 
  new Button(Location = Point(x,y), MinimumSize=Size(20,50),
              MaximumSize=Size(20,100),Text= z, BackColor = Color.AntiqueWhite)
 
let startButton =
  new Button(Location=Point(totalMatchW,65),MinimumSize=Size(100,50),
              MaximumSize=Size(100,50),Text="Start new game")

let clearButton =
  new Button(Location=Point(totalMatchW,165),MinimumSize=Size(100,50),
              MaximumSize=Size(100,50),Text="End move")

let cancelButton =
  new Button(Location=Point(totalMatchW,265),MinimumSize=Size(100,50),
              MaximumSize=Size(100,50),Text="Give up")

              
let ansBox =
  new TextBox(Location=Point(totalMatchW,10),Size=Size(100,25))


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
  async {
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
let rec generateMatches level = function
    | []    -> []
    | x::xs -> generateHeap level x @ generateMatches (level+1) xs
and generateHeap level = function
  | 0   -> []
  | n   -> ((upcast (matchButton (n*matchW) (level*matchH) (string n))) : Control)::(generateHeap level (n-1));;

panel.Controls.AddRange (List.toArray (generateMatches 1 matches));;
panel.Controls.Add ansBox
panel.Controls.Add startButton
panel.Controls.Add clearButton
panel.Controls.Add cancelButton
window.Controls.Add panel
startButton.Click.Add (fun _ -> ev.Post (Start "Nothing"))
clearButton.Click.Add (fun _ -> ev.Post Clear)
cancelButton.Click.Add (fun _ -> ev.Post Cancel)

// Start
Async.StartImmediate (ready())
window.Show()

