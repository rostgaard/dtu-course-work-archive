(************
 * 02157 Functional programming E14 - exercise 3.
 * Kim Rostgaard Christensen - kroch/s084283
 *)

(************
 * Part 1
 *)

 type Prop = 
          | A  of string
          | Dis of Prop * Prop
          | Con of Prop * Prop
          | Neg of Prop;;

let rec toString = function
 | A p       -> p
 | Dis (p,q) -> "("+toString p + " || " + toString q+")"
 | Con (p,q) -> "("+toString p + " && " + toString q+")"
 | Neg p     -> "!"+toString p

(************
 * Part 2
 *)
let rec deMorgan = function 
 | A p         -> A p
 | Dis (p,q)   -> Neg (Con (Neg (deMorgan p), Neg (deMorgan q)))
 | Con (p,q)   -> Neg (Dis (Neg (deMorgan p), Neg (deMorgan q)))
 | Neg (Neg p) -> deMorgan p
 | Neg p       -> Neg (deMorgan p);;

let prop1 = Dis (A "p", A "q");;
let exptProp1 = Con (Neg (A "p"),Neg (A "q"));;
let resProp1 = deMorgan prop1 ;;

let prop2 = Con (A "p", A "q");;
let exptProp2 = Dis (Neg (A "p"),Neg (A "q"));;
let resProp2 = deMorgan prop2 ;;
 
let prop3 = A "p";;
let resProp3 = deMorgan prop3 ;;

let resProp4 = deMorgan (Neg prop2) ;;

(************
 * Part 3
 *)
let rec normalForm = function
 | A a                  -> A a
 | Con (a, Con (b, c )) -> Dis (normalForm (Con (normalForm a, normalForm b)), normalForm (Con (normalForm a, normalForm c)))
 | Con (Con (b, c ), a) -> Dis (normalForm (Con (normalForm a, normalForm b)), normalForm (Con (normalForm a, normalForm c)))
 | Con (a, b)           -> Con (normalForm a, normalForm b)
 | Dis (a, b)           -> Dis (normalForm a, normalForm b)
 | Neg (Neg a)          -> normalForm a
 | Neg a                -> Neg (normalForm a);;

let prop4 = Con (A "a", Con ( A "b", Con (A "c", Neg (A "d"))));;
printf "%s\n" ("normalForm of " + toString prop4 + " => " + toString (normalForm prop4));;

(************
 * Part 4
 *)
let rec litOfAux p xs = 
  match p with
    | A p         -> Set.add (A p) xs
    | Con (p,q)   -> Set.union (litOfAux p xs) (litOfAux q xs)
    | Neg (Neg p) -> litOfAux p xs
    | Neg p       -> Set.union (Set.map (fun (p') -> Neg(p)) (litOfAux p Set.empty)) xs
    | _           -> failwith "Disjunctions allowed in this level.";;

let litOf bc = litOfAux bc Set.empty;;

litOf (Con (A "a", Neg (A "b")));;

let rec dnfToSetAux a ls = 
  match a with
    | A p         -> Set.add (litOf (A p)) ls
    | Dis (p,q)   -> Set.union (dnfToSetAux p ls) (dnfToSetAux q ls)
    | Con (p,q)   -> Set.add (litOf (Con (p,q))) ls
    | Neg (A p)   -> Set.add (litOf (Neg (A p))) ls
    | Neg _       -> failwith "Negations should only be present on atoms!";;

dnfToSetAux (normalForm prop4) Set.empty



//toString (complement (Neg (Neg(A "a"))));;
let testSet = litOf (Con (A "a", Neg (A "b")));;

(************
 * Part 5
 *)


(* Helper function for consistency checks.*)
let complement = function
 | Neg (A p) -> A p
 | A p       -> Neg (A p)
 | _         -> failwith "Unsupported operation";;

(* Checks for consistency in literals sets. 
 *  This is done by making a new set of complements to the exisiting set
 *  and intersecting with the original set. For consistent sets the result
 *  og this operation will be the empty set, and non-empty set for 
 *  inconsistent sets. Thus, we merely have check for the number of 
 *  intersected values to verify consistency.
 *)

let isConsistent ls = Set.count (Set.intersect (Set.map (fun (p) -> complement (p)) ls) ls) = 0; 

let testConsistent = (isConsistent testSet = true);;


(************
 * Part 6
 *)
 (*Declare an F# function
toDNFsets
that transforms an arbitrary proposition into a dns set
with just consistent literal sets.*)
 let toDNFsets = None;;

(*Declare a function impl a b for computing the implication a => b
and a function iff a b for computing the bi-implication a <=> b*)
 let impl = None;
 let iff = None;

