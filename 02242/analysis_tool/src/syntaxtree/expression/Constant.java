package syntaxtree.expression;

import analysis.Interval;
import analysis.lattices.IntervalLattice;
import analysis.Sign;
import analysis.SignSet;
import analysis.lattices.SignsLattice;
import flowgraph.datastructure.VariableSet;

/**
 * Data representation for constants
 *
 */
public class Constant extends Expression {

    private final int n;

    public Constant(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\nValue: " + n + "\n";
    }

    @Override
    public String toString() {
        return "" + n;
    }

    @Override
    public VariableSet getVariable() {
        return VariableSet.emptySet;
    }

    @Override
    public Interval evalulate(IntervalLattice lattice) {
        return new Interval(lattice).setAbsoluteInterval(n, n);
    }

    @Override
    public SignSet evalulate(SignsLattice lattice) {

        SignSet signSet = new SignSet();
        if (this.n == 0) {
            signSet.add(Sign.Z);
        } else if (this.n <= 0) {
            signSet.add(Sign.N);
        } else if (this.n >= 0) {
            signSet.add(Sign.P);
        }
        return signSet;
    }

    /**
     * 
     * @param lattice The input state.
     * @return True if an array access is potentially out of bounds, false
     * otherwise.
     */
    @Override
    public boolean isOutOfBounds(IntervalLattice lattice) {
        return false;
    }
}
