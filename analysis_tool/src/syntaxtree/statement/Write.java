package syntaxtree.statement;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

import analysis.Definition;
import analysis.RDProgramState;
import flowgraph.datastructure.FlowSet;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;
import flowgraph.datastructure.VariableSet;
import syntaxtree.Symbols;
import syntaxtree.expression.Expression;

/**
 * Data representation for read statements
 *
 */
public class Write extends Statement{

    private Expression expr;

    public Write(Expression expr) {
        this.expr = expr;
    }

    public Expression getExpr() {
        return expr;
    }

    public void setExpr(Expression expr) {
        this.expr = expr;
    }

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\nExpression: " + expr.toString() + "\n";
    }

    @Override
    public String toString() {
        return Symbols.WRITE + Symbols.SEPERATOR + expr;
    }

    @Override
    public RDProgramState RD(RDProgramState currentState) {
    	//RDentry
        TreeSet<Definition> exit = currentState.getRDExit(getLabel()-1);
    	currentState.addRDentry(getLabel(), exit);

    	//RDexit
        TreeSet<Definition> entry = currentState.getRDEntry(getLabel());
    	//killRD([write a]l) = �
    	//genRD([[write a]l) = �    	
    	currentState.addRDexit(getLabel(), entry);
    	  	
        return currentState;

    }
    
    @Override
    public NodeSet labels() {
        return NodeSet.factory().addNode(new Node(this));
    }

    @Override
    public Node init() {
        return (new Node(this));
    }

    @Override
    public NodeSet finalNodes() {
        return NodeSet.factory().addNode(new Node(this));
    }

    @Override
    public FlowSet flow() {
        return FlowSet.emptySet;
    }

    @Override
    public VariableSet getVariable() {
    	return expr.getVariable();
    }
     
}
