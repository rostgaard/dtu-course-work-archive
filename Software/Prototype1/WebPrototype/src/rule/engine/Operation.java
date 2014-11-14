/**
 * @author Kim Rostgaard Christensen - s084283
 */

package rule.engine;

import java.util.logging.Logger;

import dto.model.Event;

public class Operation extends Expression{
	final Expression lhs, rhs;
	final Operator   operation;
	
	private static final Logger log = Logger.getLogger(Operation.class.getName());

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
		boolean result = false;
		
		if (this.lhs instanceof Constant && this.rhs instanceof Constant) {
			result = this.evalConsCons ((Constant)this.lhs, (Constant)this.rhs, this.operation);
		}

		if (this.lhs instanceof Constant || this.rhs instanceof Constant) {
			if (this.rhs instanceof Attribute) {
				result = this.evalConsCons ((Constant)this.lhs, ((Attribute)this.rhs).value(event), this.operation);
			} else if (this.lhs instanceof Attribute){
				result = this.evalConsCons (((Attribute)this.lhs).value(event), (Constant)this.rhs, this.operation);
			} else {
				throw new Error ("Cannot perform boolean operations on " + (this.lhs.getClass().getSimpleName()) + " and " + (this.rhs.getClass().getSimpleName()));
			}
		} else {
			result = this.evalBoolBool(this.lhs.matches(event), this.rhs.matches(event), this.operation);
		}
		
		log.finest (this + " evaluates to " + result);
		
		return result;
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