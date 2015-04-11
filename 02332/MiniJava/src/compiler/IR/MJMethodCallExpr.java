package compiler.IR;

import java.util.LinkedList;

import compiler.PrettyPrinter;

public class MJMethodCallExpr extends MJExpression {
	private MJIdentifier indentifier;
	private LinkedList<MJExpression> parameters;

	
	public MJMethodCallExpr(MJIdentifier indentifier,
			LinkedList<MJExpression> parameters) {
		super();
		this.indentifier = indentifier;
		this.parameters = parameters;
	}




	public void prettyPrint(PrettyPrinter prepri) {
	}

}
