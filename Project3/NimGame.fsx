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
type GameEvent = Move of AsyncEventQueue<GameEvent> * int * int | EndGame of AsyncEventQueue<GameEvent> * NimPlayer 
                 | StartGame of AsyncEventQueue<GameEvent> * NimGameState
type NimGameUI = GameUI of string list * AsyncEventQueue<GameEvent>

let clickFunction (ev : AsyncEventQueue<GameEvent>) x y = ev.Post (Move (ev, x,y));;
//GUI elements
let mutable matchPanel  = new Panel();;

//game loop helper functions
let areAllZeroes (state : int list) = List.forall (fun x -> x = 0) state;;
let simpler (State x) = x;;

//game loop
let rec ready (ev, gameSetup) =
    let (buttons : Control []) = NimGUI.generateButtonMatches gameSetup (clickFunction ev)
    matchPanel.Controls.AddRange buttons
    matchPanel.BackColor <- Color.Black
    matchPanel.BackgroundImage <- null
    let state = gameSetup
    async {
       printf "Ready: Setting up a new game!\n"
       let! msg = ev.Receive()
       match msg with 
       | EndGame (ev, player)     -> return! finish(ev, player)
       | Move (ev, heap,count)     -> return! move(ev, state, heap, count)
       | StartGame (ev, gameState)-> return! ready (ev, simpler gameState)
    }
and move (ev, state, heapindex, count) =
    matchPanel.Controls.Clear ()

    let newState = (NimGUI.reduceState (state) heapindex count)
    
    // Update the matches panel with the new state
    match newState with
      | [] -> failwith "Bad state"
      | s  -> matchPanel.Controls.AddRange (generateButtonMatches s (clickFunction ev))

    async {
        printf "Move: Got event!\n"
        match newState with 
            | [] -> failwith "Bad state"
            | s -> if areAllZeroes s then
                    return! finish(ev, Human)
                   else
                    return! AIMove (ev, newState)
    }

and AIMove (ev, state) =
    matchPanel.Controls.Clear ()
    let newIntState = NimAI.nextMove state
    matchPanel.Controls.AddRange (generateButtonMatches newIntState (clickFunction ev))
    async {
        printf "nextPlayer: Got event!\n"
        if areAllZeroes newIntState then
            return! finish(ev, AI)
        else
            let! msg = ev.Receive()
            match msg with
              | EndGame (ev, player)      -> return! finish(ev, player)
              | Move (ev, heap,count)     -> return! move(ev, newIntState, heap, count)
              | StartGame (ev, gameState) -> return! ready(ev, simpler gameState)
    }
and finish (ev, player) =
    async {
        printf "Finish: Got event!\n %s \n" (toString player)
        NimGUI.updateFinishScreen (player <> AI) matchPanel
        let! msg = ev.Receive()
        match msg with 
         | EndGame (ev, player)        -> return! finish(ev, player)
         | Move (ev, heap,count)     -> failwith "No can do, sir."
         | StartGame (ev, gameState) -> return! ready(ev, simpler gameState)
        }


let resetClickOption (ev :  AsyncEventQueue<GameEvent>) state level = fun _ -> NimAI.level <- level
                                                                               ev.Post(StartGame(ev, State state));;
                                                                               
let rec addResetClickOption ev state level = function
    | _ when level <= 0  -> ()
    | []                 -> ()
    | (x : Control)::xs  -> x.Click.Add (resetClickOption ev state level)
                            addResetClickOption ev state (level-1) xs;;

//game setup
let create gameRepr = GameUI (gameRepr, AsyncEventQueue<GameEvent>());;

let window obj =
    match obj with 
    | GameUI (gameRepr, ev) ->  let intList = List.map int gameRepr
                                let form = new Form()
                                let buttonPanel = new Panel()
                                matchPanel <- new Panel()
                                NimGUI.updatePanels intList matchPanel buttonPanel form
                                form.Controls.Add matchPanel
                                form.Controls.Add buttonPanel
                                let resetBtns = NimGUI.createResetBtn buttonPanel
                                addResetClickOption ev intList 3 (Array.toList resetBtns)
                                buttonPanel.Controls.AddRange resetBtns
                                Async.StartImmediate (ready (ev, intList))
                                form
