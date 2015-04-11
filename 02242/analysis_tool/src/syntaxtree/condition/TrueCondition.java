package syntaxtree.condition;

import analysis.lattices.IntervalLattice;
import analysis.SignSet;
import analysis.lattices.SignsLattice;

/**
 * Data representation for the boolean value "true"
 *
 */
public class TrueCondition extends Condition {

    private static final boolean value = true;

    public boolean getTrue() {
        return value;
    }

    public boolean isTrue() {
        return value;
    }

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\nValue: " + value + "\n";
    }

    @Override
    public String toString() {
        return "true";
    }

    /**
     * Tries to evaluate a condition. 
     * @param lattice
     * @return 
     */
    @Override
    public void evaluate(SignsLattice lattice, Boolean trueBranch) {
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
