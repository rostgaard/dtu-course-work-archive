package syntaxtree.condition;

import analysis.lattices.IntervalLattice;
import analysis.SignSet;
import analysis.lattices.SignsLattice;
import syntaxtree.RelationOperation;
import syntaxtree.Symbols;
import syntaxtree.expression.Expression;
import syntaxtree.expression.Variable;

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
    public void evaluate(SignsLattice lattice, Boolean trueBranch) {
        SignSet lhsSigns = expr1.evalulate(lattice);
        SignSet rhsSigns = expr2.evalulate(lattice);

        ExpressionOperationCondition thisCopy = new ExpressionOperationCondition(expr1,expr2,ro);

        if (!trueBranch) {
            thisCopy.setRo(thisCopy.ro.switchOperator());
        }

        switch (thisCopy.ro) {
            case EQUAL: {
                SignSet intersection = lhsSigns.intersect(rhsSigns);

                if (expr1 instanceof Variable) {
                    Variable variable = (Variable) expr1;
                    lattice.put(variable, intersection);
                }

                if (expr2 instanceof Variable) {
                    Variable variable = (Variable) expr2;
                    lattice.put(variable, intersection);
                }

                break;
            }
            case NOTEQUAL:  {
                SignSet intersection = lhsSigns.intersect(rhsSigns);
                if (expr1 instanceof Variable) {
                    Variable variable = (Variable) expr1;
                    SignSet signs = lattice.get(variable);
                    signs.removeAll(intersection);
                    lattice.put(variable, signs);
                }

                if (expr2 instanceof Variable) {
                    Variable variable = (Variable) expr1;
                    SignSet signs = lattice.get(variable);
                    signs.removeAll(intersection);
                    lattice.put(variable, signs);
                }
            }
            case GREATEREQUALTHAN:


            case GREATERTHAN:
            case LESSEQUALTHAN:
            case LESSTHAN:
            default:
//                retval.merge(SignSet.pnz);
//                return retval;

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
