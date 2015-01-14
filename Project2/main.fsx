(* Load the parser and interpreter *)
#r "FSharp.PowerPack.dll"
#I "../Project1"
#load "AST.fs"
#load "Parser.fs"
#load "Lexer.fs"
#load "ParserUtil.fs"

open AST
open System
open System.IO
open ParserUtil
open System.Text

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

let mergeList es = List.fold (fun acc e -> merge (acc, e)) [] es ;;

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


// exp: Exp -> Node
let rec expToTree e  = 
    match e with
    | Var v       -> Node(v, [])
    | ArrVar(s, e) -> let child1 = Node("Array", [Node(s, [])])
                      let child2 = Node("Index", [expToTree e])
                      Node("Array Cell", [child1; child2])
    | Attribute(s, a) -> let child1 = Node("Object", [Node(s, [])])
                         let child2 = Node("Attribute name", [Node(a, [])])
                         Node("Attribute", [child1; child2])
    | ContOf er    -> let Node (lbl, list) = expToTree er
                      Node ("!", [])
    | Apply(func,es) -> Node(func, expListToTree es)
    | Int i       -> Node("Int: " + string i, [])
    | Bool b      -> Node("BoolVal", [Node(string b, [])])
    | String s    -> Node("StringVal", [Node(s, [])])
    | AndOp (e1, e2) -> let child1 = Node("Argument", [expToTree e1])
                        let child2 = Node("Argument", [expToTree e2])
                        Node("And", [child1; child2])
    | OrOp  (e1, e2) -> let child1 = Node("Argument", [expToTree e1])
                        let child2 = Node("Argument", [expToTree e2])
                        Node("Or", [child1; child2])

and expListToTree = function
    | []       -> []
    | e::erest -> (expToTree e) :: (expListToTree erest)

// stm: Stm -> Node
and stmToTree stm = 
    match stm with
    | ArrAsg(s, ind, e) -> let child1 = Node("Array", [Node(s, [])])
                           let child2 = Node("Index", [expToTree ind])
                           let child3 = Node("Value", [expToTree e])
                           Node("Array Assign", [child1; child2; child3])
    | Call(s, args) -> let child1 = Node("Proc", [Node(s, [])])
                       let child2 = Node("Args", expListToTree args)
                       Node("Call", [child1;child2])
    | Asg(el,e) -> let child1 = Node("Var", [expToTree el]);
                   let child3 = Node("Value", [expToTree e])
                   Node("Assign", [child1; child3])
    | PrintLn e -> Node("print", [expToTree e])
    | Seq (sts) -> Node("Sequence", List.map stmToTree sts)

    | While(e,stms)  -> let child1 = Node("Condition", [expToTree e])
                        let child2 = Node("Statements", [stmToTree stms])
                        Node("While", [child1;child2]);

    | Block(ds,stms) -> let child1 = Node("Declarations", (decListToTree ds))
                        //match stms with
                        //| Seq (stms) -> Node("Block", decListToTree ds @ List.map stmToTree stms)
                        //| single_stm -> stmToTree single_stm
                        let child2 = stmToTree stms
                        Node("Block", [child1;child2])

    | Return (e)    -> Node("Return", [expToTree e])

    | IfElse (cond, tbranch, fbranch) -> let child1 = Node("Condition", [expToTree cond]);
                                         let child2 = Node("True", [stmToTree tbranch])
                                         let child3 = Node("False", [stmToTree fbranch])
                                         Node("IfElse", [child1; child2; child3])
    | Skip                          -> Node("Skip", [])
    | Foreach (iden, colName, body) -> let child1 = Node("Iterator", [Node(iden, [])])
                                       let child2 = Node("Array", [Node(colName,[])])
                                       let child3 = Node("Statements", [stmToTree body]);
                                       Node("Foreach", [child1; child2; child3])
    | For(def, con, inc, stms)   -> let init = Node("Init", [stmToTree def])
                                    let eval = Node("Eval", [expToTree con])
                                    let incr = Node("Incr", [stmToTree inc])
                                    let body = Node ("Body", [stmToTree stms])
                                    Node("For", [init; eval; incr; body])

and decListToTree ds  = 
    match ds with
    | []       -> []
    | d::drest -> (decToTree d) :: decListToTree drest

and decToTree d =
    match d with 
    | VarDec(s,e) -> Node("Var: " + s, [expToTree e])
    | ProcDec (s, args, stm) -> let args = Node("Args", (List.map (fun x -> Node(x, [])) args))
                                let stms = Node("Statements", [stmToTree stm])
                                Node("Proc: " + s, [args; stms])
    | ArrDec(s, e, value) -> let len  = Node("Length ", [expToTree e])
                             let init = Node("Value ", [expToTree value])
                             Node("Array: " + s, [len; init]);;


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
    | Asg(el,e) -> let child1 = Node("Variable", [exp el]);
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
    | VarDec(s,e) -> let child1 = Node("Variable", [Node(s, [])])
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

let fig6 = Node ("A", 
             [Node ("B", 
               [Node ("C",
                 [Node ("D",
                   [Node ("E",[]);
                    Node ("F",[])
                   ]);
                  Node ("G", 
                    [Node ("H", [])
                  ])
                 ]);
                Node ("I",
                 [Node ("J",
                    [Node ("K", [])
                    ])
                  ]);
                 ]);
              Node ("L", 
                [Node ("M",
                  [Node ("N", 
                    [Node ("O", [])
                    ])
                  ])
                ]);
                ]);;

let fig6a = Node ("A", 
             [Node ("B", 
               [Node ("C",
                 [Node ("D",
                   [Node ("E",[]);
                    Node ("F",[])
                   ]);
                  Node ("G", 
                    [Node ("H", [])
                  ])
                 ]);
                Node ("I",
                 [Node ("J",
                    [Node ("K", [])
                    ])
                  ]);
                 Node ("C1",[])]);
              Node ("L", 
                [Node ("M",
                  [Node ("N", 
                    [Node ("O", [])
                    ])
                  ]);
                 Node ("P",[]);
                 Node ("Q",[])
                ])]);;


let lineHeight = 50.0;;
let lineWidth  = 60.0;;

let rootx = 600.0;;
let rooty = 800.0;;

let labelpadding = 10.0;

let labelToPSString label = "("+ label + ") dup stringwidth pop 2 div neg 0 rmoveto show";


let rec treePrint node level = 
  match node with 
  | Node ((label, reflPos),[])      -> let (abs_x, abs_y) = (absoluteOffset level reflPos)
                                       (string abs_x) + " " + (string abs_y) + " moveto\n" +
                                        (labelToPSString label) + "\n"
  | Node ((label, reflPos),subtree) -> let (abs_x, abs_y) = (absoluteOffset level reflPos)
                                       (string abs_x) + " " + (string abs_y) + " moveto\n" +
                                       (labelToPSString label) + "\n" +
                                       subtreePrint (subtree,level+1.0, reflPos)
and absoluteOffset level reflectPos = ((reflectPos * lineWidth) + rootx), (rooty - (level * lineHeight))
and subtreePrint = function
  | [],level,parentReflPos                                  -> ""
  | Node ((label, reflPos),subtree)::rest,level,parentReflPos -> let (abs_par_x, abs_par_y) = (absoluteOffset (level-1.0) parentReflPos)
                                                                 let (abs_x, abs_y) = (absoluteOffset level (reflPos+parentReflPos))
                                                                 let abs_x1 = abs_par_x + (reflPos*lineWidth)
                                                                 string (abs_par_x) + " " + string (abs_par_y-labelpadding) + " moveto\n" +
                                                                 string (abs_x1)     + " " + string (abs_par_y-labelpadding)     + " lineto\n" +
                                                                 string (abs_x1)     + " " + string (abs_y+labelpadding)     + " lineto\n" +
                                                                 " stroke\n" + 
                                                                 treePrint (Node ((label, reflPos+parentReflPos),subtree)) level + 
                                                                 subtreePrint (rest,level,parentReflPos);;



let labelToPSStringSB (builder : StringBuilder) (label : string) = builder.Append "(" 
                                                                   builder.Append label 
                                                                   builder.Append ") dup stringwidth pop 2 div neg 0 rmoveto show"
                                                                   builder;;

let treePrintSB' tree = 
    let rec treePrintSB node level (sb : StringBuilder) =
        match node with 
        | Node ((label, reflPos),[])      -> let (abs_x, abs_y) = (absoluteOffsetSB level reflPos)
                                             sb.Append (string abs_x)
                                             sb.Append " " 
                                             sb.Append (string abs_y)
                                             sb.Append " moveto\n"
                                             let sb2 = (labelToPSStringSB sb label) 
                                             sb2.Append "\n"
                                             sb2
        | Node ((label, reflPos),subtree) -> let (abs_x, abs_y) = (absoluteOffset level reflPos)
                                             sb.Append (string abs_x) 
                                             sb.Append " " 
                                             sb.Append (string abs_y) 
                                             sb.Append " moveto\n" 
                                             let sb2 = (labelToPSStringSB sb label) 
                                             sb2.Append "\n" 
                                             let sb3 = (subtreePrintSB (subtree,level+1.0, reflPos, sb2))
                                             sb3
    and absoluteOffsetSB level reflectPos = ((reflectPos * lineWidth) + rootx), (rooty - (level * lineHeight))
    and subtreePrintSB = function
      | [],level,parentReflPos, sb                                  -> sb
      | Node ((label, reflPos),subtree)::rest,level,parentReflPos, sb -> let (abs_par_x, abs_par_y) = (absoluteOffsetSB (level-1.0) parentReflPos)
                                                                         let (abs_x, abs_y) = (absoluteOffsetSB level (reflPos+parentReflPos))
                                                                         let abs_x1 = abs_par_x + (reflPos*lineWidth)
                                                                         sb.Append (string (abs_par_x))
                                                                         sb.Append " " 
                                                                         sb.Append (string (abs_par_y-labelpadding)) 
                                                                         sb.Append " moveto\n" 
                                                                         sb.Append (string (abs_x1))
                                                                         sb.Append " " 
                                                                         sb.Append (string (abs_par_y-labelpadding))
                                                                         sb.Append " lineto\n" 
                                                                         sb.Append (string (abs_x1))
                                                                         sb.Append " " 
                                                                         sb.Append (string (abs_y+labelpadding))
                                                                         sb.Append " lineto\n" 
                                                                         sb.Append " stroke\n" 
                                                                         let sb2 = (treePrintSB (Node ((label, reflPos+parentReflPos),subtree)) level sb)
                                                                         let sb3 = (subtreePrintSB (rest,level,parentReflPos, sb2))
                                                                         sb3
    let sb = StringBuilder()
    let result = treePrintSB tree 1.0 sb
    result.ToString();;


let labelToPSStringCon label = let strings = seq["("; label; ") dup stringwidth pop 2 div neg 0 rmoveto show"]
                               String.concat "" strings;;


let rec treePrintCon node level = 
  match node with 
  | Node ((label, reflPos),[])      -> let (abs_x, abs_y) = (absoluteOffsetCon level reflPos)
                                       let strings = seq[(string abs_x); " "; (string abs_y); " moveto\n";
                                                         (labelToPSStringCon label); "\n"]
                                       String.concat "" strings
  | Node ((label, reflPos),subtree) -> let (abs_x, abs_y) = (absoluteOffsetCon level reflPos)
                                       let strings = seq[(string abs_x); " "; (string abs_y); " moveto\n";
                                                         (labelToPSStringCon label); "\n";
                                                          subtreePrintCon (subtree,level+1.0, reflPos)];
                                       String.concat "" strings
and absoluteOffsetCon level reflectPos = ((reflectPos * lineWidth) + rootx), (rooty - (level * lineHeight))
and subtreePrintCon = function
  | [],level,parentReflPos                                  -> ""
  | Node ((label, reflPos),subtree)::rest,level,parentReflPos -> let (abs_par_x, abs_par_y) = (absoluteOffsetCon (level-1.0) parentReflPos)
                                                                 let (abs_x, abs_y) = (absoluteOffset level (reflPos+parentReflPos))
                                                                 let abs_x1 = abs_par_x + (reflPos*lineWidth)
                                                                 let strings = seq[string (abs_par_x); " "; string (abs_par_y-labelpadding);
                                                                                 " moveto\n"; string (abs_x1); " "; string (abs_par_y-labelpadding);
                                                                                 " lineto\n"; string (abs_x1); " "; string (abs_y+labelpadding);
                                                                                 " lineto\n"; " stroke\n"; 
                                                                                 treePrintCon (Node ((label, reflPos+parentReflPos),subtree)) level;
                                                                                 subtreePrintCon (rest,level,parentReflPos)]
                                                                 let result = String.concat "" strings
                                                                 // printf "%s\n" result
                                                                 result;;

let PSheader = "%!PS\n0.5 0.5 scale /Courier\n10 selectfont\n";;
let PSfooter = "showpage";;


let PSFileWrite path tree = File.WriteAllText (path, PSheader + (treePrint tree 1.0) + PSfooter);;
let PSFileWriteSB path tree = File.WriteAllText (path, PSheader + (treePrintSB' tree) + PSfooter);;
let PSFileWriteCon path tree = File.WriteAllText (path, PSheader + (treePrintCon tree 1.0) + PSfooter);;

#time "on";;

//PSFileWrite "fig6.ps" (design fig6);;
//PSFileWrite "fig6a.ps" (design fig6a);;

// =================================================
// Time testing 
// =================================================
let a1 = design (st ap1);;
PSFileWrite "1_ArrayProg1.ps" (a1);;
let a2 = design (st ap2);;
PSFileWrite "2_ArrayProg2.ps" (a2);;
let a3 = design (st p6);;
PSFileWrite "3_Factorial4.ps" (a3);;
let a4 = design (st p5);;
PSFileWrite "4_Factorial3.ps" (a4);;
let a5 = design (st foreachTest);;
PSFileWrite "5_ForeachLoop.ps" (a5);;

printf "%s" "Typical concatenation\n";;
PSFileWrite "1a_ArrayProg1.ps" (design (st ap1));;
PSFileWrite "2a_ArrayProg2.ps" (design (st ap2));;
PSFileWrite "3a_Factorial4.ps" (design (st p6));;
PSFileWrite "4a_Factorial3.ps" (design (st p5));;
PSFileWrite "5a_ForeachLoop.ps" (design (st foreachTest));;

printf "%s" "StringBuilder concatenation\n";;
PSFileWriteSB "1b_ArrayProg1.ps" (design (st ap1));;
PSFileWriteSB "2b_ArrayProg2.ps" (design (st ap2));;
PSFileWriteSB "3b_Factorial4.ps" (design (st p6));;
PSFileWriteSB "4b_Factorial3.ps" (design (st p5));;
PSFileWriteSB "5b_ForeachLoop.ps" (design (st foreachTest));;

printf "%s" "String.concat concatenation\n";;
PSFileWriteCon "1c_ArrayProg1.ps" (design (st ap1));;
PSFileWriteCon "2c_ArrayProg2.ps" (design (st ap2));;
PSFileWriteCon "3c_Factorial4.ps" (design (st p6));;
PSFileWriteCon "4c_Factorial3.ps" (design (st p5));;
PSFileWriteCon "5c_ForeachLoop.ps" (design (st foreachTest));;



PSFileWriteCon "Factorial3.ps" (design (stmToTree p5));;