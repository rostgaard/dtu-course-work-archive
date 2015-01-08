// Michael R. Hansen 03-01-2014

(* Load the parser and interpreter *)
#r "FSharp.PowerPack.dll"

#load "AST.fs"
#load "Parser.fs"
#load "Lexer.fs"
#load "ParserUtil.fs"
#load "Interpreter.fs"

open System
open Interpreter
open AST
open ParserUtil

// Functions for an initial environment

let plusInt = Primitive( function [IntVal i1; IntVal i2] -> IntVal(i1+i2) | _ -> failwith "Invalid arguments" );;
let minusInt = Primitive( function [IntVal i1; IntVal i2] -> IntVal(i1-i2) | _ -> failwith "Invalid arguments" );;
let multInt = Primitive( function [IntVal i1; IntVal i2] -> IntVal(i1*i2) | _ -> failwith "Invalid arguments" );;
let eqInt = Primitive( function [IntVal i1; IntVal i2] -> BoolVal(i1=i2) | _ -> failwith "Invalid arguments" );;
let neqInt = Primitive( function [IntVal i1; IntVal i2] -> BoolVal(i1<>i2) | _ -> failwith "Invalid arguments" );;
let lessEqInt = Primitive( function [IntVal i1; IntVal i2] -> BoolVal(i1<=i2) | _ -> failwith "Invalid arguments" );;
let lessInt = Primitive( function [IntVal i1; IntVal i2] -> BoolVal(i1<i2) | _ -> failwith "Invalid arguments" );;
let gen = let generator = new System.Random()
          generator.Next;;   
let randomInt = Primitive( function [IntVal rng] -> IntVal (gen rng) | _ -> failwith "Invalid arguments" );; 
let toString = let f vs =  match vs with 
                           | [IntVal v] -> StringVal(string v)
                           | [BoolVal v] -> StringVal(string v)
                           | [StringVal v] -> StringVal v
                           | [Reference v] -> StringVal (string v)
                           | _ as x         -> failwith ("error" + string x)
               Primitive f;;

let initEnv = Map.ofList [("+",plusInt); ("-",minusInt); ("*",multInt); 
                          ("=",eqInt); ("<>",neqInt); ("<=",lessEqInt); ("<",lessInt); 
                          ("randomInt", randomInt); ("toString",toString)  ];;
let dirDelimiter = "/" //Char.ToString System.IO.Path.PathSeparator;;
let testPath = "tests" + dirDelimiter;

// Parsing strings
let s1 = parseStm "while <>(!n,0)
                   do y := *(!n,!y);
                      n := -(!n,1)
                   od";;

let s2 = parseStm "let n: 4; y: 1
                   in while <>(!n,0)
                      do y := *(!n,!y);
                         n := -(!n,1)
                      od
                   end";;

// Parsing from files
// Set current directory
System.IO.Directory.SetCurrentDirectory __SOURCE_DIRECTORY__;;

let p3 = parseFromFile "Factorial1.while";;
// Interpret the statement
let _ = ignore (stm p3 initEnv Map.empty);;

let p5a =
  Block
    ([VarDec ("n",Int 4); VarDec ("y",Int 1) ; ProcDec ("test",["x"], PrintLn (Int 42))],
     Seq
       [While
          (Apply ("<>",[ContOf (Var "n"); Int 0]),
           Seq
             [PrintLn (Apply ("toString",[ContOf (Var "n")]));
              PrintLn (Apply ("toString",[ContOf (Var "y")]));
              Asg (Var "y",Apply ("*",[ContOf (Var "n"); ContOf (Var "y")]));
              Asg (Var "n",Apply ("-",[ContOf (Var "n"); Int 1]))]);
        PrintLn (String "Result is: ");
        PrintLn (Apply ("toString",[ContOf (Var "n")]));
        PrintLn (Apply ("toString",[ContOf (Var "y")]))]);;

let arr1 =
  Block
    ([VarDec ("n",Int 4); VarDec ("y",Int 1) ; ProcDec ("test",["x"], PrintLn (Int 42))],
     Seq[Asg (Var "n",Apply ("-",[ContOf (Var "n"); Int 1]))]);

// Test the program
//let _ = ignore (stm p5a initEnv Map.empty);;

let p5 = parseFromFile "Factorial3.while";;
let _ = ignore (stm p5 initEnv Map.empty);;


let p6 = parseFromFile "Factorial4.while";;
let _ = ignore (stm p6 initEnv Map.empty);;

let p7 = parseFromFile "Factorial5.while";;
let _ = ignore (stm p7 initEnv Map.empty);;


// Parsing and interpreting programs with arrays

let randomArray = parseDec "proc randomArray(rng, lng) 
                               let a[!lng]: 0;
                                   i: 0
                               in while <(!i,a.length)
                               do a[!i] := randomInt(rng);
                                  i    := +(!i,1)
                               od;
                               return a
                               end";;


// Auxiliary procedures on arrays are in the file "ArrayUtil.while"
// They are used to built up a basic environment and a basic store
let arrayUtilDecs = parseDecListFromFile "ArrayUtil.while";;

let (basisEnv, basisStore) = decList arrayUtilDecs initEnv Map.empty;; 

let ap1 = parseFromFile"ArrayProg1.while";; 
let _ = ignore (stm ap1 basisEnv basisStore);;

let ap2 = parseFromFile"ArrayProg2.while";; 
let _ = ignore (stm ap2 basisEnv basisStore);;

//printf "%s\n" "Extensions - foreach";;

//let foreachTest = parseFromFile "Foreach.while";;

//printf "%s\n" "Return.while";;
//let returnTest = parseFromFile "Return.while";;
//stm returnTest basisEnv basisStore


printf "%s" "Tests:\n";;

printf "%s" "  Factorial2.while - ";;
let p4 = parseFromFile "Factorial2.while";;
try
  ignore (stm p4 initEnv Map.empty)
with
    | _ -> printf "%s\n" "(fail) Got unexpected exception!"


printf "%s" "  String as array index error - ";;
let arrayTest = parseFromFile (testPath + "ArrayTestStringIndex.while");;
try
  let _ = stm arrayTest basisEnv basisStore
  printf "%s\n" "(fail) Expected exception here."
with
    | TypeError msg  -> printf "%s\n" "(ok)"
    | _              -> printf "%s\n" "(fail) Got unexpected exception!"


printf "%s" "  If-Else parsing - ";;
let ifelseparseTest = parseFromFile (testPath + "IfElseParse.while");;
try
  let _ = stm ifelseparseTest basisEnv basisStore
  printf "%s\n" "(ok)"
with
    | _ -> printf "%s\n" "(fail) Got unexpected exception!"

printf "%s" "  Crude array folding - ";;
let arrTest = parseFromFile (testPath + "ArrayFolding.while");;
try
  let _ = stm arrTest initEnv Map.empty
  printf ""
with
  | _ -> printf "%s\n" "(fail) Got unexpected exception!"

