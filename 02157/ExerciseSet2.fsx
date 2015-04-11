type FileSys = Element list
    and Element =
        | File of string * string
        | Dir of string * FileSys

let d1 = Dir("d1",[File("a1","java");
                   Dir("d2", [File("a2","fsx");
                     Dir("d3",  [File("a3","fs")])]);
                   File("a4","fsx");
                   Dir("d3", [File("a5","pdf")])]);;

let rec namesFileSys = function
    | [] -> []
    | e::es -> (namesElement e) @ (namesFileSys es)
and namesElement = function
    | File (s, ext) -> [s+"."+ext]
    | Dir(s,fs) -> s :: (namesFileSys fs) ;;

let dnames = namesElement d1 ;;
assert (dnames = ["d1"; "a1.java"; "d2"; "a2.fsx"; "d3";
                "a3.fs"; "a4.fsx"; "d3"; "a5.pdf"]);;

let rec namesFileSysTuple = function
    | [] -> []
    | e::es -> (namesElementTuple e) @ (namesFileSysTuple es)
and namesElementTuple = function
    | File (s,ext) ->  [(s,ext)]
    | Dir(s,fs) -> (s,null) :: (namesFileSysTuple fs) ;;

    let dnames1 = namesElementTuple d1 ;;


let getExtension (s,ext) = ext;


let search extension filesystem = set (List.map (fun (s,ext) -> s) 
                                        (List.filter (fun element -> getExtension(element) = "fsx") 
                                            (namesElementTuple filesystem)));

let locatefsx = search "fsx" d1;;

(* longNamesFileSys: FileSys -> Set<string> *)
(* longNamesElement: Element -> Set<string> *)
let rec longNamesFileSys = function
    | [] -> []
    | e::es -> (longNamesElement e) @ (longNamesFileSys es)
and longNamesElement = function
    | File (s, ext) -> [s+"."+ext]
    | Dir(s,fs) -> s :: (longNamesFileSys fs) ;