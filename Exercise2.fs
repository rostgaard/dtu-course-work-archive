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

let comp1 = Department ("hat",2,[Department("hat",2,[])]);;

let production2 = Department ("production1", 10,[]);

(* Declare a function to extract a list of pairs (department name, gross income), for all departments. *)
let rec toList = function
    | [] -> []
    | e::es -> (namesElement e) @ (toList es)
and namesElement = function
    | Department (name, income, []) -> [(name,income)]
    | Department (name, income, rest) -> (name,income) :: (toList rest) ;;
    
toList [comp1];;

let sumIncome departments = List.fold (fun x  (name, income) -> income+x) 0 departments;;

//let sumIncome departments = List.fold (fun x inc -> inc+x) 0 (List.map (fun (name, income) -> income) departments);;
sumIncome (toList [comp1]);;

(* Declare a function to extract the total income for a given department by adding up its gross income, including the income of its sub-departments. *)
let rec departmentIncome = function
    | Department (name, income, []) -> income
    | Department (name, income, subdeps) -> income +  sumIncome (toList subdeps);;

let rec departmentsIncome = function
    | Department (name, income, []) -> (name,income)
    | Department (name, income, rest) -> (name,income +  sumIncome (toList rest));;

(* Declare a function to extract a list of pairs (department name, total income) for all departments. *)
//TODO

(* Declare a function format of type Department -> string, which can be used to get a textual form of a department such that names of sub-departments will occur suitably indented (e.g., with four spaces) on separate lines. (Use printf to print out the result. Do not use printf in the declaration of format.) *)
let rec toString 