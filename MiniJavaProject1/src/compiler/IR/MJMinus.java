package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJMinus extends MJBinaryOp {

	public void prettyPrint(PrettyPrinter prepri) {
		this.lhs.prettyPrint(prepri);
		prepri.print(" - ");
		this.rhs.prettyPrint(prepri);
	}

	public String toString() {
		return this.lhs.toString() + " - " + this.rhs.toString();
	}

	/*
	 * The - operator type checks if both arguments type check and have type
	 * integer. The expression has the type integer.
	 * 
	 * @return return MJType.Tint on success
	 */
	MJType typeCheck() throws TypeCheckerException {
		// If there is two integers we have a logical add
		if (!this.lhs.typeCheck().isInt())
			throw new TypeCheckerException(this.getClass().getSimpleName()
					+ ": lhs " + this.lhs.toString() + " (" + this.lhs.type +")" 
					+ " is not of the type integer in expression "+this	);
		if (!this.rhs.typeCheck().isInt())
			throw new TypeCheckerException(this.getClass().getSimpleName()
					+ ": rhs " + this.rhs.toString() + " (" + this.rhs.type +")" 
					+ " is not of the type integer in expression "+ this );			
		
		this.type = MJType.Tint;
		return this.type;
	}

	void variableInit(HashSet<MJVariable> initialized)
			throws TypeCheckerException {
	}

}
