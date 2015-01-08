// Michael R. Hansen 03-01-2014
module AST
open System

type Exp = | Int of int 
           | Bool of bool 
           | String of string 
           | Var of string
           | ArrVar of string * Exp
           | ContOf of Exp 
           | Apply of string * List<Exp>
           | ArrExp of string * Exp
           | Attribute of string * string

and  Stm = | Asg of Exp * Exp
           | ArrAsg of string * Exp * Exp
           | PrintLn of Exp
           | Seq of List<Stm>
           | While of Exp * Stm
           | Block of List<Dec> * Stm
           | Call  of string * List<Exp>
           | Return of Exp
           | IfElse of Exp * Stm * Stm
           | Skip
and Dec  = | VarDec     of string * Exp
           | ProcDec    of string * List<string> * Stm
           | RecProcDec of string * List<string> * Stm
           | ArrDec     of string * Exp * Exp
           ;;