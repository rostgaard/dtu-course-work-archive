package syntaxtree.expression;

import flowgraph.datastructure.VariableSet;

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


	public String debugInformation() {
		return "\nClass: " + getClass().getSimpleName() + "\nExpression: " + expr.toString() + "\n";
	}

	@Override
	public String toString() {
		return "!" + expr;
	}

	@Override
	public VariableSet getVariable() {
		return expr.getVariable();
	}
}
