package compiler.IR;

import compiler.PrettyPrinter;

public class MJNew extends MJExpression {

	MJType type;

	MJNew (MJType type){
		this.type = type;
		
	}
	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("new");
		type.prettyPrint(prepri);
	}

}
