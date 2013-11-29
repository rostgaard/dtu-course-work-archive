package syntaxtree.expression;

import analysis.Interval;
import analysis.IntervalLattice;
import analysis.Sign;
import analysis.SignSet;
import analysis.SignsLattice;
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
    @Override
    public SignSet evalulate(SignsLattice lattice) {        
        return lattice.lookup(id);
    }

    @Override
    public Interval evalulate(IntervalLattice lattice) {        
        return lattice.lookup(id);
    }
    
    @Override
    public boolean hasPotentialUnderFlow(SignsLattice lattice) {
        return this.idx.evalulate(lattice).contains(Sign.N);
    }
}
