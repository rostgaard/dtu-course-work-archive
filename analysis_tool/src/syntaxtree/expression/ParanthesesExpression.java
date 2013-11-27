package syntaxtree.expression;

import analysis.SignSet;
import analysis.SignsLattice;
import flowgraph.datastructure.VariableSet;
import syntaxtree.Symbols;

/**
 * Data representation for expressions surrounded with parentheses
 *
 */
public class ParanthesesExpression extends Expression {

    private Expression expr;

    public ParanthesesExpression(Expression expr) {
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
        return Symbols.LPARAN + expr + Symbols.RPARAN;
    }

    @Override
    public VariableSet getVariable() {
        return expr.getVariable();
    }

    @Override
    public SignSet evalulate(SignsLattice lattice) {
        return expr.evalulate(lattice);
    }
}
