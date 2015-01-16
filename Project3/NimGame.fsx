#load "AsyncEventQueue.fs"
open AsyncEventQueue
open System 
open System.Net 
open System.Threading 
open System.Windows.Forms 
open System.Drawing 


System.IO.Directory.SetCurrentDirectory __SOURCE_DIRECTORY__;;


type NimPlayer = AI | Human;;

type NimGameState = State of List<int>
type GameEvent = EndTurn of NimPlayer | Move of NimGameState| EndGame | StartGame

let gameStateToString (gs : List<int>)  = string (List.map string gs);; 

let rec calculateM n = function
    | []    -> n
    | x::xs -> calculateM (n ^^^ x) xs;;

let rec getNewAk m index = function 
 | []    -> failwith "Cannot find appropriate move"
 | x::xs -> let newV = x ^^^ m
            if newV < x then
                (newV, index)
            else 
                getNewAk m (index+1) xs

let nextMove (State x) = let m = calculateM 0 x
                         if m = 0 then
                            let maxi = List.max x
                            let index = List.findIndex (fun y -> (y = maxi)) x
                            (index, 1)
                         else 
                            let (newValue, index) = getNewAk m 0 x
                            (index, (List.nth x index)- newValue);;



                            
let reflectMove (state:NimGameState) 
                (row:int)
                (col:int) = printf "%s" (string state)
                            match state with
                            | State []    -> failwith "Bad state!"
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
  member obj.endTurn = match obj with
                       | Nim (state,Human) -> gameEvent.Post (EndTurn Human)
                                              Nim (state,AI)
                       | Nim (state,AI)    -> gameEvent.Post (EndTurn AI)
                                              Nim (state,Human)
  member obj.move row column = match obj with
                               | Nim (state,player) -> let newState = reflectMove state row column
                                                       gameEvent.Post (Move (newState)) 
                                                       Nim (newState,AI)
  static member create intialState = Nim (State intialState, Human)


//ng <- ng.endTurn;;
///ng <- ng.move 1 2;;
//ng <- ng.endTurn;;

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

let handleMove (row:int, column:int) = //printf "%d %d \n" row column
                                       ng <- (ng.move row column)

// Initialization
let rec generateMatches level = function
    | []    -> []
    | x::xs -> generateHeap level x @ generateMatches (level+1) xs
and generateHeap level = function
  | 0   -> []
  | n   -> ((upcast (matchButton (n*matchW-matchW/2) (level*matchH-matchH/2) (string level + "." + string n) (fun (_) -> handleMove (level,n)) )) : Control)::(generateHeap level (n-1));;

//let disable bs = 
//    for b in [startButton;clearButton;cancelButton] do 
//        b.Enabled  <- true
//     for (b:Button) in bs do 
//        b.Enabled  <- false



let rec ready () =
    async {
       printf "Ready: Got event!\n"
       let! msg = gameEvent.Receive()
       match msg with 
       | EndGame        -> return! finish()
       | Move (state)   -> return! move(state)
       | EndTurn player -> return! nextPlayer(player)
       | StartGame      -> return! setupBoard()
    }

and setupBoard() =
    async {
        printf "SetupBoard: Got event!\n"
        let! msg = gameEvent.Receive()
        match msg with 
         | EndGame        -> return! finish()
         | Move (state)   -> return! move(state)
         | EndTurn player -> return! nextPlayer(player)
         | StartGame      -> return! setupBoard()
        }

and move (state) =
    matchPanel.Controls.Clear ()    
    match state with
      | State [] -> failwith "Bad state"
      | State s  -> matchPanel.Controls.AddRange (List.toArray (generateMatches 1 s))

    async {
        printf "Move: Got event!\n"
        let! msg = gameEvent.Receive()
        match msg with 
         | EndGame        -> return! finish()
         | Move (state)   -> return! move(state)
         | EndTurn player -> return! nextPlayer(player)
         | StartGame      -> return! setupBoard()
        }

and finish () =
    async {
        printf "Finish: Got event!\n"
        let! msg = gameEvent.Receive()
        match msg with 
         | EndGame        -> return! finish()
         | Move (state)   -> return! move(state)
         | EndTurn player -> return! nextPlayer(player)
         | StartGame      -> return! setupBoard()
        }

and nextPlayer (player) =
    async {
        printf "nextPlayer: Got event!\n"
        let! msg = gameEvent.Receive()
        match msg with 
         | EndGame        -> return! finish()
         | Move (state)   -> return! move(state)
         | EndTurn player -> return! nextPlayer(player)
         | StartGame      -> return! setupBoard()
        }

let buttons = List.toArray (generateMatches 1 matches);;
Array.map (fun (button : Control) -> button.Click.Add (fun _ -> gameEvent.Post (Move (State([1;2;3]))))) buttons;;
buttonPanel.Controls.Add startButton
buttonPanel.Controls.Add urlBox
buttonPanel.Controls.Add ansBox
buttonPanel.Controls.Add endTurnButton
buttonPanel.Controls.Add cancelButton
matchPanel.Controls.AddRange buttons
window.Controls.Add matchPanel
window.Controls.Add buttonPanel

startButton.Click.Add (fun _ -> gameEvent.Post (StartGame))
endTurnButton.Click.Add (fun _ -> ignore (ng.endTurn))
Async.StartImmediate (ready());

//Application.Run(window)
window.Show();;
