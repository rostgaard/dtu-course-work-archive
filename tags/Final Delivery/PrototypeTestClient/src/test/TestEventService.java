package test;

import static org.junit.Assert.*;

import javax.ws.rs.WebApplicationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import service.App;
import service.Event;
import service.EventType;
import service.SeSe2E14Glassfish41CComputeDtuDk_SmartHomeSecurityRest;
import service.SeSe2E14Glassfish41CComputeDtuDk_SmartHomeSecurityRest.Apps.AddApp;
import service.SeSe2E14Glassfish41CComputeDtuDk_SmartHomeSecurityRest.Events.AddEventByID;
import service.SeSe2E14Glassfish41CComputeDtuDk_SmartHomeSecurityRest.Events.AddEventByMac;
import service.SeSe2E14Glassfish41CComputeDtuDk_SmartHomeSecurityRest.Events.AwaitEventByID;

/**
 * 
 * @author Luai s113444
 *
 */
public class TestEventService {
	
	App app1, app2, app3, app4;
	Event awaitEvent;
	boolean awaited = false;
	
	@Before
	public void setUp() throws Exception {
		AddApp addApp = SeSe2E14Glassfish41CComputeDtuDk_SmartHomeSecurityRest.apps().addApp();		
		app1 = addApp.getAsApp("TestMac1", EventType.ACCELEROMETER.toString());
		app2 = addApp.getAsApp("TestMac2", EventType.FLASHLIGHT.toString());
		app3 = addApp.getAsApp("TestMac3", EventType.PLAYSOUND.toString());
		app4 = addApp.getAsApp("TestMac4", EventType.USERALERT.toString());
	}

	@After
	public void tearDown() throws Exception {
		SeSe2E14Glassfish41CComputeDtuDk_SmartHomeSecurityRest.apps().deleteApp().delete(app1.getId());
		SeSe2E14Glassfish41CComputeDtuDk_SmartHomeSecurityRest.apps().deleteApp().delete(app2.getId());
		SeSe2E14Glassfish41CComputeDtuDk_SmartHomeSecurityRest.apps().deleteApp().delete(app3.getId());
		SeSe2E14Glassfish41CComputeDtuDk_SmartHomeSecurityRest.apps().deleteApp().delete(app4.getId());
	}

	@Test
	public void addEventTest() {
		AddEventByID  addEventByID = SeSe2E14Glassfish41CComputeDtuDk_SmartHomeSecurityRest.events().addEventByID();
		AddEventByMac addEventByMac = SeSe2E14Glassfish41CComputeDtuDk_SmartHomeSecurityRest.events().addEventByMac();
		
		Event event1 = addEventByID.getAsEvent(4, app1.getId(), app1.getEventType().toString());
		Event event2 = addEventByID.getAsEvent(4, app2.getId(), app2.getEventType().toString());
		Event event3 = addEventByMac.getAsEvent(app3.getMac(), 4, app3.getEventType().toString());
		Event event4 = addEventByMac.getAsEvent(app4.getMac(), 4, app4.getEventType().toString());
		
		Event event11 = addEventByID.getAsEvent(5, app1.getId(), app1.getEventType().toString());
		
		assertEquals(app1.getId(), event1.getAppID());
		assertEquals(app1.getEventType(), event1.getEventType());
		assertEquals(app2.getId(), event2.getAppID());
		assertEquals(app3.getId(), event3.getAppID());
		assertEquals(app4.getId(), event4.getAppID());
		assertEquals(app1.getId(), event11.getAppID());
		
	}
	
	@Test
	public void addEventToAppThatDoesNotExistTest() {
		// Add an event to an app that doesn't exist
		AddEventByID  addEventByID = SeSe2E14Glassfish41CComputeDtuDk_SmartHomeSecurityRest.events().addEventByID();
		Event eventWithNewApp = addEventByID.getAsEvent(4, 999, EventType.ACCELEROMETER.toString());
		App appNew = SeSe2E14Glassfish41CComputeDtuDk_SmartHomeSecurityRest.apps().getAppByID().getAsApp(eventWithNewApp.getAppID());
		
		assertNotNull(appNew);
		assertEquals(eventWithNewApp.getAppID(), appNew.getId());
		assertEquals(1, appNew.getEvents().size());
		assertEquals(eventWithNewApp.getId(), appNew.getEvents().get(0).getId());
		
		
		SeSe2E14Glassfish41CComputeDtuDk_SmartHomeSecurityRest.apps().deleteApp().delete(appNew.getId());
	}
	
	@Test
	public void awaitEventTest() {
		final AwaitEventByID awaitEventByID = SeSe2E14Glassfish41CComputeDtuDk_SmartHomeSecurityRest.events().awaitEventByID();
		
		try {
			assertNull(awaitEventByID.getAsEvent(app1.getId(), app1.getEventType().toString()));
			fail();
		} catch (WebApplicationException e) {
			// Timeout
			assertEquals("500 Internal Server Error", e.getMessage());
		}
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				awaitEvent = awaitEventByID.getAsEvent(app1.getId(), app1.getEventType().toString());
				awaited = true;
			}
		}).start();
		
		SeSe2E14Glassfish41CComputeDtuDk_SmartHomeSecurityRest.events().addEventByID().getAsEvent(2, app1.getId(), app1.getEventType().toString());
		
		while(true) {
			if (awaited) {
				assertNotNull(awaitEvent);
				assertEquals(app1.getId(), awaitEvent.getAppID());
				assertEquals(app1.getEventType(), awaitEvent.getEventType());
				break;
			} 
		}
		
		
	}

}
