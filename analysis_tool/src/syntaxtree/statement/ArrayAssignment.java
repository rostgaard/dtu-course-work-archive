package syntaxtree.statement;

import syntaxtree.expression.Variable;
import syntaxtree.expression.Expression;

/**
 * Data representation for assignments (arrays only)
 *
 */
public class ArrayAssignment extends Statement{
	
	private Variable id;
	private Expression idx;
	private Expression expr;
	
	public ArrayAssignment(Variable id, Expression idx, Expression expr){
		this.id = id;
		this.idx = idx;
		this.expr = expr;
	}
}
