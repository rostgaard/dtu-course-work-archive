grammar MiniJava;

@lexer::header {
  package compiler.Frontend;
}  

@header {
  package compiler.Frontend;
  
  import java.util.LinkedList;
  import compiler.IR.*;
}

@parser::members {
}

program returns [MJProgram p]
  : { LinkedList<MJClass> cdl = new LinkedList<MJClass>(); }
    mc = mainClass 
    { cdl.add(mc); } 
      ( cd = classDeclaration 
        { cdl.add(cd); } 
      )*
    { 
      p = new MJProgram(cdl);
    } 
  ;

classDeclaration returns [MJClass c] 
  : { String superClass = "Object"; 
      LinkedList<MJVariable> vdl = new LinkedList<MJVariable>();
      LinkedList<MJMethod> mdl = new LinkedList<MJMethod>();
    }
    'class' cname = IDENT ( 'extends' scname = IDENT { superClass = $scname.text; })? 
    '{' ( vd = varDeclaration 
          { vdl.add(vd);
          }
        )* 
        ( md = methodDeclaration 
          { mdl.add(md);
          }
        )* 
    '}'
    {
      c = new MJClass($cname.text, superClass, vdl, mdl);
    }
  ;

mainClass returns [MJClass c]
  : 'class' cname = IDENT 
    '{' 
      'public' 'static' 'void' 'main' '(' 'String[]' parname = IDENT ')' b = block
    '}'
    {
      MJType partype = new MJType(new MJType("String"));
      MJVariable par = new MJVariable( partype, $parname.text);
      LinkedList<MJVariable> parlist = new LinkedList<MJVariable>();
      parlist.add(par);
     
      MJMethod md = new MJMethod(MJType.Tnone, "main", parlist, b, true, true);
      c = new MJClass($cname.text, md);
  }
  ;
  
block returns [MJBlock b]
  : {  LinkedList<MJVariable> vdl = new LinkedList<MJVariable>();
       LinkedList<MJStatement> sdl = new LinkedList<MJStatement>();    
    }
    '{' ( vd = varDeclaration
          { vdl.add(vd); } 
        )* 
        ( sd = statement
          { sdl.add(sd); } 
        )*
    '}'
    {
       b = new MJBlock(vdl, sdl);
    }
  ;
    
varDeclaration returns [MJVariable vd]
  : { MJExpression init = new MJNoExpression(); }
    t = type n = IDENT ( '=' e = expression { init = e; })? ';'
    { vd = new MJVariable(t, $n.text, init);
    }
  ;

type returns [MJType t] 
  : 'boolean'
    { t = MJType.Tboolean; }
  | 'int' 
    { t = MJType.Tint; }
    ( '[' ']'
      { t = new MJType(MJType.Tint); } 
    )?
  | IDENT
    { t = new MJType($IDENT.text); }
  ;

methodDeclaration returns [MJMethod md]
  : { LinkedList<MJVariable> parlist = new LinkedList<MJVariable>(); 
      boolean isStatic = false;
      boolean isPublic = false;
      LinkedList<MJVariable> vdl = new LinkedList<MJVariable>();
      LinkedList<MJStatement> sdl = new LinkedList<MJStatement>();    
    } 
    ( 'public' { isPublic = true; } )?
    ( 'static' { isStatic = true; })? 
    mtype = procType mname = IDENT 
    '(' 
      ( t1 = type n1 = IDENT 
        { parlist.add(new MJVariable(t1,$n1.text)); }
        ( ',' t2 = type n2 = IDENT 
          { parlist.add(new MJVariable(t2,$n2.text)); }
        )* 
      )? 
    ')' 
    '{' ( vd = varDeclaration
          { vdl.add(vd); } 
        )* 
        ( sd = statement
          { sdl.add(sd); } 
        )*
        'return' retExp = optExpression ';'
        { 
          MJReturn r = new MJReturn(retExp);
          sdl.add(r);
        }
    '}'
    {
      MJBlock b = new MJBlock(vdl, sdl);
      md = new MJMethod(mtype, $mname.text, parlist,  b, isStatic, isPublic);
    }  
  ;
  
procType returns [MJType pt]
  : t = type
  { pt = t; }
  | 'void'
  { pt = MJType.Tnone; }
  ;
  
statement returns [MJStatement s]
  : c = comment
    { s = c; }
  | b = block
    { s = b; }
  | 'if' '(' ci = expression ')' b1 = block 
    {
      s = new MJIfElse(ci, b1, null);
    }
    ( 'else' b2 = block 
      {
        s = new MJIfElse(ci, b1, b2);
      }
    )?
  | 'while' '(' cw = expression ')' bw = statement
    {
      s = new MJWhile(cw, bw);
    }
  | va = id '=' ea = expression ';'
  {
    s = new MJAssign(va, ea);
  }
  | va = id '[' idx = expression ']' '=' val = expression ';'
  {
    s = new MJAssign(new MJArray(va, idx), val);
  }
  | 'System.out.println' '(' ep = expression ')' ';'
    {
      s = new MJPrintln(ep);
    }
  | { LinkedList<MJExpression> arglist = new LinkedList<MJExpression>(); } 
    m = id '(' 
    ( a1 = expression 
      { arglist.add(a1); } 
      (',' a2 = expression
        { arglist.add(a2); } 
      )* 
    )? ')' ';'   
    { s = new MJMethodCallStmt( m, arglist); }
  ;
  
comment returns [MJComment c]
  : COMMENT 
    { c = new MJComment($COMMENT.text); }
  ;
  
expression returns [MJExpression res]
  : arg1 = level1 
    { res = arg1; }
    ( '&&' arg2 = level1 
      { MJAnd op = new MJAnd( res, arg2);
        res = op;
      }
    )*
  ;

level1 returns [MJExpression res]
  : arg1 = level2
    {  res = arg1; }
    ( '==' arg2 = level2
      { MJEqual op = new MJEqual( res, arg2); 
        res = op;
      } 
    )*
  ;
  
level2 returns [MJExpression res]
  : arg1 = level3
    {  res = arg1; }
    ( '<' arg2 = level3 
      { MJLess op = new MJLess( res, arg2 ); 
        res = op;
      }
    )*
  ;
    
level3 returns [MJExpression res]
  : arg1 = level4
    {  res = arg1; }
    ( { MJBinaryOp op = null;}
      ( '+' { op = new MJPlus(); } | '-' { op = new MJMinus(); } ) 
      arg2 = level4 
      { op.setLeftOperand( res );
        op.setRightOperand(arg2);
        res = op;
      }
    )*
  ;

level4 returns [MJExpression res]
  :  arg1 = level5
    {  res = arg1; }
    ( '*' arg2 = level5 
      { MJMult op = new MJMult( res, arg2 ); 
        res = op;
      }
    )*
  ;
  
level5 returns [MJExpression e]
  : '-' l = level5
    { e = new MJUnaryMinus(l); }
  | '!' l = level5 
    { e = new MJNegate(l); }
  | 'new' 'int' '[' size = expression ']'
    { e = new MJNewArray(new MJType(MJType.Tint), size);}
  | 'new' IDENT '('')'
    { e = new MJNew(new MJType($IDENT.text)); }
  | i = id
    { e = i; }
  | i = id '[' idx = expression ']'
    { e = new MJArray(i, idx); }
  | { LinkedList<MJExpression> arglist = new LinkedList<MJExpression>(); } 
    m = id '(' 
    ( a1 = expression 
      { arglist.add(a1); } 
      (',' a2 = expression
        { arglist.add(a2); } 
      )* 
    )? ')'    
    { e = new MJMethodCallExpr(m, arglist); }
  | '(' e1 = expression ')'
    { e = new MJParentheses(e1); }
  | 'true' 
    { e = MJBoolean.True; }
  | 'false' 
    { e = MJBoolean.False; }
  | INT 
    { e = new MJInteger($INT.text); }
  | STRING 
    { e = new MJString($STRING.text); }
  ;
  
  id returns [MJIdentifier e]
  : t = thisid
    { e = t; }
  | t = thisid '.' IDENT
    { 
      MJIdentifier i = new MJIdentifier($IDENT.text);
      e = new MJSelector(t,i);
    }
  ;

thisid returns [MJIdentifier e]
  : 'this' 
    { e= new MJIdentifier("this"); }
  | IDENT
    { e = new MJIdentifier($IDENT.text);}
  ;
  
optExpression returns [MJExpression e]
  : ex = expression
    { e = ex; }
  | // empty
    { e = new MJNoExpression(); }
  ;
  
fragment LOWER : ('a'..'z');
fragment UPPER : ('A'..'Z');
fragment NONNULL : ('1'..'9');
fragment NUMBER : ('0' | NONNULL);
IDENT : ( LOWER | UPPER ) ( LOWER | UPPER | NUMBER | '_' )*;
fragment NEWLINE:'\r'? '\n';
INT : '0' | ( NONNULL NUMBER* );
fragment CHAR : ' ' | '!' | ('\u0023'..'\u005B') | ('\u005D'..'\u007E') | '\\"' | '\\\\' | '\\t' | '\\n';
STRING : '"' CHAR* '"' ;
COMMENT : '//' .* NEWLINE | '/*' .* '*/' ;
WHITESPACE  :   ( ' ' | '\t' | NEWLINE )+ { $channel=HIDDEN; };