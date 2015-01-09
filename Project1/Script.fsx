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

let logicAnd = Primitive( function [BoolVal b1; BoolVal b2] -> BoolVal(b1 && b2) | _ -> failwith "Invalid arguments" );;
let logicOr  = Primitive( function [BoolVal b1; BoolVal b2] -> BoolVal(b1 || b2) | _ -> failwith "Invalid arguments" );;

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
                          ("randomInt", randomInt); ("toString",toString);
                          ("&&", logicAnd); ("||", logicOr)];;
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





//printf "%s\n" "Return.while";;
//let returnTest = parseFromFile "Return.while";;
//stm returnTest basisEnv basisStore


printf "%s" "Tests:\n";;

printf "%s" "  Factorial1.while - ";;
let p3 = parseFromFile "Factorial1.while";;
try
   ignore (stm p3 initEnv Map.empty)
with
    | _ -> printf "%s\n" "(fail) Got unexpected exception!"

printf "%s" "  Factorial2.while - ";;
let p4 = parseFromFile "Factorial2.while";;
try
  ignore (stm p4 initEnv Map.empty)
with
    | _ -> printf "%s\n" "(fail) Got unexpected exception!"

printf "%s" "  Factorial3.while - ";;
let p5 = parseFromFile "Factorial3.while";;
try
  ignore (stm p5 initEnv Map.empty)
with
    | _ -> printf "%s\n" "(fail) Got unexpected exception!"

printf "%s" "  Factorial4.while - ";;
let p6 = parseFromFile "Factorial4.while";;
try
  ignore (stm p6 initEnv Map.empty)
with
    | _ -> printf "%s\n" "(fail) Got unexpected exception!"

printf "%s" "  Factorial5.while - ";;
let p7 = parseFromFile "Factorial5.while";;
try
  ignore (stm p7 initEnv Map.empty)
with
    | _ -> printf "%s\n" "(fail) Got unexpected exception!"

printf "%s" "  ArrayProg1.while - ";;
let ap1 = parseFromFile "ArrayProg1.while";; 
try
  ignore (stm ap1 basisEnv basisStore)
with
    | _ -> printf "%s\n" "(fail) Got unexpected exception!"

printf "%s\n" "  ArrayProg2.while (manually expect output) ";;
printf "%s\n" "=== ArrayProg2.while output ===";;
let ap2 = parseFromFile "ArrayProg2.while";; 
try
  ignore (stm ap2 basisEnv basisStore)
with
    | _ -> printf "%s\n" "(fail) Got unexpected exception!"
printf "%s\n" "=== End ArrayProg2.while output ===";;

printf "%s" "  String as array index error - ";;
let arrayTest = parseFromFile (testPath + "ArrayTestStringIndex.while");;
try
  let _ = ignore(stm arrayTest basisEnv basisStore)
  printf "%s\n" "(fail) Expected exception here."
with
    | TypeError msg  -> printf "%s\n" "(ok)"
    | _              -> printf "%s\n" "(fail) Got unexpected exception!"

printf "%s" "  If-Else parsing - ";;
let ifelseparseTest = parseFromFile (testPath + "IfElseParse.while");;
try
  ignore (stm ifelseparseTest basisEnv basisStore)
with
    | _ -> printf "%s\n" "(fail) Got unexpected exception!"

printf "%s" "  Crude array folding - ";;
let arrTest = parseFromFile (testPath + "ArrayFolding.while");;
try
  let _ = ignore(stm arrTest initEnv Map.empty)
  printf ""
with
  | _ -> printf "%s\n" "(fail) Got unexpected exception!"

printf "%s" "  Infix logic parse - ";;
let andOrTest = parseFromFile (testPath + "AndOrInfix.while");;
try
  let _ = ignore(stm andOrTest initEnv Map.empty)
  printf "%s\n" "(ok)"
with
  | _ -> printf "%s\n" "(fail) Got unexpected exception!"

  
printf "%s\n" "Extensions - foreach";;

let foreachTest = parseFromFile (testPath + "ForeachLoop.while");;
let _ = ignore (stm foreachTest basisEnv basisStore);;


//is the parser not allowing to assign to arguments?
let ap3 = parseFromFile (testPath + "ArgsReferencesValues.while");; 
let _ = ignore (stm ap3 basisEnv basisStore);;