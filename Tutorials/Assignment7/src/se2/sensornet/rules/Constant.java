package se2.sensornet.rules;

public class Constant extends Expression {
	int value;
	
	Constant (int value) {
		this.value = value;
	}
	
	public String toString () {
		return "" + this.value;
	}
}
