package compiler.IR;

import compiler.Exceptions.TypeCheckerException;

public abstract class MJUnaryOp extends MJExpression {

	protected MJExpression arg;

	public MJUnaryOp(MJExpression l) {
		this.arg = l;
	}

}
