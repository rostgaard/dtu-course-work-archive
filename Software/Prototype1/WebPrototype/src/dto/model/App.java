package dto.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class App {
	
	private int id;
	private String mac;
	private EventType eventType;
	private boolean status;
	private List<Event> events;
	
	public App() {
		
	}
	
	public App(int id, String mac, EventType eventType, boolean status, List<Event> events) {
		this.id = id;
		this.mac = mac;
		this.eventType = eventType;
		this.status = status;
		this.events = events;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getMac() {
		return mac;
	}
	
	public void setMac(String mac) {
		this.mac = mac;
	}
	
	public EventType getEventType() {
		return eventType;
	}
	
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

}
