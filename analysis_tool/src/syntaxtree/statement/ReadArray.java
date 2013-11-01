package syntaxtree.statement;

import syntaxtree.expression.Expression;
import syntaxtree.expression.Variable;

/**
 * Data representation for read statements (arrays only)
 *
 */
public class ReadArray extends Statement{

	private Variable id;
	private Expression idx;
	
	public ReadArray(Variable id, Expression idx){
		this.id = id;
		this.idx = idx;
	}
}
