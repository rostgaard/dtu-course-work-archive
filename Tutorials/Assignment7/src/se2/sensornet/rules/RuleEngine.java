package se2.sensornet.rules;

import java.util.List;
import java.util.LinkedList;
import java.util.logging.Logger;

public class RuleEngine {

	final List<Rule> rules;
	
	private static final Logger log = Logger.getLogger(RuleEngine.class.getName());
	
	public RuleEngine (List<Rule> rules) {
		this.rules = rules;
	}
	
	public List<Action> checkEvent (Event event) {
		log.finest("Checking event " + event);
		
		List<Action> actions = new LinkedList<Action>(); 
		
		for (Rule rule : this.rules) {
			if (rule.matches (event)) {
				actions.addAll(rule.getActions());
			}
		}
		
		return actions;
	}
}
