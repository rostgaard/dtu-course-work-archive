package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJPlus extends MJBinaryOp {

	public void prettyPrint(PrettyPrinter prepri) {
		this.lhs.prettyPrint(prepri);
		prepri.print(" + ");
		this.rhs.prettyPrint(prepri);
	}
	
	public String toString() {
		return this.lhs +" + "+this.rhs; 
	}

	/*
	 * The + operator type checks if both arguments type check and both have the
	 * same type, either integer or String. The expression has the same type as
	 * the arguments.
	 * 
	 * @return MJType.Tint or MJType.Tstring
	 */
	MJType typeCheck() throws TypeCheckerException {

		// If there is two integers we have a logical add
		if (this.lhs.typeCheck().isInt() && this.rhs.typeCheck().isInt()) {
			return MJType.Tint;
		} else if (this.lhs.typeCheck().getName().equals("String")
				&& this.rhs.typeCheck().getName().equals("String")) {
			return new MJType("String");
		}

		else {
			throw new TypeCheckerException("Variable " + this.lhs.toString()
					+ " is not of the type: Integer or String");
		}

	}

	void variableInit(HashSet<MJVariable> initialized)
			throws TypeCheckerException {
	}

}
