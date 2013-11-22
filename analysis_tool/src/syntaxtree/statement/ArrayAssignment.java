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
import syntaxtree.expression.Variable;
import syntaxtree.expression.Expression;

/**
 * Data representation for assignments (arrays only)
 *
 */
public class ArrayAssignment extends Statement {

    private Variable id;
    private Expression idx;
    private Expression expr;

    public ArrayAssignment(Variable id, Expression idx, Expression expr) {
        this.id = id;
        this.idx = idx;
        this.expr = expr;
    }

    public Variable getId() {
        return id;
    }

    public void setId(Variable id) {
        this.id = id;
    }

    public Expression getIdx() {
        return idx;
    }

    public void setIdx(Expression idx) {
        this.idx = idx;
    }

    public Expression getExpr() {
        return expr;
    }

    public void setExpr(Expression expr) {
        this.expr = expr;
    }

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\nIdentifier: " + id.toString() + "\nIndex: " + idx.toString() + "\nExpression: " + expr.toString() + "\n";
    }

    @Override
    public String toString() {
        return id + "[" + idx + "] := " + expr + ";";
    }

    public String toStringWithLabel() {
        return Symbols.LSQPARAN + this.toString() + Symbols.RSQPARAN + 
                Symbols.SEPERATOR + this.getLabel();
    }

    @Override
    public RDProgramState RD(RDProgramState currentState) {
    	//RDentry
        TreeSet<Definition> exit = currentState.getRDExit(getLabel()-1);
    	currentState.addRDentry(getLabel(), exit);

    	//RDexit
        TreeSet<Definition> entry = currentState.getRDEntry(getLabel());
    	//killRD(read A[a]) = {(A, l'}| b(l') is a declaration or an assignment to A[]}    	
    	entry.removeAll(currentState.kill(id, entry));
    	//genRD(read A[a]) = {(A[a], l)}
    	entry.addAll(currentState.gen(id, new Node(this)));

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
    	return VariableSet.factory().addVariable(id)
    			.union(idx.getVariable())
    			.union(expr.getVariable());
    }
       
}
