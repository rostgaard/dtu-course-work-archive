package compiler.IR;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJNegate extends MJUnaryOp {

	public MJNegate(MJExpression operand) {
		super(operand);
	}

	// Logical negation !, the type must be boolean.
	public MJType typeCheck() throws TypeCheckerException {
		if (this.getType() != MJType.Tboolean)
			throw new TypeCheckerException(this.operand.toString() + " Not of type boolean");
		return MJType.Tvoid;
	}	
	
	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("!");
		operand.prettyPrint(prepri);
	}
}
