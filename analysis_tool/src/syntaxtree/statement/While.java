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
}
