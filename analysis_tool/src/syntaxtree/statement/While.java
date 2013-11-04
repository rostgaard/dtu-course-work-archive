package syntaxtree.statement;

import java.util.List;

import syntaxtree.condition.Condition;

/**
 * Data representation for while statements
 *
 */
public class While extends Statement{

	private Condition cond;
	private List<Statement> body;
	
	public While(Condition cond, List<Statement> body){
		this.cond = cond;
		this.body = body;
	}

	public Condition getCond() {
		return cond;
	}

	public void setCond(Condition cond) {
		this.cond = cond;
	}

	public List<Statement> getBody() {
		return body;
	}

	public void setBody(List<Statement> body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		return "\nClass: " + getClass().getSimpleName() + "\nCondition: " + cond.toString() + "\nBody: " + body.toString() + "\n";
	}
}
