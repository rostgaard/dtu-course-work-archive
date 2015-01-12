(* Load the parser and interpreter *)
#r "FSharp.PowerPack.dll"
#I "..\Project1"
#load "AST.fs"
#load "Parser.fs"
#load "Lexer.fs"
#load "ParserUtil.fs"

open AST
open System
open ParserUtil

exception TypeError of string

type 'a Tree = Node of ('a * 'a Tree list);;
type Extent = (float * float) list;;

let moveextent ((e : Extent), x : float) = List.map (fun (p, q) -> (p+x, q+x)) e;;

let movetree (Node((label, x), subtree), x' : float) = Node((label, x+x'), subtree);;

let rec merge = function
    | ([], [])  -> []
    | ([], qs)  -> qs
    | (ps, [])  -> ps
    | ((p, _)::ps, (_,q)::qs)    -> (p,q) :: (merge (ps,qs));;

let mergeList es = List.fold (fun acc e -> merge (e,acc)) [] es;;

let rmax (p: float, q:float) = if p > q then p else q;;
let rec fit = function 
    | ((_, p)::ps, (q,_)::qs)   -> rmax(fit(ps, qs), p - q + 1.0)
    | _                         -> 0.0;;

let fitlistl es =
    let rec fitlistl' = function
        | (acc,[])    -> []
        | (acc, e::es) -> let x = fit (acc, e) 
                          x :: fitlistl' ((merge (acc, moveextent (e,x))),es)
    fitlistl' ([],es);;

let fitlistr es =
    let rec fitlistr' = function
        | (acc,[])    -> []
        | (acc, e::es) -> let x = -(fit (e, acc))
                          x :: fitlistr' ((merge (moveextent (e,x), acc)),es)
    List.rev (fitlistr' ([], (List.rev es)));;

let mean (x,y) = (x+y)/2.0;;
let fitlist es = List.map mean (List.zip (fitlistl es) (fitlistr es))

let design tree = 
    let rec design' (Node(label, subtree)) = 
                        let (trees, extents)    = List.unzip (List.map design' subtree)
                        let positions           = fitlist extents
                        let ptrees              = List.map movetree (List.zip trees positions)
                        let pextents            = List.map moveextent (List.zip extents positions)
                        let resultextents       = (0.0, 0.0) :: mergeList pextents
                        let resulttree          = Node((label, 0.0), ptrees)
                        (resulttree, resultextents)
    fst (design' tree)

let child1 = Node("child1", []);;
let child2 = Node("child2", []);;
let grandchild1 = Node("grandchild1", []);;
let child3 = Node("child3", [grandchild1]);;
let child4 = Node("child4", []);;
let root = Node("root", [child1; child2; child3; child4]);;
let this = design root;;


// exp: Exp -> Node
let rec exp e  = 
    match e with
    | Var v       -> Node("Var", [Node(v, [])])
    | ArrVar(s, e) -> let child1 = Node("Array", [Node(s, [])])
                      let child2 = Node("Index", [exp e])
                      Node("Array Cell", [child1; child2])
    | Attribute(s, a) -> let child1 = Node("Object", [Node(s, [])])
                         let child2 = Node("Attribute name", [Node(a, [])])
                         Node("Attribute", [child1; child2])
    | ContOf er    -> Node("ContOf", [exp er])
    | Apply(func,es) -> let child1 = Node("Arguments", expList es)
                        let child2 = Node("Operator", [Node(func, [])])
                        Node("Apply", [child1; child2])
    | Int i       -> Node("IntVal", [Node(string i, [])])
    | Bool b      -> Node("BoolVal", [Node(string b, [])])
    | String s    -> Node("StringVal", [Node(s, [])])
    | AndOp (e1, e2) -> let child1 = Node("Argument", [exp e1])
                        let child2 = Node("Argument", [exp e2])
                        Node("And", [child1; child2])
    | OrOp  (e1, e2) -> let child1 = Node("Argument", [exp e1])
                        let child2 = Node("Argument", [exp e2])
                        Node("Or", [child1; child2])

and expList = function
    | []       -> []
    | e::erest -> (exp e) :: (expList erest)

// stm: Stm -> Node
and st stm = 
    match stm with
    | ArrAsg(s, ind, e) -> let child1 = Node("Array", [Node(s, [])])
                           let child2 = Node("Index", [exp ind])
                           let child3 = Node("Value", [exp e])
                           Node("Array Assign", [child1; child2; child3])
    | Call(s, args) -> let child1 = Node("Proc", [Node(s, [])])
                       let child2 = Node("Args", expList args)
                       Node("Call", [child1;child2])
    | Asg(el,e) -> let child1 = Node("Var", [exp el]);
                   let child3 = Node("Value", [exp e])
                   Node("Assign", [child1; child3])
    | PrintLn e -> Node("print", [exp e])
    | Seq (sts) -> Node("Sequence", List.map st sts)

    | While(e,stms)  -> let child1 = Node("Condition", [exp e])
                        let child2 = Node("Statements", [st stms])
                        Node("While", [child1;child2]);

    | Block(ds,stms) -> let child1 = Node("Declarations", (decList ds))
                        let child2 = Node("Statements", [(st stms)])
                        Node("Block", [child1;child2])

    | Return (e)    -> Node("Return", [exp e])

    | IfElse (cond, tbranch, fbranch) -> let child1 = Node("Condition", [exp cond]);
                                         let child2 = Node("True branch", [st tbranch])
                                         let child3 = Node("False branch", [st fbranch])
                                         Node("IfElse", [child1; child2; child3])
    | Skip                          -> Node("Skip", [])
    | Foreach (iden, colName, body) -> let child1 = Node("Iterator", [Node(iden, [])])
                                       let child2 = Node("Array", [Node(colName,[])])
                                       let child3 = Node("Statements", [st body]);
                                       Node("Foreach", [child1; child2; child3])
    | For(def, con, inc, stms)   -> let child1 = Node("Initialization", [st def])
                                    let child2 = Node("Condition", [exp con])
                                    let child3 = Node("Incrementation", [st inc])
                                    let child4 = Node ("Statements", [st stms])
                                    Node("For", [child1; child2; child3; child4])

and decList ds  = 
    match ds with
    | []       -> []
    | d::drest -> (dec d) :: decList drest

and dec d =
    match d with 
    | VarDec(s,e) -> let child1 = Node("Var", [Node(s, [])])
                     let child2 = Node("Value", [exp e])
                     Node("VarDec", [child1; child2])
    | ProcDec (s, args, stm) -> let child1 = Node("Proc", [Node(s, [])])
                                let child2 = Node("Args", (List.map (fun x -> Node(x, [])) args))
                                let child3 = Node("Statements", [st stm])
                                Node("ProcDec", [child1; child2; child3])
    | ArrDec(s, e, value) -> let child1 = Node("Name ", [Node(s,[])])
                             let child2 = Node("Length ", [exp e])
                             let child3 = Node("Value ", [exp value])
                             Node("ArrDec", [child1; child2; child3]);;


//////////////////////////////////////////////////////////


System.IO.Directory.SetCurrentDirectory __SOURCE_DIRECTORY__;;

let advTestPath = "../Project1/tests/";;
let testPath = "../Project1/";;
printf "%s" "Tests:\n";;

printf "%s" "  Factorial1.while - ";;
let p3 = parseFromFile (testPath + "Factorial1.while");;
try
   st p3
with
    | _ -> printf "%s\n" "(fail) Got unexpected exception!"

printf "%s" "  Factorial2.while - ";;
let p4 = parseFromFile (testPath + "Factorial2.while");;
try
  st p4
with
    | _ -> printf "%s\n" "(fail) Got unexpected exception!"

printf "%s" "  Factorial3.while - ";;
let p5 = parseFromFile (testPath + "Factorial3.while");;
try
  st p5
with
    | _ -> printf "%s\n" "(fail) Got unexpected exception!"

printf "%s" "  Factorial4.while - ";;
let p6 = parseFromFile (testPath + "Factorial4.while");;
try
  st p6
with
    | _ -> printf "%s\n" "(fail) Got unexpected exception!"

printf "%s" "  Factorial5.while - ";;
let p7 = parseFromFile (testPath + "Factorial5.while");;
try
  st p7
with
    | _ -> printf "%s\n" "(fail) Got unexpected exception!"

printf "%s" "  ArrayProg1.while - ";;
let ap1 = parseFromFile (testPath + "ArrayProg1.while");; 
try
  st ap1
with
    | _ -> printf "%s\n" "(fail) Got unexpected exception!"

printf "%s\n" "  ArrayProg2.while (manually expect output) ";;
printf "%s\n" "=== ArrayProg2.while output ===";;
let ap2 = parseFromFile (testPath + "ArrayProg2.while");; 
try
  st ap2
with
    | _ -> printf "%s\n" "(fail) Got unexpected exception!"
printf "%s\n" "=== End ArrayProg2.while output ===";;

printf "%s" "  String as array index error - ";;
let arrayTest = parseFromFile (advTestPath + "ArrayTestStringIndex.while");;
try
  let res0 = st arrayTest
  printf "%s\n" "(fail) Expected exception here."
with
    | TypeError msg  -> printf "%s\n" "(ok)"
    | _              -> printf "%s\n" "(fail) Got unexpected exception!"

printf "%s" "  If-Else parsing - ";;
let ifelseparseTest = parseFromFile (advTestPath + "IfElseParse.while");;
try
  st ifelseparseTest
with
    | _ -> printf "%s\n" "(fail) Got unexpected exception!"

printf "%s" "  Crude array folding - ";;
let arrTest = parseFromFile (advTestPath + "ArrayFolding.while");;
try
  let res1 = st arrTest
  printf ""
with
  | _ -> printf "%s\n" "(fail) Got unexpected exception!"

printf "%s" "  Infix logic parse - ";;
let andOrTest = parseFromFile (advTestPath + "AndOrInfix.while");;
try
  let res2 = st andOrTest
  printf "%s\n" "(ok)"
with
  | _ -> printf "%s\n" "(fail) Got unexpected exception!"

  
printf "%s\n" "Foreach";;

let foreachTest = parseFromFile (advTestPath + "ForeachLoop.while");;
let res3 = st foreachTest;;

printf "%s\n" "Arguments passing";;
//is the parser not allowing to assign to arguments?
let ap3 = parseFromFile (advTestPath + "ArgsReferencesValues.while");; 
let res4 = st ap3;;


let forTest = parseFromFile (advTestPath + "ForLoop.while");;
let res5 = st forTest;;