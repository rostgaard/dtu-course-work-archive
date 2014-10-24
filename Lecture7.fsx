(* Interpreter for a simple WHILE-language.           MRH 21/10 2013 *)
(* Program skeleton                                                  *)
(* Based on a natural semantics of WHILE                             *)

type AExp =                           (* Arithmetical expressions *) 
          | N  of int                 (* numbers                  *)
          | V  of string              (* variables                *)
          | Add of AExp * AExp        (* addition                 *)
          | Mul of AExp * AExp        (* multiplication           *)
          | Sub of AExp * AExp;;      (* subtraction              *)


type BExp =                          (* boolean expressions      *)
          | TT                       (* true                     *)
          | FF                       (* false                    *)
          | Eq of AExp * AExp        (* equality                 *)
          | Lt of AExp * AExp        (* less than                *)
          | Neg of BExp              (* negation                 *)
          | Con of AExp       ;;     (* conjunction              *)

type Stm  =                            (* statements             *)
          | Ass of string * AExp       (* assignment             *)
          | Skip
          | Seq  of Stm * Stm          (* sequential composition *)
          | ITE   of BExp * Stm * Stm  (* if-then-else           *)
          | While of BExp * Stm;;      (* while                  *)


type State = Map<string,int>;;

(* update: string -> int -> State -> State  *)
let update x v s = Map.add x v s;; 

(* A: AExp -> State -> int                   *)
let rec A a s      = 
   match a with 
    | N n         -> n
    | V x         -> Map.find x s
    | Add(a1, a2) -> A a1 s + A a2 s
    | Mul(a1, a2) -> A a1 s * A a2 s
    | Sub(a1, a2) -> A a1 s - A a2 s;;

(* B: BExp -> State -> bool                  *)
let rec B b s =
   match b with 
    | TT          -> true
    | FF          -> false
    | Eq(e1, e2)  -> A e1 s = A e2 s
    | Lt(e1, e2)  -> A e1 s < A e2 s
    | Neg e       -> not (B e s)
    | Con e       -> false ;;

(* I: Stm -> State -> State                          *)
let rec I stm s =
    match stm with 
    | Ass(x,a)         -> update x ((A a s)) s
    | Skip             -> s
    | Seq(stm1, stm2)  -> let s' = I stm1 s
                          I stm2 s'
                          
    | ITE(b,tbranch,fbranch) -> if B b s
                                then I tbranch s
                                else I fbranch s
    | While(b, body)         -> if B b s
                                  then let s' = I body s (While b s')
                                  else Skip;;


(* Factorial computation 
{pre: x = K and x>=0} 
   y:=1 ; while !(x=0) do (y:= y*x;x:=x-1) 
post: {y = K!}
*)

let fac = Seq(Ass("y", N 1), 
              While(Neg(Eq(V "x", N 0)), 
                    Seq(Ass("y", Mul(V "x", V "y")) , Ass("x", Sub(V "x", N 1)) ))
             );;




(* Define an initial state                           *)
let s0 = Map.ofList [("x",4)];;

(* Interpret the program                             *)
let s1 = I fac s0;;

(* Inspect the resulting state                       *)
Map.find "y" s1;;



