(* Build as dll before launching main.*)

module NimLauncher

open System.Windows.Forms 

type NimLauncher
val create : List<string> -> NimLauncher
val window : NimLauncher  -> System.Windows.Forms.Form
