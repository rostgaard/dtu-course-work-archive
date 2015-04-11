(* Lecture 9 *)

(* HR 9.6 
   Declare a continuation-based version of the factorial function and compare the run time with the results in Section 9.4.
*)

let rec sumC xs c = 
  match xs with
   | []     -> c 0
   | x::rst -> sumC rst (fun v -> c(x+v));;

let rec factC n c =
  match n with
   | 0  -> c 1
   | n' -> factC (n'-1) (fun e -> c(e * n'));;

printf "%d" (factC 5 id);;

printf "%d\n" (sumC [1;2;3] id);;

(* HR 9.7.2 
   Develop a continuation-based version fibC: int -> (int -> int) -> int that is based on the definition of Fn given in Exercise 1.5.
   Compare these two functions using the directive #time, and compare this with the while-loop based solution of Exercise 8.6.*)

let rec fibC n c =
  match n with
   | 0  -> c 1
   | n' -> fibC (n'-1) (fun e -> c(e * n'));;


(* HR 9.9 
   Declare a tail-recursive functions with the type countAC : BinTree<′a>->int ->(int ->′b) ->′b
   such that count t = countAC t 0 id. The intuition with countAC t a c is that a is the number of nodes being counted so far and c is the continuation.
*)



(* HR 7.4 Make signature and implementation files for a library of polynomials with integer coefficients (cf. Exercise 4.22).
   HR 7.5 Customize the string function in the library of polynomials inExercise 7.4. *)

(* Spec *)
 (*module Polynomial
[<Sealed>]
  type polynomial =
    val make : List<int> -> polynomial
*)
(* Implementation *)
(*module Polynomial
  type polynomial =
    | P of List<int>
    override p.ToString() =
      match p with 
        | P(coef) -> toString ("Poly");;
*)
