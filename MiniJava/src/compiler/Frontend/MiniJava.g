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
  (cd = classDeclaration {cld.add(cd)})*
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
    LinkedList<MJVariable> mdl = new LinkedList<MJMethod>();
  } 
  'class' cname = IDENT ('extends' sc = IDENT)? '{' (v = varDeclaration {vdl.add(v);} )*   (m = methodDeclaration {mdl.add(m)})* '}' {
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
  
id returns []
  : thisid
  {//TODO
  }
  | thisid '.'IDENT
  {//TODO
  }
  ;
  
thisid returns [MJIdentifier]
  : 'this' 
  {//TODO
  }
  |
  IDENT
  {//TODO
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
  : i = IDENT
  { e = new MJIdentifier($i.text); }
  | INT 
  { e = new MJInteger($INT.text); }
  ;

fragment LOWER : ('a'..'z');
fragment UPPER : ('A'..'Z');
fragment NONNULL : ('1'..'9');
fragment NUMBER : ('0' | NONNULL);
IDENT : ( LOWER | UPPER ) ( LOWER | UPPER | NUMBER | '_' )*;
fragment NEWLINE:'\r'? '\n';
INT : '0' | ( NONNULL NUMBER* );
WHITESPACE  :   ( ' ' | '\t' | NEWLINE )+ { $channel=HIDDEN; };
