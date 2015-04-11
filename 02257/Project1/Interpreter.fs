(* Interpreter for a simple WHILE-language. Michael R. Hansen 03-01-2014 *)
(* Based on a natural semantics of WHILE                                 *)

(* Remember to regenerate the parser and the lexer using the commands 
   in README.txt if you modified the parser and lexer                    *)

module Interpreter 

open System
open AST

type Location = int
type Value    = | IntVal of int 
                | BoolVal of bool 
                | StringVal of string 
                | Reference of Location 
                | Primitive of (List<Value> -> Value)
and Env       = Map<string,Value>


type Length = int
type Array = Length * Value array;;
type Closure =  List<string> * Env * Stm

exception TypeError of string
exception DeclarationNotFound of string

type Content = SimpVal of Value | Proc of Closure |  ArrayList of Array;;

type Store  = Map<Location,Content>  
  
let closureOf(ps,st) env = (ps, env, st)

let locateArray location store = 
    try 
       match Map.find location store with
       | ArrayList(length, values) -> ArrayList(length, values)
       | _                         -> raise (TypeError (""))
     with
     | TypeError msg -> raise (TypeError(msg))
     | _         -> raise (DeclarationNotFound ("Variable"));;
       

// nextLoc() generates the next available location
let nextLoc: unit -> int =  let n = ref 0
                            let f x = (n := !n+1; !n)
                            f

                            

let rec toStringEnv (mapp :Env) = Map.fold (fun acc k v -> "\n" + (string k) + " = " + match v with
                                                                                        | Reference loc -> string (int loc)
                                                                                        | _ as x        -> string x
                                                                                       + acc) " \n " mapp;;
let rec toString mapp = Map.fold (fun acc k v -> "\n" + (string k) + " = " + (string v) + acc) " \n " mapp;;
// creates nev environment based on args
let rec newEnvFromArgs env = function 
    | []    -> env
    | s::xs -> Map.add s (Reference (nextLoc())) (newEnvFromArgs env xs);;  

let rec newEnvForArray env store aName initial = function 
    | p     when p <= 0 -> (env, store)
    | p                 -> let name = aName + "[" + string p + "]"
                           let loc = nextLoc()
                           let newEnv = Map.add name (Reference loc) env
                           let newStore = Map.add loc (SimpVal initial) store
                           newEnvForArray newEnv newStore aName initial (p-1)

let rec assignArgsToVals (env:Env) (store:Store) = function
    | ([], []) -> (env, store)
    | (a::ax, v::vx) -> match v with 
                        | Reference l as loc-> let newEnv = Map.add a loc env
                                               assignArgsToVals newEnv store (ax,vx)
                        | _ as value  -> let loc = nextLoc();
                                         let newEnv = Map.add a (Reference loc) env
                                         let newStore = Map.add loc (SimpVal value) store
                                         assignArgsToVals newEnv newStore (ax,vx)
    | (_) -> failwith "Arguments list do not match the declaration"

// exp: Exp -> Env -> Store -> Value * Store 
let rec exp e (env:Env) (store:Store) = 
    match e with
    | Var v       -> match Map.find v env with
                     | Reference loc as refl -> (refl,store)
                     | IntVal i as refl      -> (refl, store)
                     | StringVal s as refl   -> (refl, store)
                     | BoolVal v as refl     -> (refl, store)
                     | _ as refl             -> raise (TypeError("This is not supported var type " + string refl))
    | ArrVar(s, e) -> match exp e env store with
                        | (IntVal index, newStore) -> match Map.find s env with
                                                        | Reference loc -> match Map.find loc store with
                                                                            | ArrayList(_, values) as x -> ((Array.get values index), store)
                                                                            | _ as res -> raise (TypeError("Cant find attribute. This is not reference to an array: " + string res))
                                                        | _ as res      -> raise (TypeError("Cant find attribute. This is not reference: " + string res))
                        | (index, newStore)       -> raise (TypeError("This is not a proper index for array: " + string index))
    | Attribute(s, a) -> match Map.find s env with
                            | Reference loc -> match Map.find loc store with 
                                                  | ArrayList(length, _) -> ((IntVal length),store)
                                                  | _           -> raise (TypeError("Only support for array.length attribute"))
                            | _             -> raise (TypeError("This object stores no reference:" + s))
    
    | ContOf er    -> match exp er env store with
                      | (Reference loc,store1) -> match Map.find loc store1 with 
                                                  | SimpVal res   -> (res,store1)
                                                  | ArrayList res -> (Reference loc, store1)
                                                  | Proc res      -> (Reference loc, store1)
                                                  | _ as res      -> raise (TypeError("This is not legit content type: " + string res))
                      | _                   -> raise (TypeError("This is not a proper reference:" + string er))

    | Apply(f,es) -> let (vals, store1) = expList es env store
                     match Map.find f env with
                        | Primitive f   -> (f vals, store1) 
                        | Reference r   -> match Map.find r store with
                                            | Proc(args, locEnv,  st) as x -> let (value : option<Value>, newStore) = stm (Call(f, es)) env store 
                                                                              (value.Value, newStore)
                                            | _                            -> raise (TypeError("This is not function or reference to procedure: " + string f))
                        | _              -> raise (TypeError("Cannot find function: " + string f))
    | Int i       -> (IntVal i, store)
    | Bool b      -> (BoolVal b,store)
    | String s    -> (StringVal s,store)
    | AndOp (e1, e2) -> exp (Apply ("&&", e1 :: [e2])) env store
    | OrOp  (e1, e2) -> exp (Apply ("||", e1 :: [e2])) env store

and expList es env store = 
    match es with 
    | []       -> ([],store)
    | e::erest -> let (res1, store1) = exp e env store
                  let (ress, store2) = expList erest env store1
                  (res1::ress, store2)  


// stm: Stm -> Env -> Store -> option<Value> * Store
and stm st (env:Env) (store:Store) = 
    match st with
    | ArrAsg(s, ind, e) -> let (value, valStore) = exp e env store
                           let (IntVal index, arrStore) = exp ind env valStore
                           let (ref, arrStore2) = exp (Var s) env arrStore
                           match ref with
                            | Reference loc -> match locateArray loc arrStore2 with
                                               | ArrayList(length, values) ->  Array.set values index value
                                                                               let newArray = ArrayList(length, values)
                                                                               let newStore = Map.add loc newArray arrStore2
                                                                               (None, Map.add loc newArray newStore)
                                               | _    -> raise (TypeError("Array assignment failed due to type error"))
                            | _             -> raise (TypeError("Array assignment failed due to type error"))
    | Call(s, args) -> let (argValues, valStore) = expList args env store
                       let ((Reference procValue), procStore) = exp (Var(s)) env valStore
                       let Proc(largs, procEnv, stms) as x = Map.find procValue procStore
                       let (localEnv, localStore) = assignArgsToVals procEnv procStore (largs,argValues)
                       match stm stms localEnv localStore with
                            | result    -> result
    | Asg(el,e) -> let (res,store1) = exp e env store
                   let (resl, store2) = exp el env store1
                   match resl with 
                   | Reference loc -> match res with
                                       | Reference value -> let result = Map.find value store2
                                                            (None, Map.add loc result store2)
                                       | _ as value -> (None, Map.add loc (SimpVal value) store2) 
                   | _             -> raise (TypeError("Variable assignment failed due to type error"))
    | PrintLn e -> match exp e env store with
                   | (StringVal s,store1) -> (printfn "%s" s; (None,store1))
                   | (IntVal s,store1)    -> (printfn "%s" (string s); (None,store1))
                   | (Reference s, store1)-> (printfn "%s" (string s); (None,store1))
                   | (_ as x, _)          -> raise (TypeError("Println does not support this type: " + string x))
    | Seq []        -> (None,store)
    | Seq (st::sts) -> match stm st env store with 
                       | (None,store1)   -> stm (Seq sts) env store1
                       | result       -> result

    | While(e,st1)  -> let (res, store1) = exp e env store
                       match res with 
                       | BoolVal true  -> match stm st1 env store1 with
                                          | (None,store2) -> stm st env store2
                                          | result     -> result
                       | BoolVal false -> (None, store1)
                       | _ as x        -> raise(TypeError("Expected Boolean expression for While condition, but: " + string x))
 
    | Block(ds,st1) -> let (env1,store1) = decList ds env store 
                       stm st1 env1 store1
    | Return (e)    -> let (res, store1) = exp e env store
                       (Some res,store1)

    | IfElse (cond, tbranch, fbranch) -> let (evalCond,store1) = exp cond env store
                                         match evalCond with
                                         | BoolVal true  -> stm tbranch env store1
                                         | BoolVal false -> stm fbranch env store1
                                         | _ as x        -> raise(TypeError("Expected Boolean expression for If condition, but: "+ string x))
    | Skip                          -> None,store
    | Foreach (iden, colName, body) -> match Map.find colName env with
                                        | Reference res -> match Map.find res store with
                                                            | ArrayList(length, values) -> let iterLoc = nextLoc();
                                                                                           let idenLoc = nextLoc();
                                                                                           let iter = "i"
                                                                                           let newEnv = Map.add iter (Reference iterLoc) env
                                                                                           let newStore = Map.add iterLoc (SimpVal(IntVal 0)) store
                                                                                           let newEnv2 = Map.add iden (Reference idenLoc) newEnv
                                                                                           let newStore2 = Map.add idenLoc (SimpVal(values.[0])) newStore
                                                                                           let rewrite = While (Apply ("<", [ContOf(Var iter); Int length]), 
                                                                                                                        Seq[Asg(Var iden, ArrVar(colName, ContOf(Var iter)));
                                                                                                                        body;
                                                                                                                        Asg(Var iter, Apply("+",[ContOf(Var iter); Int 1]))]);
                                                                                                                        
                                                                                           stm rewrite newEnv2 newStore2
                                                            | _ -> failwith "no array, no foreach"
                                        | _ as x        -> raise (TypeError ("Expected reference to variable in foreach loop, but: " + string x))
    | For(def, con, inc, stms)   -> match stm def env store with
                                    | (x , newStore)    -> let rewrite = While ((con), Seq[stms; inc]);
                                                           stm rewrite env newStore
and decList ds env store = 
    match ds with
    | []       -> (env,store)
    | d::drest -> let (env1,store1) = dec d env store
                  decList drest env1 store1

and dec d env store =
    match d with 
    | VarDec(s,e) -> let loc = nextLoc()
                     match exp e env store with
                     | (IntVal _    as res, store1)  
                     | (BoolVal _   as res, store1) 
                     | (StringVal _ as res, store1)  
                                                 -> let env2 = Map.add s (Reference loc) env
                                                    let store2 = Map.add loc (SimpVal res) store1
                                                    (env2, store2)
                     | _                         -> raise (TypeError("Variable declaration for non-existing type: " + string e))
    | ProcDec (s, args, stm) -> let loc = nextLoc()
                                let procEnv = Map.add s (Reference loc) env
                                let proc = Proc(args, procEnv, stm)
                                let newStore = Map.add loc proc store
                                (procEnv, newStore)
    | ArrDec(s, e, value) -> let loc = nextLoc()
                             match exp e env store with
                             | (IntVal length  as res, store1) -> let (initial, newStore2) = exp value env store1
                                                                  let values = [| for i in 1 .. length -> initial |]
                                                                  let array = ArrayList(length, values)
                                                                  let loc = nextLoc()
                                                                  let arrayEnv = Map.add s (Reference loc) env
                                                                  let arrayStore = Map.add loc array store
                                                                  (arrayEnv, arrayStore)
                             | _                               -> raise(TypeError ("Error in declaration of index in array \""+s+"\""));;