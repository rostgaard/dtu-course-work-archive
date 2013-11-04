package syntaxtree.declaration;

import syntaxtree.expression.Variable;

/**
 * Abstract class for declarations
 *
 */
public abstract class Declaration {

	protected Level lvl;
	protected Variable id;
	
	public Level getLvl() {
		return lvl;
	}
	public void setLvl(Level lvl) {
		this.lvl = lvl;
	}
	public Variable getId() {
		return id;
	}
	public void setId(Variable id) {
		this.id = id;
	}
	
}
