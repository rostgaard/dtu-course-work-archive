package compiler.IR;

import compiler.PrettyPrinter;

public class MJSelector extends MJIdentifier {
	private MJIdentifier parent;
	
	public void setParent(MJIdentifier mji) {
		this.parent = mji;
	}
	
	public MJIdentifier getParent() {
		return parent;
	}
	
	public void prettyPrint(PrettyPrinter prepri) {
		parent.prettyPrint(prepri);
		prepri.print(".");
	}

}
