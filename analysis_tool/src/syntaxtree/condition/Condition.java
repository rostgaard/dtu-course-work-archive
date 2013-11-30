package syntaxtree.condition;

import analysis.lattices.IntervalLattice;
import analysis.SignSet;
import analysis.lattices.SignsLattice;

/**
 * Abstract class for conditions
 *
 */
public abstract class Condition {
    
    public abstract void evaluate (SignsLattice lattice, Boolean trueBranch);
    public abstract boolean hasPotentialUnderFlow(SignsLattice lattice);

    /**
     * Determines if a condition contains elements that are out of bounds, given
     * an Interval state.
     *
     * @param lattice The entry state.
     * @return True if any expression in the condition is out of bounds, false
     * otherwise.
     */
    public abstract boolean isOutOfBounds(IntervalLattice lattice);
}
