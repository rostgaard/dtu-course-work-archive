/**
 * @author Kim Rostgaard Christensen - s084283
 */

package rule.engine;

enum OperatorType { AND, OR, EQUAL, GTEQUAL};

public class Operator {
	private OperatorType type;
	
	Operator (OperatorType type) {
		this.type = type;
	}
	
	public OperatorType getType() {
		return this.type;
	}

	public String toString () {
		switch (this.type) {
			case AND:
				return "&&";
			case OR:
				return "||";
			case EQUAL:
				return "==";
			case GTEQUAL:
				return ">=";
		}
		throw new Error("Unknown operator type.");

	}
}
