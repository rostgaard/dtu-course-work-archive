package syntaxtree.condition;

import analysis.SignSet;
import analysis.SignsLattice;
import analysis.UnderFlowException;

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
    public SignSet evaluate(SignsLattice lattice) {
        SignSet retval = new SignSet();
        retval.merge(SignSet.empty);
        return retval;
    }

    @Override
    public boolean hasPotentialUnderFlow(SignsLattice lattice) {
        return false;
    }

}
