// $ANTLR 3.2 Sep 23, 2009 12:02:23 D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g 2010-10-26 15:14:35

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
    public static final int CHAR=13;
    public static final int EOF=-1;
    public static final int T__19=19;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int IDENT=4;
    public static final int COMMENT=5;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int NUMBER=11;
    public static final int WHITESPACE=14;
    public static final int INT=6;
    public static final int NONNULL=10;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int NEWLINE=12;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int LOWER=8;
    public static final int UPPER=9;
    public static final int STRING=7;

    // delegates
    // delegators

    public MiniJavaLexer() {;} 
    public MiniJavaLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public MiniJavaLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g"; }

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:7:7: ( 'class' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:7:9: 'class'
            {
            match("class"); 


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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:8:7: ( 'extends' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:8:9: 'extends'
            {
            match("extends"); 


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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:9:7: ( '{' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:9:9: '{'
            {
            match('{'); 

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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:10:7: ( '}' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:10:9: '}'
            {
            match('}'); 

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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:11:7: ( 'public' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:11:9: 'public'
            {
            match("public"); 


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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:12:7: ( 'static' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:12:9: 'static'
            {
            match("static"); 


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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:13:7: ( 'void' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:13:9: 'void'
            {
            match("void"); 


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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:14:7: ( 'main' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:14:9: 'main'
            {
            match("main"); 


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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:15:7: ( '(' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:15:9: '('
            {
            match('('); 

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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:16:7: ( 'String[]' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:16:9: 'String[]'
            {
            match("String[]"); 


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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:17:7: ( ')' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:17:9: ')'
            {
            match(')'); 

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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:18:7: ( '=' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:18:9: '='
            {
            match('='); 

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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:19:7: ( ';' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:19:9: ';'
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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:20:7: ( 'boolean' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:20:9: 'boolean'
            {
            match("boolean"); 


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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:21:7: ( 'int' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:21:9: 'int'
            {
            match("int"); 


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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:22:7: ( '[' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:22:9: '['
            {
            match('['); 

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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:23:7: ( ']' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:23:9: ']'
            {
            match(']'); 

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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:24:7: ( ',' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:24:9: ','
            {
            match(','); 

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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:25:7: ( 'return' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:25:9: 'return'
            {
            match("return"); 


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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:26:7: ( 'if' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:26:9: 'if'
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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:27:7: ( 'else' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:27:9: 'else'
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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:28:7: ( 'while' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:28:9: 'while'
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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:29:7: ( 'System.out.println' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:29:9: 'System.out.println'
            {
            match("System.out.println"); 


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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:30:7: ( '&&' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:30:9: '&&'
            {
            match("&&"); 


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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:31:7: ( '==' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:31:9: '=='
            {
            match("=="); 


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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:32:7: ( '<' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:32:9: '<'
            {
            match('<'); 

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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:33:7: ( '+' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:33:9: '+'
            {
            match('+'); 

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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:34:7: ( '-' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:34:9: '-'
            {
            match('-'); 

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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:35:7: ( '*' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:35:9: '*'
            {
            match('*'); 

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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:36:7: ( '!' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:36:9: '!'
            {
            match('!'); 

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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:37:7: ( 'new' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:37:9: 'new'
            {
            match("new"); 


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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:38:7: ( 'true' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:38:9: 'true'
            {
            match("true"); 


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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:39:7: ( 'false' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:39:9: 'false'
            {
            match("false"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__47"

    // $ANTLR start "T__48"
    public final void mT__48() throws RecognitionException {
        try {
            int _type = T__48;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:40:7: ( '.' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:40:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__48"

    // $ANTLR start "T__49"
    public final void mT__49() throws RecognitionException {
        try {
            int _type = T__49;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:41:7: ( 'this' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:41:9: 'this'
            {
            match("this"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__49"

    // $ANTLR start "LOWER"
    public final void mLOWER() throws RecognitionException {
        try {
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:300:16: ( ( 'a' .. 'z' ) )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:300:18: ( 'a' .. 'z' )
            {
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:300:18: ( 'a' .. 'z' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:300:19: 'a' .. 'z'
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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:301:16: ( ( 'A' .. 'Z' ) )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:301:18: ( 'A' .. 'Z' )
            {
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:301:18: ( 'A' .. 'Z' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:301:19: 'A' .. 'Z'
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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:302:18: ( ( '1' .. '9' ) )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:302:20: ( '1' .. '9' )
            {
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:302:20: ( '1' .. '9' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:302:21: '1' .. '9'
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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:303:17: ( ( '0' | NONNULL ) )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:303:19: ( '0' | NONNULL )
            {
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:303:19: ( '0' | NONNULL )
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
                    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:303:20: '0'
                    {
                    match('0'); 

                    }
                    break;
                case 2 :
                    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:303:26: NONNULL
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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:304:7: ( ( LOWER | UPPER ) ( LOWER | UPPER | NUMBER | '_' )* )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:304:9: ( LOWER | UPPER ) ( LOWER | UPPER | NUMBER | '_' )*
            {
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:304:9: ( LOWER | UPPER )
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
                    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:304:11: LOWER
                    {
                    mLOWER(); 

                    }
                    break;
                case 2 :
                    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:304:19: UPPER
                    {
                    mUPPER(); 

                    }
                    break;

            }

            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:304:27: ( LOWER | UPPER | NUMBER | '_' )*
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
            	    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:304:29: LOWER
            	    {
            	    mLOWER(); 

            	    }
            	    break;
            	case 2 :
            	    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:304:37: UPPER
            	    {
            	    mUPPER(); 

            	    }
            	    break;
            	case 3 :
            	    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:304:45: NUMBER
            	    {
            	    mNUMBER(); 

            	    }
            	    break;
            	case 4 :
            	    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:304:54: '_'
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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:305:17: ( ( '\\r' )? '\\n' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:305:18: ( '\\r' )? '\\n'
            {
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:305:18: ( '\\r' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='\r') ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:305:18: '\\r'
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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:306:5: ( '0' | ( NONNULL ( NUMBER )* ) )
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
                    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:306:7: '0'
                    {
                    match('0'); 

                    }
                    break;
                case 2 :
                    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:306:13: ( NONNULL ( NUMBER )* )
                    {
                    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:306:13: ( NONNULL ( NUMBER )* )
                    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:306:15: NONNULL ( NUMBER )*
                    {
                    mNONNULL(); 
                    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:306:23: ( NUMBER )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( ((LA5_0>='0' && LA5_0<='9')) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:306:23: NUMBER
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

    // $ANTLR start "CHAR"
    public final void mCHAR() throws RecognitionException {
        try {
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:307:15: ( ' ' | '!' | ( '\\u0023' .. '\\u005B' ) | ( '\\u005D' .. '\\u007E' ) | '\\\\\"' | '\\\\\\\\' | '\\\\t' | '\\\\n' )
            int alt7=8;
            alt7 = dfa7.predict(input);
            switch (alt7) {
                case 1 :
                    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:307:17: ' '
                    {
                    match(' '); 

                    }
                    break;
                case 2 :
                    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:307:23: '!'
                    {
                    match('!'); 

                    }
                    break;
                case 3 :
                    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:307:29: ( '\\u0023' .. '\\u005B' )
                    {
                    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:307:29: ( '\\u0023' .. '\\u005B' )
                    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:307:30: '\\u0023' .. '\\u005B'
                    {
                    matchRange('#','['); 

                    }


                    }
                    break;
                case 4 :
                    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:307:52: ( '\\u005D' .. '\\u007E' )
                    {
                    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:307:52: ( '\\u005D' .. '\\u007E' )
                    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:307:53: '\\u005D' .. '\\u007E'
                    {
                    matchRange(']','~'); 

                    }


                    }
                    break;
                case 5 :
                    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:307:75: '\\\\\"'
                    {
                    match("\\\""); 


                    }
                    break;
                case 6 :
                    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:307:83: '\\\\\\\\'
                    {
                    match("\\\\"); 


                    }
                    break;
                case 7 :
                    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:307:92: '\\\\t'
                    {
                    match("\\t"); 


                    }
                    break;
                case 8 :
                    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:307:100: '\\\\n'
                    {
                    match("\\n"); 


                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "CHAR"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:308:8: ( '\"' ( CHAR )* '\"' )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:308:10: '\"' ( CHAR )* '\"'
            {
            match('\"'); 
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:308:14: ( CHAR )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>=' ' && LA8_0<='!')||(LA8_0>='#' && LA8_0<='~')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:308:14: CHAR
            	    {
            	    mCHAR(); 

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

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
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:309:9: ( '//' ( . )* NEWLINE | '/*' ( . )* '*/' )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='/') ) {
                int LA11_1 = input.LA(2);

                if ( (LA11_1=='/') ) {
                    alt11=1;
                }
                else if ( (LA11_1=='*') ) {
                    alt11=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 11, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:309:11: '//' ( . )* NEWLINE
                    {
                    match("//"); 

                    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:309:16: ( . )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0=='\r') ) {
                            alt9=2;
                        }
                        else if ( (LA9_0=='\n') ) {
                            alt9=2;
                        }
                        else if ( ((LA9_0>='\u0000' && LA9_0<='\t')||(LA9_0>='\u000B' && LA9_0<='\f')||(LA9_0>='\u000E' && LA9_0<='\uFFFF')) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:309:16: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);

                    mNEWLINE(); 

                    }
                    break;
                case 2 :
                    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:309:29: '/*' ( . )* '*/'
                    {
                    match("/*"); 

                    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:309:34: ( . )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0=='*') ) {
                            int LA10_1 = input.LA(2);

                            if ( (LA10_1=='/') ) {
                                alt10=2;
                            }
                            else if ( ((LA10_1>='\u0000' && LA10_1<='.')||(LA10_1>='0' && LA10_1<='\uFFFF')) ) {
                                alt10=1;
                            }


                        }
                        else if ( ((LA10_0>='\u0000' && LA10_0<=')')||(LA10_0>='+' && LA10_0<='\uFFFF')) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:309:34: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);

                    match("*/"); 


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

    // $ANTLR start "WHITESPACE"
    public final void mWHITESPACE() throws RecognitionException {
        try {
            int _type = WHITESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:310:13: ( ( ' ' | '\\t' | NEWLINE )+ )
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:310:17: ( ' ' | '\\t' | NEWLINE )+
            {
            // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:310:17: ( ' ' | '\\t' | NEWLINE )+
            int cnt12=0;
            loop12:
            do {
                int alt12=4;
                switch ( input.LA(1) ) {
                case ' ':
                    {
                    alt12=1;
                    }
                    break;
                case '\t':
                    {
                    alt12=2;
                    }
                    break;
                case '\n':
                case '\r':
                    {
                    alt12=3;
                    }
                    break;

                }

                switch (alt12) {
            	case 1 :
            	    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:310:19: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;
            	case 2 :
            	    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:310:25: '\\t'
            	    {
            	    match('\t'); 

            	    }
            	    break;
            	case 3 :
            	    // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:310:32: NEWLINE
            	    {
            	    mNEWLINE(); 

            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
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
        // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:8: ( T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | IDENT | INT | STRING | COMMENT | WHITESPACE )
        int alt13=40;
        alt13 = dfa13.predict(input);
        switch (alt13) {
            case 1 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:10: T__15
                {
                mT__15(); 

                }
                break;
            case 2 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:16: T__16
                {
                mT__16(); 

                }
                break;
            case 3 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:22: T__17
                {
                mT__17(); 

                }
                break;
            case 4 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:28: T__18
                {
                mT__18(); 

                }
                break;
            case 5 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:34: T__19
                {
                mT__19(); 

                }
                break;
            case 6 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:40: T__20
                {
                mT__20(); 

                }
                break;
            case 7 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:46: T__21
                {
                mT__21(); 

                }
                break;
            case 8 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:52: T__22
                {
                mT__22(); 

                }
                break;
            case 9 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:58: T__23
                {
                mT__23(); 

                }
                break;
            case 10 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:64: T__24
                {
                mT__24(); 

                }
                break;
            case 11 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:70: T__25
                {
                mT__25(); 

                }
                break;
            case 12 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:76: T__26
                {
                mT__26(); 

                }
                break;
            case 13 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:82: T__27
                {
                mT__27(); 

                }
                break;
            case 14 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:88: T__28
                {
                mT__28(); 

                }
                break;
            case 15 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:94: T__29
                {
                mT__29(); 

                }
                break;
            case 16 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:100: T__30
                {
                mT__30(); 

                }
                break;
            case 17 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:106: T__31
                {
                mT__31(); 

                }
                break;
            case 18 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:112: T__32
                {
                mT__32(); 

                }
                break;
            case 19 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:118: T__33
                {
                mT__33(); 

                }
                break;
            case 20 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:124: T__34
                {
                mT__34(); 

                }
                break;
            case 21 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:130: T__35
                {
                mT__35(); 

                }
                break;
            case 22 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:136: T__36
                {
                mT__36(); 

                }
                break;
            case 23 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:142: T__37
                {
                mT__37(); 

                }
                break;
            case 24 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:148: T__38
                {
                mT__38(); 

                }
                break;
            case 25 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:154: T__39
                {
                mT__39(); 

                }
                break;
            case 26 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:160: T__40
                {
                mT__40(); 

                }
                break;
            case 27 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:166: T__41
                {
                mT__41(); 

                }
                break;
            case 28 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:172: T__42
                {
                mT__42(); 

                }
                break;
            case 29 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:178: T__43
                {
                mT__43(); 

                }
                break;
            case 30 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:184: T__44
                {
                mT__44(); 

                }
                break;
            case 31 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:190: T__45
                {
                mT__45(); 

                }
                break;
            case 32 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:196: T__46
                {
                mT__46(); 

                }
                break;
            case 33 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:202: T__47
                {
                mT__47(); 

                }
                break;
            case 34 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:208: T__48
                {
                mT__48(); 

                }
                break;
            case 35 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:214: T__49
                {
                mT__49(); 

                }
                break;
            case 36 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:220: IDENT
                {
                mIDENT(); 

                }
                break;
            case 37 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:226: INT
                {
                mINT(); 

                }
                break;
            case 38 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:230: STRING
                {
                mSTRING(); 

                }
                break;
            case 39 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:237: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 40 :
                // D:\\My Dropbox\\02332 Compilerteknik\\Workspace\\MiniJavaProject1\\src\\compiler\\Frontend\\MiniJava.g:1:245: WHITESPACE
                {
                mWHITESPACE(); 

                }
                break;

        }

    }


    protected DFA7 dfa7 = new DFA7(this);
    protected DFA13 dfa13 = new DFA13(this);
    static final String DFA7_eotS =
        "\12\uffff";
    static final String DFA7_eofS =
        "\12\uffff";
    static final String DFA7_minS =
        "\1\40\4\uffff\1\42\4\uffff";
    static final String DFA7_maxS =
        "\1\176\4\uffff\1\164\4\uffff";
    static final String DFA7_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\uffff\1\5\1\6\1\7\1\10";
    static final String DFA7_specialS =
        "\12\uffff}>";
    static final String[] DFA7_transitionS = {
            "\1\1\1\2\1\uffff\71\3\1\5\42\4",
            "",
            "",
            "",
            "",
            "\1\6\71\uffff\1\7\21\uffff\1\11\5\uffff\1\10",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
    static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
    static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
    static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
    static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
    static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
    static final short[][] DFA7_transition;

    static {
        int numStates = DFA7_transitionS.length;
        DFA7_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
        }
    }

    class DFA7 extends DFA {

        public DFA7(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = DFA7_eot;
            this.eof = DFA7_eof;
            this.min = DFA7_min;
            this.max = DFA7_max;
            this.accept = DFA7_accept;
            this.special = DFA7_special;
            this.transition = DFA7_transition;
        }
        public String getDescription() {
            return "307:10: fragment CHAR : ( ' ' | '!' | ( '\\u0023' .. '\\u005B' ) | ( '\\u005D' .. '\\u007E' ) | '\\\\\"' | '\\\\\\\\' | '\\\\t' | '\\\\n' );";
        }
    }
    static final String DFA13_eotS =
        "\1\uffff\2\37\2\uffff\4\37\1\uffff\1\37\1\uffff\1\56\1\uffff\2"+
        "\37\3\uffff\2\37\6\uffff\3\37\6\uffff\11\37\2\uffff\2\37\1\103\20"+
        "\37\1\124\1\uffff\2\37\1\127\5\37\1\135\2\37\1\140\1\141\3\37\1"+
        "\uffff\2\37\1\uffff\1\147\1\150\1\37\1\152\1\37\1\uffff\2\37\2\uffff"+
        "\4\37\1\162\2\uffff\1\163\1\uffff\1\37\1\165\1\166\3\37\1\172\2"+
        "\uffff\1\173\4\uffff\1\174\3\uffff";
    static final String DFA13_eofS =
        "\175\uffff";
    static final String DFA13_minS =
        "\1\11\2\154\2\uffff\1\165\1\164\1\157\1\141\1\uffff\1\164\1\uffff"+
        "\1\75\1\uffff\1\157\1\146\3\uffff\1\145\1\150\6\uffff\1\145\1\150"+
        "\1\141\6\uffff\1\141\1\164\1\163\1\142\1\141\2\151\1\162\1\163\2"+
        "\uffff\1\157\1\164\1\60\1\164\1\151\1\167\1\165\1\151\1\154\1\163"+
        "\2\145\1\154\1\164\1\144\1\156\1\151\1\164\1\154\1\60\1\uffff\1"+
        "\165\1\154\1\60\1\145\3\163\1\156\1\60\2\151\2\60\1\156\2\145\1"+
        "\uffff\1\162\1\145\1\uffff\2\60\1\145\1\60\1\144\1\uffff\2\143\2"+
        "\uffff\1\147\1\155\1\141\1\156\1\60\2\uffff\1\60\1\uffff\1\163\2"+
        "\60\1\133\1\56\1\156\1\60\2\uffff\1\60\4\uffff\1\60\3\uffff";
    static final String DFA13_maxS =
        "\1\175\1\154\1\170\2\uffff\1\165\1\164\1\157\1\141\1\uffff\1\171"+
        "\1\uffff\1\75\1\uffff\1\157\1\156\3\uffff\1\145\1\150\6\uffff\1"+
        "\145\1\162\1\141\6\uffff\1\141\1\164\1\163\1\142\1\141\2\151\1\162"+
        "\1\163\2\uffff\1\157\1\164\1\172\1\164\1\151\1\167\1\165\1\151\1"+
        "\154\1\163\2\145\1\154\1\164\1\144\1\156\1\151\1\164\1\154\1\172"+
        "\1\uffff\1\165\1\154\1\172\1\145\3\163\1\156\1\172\2\151\2\172\1"+
        "\156\2\145\1\uffff\1\162\1\145\1\uffff\2\172\1\145\1\172\1\144\1"+
        "\uffff\2\143\2\uffff\1\147\1\155\1\141\1\156\1\172\2\uffff\1\172"+
        "\1\uffff\1\163\2\172\1\133\1\56\1\156\1\172\2\uffff\1\172\4\uffff"+
        "\1\172\3\uffff";
    static final String DFA13_acceptS =
        "\3\uffff\1\3\1\4\4\uffff\1\11\1\uffff\1\13\1\uffff\1\15\2\uffff"+
        "\1\20\1\21\1\22\2\uffff\1\30\1\32\1\33\1\34\1\35\1\36\3\uffff\1"+
        "\42\1\44\1\45\1\46\1\47\1\50\11\uffff\1\31\1\14\24\uffff\1\24\20"+
        "\uffff\1\17\2\uffff\1\37\5\uffff\1\25\2\uffff\1\7\1\10\5\uffff\1"+
        "\40\1\43\1\uffff\1\1\7\uffff\1\26\1\41\1\uffff\1\5\1\6\1\12\1\27"+
        "\1\uffff\1\23\1\2\1\16";
    static final String DFA13_specialS =
        "\175\uffff}>";
    static final String[] DFA13_transitionS = {
            "\2\43\2\uffff\1\43\22\uffff\1\43\1\32\1\41\3\uffff\1\25\1\uffff"+
            "\1\11\1\13\1\31\1\27\1\22\1\30\1\36\1\42\12\40\1\uffff\1\15"+
            "\1\26\1\14\3\uffff\22\37\1\12\7\37\1\20\1\uffff\1\21\3\uffff"+
            "\1\37\1\16\1\1\1\37\1\2\1\35\2\37\1\17\3\37\1\10\1\33\1\37\1"+
            "\5\1\37\1\23\1\6\1\34\1\37\1\7\1\24\3\37\1\3\1\uffff\1\4",
            "\1\44",
            "\1\46\13\uffff\1\45",
            "",
            "",
            "\1\47",
            "\1\50",
            "\1\51",
            "\1\52",
            "",
            "\1\53\4\uffff\1\54",
            "",
            "\1\55",
            "",
            "\1\57",
            "\1\61\7\uffff\1\60",
            "",
            "",
            "",
            "\1\62",
            "\1\63",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\64",
            "\1\66\11\uffff\1\65",
            "\1\67",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\70",
            "\1\71",
            "\1\72",
            "\1\73",
            "\1\74",
            "\1\75",
            "\1\76",
            "\1\77",
            "\1\100",
            "",
            "",
            "\1\101",
            "\1\102",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
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
            "\1\122",
            "\1\123",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "",
            "\1\125",
            "\1\126",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\130",
            "\1\131",
            "\1\132",
            "\1\133",
            "\1\134",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\136",
            "\1\137",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\142",
            "\1\143",
            "\1\144",
            "",
            "\1\145",
            "\1\146",
            "",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\151",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\153",
            "",
            "\1\154",
            "\1\155",
            "",
            "",
            "\1\156",
            "\1\157",
            "\1\160",
            "\1\161",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "",
            "",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "",
            "\1\164",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\167",
            "\1\170",
            "\1\171",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "",
            "",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "",
            "",
            "",
            "",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "",
            "",
            ""
    };

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | IDENT | INT | STRING | COMMENT | WHITESPACE );";
        }
    }
 

}