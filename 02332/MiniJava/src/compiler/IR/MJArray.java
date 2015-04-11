package compiler.IR;

import compiler.PrettyPrinter;

public class MJArray extends MJIdentifier {
	
	MJIdentifier ident;
	MJExpression idx;

	public MJArray(MJIdentifier ident, MJExpression idx) {
		this.ident = ident;
		this.idx = idx;
		
	}
	
	public void prettyPrint(PrettyPrinter prepri) {
		ident.prettyPrint(prepri);
		prepri.print("[");
		idx.prettyPrint(prepri);
		prepri.print("]");
	}

}
