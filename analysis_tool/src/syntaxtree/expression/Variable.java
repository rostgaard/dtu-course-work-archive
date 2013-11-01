package syntaxtree.expression;

import syntaxtree.Type;

/**
 * Data representation for variables
 *
 */
public class Variable extends Expression{

	private Type type;
	private String id;
	
	public Variable(Type type, String id){
		this.type = type;
		this.id = id;
	}
	
}
