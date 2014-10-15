package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DB {

	private static DB instance;
	private static Map<Integer,List<Event>> events = new HashMap<Integer,List<Event>>();
	
	private static final long TIMEOUT = 9000;
	
	private DB() {
	
	}

	public static DB getInstance() {
		if (instance == null)
			instance = new DB();

		return instance;
	}
	
	public void addEvent(Event event) {
		
		List<Event> tempList = null;
		
		synchronized (events) {
			tempList = events.get(event.getId());
			if (tempList == null) {
				tempList = Collections.synchronizedList(new ArrayList<Event>());
				events.put(event.getId(), tempList);
			}
			
			event.setTime(System.currentTimeMillis());
			tempList.add(event);
			
		}
		
		synchronized (tempList) {
			tempList.notifyAll();
		}
	}
	
	public List<Event> getEvents(int id) {
		List<Event> tempList = events.get(id);
		if (tempList == null) {
			tempList = new ArrayList<Event>();
		}
		
		return tempList;
	}
	
	public Event awaitEvent(int id) {
		List<Event> tempList = null;
		
		synchronized (events) {
			tempList = events.get(id);
			if(tempList == null) {
				tempList = Collections.synchronizedList(new ArrayList<Event>());
				events.put(id, tempList);
			}
		}
		
		synchronized (tempList) {
			
			Event oldEvent = null;
			if (tempList.size()>0) {
				oldEvent = tempList.get(tempList.size()-1);
			}
			
			try {
				tempList.wait(TIMEOUT);
				
				if (tempList.size()>0) {
					
					Event newEvent = tempList.get(tempList.size()-1);
					if (!newEvent.equals(oldEvent))
						return newEvent;
				}
					
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		return null;
		
	}
}