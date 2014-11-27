package eao.model;

import java.util.Collections;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.model.AppEntity;
import entity.model.EventEntity;
import enums.EventType;

@LocalBean
@Stateless
public class SensorDataEAO {
	
	@PersistenceContext(unitName = "Prototype1")
	EntityManager em;
	
	public AppEntity addApp(String mac, EventType eventType) {
		AppEntity appEntity = getAppEntity(mac, eventType);
		if (appEntity != null) {
			return null;
		}
		
		appEntity = new AppEntity();
		appEntity.setMac(mac);
		appEntity.setEventType(eventType);
		appEntity.setStatus(true);
		em.persist(appEntity);
		em.flush();
		
		return appEntity;
	}
	
	public EventEntity addEvent(int value, int appEntityId, EventType eventType) {
		AppEntity appEntity = em.find(AppEntity.class, appEntityId);
		if (appEntity == null) {
			appEntity = new AppEntity();
			appEntity.setId(appEntityId);
			appEntity.setEventType(eventType);
			appEntity.setStatus(true);
			em.persist(appEntity);
		}

		EventEntity event = new EventEntity();
		event.setAppEntity(appEntity);
		event.setValue(value);
		long time = System.currentTimeMillis();
		event.setTime(time);
		event.setEventType(eventType);
		em.persist(event);
		em.flush(); // NEEDED for updating the automatically assigned id to the event entity
		return event;
	}
	
	public EventEntity addEvent(int value, String mac, EventType eventType) {
		AppEntity appEntity = getAppEntity(mac, eventType);
		if (appEntity == null) {
			return null;
		}

		EventEntity event = new EventEntity();
		event.setAppEntity(appEntity);
		event.setValue(value);
		event.setTime(System.currentTimeMillis());
		event.setEventType(eventType);
		em.persist(event);
		em.flush(); // NEEDED for updating the automatically assigned id to the event entity
		return event;
	}	
	
	public AppEntity getAppEntity(int id) {
		return em.find(AppEntity.class, id);
	}
	
	public AppEntity getAppEntity(String mac, EventType eventType) {
		TypedQuery<AppEntity> query = em.createQuery(Query.queryGetApp, AppEntity.class);
		query.setParameter("mac", mac);
		query.setParameter("eventType",eventType);
		List<AppEntity> result = query.getResultList();
		
		// There should always be atmost one, it is not allowed to add more than once in the database
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}
	
	public List<AppEntity> getAppEntitylist(String mac) {
		TypedQuery<AppEntity> query = em.createQuery(Query.queryGetApplist, AppEntity.class);
		query.setParameter("mac", mac);
		List<AppEntity> result = query.getResultList();
		
		if (result.size() > 0) {
			return result;
		} else {
			return Collections.emptyList();
		}
	}
	
	public List<AppEntity> getAllAppEntitylist() {
		TypedQuery<AppEntity> query = em.createQuery(Query.querytextGetAllApps, AppEntity.class);	
		List<AppEntity> result = query.getResultList();

		if (result.size() > 0) {
			return result;
		} else {
			return Collections.emptyList();
		}
	}	
	

	public List<EventEntity> getEventlist(int id) {
		AppEntity appEntity = em.find(AppEntity.class, id);
		if (appEntity == null) {
			return Collections.emptyList();
		}
		
		return appEntity.getEvents();
	}
	
	public List<EventEntity> getAllEventlist() {
		TypedQuery<EventEntity> query = em.createQuery(Query.querytextGetAll, EventEntity.class);	
		List<EventEntity> result = query.getResultList();

		if (result.size() > 0) {
			return result;
		} else {
			return Collections.emptyList();
		}
	}	
	
	public List<EventEntity> getEventlist(int id, int time) {		
		TypedQuery<EventEntity> query = em.createQuery(Query.querytext, EventEntity.class);
		query.setParameter("id", id);
		query.setParameter("time", System.currentTimeMillis() - time);
		List<EventEntity> result = query.getResultList();

		if (result.size() > 0) {
			return result;
		} else {
			return Collections.emptyList();
		}
	}
	
	public List<EventEntity> getEventlist(int id, EventType eventType) {		
		TypedQuery<EventEntity> query = em.createQuery(Query.querytext2, EventEntity.class);
		query.setParameter("id", id);
		query.setParameter("eventType",eventType);
		List<EventEntity> result = query.getResultList();

		if (result.size() > 0) {
			return result;
		} else {
			return Collections.emptyList();
		}
	}
	
	/**
	 * source: http://stackoverflow.com/questions/1809159/how-to-implement-update-method-in-dao-using-entitymanager-jpa
	 */
	public AppEntity update(String mac, EventType eventType, boolean status) {
		TypedQuery<AppEntity> query = em.createQuery(Query.queryGetApp, AppEntity.class);
		query.setParameter("mac", mac);
		query.setParameter("eventType",eventType);
		List<AppEntity> result = query.getResultList();
		result.get(0).setStatus(status);
	    return em.merge(result.get(0));
	}


}
