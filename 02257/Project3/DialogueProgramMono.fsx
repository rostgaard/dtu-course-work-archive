// Code from Hansen and Rischel: Functional Programming using F#     1       6/12 2012
// Chapter 13: Asynchronous and parallel computations.          Revised MRH 25/11 2013 
// Code from Section 13.5: 13.5 Reactive programs.


// Prelude
open System 
open System.Net 
open System.Threading 
open System.Windows.Forms
open System.Drawing 


System.IO.Directory.SetCurrentDirectory __SOURCE_DIRECTORY__;;

type NimGame   = State of List<int>;;
type NimPlayer = AI | Human;;
//type NimGame = NimGameState * NimPlayer;;

let baseUrl = "http://ada-dk.org/files/";;
let game = ["1.nimgame"; "2.nimgame";];;

let gameUrl idx = baseUrl + (game.Item idx);;

let gameDownloadButtons games = List.map games 

let toGameState (text : string) =
  let lines  = text.Split [|'\n'|]
  let filter = Array.filter (fun (elem:string) -> elem.Length > 0) lines
  List.map int ( (Array.toList filter));;

let test = toGameState "1\n2\n3\n4";;

let gameStateToString (gs : int list)  = string (List.map string gs);; 

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
let  mutable  matches = [3;4;4;5;6];;

let pop idx offset = let curcount = matches.Item (idx-1)
                     let newcount = curcount - (curcount - (offset-1))
                     let arr = Array.ofList matches
                     Array.set arr (idx-1) newcount
                     List.ofArray arr

// The window part
let maxMatches matches = List.max matches;;
let matchW = 50;;
let matchH = 80;;
let totalMatchW = ((maxMatches matches) + 1) * matchW;;
let totalMatchH = (numberOfHeaps + 1) * matchH;;
let buttonW = 200;;
let buttonH = 400;;
let matchIcon = Image.FromFile("Match_Icon_small.png");;


let window =
  new Form(Text="Web Source Length", Size= Size(totalMatchW + buttonW, (max totalMatchH buttonH)), AutoScroll = true);;
  
let panel = new Panel(Location = Point(0,0), Size = Size(totalMatchW + buttonW, (max totalMatchH buttonH)), BackColor = Color.White);;

let matchButton (x : int) (y : int) z level = 
    let btn = new Button(Location = Point(x,y), MinimumSize=Size(25,matchH),
                     MaximumSize=Size(20,100),Text= z, BackColor = Color.White, Image = matchIcon, FlatStyle = FlatStyle.Flat)
    btn.Click.Add (fun _ -> (matches <- (pop (int level) (int z))))
    btn;;
 
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
  new TextBox(Location=Point(totalMatchW,320),Size=Size(100,25))

  
let urlBox =
  new TextBox(Location=Point(totalMatchW,10),Size=Size(400,25))



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
  async {urlBox.Text <- "http://ada-dk.org/files/2.nimgame"
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
             let _ = toGameState html
             let ans = gameStateToString (toGameState html)
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
  | n   -> ((upcast (matchButton (n*matchW-matchW/2) (level*matchH-matchH/2) (string n) (string level))) : Control)::(generateHeap level (n-1));;

let buttons = generateMatches 1 matches
panel.Controls.AddRange (List.toArray buttons);;
panel.Controls.Add urlBox
panel.Controls.Add ansBox
panel.Controls.Add startButton
panel.Controls.Add cancelButton

window.Controls.Add panel
startButton.Click.Add (fun _ -> ev.Post (Start "Nothing"))
clearButton.Click.Add (fun _ -> ev.Post Clear)


// Start
Async.StartImmediate (ready())
//Application.Run(window)
window.Show()

