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
}
