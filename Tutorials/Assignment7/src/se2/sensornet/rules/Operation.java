/**
 * @author Kim Rostgaard Christensen - s084283
 */

package se2.sensornet.rules;

public class Operation extends Expression{
	final Expression lhs, rhs;
	final Operator   operation;


	public Operation(Expression lhs, Expression rhs, Operator operation) {
		this.lhs = lhs;
		this.rhs = rhs;
		this.operation = operation;
	};

	public String toString () {
		return this.lhs + " " + this.operation + " " + this.rhs;
	}

	@Override
	boolean matches(Event event) {

		
		if (this.lhs instanceof Constant && this.rhs instanceof Constant) {
			return this.evalConsCons ((Constant)this.lhs, (Constant)this.rhs, this.operation);
		}

		if (this.lhs instanceof Constant || this.rhs instanceof Constant) {
			if (this.rhs instanceof Attribute) {
				return this.evalConsCons ((Constant)this.lhs, ((Attribute)this.rhs).value(), this.operation);
			} else if (this.lhs instanceof Attribute){
				return this.evalConsCons (((Attribute)this.lhs).value(), (Constant)this.rhs, this.operation);
			} else {
				throw new Error ("Cannot perform boolean operations on " + (this.lhs.getClass().getSimpleName()) + " and " + (this.rhs.getClass().getSimpleName()));
			}
		}
		
		return this.evalBoolBool(this.lhs.matches(event), this.rhs.matches(event), this.operation);
	}
	
	boolean evalBoolBool (boolean v1, boolean v2, Operator op) {
		switch (op.getType()) {
			case AND:
				return v1 && v2;
			case OR:
				return v1 || v2;
			default:
				throw new Error ("Unsupported operation " + op);
		}
	}

	boolean evalConsCons (Constant c1, Constant c2, Operator op) {
		switch (op.getType()) {
			case EQUAL:
				return c1.value == c2.value;
			case GTEQUAL:
				return c1.value >= c2.value;
			default:
				throw new Error ("Unsupported operation " + op);
		}
	}
	
}