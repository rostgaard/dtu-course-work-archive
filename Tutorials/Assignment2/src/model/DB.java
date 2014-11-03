package model;

import java.util.ArrayList;

public class DB {

	private static DB instance;
	private EventList eventList;

	private DB() {
		eventList = new EventList();
		eventList.setEvents(new ArrayList<Event>());
	}

	public static DB getInstance() {
		if (instance == null)
			instance = new DB();

		return instance;
	}
	
	public void addEvent(Event event) {
		eventList.getEvents().add(event); 
	}
	
//	public void setEventList(EventList eventList) {
//		this.eventList = eventList;
//	}
	
	public EventList getEventList() {
		return eventList;
	}
}