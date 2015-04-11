package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJParentheses extends MJExpression {

	private MJExpression exp;

	public MJParentheses(MJExpression e) {
		this.exp = e;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("( ");
		this.exp.prettyPrint(prepri);
		prepri.print(" )");
	}
	
	public String toString() {
		return "( " + this.exp.toString() + " )";
	}

	MJType typeCheck() throws TypeCheckerException {
		this.type = exp.typeCheck();
		return this.type;
	}

	void variableInit(HashSet<MJVariable> initialized)
			throws TypeCheckerException {
	}

}
