package syntaxtree.statement;

import flowgraph.datastructure.Node;
import syntaxtree.Symbols;
import utilities.Sequencer;
import analysis.lattices.IntervalLattice;
import analysis.lattices.Lattice;

/**
 * Abstract class for statements
 *
 */
public abstract class Statement implements analysis.Analysable {

    private int label = -1; // Default value for an unassigned statement.

    public String toStringWithLabel(int indention) {
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

    public Lattice transferFunction(Lattice lattice, int toLabel) {
        return lattice;
    }

    /**
     * Determines if a statement contains elements that are out of bounds, given
     * an Interval state.
     *
     * @param lattice The entry state.
     * @return True if any expression in the condition is out of bounds, false
     * otherwise.
     */
    public boolean isOutOfBounds(IntervalLattice lattice) {
        return false;
    }
}