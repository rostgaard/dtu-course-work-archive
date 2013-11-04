package syntaxtree.statement;

import syntaxtree.expression.Variable;

/**
 * Data representation for read statements (variables only)
 *
 */
public class Read extends Statement{

	private Variable id;
	
	public Read(Variable id){
		this.id = id;
	}

	public Variable getId() {
		return id;
	}

	public void setId(Variable id) {
		this.id = id;
	}
	
}
