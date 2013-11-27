package syntaxtree.expression;

import analysis.Sign;
import analysis.SignSet;
import analysis.SignsLattice;
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
}
