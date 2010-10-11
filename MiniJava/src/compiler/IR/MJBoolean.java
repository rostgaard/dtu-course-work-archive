package compiler.IR;
/**
 * Boolean literals must be the keywords true or false.
 */

import compiler.PrettyPrinter;

public class MJBoolean extends MJExpression {
	boolean value; 

	
	public MJBoolean(boolean value) {
		super();
		this.value = value;
	}


	public void prettyPrint(PrettyPrinter prepri) {
		if (value) prepri.print("true");
		else prepri.print("false");
	}
	

}
