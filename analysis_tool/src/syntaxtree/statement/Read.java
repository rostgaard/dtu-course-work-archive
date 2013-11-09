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

    @Override
    public void RD() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
}
