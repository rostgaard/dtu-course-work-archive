(************
 * 02157 Functional programming E14 - exercise 3.
 * Kim Rostgaard Christensen - kroch/s084283
 *)

(************
 * Part 1 - Type defininition.
 *)

 type Prop = 
 | A  of string
 | Dis of Prop * Prop
 | Con of Prop * Prop
 | Neg of Prop;;

(* Return a string representation of a Proposition.
   Prop -> string *)
let rec toString = function
 | A p       -> p
 | Dis (p,q) -> "("+toString p + " || " + toString q+")"
 | Con (p,q) -> "("+toString p + " && " + toString q+")"
 | Neg p     -> "!"+toString p

(* Return a string representation of a literal set.
   Set<Prop> -> string *)
let lsToString = function 
  |  []    -> "()"
  |  x::[] -> toString x
  |  x::xs -> List.fold (fun s prop -> s + " && " +  toString (prop)) (toString x) xs;;

(************
 * Part 2
 *)
let rec negNormalForm = function 
 | A p         -> A p
 | Dis (p,q)   -> Neg (Con (Neg (negNormalForm p), Neg (negNormalForm q)))
 | Con (p,q)   -> Neg (Dis (Neg (negNormalForm p), Neg (negNormalForm q)))
 | Neg (Neg p) -> negNormalForm p
 | Neg p       -> Neg (negNormalForm p);;

let prop1 = Dis (A "p", A "q");;
let exptProp1 = Con (Neg (A "p"),Neg (A "q"));;
let resProp1 = negNormalForm prop1 ;;

let prop2 = Con (A "p", A "q");;
let exptProp2 = Dis (Neg (A "p"),Neg (A "q"));;
let resProp2 = negNormalForm prop2 ;;
 
let prop3 = A "p";;
let resProp3 = negNormalForm prop3 ;;

let resProp4 = negNormalForm (Neg prop2) ;;

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

let inconsistenValues ls = Set.intersect (Set.map (fun (p) -> complement (p)) ls) ls;;
let isConsistent ls = Set.isEmpty (inconsistenValues ls); 

let testConsistentSet = litOf (Con (A "a", Neg (A "b")));;
let testInconsistentSet = litOf (Con (A "a", Neg (A "a")));;

assert (isConsistent testConsistentSet = true);;
assert (isConsistent testInconsistentSet = false);;

(* Removes inconsistent litterals from the set by using the set difference operator.*)
let removeInconsistent ls = Set.difference (inconsistenValues ls) ls;;

assert (not (Set.isEmpty (removeInconsistent testConsistentSet)));;
assert (Set.isEmpty (removeInconsistent testInconsistentSet));;



lsToString (Set.toList testConsistentSet);;

 (*//Declare an F# function toDNFsets that transforms an arbitrary proposition into a dns set with just consistent literal sets.*)

(* Prop -> Set<Set<Prop>> *)
let rec toDNFsetsAux prop set = function
 | A p                 -> Set.add (litOf (A p)) set
 | Con (Con (p,q), r)  -> Set.union (toDNFsetsAux (Con (p,q)) set) (Set.add (litOf (Con (p,q))) set)
 | Con (p, Con (q, r)) -> Set.add (litOf (Con (p,q))) set
 | Con (p,q) -> Set.add (litOf (Con (p,q))) set
 | _         -> set;;

 toDNFsetsAux testConsistentSet Set.empty;;

(************
 * Part 6
 *)

(*//Declare a function impl a b for computing the implication a => b
  and a function iff a b for computing the bi-implication a <=> b*)
 let impl = None;;
 let iff = None;;

