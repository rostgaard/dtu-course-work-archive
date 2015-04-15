/**
 * @author Kim Rostgaard Christensen - s084283
 */

package rule.engine;

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
