package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJAnd extends MJBinaryOp {

	public MJAnd(MJExpression a, MJExpression b) {
		super(a, b);
	}

	public void prettyPrint(PrettyPrinter prepri) {
		this.lhs.prettyPrint(prepri);
		prepri.print(" && ");
		this.rhs.prettyPrint(prepri);
	}
	
	public String toString() {
		return this.lhs + " && " + this.rhs;
		
	}

	/**
	 * The && operator type checks if both arguments type check and have type
	 * boolean. The expression has also the type boolean.
	 */
	MJType typeCheck() throws TypeCheckerException {
		if (!this.lhs.typeCheck().isBoolean())
			throw new TypeCheckerException(this.getClass().getSimpleName()+": lhs ("+this.lhs.getType()+") " + this.lhs.toString()
					+ " is not of the type: Boolean");

		if (!this.rhs.typeCheck().isBoolean())
			throw new TypeCheckerException(this.getClass().getSimpleName()+": rhs ("+this.rhs.getType()+") " + this.rhs.toString()
					+ " is not of the type: Boolean");

		this.type = MJType.Tboolean; 
		return this.type;
	}

	void variableInit(HashSet<MJVariable> initialized)
			throws TypeCheckerException {
	}

}
