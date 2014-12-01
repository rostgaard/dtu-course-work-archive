package dto.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Luai s113444
 */

@XmlRootElement
public class Policy {

	private int id;
	private String name;
	private List<RuleString> ruleStrings;
	private int securityLevel;
	
	public Policy() {
		
	}
	
	public Policy(int id, String name, List<RuleString> ruleStrings, int securityLevel) {
		this.id = id;
		this.name = name;
		this.ruleStrings = ruleStrings;
		this.securityLevel = securityLevel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<RuleString> getRuleStrings() {
		return ruleStrings;
	}

	public void setRuleStrings(List<RuleString> ruleStrings) {
		this.ruleStrings = ruleStrings;
	}

	public int getSecurityLevel() {
		return securityLevel;
	}

	public void setSecurityLevel(int securityLevel) {
		this.securityLevel = securityLevel;
	}
}
