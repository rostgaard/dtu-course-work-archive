package compiler.IR;

import compiler.PrettyPrinter;

public class MJArray extends MJIdentifier {
	
	MJType type;
	MJExpression idx;

	public MJArray(MJType type, MJExpression idx) {
		this.type = type;
		this.idx = idx;
		
	}
	
	public void prettyPrint(PrettyPrinter prepri) {
		type.prettyPrint(prepri);
		prepri.print("[");
		idx.prettyPrint(prepri);
		prepri.print("]");
	}

}
