package syntaxtree.condition;

/**
 * Data representation for boolean expressions surrounded with parentheses 
 *
 */
public class ParenthesesCondition extends Condition{

	private Condition cond;
	
	public ParenthesesCondition(Condition cond){
		this.cond = cond;
	}

	public Condition getCond() {
		return cond;
	}

	public void setCond(Condition cond) {
		this.cond = cond;
	}	
	
}
