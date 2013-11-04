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

	public Variable getId() {
		return id;
	}

	public void setId(Variable id) {
		this.id = id;
	}

	public Expression getIdx() {
		return idx;
	}

	public void setIdx(Expression idx) {
		this.idx = idx;
	}

	public Expression getExpr() {
		return expr;
	}

	public void setExpr(Expression expr) {
		this.expr = expr;
	}
	
	@Override
	public String toString() {
		return "\nClass: " + getClass().getSimpleName() + "\nIdentifier: " + id.toString() + "\nIndex: " + idx.toString() + "\nExpression: " + expr.toString() + "\n";
	}
}
