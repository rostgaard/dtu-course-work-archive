package syntaxtree.expression;

import analysis.Interval;
import analysis.lattices.IntervalLattice;
import analysis.Sign;
import analysis.SignSet;
import analysis.lattices.SignsLattice;
import flowgraph.datastructure.VariableSet;

/**
 * Data representation for array expressions
 *
 */
public class ArrayExpression extends Expression {

    private Variable id;
    private Expression idx;

    public ArrayExpression(Variable id, Expression idx) {
        this.id = id;
        this.idx = idx;
    }

    public Variable getId() {
        return id;
    }

    public void setId(Variable id) {
        this.id = id;
    }

    public Expression getIdx() {
        return idx;
    }

    public void setIdx(Expression idx) {
        this.idx = idx;
    }

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\nIdentifier: " + id.toString() + "\nIndex: " + idx.toString() + "\n";
    }

    @Override
    public String toString() {
        return id + "[" + idx + "]";
    }

    @Override
    public VariableSet getVariable() {
        return VariableSet.factory().addVariable(id)
                .union(idx.getVariable());
    }

    /**
     * Evaluates the ArrayExpression in the context of figuring out the signs of
     * its value(s). An ArrayExpression only returns the set of signs associated
     * with it up until the current point. It does not take into account the
     * index expression, as this is irrelevant with regards to the values of the
     * {@link Variable} in this expression.
     *
     * @param lattice The input state.
     * @return The set of Signs currently associated with the identifier in the
     * ArrayExpression.
     */
    @Override
    public SignSet evalulate(SignsLattice lattice) {
        return lattice.lookup(id);
    }

    /**
     * Evaluates the ArrayExpression in the context of figuring out the interval
     * of its value(s). An ArrayExpression only returns the interval associated
     * with it up until the current point. It does not take into account the
     * index expression, as this is irrelevant with regards to the values of the
     * {@link Variable} in this expression.
     *
     * @param lattice The input state.
     * @return The interval currently associated with the identifier in the
     * ArrayExpression.
     */
    @Override
    public Interval evalulate(IntervalLattice lattice) {
        return lattice.lookup(id);
    }

    /**
     * 
     * @param lattice The input state.
     * @return True if a potential overflow is detected, false otherwise.
     */
    @Override
    public boolean hasPotentialUnderFlow(SignsLattice lattice) {
        return this.idx.evalulate(lattice).contains(Sign.N);
    }

    /**
     * 
     * @param lattice The input state.
     * @return True if an array access is potentially out of bounds, false
     * otherwise.
     */
    @Override
    public boolean isOutOfBounds(IntervalLattice lattice) {
        Interval bounds = lattice.declarations.lookup(id).bounds(lattice);
        return !(this.idx.evalulate(lattice).subsetOf(bounds));
    }
}
