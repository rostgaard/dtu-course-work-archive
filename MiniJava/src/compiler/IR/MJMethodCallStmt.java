package compiler.IR;

import java.util.LinkedList;

import compiler.PrettyPrinter;

public class MJMethodCallStmt extends MJStatement {
	private MJIdentifier indentifier;
	private LinkedList<MJExpression> parameters;
	
	
	
	public MJMethodCallStmt(MJIdentifier indentifier,
							LinkedList<MJExpression> parameters) {
		super();
		this.indentifier = indentifier;
		this.parameters = parameters;
	}
	
	



	public MJIdentifier getIndentifier() {
		return indentifier;
	}





	public void setIndentifier(MJIdentifier indentifier) {
		this.indentifier = indentifier;
	}





	public LinkedList<MJExpression> getParameters() {
		return parameters;
	}





	public void setParameters(LinkedList<MJExpression> parameters) {
		this.parameters = parameters;
	}





	public void prettyPrint(PrettyPrinter prepri) {
	}

}
