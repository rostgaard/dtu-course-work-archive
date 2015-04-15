package web.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import model.DB;
import model.Event;

@Path("/events")
public class EventWebService {

	@GET
	@Path("/addEvent")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public Event addEvent(@QueryParam("value") float value, @QueryParam("id") int id) {
		Event event = new Event(id, value);
		DB.getInstance().addEvent(event);
		return event;
	}
	
	@GET
	@Path("/getEvents")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> getEventList(@QueryParam("id") int id) {
		return DB.getInstance().getEvents(id);
	}
	
	@GET
	@Path("/awaitEvent")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public Event awaitEvent(@QueryParam("id") int id) {
		return DB.getInstance().awaitEvent(id);
	}
}
