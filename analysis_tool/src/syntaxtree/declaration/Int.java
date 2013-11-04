package syntaxtree.declaration;

import syntaxtree.expression.Variable;

/**
 * Data representation integer decelerations
 *
 */
public class Int extends Declaration{
	
	public Int(Level lvl, Variable id){
		this.lvl = lvl;
		this.id= id;
	}

	@Override
	public String toString() {
		return "level: " + lvl.toString() + " Type: " + id.getType().toString() + " identifier: " + id.getId();
	}
}
