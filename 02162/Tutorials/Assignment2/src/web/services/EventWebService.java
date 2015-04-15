package web.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import model.DB;
import model.Event;
import model.EventList;

@Path("/events")
public class EventWebService {

	@GET
	@Path("/addEvent")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Event addEvent(@QueryParam("value") float value, @QueryParam("id") int id) {
		Event event = new Event(id, value);
		DB.getInstance().addEvent(event);
		return event;
	}
	
	@GET
	@Path("/getEvents")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public EventList getEventList(@QueryParam("id") int id) {
		EventList eventList = DB.getInstance().getEventList();
		List<Event> tempList = new ArrayList<Event>();
		
		for (Event e : eventList.getEvents()) {
			if (e.getId() == id) tempList.add(e);
		}
		
		return new EventList(tempList);
	}
	
}
