package com.example.prototypeapp;

import org.codehaus.jackson.annotate.JsonProperty;

public class Event {
	
	@JsonProperty("id")
	private int id;
	@JsonProperty("value")
	private float value;
	@JsonProperty("time")
	private long time;
	@JsonProperty("eventType")
	private EventType eventType;
	
	public Event() {
		
	}
	
	public Event(int id, float value, long time) {
		this.id = id;
		this.value = value;
		this.time = time;
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
		return this.time;
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	
	public EventType getEventType() {
		return eventType;
	}
	
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

}
