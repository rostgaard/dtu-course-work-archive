type Solution = 
  | TwoRoots of (float * float)
  | OneRoot  of float
  | None;;

//HR 3.5, TODO add quadratic function.
let solution = function 
  | 2 -> TwoRoots (2.0,2.0)
  | 3 -> OneRoot (1.0)
  | _ -> None;;

let test3_5_1 = solution 2;;
let test3_5_2 = solution 3;;
let test3_5_3 = solution 1;;

//HR 5.2
let rev xs    = List.fold (fun res x -> x::res)     [] xs;;
let revrev xs = List.fold (fun res x -> rev x::res) [] xs;;

let test5_2_1     = List.rev [1;2;3];;
assert (test5_2_1 = [3;2;1]);;

let test5_2_2     = revrev [[1;2]; [3;4;5;]];;
assert (test5_2_2 = [[5;4;3;]; [2;1]]);;

//HR 5.3
let sum p xs = List.fold (fun res x -> res + x) 0 (List.filter p xs);;

let test5_3_1 = sum (fun x -> x > 2) [1;2;3];;
assert (test5_3_1 = 3);;
let test5_3_2 = sum (fun x -> x >= 2) [1;2;3];;
assert (test5_3_2 = 5);;

//HR 5.5
//5.5 Consider the map colouring example in Section 4.6. 
// Give declarations for the functions areNb canBeExtBy, extColouring, countries and colCntrs using higher-order list functions.
// Are there cases where the old declaration from Section 4.6 is preferable?
// Example: Map colouring

type Country = string;;
type Map     = (Country * Country) list;;

type Colour     = Country list;;
type Colouring  = Colour list;;

// Changed this to exists operator.
let rec isMember x xs = List.exists (fun x' -> x' = x) xs;;

let areNb m c1 c2 =
    isMember (c1,c2) m || isMember (c2,c1) m;;

let rec canBeExtBy m col c =
    match  col with
    | []       -> true
    | c'::col' -> not(areNb m c' c) && canBeExtBy m col' c;;

let rec extColouring m cols c =
    match cols with
    | []         -> [[c]]
    | col::cols' -> if canBeExtBy m col c
                    then (c::col)::cols'
                    else col::extColouring m cols' c;;

let addElem x ys = if isMember x ys then ys else x::ys;;

let rec countries = function
    | []           -> []
    | (c1,c2)::m -> addElem c1 (addElem c2 (countries m));;

let rec colCntrs m = function
    | []    -> []
    | c::cs -> extColouring m (colCntrs m cs) c;;


let colMap m = colCntrs m (countries m);;

let exMap = [("a","b"); ("c","d"); ("d","a")];;

let testColMap = colMap exMap;;


