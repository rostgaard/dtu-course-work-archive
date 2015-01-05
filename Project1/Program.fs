module Program

open AST
open System
open ParserUtil
open Interpreter


// Create an initial environment

let plusInt = Primitive( fun [IntVal i1; IntVal i2] -> IntVal(i1+i2) );;
let minusInt = Primitive( fun [IntVal i1; IntVal i2] -> IntVal(i1-i2) );;
let multInt = Primitive( fun [IntVal i1; IntVal i2] -> IntVal(i1*i2) );;
let eqInt = Primitive( fun [IntVal i1; IntVal i2] -> BoolVal(i1=i2) );;
let neqInt = Primitive( fun [IntVal i1; IntVal i2] -> BoolVal(i1<>i2) );;
let lessEqInt = Primitive( fun [IntVal i1; IntVal i2] -> BoolVal(i1<=i2) );;
let lessInt = Primitive( fun [IntVal i1; IntVal i2] -> BoolVal(i1<i2) );;
let gen = let generator = new System.Random()
          generator.Next;;   
let randomInt = Primitive( fun [IntVal rng] -> IntVal (gen rng) );; 
let toString = let f vs =  match vs with 
                           | [IntVal v] -> StringVal(string v)
                           | [BoolVal v] -> StringVal(string v)
                           | [StringVal v] -> StringVal v
                           | _          -> failwith "error"
               Primitive f;;

let initEnv = Map.ofList [("+",plusInt); ("-",minusInt); ("*",multInt); ("=",eqInt); ("<>",neqInt); ("<=",lessEqInt); ("<",lessInt); 
                           ("randomInt", randomInt); ("toString",toString)  ];;


// Parse a program in a file  
let fac = parseFromFile "factorial.while"

// Interpret the program 
let _  = stm fac initEnv Map.empty;;


