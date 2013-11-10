package syntaxtree.expression;

import syntaxtree.*;

/**
 * Data representation for expressions with arithmetic operation
 *
 */
public class OperationExpression extends Expression{

	private Expression expr1;
	private Expression expr2;
	private ArithmeticOperation ao;
	
	public OperationExpression(Expression expr1, Expression expr2, ArithmeticOperation ao){
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
            return expr1 + " " + Symbols.symbolOf(ao) +" "+ expr2;
        }
}
