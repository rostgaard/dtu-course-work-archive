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

type NimGameState = State of List<int>
type GameEvent = Move of int * int | EndGame | StartGame of NimGameState

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
                            Array.set array index ((List.nth x index)- newValue)
                            Array.toList array


                            
let reflectMove (state:NimGameState) 
                (row:int)
                (col:int) = match state with
                            | State []    -> failwith "reflectMove: Bad state (empty)!"
                            | State s -> let listAsArr   = Array.ofList s
                                         let heapSize    = Array.get listAsArr row-1 //Account for index skew.
                                         let newHeapSize = heapSize - (heapSize - (col-1))
                                         Array.set listAsArr (row-1) newHeapSize
                                         State (List.ofArray listAsArr)

(* 
   Returns a gamestate from a string buffer. The buffer must be formatted so that
   every line represents a heap. It contains only one number that then represents
   the amount of matches in that heap

   string -> List<int> 
*)
let gameStateFromString (text : string) =
  let lines  = text.Split [|'\n'|]
  let filter = Array.filter (fun (elem:string) -> elem.Length > 0) lines
  List.map int ( (Array.toList filter));;


let gameEvent =  AsyncEventQueue<GameEvent>()
type NimGame =
  | Nim of NimGameState * NimPlayer
  //member obj.endTurn = match obj with
  //                     | Nim (state,player) -> gameEvent.Post (EndTurn (state,player))
  member obj.move row column = match obj with
                               | Nim (state,player) -> //let newState = reflectMove state row column
                                                       gameEvent.Post (Move (row,column))
  static member create intialState = Nim (State intialState, Human)

let numberOfHeaps = 5;;
let matches = [2;3;4;5;6];;

// The window part
let maxMatches matches = List.max matches;;
let matchW = 50;;
let matchH = 80;;
let totalMatchW = ((maxMatches matches) + 1) * matchW;;
let totalMatchH = (numberOfHeaps + 1) * matchH;;
let buttonW = 200;;
let buttonH = 400;;
let matchIcon = Image.FromFile("Match_Icon_small.png");;

let matchPanel  = new Panel(Location = Point(0,0), Size = Size(totalMatchW + buttonW, (max totalMatchH buttonH)), BackColor = Color.Black);;
let buttonPanel = new Panel(Location = Point(0,matchPanel.Height), Size = Size(matchPanel.Width, 300), BackColor = Color.White);;

let window =
  new Form(Text="Nim game", Size= Size(max matchPanel.Width buttonPanel.Width, 
                                       matchPanel.Height + buttonPanel.Height), 
                                       AutoScroll = true);;

  let matchButton (x : int) (y : int) z onClick = 
    let btn = new Button(Location = Point(x,y), MinimumSize=Size(25,matchH),
                         MaximumSize=Size(20,100),Text= z, BackColor = Color.Black, Image = matchIcon, FlatStyle = FlatStyle.Flat)
    btn.Click.Add (onClick)
    btn
 
let startButton =
  new Button(Location=Point(30,30),MinimumSize=Size(100,50),
              MaximumSize=Size(100,50),Text="Start new game")

let endTurnButton =
  new Button(Location=Point(startButton.Width+30+30,30),MinimumSize=Size(100,50),
              MaximumSize=Size(100,50),Text="End move")

let cancelButton =
  new Button(Location=Point(matchPanel.Height,265),MinimumSize=Size(100,50),
              MaximumSize=Size(100,50),Text="Give up")

              
let ansBox =
  new TextBox(Location=Point(totalMatchW,320),Size=Size(100,25))

  
let urlBox =
  new TextBox(Location=Point(totalMatchW,10),Size=Size(400,25))


let mutable ng = NimGame.create (matches);;

let handleMove (row:int, column:int) = gameEvent.Post (Move (row,column))

// Initialization
let rec generateMatches level = function
    | []    -> []
    | x::xs -> generateHeap level x @ generateMatches (level+1) xs
and generateHeap level = function
  | 0   -> []
  | n   -> ((upcast (matchButton (n*matchW-matchW/2) (level*matchH-matchH/2) (string level + "." + string n) (fun (_) -> handleMove (level,n)) )) : Control)::(generateHeap level (n-1));;

let getPosition (button : Control) = let positions = Array.toList (button.Text.Split [|'.'|])
                                     List.map (fun x -> int x) positions;;
let reduceState matches button = let list = getPosition button
                                 let index = List.nth list 0 
                                 let newArray = List.toArray matches
                                 Array.set newArray (index-1) (matches.[index-1] - 1)
                                 State(Array.toList  newArray);;
let generateButtonMatches matches = let (matchButtons : Control list) = (generateMatches 1 matches)
//                                    ignore(List.map (fun (button : Control) -> button.Click.Add (fun _ -> gameEvent.Post (Move (index button)))) matchButtons)
                                    List.toArray (matchButtons);;

//let disable bs = 
//    for b in [startButton;clearButton;cancelButton] do 
//        b.Enabled  <- true
//     for (b:Button) in bs do 
//        b.Enabled  <- false



let rec ready (gameSetup) =
    let state = gameSetup
    async {
       printf "Ready: Setting up a new game!\n"
       let! msg = gameEvent.Receive()
       match msg with 
       | EndGame               -> return! finish()
       | Move (heap,count)     -> return! move(state, heap, count)
       | StartGame (gameState) -> return! setupBoard()
    }

// Currently unused..
and setupBoard() =
    async {
        printf "SetupBoard: Got event!\n"
        let! msg = gameEvent.Receive()
        match msg with 
         | EndGame        -> return! finish()
         | _              -> failwith "implement me!"
         //| Move (state)   -> return! move(state)
         //| EndTurn (state, player) -> return! nextPlayer(state, player)
         //| StartGame      -> return! setupBoard()
        }

and move (state, heapindex, count) =
    matchPanel.Controls.Clear ()
    let newState = reflectMove state heapindex count

    // Update the matches panel with the new state
    match newState with
      | State [] -> failwith "Bad state"
      | State s  -> matchPanel.Controls.AddRange (generateButtonMatches s)

    async {
        printf "Move: Got event!\n"
        return! AIMove (newState)
    }

and AIMove (state) =
    matchPanel.Controls.Clear ()
    let newIntState = nextMove(state)
    matchPanel.Controls.AddRange (generateButtonMatches newIntState)
    async {
        printf "nextPlayer: Got event!\n"
        let! msg = gameEvent.Receive()
        match msg with 
         | EndGame               -> return! finish()
         | Move (heap,count)     -> return! move(State newIntState, heap, count)
         | StartGame (gameState) -> return! setupBoard()
        }
and finish () =
    async {
        printf "Finish: Got event!\n"
        let! msg = gameEvent.Receive()
        match msg with 
         | EndGame               -> return! finish()
         | Move (heap,count)     -> failwith "No can do, sir."
         | StartGame (gameState) -> return! setupBoard()
        }
(*
and nextPlayer (state, player) =
    matchPanel.Controls.Clear ()
    match player with
        | AI    -> let newState = nextMove(state)
                   matchPanel.Controls.AddRange (generateButtonMatches newState)
        | Human -> failwith "what"
    async {
        printf "nextPlayer: Got event!\n"
        let! msg = gameEvent.Receive()
        match msg with 
         | EndGame        -> return! finish()
         | Move (state)   -> return! move(state)
         | EndTurn (state, player) -> return! nextPlayer(state, player)
         | StartGame      -> return! setupBoard()
        }
*)

let (buttons : Control []) = generateButtonMatches matches;;
buttonPanel.Controls.Add startButton
buttonPanel.Controls.Add urlBox
buttonPanel.Controls.Add ansBox
buttonPanel.Controls.Add endTurnButton
buttonPanel.Controls.Add cancelButton
matchPanel.Controls.AddRange buttons
window.Controls.Add matchPanel
window.Controls.Add buttonPanel

let initialState = State [2;3;4;5;6]

startButton.Click.Add (fun _ -> gameEvent.Post (StartGame initialState))
//endTurnButton.Click.Add (fun _ -> ignore (ng.endTurn))
Async.StartImmediate (ready(initialState));

Application.Run(window)
//window.Show();;
