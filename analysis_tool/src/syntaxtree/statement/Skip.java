package syntaxtree.statement;

import analysis.DefinitionSet;
import analysis.Interval;
import analysis.IntervalLattice;
import analysis.RDProgramState;
import analysis.SignsLattice;
import flowgraph.datastructure.FlowSet;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;
import flowgraph.datastructure.VariableSet;
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
        //RDentry
        DefinitionSet exit = currentState.getRDExit(getLabel() - 1);
        currentState.addRDentry(getLabel(), exit);

        //RDexit
        DefinitionSet entry = currentState.getRDEntry(getLabel());
        //killRD([skip]l) = �
        //genRD([[skip]l) = �
        currentState.addRDexit(getLabel(), entry);
        return currentState;
    }

    @Override
    public NodeSet labels() {
        return NodeSet.factory().addNode(new Node(this));
    }

    @Override
    public Node initial() {
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
        return VariableSet.emptySet;
    }

    @Override
    public boolean hasPotentialUnderFlow(SignsLattice lattice) {
        return false;
    }

    @Override
    public boolean isOutOfBounds(IntervalLattice lattice) {
        return false;
    }
}
