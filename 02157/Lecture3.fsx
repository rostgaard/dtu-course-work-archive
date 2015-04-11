// 4.1
let upTo n = [1 .. n];;

let testUpTo = upTo 5;;

// 4.2
let downTo n = [n .. -1 .. 1];;

let testDownTo = downTo 5;;

// 4.3
let evenN n = [2 .. 2 .. n*2];;

let testEvenN = evenN 10;;

// 4.9
let rec unzip = function
| []          -> ([],[])
| (x,y)::rest -> let (xs,ys) = unzip rest
                 (x::xs, y::ys);;
let rec zip = function
| _,[]              -> []
| [],_              -> []
| x::xs, y::ys ->(x,y)::zip (xs,ys);;

let a = zip ([1;2;3],[1;2;3]);;

// 4.11.1
let rec count = function
| [],n               -> 0
| x::xs,n when x > n -> 0
| x::xs,n when x = n -> 1+ count (xs,n)
| x::xs,n            -> count (xs,n);;

let count3 = count ([3;4;4;5;5;5;6],3);;
let count4 = count ([3;4;4;5;5;5;6],4);;
let count5 = count ([3;4;4;5;5;5;6],5);;

// 4.11.2
let rec insert = function
| [],newx                  -> [newx]
| x::xs,newx when x > newx -> newx::x::xs
| x::xs,newx               -> x::insert(xs,newx);;

let newList = insert ([3;4;4;5;5;6],8);;

// 4.11.2
let rec insert = function
| [],newx                  -> [newx]
| x::xs,newx when x > newx -> newx::x::xs
| x::xs,newx               -> x::insert(xs,newx);;

let newList = insert ([3;4;4;5;5;6],8);;

// 4.11.3
let rec intersect = function
| [],_              -> []
| _,[]              -> []
| x::xs,y::ys when x = y -> x::intersect (xs,ys)
| x::xs,y::ys when x > y -> intersect (x::xs,ys)
| x::xs,y::ys when x < y -> intersect (xs,y::ys);;

let intersectTest = intersect ([1;1;1;2;2], [1;1;2;4]);;