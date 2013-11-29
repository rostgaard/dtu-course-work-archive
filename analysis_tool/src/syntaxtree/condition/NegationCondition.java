package syntaxtree.condition;

import analysis.Interval;
import analysis.IntervalLattice;
import analysis.SignSet;
import analysis.SignsLattice;

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
    
    @Override
    public boolean isOutOfBounds(IntervalLattice lattice) {
        return this.cond.isOutOfBounds(lattice);
    }
    
}
