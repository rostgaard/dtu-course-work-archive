/**
 * @author Kim Rostgaard Christensen - s084283
 */

package se2.sensornet.rules;

public class Constant extends Expression {
	int value;
	
	Constant (int value) {
		this.value = value;
	}
	
	public String toString () {
		return "" + this.value;
	}

	@Override
	boolean matches(Event event) {
		throw new Error("An event cannot match a Constant directly");
	}
}
