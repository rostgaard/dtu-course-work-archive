package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJAssign extends MJStatement {

	private MJExpression rhs;
	private MJIdentifier lhs;

	public MJAssign(MJIdentifier lhs, MJExpression rhs) {
		this.rhs = rhs;
		this.lhs = lhs;
	}

	public MJIdentifier getLhs() {
		return this.lhs;
	}

	public MJExpression getRhs() {
		return this.rhs;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		if (compiler.config.DEBUG) {
			System.out.println("Checking assignment: ");
		}

		this.lhs.prettyPrint(prepri);
		prepri.print(" = ");
		this.rhs.prettyPrint(prepri);
		prepri.println(";");
	}

	public String toString() {
		return this.lhs.toString() + " = " + this.rhs.toString();
	}

	/*
	 * An assignment type checks if the variable is declared, the right hand
	 * side type checks, and both have the same type.
	 */
	MJType typeCheck() throws TypeCheckerException {
		if (!this.lhs.typeCheck().isSame(this.rhs.typeCheck()) ) {
			throw new TypeCheckerException(this.getClass().getSimpleName()
					+ ": lhs (" + this.lhs.typeCheck().getName()
					+ ") is not of same type as rhs ("+ this.rhs.typeCheck().getName() +") in expression\n\t"
					+ this);
		}
		return MJType.Tnone;
	}

	void variableInit(HashSet<MJVariable> initialized)
			throws TypeCheckerException {
	}

}
