
import java.util.List;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

import output.TheLangLexer;
import output.TheLangParser;
import syntaxtree.Program;
import syntaxtree.declaration.Declaration;
import syntaxtree.statement.Statement;

public class Main {

    public static void printTree(CommonTree t, int indent) {
        if (t != null) {
            StringBuffer sb = new StringBuffer(indent);

            if (t.getParent() == null) {
                System.out.println(sb.toString() + 
                        t.getClass().toString());
            }
            for (int i = 0; i < indent; i++) {
                sb = sb.append("   ");
            }
            for (int i = 0; i < t.getChildCount(); i++) {
                System.out.println(sb.toString() + t.getChild(i).toString());
                printTree((CommonTree) t.getChild(i), indent + 1);
            }
        }
    }

    public static void main(String args[]) throws Exception {

        if (args.length < 1) {
            System.out.println("Please supply a file with source code.");
            System.exit(1);
        }
        
        String inputfile = args[0];
        System.out.println("input file: " + inputfile);
        TheLangLexer lex = new TheLangLexer(new ANTLRFileStream(inputfile));
        CommonTokenStream tokens = new CommonTokenStream(lex);
        TheLangParser parser = new TheLangParser(tokens);

        //int[] array = new int[-100];
        //array[-1] = 1;
        //System.out.println(array[-1]);
        

        try {

            TheLangParser.program_return parserResult = parser.program();
            if (parserResult != null) {
                Program tree = parserResult.value;
                List<Declaration> decls = tree.getDecls();
                System.out.println("Number of declarations: " + decls.size());
                for(Declaration d : decls){
                	System.out.println(d.toString());
                }
                List<Statement> stmts = tree.getStmts();
                System.out.println("Number of statements: " + stmts.size());
//                for(Statement stmt : stmts){
//                	
//                }
                //printTree(tree,0);
                


//        System.out.println(tree.getChild());
//        System.out.println(tree.get);
//        for(Object a : tree.getChildren()){
////        	System.out.println(a.getClass().getSimpleName());
//        	System.out.println(a.toString());
////        	//.getName()
//        }

//        System.out.println(tree.toStringTree());
            }
        } catch (RecognitionException e) {
            e.printStackTrace();
        }
    }
}
