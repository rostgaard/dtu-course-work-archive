/**
 * @author Kim Rostgaard Christensen - s084283
 */

package se2.sensornet.rules;

public class EventTrigger {

	private final String name;
	
	EventTrigger (String eventName) {
		this.name = eventName;
	}
	
	public String getName () {
		return this.name;
	}
	
	public String toString () {
		return this.name;
	}
	
}
