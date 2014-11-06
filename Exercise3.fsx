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


let lsToStringAux = function 
  |  []    -> "()"
  |  x::[] -> toString x
  |  x::xs -> List.fold (fun s prop -> s + " && " +  toString (prop)) (toString x) xs;;

(* Return a string representation of a literal set.
   Set<Prop> -> string *)
let lsToString ls = lsToStringAux (Set.toList ls);;

(************
 * Part 2 - negational normal form.
 *)
 
 (* Returns the proposition in negation normal form.
    Prop -> Prop *)
let rec negNormalForm = function 
 | A p         -> A p
 | Neg (Dis (p,q))   -> Con (negNormalForm (Neg (p)), negNormalForm (Neg (q)))
 | Neg (Con (p,q))   -> Dis (negNormalForm (Neg (p)), negNormalForm (Neg (q)))
 | Dis (p,q)   -> Dis (negNormalForm (p), negNormalForm (q))
 | Con (p,q)   -> Con (negNormalForm (p), negNormalForm (q))
 | Neg (Neg p) -> negNormalForm p
 | Neg p       -> Neg (negNormalForm p);;

// Tests
let prop1     = Neg (Dis (A "p", A "q"));;
let exptProp1 = Con (Neg (A "p"),Neg (A "q"));;
let resProp1  = negNormalForm prop1 ;;
assert (prop1 = exptProp1);;

let prop2 = Neg (Con (A "p", A "q"));;
let exptProp2 = Dis (Neg (A "p"),Neg (A "q"));;
let resProp2 = negNormalForm prop2 ;;
assert (prop2 = exptProp2);;
 
let prop3 = A "p";;
let resProp3 = negNormalForm prop3 ;;
assert (prop3 = resProp3);;

// Nested negations
let prop4 = Neg (Neg (Neg (A "p")));;
let resProp4 = negNormalForm (prop4) ;;

(************
 * Part 3
 * The patterns for this one are incorrect, but I could not 
 * solve the problem in time. 
 *)
let rec normalForm = function
 | A a                 -> A a
 | Con (a, Dis (b, c)) -> normalForm (Dis (Con (a, b), (Con (a,  c))))
 | Con (Dis (b, c), a) -> normalForm (Dis (Con (a, b), (Con (a,  c))))
 | Con (A a, A b)      -> Con (A a, A b);
 | Con (a, b)          -> Con (normalForm a, normalForm b);
 | Dis (a, b)          -> Dis (normalForm a, normalForm b)
 | Neg (Neg a)         -> normalForm a
 | Neg a               -> Neg (normalForm a);;

let temp = Con (Con (Dis (A "a", A "b"), A "c"), A "d");;
normalForm temp;;
printf "%s\n" (toString (temp));;
printf "%s\n" (toString (normalForm temp));;

let prop5 = Con (Con (A "a", Con (Con (A "c", Neg (A "d")), Con (A "b", Neg (A "e")))), A "f");;
printf "%s\n" ("normalForm of " + toString prop5 + " <=> " + toString (normalForm prop5));;

(************
 * Part 4
 *)
let rec litOfAux p xs = 
  match p with
    | A p         -> Set.add (A p) xs
    | Con (p,q)   -> Set.union (litOfAux p xs) (litOfAux q xs)
    | Neg (Neg p) -> litOfAux p xs
    | Neg (A p)   -> Set.add (Neg (A p)) xs
    | Neg (p)     -> failwith "Disjunctions allowed in this level."
    | _           -> failwith "Disjunctions allowed in this function.";;

let litOf bc = litOfAux bc Set.empty;;

litOf (Con (A "a", Neg (A "b")));;
printf "%s\n" (lsToString (litOf (Con (A "a", Neg (A "b")))));;

(* Prop -> Set<Set<Prop>> *)
let rec toDNFsetsAux a ls = 
  match a with
    | A p         -> Set.add (litOf (A p)) ls
    | Dis (p,q)   -> Set.union (toDNFsetsAux p ls) (toDNFsetsAux q ls)
    | Con (p,q)   -> Set.add (litOf (Con (p,q))) ls
    | Neg (A p)   -> Set.add (litOf (Neg (A p))) ls
    | Neg _       -> failwith "Negations should only be present on atoms!";;

let toDNFsets prop = toDNFsetsAux (negNormalForm (normalForm prop)) Set.empty
let testSet = litOf (Con (A "a", Neg (A "b")));;

toDNFsets prop5;;
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

lsToString (testConsistentSet);;

(************
 * Part 6
 *)

(* Proposition implication 
   Prop -> Prop -> Prop *)
let impl a b = Dis (Neg (a), b);;
(* Proposition bi-implication 
   Prop -> Prop -> Prop *)
let iff a b  = Con (impl a b, impl b a);;

let formula1 = impl (impl (Neg (A "p")) (Neg (A "q"))) (impl (A "p") (A "q"));
let formula2 = impl (impl (Neg (A "p")) (Neg (A "q"))) (impl (A "q") (A "p"));

toDNFsets formula1;;
toDNFsets formula2;;

(************
 * Part 7
 *
 * See appended .pdf file. As assignment 3 is not correct, neither is this.
 *)
let eq1 = Neg (A "a");;
let eq2 = Dis (A "a", Dis (A "b", A "c"));;
let eq3 = Dis (Neg (A "b"), Con (Neg (A "a"), Neg (A "c")));;
let eq4 = Dis (Dis (Con (A "a", A "c"), Con (Neg (A "a"), Neg (A "c"))), A "b");;

let eqn = Con (Con (Con (eq1,eq2), eq3), eq4)

toDNFsets (eqn);;

(************
 * Part 8
 *)

(* Generates a Proposition with n conjuntions.
   int -> Prop *)
let rec badProp = function
 | 1 -> Dis (A "p1", A "q1")
 | n -> Con (Dis (A ("p"+string(n)), A ("q"+string(n))), badProp (n-1));;

// Tests
let length = 3;
let expectedLength = int (float(2)**float (length));;

assert (Set.count (toDNFsets (normalForm (badProp length))) = expectedLength);;
