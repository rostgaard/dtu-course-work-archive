package syntaxtree.expression;

/**
 * Data representation for negation of expressions
 *
 */
public class NegationExpression extends Expression{

	private Expression expr;
	
	public NegationExpression(Expression expr){
		this.expr = expr;
	}

	public Expression getExpr() {
		return expr;
	}

	public void setExpr(Expression expr) {
		this.expr = expr;
	}
	
	@Override
	public String toString() {
		return "\nClass: " + getClass().getSimpleName() + "\nExpression: " + expr.toString() + "\n";
	}
}
