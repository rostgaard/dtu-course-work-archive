package dto.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Event {
	
	private int id;
	private float value;
	private long time;
	private int appID;//Sensor id
	//private App app;
	//private int sensorId;
	private EventType eventType;

	public Event() {
		
	}
	
	public Event(int id, float value) {
		this.id = id;
		this.value = value;
	}
	
	public Event(int id, float value, long time, int appID) {
		this.id = id;
		this.value = value;
		this.time = time;
		this.appID = appID;
		//this.eventType = eventType;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public float getValue() {
		return value;
	}
	
	public void setValue(float value) {
		this.value = value;
	}
	
	public long getTime() {
		return time;
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	
	public void setAppID(int appID) {
		this.appID = appID;
	}
	
	public int getAppID() {
		return appID;
	}
	
	public EventType getEventType () {
		return eventType;
	}

	public void setEventType (EventType eventType) {
		this.eventType = eventType;
	}

}
