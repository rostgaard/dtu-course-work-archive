package web.services;

import java.io.*;

import rule.engine.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import rule.engine.RuleEngine;
import dto.model.Policy;
import dto.model.RuleString;
import eao.model.Conversion;
import eao.model.RuleDataEAO;
import eao.model.SensorDataEAO;
import entity.model.EventEntity;
import entity.model.PolicyEntity;
import entity.model.RuleStringEntity;
import enums.EventType;

@Startup
@Singleton
@LocalBean
@Stateless
@Path("/rules")
public class RuleWebService {

	String ruleString = ""
			   + "test1:\n"
			   + "  when DoorAlarmEvent\n"
            + "  if event.source == 1 && event.value >= 50 && system.securitylevel == 1\n"
            + "  then actorSound1.play(1,30), UserAlert.raise(event.event);\n"
            + "\n"
            + "test2:\n"
            + "  when DoorAlarmEvent\n"
            + "  if system.securitylevel >= 2 || event.source == 1 && event.value >= 50\n"  
            + "  then actorSound1.play(1,30), UserAlert.raise(event.event);\n";

	private RuleEngine ruleEngine = null;
	
	@EJB
	RuleDataEAO    eao;
	
	@EJB
	SensorDataEAO  seao;
	
	@PostConstruct
	private void startup() {
		List<RuleString> dbRules = this.getRulesFromDB();
		
		if (dbRules.isEmpty()) {
			InputStream stream = new ByteArrayInputStream(ruleString.getBytes());					 
			List<Rule> rules = new Rules (stream).parse();
			
			dbRules = Conversion.convertRuleStringEntityList(eao.addRuleStringEntities((new RuleEngine(rules)).ruleStrings()));				
		}
		
		this.ruleEngine = new RuleEngine(RuleEngine.parseRules(dbRules));
		
		//this.reloadRules();
	}
	
	private void reloadRules() {
		this.ruleEngine = new RuleEngine(RuleEngine.parseRules(this.getRulesFromDB()));

	}
	
	private void playsound (int acutatorID, int value) {
		EventEntity eventEntity = seao.addEvent(value, acutatorID, EventType.PLAY_SOUND);
		
		List<EventEntity> tempList = EventWebService.entitiesWaiting.get(acutatorID);
		if (tempList != null) {
			synchronized(tempList) {
				tempList.notifyAll();
			}
		}
	}
	
	
	private void userAlert (int sensorID) {
		EventEntity eventEntity = seao.addEvent(0, sensorID, EventType.USER_ALERT);
		
		List<EventEntity> tempList = EventWebService.entitiesWaiting.get(sensorID);
		if (tempList != null) {
			synchronized(tempList) {
				tempList.notifyAll();
			}
		}
	}
	
	
	
	@GET
	@Path("/addPolicy")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public Policy addPolicy(@QueryParam("name") String name) {
		PolicyEntity policyEntity = eao.addPolicyEntity(name);
		if (policyEntity == null) {
			return null;
		}
		
		return Conversion.convertPolicyEntity(policyEntity);	
	}
	
	@GET
	@Path("/addRuleString")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public RuleString addRuleString(@QueryParam("ruleString") String ruleString, @QueryParam("policyId") int policyId) {
		RuleStringEntity ruleStringEntity = eao.addRuleStringEntity(ruleString, policyId);
		if (ruleStringEntity == null) {
			return null;
		}
		
		reloadRules();
		
		return Conversion.convertRuleStringEntity(ruleStringEntity);	
	}
	
	@GET
	@Path("/getPolicyByName")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public Policy getPolicy(@QueryParam("name") String name) {		
		PolicyEntity policyEntity = eao.getPolicyByName(name);
		if (policyEntity == null) {
			return null;
		}
			
		return Conversion.convertPolicyEntity(policyEntity);
	}
	
	@GET
	@Path("/getPolicy")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public Policy getPolicy(@QueryParam("id") int id) {		
		PolicyEntity policyEntity = eao.getPolicyById(id);
		if (policyEntity == null) {
			return null;
		}
			
		return Conversion.convertPolicyEntity(policyEntity);
	}
	
	@GET
	@Path("/getRuleString")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public RuleString getRuleString(@QueryParam("id") int id) {		
		RuleStringEntity ruleStringEntity = eao.getRuleStringEntityById(id);
		if (ruleStringEntity == null) {
			return null;
		}
			
		return Conversion.convertRuleStringEntity(ruleStringEntity);
	}
	
	@GET
	@Path("/getAllPolicies")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public List<Policy> getAllPolicyList() {
		List<PolicyEntity> policyEntities = new ArrayList<PolicyEntity>();
		List<Policy> policies = new ArrayList<Policy>();
		policyEntities = eao.getAllPolicyEntitylist();
		policies = Conversion.convertPolicyEntityList(policyEntities);
		return policies;
	}

	private List<RuleString> getRulesFromDB() {
		List<RuleStringEntity> ruleStringEntities = new ArrayList<RuleStringEntity>();
		List<RuleString> ruleStrings = new ArrayList<RuleString>();
		ruleStringEntities = eao.getAllRuleStringEntitylist();
		ruleStrings = Conversion.convertRuleStringEntityList(ruleStringEntities);
		return ruleStrings;
	}
	
	@GET
	@Path("/getAllRuleStrings")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public List<RuleString> getAllStringRuleList() {
		return this.ruleEngine.ruleStrings();
	}
	
	
	@GET
	@Path("/setSecurityLevel")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public void setSecurityLevel(@QueryParam("level") int level) {
		RuleEngine.systemSecurityLevel = level;
	}
	
	@GET
	@Path("/getSecurityLevel")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public int getSecurityLevel() {
		return RuleEngine.systemSecurityLevel;
	}
	
}
