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
  : ('public')? ('static')? procType IDENT '(' (type IDENT(',' type IDENT)*)? ')' '{'
                  (varDeclaration)* (statement)*  'return' optExpression ';' '}' 
  
  {
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
    //TODO - figure out what happens when no else block i present
    s = new MJIfElse(cond,ifblock,elseblock);
  }
  | 'while' '(' expr = expression ')' stmt = statement // Assignment
  {
    s = new MJWhile(expr,stmt);
  }
  | IDENT '(' (expression ( ',' expression)*)? ')' ';' // Method call
  {
    //TODO
  }
  ;  
  
 //----------------------------------------------//
 //                Expressions
 // ---------------------------------------------// 
  
/* AND */
expression returns [MJExpression e]
  : level1 ('&&' level1)*
  {e = new MJAnd();}
  ;

/* Equals */
level1 returns [MJExpression e]
  : level2 ('==' level2)*
  {}
  ;  

/* Less */
level2 returns [MJExpression e]
  : level3 ('<' level3)*
  {}
  ;

/* Plus and minus */
level3 returns [MJExpression e]
  : level4 (('+' | '-') level4)*
  {}
  ;

/* Multiplication */
level4 returns [MJExpression e]
  : level5 ('*' level5)*
  {}
  ;


level5 returns [MJExpression e]
  /* Unary minus */
  : '-' level5
  {//TODO
  }
  /* New integer array */
  | 'new' 'int' '[' ex = expression ']'
  {
    // TODO - figure out this one, should it be MJType or MJIdent ?
    //e = new MJArray(MJType.TintArray,ex);
  }
  /* New object */
  | 'new' IDENT '(' ')'
  {//TODO - new mjobject ?
  }
  /* identifier */
  | i = id
  {
   //TODO - What is the effect of this ?
    e = i;
  }
  /* Array access */
  | id '[' expression ']'
  {
    //TODO
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
  { e = expr; }
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
  | // empty
  {}
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
STRING : '"' IDENT '"'; 

// Comments
COMMENT :  '/*' .* '*/' | '//' .* NEWLINE;

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
