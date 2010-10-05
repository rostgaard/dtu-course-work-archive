// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g 2010-09-28 20:00:02

  package mjava;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class MJavaLexer extends Lexer {
    public static final int INTEGER=5;
    public static final int NEWLINE=9;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int IDENT=8;
    public static final int WHITESPACE=10;
    public static final int LOWER=6;
    public static final int EOF=-1;
    public static final int NONNULL=4;
    public static final int UPPER=7;

    // delegates
    // delegators

    public MJavaLexer() {;} 
    public MJavaLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public MJavaLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g"; }

    // $ANTLR start "T__11"
    public final void mT__11() throws RecognitionException {
        try {
            int _type = T__11;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:7:7: ( 'if' )
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:7:9: 'if'
            {
            match("if"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__11"

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:8:7: ( 'then' )
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:8:9: 'then'
            {
            match("then"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "INTEGER"
    public final void mINTEGER() throws RecognitionException {
        try {
            int _type = INTEGER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:39:9: ( ( '0' | NONNULL ( '0' .. '9' )* ) )
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:39:11: ( '0' | NONNULL ( '0' .. '9' )* )
            {
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:39:11: ( '0' | NONNULL ( '0' .. '9' )* )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='0') ) {
                alt2=1;
            }
            else if ( ((LA2_0>='1' && LA2_0<='9')) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:39:12: '0'
                    {
                    match('0'); 

                    }
                    break;
                case 2 :
                    // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:39:18: NONNULL ( '0' .. '9' )*
                    {
                    mNONNULL(); 
                    // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:39:26: ( '0' .. '9' )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( ((LA1_0>='0' && LA1_0<='9')) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:39:27: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INTEGER"

    // $ANTLR start "IDENT"
    public final void mIDENT() throws RecognitionException {
        try {
            int _type = IDENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:40:7: ( ( LOWER ) ( LOWER | UPPER | INTEGER )* )
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:40:9: ( LOWER ) ( LOWER | UPPER | INTEGER )*
            {
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:40:9: ( LOWER )
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:40:11: LOWER
            {
            mLOWER(); 

            }

            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:40:19: ( LOWER | UPPER | INTEGER )*
            loop3:
            do {
                int alt3=4;
                switch ( input.LA(1) ) {
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    {
                    alt3=1;
                    }
                    break;
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                    {
                    alt3=2;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    {
                    alt3=3;
                    }
                    break;

                }

                switch (alt3) {
            	case 1 :
            	    // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:40:21: LOWER
            	    {
            	    mLOWER(); 

            	    }
            	    break;
            	case 2 :
            	    // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:40:29: UPPER
            	    {
            	    mUPPER(); 

            	    }
            	    break;
            	case 3 :
            	    // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:40:37: INTEGER
            	    {
            	    mINTEGER(); 

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IDENT"

    // $ANTLR start "LOWER"
    public final void mLOWER() throws RecognitionException {
        try {
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:42:16: ( ( 'a' .. 'z' ) )
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:42:18: ( 'a' .. 'z' )
            {
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:42:18: ( 'a' .. 'z' )
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:42:19: 'a' .. 'z'
            {
            matchRange('a','z'); 

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "LOWER"

    // $ANTLR start "UPPER"
    public final void mUPPER() throws RecognitionException {
        try {
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:43:16: ( ( 'A' .. 'Z' ) )
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:43:18: ( 'A' .. 'Z' )
            {
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:43:18: ( 'A' .. 'Z' )
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:43:19: 'A' .. 'Z'
            {
            matchRange('A','Z'); 

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "UPPER"

    // $ANTLR start "NONNULL"
    public final void mNONNULL() throws RecognitionException {
        try {
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:44:18: ( ( '1' .. '9' ) )
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:44:20: ( '1' .. '9' )
            {
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:44:20: ( '1' .. '9' )
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:44:21: '1' .. '9'
            {
            matchRange('1','9'); 

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "NONNULL"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:45:17: ( ( '\\r' )? '\\n' )
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:45:18: ( '\\r' )? '\\n'
            {
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:45:18: ( '\\r' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='\r') ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:45:18: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "WHITESPACE"
    public final void mWHITESPACE() throws RecognitionException {
        try {
            int _type = WHITESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:47:13: ( ( ' ' | '\\t' | NEWLINE )+ )
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:47:17: ( ' ' | '\\t' | NEWLINE )+
            {
            // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:47:17: ( ' ' | '\\t' | NEWLINE )+
            int cnt5=0;
            loop5:
            do {
                int alt5=4;
                switch ( input.LA(1) ) {
                case ' ':
                    {
                    alt5=1;
                    }
                    break;
                case '\t':
                    {
                    alt5=2;
                    }
                    break;
                case '\n':
                case '\r':
                    {
                    alt5=3;
                    }
                    break;

                }

                switch (alt5) {
            	case 1 :
            	    // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:47:19: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;
            	case 2 :
            	    // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:47:25: '\\t'
            	    {
            	    match('\t'); 

            	    }
            	    break;
            	case 3 :
            	    // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:47:32: NEWLINE
            	    {
            	    mNEWLINE(); 

            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);

             _channel=HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHITESPACE"

    public void mTokens() throws RecognitionException {
        // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:1:8: ( T__11 | T__12 | INTEGER | IDENT | WHITESPACE )
        int alt6=5;
        alt6 = dfa6.predict(input);
        switch (alt6) {
            case 1 :
                // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:1:10: T__11
                {
                mT__11(); 

                }
                break;
            case 2 :
                // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:1:16: T__12
                {
                mT__12(); 

                }
                break;
            case 3 :
                // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:1:22: INTEGER
                {
                mINTEGER(); 

                }
                break;
            case 4 :
                // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:1:30: IDENT
                {
                mIDENT(); 

                }
                break;
            case 5 :
                // /home/krc/uddannelse/DTU/02332 Compilerteknik E10/work/02332_A1/src/mjava/MJava.g:1:36: WHITESPACE
                {
                mWHITESPACE(); 

                }
                break;

        }

    }


    protected DFA6 dfa6 = new DFA6(this);
    static final String DFA6_eotS =
        "\1\uffff\2\4\3\uffff\1\10\1\4\1\uffff\1\4\1\13\1\uffff";
    static final String DFA6_eofS =
        "\14\uffff";
    static final String DFA6_minS =
        "\1\11\1\146\1\150\3\uffff\1\60\1\145\1\uffff\1\156\1\60\1\uffff";
    static final String DFA6_maxS =
        "\1\172\1\146\1\150\3\uffff\1\172\1\145\1\uffff\1\156\1\172\1\uffff";
    static final String DFA6_acceptS =
        "\3\uffff\1\3\1\4\1\5\2\uffff\1\1\2\uffff\1\2";
    static final String DFA6_specialS =
        "\14\uffff}>";
    static final String[] DFA6_transitionS = {
            "\2\5\2\uffff\1\5\22\uffff\1\5\17\uffff\12\3\47\uffff\10\4\1"+
            "\1\12\4\1\2\6\4",
            "\1\6",
            "\1\7",
            "",
            "",
            "",
            "\12\4\7\uffff\32\4\6\uffff\32\4",
            "\1\11",
            "",
            "\1\12",
            "\12\4\7\uffff\32\4\6\uffff\32\4",
            ""
    };

    static final short[] DFA6_eot = DFA.unpackEncodedString(DFA6_eotS);
    static final short[] DFA6_eof = DFA.unpackEncodedString(DFA6_eofS);
    static final char[] DFA6_min = DFA.unpackEncodedStringToUnsignedChars(DFA6_minS);
    static final char[] DFA6_max = DFA.unpackEncodedStringToUnsignedChars(DFA6_maxS);
    static final short[] DFA6_accept = DFA.unpackEncodedString(DFA6_acceptS);
    static final short[] DFA6_special = DFA.unpackEncodedString(DFA6_specialS);
    static final short[][] DFA6_transition;

    static {
        int numStates = DFA6_transitionS.length;
        DFA6_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA6_transition[i] = DFA.unpackEncodedString(DFA6_transitionS[i]);
        }
    }

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = DFA6_eot;
            this.eof = DFA6_eof;
            this.min = DFA6_min;
            this.max = DFA6_max;
            this.accept = DFA6_accept;
            this.special = DFA6_special;
            this.transition = DFA6_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__11 | T__12 | INTEGER | IDENT | WHITESPACE );";
        }
    }
 

}