package syntaxtree.statement;

import analysis.DefinitionSet;
import analysis.RDProgramState;
import analysis.SignsLattice;
import analysis.UnderFlowException;
import flowgraph.datastructure.FlowSet;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;
import flowgraph.datastructure.VariableSet;
import syntaxtree.Symbols;
import syntaxtree.expression.Expression;

/**
 * Data representation for write statements
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
        DefinitionSet exit = currentState.getRDExit(getLabel()-1);
    	currentState.addRDentry(getLabel(), exit);

    	//RDexit
        DefinitionSet entry = currentState.getRDEntry(getLabel());
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

    @Override
    public boolean hasPotentialUnderFlow(SignsLattice lattice) {
        try {
            this.expr.checkUnderflow(lattice);
        } catch (UnderFlowException ex) {
            return true;
        }
        return false;
    }
     
}
