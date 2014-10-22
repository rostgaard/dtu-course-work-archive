(* 6.9 A company consists of departments with sub-departments, which again can have sub-departments, and so on. (The company can also be considered as a department.)
- Assume that each department has a name and a (possibly empty) list of sub-departments. Declare an F# type Department.
- Extend this type so that each department has its own gross income.

*)
//TODO add function type declarations
type Income = int;;
type Name   = string;;

type Departments = Node list
    and Node =
        | Department of Name * Income * Departments



let production1 = Department ("production1", 10,[]);
let production2 = Department ("production2", 4,[]);
let production3 = Department ("production3", 2,[]);

let salesShoes = Department ("Sales (Shoes)", -1,[]);;
let salesHats = Department ("Sales (Hats)", -1,[]);;
let productionUnits = Department("Production units",2,[production1;production2]);;

let company = Department ("Shoes and hats inc.",-5,[salesHats;salesShoes;productionUnits]);;

(* Declare a function to extract a list of pairs (department name, gross income), for all departments. *)
let rec toList = function
    | [] -> []
    | e::es -> (namesElement e) @ (toList es)
and namesElement = function
    | Department (name, income, []) -> [(name,income)]
    | Department (name, income, rest) -> (name,income) :: (toList rest) ;;
    
let rec asList = function
    | Department (name, income, []) -> [(name,income)]
    | Department (name, income, sub) -> (name,income) :: subdepartments sub
and subdepartments = function
    | [] -> []
    | Department (name, income, sub)::[] -> [(name,income)]
    | Department (name, income, sub)::rest -> (name,income):: subdepartments (sub) @ subdepartments (rest);;
 

let hat = toList [company];;
let hat1 = asList company;;

let sumIncome departments = List.fold (fun x  (name, income) -> income+x) 0 departments;;

sumIncome (toList [company]);;

(* Declare a function to extract the total income for a given department by adding up its gross income, including the income of its sub-departments. *)
let rec departmentIncome = function
    | Department (name, income, []) -> income
    | Department (name, income, subdeps) -> income +  sumIncome (toList subdeps);;

departmentIncome (productionUnits);
(* Declare a function to extract a list of pairs (department name, total income) for all departments. *)

let rec departmentsIncome = function
    | Department (name, income, []) -> (name,income)
    | Department (name, income, rest) -> (name,income +  sumIncome (toList rest));;

(* Declare a function format of type Department -> string, which can be used to get a textual form of a department such that names of sub-departments will occur suitably indented (e.g., with four spaces) on separate lines. (Use printf to print out the result. Do not use printf in the declaration of format.) *)

let departmentAsString = function
    | Department (name, income, subdeps) -> name + " (" + string (departmentIncome (Department (name, income, subdeps))) + ")";;

let rec departmentsAsString = function
    |[] -> ""
    | Department(name, income, subdeps)::rest -> departmentAsString(Department(name, income, subdeps)) + subDepartments (subdeps, 1) + departmentsAsString (rest)
and indention = function
  | 0 -> ""; 
  | x -> "    " + indention (x-1)
and subDepartments = function
    | [],_         -> ""
    | Department (name, income, [])::rest  ,level -> "\n" + indention level + departmentAsString (Department (name, income, [])) + subDepartments (rest,level)
    | Department (name, income, subdeps)::rest,level -> "\n" + indention level + departmentAsString (Department (name, income, [])) + subDepartments (subdeps,level+1) + subDepartments (rest,level) ;;


printf "%s\n\n" (departmentsAsString [company]);;

