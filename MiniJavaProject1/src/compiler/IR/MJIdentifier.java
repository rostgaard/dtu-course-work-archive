package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;
import compiler.Exceptions.VariableNotFound;

public class MJIdentifier extends MJExpression {

	private String name;

	public MJIdentifier() {
	}

	public MJIdentifier(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print(this.name);
	}

	public String toString() {
		return this.getName();
	}

	/*
	 * An identifier type checks if a declaration of it is visible in the
	 * current scope. It has the type as which it has been declared.
	 * 
	 * @return MJType
	 */
	MJType typeCheck() throws TypeCheckerException {
		// TODO check up on this
		// this identifier type checks and has the same type as the class it is
		// used in.
		if (this.name == "this") {
			return new MJType(IR.currentClass.getName());
		}
		MJVariable v = null;

		// Try to find the variable in a declaration
		try {
			v = IR.find(this.name);
		} catch (VariableNotFound e) {
			throw new TypeCheckerException(this.getClass().getSimpleName()
					+ ": unknown identifier: " + this);

		}
		this.type = v.getType();
		return this.type;
	}

	void variableInit(HashSet<MJVariable> initialized)
			throws TypeCheckerException {
	}

}
