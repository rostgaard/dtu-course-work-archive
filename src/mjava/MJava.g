
grammar MJava;


@lexer::header {
  package mjava;
}  

@parser::header {
  package mjava;
  
  import java.util.List;
  import java.util.LinkedList;

}

prog returns [List<String> keywords]
	: ks=keywords{
		keywords=ks;
	}
;
	
keywords returns [List<String> keywords]:
	k=keyword {
		keywords=new LinkedList<String>();
		keywords.add(k);
	} 
;
	
keyword returns [String keyword]:
	'if' {
		keyword="1";
		}
	'then' { 
		keyword="2";
	}
	;
	
INTEGER : ('0' | NONNULL ('0'..'9')*);
IDENT : ( LOWER ) ( LOWER | UPPER | INTEGER )*;

fragment LOWER : ('a'..'z');
fragment UPPER : ('A'..'Z');
fragment NONNULL : ('1'..'9');
fragment NEWLINE:'\r'? '\n';

WHITESPACE  :   ( ' ' | '\t' | NEWLINE )+ { $channel=HIDDEN; };
