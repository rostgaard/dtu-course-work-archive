package web.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import rule.engine.Action;
import rule.engine.Rule;
import dto.model.Event;
import eao.model.Conversion;
import eao.model.SensorDataEAO;
import entity.model.AppEntity;
import entity.model.EventEntity;
import enums.EventType;

/**
 * @author Luai s113444
 */

@LocalBean
@Stateless
@Path("/events")
public class EventWebService {
	
	private static final long TIMEOUT = 9000;
	public static Map<Integer,List<EventEntity>> entitiesWaiting = new HashMap<Integer,List<EventEntity>>();
	
	@EJB SensorDataEAO eao;
	
	
	@GET
	@Path("/addEventByID")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public Event addEvent(@QueryParam("value") int value, @QueryParam("id") int sensorId, @QueryParam("eventType") EventType eventType) {	
		EventEntity eventEntity = eao.addEvent(value, sensorId, eventType);

		List<EventEntity> tempList = entitiesWaiting.get(sensorId);
		if (tempList != null) {
			//tempList = Collections.synchronizedList(tempList);
			synchronized(tempList) {
				tempList.notifyAll();
			}
		}
		
		Event event = Conversion.convertEventEntity(eventEntity);
		actuateAction(event);
		
		return event;
	}
	
	@GET
	@Path("/addEventByMac")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public Event addEvent(@QueryParam("mac") String mac, @QueryParam("value") int value, @QueryParam("eventType") EventType eventType) {	
		EventEntity eventEntity = eao.addEvent(value, mac, eventType);
		if (eventEntity == null) return null;
		
		List<EventEntity> tempList = entitiesWaiting.get(eventEntity.getAppEntity().getId());
		if (tempList != null) {
			//tempList = Collections.synchronizedList(tempList);
			synchronized(tempList) {
				tempList.notifyAll();
			}
		}
		
		Event event = Conversion.convertEventEntity(eventEntity);
		actuateAction(event);
		
		return event;
	}
	
	private void actuateAction(Event event) {
		Set<Rule> ruleMatches = RuleWebService.ruleEngine.checkEvent(event);
		System.out.println(ruleMatches);
		
		for (Rule rule : ruleMatches) {
			for (Action act : rule.getActions()) {
				
				addEvent(event.getValue(), act.getTargetActuatorID(), EventType.valueOf(act.getActuator().toUpperCase()));
			}
		}
	}
	
	@GET
	@Path("/getEventsByID")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)	
	public List<Event> getEventList(@QueryParam("id") int id) {
		List<EventEntity> eventEntities = new ArrayList<EventEntity>();
		List<Event> events = new ArrayList<Event>();
		eventEntities = eao.getEventlist(id);
		events = Conversion.convertEventEntityList(eventEntities);
		return events;
	}
	
	@GET
	@Path("/getEventsByMac")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)	
	public List<Event> getEventList(@QueryParam("mac") String mac) {
		List<EventEntity> eventEntities = new ArrayList<EventEntity>();
		List<AppEntity> appEntites = new ArrayList<AppEntity>();
		List<Event> events = new ArrayList<Event>();
		
		appEntites = eao.getAppEntitylist(mac);
		for (AppEntity app : appEntites) {
			eventEntities.addAll(app.getEvents());
		}
		
		events = Conversion.convertEventEntityList(eventEntities);
		return events;
	}
	
	@GET
	@Path("/getEventsByMacAndType")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> getEventList(@QueryParam("mac") String mac, @QueryParam("eventType") EventType eventType) {
		AppEntity appEntity = eao.getAppEntity(mac, eventType);		
		List<EventEntity> eventEntities = appEntity.getEvents();
		return Conversion.convertEventEntityList(eventEntities);
	}
	
	@GET
	@Path("/getAllEvents")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> getAllEventList() {
		List<EventEntity> eventEntities = new ArrayList<EventEntity>();
		List<Event> events = new ArrayList<Event>();
		eventEntities = eao.getAllEventlist();
		events = Conversion.convertEventEntityList(eventEntities);
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
		events = Conversion.convertEventEntityList(eventEntities);
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
		events = Conversion.convertEventEntityList(eventEntities);
		return events;
	}
	
	@GET
	@Path("/awaitEventByID")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public Event awaitEvent(@QueryParam("id") int id, @QueryParam("eventType") EventType eventType) {
		Event event = null;
		EventEntity eventEntity = awaitEventEntity(id, eventType);
		
		if (eventEntity != null) {
			event = Conversion.convertEventEntity(eventEntity);
		}
		
		return event;
	}
	
	@GET
	@Path("/awaitEventByMac")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public Event awaitEvent(@QueryParam("mac") String mac, @QueryParam("eventType") EventType eventType) {
		AppEntity appEntity = eao.getAppEntity(mac, eventType);
		
		Event event = null;
		EventEntity eventEntity = awaitEventEntity(appEntity.getId(), eventType);
		
		if (eventEntity != null) {
			event = Conversion.convertEventEntity(eventEntity);
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
