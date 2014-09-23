
type name        = String;;
type phone       = String;;
type male        = bool;;
type birthyear   = int;;
type interest    = string;;
type client      = (name * phone * male * birthyear * (interest) list);;
type clientMatch = (name * phone * (interest) list);;
type bureau      = client list;;

exception BadParameters;;
exception PendingLoneliness;;

// Client declarations.
let Lotte = ("Lotte", "40882212", false, 1984, ["Fencing"; "Singing"]);;
let Totte = ("Totte", "40882211", true, 1974, ["Knitting"; "Singing"]);;
let Sigfast = ("Sigfast", "none", true, 1983, ["Looting"; "Entomology"]);;

let acmeDatingInc = [Lotte, Totte, Sigfast];;

let ageDeviates fromAge toAge = abs (fromAge - toAge) > 10;;

let testAgeNotDeviates = ageDeviates 1983 1993;;
assert (not (testAgeNotDeviates));;
let testAgeDeviates    = ageDeviates 1983 1994;;
assert (testAgeDeviates) ;;


// Utility function used for finding shared interests.
let rec intersect  = function
| [],_              -> []
| _,[]              -> []
| x::xs,y::ys when x = y -> x::intersect (xs,ys)
| x::xs,y::ys when x > y -> intersect (x::xs,ys)
| x::xs,y::ys when x < y -> intersect (xs,y::ys)
| _ -> raise BadParameters;;

let sharesInterests c1interest c2interest = not (intersect (c1interest, c2interest) =  []);;


let interestTestSharesOne  = sharesInterests ["Fencing"; "Singing"] ["Knitting"; "Singing"];
assert (interestTestSharesOne = true);;
let interestTestSharesNone = sharesInterests ["Fencing"; "Shouting"] ["Knitting"; "Singing"];
assert (interestTestSharesNone = false);;

let clientsCompatible (c1name, c1phone, c1sex, c1birthyear, c1interest)
                      (c2name, c2phone, c2sex, c2birthyear, c2interest)
                      = not (c1sex = c2sex)                       && 
                        not (ageDeviates c1birthyear c2birthyear) && 
                        sharesInterests c1interest c2interest;;

let testCompatibleClients   = clientsCompatible Lotte Totte;;
assert (testCompatibleClients);;
let testInCompatibleClients = clientsCompatible Lotte Sigfast;;
assert (not (testInCompatibleClients));;

//let rec findMatches mySex myBirthyear myInterests =

