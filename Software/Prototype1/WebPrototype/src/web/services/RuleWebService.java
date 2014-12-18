package web.services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import rule.engine.Rule;
import rule.engine.RuleEngine;
import rule.engine.Rules;
import dto.model.Policy;
import dto.model.RuleString;
import eao.model.Conversion;
import eao.model.RuleDataEAO;
import eao.model.SensorDataEAO;
import entity.model.PolicyEntity;
import entity.model.RuleStringEntity;

/**
 * @author Luai s113444
 */

@Startup
@Singleton
@LocalBean
@Stateless
@Path("/rules")
public class RuleWebService {

	String ruleString = ""
			+ "test1:\n"
            + "  when accelerometer\n"
            + "  if event.source == 37 && system.securitylevel == 1\n"  
            + "  then playSound.play(43,30), flashlight.play(42,30), UserAlert.raise(44);\n";

	public static RuleEngine ruleEngine = null;
	
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
			
			for (Rule rule : rules) {
				rule.setPolicyID(1);
			}
			
			dbRules = Conversion.convertRuleStringEntityList(eao.addRuleStringEntities((new RuleEngine(rules)).ruleStrings()));
		}
		
		ruleEngine = new RuleEngine(RuleEngine.parseRules(dbRules));
	}
	
	private void reloadRules() {
		ruleEngine = new RuleEngine(RuleEngine.parseRules(this.getRulesFromDB()));

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
		return ruleEngine.ruleStrings();
	}
	
	@DELETE
	@Path("/deletePolicy")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	public void deletePolicy(@QueryParam("id") int id) {
		if (id == 0) {
			List<PolicyEntity> policyEntities = eao.getAllPolicyEntitylist();
			eao.deletePolicies(policyEntities);
		} else {
			eao.deletePolicy(id);
		}
		reloadRules();
	}
	
	@PUT
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
	public String getSecurityLevel() {
		return Integer.toString(RuleEngine.systemSecurityLevel);
	}
	
}
