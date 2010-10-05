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

program returns [MJProgram p]:
  { LinkedList<MJClass> cdl = new LinkedList<MJClass>(); }
  mc = mainClass { cdl.add(mc); p = new MJProgram(cdl); }
  (cd = classDeclaration {cdl.add(cd);})*
  ;
  
  // program : mainClass ( classDeclaration)*

mainClass returns [MJClass c]
  : 'class' cname = IDENT '{' 
    'public' 'static' 'void' 'main' '(' 'String' '[' ']' parname = IDENT ')' b = block
  {
    MJType partype = new MJType(new MJType("String"));
    MJVariable par = new MJVariable( partype, $parname.text);
     
    MJMethod md = new MJMethod(MJType.Tnone, "main", par, b, true, true);
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
  
methodDeclaration returns [MJMethod m]
  : ('public')? ('static')? procType IDENT '(' (type IDENT(',' type IDENT)*)? ')' '{'
                  (varDeclaration)* (statement)*  'return' optExpression ';' '}' 
  
  {
  };
  
varDeclaration returns [MJVariable vd]
  : t = type n = IDENT ';'
  {
    vd = new MJVariable(t,n.getText());
  }
  ;
  
optExpression returns [MJExpression e]
  : expression {}
  | // empty
  {}
  ;
  
id returns [MJIdentifier i]
  : t=thisid
  {
    i = t;
  }
  | t=thisid '.' IDENT 
  {
   i = new MJSelector();
   i.setName($IDENT.text);
   i.setParent(t);
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


procType returns [MJType t]
  : 'void' 
  { t = MJType.Tvoid; }
  | type 
  ;

type returns [MJType t] 
  : 'int' 
  { t = MJType.Tint; }
  | 'boolean'
  { t = MJType.Tboolean; }
  | 'int' '[' ']' 
  {t = MJType.TintArray}
  | IDENT 
  {t = IDENT}
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
    s = b
  }
  | 'if' '(' expression ')' block ('else' block)? // If-then-else statement
  {
    //TODO
  }
  | 'while' '(' expression ')' statement // Assignment
  {
    //TODO
  }
  | IDENT '(' (expression ( ',' expression)*)? ')' ';' // Method call
  {
    //TODO
  }
  
  ;
  
expression returns [MJExpression e]
  : level1 ('&&' level1)*
  {}
  ;

level1 returns [MJExpression e]
  : level2 ('==' level2)*
  {}
  ;  

level2 returns [MJExpression e]
  : level3 ('<' level3)*
  {}
  ;

level3 returns [MJExpression e]
  : level4 (('+' | '-') level4)*
  {}
  ;


level4 returns [MJExpression e]
  : level5 ('*' level5)*
  {}
  ;

level5 returns [MJExpression e]
  : '-' level5
  {//TODO
  }
  | 'new' 'int' '[' ex = expression ']'
  {//TODO
  }
  | 'new' IDENT '(' ')'
  {//TODO
  }
  | id
  {//TODO
  }
  | id '[' expression ']'
  {}
  | id '(' (expression (',' expression)*)? ')'
  {}
  | '(' e1 = expression ')'
  { e = e1; }
  | INT 
  { e = new MJInteger($INT.text); }
  | STRING 
  { e = new MJString($STRING.text }
  ;

STRING : '"' IDENT '"'; 

fragment LOWER : ('a'..'z');
fragment UPPER : ('A'..'Z');
fragment NONNULL : ('1'..'9');
fragment NUMBER : ('0' | NONNULL);
IDENT : ( LOWER | UPPER ) ( LOWER | UPPER | NUMBER | '_' )*;
fragment NEWLINE:'\r'? '\n';
INT : '0' | ( NONNULL NUMBER* );
WHITESPACE  :   ( ' ' | '\t' | NEWLINE )+ { $channel=HIDDEN; };
