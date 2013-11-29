package syntaxtree.condition;

import analysis.Interval;
import analysis.IntervalLattice;
import analysis.SignSet;
import analysis.SignsLattice;
import syntaxtree.RelationOperation;
import syntaxtree.Symbols;
import syntaxtree.expression.Expression;

/**
 * Data representation for expressions with a relational operator
 *
 */
public class ExpressionOperationCondition extends Condition {

    private Expression expr1;
    private Expression expr2;
    private RelationOperation ro;

    public ExpressionOperationCondition(Expression expr1, Expression expr2, RelationOperation ro) {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.ro = ro;
    }

    public Expression getExpr1() {
        return expr1;
    }

    public void setExpr1(Expression expr1) {
        this.expr1 = expr1;
    }

    public Expression getExpr2() {
        return expr2;
    }

    public void setExpr2(Expression expr2) {
        this.expr2 = expr2;
    }

    public RelationOperation getRo() {
        return ro;
    }

    public void setRo(RelationOperation ro) {
        this.ro = ro;
    }

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\nExpression1: " + expr1.toString() + "\nExpression2: " + expr2.toString() + "\nRelation Operation: " + ro.toString() + "\n";
    }

    @Override
    public String toString() {
        return expr1 + Symbols.SEPERATOR
                + Symbols.symbolOf(ro) + Symbols.SEPERATOR + expr2;
    }

    @Override
    public SignSet evaluate(SignsLattice lattice) {
        SignSet lhsSigns = expr1.evalulate(lattice);
        SignSet rhsSigns = expr2.evalulate(lattice);
        SignSet retval = new SignSet();

        switch (this.ro) {
            case EQUAL:
                // intersection
                if (lhsSigns.equals(rhsSigns)) {
                }
            case GREATEREQUALTHAN:
            case GREATERTHAN:
            case LESSEQUALTHAN:
            case LESSTHAN:
            case NOTEQUAL:
            default:
                retval.merge(SignSet.pnz);
                return retval;

        }
    }

    @Override
    public boolean hasPotentialUnderFlow(SignsLattice lattice) {
        return this.expr1.hasPotentialUnderFlow(lattice) || this.expr2.hasPotentialUnderFlow(lattice);
    }
    
    @Override
    public boolean isOutOfBounds(IntervalLattice lattice) {
        return this.expr1.isOutOfBounds(lattice) || this.expr2.isOutOfBounds(lattice);
    }
    
}
