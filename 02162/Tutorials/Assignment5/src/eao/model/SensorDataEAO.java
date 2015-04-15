package eao.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import dto.model.Event;
import entity.model.EventEntity;
import entity.model.Sensor;

@LocalBean
@Stateless
public class SensorDataEAO {
	
	@PersistenceContext(unitName = "Assignment5")
	EntityManager em;

	public EventEntity addEvent(float value, int id) {

		Sensor sensor = em.find(Sensor.class, id);
		if (sensor == null) {
			sensor = new Sensor();
			sensor.setId(id);
			em.persist(sensor);
		}

		EventEntity event = new EventEntity();
		event.setSensor(sensor);
		event.setValue(value);
		long time = System.currentTimeMillis();
		event.setTime(time);
		em.persist(event);
		em.flush(); // NEEDED for updating the automatically assigned id to the event entity
		return event;
	}
	

	public List<EventEntity> getEventlist(int id) {
		Sensor sensor = em.find(Sensor.class, id);
		if (sensor == null) {
			return Collections.emptyList();
		}
		
		return sensor.getEvents();
	}

	
	final private static String querytext =
		"SELECT e " +
		"FROM EventEntity e, Sensor s " +
		"WHERE " + 
		  "s.id = :id " +
		"AND " +
		  "e.sensor = s " +
		"AND " +
		  "e.time >  :time " +
		"ORDER BY e.time ASC";
	
	// Note: In JPA, you can also define @NamedQueries -- just not here.
	
	public List<EventEntity> getEventlist(int id, int time) {		
		TypedQuery<EventEntity> query = em.createQuery(querytext, EventEntity.class);
		query.setParameter("id", id);
		query.setParameter("time", System.currentTimeMillis() - time);
		List<EventEntity> result = query.getResultList();

		if (result != null) {
			return result;
		} else {
			return Collections.emptyList();
		}
	}


	public Event convertEventEntity(EventEntity eventEntity) {
		int id = eventEntity.getId();
		float value = eventEntity.getValue();
		long time = eventEntity.getTime();
		int sensorId = eventEntity.getSensor().getId();
		return new Event(id, value, time, sensorId);
	}
	
	public List<Event> convertEventEntityList(List<EventEntity> list) {
		List<Event> events = new ArrayList<Event>();
		
		for (EventEntity e : list) {
			events.add(convertEventEntity(e));
		}
		
		return events;
		
	}


}
