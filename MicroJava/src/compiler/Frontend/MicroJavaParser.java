// $ANTLR 3.2 Sep 23, 2009 12:02:23 /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g 2010-09-14 15:19:26

  package compiler.Frontend;
  
  import java.util.LinkedList;
  import compiler.IR.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class MicroJavaParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "IDENT", "INT", "LOWER", "UPPER", "NONNULL", "NUMBER", "NEWLINE", "WHITESPACE", "'class'", "'{'", "'public'", "'static'", "'void'", "'main'", "'('", "'String[]'", "')'", "';'", "'int'", "'}'", "'System.out.println'", "'='"
    };
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
    public static final int T__19=19;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int NEWLINE=10;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int T__12=12;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int IDENT=4;
    public static final int LOWER=6;
    public static final int UPPER=7;

    // delegates
    // delegators


        public MicroJavaParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public MicroJavaParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return MicroJavaParser.tokenNames; }
    public String getGrammarFileName() { return "/home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g"; }





    // $ANTLR start "program"
    // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:17:1: program returns [MJProgram p] : mc= mainClass ;
    public final MJProgram program() throws RecognitionException {
        MJProgram p = null;

        MJClass mc = null;


        try {
            // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:17:30: (mc= mainClass )
            // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:18:3: mc= mainClass
            {
             LinkedList<MJClass> cdl = new LinkedList<MJClass>(); 
            pushFollow(FOLLOW_mainClass_in_program49);
            mc=mainClass();

            state._fsp--;

             cdl.add(mc); 

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


    // $ANTLR start "mainClass"
    // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:26:1: mainClass returns [MJClass c] : 'class' cname= IDENT '{' 'public' 'static' 'void' 'main' '(' 'String[]' parname= IDENT ')' b= body ;
    public final MJClass mainClass() throws RecognitionException {
        MJClass c = null;

        Token cname=null;
        Token parname=null;
        MJBlock b = null;


        try {
            // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:27:3: ( 'class' cname= IDENT '{' 'public' 'static' 'void' 'main' '(' 'String[]' parname= IDENT ')' b= body )
            // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:27:5: 'class' cname= IDENT '{' 'public' 'static' 'void' 'main' '(' 'String[]' parname= IDENT ')' b= body
            {
            match(input,12,FOLLOW_12_in_mainClass72); 
            cname=(Token)match(input,IDENT,FOLLOW_IDENT_in_mainClass78); 
            match(input,13,FOLLOW_13_in_mainClass80); 
            match(input,14,FOLLOW_14_in_mainClass87); 
            match(input,15,FOLLOW_15_in_mainClass89); 
            match(input,16,FOLLOW_16_in_mainClass91); 
            match(input,17,FOLLOW_17_in_mainClass93); 
            match(input,18,FOLLOW_18_in_mainClass95); 
            match(input,19,FOLLOW_19_in_mainClass97); 
            parname=(Token)match(input,IDENT,FOLLOW_IDENT_in_mainClass103); 
            match(input,20,FOLLOW_20_in_mainClass105); 
            pushFollow(FOLLOW_body_in_mainClass111);
            b=body();

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


    // $ANTLR start "varDeclaration"
    // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:38:1: varDeclaration returns [MJVariable vd] : t= type n= IDENT ';' ;
    public final MJVariable varDeclaration() throws RecognitionException {
        MJVariable vd = null;

        Token n=null;
        MJType t = null;


        try {
            // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:39:3: (t= type n= IDENT ';' )
            // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:39:5: t= type n= IDENT ';'
            {
            pushFollow(FOLLOW_type_in_varDeclaration138);
            t=type();

            state._fsp--;

            n=(Token)match(input,IDENT,FOLLOW_IDENT_in_varDeclaration144); 
            match(input,21,FOLLOW_21_in_varDeclaration146); 

                vd = new MJVariable(t,(n!=null?n.getText():null));
              

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
    // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:45:1: type returns [MJType t] : 'int' ;
    public final MJType type() throws RecognitionException {
        MJType t = null;

        try {
            // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:46:3: ( 'int' )
            // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:46:5: 'int'
            {
            match(input,22,FOLLOW_22_in_type168); 
             t = MJType.Tint; 

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


    // $ANTLR start "body"
    // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:50:1: body returns [MJBlock b] : '{' (vd= varDeclaration )* (sd= statement )* '}' ;
    public final MJBlock body() throws RecognitionException {
        MJBlock b = null;

        MJVariable vd = null;

        MJStatement sd = null;


        try {
            // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:51:5: ( '{' (vd= varDeclaration )* (sd= statement )* '}' )
            // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:52:5: '{' (vd= varDeclaration )* (sd= statement )* '}'
            {
              LinkedList<MJVariable> vdl = new LinkedList<MJVariable>();
                   LinkedList<MJStatement> sdl = new LinkedList<MJStatement>();
                   
                
            match(input,13,FOLLOW_13_in_body206); 
            // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:56:9: (vd= varDeclaration )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==22) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:56:11: vd= varDeclaration
            	    {
            	    pushFollow(FOLLOW_varDeclaration_in_body214);
            	    vd=varDeclaration();

            	    state._fsp--;

            	     vdl.add(vd); 

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:58:8: (sd= statement )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==IDENT||LA2_0==24) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:58:10: sd= statement
            	    {
            	    pushFollow(FOLLOW_statement_in_body236);
            	    sd=statement();

            	    state._fsp--;

            	     sdl.add(sd); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            match(input,23,FOLLOW_23_in_body256); 

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
    // $ANTLR end "body"


    // $ANTLR start "statement"
    // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:67:1: statement returns [MJStatement s] : ( 'System.out.println' '(' ep= expression ')' ';' | va= IDENT '=' ea= expression ';' );
    public final MJStatement statement() throws RecognitionException {
        MJStatement s = null;

        Token va=null;
        MJExpression ep = null;

        MJExpression ea = null;


        try {
            // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:68:3: ( 'System.out.println' '(' ep= expression ')' ';' | va= IDENT '=' ea= expression ';' )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==24) ) {
                alt3=1;
            }
            else if ( (LA3_0==IDENT) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:68:5: 'System.out.println' '(' ep= expression ')' ';'
                    {
                    match(input,24,FOLLOW_24_in_statement285); 
                    match(input,18,FOLLOW_18_in_statement287); 
                    pushFollow(FOLLOW_expression_in_statement293);
                    ep=expression();

                    state._fsp--;

                    match(input,20,FOLLOW_20_in_statement295); 
                    match(input,21,FOLLOW_21_in_statement297); 

                        s = new MJPrintln(ep);
                      

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:72:5: va= IDENT '=' ea= expression ';'
                    {
                    va=(Token)match(input,IDENT,FOLLOW_IDENT_in_statement311); 
                    match(input,25,FOLLOW_25_in_statement313); 
                    pushFollow(FOLLOW_expression_in_statement319);
                    ea=expression();

                    state._fsp--;

                    match(input,21,FOLLOW_21_in_statement321); 

                        s = new MJAssign(new MJIdentifier((va!=null?va.getText():null)), ea);
                      

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
    // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:78:1: expression returns [MJExpression e] : (i= IDENT | INT );
    public final MJExpression expression() throws RecognitionException {
        MJExpression e = null;

        Token i=null;
        Token INT1=null;

        try {
            // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:79:3: (i= IDENT | INT )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==IDENT) ) {
                alt4=1;
            }
            else if ( (LA4_0==INT) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:79:5: i= IDENT
                    {
                    i=(Token)match(input,IDENT,FOLLOW_IDENT_in_expression348); 
                     e = new MJIdentifier((i!=null?i.getText():null)); 

                    }
                    break;
                case 2 :
                    // /home/krc/workspace/MicroJava/src/compiler/Frontend/MicroJava.g:81:5: INT
                    {
                    INT1=(Token)match(input,INT,FOLLOW_INT_in_expression358); 
                     e = new MJInteger((INT1!=null?INT1.getText():null)); 

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
    // $ANTLR end "expression"

    // Delegated rules


 

    public static final BitSet FOLLOW_mainClass_in_program49 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_mainClass72 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_mainClass78 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_mainClass80 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_mainClass87 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_mainClass89 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_mainClass91 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_mainClass93 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_mainClass95 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_mainClass97 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_mainClass103 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_mainClass105 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_body_in_mainClass111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_varDeclaration138 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_IDENT_in_varDeclaration144 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_varDeclaration146 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_type168 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_body206 = new BitSet(new long[]{0x0000000001C00010L});
    public static final BitSet FOLLOW_varDeclaration_in_body214 = new BitSet(new long[]{0x0000000001C00010L});
    public static final BitSet FOLLOW_statement_in_body236 = new BitSet(new long[]{0x0000000001800010L});
    public static final BitSet FOLLOW_23_in_body256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_statement285 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_statement287 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_expression_in_statement293 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_statement295 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_statement297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_statement311 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_statement313 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_expression_in_statement319 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_statement321 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENT_in_expression348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_expression358 = new BitSet(new long[]{0x0000000000000002L});

}