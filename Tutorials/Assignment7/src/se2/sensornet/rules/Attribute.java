/**
 * @author Kim Rostgaard Christensen - s084283
 */

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

	@Override
	boolean matches(Event event) {
		return 
		   (event.sensor == this.object) && 
		   (event.key    == this.key);
	}
	
	/**
	 * FIXME: This method needs a way of accessing the runtime sensor values and system properties.
	 * @return
	 */
	public Constant value () {
		return new Constant (100);
	}
	
}
