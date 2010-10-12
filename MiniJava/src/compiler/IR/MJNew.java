package compiler.IR;

import compiler.PrettyPrinter;

public class MJNew extends MJExpression {

	MJExpression expr;
	MJType type;

	public MJNew (MJType t){
		this.type = t;
		
	}
	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("new ");
		type.prettyPrint(prepri);
		prepri.print("()");
	}

}
