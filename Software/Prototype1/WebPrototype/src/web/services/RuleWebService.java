package web.services;

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
import entity.model.PolicyEntity;
import entity.model.RuleStringEntity;
import enums.EventType;

@Startup
@Singleton
@LocalBean
@Stateless
@Path("/rules")
public class RuleWebService {
	
	private RuleEngine ruleEngine = null;
	
	@EJB
	RuleDataEAO eao;
	
	@PostConstruct
	private void startup() { 
		this.reloadRules();
	}
	
	private void reloadRules() {
		this.ruleEngine = new RuleEngine(RuleEngine.parseRules(getAllStringRuleList()));
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
	
	@GET
	@Path("/getAllRuleStrings")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces(MediaType.APPLICATION_JSON)
	public List<RuleString> getAllStringRuleList() {
		List<RuleStringEntity> ruleStringEntities = new ArrayList<RuleStringEntity>();
		List<RuleString> ruleStrings = new ArrayList<RuleString>();
		ruleStringEntities = eao.getAllRuleStringEntitylist();
		ruleStrings = Conversion.convertRuleStringEntityList(ruleStringEntities);
		return ruleStrings;
	}
	
	
}
