module NimGame

#load "NimGUI.fsx"
#load "NimAI.fsx"

open System 
open System.Net 
open System.Threading 
open System.Windows.Forms 
open System.Drawing 
open NimGUI
open NimAI

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


let gameEvent =  AsyncEventQueue<GameEvent>()

//GUI elements
let clickFunction x y = gameEvent.Post (Move (x,y));;
let matchPanel  = new Panel();;
let buttonPanel = new Panel();;
let form = new Form();;

//game loop helper functions
let areAllZeroes (state : int list) = List.forall (fun x -> x = 0) state;;
let simpler (State x) = x;;

//game loop
let rec ready (gameSetup) =
    let (buttons : Control []) = NimGUI.generateButtonMatches gameSetup clickFunction
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

    let newState = (NimGUI.reduceState (state) heapindex count)
    
    // Update the matches panel with the new state
    match newState with
      | [] -> failwith "Bad state"
      | s  -> matchPanel.Controls.AddRange (generateButtonMatches s clickFunction)

    async {
        printf "Move: Got event!\n"
        match newState with 
            | [] -> failwith "Bad state"
            | s -> if areAllZeroes s then
                    return! finish(Human)
                   else
                    return! AIMove (newState)
    }

and AIMove (state) =
    matchPanel.Controls.Clear ()
    let newIntState = NimAI.nextMove state
    matchPanel.Controls.AddRange (generateButtonMatches newIntState clickFunction)
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
        NimGUI.updateFinishScreen (player <> AI) matchPanel
        let! msg = gameEvent.Receive()
        match msg with 
         | EndGame player        -> return! finish(player)
         | Move (heap,count)     -> failwith "No can do, sir."
         | StartGame (gameState) -> return! ready(simpler gameState)
        }



//game setup
let create gameRepr = GameUI (gameRepr, gameEvent);;

let window obj =
    match obj with 
    | GameUI (gameRepr, ev) -> let intList = List.map int gameRepr
                               NimGUI.updatePanels intList matchPanel buttonPanel form
                               let resetBtn = NimGUI.resetBtn buttonPanel
                               form.Controls.Add matchPanel
                               form.Controls.Add buttonPanel
                               buttonPanel.Controls.Add resetBtn
                               resetBtn.Click.Add (fun _ -> gameEvent.Post (StartGame (State intList)))
                               Async.StartImmediate (ready intList)
                               form
