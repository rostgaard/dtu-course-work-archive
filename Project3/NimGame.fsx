module NimGame

open System 
open System.Net 
open System.Threading 
open System.Windows.Forms 
open System.Drawing 

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


type NimPlayer = AI | Human;;

let toString = function
 | AI -> "AI"
 | Human -> "Human";;

type NimGameState = State of List<int>
type GameEvent = Move of int * int | EndGame of NimPlayer| StartGame of NimGameState

type NimGameUI = GameUI of List<string> * AsyncEventQueue<GameEvent>;;

let gameStateToString (gs : List<int>)  = string (List.map string gs);; 

let rec calculateM n = function
    | []    -> n
    | x::xs -> calculateM (n ^^^ x) xs;;


let rec getNewAk m index = function 
 | []    -> failwith "getNewAk: Cannot find appropriate move from an empty list!"
 | x::xs -> let newV = x ^^^ m 
            if newV < x then
                (newV, index)
            else 
                getNewAk m (index+1) xs

let nextMove (State x) = let m = calculateM 0 x
                         if m = 0 then
                            let maxi  = List.max x
                            let index = List.findIndex (fun y -> (y = maxi)) x
                            let array = List.toArray x
                            Array.set array index (maxi-1)

                            printf  "m = 0: newval:%d idx:%d" (maxi-1) index 
                            Array.toList array
                         else 
                            let (newValue, index) = getNewAk m 0 x
                            printf  "m /= 0: newval:%d idx:%d" newValue index  
                            let array = List.toArray x
                            Array.set array index (newValue)
                            Array.toList array

(* 
   Returns a gamestate from a string buffer. The buffer must be formatted so that
   every line represents a heap. It contains only one number that then represents
   the amount of matches in that heap

   string -> List<int> 
*)
let gameStateFromString (text : string) =
  let lines  = text.Split [|' '|]
  let filter = Array.filter (fun (elem:string) -> elem.Length > 0) lines
  List.map int ( (Array.toList filter));;


let gameEvent =  AsyncEventQueue<GameEvent>()
(*
type NimGame =
  | Nim of NimGameState * NimPlayer
  //member obj.endTurn = match obj with
  //                     | Nim (state,player) -> gameEvent.Post (EndTurn (state,player))
  member obj.move row column = match obj with
                               | Nim (state,player) -> //let newState = reflectMove state row column
                                                       gameEvent.Post (Move (row,column))
  static member create intialState = Nim (State intialState, Human)
*)
// The window part
let maxMatches matches = List.max matches;;
let matchW = 50;;
let matchH = 80;;
let totalMatchW = (8 + 1) * matchW;;
let totalMatchH = (6 + 1) * matchH;;
let buttonW = 200;;
let buttonH = 100;;
let matchIcon = Image.FromFile("Match_Icon_small.png");;
let kittenW = 570;;
let kittenH = 456;;
let matchPanel  = new Panel(Location = Point(0,0), Size = Size(max (totalMatchW + buttonW) kittenW, (max totalMatchH kittenH)), BackColor = Color.Black);;
let buttonPanel = new Panel(Location = Point(0,matchPanel.Height), Size = Size(matchPanel.Width, buttonH), BackColor = Color.White);;

let form = new Form(Text="Nim game", Size= Size(max (matchPanel.Width + 50) (kittenW + 50),
                        (max matchPanel.Height kittenH) + 50 + buttonPanel.Height),
                         AutoScroll = true);;
let matchButton (x : int) (y : int) z onClick = let btn = new Button(Location = Point(x,y), MinimumSize=Size(25,matchH),
                                                                      MaximumSize=Size(20,100),Text= z, BackColor = Color.Black, Image = matchIcon, FlatStyle = FlatStyle.Flat)
                                                btn.Click.Add (onClick)
                                                btn
let resetBtn =
      new Button(Location=Point((buttonPanel.Width-100)/2,(buttonPanel.Height-50)/2),MinimumSize=Size(100,50),
                  MaximumSize=Size(100,50),Text="Reset Game")

 
let handleMove (row:int, column:int) = gameEvent.Post (Move (row,column))

// Initialization
let rec generateMatches level state = 
    match state with
    | []    -> []
    |  x::xs -> generateHeap level x @ generateMatches (level+1) xs
and generateHeap level = function
  | 0   -> []
  | n   -> ((upcast (matchButton (n*matchW-matchW/2) (level*matchH-matchH/2) (string level + "." + string n) (fun (_) -> handleMove (level,n)) )) : Control)::(generateHeap level (n-1));;

let reduceState matches index position = let newArray = List.toArray matches
                                         Array.set newArray (index-1) (position-1)
                                         State(Array.toList  newArray);;

let generateButtonMatches matches = let (matchButtons : Control list) = (generateMatches 1 matches)
//                                    ignore(List.map (fun (button : Control) -> button.Click.Add (fun _ -> gameEvent.Post (Move (index button)))) matchButtons)
                                    List.toArray (matchButtons);;

//let disable bs = 
//    for b in [startButton;clearButton;cancelButton] do 
//        b.Enabled  <- true
//     for (b:Button) in bs do 
//        b.Enabled  <- false

let areAllZeroes (state : int list) = List.forall (fun x -> x = 0) state;;
let simpler (State x) = x;;

let rec ready (gameSetup) =
    let (buttons : Control []) = generateButtonMatches gameSetup
    matchPanel.Controls.AddRange buttons
    matchPanel.BackColor <- Color.Black
    matchPanel.BackgroundImage <- null
    let state = gameSetup
    async {
       printf "Ready: Setting up a new game!\n"
       let! msg = gameEvent.Receive()
       match msg with 
       | EndGame player        -> return! finish(player)
       | Move (heap,count)     -> return! move(state, heap, count)
       | StartGame (gameState) -> return! ready(gameSetup)
    }
and move (state, heapindex, count) =
    matchPanel.Controls.Clear ()

    let newState = reduceState (state) heapindex count
    
    // Update the matches panel with the new state
    match newState with
      | State [] -> failwith "Bad state"
      | State s  -> matchPanel.Controls.AddRange (generateButtonMatches s)

    async {
        printf "Move: Got event!\n"
        match newState with 
            | State [] -> failwith "Bad state"
            | State s -> if areAllZeroes s then
                            return! finish(Human)
                         else
                            return! AIMove (newState)
    }

and AIMove (state) =
    matchPanel.Controls.Clear ()
    let newIntState = nextMove(state)
    matchPanel.Controls.AddRange (generateButtonMatches newIntState)
    async {
        printf "nextPlayer: Got event!\n"
        if areAllZeroes newIntState then
            return! finish(AI)
        else
            let! msg = gameEvent.Receive()
            match msg with
              | EndGame  player       -> return! finish(player)
              | Move (heap,count)     -> return! move(newIntState, heap, count)
              | StartGame (gameState) -> return! ready(simpler gameState)
    }
and finish (player) =
    async {
        printf "Finish: Got event!\n %s \n" (toString player)
        if player = Human then
            let image = Image.FromFile("won.jpg")
            matchPanel.BackgroundImage <- image
        else
            let image = Image.FromFile("lost.jpg")
            matchPanel.BackgroundImage <- image
        matchPanel.Size <- Size(kittenW, kittenH)
        let! msg = gameEvent.Receive()
        match msg with 
         | EndGame player        -> return! finish(player)
         | Move (heap,count)     -> failwith "No can do, sir."
         | StartGame (gameState) -> return! ready(simpler gameState)
        }


let create gameRepr = GameUI (gameRepr, gameEvent);;

let window obj =
    match obj with 
    | GameUI (gameRepr, ev) -> let intList = List.map int gameRepr
                               form.Controls.Add matchPanel
                               form.Controls.Add buttonPanel
                               buttonPanel.Controls.Add resetBtn
                               resetBtn.Click.Add (fun _ -> gameEvent.Post (StartGame (State intList)))
                               Async.StartImmediate (ready intList)
                               form
