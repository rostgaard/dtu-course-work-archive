// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g 2010-10-05 17:05:10

  package compiler.Frontend;
  
  import java.util.LinkedList;
  import compiler.IR.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class MiniJavaParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "IDENT", "INT", "STRING", "LOWER", "UPPER", "NONNULL", "NUMBER", "NEWLINE", "WHITESPACE", "'class'", "'{'", "'public'", "'static'", "'void'", "'main'", "'('", "'String'", "'['", "']'", "')'", "'extends'", "'}'", "','", "'return'", "';'", "'.'", "'this'", "'int'", "'boolean'", "'System.out.println'", "'='", "'if'", "'else'", "'while'", "'&&'", "'=='", "'<'", "'+'", "'-'", "'*'", "'new'"
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
    public static final int T__13=13;
    public static final int IDENT=4;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__44=44;
    public static final int NUMBER=10;
    public static final int WHITESPACE=12;
    public static final int INT=5;
    public static final int NONNULL=9;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int NEWLINE=11;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int LOWER=7;
    public static final int UPPER=8;
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
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:17:1: program returns [MJProgram p] : mc= mainClass (cd= classDeclaration )* ;
    public final MJProgram program() throws RecognitionException {
        MJProgram p = null;

        MJClass mc = null;

        MJClass cd = null;


        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:17:30: (mc= mainClass (cd= classDeclaration )* )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:18:3: mc= mainClass (cd= classDeclaration )*
            {
             LinkedList<MJClass> cdl = new LinkedList<MJClass>(); 
            pushFollow(FOLLOW_mainClass_in_program49);
            mc=mainClass();

            state._fsp--;

             cdl.add(mc); p = new MJProgram(cdl); 
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:20:3: (cd= classDeclaration )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==13) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:20:4: cd= classDeclaration
            	    {
            	    pushFollow(FOLLOW_classDeclaration_in_program60);
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
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:25:1: mainClass returns [MJClass c] : 'class' cname= IDENT '{' 'public' 'static' 'void' 'main' '(' 'String' '[' ']' parname= IDENT ')' b= block ;
    public final MJClass mainClass() throws RecognitionException {
        MJClass c = null;

        Token cname=null;
        Token parname=null;
        MJBlock b = null;


        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:26:3: ( 'class' cname= IDENT '{' 'public' 'static' 'void' 'main' '(' 'String' '[' ']' parname= IDENT ')' b= block )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:26:5: 'class' cname= IDENT '{' 'public' 'static' 'void' 'main' '(' 'String' '[' ']' parname= IDENT ')' b= block
            {
            match(input,13,FOLLOW_13_in_mainClass87); 
            cname=(Token)match(input,IDENT,FOLLOW_IDENT_in_mainClass93); 
            match(input,14,FOLLOW_14_in_mainClass95); 
            match(input,15,FOLLOW_15_in_mainClass102); 
            match(input,16,FOLLOW_16_in_mainClass104); 
            match(input,17,FOLLOW_17_in_mainClass106); 
            match(input,18,FOLLOW_18_in_mainClass108); 
            match(input,19,FOLLOW_19_in_mainClass110); 
            match(input,20,FOLLOW_20_in_mainClass112); 
            match(input,21,FOLLOW_21_in_mainClass114); 
            match(input,22,FOLLOW_22_in_mainClass116); 
            parname=(Token)match(input,IDENT,FOLLOW_IDENT_in_mainClass122); 
            match(input,23,FOLLOW_23_in_mainClass124); 
            pushFollow(FOLLOW_block_in_mainClass130);
            b=block();

            state._fsp--;


                MJType partype = new MJType(new MJType("String"));
                MJVariable par = new MJVariable( partype, (parname!=null?parname.getText():null));
                 
                MJMethod md = new MJMethod(MJType.Tnone, "main", par, b, true, true);
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
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:37:1: classDeclaration returns [MJClass c] : 'class' cname= IDENT ( 'extends' sc= IDENT )? '{' (v= varDeclaration )* (m= methodDeclaration )* '}' ;
    public final MJClass classDeclaration() throws RecognitionException {
        MJClass c = null;

        Token cname=null;
        Token sc=null;
        MJVariable v = null;

        MJMethod m = null;


        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:38:3: ( 'class' cname= IDENT ( 'extends' sc= IDENT )? '{' (v= varDeclaration )* (m= methodDeclaration )* '}' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:39:3: 'class' cname= IDENT ( 'extends' sc= IDENT )? '{' (v= varDeclaration )* (m= methodDeclaration )* '}'
            {
             
                LinkedList<MJVariable> vdl = new LinkedList<MJVariable>();
                LinkedList<MJMethod> mdl = new LinkedList<MJMethod>();
              
            match(input,13,FOLLOW_13_in_classDeclaration161); 
            cname=(Token)match(input,IDENT,FOLLOW_IDENT_in_classDeclaration167); 
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:43:25: ( 'extends' sc= IDENT )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==24) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:43:26: 'extends' sc= IDENT
                    {
                    match(input,24,FOLLOW_24_in_classDeclaration170); 
                    sc=(Token)match(input,IDENT,FOLLOW_IDENT_in_classDeclaration176); 

                    }
                    break;

            }

            match(input,14,FOLLOW_14_in_classDeclaration180); 
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:43:53: (v= varDeclaration )*
            loop3:
            do {
                int alt3=2;
                switch ( input.LA(1) ) {
                case 31:
                    {
                    int LA3_2 = input.LA(2);

                    if ( (LA3_2==21) ) {
                        int LA3_5 = input.LA(3);

                        if ( (LA3_5==22) ) {
                            int LA3_7 = input.LA(4);

                            if ( (LA3_7==IDENT) ) {
                                int LA3_6 = input.LA(5);

                                if ( (LA3_6==28) ) {
                                    alt3=1;
                                }


                            }


                        }


                    }
                    else if ( (LA3_2==IDENT) ) {
                        int LA3_6 = input.LA(3);

                        if ( (LA3_6==28) ) {
                            alt3=1;
                        }


                    }


                    }
                    break;
                case 32:
                    {
                    int LA3_3 = input.LA(2);

                    if ( (LA3_3==IDENT) ) {
                        int LA3_6 = input.LA(3);

                        if ( (LA3_6==28) ) {
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

                        if ( (LA3_6==28) ) {
                            alt3=1;
                        }


                    }


                    }
                    break;

                }

                switch (alt3) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:43:54: v= varDeclaration
            	    {
            	    pushFollow(FOLLOW_varDeclaration_in_classDeclaration187);
            	    v=varDeclaration();

            	    state._fsp--;

            	    vdl.add(v);

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:43:92: (m= methodDeclaration )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==IDENT||(LA4_0>=15 && LA4_0<=17)||(LA4_0>=31 && LA4_0<=32)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:43:93: m= methodDeclaration
            	    {
            	    pushFollow(FOLLOW_methodDeclaration_in_classDeclaration201);
            	    m=methodDeclaration();

            	    state._fsp--;

            	    mdl.add(m);

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            match(input,25,FOLLOW_25_in_classDeclaration207); 

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


    // $ANTLR start "methodDeclaration"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:48:1: methodDeclaration returns [MJMethod m] : ( 'public' )? ( 'static' )? procType IDENT '(' ( type IDENT ( ',' type IDENT )* )? ')' '{' ( varDeclaration )* ( statement )* 'return' optExpression ';' '}' ;
    public final MJMethod methodDeclaration() throws RecognitionException {
        MJMethod m = null;

        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:49:3: ( ( 'public' )? ( 'static' )? procType IDENT '(' ( type IDENT ( ',' type IDENT )* )? ')' '{' ( varDeclaration )* ( statement )* 'return' optExpression ';' '}' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:49:5: ( 'public' )? ( 'static' )? procType IDENT '(' ( type IDENT ( ',' type IDENT )* )? ')' '{' ( varDeclaration )* ( statement )* 'return' optExpression ';' '}'
            {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:49:5: ( 'public' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==15) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:49:6: 'public'
                    {
                    match(input,15,FOLLOW_15_in_methodDeclaration229); 

                    }
                    break;

            }

            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:49:17: ( 'static' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==16) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:49:18: 'static'
                    {
                    match(input,16,FOLLOW_16_in_methodDeclaration234); 

                    }
                    break;

            }

            pushFollow(FOLLOW_procType_in_methodDeclaration238);
            procType();

            state._fsp--;

            match(input,IDENT,FOLLOW_IDENT_in_methodDeclaration240); 
            match(input,19,FOLLOW_19_in_methodDeclaration242); 
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:49:48: ( type IDENT ( ',' type IDENT )* )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==IDENT||(LA8_0>=31 && LA8_0<=32)) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:49:49: type IDENT ( ',' type IDENT )*
                    {
                    pushFollow(FOLLOW_type_in_methodDeclaration245);
                    type();

                    state._fsp--;

                    match(input,IDENT,FOLLOW_IDENT_in_methodDeclaration247); 
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:49:59: ( ',' type IDENT )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==26) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:49:60: ',' type IDENT
                    	    {
                    	    match(input,26,FOLLOW_26_in_methodDeclaration249); 
                    	    pushFollow(FOLLOW_type_in_methodDeclaration251);
                    	    type();

                    	    state._fsp--;

                    	    match(input,IDENT,FOLLOW_IDENT_in_methodDeclaration253); 

                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);


                    }
                    break;

            }

            match(input,23,FOLLOW_23_in_methodDeclaration259); 
            match(input,14,FOLLOW_14_in_methodDeclaration261); 
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:50:19: ( varDeclaration )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==IDENT) ) {
                    int LA9_2 = input.LA(2);

                    if ( (LA9_2==IDENT) ) {
                        alt9=1;
                    }


                }
                else if ( ((LA9_0>=31 && LA9_0<=32)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:50:20: varDeclaration
            	    {
            	    pushFollow(FOLLOW_varDeclaration_in_methodDeclaration282);
            	    varDeclaration();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:50:37: ( statement )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==IDENT||LA10_0==14||LA10_0==33||LA10_0==35||LA10_0==37) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:50:38: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_methodDeclaration287);
            	    statement();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            match(input,27,FOLLOW_27_in_methodDeclaration292); 
            pushFollow(FOLLOW_optExpression_in_methodDeclaration294);
            optExpression();

            state._fsp--;

            match(input,28,FOLLOW_28_in_methodDeclaration296); 
            match(input,25,FOLLOW_25_in_methodDeclaration298); 

              

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


    // $ANTLR start "varDeclaration"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:55:1: varDeclaration returns [MJVariable vd] : t= type n= IDENT ';' ;
    public final MJVariable varDeclaration() throws RecognitionException {
        MJVariable vd = null;

        Token n=null;
        MJType t = null;


        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:56:3: (t= type n= IDENT ';' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:56:5: t= type n= IDENT ';'
            {
            pushFollow(FOLLOW_type_in_varDeclaration326);
            t=type();

            state._fsp--;

            n=(Token)match(input,IDENT,FOLLOW_IDENT_in_varDeclaration332); 
            match(input,28,FOLLOW_28_in_varDeclaration334); 

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


    // $ANTLR start "optExpression"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:62:1: optExpression returns [MJExpression e] : ( expression | );
    public final MJExpression optExpression() throws RecognitionException {
        MJExpression e = null;

        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:63:3: ( expression | )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( ((LA11_0>=IDENT && LA11_0<=STRING)||LA11_0==19||LA11_0==30||LA11_0==42||LA11_0==44) ) {
                alt11=1;
            }
            else if ( (LA11_0==28) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:63:5: expression
                    {
                    pushFollow(FOLLOW_expression_in_optExpression357);
                    expression();

                    state._fsp--;



                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:65:3: 
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


    // $ANTLR start "id"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:68:1: id returns [MJIdentifier i] : (t= thisid | t= thisid '.' IDENT );
    public final MJIdentifier id() throws RecognitionException {
        MJIdentifier i = null;

        Token IDENT1=null;
        MJIdentifier t = null;


        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:69:3: (t= thisid | t= thisid '.' IDENT )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==30) ) {
                int LA12_1 = input.LA(2);

                if ( (LA12_1==19||(LA12_1>=21 && LA12_1<=23)||LA12_1==26||LA12_1==28||(LA12_1>=38 && LA12_1<=43)) ) {
                    alt12=1;
                }
                else if ( (LA12_1==29) ) {
                    alt12=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA12_0==IDENT) ) {
                int LA12_2 = input.LA(2);

                if ( (LA12_2==29) ) {
                    alt12=2;
                }
                else if ( (LA12_2==19||(LA12_2>=21 && LA12_2<=23)||LA12_2==26||LA12_2==28||(LA12_2>=38 && LA12_2<=43)) ) {
                    alt12=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:69:5: t= thisid
                    {
                    pushFollow(FOLLOW_thisid_in_id389);
                    t=thisid();

                    state._fsp--;


                        i = t;
                      

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:73:5: t= thisid '.' IDENT
                    {
                    pushFollow(FOLLOW_thisid_in_id401);
                    t=thisid();

                    state._fsp--;

                    match(input,29,FOLLOW_29_in_id403); 
                    IDENT1=(Token)match(input,IDENT,FOLLOW_IDENT_in_id405); 

                       i = new MJSelector();
                       i.setName((IDENT1!=null?IDENT1.getText():null));
                       i.setParent(t);
                      

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
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:81:1: thisid returns [MJIdentifier ti] : ( 'this' | IDENT );
    public final MJIdentifier thisid() throws RecognitionException {
        MJIdentifier ti = null;

        Token IDENT2=null;

        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:82:3: ( 'this' | IDENT )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==30) ) {
                alt13=1;
            }
            else if ( (LA13_0==IDENT) ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:82:5: 'this'
                    {
                    match(input,30,FOLLOW_30_in_thisid429); 

                        ti =  new MJIdentifier("this");
                      

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:87:3: IDENT
                    {
                    IDENT2=(Token)match(input,IDENT,FOLLOW_IDENT_in_thisid442); 

                        ti = new MJIdentifier((IDENT2!=null?IDENT2.getText():null));
                      

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


    // $ANTLR start "procType"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:94:1: procType returns [MJType t] : ( 'void' | type );
    public final MJType procType() throws RecognitionException {
        MJType t = null;

        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:95:3: ( 'void' | type )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==17) ) {
                alt14=1;
            }
            else if ( (LA14_0==IDENT||(LA14_0>=31 && LA14_0<=32)) ) {
                alt14=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:95:5: 'void'
                    {
                    match(input,17,FOLLOW_17_in_procType465); 
                     t = MJType.Tvoid; 

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:97:5: type
                    {
                    pushFollow(FOLLOW_type_in_procType476);
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


    // $ANTLR start "type"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:100:1: type returns [MJType t] : ( 'int' | 'boolean' | 'int' '[' ']' | IDENT );
    public final MJType type() throws RecognitionException {
        MJType t = null;

        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:101:3: ( 'int' | 'boolean' | 'int' '[' ']' | IDENT )
            int alt15=4;
            switch ( input.LA(1) ) {
            case 31:
                {
                int LA15_1 = input.LA(2);

                if ( (LA15_1==21) ) {
                    alt15=3;
                }
                else if ( (LA15_1==IDENT) ) {
                    alt15=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 15, 1, input);

                    throw nvae;
                }
                }
                break;
            case 32:
                {
                alt15=2;
                }
                break;
            case IDENT:
                {
                alt15=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:101:5: 'int'
                    {
                    match(input,31,FOLLOW_31_in_type495); 
                     t = MJType.Tint; 

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:103:5: 'boolean'
                    {
                    match(input,32,FOLLOW_32_in_type506); 
                     t = MJType.Tboolean; 

                    }
                    break;
                case 3 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:105:5: 'int' '[' ']'
                    {
                    match(input,31,FOLLOW_31_in_type516); 
                    match(input,21,FOLLOW_21_in_type518); 
                    match(input,22,FOLLOW_22_in_type520); 
                    t = MJType.TintArray

                    }
                    break;
                case 4 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:107:5: IDENT
                    {
                    match(input,IDENT,FOLLOW_IDENT_in_type531); 
                    t = IDENT

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


    // $ANTLR start "block"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:111:1: block returns [MJBlock b] : '{' (vd= varDeclaration )* (sd= statement )* '}' ;
    public final MJBlock block() throws RecognitionException {
        MJBlock b = null;

        MJVariable vd = null;

        MJStatement sd = null;


        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:112:5: ( '{' (vd= varDeclaration )* (sd= statement )* '}' )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:113:5: '{' (vd= varDeclaration )* (sd= statement )* '}'
            {
              LinkedList<MJVariable> vdl = new LinkedList<MJVariable>();
                   LinkedList<MJStatement> sdl = new LinkedList<MJStatement>();
                   
                
            match(input,14,FOLLOW_14_in_block569); 
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:117:9: (vd= varDeclaration )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==IDENT) ) {
                    int LA16_2 = input.LA(2);

                    if ( (LA16_2==IDENT) ) {
                        alt16=1;
                    }


                }
                else if ( ((LA16_0>=31 && LA16_0<=32)) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:117:11: vd= varDeclaration
            	    {
            	    pushFollow(FOLLOW_varDeclaration_in_block577);
            	    vd=varDeclaration();

            	    state._fsp--;

            	     vdl.add(vd); 

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:119:8: (sd= statement )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==IDENT||LA17_0==14||LA17_0==33||LA17_0==35||LA17_0==37) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:119:10: sd= statement
            	    {
            	    pushFollow(FOLLOW_statement_in_block599);
            	    sd=statement();

            	    state._fsp--;

            	     sdl.add(sd); 

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

            match(input,25,FOLLOW_25_in_block619); 

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


    // $ANTLR start "statement"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:128:1: statement returns [MJStatement s] : ( 'System.out.println' '(' ep= expression ')' ';' | va= IDENT '=' ea= expression ';' | b= block | 'if' '(' expression ')' block ( 'else' block )? | 'while' '(' expression ')' statement | IDENT '(' ( expression ( ',' expression )* )? ')' ';' );
    public final MJStatement statement() throws RecognitionException {
        MJStatement s = null;

        Token va=null;
        MJExpression ep = null;

        MJExpression ea = null;

        MJBlock b = null;


        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:129:3: ( 'System.out.println' '(' ep= expression ')' ';' | va= IDENT '=' ea= expression ';' | b= block | 'if' '(' expression ')' block ( 'else' block )? | 'while' '(' expression ')' statement | IDENT '(' ( expression ( ',' expression )* )? ')' ';' )
            int alt21=6;
            switch ( input.LA(1) ) {
            case 33:
                {
                alt21=1;
                }
                break;
            case IDENT:
                {
                int LA21_2 = input.LA(2);

                if ( (LA21_2==34) ) {
                    alt21=2;
                }
                else if ( (LA21_2==19) ) {
                    alt21=6;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 21, 2, input);

                    throw nvae;
                }
                }
                break;
            case 14:
                {
                alt21=3;
                }
                break;
            case 35:
                {
                alt21=4;
                }
                break;
            case 37:
                {
                alt21=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }

            switch (alt21) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:129:5: 'System.out.println' '(' ep= expression ')' ';'
                    {
                    match(input,33,FOLLOW_33_in_statement648); 
                    match(input,19,FOLLOW_19_in_statement650); 
                    pushFollow(FOLLOW_expression_in_statement656);
                    ep=expression();

                    state._fsp--;

                    match(input,23,FOLLOW_23_in_statement658); 
                    match(input,28,FOLLOW_28_in_statement660); 

                        s = new MJPrintln(ep);
                      

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:133:5: va= IDENT '=' ea= expression ';'
                    {
                    va=(Token)match(input,IDENT,FOLLOW_IDENT_in_statement675); 
                    match(input,34,FOLLOW_34_in_statement677); 
                    pushFollow(FOLLOW_expression_in_statement683);
                    ea=expression();

                    state._fsp--;

                    match(input,28,FOLLOW_28_in_statement685); 

                        s = new MJAssign(new MJIdentifier((va!=null?va.getText():null)), ea);
                      

                    }
                    break;
                case 3 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:137:5: b= block
                    {
                    pushFollow(FOLLOW_block_in_statement700);
                    b=block();

                    state._fsp--;

                     
                        s = b
                      

                    }
                    break;
                case 4 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:141:5: 'if' '(' expression ')' block ( 'else' block )?
                    {
                    match(input,35,FOLLOW_35_in_statement713); 
                    match(input,19,FOLLOW_19_in_statement715); 
                    pushFollow(FOLLOW_expression_in_statement717);
                    expression();

                    state._fsp--;

                    match(input,23,FOLLOW_23_in_statement719); 
                    pushFollow(FOLLOW_block_in_statement721);
                    block();

                    state._fsp--;

                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:141:35: ( 'else' block )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==36) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:141:36: 'else' block
                            {
                            match(input,36,FOLLOW_36_in_statement724); 
                            pushFollow(FOLLOW_block_in_statement726);
                            block();

                            state._fsp--;


                            }
                            break;

                    }


                        //TODO
                      

                    }
                    break;
                case 5 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:145:5: 'while' '(' expression ')' statement
                    {
                    match(input,37,FOLLOW_37_in_statement739); 
                    match(input,19,FOLLOW_19_in_statement741); 
                    pushFollow(FOLLOW_expression_in_statement743);
                    expression();

                    state._fsp--;

                    match(input,23,FOLLOW_23_in_statement745); 
                    pushFollow(FOLLOW_statement_in_statement747);
                    statement();

                    state._fsp--;


                        //TODO
                      

                    }
                    break;
                case 6 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:149:5: IDENT '(' ( expression ( ',' expression )* )? ')' ';'
                    {
                    match(input,IDENT,FOLLOW_IDENT_in_statement758); 
                    match(input,19,FOLLOW_19_in_statement760); 
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:149:15: ( expression ( ',' expression )* )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( ((LA20_0>=IDENT && LA20_0<=STRING)||LA20_0==19||LA20_0==30||LA20_0==42||LA20_0==44) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:149:16: expression ( ',' expression )*
                            {
                            pushFollow(FOLLOW_expression_in_statement763);
                            expression();

                            state._fsp--;

                            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:149:27: ( ',' expression )*
                            loop19:
                            do {
                                int alt19=2;
                                int LA19_0 = input.LA(1);

                                if ( (LA19_0==26) ) {
                                    alt19=1;
                                }


                                switch (alt19) {
                            	case 1 :
                            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:149:29: ',' expression
                            	    {
                            	    match(input,26,FOLLOW_26_in_statement767); 
                            	    pushFollow(FOLLOW_expression_in_statement769);
                            	    expression();

                            	    state._fsp--;


                            	    }
                            	    break;

                            	default :
                            	    break loop19;
                                }
                            } while (true);


                            }
                            break;

                    }

                    match(input,23,FOLLOW_23_in_statement775); 
                    match(input,28,FOLLOW_28_in_statement777); 

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
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:156:1: expression returns [MJExpression e] : level1 ( '&&' level1 )* ;
    public final MJExpression expression() throws RecognitionException {
        MJExpression e = null;

        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:157:3: ( level1 ( '&&' level1 )* )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:157:5: level1 ( '&&' level1 )*
            {
            pushFollow(FOLLOW_level1_in_expression804);
            level1();

            state._fsp--;

            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:157:12: ( '&&' level1 )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==38) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:157:13: '&&' level1
            	    {
            	    match(input,38,FOLLOW_38_in_expression807); 
            	    pushFollow(FOLLOW_level1_in_expression809);
            	    level1();

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
    // $ANTLR end "expression"


    // $ANTLR start "level1"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:161:1: level1 returns [MJExpression e] : level2 ( '==' level2 )* ;
    public final MJExpression level1() throws RecognitionException {
        MJExpression e = null;

        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:162:3: ( level2 ( '==' level2 )* )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:162:5: level2 ( '==' level2 )*
            {
            pushFollow(FOLLOW_level2_in_level1832);
            level2();

            state._fsp--;

            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:162:12: ( '==' level2 )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==39) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:162:13: '==' level2
            	    {
            	    match(input,39,FOLLOW_39_in_level1835); 
            	    pushFollow(FOLLOW_level2_in_level1837);
            	    level2();

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
    // $ANTLR end "level1"


    // $ANTLR start "level2"
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:166:1: level2 returns [MJExpression e] : level3 ( '<' level3 )* ;
    public final MJExpression level2() throws RecognitionException {
        MJExpression e = null;

        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:167:3: ( level3 ( '<' level3 )* )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:167:5: level3 ( '<' level3 )*
            {
            pushFollow(FOLLOW_level3_in_level2862);
            level3();

            state._fsp--;

            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:167:12: ( '<' level3 )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==40) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:167:13: '<' level3
            	    {
            	    match(input,40,FOLLOW_40_in_level2865); 
            	    pushFollow(FOLLOW_level3_in_level2867);
            	    level3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop24;
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
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:171:1: level3 returns [MJExpression e] : level4 ( ( '+' | '-' ) level4 )* ;
    public final MJExpression level3() throws RecognitionException {
        MJExpression e = null;

        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:172:3: ( level4 ( ( '+' | '-' ) level4 )* )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:172:5: level4 ( ( '+' | '-' ) level4 )*
            {
            pushFollow(FOLLOW_level4_in_level3890);
            level4();

            state._fsp--;

            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:172:12: ( ( '+' | '-' ) level4 )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( ((LA25_0>=41 && LA25_0<=42)) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:172:13: ( '+' | '-' ) level4
            	    {
            	    if ( (input.LA(1)>=41 && input.LA(1)<=42) ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_level4_in_level3901);
            	    level4();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop25;
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
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:177:1: level4 returns [MJExpression e] : level5 ( '*' level5 )* ;
    public final MJExpression level4() throws RecognitionException {
        MJExpression e = null;

        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:178:3: ( level5 ( '*' level5 )* )
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:178:5: level5 ( '*' level5 )*
            {
            pushFollow(FOLLOW_level5_in_level4925);
            level5();

            state._fsp--;

            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:178:12: ( '*' level5 )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==43) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:178:13: '*' level5
            	    {
            	    match(input,43,FOLLOW_43_in_level4928); 
            	    pushFollow(FOLLOW_level5_in_level4930);
            	    level5();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop26;
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
    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:182:1: level5 returns [MJExpression e] : ( '-' level5 | 'new' 'int' '[' ex= expression ']' | 'new' IDENT '(' ')' | id | id '[' expression ']' | id '(' ( expression ( ',' expression )* )? ')' | '(' e1= expression ')' | INT | STRING );
    public final MJExpression level5() throws RecognitionException {
        MJExpression e = null;

        Token INT3=null;
        Token STRING4=null;
        MJExpression ex = null;

        MJExpression e1 = null;


        try {
            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:183:3: ( '-' level5 | 'new' 'int' '[' ex= expression ']' | 'new' IDENT '(' ')' | id | id '[' expression ']' | id '(' ( expression ( ',' expression )* )? ')' | '(' e1= expression ')' | INT | STRING )
            int alt29=9;
            alt29 = dfa29.predict(input);
            switch (alt29) {
                case 1 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:183:5: '-' level5
                    {
                    match(input,42,FOLLOW_42_in_level5953); 
                    pushFollow(FOLLOW_level5_in_level5955);
                    level5();

                    state._fsp--;

                    //TODO
                      

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:186:5: 'new' 'int' '[' ex= expression ']'
                    {
                    match(input,44,FOLLOW_44_in_level5965); 
                    match(input,31,FOLLOW_31_in_level5967); 
                    match(input,21,FOLLOW_21_in_level5969); 
                    pushFollow(FOLLOW_expression_in_level5975);
                    ex=expression();

                    state._fsp--;

                    match(input,22,FOLLOW_22_in_level5977); 
                    //TODO
                      

                    }
                    break;
                case 3 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:189:5: 'new' IDENT '(' ')'
                    {
                    match(input,44,FOLLOW_44_in_level5987); 
                    match(input,IDENT,FOLLOW_IDENT_in_level5989); 
                    match(input,19,FOLLOW_19_in_level5991); 
                    match(input,23,FOLLOW_23_in_level5993); 
                    //TODO
                      

                    }
                    break;
                case 4 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:192:5: id
                    {
                    pushFollow(FOLLOW_id_in_level51003);
                    id();

                    state._fsp--;

                    //TODO
                      

                    }
                    break;
                case 5 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:195:5: id '[' expression ']'
                    {
                    pushFollow(FOLLOW_id_in_level51013);
                    id();

                    state._fsp--;

                    match(input,21,FOLLOW_21_in_level51015); 
                    pushFollow(FOLLOW_expression_in_level51017);
                    expression();

                    state._fsp--;

                    match(input,22,FOLLOW_22_in_level51019); 


                    }
                    break;
                case 6 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:197:5: id '(' ( expression ( ',' expression )* )? ')'
                    {
                    pushFollow(FOLLOW_id_in_level51029);
                    id();

                    state._fsp--;

                    match(input,19,FOLLOW_19_in_level51031); 
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:197:12: ( expression ( ',' expression )* )?
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( ((LA28_0>=IDENT && LA28_0<=STRING)||LA28_0==19||LA28_0==30||LA28_0==42||LA28_0==44) ) {
                        alt28=1;
                    }
                    switch (alt28) {
                        case 1 :
                            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:197:13: expression ( ',' expression )*
                            {
                            pushFollow(FOLLOW_expression_in_level51034);
                            expression();

                            state._fsp--;

                            // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:197:24: ( ',' expression )*
                            loop27:
                            do {
                                int alt27=2;
                                int LA27_0 = input.LA(1);

                                if ( (LA27_0==26) ) {
                                    alt27=1;
                                }


                                switch (alt27) {
                            	case 1 :
                            	    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:197:25: ',' expression
                            	    {
                            	    match(input,26,FOLLOW_26_in_level51037); 
                            	    pushFollow(FOLLOW_expression_in_level51039);
                            	    expression();

                            	    state._fsp--;


                            	    }
                            	    break;

                            	default :
                            	    break loop27;
                                }
                            } while (true);


                            }
                            break;

                    }

                    match(input,23,FOLLOW_23_in_level51045); 


                    }
                    break;
                case 7 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:199:5: '(' e1= expression ')'
                    {
                    match(input,19,FOLLOW_19_in_level51055); 
                    pushFollow(FOLLOW_expression_in_level51061);
                    e1=expression();

                    state._fsp--;

                    match(input,23,FOLLOW_23_in_level51063); 
                     e = e1; 

                    }
                    break;
                case 8 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:201:5: INT
                    {
                    INT3=(Token)match(input,INT,FOLLOW_INT_in_level51073); 
                     e = new MJInteger((INT3!=null?INT3.getText():null)); 

                    }
                    break;
                case 9 :
                    // /home/krc/workspace/MiniJava/src/compiler/Frontend/MiniJava.g:203:5: STRING
                    {
                    STRING4=(Token)match(input,STRING,FOLLOW_STRING_in_level51084); 
                     e = new MJString((STRING4!=null?STRING4.getText():null) 

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

    // Delegated rules


    protected DFA29 dfa29 = new DFA29(this);
    static final String DFA29_eotS =
        "\17\uffff";
    static final String DFA29_eofS =
        "\17\uffff";
    static final String DFA29_minS =
        "\1\4\1\uffff\1\4\2\23\5\uffff\1\4\3\uffff\1\23";
    static final String DFA29_maxS =
        "\1\54\1\uffff\1\37\2\53\5\uffff\1\4\3\uffff\1\53";
    static final String DFA29_acceptS =
        "\1\uffff\1\1\3\uffff\1\7\1\10\1\11\1\2\1\3\1\uffff\1\5\1\6\1\4\1"+
        "\uffff";
    static final String DFA29_specialS =
        "\17\uffff}>";
    static final String[] DFA29_transitionS = {
            "\1\4\1\6\1\7\14\uffff\1\5\12\uffff\1\3\13\uffff\1\1\1\uffff"+
            "\1\2",
            "",
            "\1\11\32\uffff\1\10",
            "\1\14\1\uffff\1\13\2\15\2\uffff\1\15\1\uffff\1\15\1\12\10\uffff"+
            "\6\15",
            "\1\14\1\uffff\1\13\2\15\2\uffff\1\15\1\uffff\1\15\1\12\10\uffff"+
            "\6\15",
            "",
            "",
            "",
            "",
            "",
            "\1\16",
            "",
            "",
            "",
            "\1\14\1\uffff\1\13\2\15\2\uffff\1\15\1\uffff\1\15\11\uffff"+
            "\6\15"
    };

    static final short[] DFA29_eot = DFA.unpackEncodedString(DFA29_eotS);
    static final short[] DFA29_eof = DFA.unpackEncodedString(DFA29_eofS);
    static final char[] DFA29_min = DFA.unpackEncodedStringToUnsignedChars(DFA29_minS);
    static final char[] DFA29_max = DFA.unpackEncodedStringToUnsignedChars(DFA29_maxS);
    static final short[] DFA29_accept = DFA.unpackEncodedString(DFA29_acceptS);
    static final short[] DFA29_special = DFA.unpackEncodedString(DFA29_specialS);
    static final short[][] DFA29_transition;

    static {
        int numStates = DFA29_transitionS.length;
        DFA29_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA29_transition[i] = DFA.unpackEncodedString(DFA29_transitionS[i]);
        }
    }

    class DFA29 extends DFA {

        public DFA29(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 29;
            this.eot = DFA29_eot;
            this.eof = DFA29_eof;
            this.min = DFA29_min;
            this.max = DFA29_max;
            this.accept = DFA29_accept;
            this.special = DFA29_special;
            this.transition = DFA29_transition;
        }
        public String getDescription() {
            return "182:1: level5 returns [MJExpression e] : ( '-' level5 | 'new' 'int' '[' ex= expression ']' | 'new' IDENT '(' ')' | id | id '[' expression ']' | id '(' ( expression ( ',' expression )* )? ')' | '(' e1= expression ')' | INT | STRING );";
        }
    }
 

    public static final BitSet FOLLOW_mainClass_in_program49 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_classDeclaration_in_program60 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_13_in_mainClass87 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_mainClass93 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_mainClass95 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_mainClass102 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_mainClass104 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_mainClass106 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_mainClass108 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_mainClass110 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_mainClass112 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_mainClass114 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_mainClass116 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_mainClass122 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_mainClass124 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_block_in_mainClass130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_classDeclaration161 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_classDeclaration167 = new BitSet(new long[]{0x0000000001004000L});
    public static final BitSet FOLLOW_24_in_classDeclaration170 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_classDeclaration176 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_classDeclaration180 = new BitSet(new long[]{0x0000000182038010L});
    public static final BitSet FOLLOW_varDeclaration_in_classDeclaration187 = new BitSet(new long[]{0x0000000182038010L});
    public static final BitSet FOLLOW_methodDeclaration_in_classDeclaration201 = new BitSet(new long[]{0x0000000182038010L});
    public static final BitSet FOLLOW_25_in_classDeclaration207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_methodDeclaration229 = new BitSet(new long[]{0x0000000180038010L});
    public static final BitSet FOLLOW_16_in_methodDeclaration234 = new BitSet(new long[]{0x0000000180038010L});
    public static final BitSet FOLLOW_procType_in_methodDeclaration238 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_methodDeclaration240 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_methodDeclaration242 = new BitSet(new long[]{0x0000000180800010L});
    public static final BitSet FOLLOW_type_in_methodDeclaration245 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_methodDeclaration247 = new BitSet(new long[]{0x0000000004800000L});
    public static final BitSet FOLLOW_26_in_methodDeclaration249 = new BitSet(new long[]{0x0000000180000010L});
    public static final BitSet FOLLOW_type_in_methodDeclaration251 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_methodDeclaration253 = new BitSet(new long[]{0x0000000004800000L});
    public static final BitSet FOLLOW_23_in_methodDeclaration259 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_methodDeclaration261 = new BitSet(new long[]{0x0000002B88004010L});
    public static final BitSet FOLLOW_varDeclaration_in_methodDeclaration282 = new BitSet(new long[]{0x0000002B88004010L});
    public static final BitSet FOLLOW_statement_in_methodDeclaration287 = new BitSet(new long[]{0x0000002A08004010L});
    public static final BitSet FOLLOW_27_in_methodDeclaration292 = new BitSet(new long[]{0x0000140050080070L});
    public static final BitSet FOLLOW_optExpression_in_methodDeclaration294 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_methodDeclaration296 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_methodDeclaration298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_varDeclaration326 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_varDeclaration332 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_varDeclaration334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_optExpression357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_thisid_in_id389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_thisid_in_id401 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_id403 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_id405 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_thisid429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_thisid442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_procType465 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_procType476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_type495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_type506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_type516 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_type518 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_type520 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_type531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_block569 = new BitSet(new long[]{0x0000002B82004010L});
    public static final BitSet FOLLOW_varDeclaration_in_block577 = new BitSet(new long[]{0x0000002B82004010L});
    public static final BitSet FOLLOW_statement_in_block599 = new BitSet(new long[]{0x0000002A02004010L});
    public static final BitSet FOLLOW_25_in_block619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_statement648 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_statement650 = new BitSet(new long[]{0x0000140040080070L});
    public static final BitSet FOLLOW_expression_in_statement656 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_statement658 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_statement660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_statement675 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_statement677 = new BitSet(new long[]{0x0000140040080070L});
    public static final BitSet FOLLOW_expression_in_statement683 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_statement685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_statement700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_statement713 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_statement715 = new BitSet(new long[]{0x0000140040080070L});
    public static final BitSet FOLLOW_expression_in_statement717 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_statement719 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_block_in_statement721 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_36_in_statement724 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_block_in_statement726 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_statement739 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_statement741 = new BitSet(new long[]{0x0000140040080070L});
    public static final BitSet FOLLOW_expression_in_statement743 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_statement745 = new BitSet(new long[]{0x0000002A00004010L});
    public static final BitSet FOLLOW_statement_in_statement747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_statement758 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_statement760 = new BitSet(new long[]{0x0000140040880070L});
    public static final BitSet FOLLOW_expression_in_statement763 = new BitSet(new long[]{0x0000000004800000L});
    public static final BitSet FOLLOW_26_in_statement767 = new BitSet(new long[]{0x0000140040080070L});
    public static final BitSet FOLLOW_expression_in_statement769 = new BitSet(new long[]{0x0000000004800000L});
    public static final BitSet FOLLOW_23_in_statement775 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_statement777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_level1_in_expression804 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_38_in_expression807 = new BitSet(new long[]{0x0000140040080070L});
    public static final BitSet FOLLOW_level1_in_expression809 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_level2_in_level1832 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_39_in_level1835 = new BitSet(new long[]{0x0000140040080070L});
    public static final BitSet FOLLOW_level2_in_level1837 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_level3_in_level2862 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_40_in_level2865 = new BitSet(new long[]{0x0000140040080070L});
    public static final BitSet FOLLOW_level3_in_level2867 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_level4_in_level3890 = new BitSet(new long[]{0x0000060000000002L});
    public static final BitSet FOLLOW_set_in_level3893 = new BitSet(new long[]{0x0000140040080070L});
    public static final BitSet FOLLOW_level4_in_level3901 = new BitSet(new long[]{0x0000060000000002L});
    public static final BitSet FOLLOW_level5_in_level4925 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_43_in_level4928 = new BitSet(new long[]{0x0000140040080070L});
    public static final BitSet FOLLOW_level5_in_level4930 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_42_in_level5953 = new BitSet(new long[]{0x0000140040080070L});
    public static final BitSet FOLLOW_level5_in_level5955 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_level5965 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_level5967 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_level5969 = new BitSet(new long[]{0x0000140040080070L});
    public static final BitSet FOLLOW_expression_in_level5975 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_level5977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_level5987 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_level5989 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_level5991 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_level5993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_level51003 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_level51013 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_level51015 = new BitSet(new long[]{0x0000140040080070L});
    public static final BitSet FOLLOW_expression_in_level51017 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_level51019 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_level51029 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_level51031 = new BitSet(new long[]{0x0000140040880070L});
    public static final BitSet FOLLOW_expression_in_level51034 = new BitSet(new long[]{0x0000000004800000L});
    public static final BitSet FOLLOW_26_in_level51037 = new BitSet(new long[]{0x0000140040080070L});
    public static final BitSet FOLLOW_expression_in_level51039 = new BitSet(new long[]{0x0000000004800000L});
    public static final BitSet FOLLOW_23_in_level51045 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_level51055 = new BitSet(new long[]{0x0000140040080070L});
    public static final BitSet FOLLOW_expression_in_level51061 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_level51063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_level51073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_level51084 = new BitSet(new long[]{0x0000000000000002L});

}