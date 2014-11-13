package eao.model;

import java.util.ArrayList;
import java.util.List;

import dto.model.App;
import dto.model.Event;
import dto.model.EventType;
import entity.model.AppEntity;
import entity.model.EventEntity;

public class Conversion {

	public static Event convertEventEntity(EventEntity eventEntity) {
		int eventId = eventEntity.getId();
		float eventValue = eventEntity.getValue();
		long eventTime = eventEntity.getTime();
		
		int appID = eventEntity.getAppEntity().getId();
		
		return new Event(eventId, eventValue, eventTime, appID);
	}
	
	public static List<Event> convertEventEntityList(List<EventEntity> list) {
		if (list == null) return null;
		List<Event> events = new ArrayList<Event>();		
		
		for (EventEntity e : list) {
			events.add(convertEventEntity(e));
		}
		
		return events;
		
	}
	
	public static App convertAppEntity(AppEntity appEntity) {
		int id = appEntity.getId();
		String mac = appEntity.getMac();
		EventType eventType = appEntity.getEventType();
		boolean status = appEntity.getStatus();		
		List<Event> events = convertEventEntityList(appEntity.getEvents());		
		
		return new App(id, mac, eventType, status, events);
	}
	
	public static List<App> convertAppEntityList(List<AppEntity> list) {
		if (list == null) return null;
		List<App> apps = new ArrayList<App>();
		
		for (AppEntity a : list) {
			apps.add(convertAppEntity(a));
		}
		
		return apps;
	}
}
