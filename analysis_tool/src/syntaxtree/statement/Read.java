package syntaxtree.statement;

import analysis.RDProgramState;
import flowgraph.datastructure.FlowSet;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;
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
        return currentState;
    }
    
    @Override
    public NodeSet labels() {
        return NodeSet.emptySet
                .addNode(new Node(this));
    }

    @Override
    public Node init() {
        return (new Node(this));
    }

    @Override
    public NodeSet finalNodes() {
        return NodeSet.emptySet
                .addNode(new Node(this));
    }

    @Override
    public FlowSet flow() {
        return FlowSet.emptySet;
    }
    
}
