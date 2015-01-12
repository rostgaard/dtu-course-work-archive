#load "..\Project1\AST.fs"

open AST

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

(*
let transform = function

// exp: Exp -> Env -> Store -> Value * Store 
let rec exp e (env:Env) (store:Store) = 
    match e with
    | Var v       -> match Map.find v env with
                     | Reference loc as refl -> (refl,store)
                     | IntVal i as refl      -> (refl, store)
                     | StringVal s as refl   -> (refl, store)
                     | BoolVal v as refl     -> (refl, store)
                     | _ as refl             -> raise (TypeError("This is not supported var type " + string refl))
    | ArrVar(s, e) -> match exp e env store with
                        | (IntVal index, newStore) -> match Map.find s env with
                                                        | Reference loc -> match Map.find loc store with
                                                                            | ArrayList(_, values) as x -> ((Array.get values index), store)
                                                                            | _ as res -> raise (TypeError("Cant find attribute. This is not reference to an array: " + string res))
                                                        | _ as res      -> raise (TypeError("Cant find attribute. This is not reference: " + string res))
                        | (index, newStore)       -> raise (TypeError("This is not a proper index for array: " + string index))
    | Attribute(s, a) -> match Map.find s env with
                            | Reference loc -> match Map.find loc store with 
                                                  | ArrayList(length, _) -> ((IntVal length),store)
                                                  | _           -> raise (TypeError("Only support for array.length attribute"))
                            | _             -> raise (TypeError("This object stores no reference:" + s))
    
    | ContOf er    -> match exp er env store with
                      | (Reference loc,store1) -> match Map.find loc store1 with 
                                                  | SimpVal res   -> (res,store1)
                                                  | ArrayList res -> (Reference loc, store1)
                                                  | Proc res      -> (Reference loc, store1)
                                                  | _ as res      -> raise (TypeError("This is not legit content type: " + string res))
                      | _                   -> raise (TypeError("This is not a proper reference:" + string er))

    | Apply(func,es) -> let child1 = Node("Arguments", [expList es])
                        let child2 = Node("Operator", [Node(func, [])])
                        Node("Apply", [child1; child2])
    | Int i       -> Node("IntVal", [Node(string i, [])])
    | Bool b      -> Node("BoolVal", [Node(string b, [])])
    | String s    -> Node("StringVal", [Node(s, [])])
    | AndOp (e1, e2) -> let child1 = Node("Argument", [exp e1])
                        let child2 = Node("Argument", [exp e2])
                        Node("And", [child1; child2]
    | OrOp  (e1, e2) -> let child1 = Node("Argument", [exp e1])
                        let child2 = Node("Argument", [exp e2])
                        Node("Or", [child1; child2]

and expList = function
    | []       -> []
    | e::erest -> (exp e) :: (expList erest)

// stm: Stm -> Env -> Store -> option<Value> * Store
and st stm = 
    match stm with
    | ArrAsg(s, ind, e) -> let child1 = Node("Array", [Node(s, [])])
                           let child2 = Node("Index", [exp ind])
                           let child3 = Node("Value", [exp e])
                           Node("Array Assign", [child1; child2; child3])
    | Call(s, args) -> let child1 = Node("Proc", [Node(s, [])])
                       let child2 = Node("Args", [expList args])
                       Node("Call", [child1;child2])
    | Asg(el,e) -> let child1 = Node("Var", [exp el]);
                   let child3 = Node("Value", [exp e])
                   Node("Assign", [child1; child3])
    | PrintLn e -> Node("print", [exp e])
    | Seq (sts) -> List.map st sts

    | While(e,stms)  -> let child1 = Node("Condition", [exp e])
                        let child2 = Node("Statements", [st stms])
                        Node("While", [child1;child2]);

    | Block(ds,stms) -> (decList ds) :: (st stms)

    | Return (e)    -> Node("Return", [exp e]);

    | IfElse (cond, tbranch, fbranch) -> let child1 = Node("Condition", [exp cond]);
                                         let child2 = Node("True branch", [st tbranch])
                                         let child3 = Node("False branch", [st fbranch])
                                         Node("IfElse", [child1, child2, child3])
    | Skip                          -> Node("Skip", [])
    | Foreach (iden, colName, body) -> let child1 = Node("Iterator", [Node(iden, [])])
                                       let child2 = Node("Array", [Node(colName,[])])
                                       let child3 = Node("Statements", [st stms]);
                                       Node("Foreach", [child1; child2; child3])
    | For(def, con, inc, stms)   -> let child1 = Node("Initialization", [st def])
                                    let child2 = Node("Condition", [exp con])
                                    let child3 = Node("Incrementation", [st inc])
                                    let child4 = Node ("Statements", [st stms])
                                    Node("For", [child1; child2; child3; child4]);;

and decList ds  = 
    match ds with
    | []       -> []
    | d::drest -> (dec d) :: decList drest

and dec d =
    match d with 
    | VarDec(s,e) -> let child1 = Node("Var", [Node(s, [])])
                     let child2 = Node(exp e, [])
                     Node("VarDec", [child1; child2])
    | ProcDec (s, args, stm) -> let gChild = stList stm
                                let child = Node("Proc " + s + "(" + args + ")", gChild)
                                Node("ProcDec", [child])
    | ArrDec(s, e, value) -> let child1 = Node("Name ", [Node(s,[])])
                             let child2 = Node("Length ", [exp e])
                             let child3 = Node("Value ", [exp value])
                             Node(ArrDec, [child1; child2; child3]);;
                             *)