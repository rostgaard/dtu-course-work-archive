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
	
}
