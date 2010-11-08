package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJNegate extends MJUnaryOp {

	public MJNegate(MJExpression l) {
		super(l);
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("!");
		this.arg.prettyPrint(prepri);
	}

	/*
	 * The logical negation type checks if the argument type checks and has type
	 * boolean. The expression has type boolean.
	 * 
	 * @return MJType.Tboolean on success
	 */
	MJType typeCheck() throws TypeCheckerException {

		if (this.arg.typeCheck().isInt())
			return MJType.Tboolean;
		else
			throw new TypeCheckerException("Expression " + this.arg.toString()
					+ " is not of the type: Integer");
	}

	void variableInit(HashSet<MJVariable> initialized)
			throws TypeCheckerException {
	}

}
