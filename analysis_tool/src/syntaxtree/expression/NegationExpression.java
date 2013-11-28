package syntaxtree.expression;

import analysis.Interval;
import analysis.IntervalLattice;
import analysis.Sign;
import analysis.SignSet;
import analysis.SignsLattice;
import flowgraph.datastructure.VariableSet;

/**
 * Data representation for negation of expressions
 *
 */
public class NegationExpression extends Expression {

    private Expression expr;

    public NegationExpression(Expression expr) {
        this.expr = expr;
    }

    public Expression getExpr() {
        return expr;
    }

    public void setExpr(Expression expr) {
        this.expr = expr;
    }

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\nExpression: " + expr.toString() + "\n";
    }

    @Override
    public String toString() {
        return "-" + expr;
    }

    @Override
    public VariableSet getVariable() {
        return expr.getVariable();
    }

    @Override
    public Interval evalulate(IntervalLattice lattice) {
        Interval interval = Interval.negate (this.expr.evalulate(lattice));
        
        return interval;
    }
    
    @Override
    public SignSet evalulate(SignsLattice lattice) {
        SignSet set = new SignSet();
        SignSet eval = this.expr.evalulate(lattice);

        if (eval.contains((Sign) Sign.Z)) {
            set.add((Sign) Sign.Z);
        }

        if (eval.contains((Sign) Sign.N)) {
            set.add((Sign) Sign.P);
        }

        if (eval.contains((Sign) Sign.P)) {
            set.add((Sign) Sign.N);
        }
        return set;
    }
}
