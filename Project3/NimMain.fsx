

#r "AsyncEventQueue.dll"
#r "NimLauncher.dll"

// Prelude
open NimLauncher
open System 
open System.Net 
open System.Threading 
open System.Windows.Forms 
open System.Drawing 

let baseUrl = "http://ada-dk.org/files/";;
let games = ["1.nimgame"; "2.nimgame"; "nonexisting.nimgame"];;

let launcher = NimLauncher.create (List.map (fun (item) -> baseUrl + item) games) ;;
Application.Run (NimLauncher.window launcher);;
