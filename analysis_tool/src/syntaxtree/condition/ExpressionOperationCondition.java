package syntaxtree.condition;

import syntaxtree.RelationOperation;
import syntaxtree.expression.Expression;

/**
 * Data representation for expressions with a relational operator
 *
 */
public class ExpressionOperationCondition extends Condition {
	
	private Expression expr1;
	private Expression expr2;
	private RelationOperation ro;

	public ExpressionOperationCondition(Expression expr1, Expression expr2, RelationOperation ro){
		this.expr1 = expr1;
		this.expr2 = expr2;
		this.ro = ro; 
	}
}
