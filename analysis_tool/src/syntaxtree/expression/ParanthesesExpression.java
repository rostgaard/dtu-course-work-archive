package syntaxtree.expression;

/**
 * Data representation for expressions surrounded with parentheses
 *
 */
public class ParanthesesExpression extends Expression{

	private Expression expr;
	
	public ParanthesesExpression(Expression expr){
		this.expr = expr;
	}

	public Expression getExpr() {
		return expr;
	}

	public void setExpr(Expression expr) {
		this.expr = expr;
	}
	
}
