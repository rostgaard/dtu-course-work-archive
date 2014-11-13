package rule.engine;

import java.util.List;
import java.util.LinkedList;
import java.util.logging.Logger;

public class RuleEngine {

	final List<Rule> rules;
	
	private static final Logger log = Logger.getLogger(RuleEngine.class.getName());
	
	public static int systemSecurityLevel = 1;
	
	public RuleEngine (List<Rule> rules) {
		this.rules = rules;
	}
	
	/**
	 * TODO: Check for duplicates. Either by storing in a set or performing a contains operation.
	 * @param event
	 * @return
	 */
	public List<Rule> checkEvent (Event event) {
		log.finest("Checking event " + event);
		
		List<Rule> matches = new LinkedList<Rule>(); 
		
		for (Rule rule : this.rules) {
			if (rule.matches (event)) {
				matches.add(rule);
			}
		}
		
		return matches;
	}
}
