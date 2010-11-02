package compiler.IR;

import compiler.Exceptions.TypeCheckerException;

public abstract class MJBinaryOp extends MJExpression {

	
	protected MJExpression lhs;
	protected MJExpression rhs;

	public void setLeftOperand(MJExpression op) {
		this.lhs = op;
	}

	public void setRightOperand(MJExpression op) {
		this.rhs = op;		
	}

	public MJBinaryOp() {};
	
	public MJBinaryOp(MJExpression a, MJExpression b) {
		this.lhs = a;
		this.rhs = b;
	}

}
