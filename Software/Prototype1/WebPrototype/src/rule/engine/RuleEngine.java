package rule.engine;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import dto.model.Event;
import dto.model.RuleString;

public class RuleEngine {

	// FIXME: The current global security level of the RuleEngine.
	public static int systemSecurityLevel = 1;
	
	// Internal logger.
	private static final Logger log = Logger.getLogger(RuleEngine.class.getName());

	// Rule storage.
	final Map<Integer,List<Rule>> policy = new HashMap<>();
	
	/**
	 * Constructs a new RuleEngine object. Used in conjunction with
	 * {@code parseRule} method (or the plural equivalent).
	 * 
	 * @param A list of Rule objects to add to this engine.
	 */
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
	 * Converts a RuleString DTO object to a list of rules.
	 * 
	 * @param ruleString The RuleString DTO object to convert
	 * @return A list of Rule objects
	 */
	public static List<Rule> parseRule (RuleString ruleString) {
		// We skip over ruleStrings that are disabled.
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
	
	/**
	 * Convenience method for converting a list of RulesString DTO's 
	 * rather than just one. The method merges Rule objects generated 
	 * from each list into a single list. The reversal of this operation
	 * is possible by using the {@code ruleStrings} method.
	 * 
	 * @param ruleStrings The list of RuleString DTO objects to convert
	 * @return A list of Rule objects
	 */
	public static List<Rule> parseRules (List<RuleString> ruleStrings) {
		List<Rule> rules = new LinkedList<Rule>();
		
		for (RuleString rule : ruleStrings) {
			rules.addAll(parseRule(rule));
		}
		
		return rules;
	}
	
	/**
	 * Extracts the Rule objects currently associated with this engine
	 * as a list of RuleString DTO objects.
	 * 
	 * @return
	 */
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
	 * Serialize a list of Rule objects to a (parseable) string format.
	 * 
	 * @param rules The list of rules to serialize.
	 * @return A parseable string representation of the rules 
	 */
	public static String serializeRules (List<Rule> rules) {
		String buffer = "";
		
		for (Rule rule : rules) {
			buffer += rule;
		}
		
		return buffer;
	}
	
	/**
	 * Checks an event against the rules currently active in the 
	 * RuleEngine and returns a set of rules that responds to this
	 * event. It is similar in semantics to a filter operation, where
	 * the rules are regarded as filter.
	 * 
	 * TODO: Check for duplicates. Either by storing in a set or performing a contains operation.
	 * 
	 * @param event The event to check
	 * @return A set of rules that matches.
	 */
	public Set<Rule> checkEvent (Event event) {
		log.finest("Checking event " + event);
		
		Set<Rule> matches = new HashSet<Rule>(); 
		
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
