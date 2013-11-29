package syntaxtree.condition;

import analysis.Sign;
import analysis.SignSet;
import analysis.SignsLattice;
import analysis.UnderFlowException;

/**
 * Data representation for negation of boolean expressions
 *
 */
public class NegationCondition extends Condition {

    private Condition cond;

    public NegationCondition(Condition cond) {
        this.cond = cond;
    }

    public Condition getCond() {
        return cond;
    }

    public void setCond(Condition cond) {
        this.cond = cond;
    }

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\nCondition: " + cond.toString() + "\n";
    }

    @Override
    public String toString() {
        return "!" + cond;
    }

    @Override
    public SignSet evaluate(SignsLattice lattice) {
        SignSet retval = new SignSet();
        retval.merge(SignSet.empty);
        return retval;
    }

    @Override
    public boolean hasPotentialUnderFlow(SignsLattice lattice) {
        return this.cond.hasPotentialUnderFlow(lattice);
    }
}
