package syntaxtree.statement;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

import analysis.Definition;
import analysis.DefinitionSet;
import analysis.RDProgramState;
import flowgraph.datastructure.FlowSet;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;
import flowgraph.datastructure.VariableSet;
import syntaxtree.expression.Expression;
import syntaxtree.expression.Variable;

/**
 * Data representation for read statements (arrays only)
 *
 */
public class ReadArray extends Statement {

    private Variable id;
    private Expression idx;

    public ReadArray(Variable id, Expression idx) {
        this.id = id;
        this.idx = idx;
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

    @Override
    public String toString() {
        return "\nClass: " + getClass().getSimpleName() + "\nIdentifier: " + id.toString() + "\nIndex: " + idx.toString() + "\n";
    }

    @Override
    public RDProgramState RD(RDProgramState currentState) {
     	//RDentry
        DefinitionSet exit = currentState.getRDExit(getLabel()-1);
    	currentState.addRDentry(getLabel(), exit);

    	//RDexit
        DefinitionSet entry = currentState.getRDEntry(getLabel());
    	//killRD(read A[a]) = {(A, l'}| b(l') is a declaration or an assignment to A[]}    	
    	entry.removeAll(currentState.kill(id, entry));
    	//genRD(read A[a]) = {(A[a], l)}
    	entry.addAll(currentState.gen(id, new Node(this)));

    	currentState.addRDexit(getLabel(), entry);
        return currentState;
    }

    @Override
    public DefinitionSet killed(RDProgramState currentState) {
        DefinitionSet entry = currentState.getRDEntry(getLabel());
        return currentState.kill(id, entry);
    }

    @Override
    public DefinitionSet generated(RDProgramState currentState) {
        return currentState.gen(id, new Node(this));
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
    			.union(idx.getVariable());
    }
    
}
