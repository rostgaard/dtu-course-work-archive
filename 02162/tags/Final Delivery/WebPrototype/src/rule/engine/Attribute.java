/**
 * @author Kim Rostgaard Christensen - s084283
 */

package rule.engine;

import dto.model.Event;

public class Attribute extends Expression {
	
	public static final String SYSTEM_OBJECT = "system";
	public static final String EVENT_OBJECT  = "event";
	public static final String VALUE_KEY     = "value";
	public static final String SOURCE_KEY    = "source";
	
	String object;
	String key;
	
	Attribute (String object, String key) {
		this.object = object;
		this.key = key;
	}
	
	
	public boolean isValue() {
		return this.key.equals(VALUE_KEY);
	}
	

	public boolean isID() {
		return this.key.equals(SOURCE_KEY);
	}

	public String toString () {
		return this.object + "." + this.key;
	}

	@Override
	boolean matches(Event event) {
		return this.object.equals(EVENT_OBJECT);
	}
	
	/**
	 * FIXME: This method needs a way of accessing the runtime sensor values and system properties.
	 * @return
	 */
	public Constant value (Event event) {
		if (this.object.equals(SYSTEM_OBJECT)) {
			return new Constant (RuleEngine.systemSecurityLevel);
		} else if (this.object.equals(EVENT_OBJECT)) {

			if (this.isID()) {
				return new Constant (event.getAppID());
				//return new Constant (event.getId());
			} else if(this.isValue()) {
				return new Constant (event.getValue());
			} else {
				throw new Error ("Unknown object key: " + this.key);
			}
		}
		
		throw new Error ("Unsupported object type: " + this.object);
		
	}
	
}
