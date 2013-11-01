package syntaxtree.expression;

/**
 * Data representation for array expressions
 *
 */
public class ArrayExpression extends Expression{

	private Variable id;
	private Expression idx;
	
	public ArrayExpression(Variable id, Expression idx){
		this.id = id;
		this.idx = idx;
	}
	
}
