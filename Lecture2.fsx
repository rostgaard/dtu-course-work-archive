let rec merge = function 
  | ([],x)                         -> x
  | (x,[])                         -> x
  | (x::restx,y::resty) when x < y -> x::merge (restx,y::resty)
  | (x::restx,y::resty) when x = y -> x::merge (restx,resty)
  | (x::restx,y::resty) when x > y -> y::merge (x::restx,resty);;

  let set1 = [1;2;3;4;6];;
  let set2 = [2;5];;

  let hat = merge (set1,set2);;