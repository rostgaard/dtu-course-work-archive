package eao.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import dto.model.RuleString;
import entity.model.PolicyEntity;
import entity.model.RuleStringEntity;

/**
 * @author Luai s113444
 */

@LocalBean
@Stateless
public class RuleDataEAO {
	
	@PersistenceContext(unitName = "Prototype2")
	EntityManager em;
	
	public PolicyEntity addPolicyEntity(String name) {
		PolicyEntity entity = getPolicyByName(name);
		if (entity != null) {
			return null;
		}
		
		entity = new PolicyEntity();
		entity.setName(name);
		entity.setSecurityLevel(1);
		em.persist(entity);
		em.flush();
		return entity;
	}
	
	public RuleStringEntity addRuleStringEntity(String ruleString, int policyId) {
		PolicyEntity policyEntity = getPolicyById(policyId);
		if (policyEntity == null) {
			policyEntity = new PolicyEntity();
			policyEntity.setId(policyId);
			policyEntity.setName("Policy " + policyId);
			policyEntity.setSecurityLevel(1);
			em.persist(policyEntity);
		}
	
		RuleStringEntity ruleStringEntity = new RuleStringEntity();
		ruleStringEntity.setRule(ruleString);
		ruleStringEntity.setPolicyEntity(policyEntity);
		ruleStringEntity.setStatus(true);
		em.persist(ruleStringEntity);
		em.flush();
		return ruleStringEntity;
	}
	
	public List<RuleStringEntity> addRuleStringEntities(List<RuleString> rules) {
		List<RuleStringEntity> entities = new ArrayList<RuleStringEntity>();
		for (RuleString r : rules) {
			entities.add(addRuleStringEntity(r.getRule(), r.getPolicyId()));
		}
		return entities;
	}
	
	public PolicyEntity getPolicyByName(String name) {
		TypedQuery<PolicyEntity> query = em.createQuery(Query.getPolicyByName, PolicyEntity.class);
		query.setParameter("name", name);
		List<PolicyEntity> result = query.getResultList();
		
		// There should always be atmost one, it is not allowed to add more than once in the database
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}
	
	public PolicyEntity getPolicyById(int id) {
		return em.find(PolicyEntity.class, id);
	}
	
	public RuleStringEntity getRuleStringEntityById(int id) {
		return em.find(RuleStringEntity.class, id);
	}
	
	public List<PolicyEntity> getAllPolicyEntitylist() {
		TypedQuery<PolicyEntity> query = em.createQuery(Query.getAllPolicies, PolicyEntity.class);	
		List<PolicyEntity> result = query.getResultList();

		if (result.size() > 0) {
			return result;
		} else {
			return Collections.emptyList();
		}
	}
	
	public List<RuleStringEntity> getAllRuleStringEntitylist() {
		TypedQuery<RuleStringEntity> query = em.createQuery(Query.getAllRuleStrings, RuleStringEntity.class);	
		List<RuleStringEntity> result = query.getResultList();

		if (result.size() > 0) {
			return result;
		} else {
			return Collections.emptyList();
		}
	}
	
	public void deletePolicy(int id) {
		PolicyEntity policyEntity = em.find(PolicyEntity.class, id);
		
		em.remove(policyEntity);
	}
	
	public void deletePolicies(List<PolicyEntity> entities) {
		for (PolicyEntity policyEntity : entities) {
			em.remove(policyEntity);			
		}
	}

}
