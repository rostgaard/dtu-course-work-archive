// Exercise 2
let add (x1:float ,y1:float ) (x2:float ,y2:float ) = x1+x2,y1+y2;;
add (1.0,2.0) (3.0,4.0)

// Exercise 3
let rec toString sep:string xs = 
  match xs with
     | []     -> "]"
     | x::xs' -> "[" + toString xs' sep

toString "-" [1,2]
