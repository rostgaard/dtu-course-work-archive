package syntaxtree.statement;

import syntaxtree.expression.Expression;

/**
 * Data representation for read statements
 *
 */
public class Write extends Statement{

	private Expression expr;
	
	public Write(Expression expr){
		this.expr = expr;
	}
	
}
