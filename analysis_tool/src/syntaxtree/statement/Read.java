package syntaxtree.statement;

import java.util.ArrayList;

import analysis.Definition;
import analysis.RDProgramState;
import flowgraph.datastructure.FlowSet;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;
import flowgraph.datastructure.VariableSet;
import syntaxtree.Symbols;
import syntaxtree.expression.Variable;

/**
 * Data representation for read statements (variables only)
 *
 */
public class Read extends Statement {

    private Variable id;

    public Read(Variable id) {
        this.id = id;
    }

    public Variable getId() {
        return id;
    }

    public void setId(Variable id) {
        this.id = id;
    }

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\nIdentifier: " + id.toString() + "\n";
    }

    @Override
    public String toString() {
        return Symbols.READ + Symbols.SEPERATOR + id;
    }

    @Override
    public RDProgramState RD(RDProgramState currentState) {
    	//RDentry
    	ArrayList<Definition> exit = currentState.getRDExit(getLabel()-1);
    	currentState.addRDentry(getLabel(), exit);

    	//RDexit
    	ArrayList<Definition> entry = currentState.getRDEntry(getLabel());
    	//killRD(read x) = {(x, l'}| b(l') is a declaration or an assignment to x}    	
    	entry.removeAll(currentState.kill(id, entry));
    	//genRD(read x) = {(A[a], l)}
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
    	return VariableSet.factory().addVariable(id);
    }
    
}
