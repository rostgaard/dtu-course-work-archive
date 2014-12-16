package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import service.App;
import service.EventType;
import service.SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest;
import service.SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps.AddApp;
import service.SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps.GetAppByID;

/**
 * 
 * @author Luai s113444
 *
 */
public class TestAppService {
	
	private String errorMessage = "GET http://se-se2-e14-glassfish41-c.compute.dtu.dk:8080/Prototype245/rest/apps/addApp?mac=TestMac1&eventType=ACCELEROMETER returned a response status of 204 No Content";

	@Before
	public void setUp() throws Exception {
		// Deletes all the apps in the table
		//Localhost_Prototype1Rest.apps().deleteApp().getAsTextPlain(0,String.class);
	}

	@After
	public void tearDown() throws Exception {
		//Localhost_Prototype1Rest.apps().deleteApp().getAsTextPlain(0,String.class);
	}

	@Test
	public void addAppTest() {
		
		String mac1 = "TestMac1", mac2 = "TestMac2";
		AddApp addApp = SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.apps().addApp();
		
		App app1 = addApp.getAsApp(mac1, EventType.ACCELEROMETER.toString());
		
		assertEquals(mac1, app1.getMac());
		assertEquals(EventType.ACCELEROMETER, app1.getEventType());
		
		// Adds the same app twice
		try {
			addApp.getAsApp(mac1, EventType.ACCELEROMETER.toString());
			fail();
		} catch (Exception e) {
			assertEquals(errorMessage, e.getMessage());
		}
		
		// Add new  app
		App app2 = addApp.getAsApp(mac2, EventType.FLASHLIGHT.toString());
		
		/**
		 *  Test get from database, not all the get services are being tested.
		 */
		GetAppByID getApp = SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.apps().getAppByID();
		
		App appGet1 = getApp.getAsApp(app1.getId());
		App appGet2 = getApp.getAsApp(app2.getId());
		
		assertEquals(app1.getId(), appGet1.getId());
		assertEquals(app1.getMac(), appGet1.getMac());
		assertEquals(app1.getEventType(), appGet1.getEventType());
		assertEquals(app1.getEvents().size(), appGet1.getEvents().size());
		assertEquals(app2.getId(), appGet2.getId());
		assertEquals(app2.getMac(), appGet2.getMac());
		assertEquals(app2.getEventType(), appGet2.getEventType());
		assertEquals(app2.getEvents().size(), appGet2.getEvents().size());
		
		
		SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.apps().deleteApp().delete(app1.getId());
		SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.apps().deleteApp().delete(app2.getId());
	}

}
