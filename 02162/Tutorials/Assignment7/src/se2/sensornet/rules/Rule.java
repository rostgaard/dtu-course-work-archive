/**
 * @author Kim Rostgaard Christensen - s084283
 */

package se2.sensornet.rules;

import java.util.List;
import java.util.logging.*;


public class Rule {

	private String       name;
	private EventTrigger event;
	private Expression   condition;
	private List<Action> actions;
	
	private static final Logger log = Logger.getLogger(Rule.class.getName());

	boolean matches (Event event) {
		/* Early return. There is no need to check the
		   condition if event doesn't match.*/  
		if (!this.event.getName().toLowerCase().equals(event.type.toLowerCase())) {
			log.finest("Event does not match type. \"" +this.getEvent().getName() + "\" != \"" +event.type +"\"");
			return false;
		}
		log.finest("Event matches type. " +this.getEvent().getName() + " == " +event.type);
		
		return this.condition.matches (event);
	}
	
	public String getName () {
		return this.name;
	}
	
	public void setName (String name) {
		this.name = name;
	}

	public EventTrigger getEvent() {
		return this.event;
	}
	
	public void setEvent (EventTrigger event) {
		this.event = event;
	}

	public List<Action> getActions() {
		return actions;
	}

	private String actionsString() {
		String buffer = "";
		
		for (Action a : actions) {
			buffer += a;
			if (actions.indexOf(a) != actions.size()-1) {
				buffer += ", ";
			}
		}
		
		return buffer;
	}
	
	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public Expression getCondition () {
		return this.condition;
	}

	public void setCondition (Expression condition) {
		this.condition = condition;
	}
	
	public String toString() {
		return name + ":\n when " +this.event + "\n if " + this.condition + "\n then " + this.actionsString() + ";";
	}
}
