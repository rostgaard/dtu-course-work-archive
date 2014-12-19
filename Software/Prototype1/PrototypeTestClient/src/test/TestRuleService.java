package test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import service.App;
import service.Event;
import service.EventType;
import service.SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest;
import service.SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Apps.AddApp;
import service.SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events.AddEventByID;
import service.SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.Events.GetEventsByID;
import service.RuleString;

/**
 * 
 * @author Luai s113444
 *
 */
public class TestRuleService {

	App app1, app2, app3, app4;
	RuleString dbRulestring;
	String ruleString;
	
	@Before
	public void setUp() throws Exception {
		//Localhost_Prototype1Rest.apps().deleteApp().getAsTextPlain(0,String.class);
		SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.rules().setSecurityLevel().put(1);
		
		AddApp addApp = SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.apps().addApp();		
		app1 = addApp.getAsApp("TestMac1", EventType.ACCELEROMETER.toString());
		app2 = addApp.getAsApp("TestMac2", EventType.PLAYSOUND.toString());
		app3 = addApp.getAsApp("TestMac3", EventType.FLASHLIGHT.toString());
		app4 = addApp.getAsApp("TestMac4", EventType.USERALERT.toString());
		
		ruleString = generateRuleString(app1.getId(), app2.getId(), app3.getId(), app4.getId());
		
		dbRulestring = SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.rules().addRuleString().postTextPlainAsJson(ruleString, 999, RuleString.class);
		//dbRulestring = SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.rules().addRuleString().getAsRuleString(ruleString, 999);
		assertEquals(ruleString, dbRulestring.getRule());
	}

	@After
	public void tearDown() throws Exception {
		SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.apps().deleteApp().delete(app1.getId());
		SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.apps().deleteApp().delete(app2.getId());
		SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.apps().deleteApp().delete(app3.getId());
		SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.apps().deleteApp().delete(app4.getId());
		
		SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.rules().deletePolicy().delete(dbRulestring.getPolicyId());
	}

	@Test
	public void ruleEngineTest() {
		AddEventByID  addEventByID = SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.events().addEventByID();
		GetEventsByID getEventById = SeSe2E14Glassfish41CComputeDtuDk_Prototype245Rest.events().getEventsByID();
		
//		// Should not trigger the rule
//		addEventByID.getAsEvent(40, app2.getId(), app2.getEventType().toString());
//		assertNull(getEventById.getAsEvent(app3.getId()));
//		// Reset app2
//		Localhost_Prototype1Rest.apps().deleteApp().getAsTextPlain(app2.getId(), String.class);
//		app2 = Localhost_Prototype1Rest.apps().addApp().getAsApp("TestMac2", EventType.FLASHLIGHT.toString());
		
		// Trigger the rule and actuate the actions
		Event event1 = addEventByID.getAsEvent(51, app1.getId(), app1.getEventType().toString());
		assertEquals(app1.getId(), event1.getAppID());
		
		Event event2 = getEventById.getAsEvent(app2.getId());
		Event event3 = getEventById.getAsEvent(app3.getId());
		Event event4 = getEventById.getAsEvent(app4.getId());
		
		assertEquals(app2.getId(), event2.getAppID());
		assertEquals(app2.getEventType(), event2.getEventType());
		
		assertEquals(app3.getId(), event3.getAppID());
		assertEquals(app3.getEventType(), event3.getEventType());
		
		assertEquals(app4.getId(), event4.getAppID());
		assertEquals(app4.getEventType(), event4.getEventType());
	}
	
	private String generateRuleString(int sensorId, int soundActId, int flashActId, int alertActId) {
		return ""
				+ "test1:\n"
	            + "  when accelerometer\n"
	            + "  if event.source == " + sensorId + " && event.value >= 50 && system.securitylevel == 1\n"  
	            + "  then playSound.play(" + soundActId + "), flashlight.play(" + flashActId + "), UserAlert.raise(" + alertActId + ");\n";
	}

}
