
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

let rmax (p: float, q:float) = if p < q then p else q;;
let rec fit = function 
    | ((p, _)::ps, (_,q)::qs)   -> rmax(fit(ps, qs), p - q + 1.0)
    | _                         -> 0.0;;

let fitlistl es =
    let rec fitlistl' = function
        | (acc,[])    -> []
        | (acc, e::es) -> let x = fit (e,acc) 
                          x :: fitlistl' ((merge (acc, moveextent (e,x))),es)
    fitlistl' ([],es);;

let fitlistr es =
    let rec fitlistr' = function
        | (acc,[])    -> []
        | (acc, e::es) -> let x = fit (acc,e) 
                          x :: fitlistr' ((merge (moveextent (e,x), acc)),es)
    List.rev (fitlistr' ([], (List.rev es)));;

let mean (x,y) = (x+y)/2.0;;
let fitlist es = List.map mean (List.zip (fitlistl es) (fitlistr es))

let design tree = 
    let rec design' (Node(label, subtree)) = 
        match subtree with 
        | []        -> let trees = Node((label, 0.), [])
                       let extents = [(0.0, 2.0)]
                       (trees, extents)
        | subtress  ->  let (trees, extents)    = List.unzip (List.map design' subtree)
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
let root = Node("root", [child1; child2; child3]);;
let this = design root;;
