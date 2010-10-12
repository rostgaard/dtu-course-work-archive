package compiler.IR;

import compiler.PrettyPrinter;

public class MJNewArray extends MJNew {

	MJExpression idx;
	
	
	public MJNewArray(MJType type, MJExpression idx) {
		super(type);
		this.idx = idx;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		super.prettyPrint(prepri);
		prepri.print("[");
		idx.prettyPrint(prepri);
		prepri.print("]");
	}

}
