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


 //----------------------------------------------//
 //                Program
 // ---------------------------------------------//

/*
 * A valid program must begin with a class containing only the main method, potentially 
 * followed by a list of classes.
 */
program returns [MJProgram p]:
  { LinkedList<MJClass> cdl = new LinkedList<MJClass>(); }
  mc = mainClass { cdl.add(mc); p = new MJProgram(cdl); }
  (cd = classDeclaration {cdl.add(cd);})*
  ;
  
 //----------------------------------------------//
 //                Classes
 // ---------------------------------------------//

mainClass returns [MJClass c]
  : 'class' cname = IDENT '{' 
    'public' 'static' 'void' 'main' '(' 'String' '[' ']' parname = IDENT ')' b = block
  {
    MJType partype = new MJType(new MJType("String"));
    MJVariable par = new MJVariable( partype, $parname.text);
     
    MJMethod md = new MJMethod(MJType.Tvoid, "main", par, b, true, true);
    c = new MJClass($cname.text, md);
  }
  ;
  
classDeclaration returns [MJClass c]
  : 
  { 
    LinkedList<MJVariable> vdl = new LinkedList<MJVariable>();
    LinkedList<MJMethod> mdl = new LinkedList<MJMethod>();
  } 
  'class' cname = IDENT ('extends' sc = IDENT)? '{' (v = varDeclaration {vdl.add(v);} )*   (m = methodDeclaration {mdl.add(m);})* '}' {
    c = new MJClass($cname.text,$sc.text,vdl,mdl);
  }
  ;
  
block returns [MJBlock b] 
    : 
    {  LinkedList<MJVariable> vdl = new LinkedList<MJVariable>();
       LinkedList<MJStatement> sdl = new LinkedList<MJStatement>();
       
    }
    '{' ( vd = varDeclaration
    { vdl.add(vd); } 
    )* ( sd = statement
    { sdl.add(sd); } 
    )*
    '}'
    {
       b = new MJBlock(vdl, sdl);
    }
    ;  
  
 //----------------------------------------------//
 //                Declarations
 // ---------------------------------------------//
  
varDeclaration returns [MJVariable vd]
  : t = type n = IDENT ';'
  {
    vd = new MJVariable(t,n.getText());
  }
  ;

type returns [MJType t] 
  : 'int' 
  { t = MJType.Tint; }
  | 'boolean'
  { t = MJType.Tboolean; }
  | 'int' '[' ']' 
  {t = MJType.TintArray;}
  | IDENT 
  {
    t = new MJType($IDENT.text);
  }
  ;
   
methodDeclaration returns [MJMethod m]
  : {
     LinkedList<MJVariable> parList = new LinkedList<MJVariable>();
     LinkedList<MJVariable> vdl   = new LinkedList<MJVariable>();
     LinkedList<MJStatement> sdl   = new LinkedList<MJStatement>();
     MJBlock b = new MJBlock(vdl,sdl);
     boolean isStatic,isPublic = false;
  }
  ('public' {isPublic = true;})? ('static' {isStatic = true;})? procType name = IDENT '(' (type par = IDENT {parList.add(par);} (',' type par = IDENT {parList.add(par);})* )? ')' '{'
                  (varDeclaration {vdl.add(varDeclaration);} )* (statement {sdl.add(statement);})*  'return' optExpression ';' '}' 
  
  {
    m = new MJMethod(procType, $name.text, parList, b, isStatic, isPublic); 
  };
  
procType returns [MJType t]
  : 'void' 
  { t = MJType.Tvoid; }
  | type 
  ;
  
 //----------------------------------------------//
 //                Statements
 // ---------------------------------------------// 
  
statement returns [MJStatement s]
  : 'System.out.println' '(' ep = expression ')' ';' // Println I/O
  {
    s = new MJPrintln(ep);
  }
  | va = IDENT '=' ea = expression ';' // Assignment 
  {
    s = new MJAssign(new MJIdentifier($va.text), ea);
  }
  | b = block   //Block 
  { 
    s = b;
  }
  | 'if' '(' cond = expression ')' (ifblock = block) ('else' elseblock = block)? // If-then-else statement
  {
    //TODO - figure out what happens when no else block is present
    s = new MJIfElse(cond,ifblock,elseblock);
  }
  | 'while' '(' expr = expression ')' stmt = statement // While loop
  {
    s = new MJWhile(expr,stmt);
  }
  | {LinkedList<MJExpression> pml = new LinkedList<MJExpression>();} 
   va = IDENT '(' (expr = expression {pml.add(expr);} ( ',' expr = expression)* {pml.add(expr);} )? ')' ';' // Method call
  {
    s = new MJMethodCallStmt(new MJIdentifier($va.text),pml);
  }
  ;  
  
 //----------------------------------------------//
 //                Expressions
 // ---------------------------------------------// 
  
/* AND */
expression returns [MJExpression e]
  : { MJExpression lhs = null; }
  l1 = level1
    {lhs = l1; e = l1;} 
  ('&&' rhs = level1 
   { MJAnd op = new MJAnd();
   op.setLhs(lhs);
   op.seRLhs(rhs);
   lhs = op;
   e = op;
   } 
  )*
  ;

/* Equals */
level1 returns [MJExpression e]
  : { MJExpression lhs = null; }
  l2 = level2
    {lhs = l2; e = l2;} 
  ('==' rhs = level2 
   { MJEqual op = new MJEqual();
   op.setLhs(lhs);
   op.seRLhs(rhs);
   lhs = op;
   e = op;
   } 
  )*
  ;

/* Less */
level2 returns [MJExpression e]
  : { MJExpression lhs = null; }
  l3 = level3
    {lhs = l3; e = l3;} 
  ('<' rhs = level3 
   { MJLess op = new MJLess();
   op.setLhs(lhs);
   op.seRLhs(rhs);
   lhs = op;
   e = op;
   } 
  )*
  ;

/* Plus and minus */
level3 returns [MJExpression e]
  : { MJExpression lhs = null; }
  l4 = level4 
    {lhs = l4; e = l4;}
    {MJBinaryOp op = null}   
  (('+' 
     {op = new MJPlus();} 
  | '-'
    {op = new MJMinus();}  
     ) rhs = level4 {
   op.setLhs(lhs);
   op.seRLhs(rhs);
   lhs = op;
   e = op;
   }
  )*
  ;

/* Multiplication */
level4 returns [MJExpression e]
  : { MJExpression lhs = null; }
  l5 = level5
  {lhs = l5; e = l5;} 
  ('*' rhs = level5   
  { MJMult op = new MJMult();
   op.setLhs(lhs);
   op.seRLhs(rhs);
   lhs = op;
   e = op;
   } 
  )*
  ;


level5 returns [MJExpression e]
  /* Unary minus */
  : '-' l5 = level5
  {
    e = new MJUnaryMinus(l5);
  }
  /* New integer array */
  | 'new' 'int' '[' ex = expression ']'
  {
    e = new MJNewArray(MJType.TintArray,ex);
  }
  /* New object */
  | 'new' IDENT '(' ')'
  {
    e = MJNew(IDENT);
  }
  /* identifier */
  | i = id
  {
    e = i;
  }
  /* Array access */
  | id '[' expr = expression ']'
  {
    e = new MJArray(id,expr);
  }
  /* Method call */
  | 
  { LinkedList<MJExpression> plist = new LinkedList<MJExpression>(); } 
    i = id '(' (expr = expression {plist.add(expr);} (','expr = expression)* {plist.add(expr);} )? ')'
  {
    e = new MJMethodCallExpr(i,plist);
  }
  /* Parenteses - up evaluation priority */
  | '(' expr = expression ')'
  { e = new MJParanteses(expr); }
  /* Boolean value */
  | 'true'
  {
    e = new MJBoolean(true);
  }
  /* Boolean value */ 
  | 'false' 
  {
    e = new MJBoolean(false);
  } 
  /* Integer value */
  | INT 
  { e = new MJInteger($INT.text); }
  
  /* String value */
  | STRING 
  { e = new MJString($STRING.text); }
  ;
  
  /*
   * To specify identifiers, we need to consider several special cases, 
   * namely that we may use a normal identifier, "this", or a selector such 
   * as "x.y" or "this.y":
   */
id returns [MJIdentifier i]
  : t=thisid
  {
    i = t;
  }
  | t=thisid '.' IDENT  
  {
   
   MJSelector mjs = new MJSelector();
   mjs.setName($IDENT.text);
   mjs.setParent(t);
   i = mjs;
  }
  ;
  
thisid returns [MJIdentifier ti]
  : 'this' 
  {
    ti =  new MJIdentifier("this");
  }
  |
  IDENT
  {
    ti = new MJIdentifier($IDENT.text);
  }
  ; 
  
optExpression returns [MJExpression e]
  : expression {}
  | // intentionally empty
  {e = new MJNoExpression();}
  ;
  
  


  
 //----------------------------------------------//
 //                Types
 // ---------------------------------------------// 
    

  

/*
 * Strings are sequences of characters, enclosed in double quotes. 
 * Characters in a string can be
 *
 *  * "regular" ASCII characters (ASCII code between 32 and 126)
 *     other than quote (") or backslash(\).
 *
 *  * escape sequences to denote a quote (\") or a backslash (\\)
 *
 *  * escape sequences to denote a tab (\t) or a newline (\n)
 *
 */

//TODO change this
QUOTE : '"' ;
fragment SPECIALCHAR : ( ' ' | '!' | QUOTE | '#' | '$' | '%' | '&' | '\'' | '(' | ')' 
                       | '*' | '+' | '´' | '-' | '.' | '/' | ':' | ';' | '<' | '=' 
                       | '>' | '?' | '@' | '[' | '\\'| ']' | '^' | '_' | '`' | '{' 
                       | '|' | '}' | '~' ) ;
fragment CHAR : (NUMBER|LOWER|UPPER|SPECIALCHAR) ;
STRING : QUOTE CHAR* QUOTE; 

// Comments
COMMENT :  '/*' (CHAR)* '*/' | '//' CHAR* NEWLINE;

fragment LOWER : ('a'..'z');
fragment UPPER : ('A'..'Z');
fragment NONNULL : ('1'..'9');
// Numbers are represented as integer literals, 
// which are a sequence of digits. Non-zero numbers have no leading zeroes.
fragment NUMBER : ('0' | NONNULL);
IDENT : ( LOWER | UPPER ) ( LOWER | UPPER | NUMBER | '_' )*;
fragment NEWLINE:'\r'? '\n';
INT : '0' | ( NONNULL NUMBER* );
WHITESPACE  :   ( ' ' | '\t' | NEWLINE )+ { $channel=HIDDEN; };
