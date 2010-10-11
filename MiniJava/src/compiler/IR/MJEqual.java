package compiler.IR;

import compiler.PrettyPrinter;

public class MJEqual extends MJBinaryOp {

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print(this.getLhs().toString() +" == "+this.getRhs().toString());
	}

}
