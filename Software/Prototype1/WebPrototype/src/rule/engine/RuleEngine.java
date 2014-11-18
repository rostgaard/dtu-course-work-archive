package rule.engine;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Logger;

import dto.model.Event;
import dto.model.RuleString;

public class RuleEngine {

	final Map<Integer,List<Rule>> policy = new HashMap<>();
	
	private static final Logger log = Logger.getLogger(RuleEngine.class.getName());
	
	public static int systemSecurityLevel = 1;
	
	public RuleEngine (List<Rule> rules) {
		for (Rule rule : rules) {
			if (this.policy.containsKey(rule.getPolicyID())) {
				this.policy.get(rule.getPolicyID()).add(rule);
			} else {
				List<Rule> tmpList = new LinkedList<Rule>();
				tmpList.add(rule);
				this.policy.put(rule.getPolicyID(), tmpList);
			};
		}
	}
	
	/**
	 * 
	 * @param ruleString
	 * @return
	 */
	public static List<Rule> parseRule (RuleString ruleString) {
		if (!ruleString.isStatus()) {
			return new LinkedList<Rule>();
		}
		
		InputStream stream = new ByteArrayInputStream(ruleString.getRule().getBytes());
		
		List<Rule> rules = new Rules (stream).parse();
		
		for (Rule rule : rules) {
			rule.setPolicyID(ruleString.getPolicyId());
		}
		
		return rules;
	}

	public List<RuleString> ruleStrings () {
		List<RuleString> ruleStrings = new LinkedList<RuleString>();
		
		for (int key : this.policy.keySet()) {
			RuleString rs = new RuleString();
			rs.setRule(serializeRules (this.policy.get(key)));
			rs.setPolicyId(key);
			
			rs.setStatus(true);
			
			ruleStrings.add(rs);
		}
		
		return ruleStrings;
		
	}

	/**
	 * 
	 * @param rules
	 * @return
	 */
	public static String serializeRules (List<Rule> rules) {
		String buffer = "";
		
		for (Rule rule : rules) {
			buffer += rule;
		}
		
		return buffer;
	}
	
	/**
	 * TODO: Check for duplicates. Either by storing in a set or performing a contains operation.
	 * @param event
	 * @return
	 */
	public List<Rule> checkEvent (Event event) {
		log.finest("Checking event " + event);
		
		List<Rule> matches = new LinkedList<Rule>(); 
		
		for (List<Rule> rules : this.policy.values()) {
			for (Rule rule : rules) {
				if (rule.matches (event)) {
					matches.add(rule);
				}
			}
		}
		
		return matches;
	}
	
}
