type Fexpr = | Const of float
             | X
             | Add of Fexpr * Fexpr
             | Sub of Fexpr * Fexpr
             | Mul of Fexpr * Fexpr
             | Div of Fexpr * Fexpr
             | Sin of Fexpr
             | Cos of Fexpr
             | Log of Fexpr
             | Exp of Fexpr;;

let rec D = function
  | Const _    -> Const 0.0
  | X          -> Const 1.0
  | Add(fe,ge) -> Add(D fe, D ge)
  | Sub(fe,ge) -> Sub(D fe, D ge)
  | Mul(fe,ge) -> Add(Mul(D fe, ge), Mul(fe, D ge))
  | Div(fe,ge) -> Div(Sub(Mul(D fe,ge), Mul(fe,D ge)),
                      Mul(ge,ge))
  | Sin fe     -> Mul(Cos fe, D fe)
  | Cos fe     -> Mul(Const -1.0, Mul(Sin fe, D fe))
  | Log fe     -> Div(D fe, fe)
  | Exp fe     -> Mul(Exp fe, D fe);;

let rec toString = function
    | Const x       -> string x
    | X             -> "x"
    | Add(fe1,fe2)  -> "(" + (toString fe1) + ")"
                       + " + " + "(" + (toString fe2) + ")"
    | Sub(fe1,fe2)  -> "(" + (toString fe1) + ")"
                       + " - " + "(" + (toString fe2) + ")"
    | Mul(fe1,fe2)  -> "(" + (toString fe1) + ")"
                       + " * " + "(" + (toString fe2) + ")"
    | Div(fe1,fe2)  -> "(" + (toString fe1) + ")"
                       + " / " + "(" + (toString fe2) + ")"
    | Sin fe        -> "(sin " + (toString fe) + ")"
    | Cos fe        -> "(cos " + (toString fe) + ")"
    | Log fe        -> "(log " + (toString fe) + ")"
    | Exp fe        -> "(exp " + (toString fe) + ")";;


let rec red = function
  | Const c    -> Const c
  | X          -> X
  | Add(fe,ge) -> match fe,ge with
                    | (Const 0.0,expr) -> red expr
                    | (expr,Const 0.0) -> red expr
                    | (Const c1, Const c2) -> Const (c1+c2)
                    | _ ->  Add(red fe, red ge)
  | Sub(fe,ge) -> Sub(fe, ge)
  | Mul(fe,ge) -> match fe, ge with
                    | (Const 0.0,_) -> Const 0.0
                    | (_,Const 0.0) -> Const 0.0
                    | (expr,Const 1.0) -> red expr
                    | (Const 1.0, expr) -> red expr
                    | _  ->  Mul(red fe,red ge)
  | Div(fe,ge) -> Div(fe,ge)
  | Sin fe     -> Sin fe
  | Cos fe     -> Cos fe 
  | Log fe     -> Log fe
  | Exp fe     -> Exp fe;;

let addexpr1 = Add (Const 2.0, Const 2.0);;

toString (red (Mul (Const 2.0, addexpr1)));;
toString (red (Mul (Const 1.0, addexpr1)));;
toString (red (Mul (Const 0.0, addexpr1)));;