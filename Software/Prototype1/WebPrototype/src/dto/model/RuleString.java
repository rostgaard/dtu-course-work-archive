package dto.model;

import javax.xml.bind.annotation.XmlRootElement;

import enums.EventType;

@XmlRootElement
public class RuleString {
	
	private int id;
	private int policyId;
	private String rule;
	private EventType eventType;
	private boolean status;
	
	public RuleString() {
		
	}
	
	public RuleString(int id, int policyId, String rule, EventType eventType, boolean status) {
		this.id = id;
		this.policyId = id;
		this.rule = rule;
		this.eventType = eventType;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

}
