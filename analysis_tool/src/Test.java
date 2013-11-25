
import analysis.Definition;
import analysis.DefinitionSet;
import analysis.RDLattice;
import edu.uci.ics.jung.graph.Tree;
import flowgraph.datastructure.Node;
import syntaxtree.AbstractSyntaxTree;
import syntaxtree.DeclarationList;
import syntaxtree.Type;
import syntaxtree.declaration.Declaration;
import syntaxtree.declaration.Int;
import syntaxtree.declaration.Level;
import syntaxtree.expression.Variable;
import syntaxtree.statement.Assignment;
import syntaxtree.statement.Skip;

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
        DefinitionSet ds1 = new DefinitionSet();
        DefinitionSet ds2 = new DefinitionSet();

        System.out.println("subset:" +ds1.subsetOf(ds2));
        
        ds1.add(new Definition(new Node(new Skip())));
        System.out.println("ds1:" +ds1);
        
        System.out.println("subset:" +ds1.subsetOf(ds2));
        
        DeclarationList dl1 = new DeclarationList();
        DeclarationList dl2 = new DeclarationList();
        dl1.add(new Int(Level.LOW, new Variable(Type.INT, "a")));
        
        RDLattice rdl1  = new RDLattice(dl1);
        RDLattice rdl2 = new RDLattice(dl2);
        
        System.out.println("subset:" +rdl1.factory().subsetOf(rdl2.factory()));
        System.out.println("subset:" +rdl2.factory().subsetOf(rdl1.factory()));

        System.out.println("HELOO");
        AST.addVertex("Program");
        AST.addEdge(1, "Program", "IF");
        
        System.out.println(AST);
        

    }
}
