package com.dtu.cameraapp;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Jan-Eric Raab s123673
 */
public class App {
	
	@JsonProperty("id")
	private int id;
	@JsonProperty("mac")
	private String mac;
	@JsonProperty("eventType")
	private EventType eventType;
	@JsonProperty("status")
	private boolean status;
	@JsonProperty("events")
	private List<Event> events;
	
	public App () {
		
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

	public boolean isStatus() {
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

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

}
