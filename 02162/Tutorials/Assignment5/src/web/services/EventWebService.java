package web.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import dto.model.Event;
import eao.model.SensorDataEAO;
import entity.model.EventEntity;

@LocalBean
@Stateless
@Path("/events")
public class EventWebService {
	
	@EJB SensorDataEAO eao;

	@GET
	@Path("/addEvent")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public Event addEvent(@QueryParam("value") float value, @QueryParam("id") int id) {
		EventEntity eventEntity = eao.addEvent(value, id);
		return eao.convertEventEntity(eventEntity);
	}
	
	
	@GET
	@Path("/getEvents")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> getEventList(@QueryParam("id") int id) {
		List<EventEntity> eventEntities = new ArrayList<EventEntity>();
		List<Event> events = new ArrayList<Event>();
		eventEntities = eao.getEventlist(id);
		events = eao.convertEventEntityList(eventEntities);
		return events;
	}
	
	@GET
	@Path("/getEventsInTimeSpan")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> getEventList(@QueryParam("id") int id, @QueryParam("time") int time) {
		List<EventEntity> eventEntities = new ArrayList<EventEntity>();
		List<Event> events = new ArrayList<Event>();
		eventEntities = eao.getEventlist(id, time);
		events = eao.convertEventEntityList(eventEntities);
		return events;
	}
}
