// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g 2010-11-07 20:30:59

  package compiler.Frontend;
  
  import java.util.LinkedList;
  import compiler.IR.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class MiniJavaParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "IDENT", "COMMENT", "INT", "STRING", "LOWER", "UPPER", "NONNULL", "NUMBER", "NEWLINE", "CHAR", "WHITESPACE", "'class'", "'extends'", "'{'", "'}'", "'public'", "'static'", "'void'", "'main'", "'('", "'String[]'", "')'", "'='", "';'", "'boolean'", "'int'", "'['", "']'", "','", "'return'", "'if'", "'else'", "'while'", "'System.out.println'", "'&&'", "'=='", "'<'", "'+'", "'-'", "'*'", "'!'", "'new'", "'true'", "'false'", "'.'", "'this'"
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


        public MiniJavaParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public MiniJavaParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return MiniJavaParser.tokenNames; }
    public String getGrammarFileName() { return "/home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g"; }





    // $ANTLR start "program"
    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:17:1: program returns [MJProgram p] : mc= mainClass (cd= classDeclaration )* ;
    public final MJProgram program() throws RecognitionException {
        MJProgram p = null;

        MJClass mc = null;

        MJClass cd = null;


        try {
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:18:3: (mc= mainClass (cd= classDeclaration )* )
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:18:5: mc= mainClass (cd= classDeclaration )*
            {
             LinkedList<MJClass> cdl = new LinkedList<MJClass>(); 
            pushFollow(FOLLOW_mainClass_in_program52);
            mc=mainClass();

            state._fsp--;

             cdl.add(mc); 
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:21:7: (cd= classDeclaration )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==15) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:21:9: cd= classDeclaration
            	    {
            	    pushFollow(FOLLOW_classDeclaration_in_program74);
            	    cd=classDeclaration();

            	    state._fsp--;

            	     cdl.add(cd); 

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

             
                  p = new MJProgram(cdl);
                

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


    // $ANTLR start "classDeclaration"
    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:29:1: classDeclaration returns [MJClass c] : 'class' cname= IDENT ( 'extends' scname= IDENT )? '{' (vd= varDeclaration )* (md= methodDeclaration )* '}' ;
    public final MJClass classDeclaration() throws RecognitionException {
        MJClass c = null;

        Token cname=null;
        Token scname=null;
        MJVariable vd = null;

        MJMethod md = null;


        try {
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:30:3: ( 'class' cname= IDENT ( 'extends' scname= IDENT )? '{' (vd= varDeclaration )* (md= methodDeclaration )* '}' )
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:30:5: 'class' cname= IDENT ( 'extends' scname= IDENT )? '{' (vd= varDeclaration )* (md= methodDeclaration )* '}'
            {
             String superClass = "Object"; 
                  LinkedList<MJVariable> vdl = new LinkedList<MJVariable>();
                  LinkedList<MJMethod> mdl = new LinkedList<MJMethod>();
                
            match(input,15,FOLLOW_15_in_classDeclaration126); 
            cname=(Token)match(input,IDENT,FOLLOW_IDENT_in_classDeclaration132); 
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:34:27: ( 'extends' scname= IDENT )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==16) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:34:29: 'extends' scname= IDENT
                    {
                    match(input,16,FOLLOW_16_in_classDeclaration136); 
                    scname=(Token)match(input,IDENT,FOLLOW_IDENT_in_classDeclaration142); 
                     superClass = (scname!=null?scname.getText():null); 

                    }
                    break;

            }

            match(input,17,FOLLOW_17_in_classDeclaration153); 
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:35:9: (vd= varDeclaration )*
            loop3:
            do {
                int alt3=2;
                switch ( input.LA(1) ) {
                case 28:
                    {
                    int LA3_2 = input.LA(2);

                    if ( (LA3_2==IDENT) ) {
                        int LA3_5 = input.LA(3);

                        if ( ((LA3_5>=26 && LA3_5<=27)) ) {
                            alt3=1;
                        }


                    }


                    }
                    break;
                case 29:
                    {
                    int LA3_3 = input.LA(2);

                    if ( (LA3_3==30) ) {
                        int LA3_6 = input.LA(3);

                        if ( (LA3_6==31) ) {
                            int LA3_8 = input.LA(4);

                            if ( (LA3_8==IDENT) ) {
                                int LA3_5 = input.LA(5);

                                if ( ((LA3_5>=26 && LA3_5<=27)) ) {
                                    alt3=1;
                                }


                            }


                        }


                    }
                    else if ( (LA3_3==IDENT) ) {
                        int LA3_5 = input.LA(3);

                        if ( ((LA3_5>=26 && LA3_5<=27)) ) {
                            alt3=1;
                        }


                    }


                    }
                    break;
                case IDENT:
                    {
                    int LA3_4 = input.LA(2);

                    if ( (LA3_4==IDENT) ) {
                        int LA3_5 = input.LA(3);

                        if ( ((LA3_5>=26 && LA3_5<=27)) ) {
                            alt3=1;
                        }


                    }


                    }
                    break;

                }

                switch (alt3) {
            	case 1 :
            	    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:35:11: vd= varDeclaration
            	    {
            	    pushFollow(FOLLOW_varDeclaration_in_classDeclaration161);
            	    vd=varDeclaration();

            	    state._fsp--;

            	     vdl.add(vd);
            	              

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:39:9: (md= methodDeclaration )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==IDENT||(LA4_0>=19 && LA4_0<=21)||(LA4_0>=28 && LA4_0<=29)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:39:11: md= methodDeclaration
            	    {
            	    pushFollow(FOLLOW_methodDeclaration_in_classDeclaration202);
            	    md=methodDeclaration();

            	    state._fsp--;

            	     mdl.add(md);
            	              

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            match(input,18,FOLLOW_18_in_classDeclaration233); 

                  c = new MJClass((cname!=null?cname.getText():null), superClass, vdl, mdl);
                

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


    // $ANTLR start "mainClass"
    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:49:1: mainClass returns [MJClass c] : 'class' cname= IDENT '{' 'public' 'static' 'void' 'main' '(' 'String[]' parname= IDENT ')' b= block '}' ;
    public final MJClass mainClass() throws RecognitionException {
        MJClass c = null;

        Token cname=null;
        Token parname=null;
        MJBlock b = null;


        try {
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:50:3: ( 'class' cname= IDENT '{' 'public' 'static' 'void' 'main' '(' 'String[]' parname= IDENT ')' b= block '}' )
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:50:5: 'class' cname= IDENT '{' 'public' 'static' 'void' 'main' '(' 'String[]' parname= IDENT ')' b= block '}'
            {
            match(input,15,FOLLOW_15_in_mainClass256); 
            cname=(Token)match(input,IDENT,FOLLOW_IDENT_in_mainClass262); 
            match(input,17,FOLLOW_17_in_mainClass269); 
            match(input,19,FOLLOW_19_in_mainClass278); 
            match(input,20,FOLLOW_20_in_mainClass280); 
            match(input,21,FOLLOW_21_in_mainClass282); 
            match(input,22,FOLLOW_22_in_mainClass284); 
            match(input,23,FOLLOW_23_in_mainClass286); 
            match(input,24,FOLLOW_24_in_mainClass288); 
            parname=(Token)match(input,IDENT,FOLLOW_IDENT_in_mainClass294); 
            match(input,25,FOLLOW_25_in_mainClass296); 
            pushFollow(FOLLOW_block_in_mainClass302);
            b=block();

            state._fsp--;

            match(input,18,FOLLOW_18_in_mainClass308); 

                  MJType partype = new MJType(new MJType("String"));
                  MJVariable par = new MJVariable( partype, (parname!=null?parname.getText():null));
                  LinkedList<MJVariable> parlist = new LinkedList<MJVariable>();
                  parlist.add(par);
                 
                  MJMethod md = new MJMethod(MJType.Tnone, "main", parlist, b, true, true);
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


    // $ANTLR start "block"
    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:65:1: block returns [MJBlock b] : '{' (vd= varDeclaration )* (sd= statement )* '}' ;
    public final MJBlock block() throws RecognitionException {
        MJBlock b = null;

        MJVariable vd = null;

        MJStatement sd = null;


        try {
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:66:3: ( '{' (vd= varDeclaration )* (sd= statement )* '}' )
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:66:5: '{' (vd= varDeclaration )* (sd= statement )* '}'
            {
              LinkedList<MJVariable> vdl = new LinkedList<MJVariable>();
                   LinkedList<MJStatement> sdl = new LinkedList<MJStatement>();    
                
            match(input,17,FOLLOW_17_in_block339); 
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:69:9: (vd= varDeclaration )*
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
            	    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:69:11: vd= varDeclaration
            	    {
            	    pushFollow(FOLLOW_varDeclaration_in_block347);
            	    vd=varDeclaration();

            	    state._fsp--;

            	     vdl.add(vd); 

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:72:9: (sd= statement )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>=IDENT && LA6_0<=COMMENT)||LA6_0==17||LA6_0==34||(LA6_0>=36 && LA6_0<=37)||LA6_0==49) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:72:11: sd= statement
            	    {
            	    pushFollow(FOLLOW_statement_in_block388);
            	    sd=statement();

            	    state._fsp--;

            	     sdl.add(sd); 

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            match(input,18,FOLLOW_18_in_block418); 

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
    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:81:1: varDeclaration returns [MJVariable vd] : t= type n= IDENT ( '=' e= expression )? ';' ;
    public final MJVariable varDeclaration() throws RecognitionException {
        MJVariable vd = null;

        Token n=null;
        MJType t = null;

        MJExpression e = null;


        try {
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:82:3: (t= type n= IDENT ( '=' e= expression )? ';' )
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:82:5: t= type n= IDENT ( '=' e= expression )? ';'
            {
             MJExpression init = new MJNoExpression(); 
            pushFollow(FOLLOW_type_in_varDeclaration455);
            t=type();

            state._fsp--;

            n=(Token)match(input,IDENT,FOLLOW_IDENT_in_varDeclaration461); 
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:83:24: ( '=' e= expression )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==26) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:83:26: '=' e= expression
                    {
                    match(input,26,FOLLOW_26_in_varDeclaration465); 
                    pushFollow(FOLLOW_expression_in_varDeclaration471);
                    e=expression();

                    state._fsp--;

                     init = e; 

                    }
                    break;

            }

            match(input,27,FOLLOW_27_in_varDeclaration477); 
             vd = new MJVariable(t, (n!=null?n.getText():null), init);
                

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
    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:88:1: type returns [MJType t] : ( 'boolean' | 'int' ( '[' ']' )? | IDENT );
    public final MJType type() throws RecognitionException {
        MJType t = null;

        Token IDENT1=null;

        try {
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:89:3: ( 'boolean' | 'int' ( '[' ']' )? | IDENT )
            int alt9=3;
            switch ( input.LA(1) ) {
            case 28:
                {
                alt9=1;
                }
                break;
            case 29:
                {
                alt9=2;
                }
                break;
            case IDENT:
                {
                alt9=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:89:5: 'boolean'
                    {
                    match(input,28,FOLLOW_28_in_type501); 
                     t = MJType.Tboolean; 

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:91:5: 'int' ( '[' ']' )?
                    {
                    match(input,29,FOLLOW_29_in_type513); 
                     t = MJType.Tint; 
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:93:5: ( '[' ']' )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==30) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:93:7: '[' ']'
                            {
                            match(input,30,FOLLOW_30_in_type528); 
                            match(input,31,FOLLOW_31_in_type530); 
                             t = new MJType(MJType.Tint); 

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:96:5: IDENT
                    {
                    IDENT1=(Token)match(input,IDENT,FOLLOW_IDENT_in_type552); 
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
    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:100:1: methodDeclaration returns [MJMethod md] : ( 'public' )? ( 'static' )? mtype= procType mname= IDENT '(' (t1= type n1= IDENT ( ',' t2= type n2= IDENT )* )? ')' '{' (vd= varDeclaration )* (sd= statement )* 'return' retExp= optExpression ';' '}' ;
    public final MJMethod methodDeclaration() throws RecognitionException {
        MJMethod md = null;

        Token mname=null;
        Token n1=null;
        Token n2=null;
        MJType mtype = null;

        MJType t1 = null;

        MJType t2 = null;

        MJVariable vd = null;

        MJStatement sd = null;

        MJExpression retExp = null;


        try {
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:101:3: ( ( 'public' )? ( 'static' )? mtype= procType mname= IDENT '(' (t1= type n1= IDENT ( ',' t2= type n2= IDENT )* )? ')' '{' (vd= varDeclaration )* (sd= statement )* 'return' retExp= optExpression ';' '}' )
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:101:5: ( 'public' )? ( 'static' )? mtype= procType mname= IDENT '(' (t1= type n1= IDENT ( ',' t2= type n2= IDENT )* )? ')' '{' (vd= varDeclaration )* (sd= statement )* 'return' retExp= optExpression ';' '}'
            {
             LinkedList<MJVariable> parlist = new LinkedList<MJVariable>(); 
                  boolean isStatic = false;
                  boolean isPublic = false;
                  LinkedList<MJVariable> vdl = new LinkedList<MJVariable>();
                  LinkedList<MJStatement> sdl = new LinkedList<MJStatement>();    
                
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:107:5: ( 'public' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==19) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:107:7: 'public'
                    {
                    match(input,19,FOLLOW_19_in_methodDeclaration584); 
                     isPublic = true; 

                    }
                    break;

            }

            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:108:5: ( 'static' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==20) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:108:7: 'static'
                    {
                    match(input,20,FOLLOW_20_in_methodDeclaration597); 
                     isStatic = true; 

                    }
                    break;

            }

            pushFollow(FOLLOW_procType_in_methodDeclaration612);
            mtype=procType();

            state._fsp--;

            mname=(Token)match(input,IDENT,FOLLOW_IDENT_in_methodDeclaration618); 
            match(input,23,FOLLOW_23_in_methodDeclaration625); 
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:111:7: (t1= type n1= IDENT ( ',' t2= type n2= IDENT )* )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==IDENT||(LA13_0>=28 && LA13_0<=29)) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:111:9: t1= type n1= IDENT ( ',' t2= type n2= IDENT )*
                    {
                    pushFollow(FOLLOW_type_in_methodDeclaration640);
                    t1=type();

                    state._fsp--;

                    n1=(Token)match(input,IDENT,FOLLOW_IDENT_in_methodDeclaration646); 
                     parlist.add(new MJVariable(t1,(n1!=null?n1.getText():null))); 
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:113:9: ( ',' t2= type n2= IDENT )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==32) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:113:11: ',' t2= type n2= IDENT
                    	    {
                    	    match(input,32,FOLLOW_32_in_methodDeclaration669); 
                    	    pushFollow(FOLLOW_type_in_methodDeclaration675);
                    	    t2=type();

                    	    state._fsp--;

                    	    n2=(Token)match(input,IDENT,FOLLOW_IDENT_in_methodDeclaration681); 
                    	     parlist.add(new MJVariable(t2,(n2!=null?n2.getText():null))); 

                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);


                    }
                    break;

            }

            match(input,25,FOLLOW_25_in_methodDeclaration722); 
            match(input,17,FOLLOW_17_in_methodDeclaration729); 
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:118:9: (vd= varDeclaration )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==IDENT) ) {
                    int LA14_2 = input.LA(2);

                    if ( (LA14_2==IDENT) ) {
                        alt14=1;
                    }


                }
                else if ( ((LA14_0>=28 && LA14_0<=29)) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:118:11: vd= varDeclaration
            	    {
            	    pushFollow(FOLLOW_varDeclaration_in_methodDeclaration737);
            	    vd=varDeclaration();

            	    state._fsp--;

            	     vdl.add(vd); 

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:121:9: (sd= statement )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>=IDENT && LA15_0<=COMMENT)||LA15_0==17||LA15_0==34||(LA15_0>=36 && LA15_0<=37)||LA15_0==49) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:121:11: sd= statement
            	    {
            	    pushFollow(FOLLOW_statement_in_methodDeclaration778);
            	    sd=statement();

            	    state._fsp--;

            	     sdl.add(sd); 

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

            match(input,33,FOLLOW_33_in_methodDeclaration812); 
            pushFollow(FOLLOW_optExpression_in_methodDeclaration818);
            retExp=optExpression();

            state._fsp--;

            match(input,27,FOLLOW_27_in_methodDeclaration820); 
             
                      MJReturn r = new MJReturn(retExp);
                      sdl.add(r);
                    
            match(input,18,FOLLOW_18_in_methodDeclaration836); 

                  MJBlock b = new MJBlock(vdl, sdl);
                  md = new MJMethod(mtype, (mname!=null?mname.getText():null), parlist,  b, isStatic, isPublic);
                

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return md;
    }
    // $ANTLR end "methodDeclaration"


    // $ANTLR start "procType"
    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:136:1: procType returns [MJType pt] : (t= type | 'void' );
    public final MJType procType() throws RecognitionException {
        MJType pt = null;

        MJType t = null;


        try {
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:137:3: (t= type | 'void' )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==IDENT||(LA16_0>=28 && LA16_0<=29)) ) {
                alt16=1;
            }
            else if ( (LA16_0==21) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:137:5: t= type
                    {
                    pushFollow(FOLLOW_type_in_procType867);
                    t=type();

                    state._fsp--;

                     pt = t; 

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:139:5: 'void'
                    {
                    match(input,21,FOLLOW_21_in_procType877); 
                     pt = MJType.Tnone; 

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
        return pt;
    }
    // $ANTLR end "procType"


    // $ANTLR start "statement"
    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:143:1: statement returns [MJStatement s] : (c= comment | b= block | 'if' '(' ci= expression ')' b1= block ( 'else' b2= block )? | 'while' '(' cw= expression ')' bw= statement | va= id '=' ea= expression ';' | va= id '[' idx= expression ']' '=' val= expression ';' | 'System.out.println' '(' ep= expression ')' ';' | m= id '(' (a1= expression ( ',' a2= expression )* )? ')' ';' );
    public final MJStatement statement() throws RecognitionException {
        MJStatement s = null;

        MJComment c = null;

        MJBlock b = null;

        MJExpression ci = null;

        MJBlock b1 = null;

        MJBlock b2 = null;

        MJExpression cw = null;

        MJStatement bw = null;

        MJIdentifier va = null;

        MJExpression ea = null;

        MJExpression idx = null;

        MJExpression val = null;

        MJExpression ep = null;

        MJIdentifier m = null;

        MJExpression a1 = null;

        MJExpression a2 = null;


        try {
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:144:3: (c= comment | b= block | 'if' '(' ci= expression ')' b1= block ( 'else' b2= block )? | 'while' '(' cw= expression ')' bw= statement | va= id '=' ea= expression ';' | va= id '[' idx= expression ']' '=' val= expression ';' | 'System.out.println' '(' ep= expression ')' ';' | m= id '(' (a1= expression ( ',' a2= expression )* )? ')' ';' )
            int alt20=8;
            alt20 = dfa20.predict(input);
            switch (alt20) {
                case 1 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:144:5: c= comment
                    {
                    pushFollow(FOLLOW_comment_in_statement904);
                    c=comment();

                    state._fsp--;

                     s = c; 

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:146:5: b= block
                    {
                    pushFollow(FOLLOW_block_in_statement920);
                    b=block();

                    state._fsp--;

                     s = b; 

                    }
                    break;
                case 3 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:148:5: 'if' '(' ci= expression ')' b1= block ( 'else' b2= block )?
                    {
                    match(input,34,FOLLOW_34_in_statement932); 
                    match(input,23,FOLLOW_23_in_statement934); 
                    pushFollow(FOLLOW_expression_in_statement940);
                    ci=expression();

                    state._fsp--;

                    match(input,25,FOLLOW_25_in_statement942); 
                    pushFollow(FOLLOW_block_in_statement948);
                    b1=block();

                    state._fsp--;


                          s = new MJIfElse(ci, b1, null);
                        
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:152:5: ( 'else' b2= block )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==35) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:152:7: 'else' b2= block
                            {
                            match(input,35,FOLLOW_35_in_statement963); 
                            pushFollow(FOLLOW_block_in_statement969);
                            b2=block();

                            state._fsp--;


                                    s = new MJIfElse(ci, b1, b2);
                                  

                            }
                            break;

                    }


                    }
                    break;
                case 4 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:157:5: 'while' '(' cw= expression ')' bw= statement
                    {
                    match(input,36,FOLLOW_36_in_statement991); 
                    match(input,23,FOLLOW_23_in_statement993); 
                    pushFollow(FOLLOW_expression_in_statement999);
                    cw=expression();

                    state._fsp--;

                    match(input,25,FOLLOW_25_in_statement1001); 
                    pushFollow(FOLLOW_statement_in_statement1007);
                    bw=statement();

                    state._fsp--;


                          s = new MJWhile(cw, bw);
                        

                    }
                    break;
                case 5 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:161:5: va= id '=' ea= expression ';'
                    {
                    pushFollow(FOLLOW_id_in_statement1023);
                    va=id();

                    state._fsp--;

                    match(input,26,FOLLOW_26_in_statement1025); 
                    pushFollow(FOLLOW_expression_in_statement1031);
                    ea=expression();

                    state._fsp--;

                    match(input,27,FOLLOW_27_in_statement1033); 

                        s = new MJAssign(va, ea);
                      

                    }
                    break;
                case 6 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:165:5: va= id '[' idx= expression ']' '=' val= expression ';'
                    {
                    pushFollow(FOLLOW_id_in_statement1047);
                    va=id();

                    state._fsp--;

                    match(input,30,FOLLOW_30_in_statement1049); 
                    pushFollow(FOLLOW_expression_in_statement1055);
                    idx=expression();

                    state._fsp--;

                    match(input,31,FOLLOW_31_in_statement1057); 
                    match(input,26,FOLLOW_26_in_statement1059); 
                    pushFollow(FOLLOW_expression_in_statement1065);
                    val=expression();

                    state._fsp--;

                    match(input,27,FOLLOW_27_in_statement1067); 

                        s = new MJAssign(new MJArray(va, idx), val);
                      

                    }
                    break;
                case 7 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:169:5: 'System.out.println' '(' ep= expression ')' ';'
                    {
                    match(input,37,FOLLOW_37_in_statement1077); 
                    match(input,23,FOLLOW_23_in_statement1079); 
                    pushFollow(FOLLOW_expression_in_statement1085);
                    ep=expression();

                    state._fsp--;

                    match(input,25,FOLLOW_25_in_statement1087); 
                    match(input,27,FOLLOW_27_in_statement1089); 

                          s = new MJPrintln(ep);
                        

                    }
                    break;
                case 8 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:173:5: m= id '(' (a1= expression ( ',' a2= expression )* )? ')' ';'
                    {
                     LinkedList<MJExpression> arglist = new LinkedList<MJExpression>(); 
                    pushFollow(FOLLOW_id_in_statement1112);
                    m=id();

                    state._fsp--;

                    match(input,23,FOLLOW_23_in_statement1114); 
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:175:5: (a1= expression ( ',' a2= expression )* )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==IDENT||(LA19_0>=INT && LA19_0<=STRING)||LA19_0==23||LA19_0==42||(LA19_0>=44 && LA19_0<=47)||LA19_0==49) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:175:7: a1= expression ( ',' a2= expression )*
                            {
                            pushFollow(FOLLOW_expression_in_statement1127);
                            a1=expression();

                            state._fsp--;

                             arglist.add(a1); 
                            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:177:7: ( ',' a2= expression )*
                            loop18:
                            do {
                                int alt18=2;
                                int LA18_0 = input.LA(1);

                                if ( (LA18_0==32) ) {
                                    alt18=1;
                                }


                                switch (alt18) {
                            	case 1 :
                            	    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:177:8: ',' a2= expression
                            	    {
                            	    match(input,32,FOLLOW_32_in_statement1146); 
                            	    pushFollow(FOLLOW_expression_in_statement1152);
                            	    a2=expression();

                            	    state._fsp--;

                            	     arglist.add(a2); 

                            	    }
                            	    break;

                            	default :
                            	    break loop18;
                                }
                            } while (true);


                            }
                            break;

                    }

                    match(input,25,FOLLOW_25_in_statement1182); 
                    match(input,27,FOLLOW_27_in_statement1184); 
                     s = new MJMethodCallStmt( m, arglist); 

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


    // $ANTLR start "comment"
    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:184:1: comment returns [MJComment c] : COMMENT ;
    public final MJComment comment() throws RecognitionException {
        MJComment c = null;

        Token COMMENT2=null;

        try {
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:185:3: ( COMMENT )
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:185:5: COMMENT
            {
            COMMENT2=(Token)match(input,COMMENT,FOLLOW_COMMENT_in_comment1212); 
             c = new MJComment((COMMENT2!=null?COMMENT2.getText():null)); 

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
    // $ANTLR end "comment"


    // $ANTLR start "expression"
    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:189:1: expression returns [MJExpression res] : arg1= level1 ( '&&' arg2= level1 )* ;
    public final MJExpression expression() throws RecognitionException {
        MJExpression res = null;

        MJExpression arg1 = null;

        MJExpression arg2 = null;


        try {
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:190:3: (arg1= level1 ( '&&' arg2= level1 )* )
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:190:5: arg1= level1 ( '&&' arg2= level1 )*
            {
            pushFollow(FOLLOW_level1_in_expression1242);
            arg1=level1();

            state._fsp--;

             res = arg1; 
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:192:5: ( '&&' arg2= level1 )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==38) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:192:7: '&&' arg2= level1
            	    {
            	    match(input,38,FOLLOW_38_in_expression1257); 
            	    pushFollow(FOLLOW_level1_in_expression1263);
            	    arg2=level1();

            	    state._fsp--;

            	     MJAnd op = new MJAnd( res, arg2);
            	            res = op;
            	          

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
        return res;
    }
    // $ANTLR end "expression"


    // $ANTLR start "level1"
    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:199:1: level1 returns [MJExpression res] : arg1= level2 ( '==' arg2= level2 )* ;
    public final MJExpression level1() throws RecognitionException {
        MJExpression res = null;

        MJExpression arg1 = null;

        MJExpression arg2 = null;


        try {
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:200:3: (arg1= level2 ( '==' arg2= level2 )* )
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:200:5: arg1= level2 ( '==' arg2= level2 )*
            {
            pushFollow(FOLLOW_level2_in_level11300);
            arg1=level2();

            state._fsp--;

              res = arg1; 
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:202:5: ( '==' arg2= level2 )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==39) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:202:7: '==' arg2= level2
            	    {
            	    match(input,39,FOLLOW_39_in_level11314); 
            	    pushFollow(FOLLOW_level2_in_level11320);
            	    arg2=level2();

            	    state._fsp--;

            	     MJEqual op = new MJEqual( res, arg2); 
            	            res = op;
            	          

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
        return res;
    }
    // $ANTLR end "level1"


    // $ANTLR start "level2"
    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:209:1: level2 returns [MJExpression res] : arg1= level3 ( '<' arg2= level3 )* ;
    public final MJExpression level2() throws RecognitionException {
        MJExpression res = null;

        MJExpression arg1 = null;

        MJExpression arg2 = null;


        try {
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:210:3: (arg1= level3 ( '<' arg2= level3 )* )
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:210:5: arg1= level3 ( '<' arg2= level3 )*
            {
            pushFollow(FOLLOW_level3_in_level21359);
            arg1=level3();

            state._fsp--;

              res = arg1; 
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:212:5: ( '<' arg2= level3 )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==40) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:212:7: '<' arg2= level3
            	    {
            	    match(input,40,FOLLOW_40_in_level21373); 
            	    pushFollow(FOLLOW_level3_in_level21379);
            	    arg2=level3();

            	    state._fsp--;

            	     MJLess op = new MJLess( res, arg2 ); 
            	            res = op;
            	          

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
        return res;
    }
    // $ANTLR end "level2"


    // $ANTLR start "level3"
    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:219:1: level3 returns [MJExpression res] : arg1= level4 ( ( '+' | '-' ) arg2= level4 )* ;
    public final MJExpression level3() throws RecognitionException {
        MJExpression res = null;

        MJExpression arg1 = null;

        MJExpression arg2 = null;


        try {
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:220:3: (arg1= level4 ( ( '+' | '-' ) arg2= level4 )* )
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:220:5: arg1= level4 ( ( '+' | '-' ) arg2= level4 )*
            {
            pushFollow(FOLLOW_level4_in_level31420);
            arg1=level4();

            state._fsp--;

              res = arg1; 
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:222:5: ( ( '+' | '-' ) arg2= level4 )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( ((LA25_0>=41 && LA25_0<=42)) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:222:7: ( '+' | '-' ) arg2= level4
            	    {
            	     MJBinaryOp op = null;
            	    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:223:7: ( '+' | '-' )
            	    int alt24=2;
            	    int LA24_0 = input.LA(1);

            	    if ( (LA24_0==41) ) {
            	        alt24=1;
            	    }
            	    else if ( (LA24_0==42) ) {
            	        alt24=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 24, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt24) {
            	        case 1 :
            	            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:223:9: '+'
            	            {
            	            match(input,41,FOLLOW_41_in_level31444); 
            	             op = new MJPlus(); 

            	            }
            	            break;
            	        case 2 :
            	            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:223:38: '-'
            	            {
            	            match(input,42,FOLLOW_42_in_level31450); 
            	             op = new MJMinus(); 

            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_level4_in_level31467);
            	    arg2=level4();

            	    state._fsp--;

            	     op.setLeftOperand( res );
            	            op.setRightOperand(arg2);
            	            res = op;
            	          

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
        return res;
    }
    // $ANTLR end "level3"


    // $ANTLR start "level4"
    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:232:1: level4 returns [MJExpression res] : arg1= level5 ( '*' arg2= level5 )* ;
    public final MJExpression level4() throws RecognitionException {
        MJExpression res = null;

        MJExpression arg1 = null;

        MJExpression arg2 = null;


        try {
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:233:3: (arg1= level5 ( '*' arg2= level5 )* )
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:233:6: arg1= level5 ( '*' arg2= level5 )*
            {
            pushFollow(FOLLOW_level5_in_level41505);
            arg1=level5();

            state._fsp--;

              res = arg1; 
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:235:5: ( '*' arg2= level5 )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==43) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:235:7: '*' arg2= level5
            	    {
            	    match(input,43,FOLLOW_43_in_level41519); 
            	    pushFollow(FOLLOW_level5_in_level41525);
            	    arg2=level5();

            	    state._fsp--;

            	     MJMult op = new MJMult( res, arg2 ); 
            	            res = op;
            	          

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
        return res;
    }
    // $ANTLR end "level4"


    // $ANTLR start "level5"
    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:242:1: level5 returns [MJExpression e] : ( '-' l= level5 | '!' l= level5 | 'new' 'int' '[' size= expression ']' | 'new' IDENT '(' ')' | i= id | i= id '[' idx= expression ']' | m= id '(' (a1= expression ( ',' a2= expression )* )? ')' | '(' e1= expression ')' | 'true' | 'false' | INT | STRING );
    public final MJExpression level5() throws RecognitionException {
        MJExpression e = null;

        Token IDENT3=null;
        Token INT4=null;
        Token STRING5=null;
        MJExpression l = null;

        MJExpression size = null;

        MJIdentifier i = null;

        MJExpression idx = null;

        MJIdentifier m = null;

        MJExpression a1 = null;

        MJExpression a2 = null;

        MJExpression e1 = null;


        try {
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:243:3: ( '-' l= level5 | '!' l= level5 | 'new' 'int' '[' size= expression ']' | 'new' IDENT '(' ')' | i= id | i= id '[' idx= expression ']' | m= id '(' (a1= expression ( ',' a2= expression )* )? ')' | '(' e1= expression ')' | 'true' | 'false' | INT | STRING )
            int alt29=12;
            alt29 = dfa29.predict(input);
            switch (alt29) {
                case 1 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:243:5: '-' l= level5
                    {
                    match(input,42,FOLLOW_42_in_level51560); 
                    pushFollow(FOLLOW_level5_in_level51566);
                    l=level5();

                    state._fsp--;

                     e = new MJUnaryMinus(l); 

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:245:5: '!' l= level5
                    {
                    match(input,44,FOLLOW_44_in_level51578); 
                    pushFollow(FOLLOW_level5_in_level51584);
                    l=level5();

                    state._fsp--;

                     e = new MJNegate(l); 

                    }
                    break;
                case 3 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:247:5: 'new' 'int' '[' size= expression ']'
                    {
                    match(input,45,FOLLOW_45_in_level51597); 
                    match(input,29,FOLLOW_29_in_level51599); 
                    match(input,30,FOLLOW_30_in_level51601); 
                    pushFollow(FOLLOW_expression_in_level51607);
                    size=expression();

                    state._fsp--;

                    match(input,31,FOLLOW_31_in_level51609); 
                     e = new MJNewArray(new MJType(MJType.Tint), size);

                    }
                    break;
                case 4 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:249:5: 'new' IDENT '(' ')'
                    {
                    match(input,45,FOLLOW_45_in_level51621); 
                    IDENT3=(Token)match(input,IDENT,FOLLOW_IDENT_in_level51623); 
                    match(input,23,FOLLOW_23_in_level51625); 
                    match(input,25,FOLLOW_25_in_level51626); 
                     e = new MJNew(new MJType((IDENT3!=null?IDENT3.getText():null))); 

                    }
                    break;
                case 5 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:251:5: i= id
                    {
                    pushFollow(FOLLOW_id_in_level51642);
                    i=id();

                    state._fsp--;

                     e = i; 

                    }
                    break;
                case 6 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:253:5: i= id '[' idx= expression ']'
                    {
                    pushFollow(FOLLOW_id_in_level51658);
                    i=id();

                    state._fsp--;

                    match(input,30,FOLLOW_30_in_level51660); 
                    pushFollow(FOLLOW_expression_in_level51666);
                    idx=expression();

                    state._fsp--;

                    match(input,31,FOLLOW_31_in_level51668); 
                     e = new MJArray(i, idx); 

                    }
                    break;
                case 7 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:255:5: m= id '(' (a1= expression ( ',' a2= expression )* )? ')'
                    {
                     LinkedList<MJExpression> arglist = new LinkedList<MJExpression>(); 
                    pushFollow(FOLLOW_id_in_level51691);
                    m=id();

                    state._fsp--;

                    match(input,23,FOLLOW_23_in_level51693); 
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:257:5: (a1= expression ( ',' a2= expression )* )?
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==IDENT||(LA28_0>=INT && LA28_0<=STRING)||LA28_0==23||LA28_0==42||(LA28_0>=44 && LA28_0<=47)||LA28_0==49) ) {
                        alt28=1;
                    }
                    switch (alt28) {
                        case 1 :
                            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:257:7: a1= expression ( ',' a2= expression )*
                            {
                            pushFollow(FOLLOW_expression_in_level51706);
                            a1=expression();

                            state._fsp--;

                             arglist.add(a1); 
                            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:259:7: ( ',' a2= expression )*
                            loop27:
                            do {
                                int alt27=2;
                                int LA27_0 = input.LA(1);

                                if ( (LA27_0==32) ) {
                                    alt27=1;
                                }


                                switch (alt27) {
                            	case 1 :
                            	    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:259:8: ',' a2= expression
                            	    {
                            	    match(input,32,FOLLOW_32_in_level51725); 
                            	    pushFollow(FOLLOW_expression_in_level51731);
                            	    a2=expression();

                            	    state._fsp--;

                            	     arglist.add(a2); 

                            	    }
                            	    break;

                            	default :
                            	    break loop27;
                                }
                            } while (true);


                            }
                            break;

                    }

                    match(input,25,FOLLOW_25_in_level51761); 
                     e = new MJMethodCallExpr(m, arglist); 

                    }
                    break;
                case 8 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:264:5: '(' e1= expression ')'
                    {
                    match(input,23,FOLLOW_23_in_level51777); 
                    pushFollow(FOLLOW_expression_in_level51783);
                    e1=expression();

                    state._fsp--;

                    match(input,25,FOLLOW_25_in_level51785); 
                     e = new MJParentheses(e1); 

                    }
                    break;
                case 9 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:266:5: 'true'
                    {
                    match(input,46,FOLLOW_46_in_level51797); 
                     e = MJBoolean.True; 

                    }
                    break;
                case 10 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:268:5: 'false'
                    {
                    match(input,47,FOLLOW_47_in_level51810); 
                     e = MJBoolean.False; 

                    }
                    break;
                case 11 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:270:5: INT
                    {
                    INT4=(Token)match(input,INT,FOLLOW_INT_in_level51823); 
                     e = new MJInteger((INT4!=null?INT4.getText():null)); 

                    }
                    break;
                case 12 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:272:5: STRING
                    {
                    STRING5=(Token)match(input,STRING,FOLLOW_STRING_in_level51836); 
                     e = new MJString((STRING5!=null?STRING5.getText():null)); 

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
    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:276:3: id returns [MJIdentifier e] : (t= thisid | t= thisid '.' IDENT );
    public final MJIdentifier id() throws RecognitionException {
        MJIdentifier e = null;

        Token IDENT6=null;
        MJIdentifier t = null;


        try {
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:277:3: (t= thisid | t= thisid '.' IDENT )
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==49) ) {
                int LA30_1 = input.LA(2);

                if ( (LA30_1==48) ) {
                    alt30=2;
                }
                else if ( (LA30_1==23||(LA30_1>=25 && LA30_1<=27)||(LA30_1>=30 && LA30_1<=32)||(LA30_1>=38 && LA30_1<=43)) ) {
                    alt30=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 30, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA30_0==IDENT) ) {
                int LA30_2 = input.LA(2);

                if ( (LA30_2==23||(LA30_2>=25 && LA30_2<=27)||(LA30_2>=30 && LA30_2<=32)||(LA30_2>=38 && LA30_2<=43)) ) {
                    alt30=1;
                }
                else if ( (LA30_2==48) ) {
                    alt30=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 30, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }
            switch (alt30) {
                case 1 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:277:5: t= thisid
                    {
                    pushFollow(FOLLOW_thisid_in_id1868);
                    t=thisid();

                    state._fsp--;

                     e = t; 

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:279:5: t= thisid '.' IDENT
                    {
                    pushFollow(FOLLOW_thisid_in_id1884);
                    t=thisid();

                    state._fsp--;

                    match(input,48,FOLLOW_48_in_id1886); 
                    IDENT6=(Token)match(input,IDENT,FOLLOW_IDENT_in_id1888); 
                     
                          MJIdentifier i = new MJIdentifier((IDENT6!=null?IDENT6.getText():null));
                          e = new MJSelector(t,i);
                        

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
    // $ANTLR end "id"


    // $ANTLR start "thisid"
    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:286:1: thisid returns [MJIdentifier e] : ( 'this' | IDENT );
    public final MJIdentifier thisid() throws RecognitionException {
        MJIdentifier e = null;

        Token IDENT7=null;

        try {
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:287:3: ( 'this' | IDENT )
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==49) ) {
                alt31=1;
            }
            else if ( (LA31_0==IDENT) ) {
                alt31=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }
            switch (alt31) {
                case 1 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:287:5: 'this'
                    {
                    match(input,49,FOLLOW_49_in_thisid1911); 
                     e= new MJIdentifier("this"); 

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:289:5: IDENT
                    {
                    IDENT7=(Token)match(input,IDENT,FOLLOW_IDENT_in_thisid1924); 
                     e = new MJIdentifier((IDENT7!=null?IDENT7.getText():null));

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
    // $ANTLR end "thisid"


    // $ANTLR start "optExpression"
    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:293:1: optExpression returns [MJExpression e] : (ex= expression | );
    public final MJExpression optExpression() throws RecognitionException {
        MJExpression e = null;

        MJExpression ex = null;


        try {
            // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:294:3: (ex= expression | )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==IDENT||(LA32_0>=INT && LA32_0<=STRING)||LA32_0==23||LA32_0==42||(LA32_0>=44 && LA32_0<=47)||LA32_0==49) ) {
                alt32=1;
            }
            else if ( (LA32_0==27) ) {
                alt32=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }
            switch (alt32) {
                case 1 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:294:5: ex= expression
                    {
                    pushFollow(FOLLOW_expression_in_optExpression1953);
                    ex=expression();

                    state._fsp--;

                     e = ex; 

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MiniJavaProject1/src/compiler/Frontend/MiniJava.g:297:5: 
                    {
                     e = new MJNoExpression(); 

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


    protected DFA20 dfa20 = new DFA20(this);
    protected DFA29 dfa29 = new DFA29(this);
    static final String DFA20_eotS =
        "\15\uffff";
    static final String DFA20_eofS =
        "\15\uffff";
    static final String DFA20_minS =
        "\1\4\4\uffff\2\27\2\uffff\1\4\2\uffff\1\27";
    static final String DFA20_maxS =
        "\1\61\4\uffff\2\60\2\uffff\1\4\2\uffff\1\36";
    static final String DFA20_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\2\uffff\1\7\1\10\1\uffff\1\6\1\5\1\uffff";
    static final String DFA20_specialS =
        "\15\uffff}>";
    static final String[] DFA20_transitionS = {
            "\1\6\1\1\13\uffff\1\2\20\uffff\1\3\1\uffff\1\4\1\7\13\uffff"+
            "\1\5",
            "",
            "",
            "",
            "",
            "\1\10\2\uffff\1\13\3\uffff\1\12\21\uffff\1\11",
            "\1\10\2\uffff\1\13\3\uffff\1\12\21\uffff\1\11",
            "",
            "",
            "\1\14",
            "",
            "",
            "\1\10\2\uffff\1\13\3\uffff\1\12"
    };

    static final short[] DFA20_eot = DFA.unpackEncodedString(DFA20_eotS);
    static final short[] DFA20_eof = DFA.unpackEncodedString(DFA20_eofS);
    static final char[] DFA20_min = DFA.unpackEncodedStringToUnsignedChars(DFA20_minS);
    static final char[] DFA20_max = DFA.unpackEncodedStringToUnsignedChars(DFA20_maxS);
    static final short[] DFA20_accept = DFA.unpackEncodedString(DFA20_acceptS);
    static final short[] DFA20_special = DFA.unpackEncodedString(DFA20_specialS);
    static final short[][] DFA20_transition;

    static {
        int numStates = DFA20_transitionS.length;
        DFA20_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA20_transition[i] = DFA.unpackEncodedString(DFA20_transitionS[i]);
        }
    }

    class DFA20 extends DFA {

        public DFA20(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 20;
            this.eot = DFA20_eot;
            this.eof = DFA20_eof;
            this.min = DFA20_min;
            this.max = DFA20_max;
            this.accept = DFA20_accept;
            this.special = DFA20_special;
            this.transition = DFA20_transition;
        }
        public String getDescription() {
            return "143:1: statement returns [MJStatement s] : (c= comment | b= block | 'if' '(' ci= expression ')' b1= block ( 'else' b2= block )? | 'while' '(' cw= expression ')' bw= statement | va= id '=' ea= expression ';' | va= id '[' idx= expression ']' '=' val= expression ';' | 'System.out.println' '(' ep= expression ')' ';' | m= id '(' (a1= expression ( ',' a2= expression )* )? ')' ';' );";
        }
    }
    static final String DFA29_eotS =
        "\22\uffff";
    static final String DFA29_eofS =
        "\22\uffff";
    static final String DFA29_minS =
        "\1\4\2\uffff\1\4\2\27\10\uffff\1\4\2\uffff\1\27";
    static final String DFA29_maxS =
        "\1\61\2\uffff\1\35\2\60\10\uffff\1\4\2\uffff\1\53";
    static final String DFA29_acceptS =
        "\1\uffff\1\1\1\2\3\uffff\1\10\1\11\1\12\1\13\1\14\1\3\1\4\1\5\1"+
        "\uffff\1\6\1\7\1\uffff";
    static final String DFA29_specialS =
        "\22\uffff}>";
    static final String[] DFA29_transitionS = {
            "\1\5\1\uffff\1\11\1\12\17\uffff\1\6\22\uffff\1\1\1\uffff\1\2"+
            "\1\3\1\7\1\10\1\uffff\1\4",
            "",
            "",
            "\1\14\30\uffff\1\13",
            "\1\20\1\uffff\1\15\1\uffff\1\15\2\uffff\1\17\2\15\5\uffff\6"+
            "\15\4\uffff\1\16",
            "\1\20\1\uffff\1\15\1\uffff\1\15\2\uffff\1\17\2\15\5\uffff\6"+
            "\15\4\uffff\1\16",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\21",
            "",
            "",
            "\1\20\1\uffff\1\15\1\uffff\1\15\2\uffff\1\17\2\15\5\uffff\6"+
            "\15"
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
            return "242:1: level5 returns [MJExpression e] : ( '-' l= level5 | '!' l= level5 | 'new' 'int' '[' size= expression ']' | 'new' IDENT '(' ')' | i= id | i= id '[' idx= expression ']' | m= id '(' (a1= expression ( ',' a2= expression )* )? ')' | '(' e1= expression ')' | 'true' | 'false' | INT | STRING );";
        }
    }
 

    public static final BitSet FOLLOW_mainClass_in_program52 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_classDeclaration_in_program74 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_15_in_classDeclaration126 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_classDeclaration132 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_16_in_classDeclaration136 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_classDeclaration142 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_classDeclaration153 = new BitSet(new long[]{0x00000000303C0010L});
    public static final BitSet FOLLOW_varDeclaration_in_classDeclaration161 = new BitSet(new long[]{0x00000000303C0010L});
    public static final BitSet FOLLOW_methodDeclaration_in_classDeclaration202 = new BitSet(new long[]{0x00000000303C0010L});
    public static final BitSet FOLLOW_18_in_classDeclaration233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_mainClass256 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_mainClass262 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_mainClass269 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_mainClass278 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_mainClass280 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_mainClass282 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_mainClass284 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_mainClass286 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_mainClass288 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_mainClass294 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_mainClass296 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_block_in_mainClass302 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_mainClass308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_block339 = new BitSet(new long[]{0x0002003430060030L});
    public static final BitSet FOLLOW_varDeclaration_in_block347 = new BitSet(new long[]{0x0002003430060030L});
    public static final BitSet FOLLOW_statement_in_block388 = new BitSet(new long[]{0x0002003400060030L});
    public static final BitSet FOLLOW_18_in_block418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_varDeclaration455 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_varDeclaration461 = new BitSet(new long[]{0x000000000C000000L});
    public static final BitSet FOLLOW_26_in_varDeclaration465 = new BitSet(new long[]{0x0002F400008000D0L});
    public static final BitSet FOLLOW_expression_in_varDeclaration471 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_varDeclaration477 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_type501 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_type513 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_30_in_type528 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_type530 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_type552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_methodDeclaration584 = new BitSet(new long[]{0x0000000030380010L});
    public static final BitSet FOLLOW_20_in_methodDeclaration597 = new BitSet(new long[]{0x0000000030380010L});
    public static final BitSet FOLLOW_procType_in_methodDeclaration612 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_methodDeclaration618 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_methodDeclaration625 = new BitSet(new long[]{0x0000000032000010L});
    public static final BitSet FOLLOW_type_in_methodDeclaration640 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_methodDeclaration646 = new BitSet(new long[]{0x0000000102000000L});
    public static final BitSet FOLLOW_32_in_methodDeclaration669 = new BitSet(new long[]{0x0000000030000010L});
    public static final BitSet FOLLOW_type_in_methodDeclaration675 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_methodDeclaration681 = new BitSet(new long[]{0x0000000102000000L});
    public static final BitSet FOLLOW_25_in_methodDeclaration722 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_methodDeclaration729 = new BitSet(new long[]{0x0002003630020030L});
    public static final BitSet FOLLOW_varDeclaration_in_methodDeclaration737 = new BitSet(new long[]{0x0002003630020030L});
    public static final BitSet FOLLOW_statement_in_methodDeclaration778 = new BitSet(new long[]{0x0002003600020030L});
    public static final BitSet FOLLOW_33_in_methodDeclaration812 = new BitSet(new long[]{0x0002F400088000D0L});
    public static final BitSet FOLLOW_optExpression_in_methodDeclaration818 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_methodDeclaration820 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_methodDeclaration836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_procType867 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_procType877 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comment_in_statement904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_block_in_statement920 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_statement932 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_statement934 = new BitSet(new long[]{0x0002F400008000D0L});
    public static final BitSet FOLLOW_expression_in_statement940 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_statement942 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_block_in_statement948 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_35_in_statement963 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_block_in_statement969 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_statement991 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_statement993 = new BitSet(new long[]{0x0002F400008000D0L});
    public static final BitSet FOLLOW_expression_in_statement999 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_statement1001 = new BitSet(new long[]{0x0002003400020030L});
    public static final BitSet FOLLOW_statement_in_statement1007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_statement1023 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_statement1025 = new BitSet(new long[]{0x0002F400008000D0L});
    public static final BitSet FOLLOW_expression_in_statement1031 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_statement1033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_statement1047 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_statement1049 = new BitSet(new long[]{0x0002F400008000D0L});
    public static final BitSet FOLLOW_expression_in_statement1055 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_statement1057 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_statement1059 = new BitSet(new long[]{0x0002F400008000D0L});
    public static final BitSet FOLLOW_expression_in_statement1065 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_statement1067 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_statement1077 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_statement1079 = new BitSet(new long[]{0x0002F400008000D0L});
    public static final BitSet FOLLOW_expression_in_statement1085 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_statement1087 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_statement1089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_statement1112 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_statement1114 = new BitSet(new long[]{0x0002F400028000D0L});
    public static final BitSet FOLLOW_expression_in_statement1127 = new BitSet(new long[]{0x0000000102000000L});
    public static final BitSet FOLLOW_32_in_statement1146 = new BitSet(new long[]{0x0002F400008000D0L});
    public static final BitSet FOLLOW_expression_in_statement1152 = new BitSet(new long[]{0x0000000102000000L});
    public static final BitSet FOLLOW_25_in_statement1182 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_statement1184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMMENT_in_comment1212 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_level1_in_expression1242 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_38_in_expression1257 = new BitSet(new long[]{0x0002F400008000D0L});
    public static final BitSet FOLLOW_level1_in_expression1263 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_level2_in_level11300 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_39_in_level11314 = new BitSet(new long[]{0x0002F400008000D0L});
    public static final BitSet FOLLOW_level2_in_level11320 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_level3_in_level21359 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_40_in_level21373 = new BitSet(new long[]{0x0002F400008000D0L});
    public static final BitSet FOLLOW_level3_in_level21379 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_level4_in_level31420 = new BitSet(new long[]{0x0000060000000002L});
    public static final BitSet FOLLOW_41_in_level31444 = new BitSet(new long[]{0x0002F400008000D0L});
    public static final BitSet FOLLOW_42_in_level31450 = new BitSet(new long[]{0x0002F400008000D0L});
    public static final BitSet FOLLOW_level4_in_level31467 = new BitSet(new long[]{0x0000060000000002L});
    public static final BitSet FOLLOW_level5_in_level41505 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_43_in_level41519 = new BitSet(new long[]{0x0002F400008000D0L});
    public static final BitSet FOLLOW_level5_in_level41525 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_42_in_level51560 = new BitSet(new long[]{0x0002F400008000D0L});
    public static final BitSet FOLLOW_level5_in_level51566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_level51578 = new BitSet(new long[]{0x0002F400008000D0L});
    public static final BitSet FOLLOW_level5_in_level51584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_level51597 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_level51599 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_level51601 = new BitSet(new long[]{0x0002F400008000D0L});
    public static final BitSet FOLLOW_expression_in_level51607 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_level51609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_level51621 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_level51623 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_level51625 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_level51626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_level51642 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_level51658 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_level51660 = new BitSet(new long[]{0x0002F400008000D0L});
    public static final BitSet FOLLOW_expression_in_level51666 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_level51668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_level51691 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_level51693 = new BitSet(new long[]{0x0002F400028000D0L});
    public static final BitSet FOLLOW_expression_in_level51706 = new BitSet(new long[]{0x0000000102000000L});
    public static final BitSet FOLLOW_32_in_level51725 = new BitSet(new long[]{0x0002F400008000D0L});
    public static final BitSet FOLLOW_expression_in_level51731 = new BitSet(new long[]{0x0000000102000000L});
    public static final BitSet FOLLOW_25_in_level51761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_level51777 = new BitSet(new long[]{0x0002F400008000D0L});
    public static final BitSet FOLLOW_expression_in_level51783 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_level51785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_level51797 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_level51810 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_level51823 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_level51836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_thisid_in_id1868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_thisid_in_id1884 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_48_in_id1886 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_id1888 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_thisid1911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_thisid1924 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_optExpression1953 = new BitSet(new long[]{0x0000000000000002L});

}