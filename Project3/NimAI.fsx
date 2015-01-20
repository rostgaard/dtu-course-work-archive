module NimAI

let rec calculateM n = function
    | []    -> n
    | x::xs -> calculateM (n ^^^ x) xs;;

let rec getNewAk m index = function 
 | []    -> failwith "getNewAk: Cannot find appropriate move from an empty list!"
 | x::xs -> let newV = x ^^^ m 
            if newV < x then
                (newV, index)
            else 
                getNewAk m (index+1) xs

let nextMove x = let m = calculateM 0 x
                 if m = 0 then
                        let maxi  = List.max x
                        let index = List.findIndex (fun y -> (y = maxi)) x
                        let array = List.toArray x
                        Array.set array index (maxi-1)

                        printf  "m = 0: newval:%d idx:%d" (maxi-1) index 
                        Array.toList array
                 else 
                        let (newValue, index) = getNewAk m 0 x
                        printf  "m /= 0: newval:%d idx:%d" newValue index  
                        let array = List.toArray x
                        Array.set array index (newValue)
                        Array.toList array
