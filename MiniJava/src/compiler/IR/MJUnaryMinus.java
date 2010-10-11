package compiler.IR;

import compiler.PrettyPrinter;

public class MJUnaryMinus extends MJUnaryOp {

	public MJUnaryMinus(MJExpression operand) {
		super(operand);
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("-");
		operand.prettyPrint();
	}

}
