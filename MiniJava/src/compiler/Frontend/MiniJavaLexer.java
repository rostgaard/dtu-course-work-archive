// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g 2010-09-28 18:47:43

  package compiler.Frontend;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class MiniJavaLexer extends Lexer {
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int NUMBER=9;
    public static final int WHITESPACE=11;
    public static final int INT=5;
    public static final int EOF=-1;
    public static final int NONNULL=8;
    public static final int T__30=30;
    public static final int T__19=19;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__16=16;
    public static final int T__33=33;
    public static final int T__15=15;
    public static final int T__34=34;
    public static final int T__18=18;
    public static final int T__35=35;
    public static final int NEWLINE=10;
    public static final int T__17=17;
    public static final int T__36=36;
    public static final int T__12=12;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int IDENT=4;
    public static final int LOWER=6;
    public static final int UPPER=7;

    // delegates
    // delegators

    public MiniJavaLexer() {;} 
    public MiniJavaLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public MiniJavaLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g"; }

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:7:7: ( 'class' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:7:9: 'class'
            {
            match("class"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:8:7: ( '{' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:8:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:9:7: ( 'public' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:9:9: 'public'
            {
            match("public"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:10:7: ( 'static' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:10:9: 'static'
            {
            match("static"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:11:7: ( 'void' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:11:9: 'void'
            {
            match("void"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:12:7: ( 'main' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:12:9: 'main'
            {
            match("main"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:13:7: ( '(' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:13:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:14:7: ( 'String' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:14:9: 'String'
            {
            match("String"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:15:7: ( '[' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:15:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:16:7: ( ']' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:16:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:17:7: ( ')' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:17:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:18:7: ( 'extends' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:18:9: 'extends'
            {
            match("extends"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:19:7: ( '}' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:19:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:20:7: ( ',' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:20:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:21:7: ( 'return' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:21:9: 'return'
            {
            match("return"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:22:7: ( ';' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:22:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:23:7: ( '.' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:23:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:24:7: ( 'this' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:24:9: 'this'
            {
            match("this"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:25:7: ( 'int' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:25:9: 'int'
            {
            match("int"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:26:7: ( 'boolean' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:26:9: 'boolean'
            {
            match("boolean"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:27:7: ( 'System.out.println' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:27:9: 'System.out.println'
            {
            match("System.out.println"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:28:7: ( '=' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:28:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:29:7: ( 'if' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:29:9: 'if'
            {
            match("if"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:30:7: ( 'else' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:30:9: 'else'
            {
            match("else"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException {
        try {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:31:7: ( 'while' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:31:9: 'while'
            {
            match("while"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "LOWER"
    public final void mLOWER() throws RecognitionException {
        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:157:16: ( ( 'a' .. 'z' ) )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:157:18: ( 'a' .. 'z' )
            {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:157:18: ( 'a' .. 'z' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:157:19: 'a' .. 'z'
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
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:158:16: ( ( 'A' .. 'Z' ) )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:158:18: ( 'A' .. 'Z' )
            {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:158:18: ( 'A' .. 'Z' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:158:19: 'A' .. 'Z'
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
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:159:18: ( ( '1' .. '9' ) )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:159:20: ( '1' .. '9' )
            {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:159:20: ( '1' .. '9' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:159:21: '1' .. '9'
            {
            matchRange('1','9'); 

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "NONNULL"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:160:17: ( ( '0' | NONNULL ) )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:160:19: ( '0' | NONNULL )
            {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:160:19: ( '0' | NONNULL )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='0') ) {
                alt1=1;
            }
            else if ( ((LA1_0>='1' && LA1_0<='9')) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:160:20: '0'
                    {
                    match('0'); 

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:160:26: NONNULL
                    {
                    mNONNULL(); 

                    }
                    break;

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "NUMBER"

    // $ANTLR start "IDENT"
    public final void mIDENT() throws RecognitionException {
        try {
            int _type = IDENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:161:7: ( ( LOWER | UPPER ) ( LOWER | UPPER | NUMBER | '_' )* )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:161:9: ( LOWER | UPPER ) ( LOWER | UPPER | NUMBER | '_' )*
            {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:161:9: ( LOWER | UPPER )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( ((LA2_0>='a' && LA2_0<='z')) ) {
                alt2=1;
            }
            else if ( ((LA2_0>='A' && LA2_0<='Z')) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:161:11: LOWER
                    {
                    mLOWER(); 

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:161:19: UPPER
                    {
                    mUPPER(); 

                    }
                    break;

            }

            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:161:27: ( LOWER | UPPER | NUMBER | '_' )*
            loop3:
            do {
                int alt3=5;
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
                case '_':
                    {
                    alt3=4;
                    }
                    break;

                }

                switch (alt3) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:161:29: LOWER
            	    {
            	    mLOWER(); 

            	    }
            	    break;
            	case 2 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:161:37: UPPER
            	    {
            	    mUPPER(); 

            	    }
            	    break;
            	case 3 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:161:45: NUMBER
            	    {
            	    mNUMBER(); 

            	    }
            	    break;
            	case 4 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:161:54: '_'
            	    {
            	    match('_'); 

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

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:162:17: ( ( '\\r' )? '\\n' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:162:18: ( '\\r' )? '\\n'
            {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:162:18: ( '\\r' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='\r') ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:162:18: '\\r'
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

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:163:5: ( '0' | ( NONNULL ( NUMBER )* ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='0') ) {
                alt6=1;
            }
            else if ( ((LA6_0>='1' && LA6_0<='9')) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:163:7: '0'
                    {
                    match('0'); 

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:163:13: ( NONNULL ( NUMBER )* )
                    {
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:163:13: ( NONNULL ( NUMBER )* )
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:163:15: NONNULL ( NUMBER )*
                    {
                    mNONNULL(); 
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:163:23: ( NUMBER )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( ((LA5_0>='0' && LA5_0<='9')) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:163:23: NUMBER
                    	    {
                    	    mNUMBER(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);


                    }


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "WHITESPACE"
    public final void mWHITESPACE() throws RecognitionException {
        try {
            int _type = WHITESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:164:13: ( ( ' ' | '\\t' | NEWLINE )+ )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:164:17: ( ' ' | '\\t' | NEWLINE )+
            {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:164:17: ( ' ' | '\\t' | NEWLINE )+
            int cnt7=0;
            loop7:
            do {
                int alt7=4;
                switch ( input.LA(1) ) {
                case ' ':
                    {
                    alt7=1;
                    }
                    break;
                case '\t':
                    {
                    alt7=2;
                    }
                    break;
                case '\n':
                case '\r':
                    {
                    alt7=3;
                    }
                    break;

                }

                switch (alt7) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:164:19: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;
            	case 2 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:164:25: '\\t'
            	    {
            	    match('\t'); 

            	    }
            	    break;
            	case 3 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:164:32: NEWLINE
            	    {
            	    mNEWLINE(); 

            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
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
        // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:8: ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | IDENT | INT | WHITESPACE )
        int alt8=28;
        alt8 = dfa8.predict(input);
        switch (alt8) {
            case 1 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:10: T__12
                {
                mT__12(); 

                }
                break;
            case 2 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:16: T__13
                {
                mT__13(); 

                }
                break;
            case 3 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:22: T__14
                {
                mT__14(); 

                }
                break;
            case 4 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:28: T__15
                {
                mT__15(); 

                }
                break;
            case 5 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:34: T__16
                {
                mT__16(); 

                }
                break;
            case 6 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:40: T__17
                {
                mT__17(); 

                }
                break;
            case 7 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:46: T__18
                {
                mT__18(); 

                }
                break;
            case 8 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:52: T__19
                {
                mT__19(); 

                }
                break;
            case 9 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:58: T__20
                {
                mT__20(); 

                }
                break;
            case 10 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:64: T__21
                {
                mT__21(); 

                }
                break;
            case 11 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:70: T__22
                {
                mT__22(); 

                }
                break;
            case 12 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:76: T__23
                {
                mT__23(); 

                }
                break;
            case 13 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:82: T__24
                {
                mT__24(); 

                }
                break;
            case 14 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:88: T__25
                {
                mT__25(); 

                }
                break;
            case 15 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:94: T__26
                {
                mT__26(); 

                }
                break;
            case 16 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:100: T__27
                {
                mT__27(); 

                }
                break;
            case 17 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:106: T__28
                {
                mT__28(); 

                }
                break;
            case 18 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:112: T__29
                {
                mT__29(); 

                }
                break;
            case 19 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:118: T__30
                {
                mT__30(); 

                }
                break;
            case 20 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:124: T__31
                {
                mT__31(); 

                }
                break;
            case 21 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:130: T__32
                {
                mT__32(); 

                }
                break;
            case 22 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:136: T__33
                {
                mT__33(); 

                }
                break;
            case 23 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:142: T__34
                {
                mT__34(); 

                }
                break;
            case 24 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:148: T__35
                {
                mT__35(); 

                }
                break;
            case 25 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:154: T__36
                {
                mT__36(); 

                }
                break;
            case 26 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:160: IDENT
                {
                mIDENT(); 

                }
                break;
            case 27 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:166: INT
                {
                mINT(); 

                }
                break;
            case 28 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:170: WHITESPACE
                {
                mWHITESPACE(); 

                }
                break;

        }

    }


    protected DFA8 dfa8 = new DFA8(this);
    static final String DFA8_eotS =
        "\1\uffff\1\27\1\uffff\4\27\1\uffff\1\27\3\uffff\1\27\2\uffff\1\27"+
        "\2\uffff\3\27\1\uffff\1\27\3\uffff\14\27\1\65\15\27\1\103\1\uffff"+
        "\5\27\1\111\1\112\3\27\1\116\1\27\1\120\1\uffff\2\27\1\123\2\27"+
        "\2\uffff\3\27\1\uffff\1\27\1\uffff\1\27\1\133\1\uffff\1\134\1\135"+
        "\1\136\2\27\1\141\1\27\5\uffff\1\143\1\uffff\1\144\2\uffff";
    static final String DFA8_eofS =
        "\145\uffff";
    static final String DFA8_minS =
        "\1\11\1\154\1\uffff\1\165\1\164\1\157\1\141\1\uffff\1\164\3\uffff"+
        "\1\154\2\uffff\1\145\2\uffff\1\150\1\146\1\157\1\uffff\1\150\3\uffff"+
        "\1\141\1\142\1\141\2\151\1\162\1\163\1\164\1\163\1\164\1\151\1\164"+
        "\1\60\1\157\1\151\1\163\1\154\1\164\1\144\1\156\1\151\1\164\2\145"+
        "\1\165\1\163\1\60\1\uffff\2\154\1\163\2\151\2\60\1\156\1\145\1\156"+
        "\1\60\1\162\1\60\1\uffff\2\145\1\60\2\143\2\uffff\1\147\1\155\1"+
        "\144\1\uffff\1\156\1\uffff\1\141\1\60\1\uffff\3\60\1\56\1\163\1"+
        "\60\1\156\5\uffff\1\60\1\uffff\1\60\2\uffff";
    static final String DFA8_maxS =
        "\1\175\1\154\1\uffff\1\165\1\164\1\157\1\141\1\uffff\1\171\3\uffff"+
        "\1\170\2\uffff\1\145\2\uffff\1\150\1\156\1\157\1\uffff\1\150\3\uffff"+
        "\1\141\1\142\1\141\2\151\1\162\1\163\1\164\1\163\1\164\1\151\1\164"+
        "\1\172\1\157\1\151\1\163\1\154\1\164\1\144\1\156\1\151\1\164\2\145"+
        "\1\165\1\163\1\172\1\uffff\2\154\1\163\2\151\2\172\1\156\1\145\1"+
        "\156\1\172\1\162\1\172\1\uffff\2\145\1\172\2\143\2\uffff\1\147\1"+
        "\155\1\144\1\uffff\1\156\1\uffff\1\141\1\172\1\uffff\3\172\1\56"+
        "\1\163\1\172\1\156\5\uffff\1\172\1\uffff\1\172\2\uffff";
    static final String DFA8_acceptS =
        "\2\uffff\1\2\4\uffff\1\7\1\uffff\1\11\1\12\1\13\1\uffff\1\15\1\16"+
        "\1\uffff\1\20\1\21\3\uffff\1\26\1\uffff\1\32\1\33\1\34\33\uffff"+
        "\1\27\15\uffff\1\23\5\uffff\1\5\1\6\3\uffff\1\30\1\uffff\1\22\2"+
        "\uffff\1\1\7\uffff\1\31\1\3\1\4\1\10\1\25\1\uffff\1\17\1\uffff\1"+
        "\14\1\24";
    static final String DFA8_specialS =
        "\145\uffff}>";
    static final String[] DFA8_transitionS = {
            "\2\31\2\uffff\1\31\22\uffff\1\31\7\uffff\1\7\1\13\2\uffff\1"+
            "\16\1\uffff\1\21\1\uffff\12\30\1\uffff\1\20\1\uffff\1\25\3\uffff"+
            "\22\27\1\10\7\27\1\11\1\uffff\1\12\3\uffff\1\27\1\24\1\1\1\27"+
            "\1\14\3\27\1\23\3\27\1\6\2\27\1\3\1\27\1\17\1\4\1\22\1\27\1"+
            "\5\1\26\3\27\1\2\1\uffff\1\15",
            "\1\32",
            "",
            "\1\33",
            "\1\34",
            "\1\35",
            "\1\36",
            "",
            "\1\37\4\uffff\1\40",
            "",
            "",
            "",
            "\1\42\13\uffff\1\41",
            "",
            "",
            "\1\43",
            "",
            "",
            "\1\44",
            "\1\46\7\uffff\1\45",
            "\1\47",
            "",
            "\1\50",
            "",
            "",
            "",
            "\1\51",
            "\1\52",
            "\1\53",
            "\1\54",
            "\1\55",
            "\1\56",
            "\1\57",
            "\1\60",
            "\1\61",
            "\1\62",
            "\1\63",
            "\1\64",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\66",
            "\1\67",
            "\1\70",
            "\1\71",
            "\1\72",
            "\1\73",
            "\1\74",
            "\1\75",
            "\1\76",
            "\1\77",
            "\1\100",
            "\1\101",
            "\1\102",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            "\1\104",
            "\1\105",
            "\1\106",
            "\1\107",
            "\1\110",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\113",
            "\1\114",
            "\1\115",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\117",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            "\1\121",
            "\1\122",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\124",
            "\1\125",
            "",
            "",
            "\1\126",
            "\1\127",
            "\1\130",
            "",
            "\1\131",
            "",
            "\1\132",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\137",
            "\1\140",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\142",
            "",
            "",
            "",
            "",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            ""
    };

    static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
    static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
    static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
    static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
    static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
    static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
    static final short[][] DFA8_transition;

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | IDENT | INT | WHITESPACE );";
        }
    }
 

}