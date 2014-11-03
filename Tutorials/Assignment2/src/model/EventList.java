package model;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EventList {
	
	private List<Event> events;
	
	public EventList() {
		
	}
	
	public EventList(List<Event> events) {
		this.events = events;
	}
	
	//@XmlElement(name="event")
	public List<Event> getEvents() {
		return this.events;
	}
	
	public void setEvents(List<Event> events) {
		this.events = events;
	}

}
