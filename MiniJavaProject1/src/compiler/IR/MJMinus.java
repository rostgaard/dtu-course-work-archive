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
		return this.lhs.toString() +" - " +this.rhs.toString();
	}
	
	/*
	 * The − operator type checks if both arguments type check and have type
	 * integer. The expression has the type integer.
	 * 
	 * @return return MJType.Tint on success
	 */
	MJType typeCheck() throws TypeCheckerException {

		if (!this.lhs.typeCheck().isInt() || !this.rhs.typeCheck().isInt())
			throw new TypeCheckerException(this.getClass().getSimpleName() 
					+": operands not of type int in expression " + this);

		return MJType.Tint;
	}

	void variableInit(HashSet<MJVariable> initialized)
			throws TypeCheckerException {
	}

}
