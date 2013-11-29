package syntaxtree.statement;

import analysis.RDProgramState;
import flowgraph.datastructure.Node;
import syntaxtree.Symbols;
import utilities.Sequencer;
import analysis.DefinitionSet;
import analysis.IntervalLattice;
import analysis.Lattice;
import analysis.SignsLattice;

/**
 * Abstract class for statements
 *
 */
public abstract class Statement implements analysis.Analysable {

    private int label = -1; // Default value for an unassigned statement.

    public String toStringWithLabel() {
        return Symbols.LSQPARAN + this.toString() + Symbols.RSQPARAN + this.label;
    }

    public void setLabel(Sequencer seq) {
        this.label = seq.nextInt();
    }

    public int getLabel() {
        return this.label;
    }

    public Node toNode() {
        return new Node(this);
    }

    public DefinitionSet killed(RDProgramState currentState) {
        DefinitionSet killed = currentState.getRDEntry(getLabel());
        return killed;
    }

    public DefinitionSet generated(RDProgramState currentState) {
        DefinitionSet generated = currentState.getRDEntry(getLabel());
        return generated;
    }

    public Lattice transferFunction(Lattice lattice, int toLabel) {
        return lattice;
    }

    public boolean isOutOfBounds(IntervalLattice lattice) {
        return false;
    }
}