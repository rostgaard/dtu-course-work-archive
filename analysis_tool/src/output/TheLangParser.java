// $ANTLR 3.3 Nov 30, 2010 12:45:30 C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g 2013-11-04 16:59:51

  package output;
  import java.util.ArrayList;
  import syntaxtree.condition.*;
  import syntaxtree.declaration.*;
  import syntaxtree.expression.*;
  import syntaxtree.statement.*;
  import syntaxtree.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;

public class TheLangParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "AND", "OR", "ASSIGN", "SEMI", "GT", "GE", "LT", "LE", "EQ", "NEQ", "PLUS", "MINUS", "MUL", "DIV", "NOT", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACKET", "RBRACKET", "COLON", "IF", "THEN", "ELSE", "FI", "WHILE", "DO", "OD", "SKIP", "WRITE", "READ", "PROGRAM", "END", "TRUE", "FALSE", "INT", "LOW", "HIGH", "IDENTIFIER", "INTEGER", "COMMENT", "LETTER", "WS"
    };
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


        public TheLangParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public TheLangParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
            this.state.ruleMemo = new HashMap[54+1];
             
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return TheLangParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g"; }


    public static class aexpr_return extends ParserRuleReturnScope {
        public Expression value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "aexpr"
    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:74:1: aexpr returns [Expression value] : arg1= aexpr1 ( PLUS arg1= aexpr1 | MINUS arg1= aexpr1 )* ;
    public final TheLangParser.aexpr_return aexpr() throws RecognitionException {
        TheLangParser.aexpr_return retval = new TheLangParser.aexpr_return();
        retval.start = input.LT(1);
        int aexpr_StartIndex = input.index();
        CommonTree root_0 = null;

        Token PLUS1=null;
        Token MINUS2=null;
        TheLangParser.aexpr1_return arg1 = null;


        CommonTree PLUS1_tree=null;
        CommonTree MINUS2_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return retval; }
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:75:5: (arg1= aexpr1 ( PLUS arg1= aexpr1 | MINUS arg1= aexpr1 )* )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:75:7: arg1= aexpr1 ( PLUS arg1= aexpr1 | MINUS arg1= aexpr1 )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_aexpr1_in_aexpr440);
            arg1=aexpr1();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, arg1.getTree());
            if ( state.backtracking==0 ) {
              retval.value = arg1.value;
            }
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:76:7: ( PLUS arg1= aexpr1 | MINUS arg1= aexpr1 )*
            loop1:
            do {
                int alt1=3;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==PLUS) ) {
                    alt1=1;
                }
                else if ( (LA1_0==MINUS) ) {
                    alt1=2;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:76:9: PLUS arg1= aexpr1
            	    {
            	    PLUS1=(Token)match(input,PLUS,FOLLOW_PLUS_in_aexpr453); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    PLUS1_tree = (CommonTree)adaptor.create(PLUS1);
            	    adaptor.addChild(root_0, PLUS1_tree);
            	    }
            	    pushFollow(FOLLOW_aexpr1_in_aexpr459);
            	    arg1=aexpr1();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, arg1.getTree());
            	    if ( state.backtracking==0 ) {
            	      retval.value = new OperationExpression(retval.value, arg1.value, ArithmeticOperation.PLUS); 
            	    }

            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:77:9: MINUS arg1= aexpr1
            	    {
            	    MINUS2=(Token)match(input,MINUS,FOLLOW_MINUS_in_aexpr471); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    MINUS2_tree = (CommonTree)adaptor.create(MINUS2);
            	    adaptor.addChild(root_0, MINUS2_tree);
            	    }
            	    pushFollow(FOLLOW_aexpr1_in_aexpr477);
            	    arg1=aexpr1();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, arg1.getTree());
            	    if ( state.backtracking==0 ) {
            	      retval.value = new OperationExpression(retval.value, arg1.value, ArithmeticOperation.MINUS);
            	    }

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 1, aexpr_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "aexpr"

    public static class aexpr1_return extends ParserRuleReturnScope {
        public Expression value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "aexpr1"
    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:81:1: aexpr1 returns [Expression value] : arg1= aexpr2 ( MUL arg1= aexpr2 | DIV arg1= aexpr2 )* ;
    public final TheLangParser.aexpr1_return aexpr1() throws RecognitionException {
        TheLangParser.aexpr1_return retval = new TheLangParser.aexpr1_return();
        retval.start = input.LT(1);
        int aexpr1_StartIndex = input.index();
        CommonTree root_0 = null;

        Token MUL3=null;
        Token DIV4=null;
        TheLangParser.aexpr2_return arg1 = null;


        CommonTree MUL3_tree=null;
        CommonTree DIV4_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return retval; }
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:82:5: (arg1= aexpr2 ( MUL arg1= aexpr2 | DIV arg1= aexpr2 )* )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:82:7: arg1= aexpr2 ( MUL arg1= aexpr2 | DIV arg1= aexpr2 )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_aexpr2_in_aexpr1507);
            arg1=aexpr2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, arg1.getTree());
            if ( state.backtracking==0 ) {
              retval.value = arg1.value;
            }
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:83:7: ( MUL arg1= aexpr2 | DIV arg1= aexpr2 )*
            loop2:
            do {
                int alt2=3;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==MUL) ) {
                    alt2=1;
                }
                else if ( (LA2_0==DIV) ) {
                    alt2=2;
                }


                switch (alt2) {
            	case 1 :
            	    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:83:9: MUL arg1= aexpr2
            	    {
            	    MUL3=(Token)match(input,MUL,FOLLOW_MUL_in_aexpr1520); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    MUL3_tree = (CommonTree)adaptor.create(MUL3);
            	    adaptor.addChild(root_0, MUL3_tree);
            	    }
            	    pushFollow(FOLLOW_aexpr2_in_aexpr1526);
            	    arg1=aexpr2();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, arg1.getTree());
            	    if ( state.backtracking==0 ) {
            	      retval.value = new OperationExpression(retval.value, arg1.value, ArithmeticOperation.MULTIPLICATION); 
            	    }

            	    }
            	    break;
            	case 2 :
            	    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:84:9: DIV arg1= aexpr2
            	    {
            	    DIV4=(Token)match(input,DIV,FOLLOW_DIV_in_aexpr1539); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    DIV4_tree = (CommonTree)adaptor.create(DIV4);
            	    adaptor.addChild(root_0, DIV4_tree);
            	    }
            	    pushFollow(FOLLOW_aexpr2_in_aexpr1545);
            	    arg1=aexpr2();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, arg1.getTree());
            	    if ( state.backtracking==0 ) {
            	      retval.value = new OperationExpression(retval.value, arg1.value, ArithmeticOperation.DIVISION);
            	    }

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 2, aexpr1_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "aexpr1"

    public static class aexpr2_return extends ParserRuleReturnScope {
        public Expression value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "aexpr2"
    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:88:1: aexpr2 returns [Expression value] : ( MINUS expr= aexpr3 | expr= aexpr3 );
    public final TheLangParser.aexpr2_return aexpr2() throws RecognitionException {
        TheLangParser.aexpr2_return retval = new TheLangParser.aexpr2_return();
        retval.start = input.LT(1);
        int aexpr2_StartIndex = input.index();
        CommonTree root_0 = null;

        Token MINUS5=null;
        TheLangParser.aexpr3_return expr = null;


        CommonTree MINUS5_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return retval; }
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:89:5: ( MINUS expr= aexpr3 | expr= aexpr3 )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==MINUS) ) {
                alt3=1;
            }
            else if ( (LA3_0==LPAREN||(LA3_0>=IDENTIFIER && LA3_0<=INTEGER)) ) {
                alt3=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:89:7: MINUS expr= aexpr3
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    MINUS5=(Token)match(input,MINUS,FOLLOW_MINUS_in_aexpr2571); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    MINUS5_tree = (CommonTree)adaptor.create(MINUS5);
                    adaptor.addChild(root_0, MINUS5_tree);
                    }
                    pushFollow(FOLLOW_aexpr3_in_aexpr2577);
                    expr=aexpr3();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr.getTree());
                    if ( state.backtracking==0 ) {
                      retval.value = new NegationExpression(expr.value);
                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:90:7: expr= aexpr3
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_aexpr3_in_aexpr2591);
                    expr=aexpr3();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr.getTree());
                    if ( state.backtracking==0 ) {
                      retval.value = expr.value;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 3, aexpr2_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "aexpr2"

    public static class aexpr3_return extends ParserRuleReturnScope {
        public Expression value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "aexpr3"
    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:94:1: aexpr3 returns [Expression value] : (id= IDENTIFIER | id= IDENTIFIER LBRACKET idx= aexpr RBRACKET | cons= INTEGER | LPAREN expr= aexpr RPAREN );
    public final TheLangParser.aexpr3_return aexpr3() throws RecognitionException {
        TheLangParser.aexpr3_return retval = new TheLangParser.aexpr3_return();
        retval.start = input.LT(1);
        int aexpr3_StartIndex = input.index();
        CommonTree root_0 = null;

        Token id=null;
        Token cons=null;
        Token LBRACKET6=null;
        Token RBRACKET7=null;
        Token LPAREN8=null;
        Token RPAREN9=null;
        TheLangParser.aexpr_return idx = null;

        TheLangParser.aexpr_return expr = null;


        CommonTree id_tree=null;
        CommonTree cons_tree=null;
        CommonTree LBRACKET6_tree=null;
        CommonTree RBRACKET7_tree=null;
        CommonTree LPAREN8_tree=null;
        CommonTree RPAREN9_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return retval; }
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:95:5: (id= IDENTIFIER | id= IDENTIFIER LBRACKET idx= aexpr RBRACKET | cons= INTEGER | LPAREN expr= aexpr RPAREN )
            int alt4=4;
            switch ( input.LA(1) ) {
            case IDENTIFIER:
                {
                int LA4_1 = input.LA(2);

                if ( (LA4_1==LBRACKET) ) {
                    alt4=2;
                }
                else if ( (LA4_1==EOF||(LA4_1>=AND && LA4_1<=OR)||(LA4_1>=SEMI && LA4_1<=DIV)||LA4_1==RPAREN||LA4_1==RBRACKET||LA4_1==THEN||LA4_1==DO) ) {
                    alt4=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 1, input);

                    throw nvae;
                }
                }
                break;
            case INTEGER:
                {
                alt4=3;
                }
                break;
            case LPAREN:
                {
                alt4=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:95:7: id= IDENTIFIER
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    id=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_aexpr3620); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    id_tree = (CommonTree)adaptor.create(id);
                    adaptor.addChild(root_0, id_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.value = new Variable(Type.INT, id.getText());
                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:96:7: id= IDENTIFIER LBRACKET idx= aexpr RBRACKET
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    id=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_aexpr3634); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    id_tree = (CommonTree)adaptor.create(id);
                    adaptor.addChild(root_0, id_tree);
                    }
                    LBRACKET6=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_aexpr3636); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LBRACKET6_tree = (CommonTree)adaptor.create(LBRACKET6);
                    adaptor.addChild(root_0, LBRACKET6_tree);
                    }
                    pushFollow(FOLLOW_aexpr_in_aexpr3642);
                    idx=aexpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, idx.getTree());
                    RBRACKET7=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_aexpr3644); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RBRACKET7_tree = (CommonTree)adaptor.create(RBRACKET7);
                    adaptor.addChild(root_0, RBRACKET7_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.value = new ArrayExpression(new Variable(Type.ARRAY, id.getText()), idx.value);
                    }

                    }
                    break;
                case 3 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:97:7: cons= INTEGER
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    cons=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_aexpr3659); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    cons_tree = (CommonTree)adaptor.create(cons);
                    adaptor.addChild(root_0, cons_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.value = new Constant(Integer.parseInt(cons.getText()));
                    }

                    }
                    break;
                case 4 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:98:7: LPAREN expr= aexpr RPAREN
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    LPAREN8=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_aexpr3669); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LPAREN8_tree = (CommonTree)adaptor.create(LPAREN8);
                    adaptor.addChild(root_0, LPAREN8_tree);
                    }
                    pushFollow(FOLLOW_aexpr_in_aexpr3675);
                    expr=aexpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr.getTree());
                    RPAREN9=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_aexpr3677); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RPAREN9_tree = (CommonTree)adaptor.create(RPAREN9);
                    adaptor.addChild(root_0, RPAREN9_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.value = new ParanthesesExpression(expr.value);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 4, aexpr3_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "aexpr3"

    public static class bexpr_return extends ParserRuleReturnScope {
        public Condition value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "bexpr"
    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:102:1: bexpr returns [Condition value] : expr= bexpr1 ( OR expr= bexpr1 )* ;
    public final TheLangParser.bexpr_return bexpr() throws RecognitionException {
        TheLangParser.bexpr_return retval = new TheLangParser.bexpr_return();
        retval.start = input.LT(1);
        int bexpr_StartIndex = input.index();
        CommonTree root_0 = null;

        Token OR10=null;
        TheLangParser.bexpr1_return expr = null;


        CommonTree OR10_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return retval; }
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:103:5: (expr= bexpr1 ( OR expr= bexpr1 )* )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:103:7: expr= bexpr1 ( OR expr= bexpr1 )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_bexpr1_in_bexpr705);
            expr=bexpr1();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr.getTree());
            if ( state.backtracking==0 ) {
              retval.value = expr.value;
            }
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:104:7: ( OR expr= bexpr1 )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==OR) ) {
                    int LA5_2 = input.LA(2);

                    if ( (synpred9_TheLang()) ) {
                        alt5=1;
                    }


                }


                switch (alt5) {
            	case 1 :
            	    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:104:8: OR expr= bexpr1
            	    {
            	    OR10=(Token)match(input,OR,FOLLOW_OR_in_bexpr717); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    OR10_tree = (CommonTree)adaptor.create(OR10);
            	    adaptor.addChild(root_0, OR10_tree);
            	    }
            	    pushFollow(FOLLOW_bexpr1_in_bexpr723);
            	    expr=bexpr1();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr.getTree());
            	    if ( state.backtracking==0 ) {
            	      retval.value = new OperationCondition(retval.value, expr.value, BooleanOperation.OR);
            	    }

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 5, bexpr_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "bexpr"

    public static class bexpr1_return extends ParserRuleReturnScope {
        public Condition value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "bexpr1"
    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:108:1: bexpr1 returns [Condition value] : expr= bexpr2 ( AND expr= bexpr2 )* ;
    public final TheLangParser.bexpr1_return bexpr1() throws RecognitionException {
        TheLangParser.bexpr1_return retval = new TheLangParser.bexpr1_return();
        retval.start = input.LT(1);
        int bexpr1_StartIndex = input.index();
        CommonTree root_0 = null;

        Token AND11=null;
        TheLangParser.bexpr2_return expr = null;


        CommonTree AND11_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return retval; }
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:109:5: (expr= bexpr2 ( AND expr= bexpr2 )* )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:109:7: expr= bexpr2 ( AND expr= bexpr2 )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_bexpr2_in_bexpr1753);
            expr=bexpr2();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr.getTree());
            if ( state.backtracking==0 ) {
              retval.value = expr.value;
            }
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:110:7: ( AND expr= bexpr2 )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==AND) ) {
                    int LA6_2 = input.LA(2);

                    if ( (synpred10_TheLang()) ) {
                        alt6=1;
                    }


                }


                switch (alt6) {
            	case 1 :
            	    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:110:8: AND expr= bexpr2
            	    {
            	    AND11=(Token)match(input,AND,FOLLOW_AND_in_bexpr1765); if (state.failed) return retval;
            	    if ( state.backtracking==0 ) {
            	    AND11_tree = (CommonTree)adaptor.create(AND11);
            	    adaptor.addChild(root_0, AND11_tree);
            	    }
            	    pushFollow(FOLLOW_bexpr2_in_bexpr1771);
            	    expr=bexpr2();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr.getTree());
            	    if ( state.backtracking==0 ) {
            	      retval.value = new OperationCondition(retval.value, expr.value, BooleanOperation.AND);
            	    }

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 6, bexpr1_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "bexpr1"

    public static class bexpr2_return extends ParserRuleReturnScope {
        public Condition value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "bexpr2"
    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:114:1: bexpr2 returns [Condition value] : (expr1= aexpr op= opr expr2= aexpr | NOT cond= bexpr | TRUE | FALSE | LPAREN cond= bexpr RPAREN );
    public final TheLangParser.bexpr2_return bexpr2() throws RecognitionException {
        TheLangParser.bexpr2_return retval = new TheLangParser.bexpr2_return();
        retval.start = input.LT(1);
        int bexpr2_StartIndex = input.index();
        CommonTree root_0 = null;

        Token NOT12=null;
        Token TRUE13=null;
        Token FALSE14=null;
        Token LPAREN15=null;
        Token RPAREN16=null;
        TheLangParser.aexpr_return expr1 = null;

        TheLangParser.opr_return op = null;

        TheLangParser.aexpr_return expr2 = null;

        TheLangParser.bexpr_return cond = null;


        CommonTree NOT12_tree=null;
        CommonTree TRUE13_tree=null;
        CommonTree FALSE14_tree=null;
        CommonTree LPAREN15_tree=null;
        CommonTree RPAREN16_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return retval; }
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:115:5: (expr1= aexpr op= opr expr2= aexpr | NOT cond= bexpr | TRUE | FALSE | LPAREN cond= bexpr RPAREN )
            int alt7=5;
            switch ( input.LA(1) ) {
            case MINUS:
            case IDENTIFIER:
            case INTEGER:
                {
                alt7=1;
                }
                break;
            case LPAREN:
                {
                int LA7_4 = input.LA(2);

                if ( (synpred11_TheLang()) ) {
                    alt7=1;
                }
                else if ( (true) ) {
                    alt7=5;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 4, input);

                    throw nvae;
                }
                }
                break;
            case NOT:
                {
                alt7=2;
                }
                break;
            case TRUE:
                {
                alt7=3;
                }
                break;
            case FALSE:
                {
                alt7=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:115:7: expr1= aexpr op= opr expr2= aexpr
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_aexpr_in_bexpr2802);
                    expr1=aexpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr1.getTree());
                    pushFollow(FOLLOW_opr_in_bexpr2808);
                    op=opr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, op.getTree());
                    pushFollow(FOLLOW_aexpr_in_bexpr2814);
                    expr2=aexpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr2.getTree());
                    if ( state.backtracking==0 ) {
                      retval.value = new ExpressionOperationCondition(expr1.value, expr2.value, op.value);
                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:116:7: NOT cond= bexpr
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    NOT12=(Token)match(input,NOT,FOLLOW_NOT_in_bexpr2824); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NOT12_tree = (CommonTree)adaptor.create(NOT12);
                    adaptor.addChild(root_0, NOT12_tree);
                    }
                    pushFollow(FOLLOW_bexpr_in_bexpr2830);
                    cond=bexpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, cond.getTree());
                    if ( state.backtracking==0 ) {
                      retval.value = new NegationCondition(cond.value);
                    }

                    }
                    break;
                case 3 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:117:7: TRUE
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    TRUE13=(Token)match(input,TRUE,FOLLOW_TRUE_in_bexpr2841); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    TRUE13_tree = (CommonTree)adaptor.create(TRUE13);
                    adaptor.addChild(root_0, TRUE13_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.value = new TrueCondition();
                    }

                    }
                    break;
                case 4 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:118:7: FALSE
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    FALSE14=(Token)match(input,FALSE,FOLLOW_FALSE_in_bexpr2851); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    FALSE14_tree = (CommonTree)adaptor.create(FALSE14);
                    adaptor.addChild(root_0, FALSE14_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.value = new FalseCondition();
                    }

                    }
                    break;
                case 5 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:119:7: LPAREN cond= bexpr RPAREN
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    LPAREN15=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_bexpr2861); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LPAREN15_tree = (CommonTree)adaptor.create(LPAREN15);
                    adaptor.addChild(root_0, LPAREN15_tree);
                    }
                    pushFollow(FOLLOW_bexpr_in_bexpr2867);
                    cond=bexpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, cond.getTree());
                    RPAREN16=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_bexpr2869); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RPAREN16_tree = (CommonTree)adaptor.create(RPAREN16);
                    adaptor.addChild(root_0, RPAREN16_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.value = new ParenthesesCondition(cond.value);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 7, bexpr2_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "bexpr2"

    public static class opr_return extends ParserRuleReturnScope {
        public RelationOperation value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "opr"
    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:123:1: opr returns [RelationOperation value] : ( GT | GE | LT | LE | EQ | NEQ );
    public final TheLangParser.opr_return opr() throws RecognitionException {
        TheLangParser.opr_return retval = new TheLangParser.opr_return();
        retval.start = input.LT(1);
        int opr_StartIndex = input.index();
        CommonTree root_0 = null;

        Token GT17=null;
        Token GE18=null;
        Token LT19=null;
        Token LE20=null;
        Token EQ21=null;
        Token NEQ22=null;

        CommonTree GT17_tree=null;
        CommonTree GE18_tree=null;
        CommonTree LT19_tree=null;
        CommonTree LE20_tree=null;
        CommonTree EQ21_tree=null;
        CommonTree NEQ22_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return retval; }
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:124:5: ( GT | GE | LT | LE | EQ | NEQ )
            int alt8=6;
            switch ( input.LA(1) ) {
            case GT:
                {
                alt8=1;
                }
                break;
            case GE:
                {
                alt8=2;
                }
                break;
            case LT:
                {
                alt8=3;
                }
                break;
            case LE:
                {
                alt8=4;
                }
                break;
            case EQ:
                {
                alt8=5;
                }
                break;
            case NEQ:
                {
                alt8=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:124:7: GT
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    GT17=(Token)match(input,GT,FOLLOW_GT_in_opr894); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    GT17_tree = (CommonTree)adaptor.create(GT17);
                    adaptor.addChild(root_0, GT17_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.value = RelationOperation.GREATERTHAN;
                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:125:7: GE
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    GE18=(Token)match(input,GE,FOLLOW_GE_in_opr904); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    GE18_tree = (CommonTree)adaptor.create(GE18);
                    adaptor.addChild(root_0, GE18_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.value = RelationOperation.GREATEREQUALTHAN;
                    }

                    }
                    break;
                case 3 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:126:7: LT
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    LT19=(Token)match(input,LT,FOLLOW_LT_in_opr914); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LT19_tree = (CommonTree)adaptor.create(LT19);
                    adaptor.addChild(root_0, LT19_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.value = RelationOperation.LESSTHAN;
                    }

                    }
                    break;
                case 4 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:127:7: LE
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    LE20=(Token)match(input,LE,FOLLOW_LE_in_opr924); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LE20_tree = (CommonTree)adaptor.create(LE20);
                    adaptor.addChild(root_0, LE20_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.value = RelationOperation.LESSEQUALTHAN;
                    }

                    }
                    break;
                case 5 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:128:7: EQ
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    EQ21=(Token)match(input,EQ,FOLLOW_EQ_in_opr934); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    EQ21_tree = (CommonTree)adaptor.create(EQ21);
                    adaptor.addChild(root_0, EQ21_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.value = RelationOperation.EQUAL;
                    }

                    }
                    break;
                case 6 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:129:7: NEQ
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    NEQ22=(Token)match(input,NEQ,FOLLOW_NEQ_in_opr944); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    NEQ22_tree = (CommonTree)adaptor.create(NEQ22);
                    adaptor.addChild(root_0, NEQ22_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.value = RelationOperation.NOTEQUAL;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 8, opr_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "opr"

    public static class decl_return extends ParserRuleReturnScope {
        public Declaration value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "decl"
    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:133:1: decl returns [Declaration value] : ( (lvl= level )? INT id= IDENTIFIER SEMI | (lvl= level )? INT id= IDENTIFIER LBRACKET size= INTEGER RBRACKET SEMI );
    public final TheLangParser.decl_return decl() throws RecognitionException {
        TheLangParser.decl_return retval = new TheLangParser.decl_return();
        retval.start = input.LT(1);
        int decl_StartIndex = input.index();
        CommonTree root_0 = null;

        Token id=null;
        Token size=null;
        Token INT23=null;
        Token SEMI24=null;
        Token INT25=null;
        Token LBRACKET26=null;
        Token RBRACKET27=null;
        Token SEMI28=null;
        TheLangParser.level_return lvl = null;


        CommonTree id_tree=null;
        CommonTree size_tree=null;
        CommonTree INT23_tree=null;
        CommonTree SEMI24_tree=null;
        CommonTree INT25_tree=null;
        CommonTree LBRACKET26_tree=null;
        CommonTree RBRACKET27_tree=null;
        CommonTree SEMI28_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return retval; }
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:134:5: ( (lvl= level )? INT id= IDENTIFIER SEMI | (lvl= level )? INT id= IDENTIFIER LBRACKET size= INTEGER RBRACKET SEMI )
            int alt11=2;
            switch ( input.LA(1) ) {
            case LOW:
                {
                int LA11_1 = input.LA(2);

                if ( (LA11_1==INT) ) {
                    int LA11_3 = input.LA(3);

                    if ( (LA11_3==IDENTIFIER) ) {
                        int LA11_4 = input.LA(4);

                        if ( (LA11_4==SEMI) ) {
                            alt11=1;
                        }
                        else if ( (LA11_4==LBRACKET) ) {
                            alt11=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 11, 4, input);

                            throw nvae;
                        }
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 11, 3, input);

                        throw nvae;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 11, 1, input);

                    throw nvae;
                }
                }
                break;
            case HIGH:
                {
                int LA11_2 = input.LA(2);

                if ( (LA11_2==INT) ) {
                    int LA11_3 = input.LA(3);

                    if ( (LA11_3==IDENTIFIER) ) {
                        int LA11_4 = input.LA(4);

                        if ( (LA11_4==SEMI) ) {
                            alt11=1;
                        }
                        else if ( (LA11_4==LBRACKET) ) {
                            alt11=2;
                        }
                        else {
                            if (state.backtracking>0) {state.failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("", 11, 4, input);

                            throw nvae;
                        }
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 11, 3, input);

                        throw nvae;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 11, 2, input);

                    throw nvae;
                }
                }
                break;
            case INT:
                {
                int LA11_3 = input.LA(2);

                if ( (LA11_3==IDENTIFIER) ) {
                    int LA11_4 = input.LA(3);

                    if ( (LA11_4==SEMI) ) {
                        alt11=1;
                    }
                    else if ( (LA11_4==LBRACKET) ) {
                        alt11=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 11, 4, input);

                        throw nvae;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 11, 3, input);

                    throw nvae;
                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:134:7: (lvl= level )? INT id= IDENTIFIER SEMI
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:134:11: (lvl= level )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( ((LA9_0>=LOW && LA9_0<=HIGH)) ) {
                        alt9=1;
                    }
                    else if ( (LA9_0==INT) ) {
                        int LA9_2 = input.LA(2);

                        if ( (synpred20_TheLang()) ) {
                            alt9=1;
                        }
                    }
                    switch (alt9) {
                        case 1 :
                            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:0:0: lvl= level
                            {
                            pushFollow(FOLLOW_level_in_decl972);
                            lvl=level();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, lvl.getTree());

                            }
                            break;

                    }

                    INT23=(Token)match(input,INT,FOLLOW_INT_in_decl975); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INT23_tree = (CommonTree)adaptor.create(INT23);
                    adaptor.addChild(root_0, INT23_tree);
                    }
                    id=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_decl981); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    id_tree = (CommonTree)adaptor.create(id);
                    adaptor.addChild(root_0, id_tree);
                    }
                    SEMI24=(Token)match(input,SEMI,FOLLOW_SEMI_in_decl983); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SEMI24_tree = (CommonTree)adaptor.create(SEMI24);
                    adaptor.addChild(root_0, SEMI24_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.value = new Int(lvl.value, new Variable(Type.INT, id.getText()));
                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:135:7: (lvl= level )? INT id= IDENTIFIER LBRACKET size= INTEGER RBRACKET SEMI
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:135:11: (lvl= level )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( ((LA10_0>=LOW && LA10_0<=HIGH)) ) {
                        alt10=1;
                    }
                    else if ( (LA10_0==INT) ) {
                        int LA10_2 = input.LA(2);

                        if ( (synpred22_TheLang()) ) {
                            alt10=1;
                        }
                    }
                    switch (alt10) {
                        case 1 :
                            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:0:0: lvl= level
                            {
                            pushFollow(FOLLOW_level_in_decl998);
                            lvl=level();

                            state._fsp--;
                            if (state.failed) return retval;
                            if ( state.backtracking==0 ) adaptor.addChild(root_0, lvl.getTree());

                            }
                            break;

                    }

                    INT25=(Token)match(input,INT,FOLLOW_INT_in_decl1001); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    INT25_tree = (CommonTree)adaptor.create(INT25);
                    adaptor.addChild(root_0, INT25_tree);
                    }
                    id=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_decl1007); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    id_tree = (CommonTree)adaptor.create(id);
                    adaptor.addChild(root_0, id_tree);
                    }
                    LBRACKET26=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_decl1009); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LBRACKET26_tree = (CommonTree)adaptor.create(LBRACKET26);
                    adaptor.addChild(root_0, LBRACKET26_tree);
                    }
                    size=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_decl1015); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    size_tree = (CommonTree)adaptor.create(size);
                    adaptor.addChild(root_0, size_tree);
                    }
                    RBRACKET27=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_decl1017); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RBRACKET27_tree = (CommonTree)adaptor.create(RBRACKET27);
                    adaptor.addChild(root_0, RBRACKET27_tree);
                    }
                    SEMI28=(Token)match(input,SEMI,FOLLOW_SEMI_in_decl1019); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SEMI28_tree = (CommonTree)adaptor.create(SEMI28);
                    adaptor.addChild(root_0, SEMI28_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.value = new Array(lvl.value, new Variable(Type.ARRAY, id.getText()), new Constant(Integer.parseInt(size.getText())));
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 9, decl_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "decl"

    public static class level_return extends ParserRuleReturnScope {
        public Level value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "level"
    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:139:1: level returns [Level value] : ( LOW | HIGH | );
    public final TheLangParser.level_return level() throws RecognitionException {
        TheLangParser.level_return retval = new TheLangParser.level_return();
        retval.start = input.LT(1);
        int level_StartIndex = input.index();
        CommonTree root_0 = null;

        Token LOW29=null;
        Token HIGH30=null;

        CommonTree LOW29_tree=null;
        CommonTree HIGH30_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return retval; }
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:140:5: ( LOW | HIGH | )
            int alt12=3;
            switch ( input.LA(1) ) {
            case LOW:
                {
                alt12=1;
                }
                break;
            case HIGH:
                {
                alt12=2;
                }
                break;
            case EOF:
            case INT:
                {
                alt12=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
                case 1 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:140:7: LOW
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    LOW29=(Token)match(input,LOW,FOLLOW_LOW_in_level1044); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LOW29_tree = (CommonTree)adaptor.create(LOW29);
                    adaptor.addChild(root_0, LOW29_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.value = Level.LOW;
                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:141:7: HIGH
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    HIGH30=(Token)match(input,HIGH,FOLLOW_HIGH_in_level1056); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    HIGH30_tree = (CommonTree)adaptor.create(HIGH30);
                    adaptor.addChild(root_0, HIGH30_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.value = Level.HIGH;
                    }

                    }
                    break;
                case 3 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:142:7: 
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    if ( state.backtracking==0 ) {
                      retval.value = Level.UNKNOWN;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 10, level_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "level"

    public static class stmt_return extends ParserRuleReturnScope {
        public Statement value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "stmt"
    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:146:1: stmt returns [Statement value] : (statement1= assignStmt | statement2= skipStmt | statement3= readStmt | statement4= writeStmt | statement5= ifStmt | statement6= whileStmt );
    public final TheLangParser.stmt_return stmt() throws RecognitionException {
        TheLangParser.stmt_return retval = new TheLangParser.stmt_return();
        retval.start = input.LT(1);
        int stmt_StartIndex = input.index();
        CommonTree root_0 = null;

        TheLangParser.assignStmt_return statement1 = null;

        TheLangParser.skipStmt_return statement2 = null;

        TheLangParser.readStmt_return statement3 = null;

        TheLangParser.writeStmt_return statement4 = null;

        TheLangParser.ifStmt_return statement5 = null;

        TheLangParser.whileStmt_return statement6 = null;



        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return retval; }
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:147:6: (statement1= assignStmt | statement2= skipStmt | statement3= readStmt | statement4= writeStmt | statement5= ifStmt | statement6= whileStmt )
            int alt13=6;
            switch ( input.LA(1) ) {
            case IDENTIFIER:
                {
                alt13=1;
                }
                break;
            case SKIP:
                {
                alt13=2;
                }
                break;
            case READ:
                {
                alt13=3;
                }
                break;
            case WRITE:
                {
                alt13=4;
                }
                break;
            case IF:
                {
                alt13=5;
                }
                break;
            case WHILE:
                {
                alt13=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }

            switch (alt13) {
                case 1 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:147:8: statement1= assignStmt
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_assignStmt_in_stmt1093);
                    statement1=assignStmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statement1.getTree());
                    if ( state.backtracking==0 ) {
                      retval.value = statement1.value;
                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:148:8: statement2= skipStmt
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_skipStmt_in_stmt1108);
                    statement2=skipStmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statement2.getTree());
                    if ( state.backtracking==0 ) {
                      retval.value = statement2.value;
                    }

                    }
                    break;
                case 3 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:149:8: statement3= readStmt
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_readStmt_in_stmt1123);
                    statement3=readStmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statement3.getTree());
                    if ( state.backtracking==0 ) {
                      retval.value = statement3.value;
                    }

                    }
                    break;
                case 4 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:150:8: statement4= writeStmt
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_writeStmt_in_stmt1138);
                    statement4=writeStmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statement4.getTree());
                    if ( state.backtracking==0 ) {
                      retval.value = statement4.value;
                    }

                    }
                    break;
                case 5 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:151:8: statement5= ifStmt
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_ifStmt_in_stmt1152);
                    statement5=ifStmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statement5.getTree());
                    if ( state.backtracking==0 ) {
                      retval.value = statement5.value;
                    }

                    }
                    break;
                case 6 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:152:8: statement6= whileStmt
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_whileStmt_in_stmt1167);
                    statement6=whileStmt();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, statement6.getTree());
                    if ( state.backtracking==0 ) {
                      retval.value = statement6.value;
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 11, stmt_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "stmt"

    public static class assignStmt_return extends ParserRuleReturnScope {
        public Statement value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "assignStmt"
    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:156:1: assignStmt returns [Statement value] : (id= IDENTIFIER ASSIGN expr= aexpr SEMI | id= IDENTIFIER LBRACKET idx= aexpr RBRACKET ASSIGN expr= aexpr SEMI );
    public final TheLangParser.assignStmt_return assignStmt() throws RecognitionException {
        TheLangParser.assignStmt_return retval = new TheLangParser.assignStmt_return();
        retval.start = input.LT(1);
        int assignStmt_StartIndex = input.index();
        CommonTree root_0 = null;

        Token id=null;
        Token ASSIGN31=null;
        Token SEMI32=null;
        Token LBRACKET33=null;
        Token RBRACKET34=null;
        Token ASSIGN35=null;
        Token SEMI36=null;
        TheLangParser.aexpr_return expr = null;

        TheLangParser.aexpr_return idx = null;


        CommonTree id_tree=null;
        CommonTree ASSIGN31_tree=null;
        CommonTree SEMI32_tree=null;
        CommonTree LBRACKET33_tree=null;
        CommonTree RBRACKET34_tree=null;
        CommonTree ASSIGN35_tree=null;
        CommonTree SEMI36_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return retval; }
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:157:5: (id= IDENTIFIER ASSIGN expr= aexpr SEMI | id= IDENTIFIER LBRACKET idx= aexpr RBRACKET ASSIGN expr= aexpr SEMI )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==IDENTIFIER) ) {
                int LA14_1 = input.LA(2);

                if ( (LA14_1==ASSIGN) ) {
                    alt14=1;
                }
                else if ( (LA14_1==LBRACKET) ) {
                    alt14=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:157:7: id= IDENTIFIER ASSIGN expr= aexpr SEMI
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    id=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_assignStmt1196); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    id_tree = (CommonTree)adaptor.create(id);
                    adaptor.addChild(root_0, id_tree);
                    }
                    ASSIGN31=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_assignStmt1198); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ASSIGN31_tree = (CommonTree)adaptor.create(ASSIGN31);
                    adaptor.addChild(root_0, ASSIGN31_tree);
                    }
                    pushFollow(FOLLOW_aexpr_in_assignStmt1204);
                    expr=aexpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr.getTree());
                    SEMI32=(Token)match(input,SEMI,FOLLOW_SEMI_in_assignStmt1206); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SEMI32_tree = (CommonTree)adaptor.create(SEMI32);
                    adaptor.addChild(root_0, SEMI32_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.value = new Assignment(new Variable(Type.INT, id.getText()), expr.value);
                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:158:7: id= IDENTIFIER LBRACKET idx= aexpr RBRACKET ASSIGN expr= aexpr SEMI
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    id=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_assignStmt1220); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    id_tree = (CommonTree)adaptor.create(id);
                    adaptor.addChild(root_0, id_tree);
                    }
                    LBRACKET33=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_assignStmt1222); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LBRACKET33_tree = (CommonTree)adaptor.create(LBRACKET33);
                    adaptor.addChild(root_0, LBRACKET33_tree);
                    }
                    pushFollow(FOLLOW_aexpr_in_assignStmt1228);
                    idx=aexpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, idx.getTree());
                    RBRACKET34=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_assignStmt1230); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RBRACKET34_tree = (CommonTree)adaptor.create(RBRACKET34);
                    adaptor.addChild(root_0, RBRACKET34_tree);
                    }
                    ASSIGN35=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_assignStmt1232); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    ASSIGN35_tree = (CommonTree)adaptor.create(ASSIGN35);
                    adaptor.addChild(root_0, ASSIGN35_tree);
                    }
                    pushFollow(FOLLOW_aexpr_in_assignStmt1238);
                    expr=aexpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, expr.getTree());
                    SEMI36=(Token)match(input,SEMI,FOLLOW_SEMI_in_assignStmt1240); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SEMI36_tree = (CommonTree)adaptor.create(SEMI36);
                    adaptor.addChild(root_0, SEMI36_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.value = new ArrayAssignment(new Variable(Type.ARRAY, id.getText()), idx.value, expr.value);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 12, assignStmt_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "assignStmt"

    public static class skipStmt_return extends ParserRuleReturnScope {
        public Statement value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "skipStmt"
    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:162:1: skipStmt returns [Statement value] : SKIP SEMI ;
    public final TheLangParser.skipStmt_return skipStmt() throws RecognitionException {
        TheLangParser.skipStmt_return retval = new TheLangParser.skipStmt_return();
        retval.start = input.LT(1);
        int skipStmt_StartIndex = input.index();
        CommonTree root_0 = null;

        Token SKIP37=null;
        Token SEMI38=null;

        CommonTree SKIP37_tree=null;
        CommonTree SEMI38_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return retval; }
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:163:5: ( SKIP SEMI )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:163:7: SKIP SEMI
            {
            root_0 = (CommonTree)adaptor.nil();

            SKIP37=(Token)match(input,SKIP,FOLLOW_SKIP_in_skipStmt1265); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            SKIP37_tree = (CommonTree)adaptor.create(SKIP37);
            adaptor.addChild(root_0, SKIP37_tree);
            }
            SEMI38=(Token)match(input,SEMI,FOLLOW_SEMI_in_skipStmt1267); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            SEMI38_tree = (CommonTree)adaptor.create(SEMI38);
            adaptor.addChild(root_0, SEMI38_tree);
            }
            if ( state.backtracking==0 ) {
              retval.value = new Skip();
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 13, skipStmt_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "skipStmt"

    public static class readStmt_return extends ParserRuleReturnScope {
        public Statement value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "readStmt"
    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:167:1: readStmt returns [Statement value] : ( READ id= IDENTIFIER SEMI | READ id= IDENTIFIER LBRACKET idx= aexpr RBRACKET SEMI );
    public final TheLangParser.readStmt_return readStmt() throws RecognitionException {
        TheLangParser.readStmt_return retval = new TheLangParser.readStmt_return();
        retval.start = input.LT(1);
        int readStmt_StartIndex = input.index();
        CommonTree root_0 = null;

        Token id=null;
        Token READ39=null;
        Token SEMI40=null;
        Token READ41=null;
        Token LBRACKET42=null;
        Token RBRACKET43=null;
        Token SEMI44=null;
        TheLangParser.aexpr_return idx = null;


        CommonTree id_tree=null;
        CommonTree READ39_tree=null;
        CommonTree SEMI40_tree=null;
        CommonTree READ41_tree=null;
        CommonTree LBRACKET42_tree=null;
        CommonTree RBRACKET43_tree=null;
        CommonTree SEMI44_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return retval; }
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:168:5: ( READ id= IDENTIFIER SEMI | READ id= IDENTIFIER LBRACKET idx= aexpr RBRACKET SEMI )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==READ) ) {
                int LA15_1 = input.LA(2);

                if ( (LA15_1==IDENTIFIER) ) {
                    int LA15_2 = input.LA(3);

                    if ( (LA15_2==SEMI) ) {
                        alt15=1;
                    }
                    else if ( (LA15_2==LBRACKET) ) {
                        alt15=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 15, 2, input);

                        throw nvae;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 15, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:168:7: READ id= IDENTIFIER SEMI
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    READ39=(Token)match(input,READ,FOLLOW_READ_in_readStmt1292); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    READ39_tree = (CommonTree)adaptor.create(READ39);
                    adaptor.addChild(root_0, READ39_tree);
                    }
                    id=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_readStmt1298); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    id_tree = (CommonTree)adaptor.create(id);
                    adaptor.addChild(root_0, id_tree);
                    }
                    SEMI40=(Token)match(input,SEMI,FOLLOW_SEMI_in_readStmt1300); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SEMI40_tree = (CommonTree)adaptor.create(SEMI40);
                    adaptor.addChild(root_0, SEMI40_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.value = new Read(new Variable(Type.INT, id.getText()));
                    }

                    }
                    break;
                case 2 :
                    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:169:7: READ id= IDENTIFIER LBRACKET idx= aexpr RBRACKET SEMI
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    READ41=(Token)match(input,READ,FOLLOW_READ_in_readStmt1310); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    READ41_tree = (CommonTree)adaptor.create(READ41);
                    adaptor.addChild(root_0, READ41_tree);
                    }
                    id=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_readStmt1316); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    id_tree = (CommonTree)adaptor.create(id);
                    adaptor.addChild(root_0, id_tree);
                    }
                    LBRACKET42=(Token)match(input,LBRACKET,FOLLOW_LBRACKET_in_readStmt1318); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    LBRACKET42_tree = (CommonTree)adaptor.create(LBRACKET42);
                    adaptor.addChild(root_0, LBRACKET42_tree);
                    }
                    pushFollow(FOLLOW_aexpr_in_readStmt1324);
                    idx=aexpr();

                    state._fsp--;
                    if (state.failed) return retval;
                    if ( state.backtracking==0 ) adaptor.addChild(root_0, idx.getTree());
                    RBRACKET43=(Token)match(input,RBRACKET,FOLLOW_RBRACKET_in_readStmt1326); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    RBRACKET43_tree = (CommonTree)adaptor.create(RBRACKET43);
                    adaptor.addChild(root_0, RBRACKET43_tree);
                    }
                    SEMI44=(Token)match(input,SEMI,FOLLOW_SEMI_in_readStmt1328); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                    SEMI44_tree = (CommonTree)adaptor.create(SEMI44);
                    adaptor.addChild(root_0, SEMI44_tree);
                    }
                    if ( state.backtracking==0 ) {
                      retval.value = new ReadArray(new Variable(Type.INT, id.getText()), idx.value);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 14, readStmt_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "readStmt"

    public static class writeStmt_return extends ParserRuleReturnScope {
        public Statement value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "writeStmt"
    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:173:1: writeStmt returns [Statement value] : WRITE expr= aexpr SEMI ;
    public final TheLangParser.writeStmt_return writeStmt() throws RecognitionException {
        TheLangParser.writeStmt_return retval = new TheLangParser.writeStmt_return();
        retval.start = input.LT(1);
        int writeStmt_StartIndex = input.index();
        CommonTree root_0 = null;

        Token WRITE45=null;
        Token SEMI46=null;
        TheLangParser.aexpr_return expr = null;


        CommonTree WRITE45_tree=null;
        CommonTree SEMI46_tree=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return retval; }
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:174:5: ( WRITE expr= aexpr SEMI )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:174:7: WRITE expr= aexpr SEMI
            {
            root_0 = (CommonTree)adaptor.nil();

            WRITE45=(Token)match(input,WRITE,FOLLOW_WRITE_in_writeStmt1353); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            WRITE45_tree = (CommonTree)adaptor.create(WRITE45);
            adaptor.addChild(root_0, WRITE45_tree);
            }
            pushFollow(FOLLOW_aexpr_in_writeStmt1359);
            expr=aexpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, expr.getTree());
            SEMI46=(Token)match(input,SEMI,FOLLOW_SEMI_in_writeStmt1361); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            SEMI46_tree = (CommonTree)adaptor.create(SEMI46);
            adaptor.addChild(root_0, SEMI46_tree);
            }
            if ( state.backtracking==0 ) {
              retval.value = new Write(expr.value);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 15, writeStmt_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "writeStmt"

    public static class ifStmt_return extends ParserRuleReturnScope {
        public Statement value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "ifStmt"
    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:178:1: ifStmt returns [Statement value] : IF exp= bexpr THEN (trueStmt= stmt )+ ELSE (falseStmt= stmt )+ FI ;
    public final TheLangParser.ifStmt_return ifStmt() throws RecognitionException {
        TheLangParser.ifStmt_return retval = new TheLangParser.ifStmt_return();
        retval.start = input.LT(1);
        int ifStmt_StartIndex = input.index();
        CommonTree root_0 = null;

        Token IF47=null;
        Token THEN48=null;
        Token ELSE49=null;
        Token FI50=null;
        TheLangParser.bexpr_return exp = null;

        TheLangParser.stmt_return trueStmt = null;

        TheLangParser.stmt_return falseStmt = null;


        CommonTree IF47_tree=null;
        CommonTree THEN48_tree=null;
        CommonTree ELSE49_tree=null;
        CommonTree FI50_tree=null;


            	ArrayList<Statement> trueList = new ArrayList<Statement>();
        	ArrayList<Statement> falseList = new ArrayList<Statement>();
            
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return retval; }
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:184:5: ( IF exp= bexpr THEN (trueStmt= stmt )+ ELSE (falseStmt= stmt )+ FI )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:184:7: IF exp= bexpr THEN (trueStmt= stmt )+ ELSE (falseStmt= stmt )+ FI
            {
            root_0 = (CommonTree)adaptor.nil();

            IF47=(Token)match(input,IF,FOLLOW_IF_in_ifStmt1399); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            IF47_tree = (CommonTree)adaptor.create(IF47);
            adaptor.addChild(root_0, IF47_tree);
            }
            pushFollow(FOLLOW_bexpr_in_ifStmt1405);
            exp=bexpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exp.getTree());
            THEN48=(Token)match(input,THEN,FOLLOW_THEN_in_ifStmt1407); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            THEN48_tree = (CommonTree)adaptor.create(THEN48);
            adaptor.addChild(root_0, THEN48_tree);
            }
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:184:27: (trueStmt= stmt )+
            int cnt16=0;
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==IF||LA16_0==WHILE||(LA16_0>=SKIP && LA16_0<=READ)||LA16_0==IDENTIFIER) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:184:28: trueStmt= stmt
            	    {
            	    pushFollow(FOLLOW_stmt_in_ifStmt1414);
            	    trueStmt=stmt();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, trueStmt.getTree());
            	    if ( state.backtracking==0 ) {
            	      trueList.add(trueStmt.value);
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt16 >= 1 ) break loop16;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(16, input);
                        throw eee;
                }
                cnt16++;
            } while (true);

            ELSE49=(Token)match(input,ELSE,FOLLOW_ELSE_in_ifStmt1420); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            ELSE49_tree = (CommonTree)adaptor.create(ELSE49);
            adaptor.addChild(root_0, ELSE49_tree);
            }
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:184:83: (falseStmt= stmt )+
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==IF||LA17_0==WHILE||(LA17_0>=SKIP && LA17_0<=READ)||LA17_0==IDENTIFIER) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:184:84: falseStmt= stmt
            	    {
            	    pushFollow(FOLLOW_stmt_in_ifStmt1427);
            	    falseStmt=stmt();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, falseStmt.getTree());
            	    if ( state.backtracking==0 ) {
            	      falseList.add(falseStmt.value);
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt17 >= 1 ) break loop17;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(17, input);
                        throw eee;
                }
                cnt17++;
            } while (true);

            FI50=(Token)match(input,FI,FOLLOW_FI_in_ifStmt1433); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            FI50_tree = (CommonTree)adaptor.create(FI50);
            adaptor.addChild(root_0, FI50_tree);
            }
            if ( state.backtracking==0 ) {
              retval.value = new If(exp.value, trueList, falseList);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 16, ifStmt_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "ifStmt"

    public static class whileStmt_return extends ParserRuleReturnScope {
        public Statement value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "whileStmt"
    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:189:1: whileStmt returns [Statement value] : WHILE exp= bexpr DO (statement= stmt )+ OD ;
    public final TheLangParser.whileStmt_return whileStmt() throws RecognitionException {
        TheLangParser.whileStmt_return retval = new TheLangParser.whileStmt_return();
        retval.start = input.LT(1);
        int whileStmt_StartIndex = input.index();
        CommonTree root_0 = null;

        Token WHILE51=null;
        Token DO52=null;
        Token OD53=null;
        TheLangParser.bexpr_return exp = null;

        TheLangParser.stmt_return statement = null;


        CommonTree WHILE51_tree=null;
        CommonTree DO52_tree=null;
        CommonTree OD53_tree=null;


            	ArrayList<Statement> body = new ArrayList<Statement>();
            
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return retval; }
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:194:5: ( WHILE exp= bexpr DO (statement= stmt )+ OD )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:194:7: WHILE exp= bexpr DO (statement= stmt )+ OD
            {
            root_0 = (CommonTree)adaptor.nil();

            WHILE51=(Token)match(input,WHILE,FOLLOW_WHILE_in_whileStmt1484); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            WHILE51_tree = (CommonTree)adaptor.create(WHILE51);
            adaptor.addChild(root_0, WHILE51_tree);
            }
            pushFollow(FOLLOW_bexpr_in_whileStmt1490);
            exp=bexpr();

            state._fsp--;
            if (state.failed) return retval;
            if ( state.backtracking==0 ) adaptor.addChild(root_0, exp.getTree());
            DO52=(Token)match(input,DO,FOLLOW_DO_in_whileStmt1492); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            DO52_tree = (CommonTree)adaptor.create(DO52);
            adaptor.addChild(root_0, DO52_tree);
            }
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:194:28: (statement= stmt )+
            int cnt18=0;
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==IF||LA18_0==WHILE||(LA18_0>=SKIP && LA18_0<=READ)||LA18_0==IDENTIFIER) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:194:29: statement= stmt
            	    {
            	    pushFollow(FOLLOW_stmt_in_whileStmt1499);
            	    statement=stmt();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, statement.getTree());
            	    if ( state.backtracking==0 ) {
            	      body.add(statement.value);
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt18 >= 1 ) break loop18;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(18, input);
                        throw eee;
                }
                cnt18++;
            } while (true);

            OD53=(Token)match(input,OD,FOLLOW_OD_in_whileStmt1505); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            OD53_tree = (CommonTree)adaptor.create(OD53);
            adaptor.addChild(root_0, OD53_tree);
            }
            if ( state.backtracking==0 ) {
              retval.value = new While(exp.value, body);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 17, whileStmt_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "whileStmt"

    public static class program_return extends ParserRuleReturnScope {
        public Program value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "program"
    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:199:1: program returns [Program value] : PROGRAM (declaration= decl )* (statement= stmt )+ END ;
    public final TheLangParser.program_return program() throws RecognitionException {
        TheLangParser.program_return retval = new TheLangParser.program_return();
        retval.start = input.LT(1);
        int program_StartIndex = input.index();
        CommonTree root_0 = null;

        Token PROGRAM54=null;
        Token END55=null;
        TheLangParser.decl_return declaration = null;

        TheLangParser.stmt_return statement = null;


        CommonTree PROGRAM54_tree=null;
        CommonTree END55_tree=null;


            	ArrayList<Declaration> declList = new ArrayList<Declaration>();
            	ArrayList<Statement> stmtList = new ArrayList<Statement>();
            
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return retval; }
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:205:5: ( PROGRAM (declaration= decl )* (statement= stmt )+ END )
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:205:7: PROGRAM (declaration= decl )* (statement= stmt )+ END
            {
            root_0 = (CommonTree)adaptor.nil();

            PROGRAM54=(Token)match(input,PROGRAM,FOLLOW_PROGRAM_in_program1550); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            PROGRAM54_tree = (CommonTree)adaptor.create(PROGRAM54);
            adaptor.addChild(root_0, PROGRAM54_tree);
            }
            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:205:15: (declaration= decl )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==EOF||(LA19_0>=INT && LA19_0<=HIGH)) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:205:16: declaration= decl
            	    {
            	    pushFollow(FOLLOW_decl_in_program1557);
            	    declaration=decl();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, declaration.getTree());
            	    if ( state.backtracking==0 ) {
            	      declList.add((declaration!=null?declaration.value:null));
            	    }

            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

            // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:205:73: (statement= stmt )+
            int cnt20=0;
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==IF||LA20_0==WHILE||(LA20_0>=SKIP && LA20_0<=READ)||LA20_0==IDENTIFIER) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:205:74: statement= stmt
            	    {
            	    pushFollow(FOLLOW_stmt_in_program1568);
            	    statement=stmt();

            	    state._fsp--;
            	    if (state.failed) return retval;
            	    if ( state.backtracking==0 ) adaptor.addChild(root_0, statement.getTree());
            	    if ( state.backtracking==0 ) {
            	      stmtList.add((statement!=null?statement.value:null));
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt20 >= 1 ) break loop20;
            	    if (state.backtracking>0) {state.failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(20, input);
                        throw eee;
                }
                cnt20++;
            } while (true);

            END55=(Token)match(input,END,FOLLOW_END_in_program1574); if (state.failed) return retval;
            if ( state.backtracking==0 ) {
            END55_tree = (CommonTree)adaptor.create(END55);
            adaptor.addChild(root_0, END55_tree);
            }
            if ( state.backtracking==0 ) {
              retval.value = new Program(declList, stmtList);
            }

            }

            retval.stop = input.LT(-1);

            if ( state.backtracking==0 ) {

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }
        finally {
            if ( state.backtracking>0 ) { memoize(input, 18, program_StartIndex); }
        }
        return retval;
    }
    // $ANTLR end "program"

    // $ANTLR start synpred9_TheLang
    public final void synpred9_TheLang_fragment() throws RecognitionException {   
        TheLangParser.bexpr1_return expr = null;


        // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:104:8: ( OR expr= bexpr1 )
        // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:104:8: OR expr= bexpr1
        {
        match(input,OR,FOLLOW_OR_in_synpred9_TheLang717); if (state.failed) return ;
        pushFollow(FOLLOW_bexpr1_in_synpred9_TheLang723);
        expr=bexpr1();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred9_TheLang

    // $ANTLR start synpred10_TheLang
    public final void synpred10_TheLang_fragment() throws RecognitionException {   
        TheLangParser.bexpr2_return expr = null;


        // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:110:8: ( AND expr= bexpr2 )
        // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:110:8: AND expr= bexpr2
        {
        match(input,AND,FOLLOW_AND_in_synpred10_TheLang765); if (state.failed) return ;
        pushFollow(FOLLOW_bexpr2_in_synpred10_TheLang771);
        expr=bexpr2();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred10_TheLang

    // $ANTLR start synpred11_TheLang
    public final void synpred11_TheLang_fragment() throws RecognitionException {   
        TheLangParser.aexpr_return expr1 = null;

        TheLangParser.opr_return op = null;

        TheLangParser.aexpr_return expr2 = null;


        // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:115:7: (expr1= aexpr op= opr expr2= aexpr )
        // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:115:7: expr1= aexpr op= opr expr2= aexpr
        {
        pushFollow(FOLLOW_aexpr_in_synpred11_TheLang802);
        expr1=aexpr();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_opr_in_synpred11_TheLang808);
        op=opr();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_aexpr_in_synpred11_TheLang814);
        expr2=aexpr();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred11_TheLang

    // $ANTLR start synpred20_TheLang
    public final void synpred20_TheLang_fragment() throws RecognitionException {   
        TheLangParser.level_return lvl = null;


        // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:134:11: (lvl= level )
        // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:134:11: lvl= level
        {
        pushFollow(FOLLOW_level_in_synpred20_TheLang972);
        lvl=level();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred20_TheLang

    // $ANTLR start synpred22_TheLang
    public final void synpred22_TheLang_fragment() throws RecognitionException {   
        TheLangParser.level_return lvl = null;


        // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:135:11: (lvl= level )
        // C:\\Users\\Ibrahim\\workspace\\parser\\src\\TheLang.g:135:11: lvl= level
        {
        pushFollow(FOLLOW_level_in_synpred22_TheLang998);
        lvl=level();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred22_TheLang

    // Delegated rules

    public final boolean synpred22_TheLang() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred22_TheLang_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred20_TheLang() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred20_TheLang_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred10_TheLang() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred10_TheLang_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred9_TheLang() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred9_TheLang_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred11_TheLang() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred11_TheLang_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_aexpr1_in_aexpr440 = new BitSet(new long[]{0x000000000000C002L});
    public static final BitSet FOLLOW_PLUS_in_aexpr453 = new BitSet(new long[]{0x0000180000088000L});
    public static final BitSet FOLLOW_aexpr1_in_aexpr459 = new BitSet(new long[]{0x000000000000C002L});
    public static final BitSet FOLLOW_MINUS_in_aexpr471 = new BitSet(new long[]{0x0000180000088000L});
    public static final BitSet FOLLOW_aexpr1_in_aexpr477 = new BitSet(new long[]{0x000000000000C002L});
    public static final BitSet FOLLOW_aexpr2_in_aexpr1507 = new BitSet(new long[]{0x0000000000030002L});
    public static final BitSet FOLLOW_MUL_in_aexpr1520 = new BitSet(new long[]{0x0000180000088000L});
    public static final BitSet FOLLOW_aexpr2_in_aexpr1526 = new BitSet(new long[]{0x0000000000030002L});
    public static final BitSet FOLLOW_DIV_in_aexpr1539 = new BitSet(new long[]{0x0000180000088000L});
    public static final BitSet FOLLOW_aexpr2_in_aexpr1545 = new BitSet(new long[]{0x0000000000030002L});
    public static final BitSet FOLLOW_MINUS_in_aexpr2571 = new BitSet(new long[]{0x0000180000088000L});
    public static final BitSet FOLLOW_aexpr3_in_aexpr2577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_aexpr3_in_aexpr2591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_aexpr3620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_aexpr3634 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LBRACKET_in_aexpr3636 = new BitSet(new long[]{0x0000180000088000L});
    public static final BitSet FOLLOW_aexpr_in_aexpr3642 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RBRACKET_in_aexpr3644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_aexpr3659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_aexpr3669 = new BitSet(new long[]{0x0000180000088000L});
    public static final BitSet FOLLOW_aexpr_in_aexpr3675 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_RPAREN_in_aexpr3677 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_bexpr1_in_bexpr705 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_OR_in_bexpr717 = new BitSet(new long[]{0x000018C0000C8000L});
    public static final BitSet FOLLOW_bexpr1_in_bexpr723 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_bexpr2_in_bexpr1753 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_AND_in_bexpr1765 = new BitSet(new long[]{0x000018C0000C8000L});
    public static final BitSet FOLLOW_bexpr2_in_bexpr1771 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_aexpr_in_bexpr2802 = new BitSet(new long[]{0x0000000000003F00L});
    public static final BitSet FOLLOW_opr_in_bexpr2808 = new BitSet(new long[]{0x0000180000088000L});
    public static final BitSet FOLLOW_aexpr_in_bexpr2814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_bexpr2824 = new BitSet(new long[]{0x000018C0000C8000L});
    public static final BitSet FOLLOW_bexpr_in_bexpr2830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_bexpr2841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_bexpr2851 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_bexpr2861 = new BitSet(new long[]{0x000018C0000C8000L});
    public static final BitSet FOLLOW_bexpr_in_bexpr2867 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_RPAREN_in_bexpr2869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GT_in_opr894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GE_in_opr904 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LT_in_opr914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LE_in_opr924 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQ_in_opr934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEQ_in_opr944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_level_in_decl972 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_INT_in_decl975 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_decl981 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_SEMI_in_decl983 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_level_in_decl998 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_INT_in_decl1001 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_decl1007 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LBRACKET_in_decl1009 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_INTEGER_in_decl1015 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RBRACKET_in_decl1017 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_SEMI_in_decl1019 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LOW_in_level1044 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HIGH_in_level1056 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignStmt_in_stmt1093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_skipStmt_in_stmt1108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_readStmt_in_stmt1123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_writeStmt_in_stmt1138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStmt_in_stmt1152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whileStmt_in_stmt1167 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_assignStmt1196 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ASSIGN_in_assignStmt1198 = new BitSet(new long[]{0x0000180000088000L});
    public static final BitSet FOLLOW_aexpr_in_assignStmt1204 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_SEMI_in_assignStmt1206 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_assignStmt1220 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LBRACKET_in_assignStmt1222 = new BitSet(new long[]{0x0000180000088000L});
    public static final BitSet FOLLOW_aexpr_in_assignStmt1228 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RBRACKET_in_assignStmt1230 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_ASSIGN_in_assignStmt1232 = new BitSet(new long[]{0x0000180000088000L});
    public static final BitSet FOLLOW_aexpr_in_assignStmt1238 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_SEMI_in_assignStmt1240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SKIP_in_skipStmt1265 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_SEMI_in_skipStmt1267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_READ_in_readStmt1292 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_readStmt1298 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_SEMI_in_readStmt1300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_READ_in_readStmt1310 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_readStmt1316 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_LBRACKET_in_readStmt1318 = new BitSet(new long[]{0x0000180000088000L});
    public static final BitSet FOLLOW_aexpr_in_readStmt1324 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_RBRACKET_in_readStmt1326 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_SEMI_in_readStmt1328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WRITE_in_writeStmt1353 = new BitSet(new long[]{0x0000180000088000L});
    public static final BitSet FOLLOW_aexpr_in_writeStmt1359 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_SEMI_in_writeStmt1361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_ifStmt1399 = new BitSet(new long[]{0x000018C0000C8000L});
    public static final BitSet FOLLOW_bexpr_in_ifStmt1405 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_THEN_in_ifStmt1407 = new BitSet(new long[]{0x0000080E44000000L});
    public static final BitSet FOLLOW_stmt_in_ifStmt1414 = new BitSet(new long[]{0x0000080E54000000L});
    public static final BitSet FOLLOW_ELSE_in_ifStmt1420 = new BitSet(new long[]{0x0000080E44000000L});
    public static final BitSet FOLLOW_stmt_in_ifStmt1427 = new BitSet(new long[]{0x0000080E64000000L});
    public static final BitSet FOLLOW_FI_in_ifStmt1433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHILE_in_whileStmt1484 = new BitSet(new long[]{0x000018C0000C8000L});
    public static final BitSet FOLLOW_bexpr_in_whileStmt1490 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_DO_in_whileStmt1492 = new BitSet(new long[]{0x0000080E44000000L});
    public static final BitSet FOLLOW_stmt_in_whileStmt1499 = new BitSet(new long[]{0x0000080F44000000L});
    public static final BitSet FOLLOW_OD_in_whileStmt1505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROGRAM_in_program1550 = new BitSet(new long[]{0x00000F0E44000000L});
    public static final BitSet FOLLOW_decl_in_program1557 = new BitSet(new long[]{0x00000F0E44000000L});
    public static final BitSet FOLLOW_stmt_in_program1568 = new BitSet(new long[]{0x0000082E44000000L});
    public static final BitSet FOLLOW_END_in_program1574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OR_in_synpred9_TheLang717 = new BitSet(new long[]{0x000018C0000C8000L});
    public static final BitSet FOLLOW_bexpr1_in_synpred9_TheLang723 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AND_in_synpred10_TheLang765 = new BitSet(new long[]{0x000018C0000C8000L});
    public static final BitSet FOLLOW_bexpr2_in_synpred10_TheLang771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_aexpr_in_synpred11_TheLang802 = new BitSet(new long[]{0x0000000000003F00L});
    public static final BitSet FOLLOW_opr_in_synpred11_TheLang808 = new BitSet(new long[]{0x0000180000088000L});
    public static final BitSet FOLLOW_aexpr_in_synpred11_TheLang814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_level_in_synpred20_TheLang972 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_level_in_synpred22_TheLang998 = new BitSet(new long[]{0x0000000000000002L});

}