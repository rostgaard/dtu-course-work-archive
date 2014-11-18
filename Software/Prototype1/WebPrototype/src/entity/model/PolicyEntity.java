package entity.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
@Cacheable(false)
public class PolicyEntity implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	@OneToMany(mappedBy="policyEntity")
	private List<RuleStringEntity> ruleStringEntities;
	
	private int securityLevel;

	private static final long serialVersionUID = 1L;
	
	public PolicyEntity() {
		super();
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSecurityLevel() {
		return securityLevel;
	}

	public void setSecurityLevel(int securityLevel) {
		this.securityLevel = securityLevel;
	}

	public List<RuleStringEntity> getRuleStringEntities() {
		return ruleStringEntities;
	}

	public void setRuleStringEntities(List<RuleStringEntity> ruleStringEntities) {
		this.ruleStringEntities = ruleStringEntities;
	}
}
