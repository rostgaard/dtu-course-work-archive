package syntaxtree.condition;

import analysis.lattices.IntervalLattice;
import analysis.SignSet;
import analysis.lattices.SignsLattice;

/**
 * Data representation for the boolean value "false"
 *
 */
public class FalseCondition extends Condition {

    private boolean value = false;

    public boolean getFalse() {
        return value;
    }

    public boolean isFalse() {
        return value;
    }

    public void setFalse(boolean falsee) {
        this.value = falsee;
    }

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\nValue: " + value + "\n";
    }

    @Override
    public String toString() {
        return "false";
    }

    /**
     * Tries to derive information about the expressions contained. A
     * {@link FalseCondition} does not give us any information, so we just
     * over-approximate and return TOP.
     *
     * @param lattice
     * @return
     */
    @Override
    public SignSet evaluate(SignsLattice lattice) {
        SignSet retval = new SignSet();
        retval.merge(SignSet.pnz);
        return retval;
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
