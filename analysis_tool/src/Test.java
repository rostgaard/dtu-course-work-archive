
import edu.uci.ics.jung.graph.Tree;
import syntaxtree.AbstractSyntaxTree;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author krc
 */
public class Test {

    public static AbstractSyntaxTree AST = new AbstractSyntaxTree();

    public static void main(String[] args) {
        System.out.println("HELOO");
        AST.addVertex("Program");
        AST.addEdge(1, "Program", "IF");
        
        System.out.println(AST);
        

    }
}
