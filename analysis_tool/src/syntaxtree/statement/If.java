package syntaxtree.statement;

import java.util.List;

import syntaxtree.condition.Condition;
import syntaxtree.statement.Statement;

/**
 * Data representation for if statements
 *
 */
public class If extends Statement{

	private Condition cond;
	private List<Statement> tBranch;
	private List<Statement> fBranch;
	
	public If(Condition cond, List<Statement> tBranch, List<Statement> fBranch){
		this.cond = cond;
		this.tBranch = tBranch;
		this.fBranch = fBranch;
	}
}
