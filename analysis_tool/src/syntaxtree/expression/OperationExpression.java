package syntaxtree.expression;

import syntaxtree.ArithmeticOperation;

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
	
}
