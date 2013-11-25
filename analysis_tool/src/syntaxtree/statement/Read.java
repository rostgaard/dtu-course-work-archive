package syntaxtree.statement;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

import analysis.Definition;
import analysis.DefinitionSet;
import analysis.Lattice;
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
        DefinitionSet exit = currentState.getRDExit(getLabel()-1);
    	currentState.addRDentry(getLabel(), exit);

    	//RDexit
        DefinitionSet entry = currentState.getRDEntry(getLabel());
    	//killRD(read x) = {(x, l'}| b(l') is a declaration or an assignment to x}    	
    	entry.removeAll(currentState.kill(id, entry));
    	//genRD(read x) = {(A[a], l)}
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
    	return VariableSet.factory().addVariable(id);
    }

    @Override
    public Lattice transferFunction(Lattice lattice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
