package compiler.IR;

public abstract class MJUnaryOp extends MJExpression {
	MJExpression operand;

	public MJUnaryOp(MJExpression operand) {
		super();
		this.operand = operand;
	}


}
