package web.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import sun.reflect.CallerSensitive;
import dto.model.Event;
import eao.model.SensorDataEAO;
import entity.model.EventEntity;
import entity.model.EventEntity.EventType;

@LocalBean
@Stateless
@Path("/events")
public class EventWebService {
	
	private static final long TIMEOUT = 9000;
	private static Map<Integer,List<EventEntity>> entitiesWaiting = new HashMap<Integer,List<EventEntity>>();
	
	@EJB SensorDataEAO eao;

	@GET
	@Path("/addEvent")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public Event addEvent(@QueryParam("value") float value, @QueryParam("id") int id, @QueryParam("eventType") EventType eventType) {	
		EventEntity eventEntity = eao.addEvent(value, id, eventType);
		
		List<EventEntity> tempList = entitiesWaiting.get(id);
		if (tempList != null) {
			//tempList = Collections.synchronizedList(tempList);
			synchronized(tempList) {
				System.out.println("NOtify");
				tempList.notifyAll();
			}
		}
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
	@Path("/getAllEvents")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> getAllEventList() {
		List<EventEntity> eventEntities = new ArrayList<EventEntity>();
		List<Event> events = new ArrayList<Event>();
		eventEntities = eao.getAllEventlist();
		events = eao.convertEventEntityList(eventEntities);
		return events;
	}
	
	@GET
	@Path("/getEventsByType")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> getEventList(@QueryParam("id") int id, @QueryParam("eventType") EventType eventType) {
		List<EventEntity> eventEntities = new ArrayList<EventEntity>();
		List<Event> events = new ArrayList<Event>();
		eventEntities = eao.getEventlist(id, eventType);
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
	
	@GET
	@Path("/awaitEvent")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public Event awaitEvent(@QueryParam("id") int id, @QueryParam("eventType") EventType eventType) {
		Event event = null;
		EventEntity eventEntity = awaitEventEntity(id, eventType);
		
		if (eventEntity != null) {
			event = eao.convertEventEntity(eventEntity);
		}
		
		return event;
	}
	
	private EventEntity awaitEventEntity(int id, EventType eventType) {
		
		List<EventEntity> eventEntities = Collections.synchronizedList(eao.getEventlist(id, eventType));
		
		synchronized (entitiesWaiting) {
				entitiesWaiting.put(id, eventEntities);
		}
		
		
		synchronized (eventEntities) {
			
			EventEntity tempEventEntity = null;
			if (eventEntities.size()>0) {
				tempEventEntity = eventEntities.get(eventEntities.size()-1);
			}
			
			try {
				eventEntities.wait(TIMEOUT);
				
				eventEntities = eao.getEventlist(id, eventType);
				
				if (eventEntities.size()>0) {	
					EventEntity newEvent = eventEntities.get(eventEntities.size()-1);
					if (!newEvent.equals(tempEventEntity)) {
						return newEvent;
					}
				}
				
				throw new WebApplicationException();
				//throw new TimeoutException();	
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			entitiesWaiting.remove(id);
		}
		
		return null;
		
	}
}
