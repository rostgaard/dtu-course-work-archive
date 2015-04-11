package compiler.IR;

import compiler.PrettyPrinter;

public class MJString extends MJExpression {
	String chars;
	

	public MJString(String chars) {
		super();
		this.chars = chars;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("\"" + chars + "\"");
	}

}
