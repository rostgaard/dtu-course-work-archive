package compiler.IR;

import compiler.PrettyPrinter;

public class MJUnaryMinus extends MJUnaryOp {
	MJExpression operand;
	

	public MJUnaryMinus(MJExpression operand) {
		super();
		this.operand = operand;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("-");
		operand.prettyPrint();
	}

}
