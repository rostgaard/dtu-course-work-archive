open Microsoft.FSharp.Collections.List;;

let st = toString p2;;

// Michael R. Hansen, August 31, 2011
// Program skeleton to be use when solving the exercise on polynomials

// An infix function +. for adding polynomials.
// Has the same preceedence as +.
let rec (+.) p q =
    match (p,q) with
       | ([],x)         -> x
       | (x,[])         -> x
       | (a::p',b::q')  -> a + b :: p' +. q' ;;


// multiply a polynimial by a constant
let rec multC = function
  |  ([],c)    -> []
  |  (x::xs,c) -> c*x :: multC(xs,c);;

// multiply P(x) by x
let multX p = 0::p ;;

// An infix function *. for mytiplying polynomials
// Has the same preceedence as *
let rec ( *.) p q =
   match p with
    | ...   -> ...
    | ...   -> ...  ;;


// convert a polynomial to a string representation
// you may use an auxiliary function 
let rec toString = function
  | x::xs when xs.Length = 0  -> string x
  | x:int::xs -> string (x)+"x^" + string xs.Length + " +  " + (toString (xs))
 ;;



// examples
let p1 = [1; 2; 3];;

let p2 = [1; 2];;

//let p5 = p2 +. p2;;

let p3 = [1; 2; 3; 4];

//let p4 = p1 +. p2 *. p3;;

let st = toString p3;;
let st = List.rev p3;;
let st = toString st;;


