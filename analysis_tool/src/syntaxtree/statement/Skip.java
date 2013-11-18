package syntaxtree.statement;

import analysis.RDProgramState;
import flowgraph.datastructure.FlowSet;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;
import syntaxtree.Symbols;

/**
 * Data representation for skip statements
 *
 */
public class Skip extends Statement {

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\n";
    }

    @Override
    public String toString() {
        return Symbols.SKIP;
    }

    @Override
    public RDProgramState RD(RDProgramState currentState) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
}
