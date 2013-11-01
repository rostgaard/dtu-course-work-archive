
import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

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

        String inputfile = "/home/krc/DTU/02242 Program Analysis E13/work/src/examples/sample.lang";
        TheLangLexer lex = new TheLangLexer(new ANTLRFileStream(inputfile));
        CommonTokenStream tokens = new CommonTokenStream(lex);
        TheLangParser parser = new TheLangParser(tokens);

        //int[] array = new int[-100];
        //array[-1] = 1;
        //System.out.println(array[-1]);
        

        try {

            TheLangParser.program_return parserResult = parser.program();
            if (parserResult != null) {
                CommonTree tree = (CommonTree) parserResult.tree;
        printTree(tree,0);

                for (int i = 0; i < tree.getChildCount(); i++) {
                    System.out.println(i + ": " + tree.getChild(i).getChildCount());
                }


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
