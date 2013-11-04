package syntaxtree.condition;

import syntaxtree.BooleanOperation;

/**
 * Data representation for boolean condition with a boolean operator
 *
 */
public class OperationCondition extends Condition{
	
	private Condition cond1;
	private Condition cond2;
	private BooleanOperation bo;
	
	public OperationCondition(Condition cond1, Condition cond2, BooleanOperation bo){
		this.cond1 = cond1;
		this.cond2 = cond2;
		this.bo = bo;
	}

	public Condition getCond1() {
		return cond1;
	}

	public void setCond1(Condition cond1) {
		this.cond1 = cond1;
	}

	public Condition getCond2() {
		return cond2;
	}

	public void setCond2(Condition cond2) {
		this.cond2 = cond2;
	}

	public BooleanOperation getBo() {
		return bo;
	}

	public void setBo(BooleanOperation bo) {
		this.bo = bo;
	}	
	
	@Override
	public String toString() {
		return "\nClass: " + getClass().getSimpleName() + "\nCondition1: " + cond1.toString() + "\nCondition2: " + cond2.toString() + "\nBoolean Operation: " + bo.toString() + "\n";
	}
}
