package compiler.IR;

/**
 * Conditional operator short-circuit “and” &&. If the first operand of && evaluates to 
 * false, its second operand is not evaluated. 
 */

import java.util.LinkedList;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJAnd extends MJBinaryOp {
	
	public MJAnd(MJExpression lhs, LinkedList<MJExpression> rhslist) {
		this.setLhs(lhs);
		this.setRhsList(rhslist);
	}

	public void prettyPrint(PrettyPrinter prepri) {
		//TODO
		this.getLhs().prettyPrint(prepri);
		
		prepri.print(" && ");
	}

	// Logical negation !, the type must be boolean.
	public MJType typeCheck() throws TypeCheckerException {
		// The operands must be booleans.
		if (this.getLhs().getType() != MJType.Tboolean )
			throw new TypeCheckerException(this.getLhs().toString() + " Not of type boolean");

		//TODO
//		if (this.getRhs().getType() != MJType.Tboolean )
//				throw new TypeCheckerException(this.getRhs().toString() + " Not of type boolean");
			
		
		

		return MJType.Tvoid;
	}	
	
}
