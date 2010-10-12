package compiler.IR;

import compiler.PrettyPrinter;

public class MJWhile extends MJStatement {
	private MJExpression condition;
	private MJStatement body;

	

	public MJWhile(MJExpression condition, MJStatement body) {
		super();
		this.condition = condition;
		this.body = body;
	}

	public MJExpression getCondition() {
		return condition;
	}



	public void setCondition(MJExpression condition) {
		this.condition = condition;
	}



	public MJStatement getBody() {
		return body;
	}



	public void setBody(MJStatement body) {
		this.body = body;
	}



	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("while (");
		condition.prettyPrint();
		prepri.print(")");
		body.prettyPrint();
		
	}
	
}
