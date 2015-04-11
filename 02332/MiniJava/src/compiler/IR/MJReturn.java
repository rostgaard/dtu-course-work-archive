package compiler.IR;

import compiler.PrettyPrinter;

public class MJReturn extends MJStatement {
	MJExpression expr;
	
	

	public MJReturn(MJExpression expr) {
		super();
		this.expr = expr;
	}



	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("return");
	}

}
