package compiler.IR;

import compiler.PrettyPrinter;

public class MJWhile extends MJStatement {
	private MJExpression condition;
	private MJBlock body;

	

	public MJWhile(MJExpression condition, MJBlock body) {
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



	public MJBlock getBody() {
		return body;
	}



	public void setBody(MJBlock body) {
		this.body = body;
	}



	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("while (");
		condition.prettyPrint();
		prepri.print(")");
		body.prettyPrint();
		
	}
	
}
