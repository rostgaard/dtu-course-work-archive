package eao.model;

import java.util.ArrayList;
import java.util.List;

import dto.model.App;
import dto.model.Event;
import dto.model.Policy;
import dto.model.RuleString;
import dto.model.User;
import entity.model.AppEntity;
import entity.model.EventEntity;
import entity.model.PolicyEntity;
import entity.model.RuleStringEntity;
import entity.model.UserEntity;
import enums.EventType;
import enums.Role;

public class Conversion {

	public static Event convertEventEntity(EventEntity eventEntity) {
		int eventId = eventEntity.getId();
		int eventValue = eventEntity.getValue();
		long eventTime = eventEntity.getTime();
		
		int appID = eventEntity.getAppEntity().getId();
		EventType eventType = eventEntity.getEventType();
		
		return new Event(eventId, eventValue, eventTime, appID, eventType);
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
	
	public static User convertUserEntity(UserEntity userEntity){
		String userName = userEntity.getUserName();
		String email = userEntity.getEmail();
		String firstName = userEntity.getFirstName();
		String lastName = userEntity.getLastName();
		Role role = userEntity.getRole();
		String password = userEntity.getPassword();
		Long lastLogin = userEntity.getLastLogin();

		return new User(userName, email, firstName, lastName, role, password, lastLogin);
	}
	
	public static List<User> convertUserEntityList(List<UserEntity> userEntityList){
		List<User> users = new ArrayList<User>();
		for(UserEntity e : userEntityList)
			users.add(convertUserEntity(e));
		
		return users;
		
	}
	
	public static Policy convertPolicyEntity(PolicyEntity policyEntity) {
		int id = policyEntity.getId();
		String name = policyEntity.getName();
		List<RuleString> ruleStrings = convertRuleStringEntityList(policyEntity.getRuleStringEntities());
		int securityLevel = policyEntity.getSecurityLevel();
		
		return new Policy(id, name, ruleStrings, securityLevel);
	}
	
	public static RuleString convertRuleStringEntity(RuleStringEntity entity) {
		int id = entity.getId();
		int policyId = entity.getPolicyEntity().getId();
		String ruleString = entity.getRule();
		boolean status = entity.getStatus();
		
		return new RuleString(id, policyId, ruleString, status);
	}
	
	public static List<Policy> convertPolicyEntityList(List<PolicyEntity> list) {
		if (list == null) return null;
		List<Policy> policies = new ArrayList<Policy>();
		
		for (PolicyEntity p : list) {
			policies.add(convertPolicyEntity(p));
		}
		
		return policies;
	}	
	
	public static List<RuleString> convertRuleStringEntityList(List<RuleStringEntity> list) {
		if (list == null) return null;
		List<RuleString> rules = new ArrayList<RuleString>();
		
		for (RuleStringEntity r : list) {
			rules.add(convertRuleStringEntity(r));
		}
		
		return rules;
	}
	
	
}
