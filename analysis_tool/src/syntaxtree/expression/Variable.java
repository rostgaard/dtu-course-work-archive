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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String debugInformation() {
		return "\nClass: " + getClass().getSimpleName() + "\nType: " + type.toString() + "\nIdentifier: " + id.toString() + "\n";
	}

        @Override
        public String toString() {
            return id;
        }
        
}
