package syntaxtree.declaration;

import syntaxtree.expression.Constant;
import syntaxtree.expression.Variable;

/**
 * Data representation array decelerations
 *
 */
public class Array extends Declaration{

	private Constant size;
	
	public Array(Level lvl, Variable id, Constant size){
		this.lvl = lvl;
		this.id = id;
		this.size = size;
	}
}
