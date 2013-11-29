package syntaxtree.condition;

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
    public void evaluate(SignsLattice lattice, Boolean trueBranch) {
        cond.evaluate(lattice,!trueBranch);
    }

    @Override
    public boolean hasPotentialUnderFlow(SignsLattice lattice) {
        return this.cond.hasPotentialUnderFlow(lattice);
    }
}
