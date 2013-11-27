package syntaxtree.expression;

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
    public SignSet evalulate(SignsLattice lattice) {
        SignSet set = this.expr.evalulate(lattice);

        if (set.contains((Sign) Sign.NEGATIVE)) {
            set.remove((Sign) Sign.NEGATIVE);
            set.add((Sign) Sign.POSTIVE);
        }

        if (set.contains((Sign) Sign.POSTIVE)) {
            set.remove((Sign) Sign.POSTIVE);
            set.add((Sign) Sign.NEGATIVE);
        }

        return set;
    }
}
