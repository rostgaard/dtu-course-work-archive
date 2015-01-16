#load "AsyncEventQueue.fs"

// Prelude
open AsyncEventQueue
open System 
open System.Net 
open System.Threading 
open System.Windows.Forms 
open System.Drawing 

type NimPlayer = AI | Human;;

type NimGameState = State of List<int>
type GameEvent = EndTurn of NimPlayer | Move of int * int | EndGame | StartGame

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
                               | Nim (state,player) -> gameEvent.Post (Move (row,column)) 
                                                       Nim (state,AI)
  static member create intialState = Nim (State intialState, Human)


let mutable ng = NimGame.create ([2;3]);;

let rec ready () =
    async {
       printf "Ready: Got event!\n"
       let! msg = gameEvent.Receive()
       match msg with 
       | EndGame        -> return! finish()
       | Move (x,y)     -> return! move(x,y)
       | EndTurn player -> return! nextPlayer(player)
       | StartGame      -> return! setupBoard()
    }

and setupBoard() =
    async {
        printf "SetupBoard: Got event!\n"
        let! msg = gameEvent.Receive()
        match msg with 
         | EndGame        -> return! finish()
         | Move (x,y)     -> return! move(x,y)
         | EndTurn player -> return! nextPlayer(player)
         | StartGame      -> return! setupBoard()
        }

and move (x,y) =
    async {
        printf "Move: Got event!\n"
        let! msg = gameEvent.Receive()
        match msg with 
         | EndGame        -> return! finish()
         | Move (x,y)     -> return! move(x,y)
         | EndTurn player -> return! nextPlayer(player)
         | StartGame      -> return! setupBoard()
        }

and finish () =
    async {
        printf "Finish: Got event!\n"
        let! msg = gameEvent.Receive()
        match msg with 
         | EndGame        -> return! finish()
         | Move (x,y)     -> return! move(x,y)
         | EndTurn player -> return! nextPlayer(player)
         | StartGame      -> return! setupBoard()
        }

and nextPlayer (player) =
    async {
        printf "nextPlayer: Got event!\n"
        let! msg = gameEvent.Receive()
        match msg with 
         | EndGame        -> return! finish()
         | Move (x,y)     -> return! move(x,y)
         | EndTurn player -> return! nextPlayer(player)
         | StartGame      -> return! setupBoard()
        }
Async.StartImmediate (ready());

ng <- ng.endTurn;;
ng <- ng.move 1 2;;
ng <- ng.endTurn;;

