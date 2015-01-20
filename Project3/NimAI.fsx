module NimAI

let mutable level = 3;;

let random x y = let r = new System.Random()
                 r.Next(x, y);;

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



let nextMoveHard x = let m = calculateM 0 x
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

let rec nextMoveInter x = let m = calculateM 0 x
                          if ((random 0 4) <> 0) then 
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
                          else
                              let heap = random 0 (x.Length)
                              let oldValue = List.nth x heap
                              if oldValue = 0 then 
                                  (nextMoveInter x)
                              else 
                                  let size = random 1 (oldValue+1)
                                  printf  "m /= 0: newval:%d idx:%d" size heap
                                  let array = List.toArray x
                                  Array.set array heap (oldValue-size)
                                  Array.toList array


let rec nextMoveEasy (x : int list) = let heap = random 0 (x.Length)
                                      let oldValue = List.nth x heap
                                      if oldValue = 0 then 
                                        nextMoveEasy x
                                          else
                                          let size = random 1 (oldValue+1)
                                          printf  "m /= 0: newval:%d idx:%d" size heap
                                          let array = List.toArray x
                                          Array.set array heap (oldValue-size)
                                          Array.toList array

let nextMove x = if level = 3 then
                    nextMoveHard x
                 else if level = 2 then
                    nextMoveInter x 
                 else if level = 1 then
                    nextMoveEasy x
                 else failwith "no Such level of difficulty";;