(* 9.1, 9.2, 9.3, 9.4, 9.7.1 (see correction on the textbook's homepage), 9.8 *)

(* 9.1 Consider the function g declared on Page 202 and the stack and heap after the 
   evaluation of g 2 shown in Figure 9.2. 
   Reproduce this resulting stack and heap by a systematic application of 
   push and pop operations on the stack, and heap allocations that 
   follow the step by step evaluation of g 2.*)

(* 9.2 Show that the gcd function on Page 16 is iterative. *)

(* 9.3 Declare an iterative solution to exercise 1.6. *)

(* 9.4 Give iterative declarations of the list function List.length. *)

(* 9.7.1 Develop the following three versions of functions computing Fibonacci 
        numbers Fn (see Exercise 1.5):  
   A version fibA: int -> int -> int -> int with two accumulating parameters
   n1 and n2, where fibA n n1 n2 = Fn, when n1 = Fn−1 and n2 = Fn−2. 
   Hint: consider suitable definitions of F−1 and F−2.*)

(* 9.8 Develop a version of the counting function for binary trees
       countA: int ->BinTree<′a>->int
       that makes use of an accumulating parameter.
       Observe that this function is not tail recursive.*)
