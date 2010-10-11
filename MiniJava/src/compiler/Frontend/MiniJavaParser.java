// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g 2010-10-11 13:40:56

  package compiler.Frontend;
  
  import java.util.LinkedList;
  import compiler.IR.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class MiniJavaParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "IDENT", "INT", "STRING", "NEWLINE", "COMMENT", "LOWER", "UPPER", "NONNULL", "NUMBER", "WHITESPACE", "'class'", "'{'", "'public'", "'static'", "'void'", "'main'", "'('", "'String'", "'['", "']'", "')'", "'extends'", "'}'", "';'", "'int'", "'boolean'", "','", "'return'", "'System.out.println'", "'='", "'if'", "'else'", "'while'", "'&&'", "'=='", "'<'", "'+'", "'-'", "'*'", "'new'", "'true'", "'false'", "'.'", "'this'"
    };
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


        public MiniJavaParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public MiniJavaParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return MiniJavaParser.tokenNames; }
    public String getGrammarFileName() { return "/home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g"; }





    // $ANTLR start "program"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:26:1: program returns [MJProgram p] : mc= mainClass (cd= classDeclaration )* ;
    public final MJProgram program() throws RecognitionException {
        MJProgram p = null;

        MJClass mc = null;

        MJClass cd = null;


        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:26:30: (mc= mainClass (cd= classDeclaration )* )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:27:3: mc= mainClass (cd= classDeclaration )*
            {
             LinkedList<MJClass> cdl = new LinkedList<MJClass>(); 
            pushFollow(FOLLOW_mainClass_in_program59);
            mc=mainClass();

            state._fsp--;

             cdl.add(mc); p = new MJProgram(cdl); 
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:29:3: (cd= classDeclaration )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==14) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:29:4: cd= classDeclaration
            	    {
            	    pushFollow(FOLLOW_classDeclaration_in_program70);
            	    cd=classDeclaration();

            	    state._fsp--;

            	    cdl.add(cd);

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return p;
    }
    // $ANTLR end "program"


    // $ANTLR start "mainClass"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:36:1: mainClass returns [MJClass c] : 'class' cname= IDENT '{' 'public' 'static' 'void' 'main' '(' 'String' '[' ']' parname= IDENT ')' b= block ;
    public final MJClass mainClass() throws RecognitionException {
        MJClass c = null;

        Token cname=null;
        Token parname=null;
        MJBlock b = null;


        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:37:3: ( 'class' cname= IDENT '{' 'public' 'static' 'void' 'main' '(' 'String' '[' ']' parname= IDENT ')' b= block )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:37:5: 'class' cname= IDENT '{' 'public' 'static' 'void' 'main' '(' 'String' '[' ']' parname= IDENT ')' b= block
            {
            match(input,14,FOLLOW_14_in_mainClass100); 
            cname=(Token)match(input,IDENT,FOLLOW_IDENT_in_mainClass106); 
            match(input,15,FOLLOW_15_in_mainClass108); 
            match(input,16,FOLLOW_16_in_mainClass115); 
            match(input,17,FOLLOW_17_in_mainClass117); 
            match(input,18,FOLLOW_18_in_mainClass119); 
            match(input,19,FOLLOW_19_in_mainClass121); 
            match(input,20,FOLLOW_20_in_mainClass123); 
            match(input,21,FOLLOW_21_in_mainClass125); 
            match(input,22,FOLLOW_22_in_mainClass127); 
            match(input,23,FOLLOW_23_in_mainClass129); 
            parname=(Token)match(input,IDENT,FOLLOW_IDENT_in_mainClass135); 
            match(input,24,FOLLOW_24_in_mainClass137); 
            pushFollow(FOLLOW_block_in_mainClass143);
            b=block();

            state._fsp--;


                MJType partype = new MJType(new MJType("String"));
                MJVariable par = new MJVariable( partype, (parname!=null?parname.getText():null));
                 
                MJMethod md = new MJMethod(MJType.Tvoid, "main", par, b, true, true);
                c = new MJClass((cname!=null?cname.getText():null), md);
              

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return c;
    }
    // $ANTLR end "mainClass"


    // $ANTLR start "classDeclaration"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:48:1: classDeclaration returns [MJClass c] : 'class' cname= IDENT ( 'extends' sc= IDENT )? '{' (v= varDeclaration )* (m= methodDeclaration )* '}' ;
    public final MJClass classDeclaration() throws RecognitionException {
        MJClass c = null;

        Token cname=null;
        Token sc=null;
        MJVariable v = null;

        MJMethod m = null;


        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:49:3: ( 'class' cname= IDENT ( 'extends' sc= IDENT )? '{' (v= varDeclaration )* (m= methodDeclaration )* '}' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:50:3: 'class' cname= IDENT ( 'extends' sc= IDENT )? '{' (v= varDeclaration )* (m= methodDeclaration )* '}'
            {
             
                LinkedList<MJVariable> vdl = new LinkedList<MJVariable>();
                LinkedList<MJMethod> mdl = new LinkedList<MJMethod>();
              
            match(input,14,FOLLOW_14_in_classDeclaration174); 
            cname=(Token)match(input,IDENT,FOLLOW_IDENT_in_classDeclaration180); 
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:54:25: ( 'extends' sc= IDENT )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==25) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:54:26: 'extends' sc= IDENT
                    {
                    match(input,25,FOLLOW_25_in_classDeclaration183); 
                    sc=(Token)match(input,IDENT,FOLLOW_IDENT_in_classDeclaration189); 

                    }
                    break;

            }

            match(input,15,FOLLOW_15_in_classDeclaration193); 
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:54:53: (v= varDeclaration )*
            loop3:
            do {
                int alt3=2;
                switch ( input.LA(1) ) {
                case 28:
                    {
                    int LA3_2 = input.LA(2);

                    if ( (LA3_2==22) ) {
                        int LA3_5 = input.LA(3);

                        if ( (LA3_5==23) ) {
                            int LA3_7 = input.LA(4);

                            if ( (LA3_7==IDENT) ) {
                                int LA3_6 = input.LA(5);

                                if ( (LA3_6==27) ) {
                                    alt3=1;
                                }


                            }


                        }


                    }
                    else if ( (LA3_2==IDENT) ) {
                        int LA3_6 = input.LA(3);

                        if ( (LA3_6==27) ) {
                            alt3=1;
                        }


                    }


                    }
                    break;
                case 29:
                    {
                    int LA3_3 = input.LA(2);

                    if ( (LA3_3==IDENT) ) {
                        int LA3_6 = input.LA(3);

                        if ( (LA3_6==27) ) {
                            alt3=1;
                        }


                    }


                    }
                    break;
                case IDENT:
                    {
                    int LA3_4 = input.LA(2);

                    if ( (LA3_4==IDENT) ) {
                        int LA3_6 = input.LA(3);

                        if ( (LA3_6==27) ) {
                            alt3=1;
                        }


                    }


                    }
                    break;

                }

                switch (alt3) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:54:54: v= varDeclaration
            	    {
            	    pushFollow(FOLLOW_varDeclaration_in_classDeclaration200);
            	    v=varDeclaration();

            	    state._fsp--;

            	    vdl.add(v);

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:54:92: (m= methodDeclaration )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==IDENT||(LA4_0>=16 && LA4_0<=18)||(LA4_0>=28 && LA4_0<=29)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:54:93: m= methodDeclaration
            	    {
            	    pushFollow(FOLLOW_methodDeclaration_in_classDeclaration214);
            	    m=methodDeclaration();

            	    state._fsp--;

            	    mdl.add(m);

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            match(input,26,FOLLOW_26_in_classDeclaration220); 

                c = new MJClass((cname!=null?cname.getText():null),(sc!=null?sc.getText():null),vdl,mdl);
              

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return c;
    }
    // $ANTLR end "classDeclaration"


    // $ANTLR start "block"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:59:1: block returns [MJBlock b] : '{' (vd= varDeclaration )* (sd= statement )* '}' ;
    public final MJBlock block() throws RecognitionException {
        MJBlock b = null;

        MJVariable vd = null;

        MJStatement sd = null;


        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:60:5: ( '{' (vd= varDeclaration )* (sd= statement )* '}' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:61:5: '{' (vd= varDeclaration )* (sd= statement )* '}'
            {
              LinkedList<MJVariable> vdl = new LinkedList<MJVariable>();
                   LinkedList<MJStatement> sdl = new LinkedList<MJStatement>();
                   
                
            match(input,15,FOLLOW_15_in_block255); 
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:65:9: (vd= varDeclaration )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==IDENT) ) {
                    int LA5_2 = input.LA(2);

                    if ( (LA5_2==IDENT) ) {
                        alt5=1;
                    }


                }
                else if ( ((LA5_0>=28 && LA5_0<=29)) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:65:11: vd= varDeclaration
            	    {
            	    pushFollow(FOLLOW_varDeclaration_in_block263);
            	    vd=varDeclaration();

            	    state._fsp--;

            	     vdl.add(vd); 

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:67:8: (sd= statement )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==IDENT||LA6_0==15||LA6_0==32||LA6_0==34||LA6_0==36) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:67:10: sd= statement
            	    {
            	    pushFollow(FOLLOW_statement_in_block285);
            	    sd=statement();

            	    state._fsp--;

            	     sdl.add(sd); 

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            match(input,26,FOLLOW_26_in_block305); 

                   b = new MJBlock(vdl, sdl);
                

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return b;
    }
    // $ANTLR end "block"


    // $ANTLR start "varDeclaration"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:80:1: varDeclaration returns [MJVariable vd] : t= type n= IDENT ';' ;
    public final MJVariable varDeclaration() throws RecognitionException {
        MJVariable vd = null;

        Token n=null;
        MJType t = null;


        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:81:3: (t= type n= IDENT ';' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:81:5: t= type n= IDENT ';'
            {
            pushFollow(FOLLOW_type_in_varDeclaration347);
            t=type();

            state._fsp--;

            n=(Token)match(input,IDENT,FOLLOW_IDENT_in_varDeclaration353); 
            match(input,27,FOLLOW_27_in_varDeclaration355); 

                vd = new MJVariable(t,n.getText());
              

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return vd;
    }
    // $ANTLR end "varDeclaration"


    // $ANTLR start "type"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:87:1: type returns [MJType t] : ( 'int' | 'boolean' | 'int' '[' ']' | IDENT );
    public final MJType type() throws RecognitionException {
        MJType t = null;

        Token IDENT1=null;

        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:88:3: ( 'int' | 'boolean' | 'int' '[' ']' | IDENT )
            int alt7=4;
            switch ( input.LA(1) ) {
            case 28:
                {
                int LA7_1 = input.LA(2);

                if ( (LA7_1==22) ) {
                    alt7=3;
                }
                else if ( (LA7_1==IDENT) ) {
                    alt7=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 1, input);

                    throw nvae;
                }
                }
                break;
            case 29:
                {
                alt7=2;
                }
                break;
            case IDENT:
                {
                alt7=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:88:5: 'int'
                    {
                    match(input,28,FOLLOW_28_in_type377); 
                     t = MJType.Tint; 

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:90:5: 'boolean'
                    {
                    match(input,29,FOLLOW_29_in_type388); 
                     t = MJType.Tboolean; 

                    }
                    break;
                case 3 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:92:5: 'int' '[' ']'
                    {
                    match(input,28,FOLLOW_28_in_type398); 
                    match(input,22,FOLLOW_22_in_type400); 
                    match(input,23,FOLLOW_23_in_type402); 
                    t = MJType.TintArray;

                    }
                    break;
                case 4 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:94:5: IDENT
                    {
                    IDENT1=(Token)match(input,IDENT,FOLLOW_IDENT_in_type413); 

                        t = new MJType((IDENT1!=null?IDENT1.getText():null));
                      

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return t;
    }
    // $ANTLR end "type"


    // $ANTLR start "methodDeclaration"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:100:1: methodDeclaration returns [MJMethod m] : ( 'public' )? ( 'static' )? procType IDENT '(' ( type IDENT ( ',' type IDENT )* )? ')' '{' ( varDeclaration )* ( statement )* 'return' optExpression ';' '}' ;
    public final MJMethod methodDeclaration() throws RecognitionException {
        MJMethod m = null;

        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:101:3: ( ( 'public' )? ( 'static' )? procType IDENT '(' ( type IDENT ( ',' type IDENT )* )? ')' '{' ( varDeclaration )* ( statement )* 'return' optExpression ';' '}' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:101:5: ( 'public' )? ( 'static' )? procType IDENT '(' ( type IDENT ( ',' type IDENT )* )? ')' '{' ( varDeclaration )* ( statement )* 'return' optExpression ';' '}'
            {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:101:5: ( 'public' )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==16) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:101:6: 'public'
                    {
                    match(input,16,FOLLOW_16_in_methodDeclaration439); 

                    }
                    break;

            }

            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:101:17: ( 'static' )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==17) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:101:18: 'static'
                    {
                    match(input,17,FOLLOW_17_in_methodDeclaration444); 

                    }
                    break;

            }

            pushFollow(FOLLOW_procType_in_methodDeclaration448);
            procType();

            state._fsp--;

            match(input,IDENT,FOLLOW_IDENT_in_methodDeclaration450); 
            match(input,20,FOLLOW_20_in_methodDeclaration452); 
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:101:48: ( type IDENT ( ',' type IDENT )* )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==IDENT||(LA11_0>=28 && LA11_0<=29)) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:101:49: type IDENT ( ',' type IDENT )*
                    {
                    pushFollow(FOLLOW_type_in_methodDeclaration455);
                    type();

                    state._fsp--;

                    match(input,IDENT,FOLLOW_IDENT_in_methodDeclaration457); 
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:101:59: ( ',' type IDENT )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==30) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:101:60: ',' type IDENT
                    	    {
                    	    match(input,30,FOLLOW_30_in_methodDeclaration459); 
                    	    pushFollow(FOLLOW_type_in_methodDeclaration461);
                    	    type();

                    	    state._fsp--;

                    	    match(input,IDENT,FOLLOW_IDENT_in_methodDeclaration463); 

                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);


                    }
                    break;

            }

            match(input,24,FOLLOW_24_in_methodDeclaration469); 
            match(input,15,FOLLOW_15_in_methodDeclaration471); 
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:102:19: ( varDeclaration )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==IDENT) ) {
                    int LA12_2 = input.LA(2);

                    if ( (LA12_2==IDENT) ) {
                        alt12=1;
                    }


                }
                else if ( ((LA12_0>=28 && LA12_0<=29)) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:102:20: varDeclaration
            	    {
            	    pushFollow(FOLLOW_varDeclaration_in_methodDeclaration492);
            	    varDeclaration();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:102:37: ( statement )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==IDENT||LA13_0==15||LA13_0==32||LA13_0==34||LA13_0==36) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:102:38: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_methodDeclaration497);
            	    statement();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            match(input,31,FOLLOW_31_in_methodDeclaration502); 
            pushFollow(FOLLOW_optExpression_in_methodDeclaration504);
            optExpression();

            state._fsp--;

            match(input,27,FOLLOW_27_in_methodDeclaration506); 
            match(input,26,FOLLOW_26_in_methodDeclaration508); 

              

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return m;
    }
    // $ANTLR end "methodDeclaration"


    // $ANTLR start "procType"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:107:1: procType returns [MJType t] : ( 'void' | type );
    public final MJType procType() throws RecognitionException {
        MJType t = null;

        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:108:3: ( 'void' | type )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==18) ) {
                alt14=1;
            }
            else if ( (LA14_0==IDENT||(LA14_0>=28 && LA14_0<=29)) ) {
                alt14=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:108:5: 'void'
                    {
                    match(input,18,FOLLOW_18_in_procType532); 
                     t = MJType.Tvoid; 

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:110:5: type
                    {
                    pushFollow(FOLLOW_type_in_procType543);
                    type();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return t;
    }
    // $ANTLR end "procType"


    // $ANTLR start "statement"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:117:1: statement returns [MJStatement s] : ( 'System.out.println' '(' ep= expression ')' ';' | va= IDENT '=' ea= expression ';' | b= block | 'if' '(' cond= expression ')' (ifblock= block ) ( 'else' elseblock= block )? | 'while' '(' expr= expression ')' stmt= statement | IDENT '(' ( expression ( ',' expression )* )? ')' ';' );
    public final MJStatement statement() throws RecognitionException {
        MJStatement s = null;

        Token va=null;
        MJExpression ep = null;

        MJExpression ea = null;

        MJBlock b = null;

        MJExpression cond = null;

        MJBlock ifblock = null;

        MJBlock elseblock = null;

        MJExpression expr = null;

        MJStatement stmt = null;


        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:118:3: ( 'System.out.println' '(' ep= expression ')' ';' | va= IDENT '=' ea= expression ';' | b= block | 'if' '(' cond= expression ')' (ifblock= block ) ( 'else' elseblock= block )? | 'while' '(' expr= expression ')' stmt= statement | IDENT '(' ( expression ( ',' expression )* )? ')' ';' )
            int alt18=6;
            switch ( input.LA(1) ) {
            case 32:
                {
                alt18=1;
                }
                break;
            case IDENT:
                {
                int LA18_2 = input.LA(2);

                if ( (LA18_2==33) ) {
                    alt18=2;
                }
                else if ( (LA18_2==20) ) {
                    alt18=6;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 18, 2, input);

                    throw nvae;
                }
                }
                break;
            case 15:
                {
                alt18=3;
                }
                break;
            case 34:
                {
                alt18=4;
                }
                break;
            case 36:
                {
                alt18=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:118:5: 'System.out.println' '(' ep= expression ')' ';'
                    {
                    match(input,32,FOLLOW_32_in_statement572); 
                    match(input,20,FOLLOW_20_in_statement574); 
                    pushFollow(FOLLOW_expression_in_statement580);
                    ep=expression();

                    state._fsp--;

                    match(input,24,FOLLOW_24_in_statement582); 
                    match(input,27,FOLLOW_27_in_statement584); 

                        s = new MJPrintln(ep);
                      

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:122:5: va= IDENT '=' ea= expression ';'
                    {
                    va=(Token)match(input,IDENT,FOLLOW_IDENT_in_statement599); 
                    match(input,33,FOLLOW_33_in_statement601); 
                    pushFollow(FOLLOW_expression_in_statement607);
                    ea=expression();

                    state._fsp--;

                    match(input,27,FOLLOW_27_in_statement609); 

                        s = new MJAssign(new MJIdentifier((va!=null?va.getText():null)), ea);
                      

                    }
                    break;
                case 3 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:126:5: b= block
                    {
                    pushFollow(FOLLOW_block_in_statement624);
                    b=block();

                    state._fsp--;

                     
                        s = b;
                      

                    }
                    break;
                case 4 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:130:5: 'if' '(' cond= expression ')' (ifblock= block ) ( 'else' elseblock= block )?
                    {
                    match(input,34,FOLLOW_34_in_statement637); 
                    match(input,20,FOLLOW_20_in_statement639); 
                    pushFollow(FOLLOW_expression_in_statement645);
                    cond=expression();

                    state._fsp--;

                    match(input,24,FOLLOW_24_in_statement647); 
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:130:36: (ifblock= block )
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:130:37: ifblock= block
                    {
                    pushFollow(FOLLOW_block_in_statement654);
                    ifblock=block();

                    state._fsp--;


                    }

                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:130:54: ( 'else' elseblock= block )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0==35) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:130:55: 'else' elseblock= block
                            {
                            match(input,35,FOLLOW_35_in_statement658); 
                            pushFollow(FOLLOW_block_in_statement664);
                            elseblock=block();

                            state._fsp--;


                            }
                            break;

                    }


                        //TODO - figure out what happens when no else block i present
                        s = new MJIfElse(cond,ifblock,elseblock);
                      

                    }
                    break;
                case 5 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:135:5: 'while' '(' expr= expression ')' stmt= statement
                    {
                    match(input,36,FOLLOW_36_in_statement677); 
                    match(input,20,FOLLOW_20_in_statement679); 
                    pushFollow(FOLLOW_expression_in_statement685);
                    expr=expression();

                    state._fsp--;

                    match(input,24,FOLLOW_24_in_statement687); 
                    pushFollow(FOLLOW_statement_in_statement693);
                    stmt=statement();

                    state._fsp--;


                        s = new MJWhile(expr,stmt);
                      

                    }
                    break;
                case 6 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:139:5: IDENT '(' ( expression ( ',' expression )* )? ')' ';'
                    {
                    match(input,IDENT,FOLLOW_IDENT_in_statement704); 
                    match(input,20,FOLLOW_20_in_statement706); 
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:139:15: ( expression ( ',' expression )* )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( ((LA17_0>=IDENT && LA17_0<=STRING)||LA17_0==20||LA17_0==41||(LA17_0>=43 && LA17_0<=45)||LA17_0==47) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:139:16: expression ( ',' expression )*
                            {
                            pushFollow(FOLLOW_expression_in_statement709);
                            expression();

                            state._fsp--;

                            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:139:27: ( ',' expression )*
                            loop16:
                            do {
                                int alt16=2;
                                int LA16_0 = input.LA(1);

                                if ( (LA16_0==30) ) {
                                    alt16=1;
                                }


                                switch (alt16) {
                            	case 1 :
                            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:139:29: ',' expression
                            	    {
                            	    match(input,30,FOLLOW_30_in_statement713); 
                            	    pushFollow(FOLLOW_expression_in_statement715);
                            	    expression();

                            	    state._fsp--;


                            	    }
                            	    break;

                            	default :
                            	    break loop16;
                                }
                            } while (true);


                            }
                            break;

                    }

                    match(input,24,FOLLOW_24_in_statement721); 
                    match(input,27,FOLLOW_27_in_statement723); 

                        //TODO
                      

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return s;
    }
    // $ANTLR end "statement"


    // $ANTLR start "expression"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:150:1: expression returns [MJExpression e] : level1 ( '&&' level1 )* ;
    public final MJExpression expression() throws RecognitionException {
        MJExpression e = null;

        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:151:3: ( level1 ( '&&' level1 )* )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:151:5: level1 ( '&&' level1 )*
            {
            pushFollow(FOLLOW_level1_in_expression760);
            level1();

            state._fsp--;

            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:151:12: ( '&&' level1 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==37) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:151:13: '&&' level1
            	    {
            	    match(input,37,FOLLOW_37_in_expression763); 
            	    pushFollow(FOLLOW_level1_in_expression765);
            	    level1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

            e = new MJAnd();

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return e;
    }
    // $ANTLR end "expression"


    // $ANTLR start "level1"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:156:1: level1 returns [MJExpression e] : level2 ( '==' level2 )* ;
    public final MJExpression level1() throws RecognitionException {
        MJExpression e = null;

        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:157:3: ( level2 ( '==' level2 )* )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:157:5: level2 ( '==' level2 )*
            {
            pushFollow(FOLLOW_level2_in_level1790);
            level2();

            state._fsp--;

            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:157:12: ( '==' level2 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==38) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:157:13: '==' level2
            	    {
            	    match(input,38,FOLLOW_38_in_level1793); 
            	    pushFollow(FOLLOW_level2_in_level1795);
            	    level2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);



            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return e;
    }
    // $ANTLR end "level1"


    // $ANTLR start "level2"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:162:1: level2 returns [MJExpression e] : level3 ( '<' level3 )* ;
    public final MJExpression level2() throws RecognitionException {
        MJExpression e = null;

        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:163:3: ( level3 ( '<' level3 )* )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:163:5: level3 ( '<' level3 )*
            {
            pushFollow(FOLLOW_level3_in_level2822);
            level3();

            state._fsp--;

            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:163:12: ( '<' level3 )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==39) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:163:13: '<' level3
            	    {
            	    match(input,39,FOLLOW_39_in_level2825); 
            	    pushFollow(FOLLOW_level3_in_level2827);
            	    level3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);



            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return e;
    }
    // $ANTLR end "level2"


    // $ANTLR start "level3"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:168:1: level3 returns [MJExpression e] : level4 ( ( '+' | '-' ) level4 )* ;
    public final MJExpression level3() throws RecognitionException {
        MJExpression e = null;

        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:169:3: ( level4 ( ( '+' | '-' ) level4 )* )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:169:5: level4 ( ( '+' | '-' ) level4 )*
            {
            pushFollow(FOLLOW_level4_in_level3852);
            level4();

            state._fsp--;

            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:169:12: ( ( '+' | '-' ) level4 )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( ((LA22_0>=40 && LA22_0<=41)) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:169:13: ( '+' | '-' ) level4
            	    {
            	    if ( (input.LA(1)>=40 && input.LA(1)<=41) ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_level4_in_level3863);
            	    level4();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);



            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return e;
    }
    // $ANTLR end "level3"


    // $ANTLR start "level4"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:174:1: level4 returns [MJExpression e] : level5 ( '*' level5 )* ;
    public final MJExpression level4() throws RecognitionException {
        MJExpression e = null;

        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:175:3: ( level5 ( '*' level5 )* )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:175:5: level5 ( '*' level5 )*
            {
            pushFollow(FOLLOW_level5_in_level4888);
            level5();

            state._fsp--;

            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:175:12: ( '*' level5 )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==42) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:175:13: '*' level5
            	    {
            	    match(input,42,FOLLOW_42_in_level4891); 
            	    pushFollow(FOLLOW_level5_in_level4893);
            	    level5();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);



            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return e;
    }
    // $ANTLR end "level4"


    // $ANTLR start "level5"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:180:1: level5 returns [MJExpression e] : ( '-' level5 | 'new' 'int' '[' ex= expression ']' | 'new' IDENT '(' ')' | i= id | id '[' expression ']' | i= id '(' (expr= expression ( ',' expr= expression )* )? ')' | '(' expr= expression ')' | 'true' | 'false' | INT | STRING );
    public final MJExpression level5() throws RecognitionException {
        MJExpression e = null;

        Token INT2=null;
        Token STRING3=null;
        MJExpression ex = null;

        MJIdentifier i = null;

        MJExpression expr = null;


        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:182:3: ( '-' level5 | 'new' 'int' '[' ex= expression ']' | 'new' IDENT '(' ')' | i= id | id '[' expression ']' | i= id '(' (expr= expression ( ',' expr= expression )* )? ')' | '(' expr= expression ')' | 'true' | 'false' | INT | STRING )
            int alt26=11;
            alt26 = dfa26.predict(input);
            switch (alt26) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:182:5: '-' level5
                    {
                    match(input,41,FOLLOW_41_in_level5921); 
                    pushFollow(FOLLOW_level5_in_level5923);
                    level5();

                    state._fsp--;

                    //TODO
                      

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:186:5: 'new' 'int' '[' ex= expression ']'
                    {
                    match(input,43,FOLLOW_43_in_level5937); 
                    match(input,28,FOLLOW_28_in_level5939); 
                    match(input,22,FOLLOW_22_in_level5941); 
                    pushFollow(FOLLOW_expression_in_level5947);
                    ex=expression();

                    state._fsp--;

                    match(input,23,FOLLOW_23_in_level5949); 

                        // TODO - figure out this one, should it be MJType or MJIdent ?
                        //e = new MJArray(MJType.TintArray,ex);
                      

                    }
                    break;
                case 3 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:192:5: 'new' IDENT '(' ')'
                    {
                    match(input,43,FOLLOW_43_in_level5963); 
                    match(input,IDENT,FOLLOW_IDENT_in_level5965); 
                    match(input,20,FOLLOW_20_in_level5967); 
                    match(input,24,FOLLOW_24_in_level5969); 
                    //TODO - new mjobject ?
                      

                    }
                    break;
                case 4 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:196:5: i= id
                    {
                    pushFollow(FOLLOW_id_in_level5987);
                    i=id();

                    state._fsp--;


                       //TODO - What is the effect of this ?
                        e = i;
                      

                    }
                    break;
                case 5 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:202:5: id '[' expression ']'
                    {
                    pushFollow(FOLLOW_id_in_level51001);
                    id();

                    state._fsp--;

                    match(input,22,FOLLOW_22_in_level51003); 
                    pushFollow(FOLLOW_expression_in_level51005);
                    expression();

                    state._fsp--;

                    match(input,23,FOLLOW_23_in_level51007); 

                        //TODO
                      

                    }
                    break;
                case 6 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:208:3: i= id '(' (expr= expression ( ',' expr= expression )* )? ')'
                    {
                     LinkedList<MJExpression> plist = new LinkedList<MJExpression>(); 
                    pushFollow(FOLLOW_id_in_level51035);
                    i=id();

                    state._fsp--;

                    match(input,20,FOLLOW_20_in_level51037); 
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:209:16: (expr= expression ( ',' expr= expression )* )?
                    int alt25=2;
                    int LA25_0 = input.LA(1);

                    if ( ((LA25_0>=IDENT && LA25_0<=STRING)||LA25_0==20||LA25_0==41||(LA25_0>=43 && LA25_0<=45)||LA25_0==47) ) {
                        alt25=1;
                    }
                    switch (alt25) {
                        case 1 :
                            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:209:17: expr= expression ( ',' expr= expression )*
                            {
                            pushFollow(FOLLOW_expression_in_level51044);
                            expr=expression();

                            state._fsp--;

                            plist.add(expr);
                            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:209:54: ( ',' expr= expression )*
                            loop24:
                            do {
                                int alt24=2;
                                int LA24_0 = input.LA(1);

                                if ( (LA24_0==30) ) {
                                    alt24=1;
                                }


                                switch (alt24) {
                            	case 1 :
                            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:209:55: ',' expr= expression
                            	    {
                            	    match(input,30,FOLLOW_30_in_level51049); 
                            	    pushFollow(FOLLOW_expression_in_level51054);
                            	    expr=expression();

                            	    state._fsp--;


                            	    }
                            	    break;

                            	default :
                            	    break loop24;
                                }
                            } while (true);

                            plist.add(expr);

                            }
                            break;

                    }

                    match(input,24,FOLLOW_24_in_level51063); 

                        e = new MJMethodCallExpr(i,plist);
                      

                    }
                    break;
                case 7 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:214:5: '(' expr= expression ')'
                    {
                    match(input,20,FOLLOW_20_in_level51077); 
                    pushFollow(FOLLOW_expression_in_level51083);
                    expr=expression();

                    state._fsp--;

                    match(input,24,FOLLOW_24_in_level51085); 
                     e = expr; 

                    }
                    break;
                case 8 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:217:5: 'true'
                    {
                    match(input,44,FOLLOW_44_in_level51099); 

                        e = new MJBoolean(true);
                      

                    }
                    break;
                case 9 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:222:5: 'false'
                    {
                    match(input,45,FOLLOW_45_in_level51114); 

                        e = new MJBoolean(false);
                      

                    }
                    break;
                case 10 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:227:5: INT
                    {
                    INT2=(Token)match(input,INT,FOLLOW_INT_in_level51130); 
                     e = new MJInteger((INT2!=null?INT2.getText():null)); 

                    }
                    break;
                case 11 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:231:5: STRING
                    {
                    STRING3=(Token)match(input,STRING,FOLLOW_STRING_in_level51148); 
                     e = new MJString((STRING3!=null?STRING3.getText():null)); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return e;
    }
    // $ANTLR end "level5"


    // $ANTLR start "id"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:240:1: id returns [MJIdentifier i] : (t= thisid | t= thisid '.' IDENT );
    public final MJIdentifier id() throws RecognitionException {
        MJIdentifier i = null;

        Token IDENT4=null;
        MJIdentifier t = null;


        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:241:3: (t= thisid | t= thisid '.' IDENT )
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==47) ) {
                int LA27_1 = input.LA(2);

                if ( (LA27_1==20||(LA27_1>=22 && LA27_1<=24)||LA27_1==27||LA27_1==30||(LA27_1>=37 && LA27_1<=42)) ) {
                    alt27=1;
                }
                else if ( (LA27_1==46) ) {
                    alt27=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 27, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA27_0==IDENT) ) {
                int LA27_2 = input.LA(2);

                if ( (LA27_2==20||(LA27_2>=22 && LA27_2<=24)||LA27_2==27||LA27_2==30||(LA27_2>=37 && LA27_2<=42)) ) {
                    alt27=1;
                }
                else if ( (LA27_2==46) ) {
                    alt27=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 27, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }
            switch (alt27) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:241:5: t= thisid
                    {
                    pushFollow(FOLLOW_thisid_in_id1178);
                    t=thisid();

                    state._fsp--;


                        i = t;
                      

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:245:5: t= thisid '.' IDENT
                    {
                    pushFollow(FOLLOW_thisid_in_id1190);
                    t=thisid();

                    state._fsp--;

                    match(input,46,FOLLOW_46_in_id1192); 
                    IDENT4=(Token)match(input,IDENT,FOLLOW_IDENT_in_id1194); 

                       
                       MJSelector mjs = new MJSelector();
                       mjs.setName((IDENT4!=null?IDENT4.getText():null));
                       mjs.setParent(t);
                       i = mjs;
                      

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return i;
    }
    // $ANTLR end "id"


    // $ANTLR start "thisid"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:255:1: thisid returns [MJIdentifier ti] : ( 'this' | IDENT );
    public final MJIdentifier thisid() throws RecognitionException {
        MJIdentifier ti = null;

        Token IDENT5=null;

        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:256:3: ( 'this' | IDENT )
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==47) ) {
                alt28=1;
            }
            else if ( (LA28_0==IDENT) ) {
                alt28=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }
            switch (alt28) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:256:5: 'this'
                    {
                    match(input,47,FOLLOW_47_in_thisid1219); 

                        ti =  new MJIdentifier("this");
                      

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:261:3: IDENT
                    {
                    IDENT5=(Token)match(input,IDENT,FOLLOW_IDENT_in_thisid1232); 

                        ti = new MJIdentifier((IDENT5!=null?IDENT5.getText():null));
                      

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ti;
    }
    // $ANTLR end "thisid"


    // $ANTLR start "optExpression"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:267:1: optExpression returns [MJExpression e] : ( expression | );
    public final MJExpression optExpression() throws RecognitionException {
        MJExpression e = null;

        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:268:3: ( expression | )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( ((LA29_0>=IDENT && LA29_0<=STRING)||LA29_0==20||LA29_0==41||(LA29_0>=43 && LA29_0<=45)||LA29_0==47) ) {
                alt29=1;
            }
            else if ( (LA29_0==27) ) {
                alt29=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }
            switch (alt29) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:268:5: expression
                    {
                    pushFollow(FOLLOW_expression_in_optExpression1256);
                    expression();

                    state._fsp--;



                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:270:3: 
                    {


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return e;
    }
    // $ANTLR end "optExpression"

    // Delegated rules


    protected DFA26 dfa26 = new DFA26(this);
    static final String DFA26_eotS =
        "\21\uffff";
    static final String DFA26_eofS =
        "\21\uffff";
    static final String DFA26_minS =
        "\1\4\1\uffff\1\4\2\24\7\uffff\1\4\3\uffff\1\24";
    static final String DFA26_maxS =
        "\1\57\1\uffff\1\34\2\56\7\uffff\1\4\3\uffff\1\52";
    static final String DFA26_acceptS =
        "\1\uffff\1\1\3\uffff\1\7\1\10\1\11\1\12\1\13\1\2\1\3\1\uffff\1\4"+
        "\1\6\1\5\1\uffff";
    static final String DFA26_specialS =
        "\21\uffff}>";
    static final String[] DFA26_transitionS = {
            "\1\4\1\10\1\11\15\uffff\1\5\24\uffff\1\1\1\uffff\1\2\1\6\1\7"+
            "\1\uffff\1\3",
            "",
            "\1\13\27\uffff\1\12",
            "\1\16\1\uffff\1\17\2\15\2\uffff\1\15\2\uffff\1\15\6\uffff\6"+
            "\15\3\uffff\1\14",
            "\1\16\1\uffff\1\17\2\15\2\uffff\1\15\2\uffff\1\15\6\uffff\6"+
            "\15\3\uffff\1\14",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\20",
            "",
            "",
            "",
            "\1\16\1\uffff\1\17\2\15\2\uffff\1\15\2\uffff\1\15\6\uffff\6"+
            "\15"
    };

    static final short[] DFA26_eot = DFA.unpackEncodedString(DFA26_eotS);
    static final short[] DFA26_eof = DFA.unpackEncodedString(DFA26_eofS);
    static final char[] DFA26_min = DFA.unpackEncodedStringToUnsignedChars(DFA26_minS);
    static final char[] DFA26_max = DFA.unpackEncodedStringToUnsignedChars(DFA26_maxS);
    static final short[] DFA26_accept = DFA.unpackEncodedString(DFA26_acceptS);
    static final short[] DFA26_special = DFA.unpackEncodedString(DFA26_specialS);
    static final short[][] DFA26_transition;

    static {
        int numStates = DFA26_transitionS.length;
        DFA26_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA26_transition[i] = DFA.unpackEncodedString(DFA26_transitionS[i]);
        }
    }

    class DFA26 extends DFA {

        public DFA26(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 26;
            this.eot = DFA26_eot;
            this.eof = DFA26_eof;
            this.min = DFA26_min;
            this.max = DFA26_max;
            this.accept = DFA26_accept;
            this.special = DFA26_special;
            this.transition = DFA26_transition;
        }
        public String getDescription() {
            return "180:1: level5 returns [MJExpression e] : ( '-' level5 | 'new' 'int' '[' ex= expression ']' | 'new' IDENT '(' ')' | i= id | id '[' expression ']' | i= id '(' (expr= expression ( ',' expr= expression )* )? ')' | '(' expr= expression ')' | 'true' | 'false' | INT | STRING );";
        }
    }
 

    public static final BitSet FOLLOW_mainClass_in_program59 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_classDeclaration_in_program70 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_14_in_mainClass100 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_mainClass106 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_mainClass108 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_mainClass115 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_mainClass117 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_mainClass119 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_mainClass121 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_mainClass123 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_mainClass125 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_mainClass127 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_mainClass129 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_mainClass135 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_mainClass137 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_block_in_mainClass143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_classDeclaration174 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_classDeclaration180 = new BitSet(new long[]{0x0000000002008000L});
    public static final BitSet FOLLOW_25_in_classDeclaration183 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_classDeclaration189 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_classDeclaration193 = new BitSet(new long[]{0x0000000034070010L});
    public static final BitSet FOLLOW_varDeclaration_in_classDeclaration200 = new BitSet(new long[]{0x0000000034070010L});
    public static final BitSet FOLLOW_methodDeclaration_in_classDeclaration214 = new BitSet(new long[]{0x0000000034070010L});
    public static final BitSet FOLLOW_26_in_classDeclaration220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_block255 = new BitSet(new long[]{0x0000001534008010L});
    public static final BitSet FOLLOW_varDeclaration_in_block263 = new BitSet(new long[]{0x0000001534008010L});
    public static final BitSet FOLLOW_statement_in_block285 = new BitSet(new long[]{0x0000001504008010L});
    public static final BitSet FOLLOW_26_in_block305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_varDeclaration347 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_varDeclaration353 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_varDeclaration355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_type377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_type388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_type398 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_type400 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_type402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_type413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_methodDeclaration439 = new BitSet(new long[]{0x0000000030070010L});
    public static final BitSet FOLLOW_17_in_methodDeclaration444 = new BitSet(new long[]{0x0000000030070010L});
    public static final BitSet FOLLOW_procType_in_methodDeclaration448 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_methodDeclaration450 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_methodDeclaration452 = new BitSet(new long[]{0x0000000031000010L});
    public static final BitSet FOLLOW_type_in_methodDeclaration455 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_methodDeclaration457 = new BitSet(new long[]{0x0000000041000000L});
    public static final BitSet FOLLOW_30_in_methodDeclaration459 = new BitSet(new long[]{0x0000000030000010L});
    public static final BitSet FOLLOW_type_in_methodDeclaration461 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_methodDeclaration463 = new BitSet(new long[]{0x0000000041000000L});
    public static final BitSet FOLLOW_24_in_methodDeclaration469 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_methodDeclaration471 = new BitSet(new long[]{0x00000015B0008010L});
    public static final BitSet FOLLOW_varDeclaration_in_methodDeclaration492 = new BitSet(new long[]{0x00000015B0008010L});
    public static final BitSet FOLLOW_statement_in_methodDeclaration497 = new BitSet(new long[]{0x0000001580008010L});
    public static final BitSet FOLLOW_31_in_methodDeclaration502 = new BitSet(new long[]{0x0000BA0008100070L});
    public static final BitSet FOLLOW_optExpression_in_methodDeclaration504 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_methodDeclaration506 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_methodDeclaration508 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_procType532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_procType543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_statement572 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_statement574 = new BitSet(new long[]{0x0000BA0000100070L});
    public static final BitSet FOLLOW_expression_in_statement580 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_statement582 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_statement584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_statement599 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_statement601 = new BitSet(new long[]{0x0000BA0000100070L});
    public static final BitSet FOLLOW_expression_in_statement607 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_statement609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_statement624 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_statement637 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_statement639 = new BitSet(new long[]{0x0000BA0000100070L});
    public static final BitSet FOLLOW_expression_in_statement645 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_statement647 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_block_in_statement654 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_35_in_statement658 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_block_in_statement664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_statement677 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_statement679 = new BitSet(new long[]{0x0000BA0000100070L});
    public static final BitSet FOLLOW_expression_in_statement685 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_statement687 = new BitSet(new long[]{0x0000001500008010L});
    public static final BitSet FOLLOW_statement_in_statement693 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_statement704 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_statement706 = new BitSet(new long[]{0x0000BA0001100070L});
    public static final BitSet FOLLOW_expression_in_statement709 = new BitSet(new long[]{0x0000000041000000L});
    public static final BitSet FOLLOW_30_in_statement713 = new BitSet(new long[]{0x0000BA0000100070L});
    public static final BitSet FOLLOW_expression_in_statement715 = new BitSet(new long[]{0x0000000041000000L});
    public static final BitSet FOLLOW_24_in_statement721 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_statement723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_level1_in_expression760 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_37_in_expression763 = new BitSet(new long[]{0x0000BA0000100070L});
    public static final BitSet FOLLOW_level1_in_expression765 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_level2_in_level1790 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_38_in_level1793 = new BitSet(new long[]{0x0000BA0000100070L});
    public static final BitSet FOLLOW_level2_in_level1795 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_level3_in_level2822 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_39_in_level2825 = new BitSet(new long[]{0x0000BA0000100070L});
    public static final BitSet FOLLOW_level3_in_level2827 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_level4_in_level3852 = new BitSet(new long[]{0x0000030000000002L});
    public static final BitSet FOLLOW_set_in_level3855 = new BitSet(new long[]{0x0000BA0000100070L});
    public static final BitSet FOLLOW_level4_in_level3863 = new BitSet(new long[]{0x0000030000000002L});
    public static final BitSet FOLLOW_level5_in_level4888 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_42_in_level4891 = new BitSet(new long[]{0x0000BA0000100070L});
    public static final BitSet FOLLOW_level5_in_level4893 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_41_in_level5921 = new BitSet(new long[]{0x0000BA0000100070L});
    public static final BitSet FOLLOW_level5_in_level5923 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_level5937 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_level5939 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_level5941 = new BitSet(new long[]{0x0000BA0000100070L});
    public static final BitSet FOLLOW_expression_in_level5947 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_level5949 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_level5963 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_level5965 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_level5967 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_level5969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_level5987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_level51001 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_level51003 = new BitSet(new long[]{0x0000BA0000100070L});
    public static final BitSet FOLLOW_expression_in_level51005 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_level51007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_level51035 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_level51037 = new BitSet(new long[]{0x0000BA0001100070L});
    public static final BitSet FOLLOW_expression_in_level51044 = new BitSet(new long[]{0x0000000041000000L});
    public static final BitSet FOLLOW_30_in_level51049 = new BitSet(new long[]{0x0000BA0000100070L});
    public static final BitSet FOLLOW_expression_in_level51054 = new BitSet(new long[]{0x0000000041000000L});
    public static final BitSet FOLLOW_24_in_level51063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_level51077 = new BitSet(new long[]{0x0000BA0000100070L});
    public static final BitSet FOLLOW_expression_in_level51083 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_level51085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_level51099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_level51114 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_level51130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_level51148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_thisid_in_id1178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_thisid_in_id1190 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_46_in_id1192 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_id1194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_thisid1219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_thisid1232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_optExpression1256 = new BitSet(new long[]{0x0000000000000002L});

}