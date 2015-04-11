package compiler.IR;

/**
 * Conditional operator short-circuit “and” &&. If the first operand of && evaluates to 
 * false, its second operand is not evaluated. 
 */

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJAnd extends MJBinaryOp {
	
	public MJAnd() {
	}

	public void prettyPrint(PrettyPrinter prepri) {
		this.getLhs().prettyPrint(prepri);
		prepri.print(" && ");
		this.getRhs().prettyPrint(prepri);
	}

	// Logical negation !, the type must be boolean.
	public MJType typeCheck() throws TypeCheckerException {
		// The operands must be booleans.
		if (this.getLhs().getType() != MJType.Tboolean )
			throw new TypeCheckerException(this.getLhs().toString() + " Not of type boolean");

		//TODO
		if (this.getRhs().getType() != MJType.Tboolean )
				throw new TypeCheckerException(this.getRhs().toString() + " Not of type boolean");

		return MJType.Tvoid;
	}	
	
}
