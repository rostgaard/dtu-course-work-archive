package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJLess extends MJBinaryOp {

	public MJLess(MJExpression a, MJExpression b) {
		super(a, b);
	}

	public void prettyPrint(PrettyPrinter prepri) {
		this.lhs.prettyPrint(prepri);
		prepri.print(" < ");
		this.rhs.prettyPrint(prepri);
	}

	public String toString() {
		return this.lhs.toString() + " < " + this.rhs.toString();
	}

	/*
	 * The < operator type checks if both arguments type check and have type
	 * integer. The expression has the type boolean. 
	 * @return MJType.Tboolean on success
	 */
	MJType typeCheck() throws TypeCheckerException {
		if (!(this.lhs.typeCheck().isInt() && this.rhs.typeCheck().isInt())) {
			throw new TypeCheckerException("Expression " + this
					+ " requires operands of type int");
		}

		return MJType.Tboolean;
	}

	void variableInit(HashSet<MJVariable> initialized)
			throws TypeCheckerException {
	}

}
