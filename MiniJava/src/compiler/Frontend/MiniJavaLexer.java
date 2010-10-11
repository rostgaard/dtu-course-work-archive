// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g 2010-10-11 13:40:56

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
    public static final int EOF=-1;
    public static final int T__19=19;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int T__14=14;
    public static final int IDENT=4;
    public static final int COMMENT=8;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int NUMBER=12;
    public static final int WHITESPACE=13;
    public static final int INT=5;
    public static final int NONNULL=11;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int NEWLINE=7;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int LOWER=9;
    public static final int UPPER=10;
    public static final int STRING=6;

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

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
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
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
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
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
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
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
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
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
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
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
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
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
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
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
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
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
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
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
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
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
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
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
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
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
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
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:20:7: ( ';' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:20:9: ';'
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
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:21:7: ( 'int' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:21:9: 'int'
            {
            match("int"); 


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
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:22:7: ( 'boolean' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:22:9: 'boolean'
            {
            match("boolean"); 


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
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:23:7: ( ',' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:23:9: ','
            {
            match(','); 

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
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:24:7: ( 'return' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:24:9: 'return'
            {
            match("return"); 


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
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:25:7: ( 'System.out.println' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:25:9: 'System.out.println'
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
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:26:7: ( '=' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:26:9: '='
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
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:27:7: ( 'if' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:27:9: 'if'
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
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:28:7: ( 'else' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:28:9: 'else'
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
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:29:7: ( 'while' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:29:9: 'while'
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

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:30:7: ( '&&' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:30:9: '&&'
            {
            match("&&"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:31:7: ( '==' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:31:9: '=='
            {
            match("=="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "T__39"
    public final void mT__39() throws RecognitionException {
        try {
            int _type = T__39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:32:7: ( '<' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:32:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__39"

    // $ANTLR start "T__40"
    public final void mT__40() throws RecognitionException {
        try {
            int _type = T__40;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:33:7: ( '+' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:33:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__40"

    // $ANTLR start "T__41"
    public final void mT__41() throws RecognitionException {
        try {
            int _type = T__41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:34:7: ( '-' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:34:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__41"

    // $ANTLR start "T__42"
    public final void mT__42() throws RecognitionException {
        try {
            int _type = T__42;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:35:7: ( '*' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:35:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__42"

    // $ANTLR start "T__43"
    public final void mT__43() throws RecognitionException {
        try {
            int _type = T__43;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:36:7: ( 'new' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:36:9: 'new'
            {
            match("new"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__43"

    // $ANTLR start "T__44"
    public final void mT__44() throws RecognitionException {
        try {
            int _type = T__44;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:37:7: ( 'true' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:37:9: 'true'
            {
            match("true"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__44"

    // $ANTLR start "T__45"
    public final void mT__45() throws RecognitionException {
        try {
            int _type = T__45;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:38:7: ( 'false' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:38:9: 'false'
            {
            match("false"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__45"

    // $ANTLR start "T__46"
    public final void mT__46() throws RecognitionException {
        try {
            int _type = T__46;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:39:7: ( '.' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:39:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__46"

    // $ANTLR start "T__47"
    public final void mT__47() throws RecognitionException {
        try {
            int _type = T__47;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:40:7: ( 'this' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:40:9: 'this'
            {
            match("this"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__47"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:298:8: ( '\"' IDENT '\"' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:298:10: '\"' IDENT '\"'
            {
            match('\"'); 
            mIDENT(); 
            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:301:9: ( '/*' ( . )* '*/' | '//' ( . )* NEWLINE )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='/') ) {
                int LA3_1 = input.LA(2);

                if ( (LA3_1=='*') ) {
                    alt3=1;
                }
                else if ( (LA3_1=='/') ) {
                    alt3=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:301:12: '/*' ( . )* '*/'
                    {
                    match("/*"); 

                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:301:17: ( . )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0=='*') ) {
                            int LA1_1 = input.LA(2);

                            if ( (LA1_1=='/') ) {
                                alt1=2;
                            }
                            else if ( ((LA1_1>='\u0000' && LA1_1<='.')||(LA1_1>='0' && LA1_1<='\uFFFF')) ) {
                                alt1=1;
                            }


                        }
                        else if ( ((LA1_0>='\u0000' && LA1_0<=')')||(LA1_0>='+' && LA1_0<='\uFFFF')) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:301:17: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);

                    match("*/"); 


                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:301:27: '//' ( . )* NEWLINE
                    {
                    match("//"); 

                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:301:32: ( . )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0=='\r') ) {
                            alt2=2;
                        }
                        else if ( (LA2_0=='\n') ) {
                            alt2=2;
                        }
                        else if ( ((LA2_0>='\u0000' && LA2_0<='\t')||(LA2_0>='\u000B' && LA2_0<='\f')||(LA2_0>='\u000E' && LA2_0<='\uFFFF')) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:301:32: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);

                    mNEWLINE(); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "LOWER"
    public final void mLOWER() throws RecognitionException {
        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:303:16: ( ( 'a' .. 'z' ) )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:303:18: ( 'a' .. 'z' )
            {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:303:18: ( 'a' .. 'z' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:303:19: 'a' .. 'z'
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
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:304:16: ( ( 'A' .. 'Z' ) )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:304:18: ( 'A' .. 'Z' )
            {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:304:18: ( 'A' .. 'Z' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:304:19: 'A' .. 'Z'
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
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:305:18: ( ( '1' .. '9' ) )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:305:20: ( '1' .. '9' )
            {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:305:20: ( '1' .. '9' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:305:21: '1' .. '9'
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
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:308:17: ( ( '0' | NONNULL ) )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:308:19: ( '0' | NONNULL )
            {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:308:19: ( '0' | NONNULL )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='0') ) {
                alt4=1;
            }
            else if ( ((LA4_0>='1' && LA4_0<='9')) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:308:20: '0'
                    {
                    match('0'); 

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:308:26: NONNULL
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
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:309:7: ( ( LOWER | UPPER ) ( LOWER | UPPER | NUMBER | '_' )* )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:309:9: ( LOWER | UPPER ) ( LOWER | UPPER | NUMBER | '_' )*
            {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:309:9: ( LOWER | UPPER )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( ((LA5_0>='a' && LA5_0<='z')) ) {
                alt5=1;
            }
            else if ( ((LA5_0>='A' && LA5_0<='Z')) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:309:11: LOWER
                    {
                    mLOWER(); 

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:309:19: UPPER
                    {
                    mUPPER(); 

                    }
                    break;

            }

            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:309:27: ( LOWER | UPPER | NUMBER | '_' )*
            loop6:
            do {
                int alt6=5;
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
                    alt6=1;
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
                    alt6=2;
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
                    alt6=3;
                    }
                    break;
                case '_':
                    {
                    alt6=4;
                    }
                    break;

                }

                switch (alt6) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:309:29: LOWER
            	    {
            	    mLOWER(); 

            	    }
            	    break;
            	case 2 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:309:37: UPPER
            	    {
            	    mUPPER(); 

            	    }
            	    break;
            	case 3 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:309:45: NUMBER
            	    {
            	    mNUMBER(); 

            	    }
            	    break;
            	case 4 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:309:54: '_'
            	    {
            	    match('_'); 

            	    }
            	    break;

            	default :
            	    break loop6;
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
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:310:17: ( ( '\\r' )? '\\n' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:310:18: ( '\\r' )? '\\n'
            {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:310:18: ( '\\r' )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='\r') ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:310:18: '\\r'
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
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:311:5: ( '0' | ( NONNULL ( NUMBER )* ) )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0=='0') ) {
                alt9=1;
            }
            else if ( ((LA9_0>='1' && LA9_0<='9')) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:311:7: '0'
                    {
                    match('0'); 

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:311:13: ( NONNULL ( NUMBER )* )
                    {
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:311:13: ( NONNULL ( NUMBER )* )
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:311:15: NONNULL ( NUMBER )*
                    {
                    mNONNULL(); 
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:311:23: ( NUMBER )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( ((LA8_0>='0' && LA8_0<='9')) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:311:23: NUMBER
                    	    {
                    	    mNUMBER(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop8;
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
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:312:13: ( ( ' ' | '\\t' | NEWLINE )+ )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:312:17: ( ' ' | '\\t' | NEWLINE )+
            {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:312:17: ( ' ' | '\\t' | NEWLINE )+
            int cnt10=0;
            loop10:
            do {
                int alt10=4;
                switch ( input.LA(1) ) {
                case ' ':
                    {
                    alt10=1;
                    }
                    break;
                case '\t':
                    {
                    alt10=2;
                    }
                    break;
                case '\n':
                case '\r':
                    {
                    alt10=3;
                    }
                    break;

                }

                switch (alt10) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:312:19: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;
            	case 2 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:312:25: '\\t'
            	    {
            	    match('\t'); 

            	    }
            	    break;
            	case 3 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:312:32: NEWLINE
            	    {
            	    mNEWLINE(); 

            	    }
            	    break;

            	default :
            	    if ( cnt10 >= 1 ) break loop10;
                        EarlyExitException eee =
                            new EarlyExitException(10, input);
                        throw eee;
                }
                cnt10++;
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
        // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:8: ( T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | STRING | COMMENT | IDENT | INT | WHITESPACE )
        int alt11=39;
        alt11 = dfa11.predict(input);
        switch (alt11) {
            case 1 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:10: T__14
                {
                mT__14(); 

                }
                break;
            case 2 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:16: T__15
                {
                mT__15(); 

                }
                break;
            case 3 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:22: T__16
                {
                mT__16(); 

                }
                break;
            case 4 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:28: T__17
                {
                mT__17(); 

                }
                break;
            case 5 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:34: T__18
                {
                mT__18(); 

                }
                break;
            case 6 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:40: T__19
                {
                mT__19(); 

                }
                break;
            case 7 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:46: T__20
                {
                mT__20(); 

                }
                break;
            case 8 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:52: T__21
                {
                mT__21(); 

                }
                break;
            case 9 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:58: T__22
                {
                mT__22(); 

                }
                break;
            case 10 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:64: T__23
                {
                mT__23(); 

                }
                break;
            case 11 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:70: T__24
                {
                mT__24(); 

                }
                break;
            case 12 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:76: T__25
                {
                mT__25(); 

                }
                break;
            case 13 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:82: T__26
                {
                mT__26(); 

                }
                break;
            case 14 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:88: T__27
                {
                mT__27(); 

                }
                break;
            case 15 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:94: T__28
                {
                mT__28(); 

                }
                break;
            case 16 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:100: T__29
                {
                mT__29(); 

                }
                break;
            case 17 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:106: T__30
                {
                mT__30(); 

                }
                break;
            case 18 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:112: T__31
                {
                mT__31(); 

                }
                break;
            case 19 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:118: T__32
                {
                mT__32(); 

                }
                break;
            case 20 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:124: T__33
                {
                mT__33(); 

                }
                break;
            case 21 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:130: T__34
                {
                mT__34(); 

                }
                break;
            case 22 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:136: T__35
                {
                mT__35(); 

                }
                break;
            case 23 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:142: T__36
                {
                mT__36(); 

                }
                break;
            case 24 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:148: T__37
                {
                mT__37(); 

                }
                break;
            case 25 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:154: T__38
                {
                mT__38(); 

                }
                break;
            case 26 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:160: T__39
                {
                mT__39(); 

                }
                break;
            case 27 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:166: T__40
                {
                mT__40(); 

                }
                break;
            case 28 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:172: T__41
                {
                mT__41(); 

                }
                break;
            case 29 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:178: T__42
                {
                mT__42(); 

                }
                break;
            case 30 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:184: T__43
                {
                mT__43(); 

                }
                break;
            case 31 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:190: T__44
                {
                mT__44(); 

                }
                break;
            case 32 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:196: T__45
                {
                mT__45(); 

                }
                break;
            case 33 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:202: T__46
                {
                mT__46(); 

                }
                break;
            case 34 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:208: T__47
                {
                mT__47(); 

                }
                break;
            case 35 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:214: STRING
                {
                mSTRING(); 

                }
                break;
            case 36 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:221: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 37 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:229: IDENT
                {
                mIDENT(); 

                }
                break;
            case 38 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:235: INT
                {
                mINT(); 

                }
                break;
            case 39 :
                // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:1:239: WHITESPACE
                {
                mWHITESPACE(); 

                }
                break;

        }

    }


    protected DFA11 dfa11 = new DFA11(this);
    static final String DFA11_eotS =
        "\1\uffff\1\40\1\uffff\4\40\1\uffff\1\40\3\uffff\1\40\2\uffff\2\40"+
        "\1\uffff\1\40\1\61\1\40\5\uffff\3\40\6\uffff\12\40\1\101\2\40\2"+
        "\uffff\16\40\1\122\1\uffff\3\40\1\126\6\40\1\135\1\136\3\40\1\142"+
        "\1\uffff\3\40\1\uffff\1\146\1\147\1\40\1\151\2\40\2\uffff\3\40\1"+
        "\uffff\2\40\1\161\2\uffff\1\162\1\uffff\1\163\1\164\1\165\3\40\1"+
        "\171\6\uffff\1\172\1\173\3\uffff";
    static final String DFA11_eofS =
        "\174\uffff";
    static final String DFA11_minS =
        "\1\11\1\154\1\uffff\1\165\1\164\1\157\1\141\1\uffff\1\164\3\uffff"+
        "\1\154\2\uffff\1\146\1\157\1\uffff\1\145\1\75\1\150\5\uffff\1\145"+
        "\1\150\1\141\6\uffff\1\141\1\142\1\141\2\151\1\162\1\163\1\164\1"+
        "\163\1\164\1\60\1\157\1\164\2\uffff\1\151\1\167\1\165\1\151\1\154"+
        "\1\163\1\154\1\164\1\144\1\156\1\151\1\164\2\145\1\60\1\uffff\1"+
        "\154\1\165\1\154\1\60\1\145\3\163\2\151\2\60\1\156\1\145\1\156\1"+
        "\60\1\uffff\1\145\1\162\1\145\1\uffff\2\60\1\145\1\60\2\143\2\uffff"+
        "\1\147\1\155\1\144\1\uffff\1\141\1\156\1\60\2\uffff\1\60\1\uffff"+
        "\3\60\1\56\1\163\1\156\1\60\6\uffff\2\60\3\uffff";
    static final String DFA11_maxS =
        "\1\175\1\154\1\uffff\1\165\1\164\1\157\1\141\1\uffff\1\171\3\uffff"+
        "\1\170\2\uffff\1\156\1\157\1\uffff\1\145\1\75\1\150\5\uffff\1\145"+
        "\1\162\1\141\6\uffff\1\141\1\142\1\141\2\151\1\162\1\163\1\164\1"+
        "\163\1\164\1\172\1\157\1\164\2\uffff\1\151\1\167\1\165\1\151\1\154"+
        "\1\163\1\154\1\164\1\144\1\156\1\151\1\164\2\145\1\172\1\uffff\1"+
        "\154\1\165\1\154\1\172\1\145\3\163\2\151\2\172\1\156\1\145\1\156"+
        "\1\172\1\uffff\1\145\1\162\1\145\1\uffff\2\172\1\145\1\172\2\143"+
        "\2\uffff\1\147\1\155\1\144\1\uffff\1\141\1\156\1\172\2\uffff\1\172"+
        "\1\uffff\3\172\1\56\1\163\1\156\1\172\6\uffff\2\172\3\uffff";
    static final String DFA11_acceptS =
        "\2\uffff\1\2\4\uffff\1\7\1\uffff\1\11\1\12\1\13\1\uffff\1\15\1\16"+
        "\2\uffff\1\21\3\uffff\1\30\1\32\1\33\1\34\1\35\3\uffff\1\41\1\43"+
        "\1\44\1\45\1\46\1\47\15\uffff\1\31\1\24\17\uffff\1\25\20\uffff\1"+
        "\17\3\uffff\1\36\6\uffff\1\5\1\6\3\uffff\1\26\3\uffff\1\37\1\42"+
        "\1\uffff\1\1\7\uffff\1\27\1\40\1\3\1\4\1\10\1\23\2\uffff\1\22\1"+
        "\14\1\20";
    static final String DFA11_specialS =
        "\174\uffff}>";
    static final String[] DFA11_transitionS = {
            "\2\42\2\uffff\1\42\22\uffff\1\42\1\uffff\1\36\3\uffff\1\25\1"+
            "\uffff\1\7\1\13\1\31\1\27\1\21\1\30\1\35\1\37\12\41\1\uffff"+
            "\1\16\1\26\1\23\3\uffff\22\40\1\10\7\40\1\11\1\uffff\1\12\3"+
            "\uffff\1\40\1\20\1\1\1\40\1\14\1\34\2\40\1\17\3\40\1\6\1\32"+
            "\1\40\1\3\1\40\1\22\1\4\1\33\1\40\1\5\1\24\3\40\1\2\1\uffff"+
            "\1\15",
            "\1\43",
            "",
            "\1\44",
            "\1\45",
            "\1\46",
            "\1\47",
            "",
            "\1\50\4\uffff\1\51",
            "",
            "",
            "",
            "\1\53\13\uffff\1\52",
            "",
            "",
            "\1\55\7\uffff\1\54",
            "\1\56",
            "",
            "\1\57",
            "\1\60",
            "\1\62",
            "",
            "",
            "",
            "",
            "",
            "\1\63",
            "\1\65\11\uffff\1\64",
            "\1\66",
            "",
            "",
            "",
            "",
            "",
            "",
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
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "\1\102",
            "\1\103",
            "",
            "",
            "\1\104",
            "\1\105",
            "\1\106",
            "\1\107",
            "\1\110",
            "\1\111",
            "\1\112",
            "\1\113",
            "\1\114",
            "\1\115",
            "\1\116",
            "\1\117",
            "\1\120",
            "\1\121",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "",
            "\1\123",
            "\1\124",
            "\1\125",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "\1\127",
            "\1\130",
            "\1\131",
            "\1\132",
            "\1\133",
            "\1\134",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "\1\137",
            "\1\140",
            "\1\141",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "",
            "\1\143",
            "\1\144",
            "\1\145",
            "",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "\1\150",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "\1\152",
            "\1\153",
            "",
            "",
            "\1\154",
            "\1\155",
            "\1\156",
            "",
            "\1\157",
            "\1\160",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "",
            "",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "\1\166",
            "\1\167",
            "\1\170",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "",
            "",
            "",
            "",
            "",
            "",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "",
            "",
            ""
    };

    static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
    static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
    static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
    static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
    static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
    static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
    static final short[][] DFA11_transition;

    static {
        int numStates = DFA11_transitionS.length;
        DFA11_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
        }
    }

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = DFA11_eot;
            this.eof = DFA11_eof;
            this.min = DFA11_min;
            this.max = DFA11_max;
            this.accept = DFA11_accept;
            this.special = DFA11_special;
            this.transition = DFA11_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | STRING | COMMENT | IDENT | INT | WHITESPACE );";
        }
    }
 

}