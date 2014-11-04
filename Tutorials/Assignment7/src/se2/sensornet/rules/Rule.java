package se2.sensornet.rules;

import java.util.List;

public class Rule {

	private String       name;
	private Event        event;
	private Expression   condition;
	private List<Action> actions;

	String getName () {
		return this.name;
	}
	
	void setName (String name) {
		this.name = name;
	}

	Event getEvent() {
		return this.event;
	}
	
	void setEvent (Event event) {
		this.event = event;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public Expression  getCondition () {
		return this.condition;
	}

	public void setCondition (Expression condition) {
		this.condition = condition;
	}
	
	public String toString() {
		return name + ":\n when " +this.event + "\n if " + this.condition + "\n then " + this.actions;
		
	}
}
