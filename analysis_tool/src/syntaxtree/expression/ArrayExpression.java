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

	public Variable getId() {
		return id;
	}

	public void setId(Variable id) {
		this.id = id;
	}

	public Expression getIdx() {
		return idx;
	}

	public void setIdx(Expression idx) {
		this.idx = idx;
	}
	
}
