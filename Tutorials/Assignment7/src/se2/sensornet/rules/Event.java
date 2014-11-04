package se2.sensornet.rules;

public class Event {

	private final String name;
	
	Event (String eventName) {
		this.name = eventName;
	}
	
	public String getName () {
		return this.name;
	}
	
	public String toString () {
		return this.name;
	}
	
}
