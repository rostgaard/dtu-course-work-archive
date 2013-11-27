package syntaxtree.expression;

import analysis.Sign;
import analysis.SignSet;
import analysis.SignsLattice;
import flowgraph.datastructure.VariableSet;
import java.util.HashMap;
import syntaxtree.*;

/**
 * Data representation for expressions with arithmetic operation
 *
 */
public class OperationExpression extends Expression {

    private Expression expr1;
    private Expression expr2;
    private ArithmeticOperation ao;

    class OperatorVector extends HashMap<Sign, SignSet> {

        public OperatorVector(Sign[] negative, Sign[] zero, Sign[] positive) {
        }
    }

    class OperatorMatrix extends HashMap<Sign, OperatorVector> {

        public OperatorMatrix() {
        }
    }

    private SignSet set(Sign[] signs) {
        SignSet set = new SignSet();
        for (Sign s : signs) {
            set.add(s);
        }
        return set;
    }
    private SignSet p = new SignSet().put(Sign.POSTIVE);
    private SignSet z = new SignSet().put(Sign.ZERO);
    private SignSet n = new SignSet().put(Sign.NEGATIVE);
    private SignSet pz = new SignSet().put(Sign.POSTIVE).put(Sign.ZERO);
    private SignSet p_n = new SignSet().put(Sign.POSTIVE).put(Sign.NEGATIVE);
    private SignSet pnz = new SignSet().put(Sign.POSTIVE).put(Sign.ZERO).put(Sign.NEGATIVE);
    private SignSet nz = new SignSet().put(Sign.ZERO).put(Sign.NEGATIVE);
    private SignSet empty = new SignSet();

    private int indexOf(Sign s) {
        switch (s) {
            case NEGATIVE:
                return 0;
            case ZERO:
                return 1;
            case POSTIVE:
                return 2;
        }
        return -1;
    }
    private final SignSet[][] plusMatrix = {
        {n, n, pnz},
        {n, z, p},
        {pnz, p, p}
    };
    private final SignSet[][] subtractionMatrix = {
        {pnz, n, n},
        {n, z, n},
        {p, p, pnz}
    };
    private final SignSet[][] multiplicationMatrix = {
        {p, z, n},
        {z, z, z},
        {n, z, p}
    };
    private final SignSet[][] divisionMatrix = {
        {p, empty, n},
        {z, empty, z},
        {n, empty, p}
    };

    /**
     * Returns the list of possible signs from an operation.
     *
     * @param op The arithmetic operation to perform
     * @param lhs The left hand side of the operation
     * @param rhs The right hand side of the operation
     * @return
     */
    public SignSet evaluationTable(Sign lhs, Sign rhs) {
        switch (this.ao) {
            case PLUS:
                return plusMatrix[indexOf(lhs)][indexOf(rhs)];
            case MINUS:
                return subtractionMatrix[indexOf(lhs)][indexOf(rhs)];
            case MULTIPLICATION:
                return multiplicationMatrix[indexOf(lhs)][indexOf(rhs)];
            case DIVISION:
                return divisionMatrix[indexOf(lhs)][indexOf(rhs)];
        }
        return empty;
    }

    @Override
    public SignSet evalulate(SignsLattice lattice) {
        SignSet lhsSigns = this.expr1.evalulate(lattice);
        SignSet rhsSigns = this.expr2.evalulate(lattice);
        SignSet result = new SignSet();

        for (Sign lhs : lhsSigns) {
            for(Sign rhs : rhsSigns) {
                result.merge(evaluationTable(lhs, rhs));
            }
        }
        System.out.println("OPexp eval" + result);
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
