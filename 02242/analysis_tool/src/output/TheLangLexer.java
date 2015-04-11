package output;

// $ANTLR 3.3 Nov 30, 2010 12:45:30 C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g 2013-11-04 16:59:51

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class TheLangLexer extends Lexer {
    public static final int EOF=-1;
    public static final int AND=4;
    public static final int OR=5;
    public static final int ASSIGN=6;
    public static final int SEMI=7;
    public static final int GT=8;
    public static final int GE=9;
    public static final int LT=10;
    public static final int LE=11;
    public static final int EQ=12;
    public static final int NEQ=13;
    public static final int PLUS=14;
    public static final int MINUS=15;
    public static final int MUL=16;
    public static final int DIV=17;
    public static final int NOT=18;
    public static final int LPAREN=19;
    public static final int RPAREN=20;
    public static final int LBRACE=21;
    public static final int RBRACE=22;
    public static final int LBRACKET=23;
    public static final int RBRACKET=24;
    public static final int COLON=25;
    public static final int IF=26;
    public static final int THEN=27;
    public static final int ELSE=28;
    public static final int FI=29;
    public static final int WHILE=30;
    public static final int DO=31;
    public static final int OD=32;
    public static final int SKIP=33;
    public static final int WRITE=34;
    public static final int READ=35;
    public static final int PROGRAM=36;
    public static final int END=37;
    public static final int TRUE=38;
    public static final int FALSE=39;
    public static final int INT=40;
    public static final int LOW=41;
    public static final int HIGH=42;
    public static final int IDENTIFIER=43;
    public static final int INTEGER=44;
    public static final int COMMENT=45;
    public static final int LETTER=46;
    public static final int WS=47;

    // delegates
    // delegators

    public TheLangLexer() {;} 
    public TheLangLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public TheLangLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g"; }

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:7:5: ( '&' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:7:7: '&'
            {
            match('&'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:8:4: ( '|' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:8:6: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "ASSIGN"
    public final void mASSIGN() throws RecognitionException {
        try {
            int _type = ASSIGN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:9:8: ( ':=' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:9:10: ':='
            {
            match(":="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ASSIGN"

    // $ANTLR start "SEMI"
    public final void mSEMI() throws RecognitionException {
        try {
            int _type = SEMI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:10:6: ( ';' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:10:8: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SEMI"

    // $ANTLR start "GT"
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:11:4: ( '>' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:11:6: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GT"

    // $ANTLR start "GE"
    public final void mGE() throws RecognitionException {
        try {
            int _type = GE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:12:4: ( '>=' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:12:6: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GE"

    // $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:13:4: ( '<' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:13:6: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LT"

    // $ANTLR start "LE"
    public final void mLE() throws RecognitionException {
        try {
            int _type = LE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:14:4: ( '<=' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:14:6: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LE"

    // $ANTLR start "EQ"
    public final void mEQ() throws RecognitionException {
        try {
            int _type = EQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:15:4: ( '=' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:15:6: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQ"

    // $ANTLR start "NEQ"
    public final void mNEQ() throws RecognitionException {
        try {
            int _type = NEQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:16:5: ( '!=' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:16:7: '!='
            {
            match("!="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NEQ"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:17:6: ( '+' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:17:8: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:18:7: ( '-' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:18:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "MUL"
    public final void mMUL() throws RecognitionException {
        try {
            int _type = MUL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:19:5: ( '*' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:19:7: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MUL"

    // $ANTLR start "DIV"
    public final void mDIV() throws RecognitionException {
        try {
            int _type = DIV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:20:5: ( '/' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:20:7: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIV"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:21:5: ( '!' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:21:7: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:22:8: ( '(' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:22:10: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LPAREN"

    // $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:23:8: ( ')' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:23:10: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RPAREN"

    // $ANTLR start "LBRACE"
    public final void mLBRACE() throws RecognitionException {
        try {
            int _type = LBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:24:8: ( '{' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:24:10: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LBRACE"

    // $ANTLR start "RBRACE"
    public final void mRBRACE() throws RecognitionException {
        try {
            int _type = RBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:25:8: ( '}' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:25:10: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RBRACE"

    // $ANTLR start "LBRACKET"
    public final void mLBRACKET() throws RecognitionException {
        try {
            int _type = LBRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:26:10: ( '[' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:26:12: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LBRACKET"

    // $ANTLR start "RBRACKET"
    public final void mRBRACKET() throws RecognitionException {
        try {
            int _type = RBRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:27:10: ( ']' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:27:12: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RBRACKET"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:28:7: ( ':' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:28:9: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "IF"
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:29:4: ( 'if' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:29:6: 'if'
            {
            match("if"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IF"

    // $ANTLR start "THEN"
    public final void mTHEN() throws RecognitionException {
        try {
            int _type = THEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:30:6: ( 'then' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:30:8: 'then'
            {
            match("then"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "THEN"

    // $ANTLR start "ELSE"
    public final void mELSE() throws RecognitionException {
        try {
            int _type = ELSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:31:6: ( 'else' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:31:8: 'else'
            {
            match("else"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ELSE"

    // $ANTLR start "FI"
    public final void mFI() throws RecognitionException {
        try {
            int _type = FI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:32:4: ( 'fi' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:32:6: 'fi'
            {
            match("fi"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FI"

    // $ANTLR start "WHILE"
    public final void mWHILE() throws RecognitionException {
        try {
            int _type = WHILE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:33:7: ( 'while' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:33:9: 'while'
            {
            match("while"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHILE"

    // $ANTLR start "DO"
    public final void mDO() throws RecognitionException {
        try {
            int _type = DO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:34:4: ( 'do' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:34:6: 'do'
            {
            match("do"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DO"

    // $ANTLR start "OD"
    public final void mOD() throws RecognitionException {
        try {
            int _type = OD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:35:4: ( 'od' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:35:6: 'od'
            {
            match("od"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OD"

    // $ANTLR start "SKIP"
    public final void mSKIP() throws RecognitionException {
        try {
            int _type = SKIP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:36:6: ( 'skip' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:36:8: 'skip'
            {
            match("skip"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SKIP"

    // $ANTLR start "WRITE"
    public final void mWRITE() throws RecognitionException {
        try {
            int _type = WRITE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:37:7: ( 'write' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:37:9: 'write'
            {
            match("write"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WRITE"

    // $ANTLR start "READ"
    public final void mREAD() throws RecognitionException {
        try {
            int _type = READ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:38:6: ( 'read' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:38:8: 'read'
            {
            match("read"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "READ"

    // $ANTLR start "PROGRAM"
    public final void mPROGRAM() throws RecognitionException {
        try {
            int _type = PROGRAM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:39:9: ( 'program' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:39:11: 'program'
            {
            match("program"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PROGRAM"

    // $ANTLR start "END"
    public final void mEND() throws RecognitionException {
        try {
            int _type = END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:40:5: ( 'end' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:40:7: 'end'
            {
            match("end"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "END"

    // $ANTLR start "TRUE"
    public final void mTRUE() throws RecognitionException {
        try {
            int _type = TRUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:41:6: ( 'true' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:41:8: 'true'
            {
            match("true"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TRUE"

    // $ANTLR start "FALSE"
    public final void mFALSE() throws RecognitionException {
        try {
            int _type = FALSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:42:7: ( 'false' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:42:9: 'false'
            {
            match("false"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FALSE"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:43:5: ( 'int' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:43:7: 'int'
            {
            match("int"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "LOW"
    public final void mLOW() throws RecognitionException {
        try {
            int _type = LOW;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:44:5: ( 'low' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:44:7: 'low'
            {
            match("low"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LOW"

    // $ANTLR start "HIGH"
    public final void mHIGH() throws RecognitionException {
        try {
            int _type = HIGH;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:45:6: ( 'high' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:45:8: 'high'
            {
            match("high"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "HIGH"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:209:9: ( '(*' ( options {greedy=false; } : . )* '*)' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:209:11: '(*' ( options {greedy=false; } : . )* '*)'
            {
            match("(*"); 

            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:209:16: ( options {greedy=false; } : . )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0=='*') ) {
                    int LA1_1 = input.LA(2);

                    if ( (LA1_1==')') ) {
                        alt1=2;
                    }
                    else if ( ((LA1_1>='\u0000' && LA1_1<='(')||(LA1_1>='*' && LA1_1<='\uFFFF')) ) {
                        alt1=1;
                    }


                }
                else if ( ((LA1_0>='\u0000' && LA1_0<=')')||(LA1_0>='+' && LA1_0<='\uFFFF')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:209:43: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            match("*)"); 

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "INTEGER"
    public final void mINTEGER() throws RecognitionException {
        try {
            int _type = INTEGER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:212:9: ( ( '0' | '1' .. '9' ( '0' .. '9' )* ) )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:212:11: ( '0' | '1' .. '9' ( '0' .. '9' )* )
            {
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:212:11: ( '0' | '1' .. '9' ( '0' .. '9' )* )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='0') ) {
                alt3=1;
            }
            else if ( ((LA3_0>='1' && LA3_0<='9')) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:212:12: '0'
                    {
                    match('0'); 

                    }
                    break;
                case 2 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:212:18: '1' .. '9' ( '0' .. '9' )*
                    {
                    matchRange('1','9'); 
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:212:27: ( '0' .. '9' )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:212:27: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop2;
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

    // $ANTLR start "IDENTIFIER"
    public final void mIDENTIFIER() throws RecognitionException {
        try {
            int _type = IDENTIFIER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:214:12: ( LETTER ( LETTER | '0' .. '9' )* )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:214:14: LETTER ( LETTER | '0' .. '9' )*
            {
            mLETTER(); 
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:214:21: ( LETTER | '0' .. '9' )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')||(LA4_0>='A' && LA4_0<='Z')||LA4_0=='_'||(LA4_0>='a' && LA4_0<='z')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IDENTIFIER"

    // $ANTLR start "LETTER"
    public final void mLETTER() throws RecognitionException {
        try {
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:217:8: ( 'A' .. 'Z' | 'a' .. 'z' | '_' )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "LETTER"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:222:4: ( ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' ) )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:222:6: ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' )
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||(input.LA(1)>='\f' && input.LA(1)<='\r')||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

             skip(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:8: ( AND | OR | ASSIGN | SEMI | GT | GE | LT | LE | EQ | NEQ | PLUS | MINUS | MUL | DIV | NOT | LPAREN | RPAREN | LBRACE | RBRACE | LBRACKET | RBRACKET | COLON | IF | THEN | ELSE | FI | WHILE | DO | OD | SKIP | WRITE | READ | PROGRAM | END | TRUE | FALSE | INT | LOW | HIGH | COMMENT | INTEGER | IDENTIFIER | WS )
        int alt5=43;
        alt5 = dfa5.predict(input);
        switch (alt5) {
            case 1 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:10: AND
                {
                mAND(); 

                }
                break;
            case 2 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:14: OR
                {
                mOR(); 

                }
                break;
            case 3 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:17: ASSIGN
                {
                mASSIGN(); 

                }
                break;
            case 4 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:24: SEMI
                {
                mSEMI(); 

                }
                break;
            case 5 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:29: GT
                {
                mGT(); 

                }
                break;
            case 6 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:32: GE
                {
                mGE(); 

                }
                break;
            case 7 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:35: LT
                {
                mLT(); 

                }
                break;
            case 8 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:38: LE
                {
                mLE(); 

                }
                break;
            case 9 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:41: EQ
                {
                mEQ(); 

                }
                break;
            case 10 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:44: NEQ
                {
                mNEQ(); 

                }
                break;
            case 11 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:48: PLUS
                {
                mPLUS(); 

                }
                break;
            case 12 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:53: MINUS
                {
                mMINUS(); 

                }
                break;
            case 13 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:59: MUL
                {
                mMUL(); 

                }
                break;
            case 14 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:63: DIV
                {
                mDIV(); 

                }
                break;
            case 15 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:67: NOT
                {
                mNOT(); 

                }
                break;
            case 16 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:71: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 17 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:78: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 18 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:85: LBRACE
                {
                mLBRACE(); 

                }
                break;
            case 19 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:92: RBRACE
                {
                mRBRACE(); 

                }
                break;
            case 20 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:99: LBRACKET
                {
                mLBRACKET(); 

                }
                break;
            case 21 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:108: RBRACKET
                {
                mRBRACKET(); 

                }
                break;
            case 22 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:117: COLON
                {
                mCOLON(); 

                }
                break;
            case 23 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:123: IF
                {
                mIF(); 

                }
                break;
            case 24 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:126: THEN
                {
                mTHEN(); 

                }
                break;
            case 25 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:131: ELSE
                {
                mELSE(); 

                }
                break;
            case 26 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:136: FI
                {
                mFI(); 

                }
                break;
            case 27 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:139: WHILE
                {
                mWHILE(); 

                }
                break;
            case 28 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:145: DO
                {
                mDO(); 

                }
                break;
            case 29 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:148: OD
                {
                mOD(); 

                }
                break;
            case 30 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:151: SKIP
                {
                mSKIP(); 

                }
                break;
            case 31 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:156: WRITE
                {
                mWRITE(); 

                }
                break;
            case 32 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:162: READ
                {
                mREAD(); 

                }
                break;
            case 33 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:167: PROGRAM
                {
                mPROGRAM(); 

                }
                break;
            case 34 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:175: END
                {
                mEND(); 

                }
                break;
            case 35 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:179: TRUE
                {
                mTRUE(); 

                }
                break;
            case 36 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:184: FALSE
                {
                mFALSE(); 

                }
                break;
            case 37 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:190: INT
                {
                mINT(); 

                }
                break;
            case 38 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:194: LOW
                {
                mLOW(); 

                }
                break;
            case 39 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:198: HIGH
                {
                mHIGH(); 

                }
                break;
            case 40 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:203: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 41 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:211: INTEGER
                {
                mINTEGER(); 

                }
                break;
            case 42 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:219: IDENTIFIER
                {
                mIDENTIFIER(); 

                }
                break;
            case 43 :
                // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:1:230: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA5 dfa5 = new DFA5(this);
    static final String DFA5_eotS =
        "\3\uffff\1\43\1\uffff\1\45\1\47\1\uffff\1\51\4\uffff\1\53\5\uffff"+
        "\14\40\15\uffff\1\75\5\40\1\103\3\40\1\107\1\110\5\40\1\uffff\1"+
        "\116\3\40\1\122\1\uffff\3\40\2\uffff\3\40\1\131\1\40\1\uffff\1\133"+
        "\1\134\1\135\1\uffff\3\40\1\141\1\142\1\40\1\uffff\1\144\3\uffff"+
        "\1\145\1\146\1\147\2\uffff\1\40\4\uffff\1\40\1\152\1\uffff";
    static final String DFA5_eofS =
        "\153\uffff";
    static final String DFA5_minS =
        "\1\11\2\uffff\1\75\1\uffff\2\75\1\uffff\1\75\4\uffff\1\52\5\uffff"+
        "\1\146\1\150\1\154\1\141\1\150\1\157\1\144\1\153\1\145\1\162\1\157"+
        "\1\151\15\uffff\1\60\1\164\1\145\1\165\1\163\1\144\1\60\1\154\2"+
        "\151\2\60\1\151\1\141\1\157\1\167\1\147\1\uffff\1\60\1\156\2\145"+
        "\1\60\1\uffff\1\163\1\154\1\164\2\uffff\1\160\1\144\1\147\1\60\1"+
        "\150\1\uffff\3\60\1\uffff\3\145\2\60\1\162\1\uffff\1\60\3\uffff"+
        "\3\60\2\uffff\1\141\4\uffff\1\155\1\60\1\uffff";
    static final String DFA5_maxS =
        "\1\175\2\uffff\1\75\1\uffff\2\75\1\uffff\1\75\4\uffff\1\52\5\uffff"+
        "\1\156\1\162\1\156\1\151\1\162\1\157\1\144\1\153\1\145\1\162\1\157"+
        "\1\151\15\uffff\1\172\1\164\1\145\1\165\1\163\1\144\1\172\1\154"+
        "\2\151\2\172\1\151\1\141\1\157\1\167\1\147\1\uffff\1\172\1\156\2"+
        "\145\1\172\1\uffff\1\163\1\154\1\164\2\uffff\1\160\1\144\1\147\1"+
        "\172\1\150\1\uffff\3\172\1\uffff\3\145\2\172\1\162\1\uffff\1\172"+
        "\3\uffff\3\172\2\uffff\1\141\4\uffff\1\155\1\172\1\uffff";
    static final String DFA5_acceptS =
        "\1\uffff\1\1\1\2\1\uffff\1\4\2\uffff\1\11\1\uffff\1\13\1\14\1\15"+
        "\1\16\1\uffff\1\21\1\22\1\23\1\24\1\25\14\uffff\1\51\1\52\1\53\1"+
        "\3\1\26\1\6\1\5\1\10\1\7\1\12\1\17\1\50\1\20\21\uffff\1\27\5\uffff"+
        "\1\32\3\uffff\1\34\1\35\5\uffff\1\45\3\uffff\1\42\6\uffff\1\46\1"+
        "\uffff\1\30\1\43\1\31\3\uffff\1\36\1\40\1\uffff\1\47\1\44\1\33\1"+
        "\37\2\uffff\1\41";
    static final String DFA5_specialS =
        "\153\uffff}>";
    static final String[] DFA5_transitionS = {
            "\2\41\1\uffff\2\41\22\uffff\1\41\1\10\4\uffff\1\1\1\uffff\1"+
            "\15\1\16\1\13\1\11\1\uffff\1\12\1\uffff\1\14\12\37\1\3\1\4\1"+
            "\6\1\7\1\5\2\uffff\32\40\1\21\1\uffff\1\22\1\uffff\1\40\1\uffff"+
            "\3\40\1\30\1\25\1\26\1\40\1\36\1\23\2\40\1\35\2\40\1\31\1\34"+
            "\1\40\1\33\1\32\1\24\2\40\1\27\3\40\1\17\1\2\1\20",
            "",
            "",
            "\1\42",
            "",
            "\1\44",
            "\1\46",
            "",
            "\1\50",
            "",
            "",
            "",
            "",
            "\1\52",
            "",
            "",
            "",
            "",
            "",
            "\1\54\7\uffff\1\55",
            "\1\56\11\uffff\1\57",
            "\1\60\1\uffff\1\61",
            "\1\63\7\uffff\1\62",
            "\1\64\11\uffff\1\65",
            "\1\66",
            "\1\67",
            "\1\70",
            "\1\71",
            "\1\72",
            "\1\73",
            "\1\74",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "\1\76",
            "\1\77",
            "\1\100",
            "\1\101",
            "\1\102",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "\1\104",
            "\1\105",
            "\1\106",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "\1\111",
            "\1\112",
            "\1\113",
            "\1\114",
            "\1\115",
            "",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "\1\117",
            "\1\120",
            "\1\121",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "",
            "\1\123",
            "\1\124",
            "\1\125",
            "",
            "",
            "\1\126",
            "\1\127",
            "\1\130",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "\1\132",
            "",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "",
            "\1\136",
            "\1\137",
            "\1\140",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "\1\143",
            "",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "",
            "",
            "",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            "",
            "",
            "\1\150",
            "",
            "",
            "",
            "",
            "\1\151",
            "\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
            ""
    };

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
        }
    }

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = DFA5_eot;
            this.eof = DFA5_eof;
            this.min = DFA5_min;
            this.max = DFA5_max;
            this.accept = DFA5_accept;
            this.special = DFA5_special;
            this.transition = DFA5_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( AND | OR | ASSIGN | SEMI | GT | GE | LT | LE | EQ | NEQ | PLUS | MINUS | MUL | DIV | NOT | LPAREN | RPAREN | LBRACE | RBRACE | LBRACKET | RBRACKET | COLON | IF | THEN | ELSE | FI | WHILE | DO | OD | SKIP | WRITE | READ | PROGRAM | END | TRUE | FALSE | INT | LOW | HIGH | COMMENT | INTEGER | IDENTIFIER | WS );";
        }
    }
 

}