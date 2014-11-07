
type name        = String;;
type phone       = String;;
type Sex         = Male = 'm' | Female = 'f';;
type birthyear   = int;;
type interest    = string;;
type client      = (name * phone * Sex * birthyear * (interest) list);;
type clientMatch = (name * phone * (interest) list);;
type bureau      = client list;;

exception BadParameters;;
exception PendingLoneliness;;

// Client declarations.
let Lotte = ("Lotte", "40882212", Sex.Female, 1984, ["Fencing"; "Singing"]);;
let Totte = ("Totte", "40882211", Sex.Male, 1974, ["Knitting"; "Singing"]);;
let Sigfast = ("Sigfast", "none", Sex.Male, 1983, ["Looting"; "Entomology"]);;

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

// Determines whether or not the two clients shares interests.
let sharesInterests c1interest c2interest = not (intersect (c1interest, c2interest) =  []);;

// Test cases.
let interestTestSharesOne  = sharesInterests ["Fencing"; "Singing"] ["Knitting"; "Singing"];
assert (interestTestSharesOne = true);;
let interestTestSharesNone = sharesInterests ["Fencing"; "Shouting"] ["Knitting"; "Singing"];
assert (interestTestSharesNone = false);;

// Detemines whether or not two clients are compatible by the rules given in the description.
let clientsCompatible (c1name, c1phone, c1sex, c1birthyear, c1interest)
                      (c2name, c2phone, c2sex, c2birthyear, c2interest)
                      = not (c1sex = c2sex)                       && 
                        not (ageDeviates c1birthyear c2birthyear) && 
                        sharesInterests c1interest c2interest;;

// Test cases.
let testCompatibleClients   = clientsCompatible Lotte Totte;;
assert (testCompatibleClients);;
let testInCompatibleClients = clientsCompatible Lotte Sigfast;;
assert (not (testInCompatibleClients));;

// Look up matches for the given profile.
let rec findMatches mySex myBirthyear myInterests agency = 
  match agency with 
    | []                                         -> []
    | client::remainingClients 
       when clientsCompatible 
         ("", "",mySex, myBirthyear, myInterests) client -> let (c1name, c1phone, c1sex, c1birthyear, c1interest) = client
                                                            (c1name, c1phone, c1interest)::findMatches mySex myBirthyear myInterests remainingClients
    | client::remainingClients                           -> findMatches mySex myBirthyear myInterests remainingClients;;

// Test cases.
let testMatches = findMatches Sex.Male 1983 ["Singing"] [Lotte];
assert (testMatches = [("Lotte", "40882212", ["Fencing"; "Singing"])]);;

let testNotMatches = findMatches Sex.Male 1983 ["Shouting"] [Lotte];
assert (testNotMatches = []);;