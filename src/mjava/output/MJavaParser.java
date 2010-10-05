// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g 2010-09-28 20:00:01

  package mjava;
  
  import java.util.List;
  import java.util.LinkedList;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.debug.*;
import java.io.IOException;
public class MJavaParser extends DebugParser {
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

    public static final String[] ruleNames = new String[] {
        "invalidRule", "keyword", "prog", "keywords"
    };
     
        public int ruleLevel = 0;
        public int getRuleLevel() { return ruleLevel; }
        public void incRuleLevel() { ruleLevel++; }
        public void decRuleLevel() { ruleLevel--; }
        public MJavaParser(TokenStream input) {
            this(input, DebugEventSocketProxy.DEFAULT_DEBUGGER_PORT, new RecognizerSharedState());
        }
        public MJavaParser(TokenStream input, int port, RecognizerSharedState state) {
            super(input, state);
            DebugEventSocketProxy proxy =
                new DebugEventSocketProxy(this, port, null);
            setDebugListener(proxy);
            try {
                proxy.handshake();
            }
            catch (IOException ioe) {
                reportError(ioe);
            }
        }
    public MJavaParser(TokenStream input, DebugEventListener dbg) {
        super(input, dbg, new RecognizerSharedState());

    }
    protected boolean evalPredicate(boolean result, String predicate) {
        dbg.semanticPredicate(result, predicate);
        return result;
    }


    public String[] getTokenNames() { return MJavaParser.tokenNames; }
    public String getGrammarFileName() { return "/home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g"; }



    // $ANTLR start "prog"
    // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:17:1: prog returns [List<String> keywords] : ks= keywords ;
    public final List<String> prog() throws RecognitionException {
        List<String> keywords = null;

        List<String> ks = null;


        try { dbg.enterRule(getGrammarFileName(), "prog");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(17, 1);

        try {
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:18:2: (ks= keywords )
            dbg.enterAlt(1);

            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:18:4: ks= keywords
            {
            dbg.location(18,6);
            pushFollow(FOLLOW_keywords_in_prog39);
            ks=keywords();

            state._fsp--;

            dbg.location(18,15);

            		keywords=ks;
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(21, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "prog");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return keywords;
    }
    // $ANTLR end "prog"


    // $ANTLR start "keywords"
    // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:23:1: keywords returns [List<String> keywords] : k= keyword ;
    public final List<String> keywords() throws RecognitionException {
        List<String> keywords = null;

        String k = null;


        try { dbg.enterRule(getGrammarFileName(), "keywords");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(23, 1);

        try {
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:23:41: (k= keyword )
            dbg.enterAlt(1);

            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:24:2: k= keyword
            {
            dbg.location(24,3);
            pushFollow(FOLLOW_keyword_in_keywords56);
            k=keyword();

            state._fsp--;

            dbg.location(24,12);

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
        dbg.location(28, 1);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "keywords");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return keywords;
    }
    // $ANTLR end "keywords"


    // $ANTLR start "keyword"
    // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:30:1: keyword returns [String keyword] : 'if' 'then' ;
    public final String keyword() throws RecognitionException {
        String keyword = null;

        try { dbg.enterRule(getGrammarFileName(), "keyword");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(30, 1);

        try {
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:30:33: ( 'if' 'then' )
            dbg.enterAlt(1);

            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:31:2: 'if' 'then'
            {
            dbg.location(31,2);
            match(input,11,FOLLOW_11_in_keyword73); 
            dbg.location(31,7);

            		keyword="1";
            		
            dbg.location(34,2);
            match(input,12,FOLLOW_12_in_keyword78); 
            dbg.location(34,9);
             
            		keyword="2";
            	

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(37, 2);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "keyword");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
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