package syntaxtree.condition;

/**
 * Data representation for negation of boolean expressions
 *
 */
public class NegationCondition extends Condition{

	private Condition cond;
	
	public NegationCondition(Condition cond){
		this.cond = cond;
	}

	public Condition getCond() {
		return cond;
	}

	public void setCond(Condition cond) {
		this.cond = cond;
	}
	
}
