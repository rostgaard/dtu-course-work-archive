package syntaxtree.expression;

import analysis.Sign;
import analysis.SignSet;
import analysis.SignsLattice;
import flowgraph.datastructure.VariableSet;
import syntaxtree.*;

/**
 * Data representation for expressions with arithmetic operation
 *
 */
public class OperationExpression extends Expression {

    private Expression expr1;
    private Expression expr2;
    private ArithmeticOperation ao;

    /**
     * Returns the list of possible signs from an operation. This is based on
     * the operation type given in the current object. It performs lookups in 
     * tables defined in {@link SignSet}.
     *
     * @param lhs The left hand side of the operation
     * @param rhs The right hand side of the operation
     * @return
     */
    public SignSet evaluationTable(Sign lhs, Sign rhs) {
        switch (this.ao) {
            case PLUS:
                return SignSet.plusMatrix[SignSet.indexOf(lhs)][SignSet.indexOf(rhs)];
            case MINUS:
                return SignSet.subtractionMatrix[SignSet.indexOf(lhs)][SignSet.indexOf(rhs)];
            case MULTIPLICATION:
                return SignSet.multiplicationMatrix[SignSet.indexOf(lhs)][SignSet.indexOf(rhs)];
            case DIVISION:
                return SignSet.divisionMatrix[SignSet.indexOf(lhs)][SignSet.indexOf(rhs)];
        }
        
        // If we don't know the operator, is better to cowardly quit.
        return SignSet.empty;
    }

    @Override
    public SignSet evalulate(SignsLattice lattice) {
        SignSet lhsSigns = this.expr1.evalulate(lattice);
        SignSet rhsSigns = this.expr2.evalulate(lattice);
        SignSet result = new SignSet();

        for (Sign lhs : lhsSigns) {
            for (Sign rhs : rhsSigns) {
                result.merge(evaluationTable(lhs, rhs));
            }
        }
        return result;
    }

    public OperationExpression(Expression expr1, Expression expr2, ArithmeticOperation ao) {

        this.expr1 = expr1;
        this.expr2 = expr2;
        this.ao = ao;
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

    public ArithmeticOperation getAo() {
        return ao;
    }

    public void setAo(ArithmeticOperation ao) {
        this.ao = ao;
    }

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\nExpression1: " + expr1.toString() + "\nExression2: " + expr2.toString() + "\nArithmetic operation: " + ao.toString() + "\n";
    }

    @Override
    public String toString() {
        return expr1 + " " + Symbols.symbolOf(ao) + " " + expr2;
    }

    @Override
    public VariableSet getVariable() {
        return expr1.getVariable()
                .union(expr2.getVariable());
    }
}
