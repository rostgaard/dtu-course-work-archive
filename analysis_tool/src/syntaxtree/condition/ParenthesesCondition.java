package syntaxtree.condition;

import analysis.Interval;
import analysis.IntervalLattice;
import analysis.SignSet;
import analysis.SignsLattice;
import syntaxtree.Symbols;

/**
 * Data representation for boolean expressions surrounded with parentheses
 *
 */
public class ParenthesesCondition extends Condition {

    private Condition cond;

    public ParenthesesCondition(Condition cond) {
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
        return Symbols.LPARAN + cond + Symbols.RPARAN;
    }

    @Override
    public SignSet evaluate(SignsLattice lattice) {
        SignSet retval = new SignSet();
        retval.merge(this.evaluate(lattice));
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
