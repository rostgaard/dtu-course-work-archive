
(* Department type declaration.*)
type Income = int;;
type Name   = string;;

type Departments = Node list
    and Node =
        | Department of Name * Income * Departments

(* Department declarations. *)
let production1 = Department ("Shoe shrinking", 10,[]);
let production2 = Department ("Hat feathering ", 4,[]);
let production3 = Department ("Hat tipping", 2,[]);

let salesShoes = Department ("Sales (Shoes)", -1,[]);;
let salesHats = Department ("Sales (Hats)", -1,[]);;
let Marketing = Department ("Marketing", 3, []);;
let salesManagement = Department ("Sales Management", 2, [salesHats;salesShoes]);;
let productionUnits = Department("Production units",2,[production1;production2;production3]);;

let company = Department ("Shoes and hats inc.",-5,[salesManagement;productionUnits]);;

(* Helper function to convert a company tree to a list of companies.*)
let rec toList = function
    | Department (name, income, []) -> [Department (name, income, [])]
    | Department (name, income, sub) -> Department (name, income, sub) :: subDepartments sub
and subDepartments = function
    | [] -> []
    | Department (name, income, sub)::[] -> Department (name, income, sub):: subDepartments (sub)
    | Department (name, income, sub)::rest -> Department (name, income, sub) :: subDepartments (rest);;

(* Helper function for summing up the total income of a department list.*)
let sumIncome departments = List.fold (fun x  (Department (name, income, sub)) -> income+x) 0 departments;;
let departmentIncome department = sumIncome (toList department);;

assert ((departmentIncome productionUnits)= 10+4+2+2);;

(** Implementation **)

(* This method converts a department to a list of tuples containing its sub departments. *)
let incometuple = function 
   | Department (name, income, subs) -> (name, income);;
let departmentsIncome company = List.map (incometuple) (toList company);;

(* Total department incomes. *)
let totalincometuple = function 
   | Department (name, income, subs) -> (name, departmentIncome (Department (name, income, subs)));;
let departmentsTotalIncome company = List.map (totalincometuple) (toList company);;

(* ToString method *)

let departmentAsString = function
    | Department (name, income, subdeps) -> name + " " + string (income);;

let rec departmentsAsString = function
    |[] -> ""
    | Department(name, income, subdeps)::rest -> departmentAsString(Department(name, income, subdeps)) + subDepartmentString (subdeps, 1) + departmentsAsString (rest)
and indention = function
  | 0 -> ""; 
  | x -> "    " + indention (x-1)
and subDepartmentString = function
    | [],_         -> ""
    | Department (name, income, [])::rest  ,level -> "\n" + indention level + departmentAsString (Department (name, income, [])) + subDepartmentString (rest,level)
    | Department (name, income, subdeps)::rest,level -> "\n" + indention level + departmentAsString (Department (name, income, [])) + subDepartmentString (subdeps,level+1) + subDepartmentString (rest,level) ;;


printf "%s\n\n" (departmentsAsString [company]);;

