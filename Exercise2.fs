(* 6.9 A company consists of departments with sub-departments, which again can have sub-departments, and so on. (The company can also be considered as a department.)
- Assume that each department has a name and a (possibly empty) list of sub-departments. Declare an F# type Department.
- Extend this type so that each department has its own gross income.
- Declare a function to extract a list of pairs (department name, gross income), for all departments.
- Declare a function to extract the total income for a given department by adding up its gross income, including the income of its sub-departments.
- Declare a function to extract a list of pairs (department name, total income) for all departments.
- Declare a function format of type Department -> string, which can be used to get a textual form of a department such that names of sub-departments will occur suitably indented (e.g., with four spaces) on separate lines. (Use printf to print out the result. Do not use printf in the declaration of format.)
*)
