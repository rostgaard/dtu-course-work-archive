package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJReturn extends MJStatement {

	private MJExpression retExp;

	public MJReturn(MJExpression retExp) {
		this.retExp = retExp;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("return");

		if (!(this.retExp instanceof MJNoExpression)) {
			prepri.print(" ");
			this.retExp.prettyPrint(prepri);
		}

		prepri.println(";");
	}

	public String toString() {
		String ret = "return";
		if (!(this.retExp instanceof MJNoExpression)) {
			ret += " " + this.retExp;
		}
		return ret + ";";
	}

	/*
	 * if the method has not return type void then the method's return
	 * statements must return an expression with the correct type, and
	 * 
	 * if the method has return type void then the return statements may not
	 * have an argument.
	 */
	MJType typeCheck() throws TypeCheckerException {
		if (this.retExp instanceof MJNoExpression)
			return MJType.Tnone;

		else
			this.retExp.type = this.retExp.typeCheck();
		if (!this.retExp.type.isSame(IR.currentMethod.getType()))
			throw new TypeCheckerException(this.getClass().getName()
					+ ": method " + IR.currentMethod.getName()
					+ " return statement does not match declaration in expression: " +this);
		return this.retExp.type;

	}

	void variableInit(HashSet<MJVariable> initialized)
			throws TypeCheckerException {
	}

}