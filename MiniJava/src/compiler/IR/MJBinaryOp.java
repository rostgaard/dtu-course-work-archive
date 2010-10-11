package compiler.IR;

import compiler.PrettyPrinter;

public abstract class MJBinaryOp extends MJExpression {
	private MJExpression lhs,rhs;

	public MJExpression getLhs() {
		return lhs;
	}

	public void setLhs(MJExpression lhs) {
		this.lhs = lhs;
	}

	public MJExpression getRhs() {
		return rhs;
	}

	public void setRhs(MJExpression rhs) {
		this.rhs = rhs;
	}
	
}
