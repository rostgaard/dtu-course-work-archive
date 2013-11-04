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

	public Condition getCond() {
		return cond;
	}

	public void setCond(Condition cond) {
		this.cond = cond;
	}

	public List<Statement> gettBranch() {
		return tBranch;
	}

	public void settBranch(List<Statement> tBranch) {
		this.tBranch = tBranch;
	}

	public List<Statement> getfBranch() {
		return fBranch;
	}

	public void setfBranch(List<Statement> fBranch) {
		this.fBranch = fBranch;
	}
	
	@Override
	public String toString() {
		return "\nClass: " + getClass().getSimpleName() + "\nCondition: " + cond.toString() + "\nTrue branch: " + tBranch.toString() + "\nFalse branch: " + fBranch.toString() + "\n";
	}	
}
