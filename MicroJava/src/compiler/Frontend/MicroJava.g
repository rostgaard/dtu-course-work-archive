grammar MicroJava;

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
  mc = mainClass 
  { cdl.add(mc); 

    p = new MJProgram(cdl);
  } 
  ;

mainClass returns [MJClass c]
  : 'class' cname = IDENT '{' 
    'public' 'static' 'void' 'main' '(' 'String[]' parname = IDENT ')' b = body
  {
    MJType partype = new MJType(new MJType("String"));
    MJVariable par = new MJVariable( partype, $parname.text);
     
    MJMethod md = new MJMethod(MJType.Tnone, "main", par, b, true, true);
    c = new MJClass($cname.text, md);
  }
  ;
  
varDeclaration returns [MJVariable vd]
  : t = type n = IDENT ';'
  {
    vd = new MJVariable(t,$n.text);
  }
  ;

type returns [MJType t] 
  : 'int' 
  { t = MJType.Tint; }
  ;
  
body returns [MJBlock b] 
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
  : 'System.out.println' '(' ep = expression ')' ';'
  {
    s = new MJPrintln(ep);
  }
  | va = IDENT '=' ea = expression ';'
  {
    s = new MJAssign(new MJIdentifier($va.text), ea);
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
