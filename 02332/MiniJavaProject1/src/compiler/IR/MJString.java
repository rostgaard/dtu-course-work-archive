package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJString extends MJExpression {

	private String string;

	public MJString(String string) {
		this.string = string;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print(this.string);
	}

	public String toString() {
		return this.string;
	}
	/*
	 * String type checks and has the class type String.
	 * 
	 * @return MJType.TString
	 */
	MJType typeCheck() throws TypeCheckerException {
		this.type = MJType.TString; 
		return this.type;
	}

	void variableInit(HashSet<MJVariable> initialized)
			throws TypeCheckerException {
	}

}
