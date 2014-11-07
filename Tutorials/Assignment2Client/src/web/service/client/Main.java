package web.service.client;

import web.service.client.Localhost_Assignment2Rest.Events;
import web.service.client.Localhost_Assignment2Rest.Events.AddEvent;
import web.service.client.Localhost_Assignment2Rest.Events.GetEvents;

public class Main {

	public static void main(String[] args) {
		Events events = Localhost_Assignment2Rest.events();
		
		AddEvent addEvent = events.addEvent();
		GetEvents getEvent = events.getEvents();
		
		
		Event event1 = addEvent.getAsEventJson(1.0f,2);
		Event event2 = addEvent.getAsEventJson(2.0f,2);
		Event event3 = addEvent.getAsEventJson(3.0f,2);
		Event event4 = addEvent.getAsEventJson(4.0f,3);
		System.out.println("Added: Id: " + event1.id + " Value: "+ event1.value);
		System.out.println("Added: Id: " + event2.id + " Value: "+ event2.value);
		System.out.println("Added: Id: " + event3.id + " Value: "+ event3.value);
		System.out.println("Added: Id: " + event4.id + " Value: "+ event4.value);
		
		EventList eventList1 = getEvent.getAsEventListJson(2);
		EventList eventList2 = getEvent.getAsEventListJson(3);
		
		System.out.println();
		for (Event e : eventList1.events)
			System.out.println("Id: " + e.id + " Value: "+ e.value);
			
		System.out.println();
		for (Event e : eventList2.events) 
			System.out.println("Id: " + e.id + " Value: "+ e.value);
	}

}
