package se2.sensornet.rules;

public class Attribute extends Expression {
	String object;
	String key;
	
	Attribute (String object, String key) {
		this.object = object;
		this.key = key;
	}
	
	public String toString () {
		return this.object + "." + this.key;
	}
}
