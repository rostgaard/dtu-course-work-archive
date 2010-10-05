// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g 2010-09-14 11:53:12

  package mjava;
  
  import java.util.List;
  import java.util.LinkedList;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class MJavaParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "NONNULL", "INTEGER", "LOWER", "UPPER", "IDENT", "NEWLINE", "WHITESPACE", "'if'", "'then'"
    };
    public static final int INTEGER=5;
    public static final int NEWLINE=9;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int WHITESPACE=10;
    public static final int IDENT=8;
    public static final int LOWER=6;
    public static final int EOF=-1;
    public static final int NONNULL=4;
    public static final int UPPER=7;

    // delegates
    // delegators


        public MJavaParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public MJavaParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return MJavaParser.tokenNames; }
    public String getGrammarFileName() { return "/home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g"; }



    // $ANTLR start "prog"
    // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:17:1: prog returns [List<String> keywords] : ks= keywords ;
    public final List<String> prog() throws RecognitionException {
        List<String> keywords = null;

        List<String> ks = null;


        try {
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:18:2: (ks= keywords )
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:18:4: ks= keywords
            {
            pushFollow(FOLLOW_keywords_in_prog39);
            ks=keywords();

            state._fsp--;


            		keywords=ks;
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return keywords;
    }
    // $ANTLR end "prog"


    // $ANTLR start "keywords"
    // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:23:1: keywords returns [List<String> keywords] : k= keyword ;
    public final List<String> keywords() throws RecognitionException {
        List<String> keywords = null;

        String k = null;


        try {
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:23:41: (k= keyword )
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:24:2: k= keyword
            {
            pushFollow(FOLLOW_keyword_in_keywords56);
            k=keyword();

            state._fsp--;


            		keywords=new LinkedList<String>();
            		keywords.add(k);
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return keywords;
    }
    // $ANTLR end "keywords"


    // $ANTLR start "keyword"
    // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:30:1: keyword returns [String keyword] : 'if' 'then' ;
    public final String keyword() throws RecognitionException {
        String keyword = null;

        try {
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:30:33: ( 'if' 'then' )
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:31:2: 'if' 'then'
            {
            match(input,11,FOLLOW_11_in_keyword73); 

            		keyword="1";
            		
            match(input,12,FOLLOW_12_in_keyword78); 
             
            		keyword="2";
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return keyword;
    }
    // $ANTLR end "keyword"

    // Delegated rules


 

    public static final BitSet FOLLOW_keywords_in_prog39 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_keywords56 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_keyword73 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_keyword78 = new BitSet(new long[]{0x0000000000000002L});

}