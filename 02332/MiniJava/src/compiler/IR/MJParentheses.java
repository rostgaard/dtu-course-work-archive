package compiler.IR;

import compiler.PrettyPrinter;


public class MJParentheses extends MJExpression {

	private MJExpression expr;
	
	
	public MJParentheses(MJExpression expr) {
		super();
		this.expr = expr;
	}


	public void prettyPrint(PrettyPrinter prepri) {
		expr.prettyPrint(prepri);
	}

}
