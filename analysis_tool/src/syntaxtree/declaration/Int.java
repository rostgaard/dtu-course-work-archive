package syntaxtree.declaration;

import syntaxtree.expression.Constant;
import syntaxtree.expression.Variable;

/**
 * Data representation integer decelerations
 *
 */
public class Int extends Declaration{
	
	private Constant value;

	public Int(Level lvl, Variable id, Constant value){
		this.lvl = lvl;
		this.id= id;
		this.value = value;
	}
}
