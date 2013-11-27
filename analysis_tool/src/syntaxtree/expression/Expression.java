package syntaxtree.expression;

import analysis.Interval;
import analysis.IntervalLattice;
import analysis.SignSet;
import analysis.SignsLattice;
import flowgraph.datastructure.VariableSet;

/**
 * Abstract class for expressions
 *
 */
public abstract class Expression {

    public abstract VariableSet getVariable();

    public abstract SignSet evalulate(SignsLattice lattice);
    public Interval evalulate(IntervalLattice lattice) {
        return new Interval(0, 0);
    }
}
