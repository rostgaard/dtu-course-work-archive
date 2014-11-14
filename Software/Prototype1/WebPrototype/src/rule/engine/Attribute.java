/**
 * @author Kim Rostgaard Christensen - s084283
 */

package rule.engine;

import dto.model.Event;
import enums.EventType;

public class Attribute extends Expression {
	
	public static final String SYSTEM_OBJECT = "system";
	public static final String EVENT_OBJECT  = "event";
	
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
			Integer value = event.getAttributeValue(this.key);
			
			if (value == null) {
				throw new Error ("Unknown key: " + this.key);
			}
			
			return new Constant (value);	
		}
		
		throw new Error ("Unsupported object type: " + this.object);
		
	}
	
}
