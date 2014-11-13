package test;

import java.io.*;
import rule.engine.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Logger;


public class testParser {
	
	private static final Logger log = Logger.getLogger(testParser.class.getName());
	
	public static void main (String[] args) {
		File file = new File("../testData/example1.rules");
		
		try {
			testStream (new FileInputStream(file));
	    } catch (FileNotFoundException e) {
	    	System.out.println("File not found.");
	    }
		
		
		// This part just tests reading in rules from a string rather than a file.
		String ruleString = ""
			   + "test1:\n"
			   + "  when DoorAlarmEvent\n"
               + "  if event.source == 1 && event.value >= 50 && system.securitylevel == 1\n"
               + "  then actorSound1.play(1,30), UserAlert.raise(event.event);\n"
               + "\n"
               + "test2:\n"
               + "  when DoorAlarmEvent\n"
               + "  if system.securitylevel >= 2 || event.source == 1 && event.value >= 50\n"  
               + "  then actorSound1.play(1,30), UserAlert.raise(event.event);\n";
		
		InputStream stream = new ByteArrayInputStream(ruleString.getBytes());					 
		
		testStream (stream);
		
		// Tries to serialize the rules back to string and then into a ruleset again.
		InputStream stringStream = new ByteArrayInputStream(ruleString.getBytes());
		
		List<Rule> rules = new Rules (stringStream).parse();

		InputStream ruleStringStream = new ByteArrayInputStream(rulesString (rules).getBytes());
		
		System.out.println  (rulesString (new Rules (ruleStringStream).parse()));
	}
	public static String rulesString (List<Rule> rules) {
		String buffer = "";
		
		for (Rule rule : rules) {
			buffer += rule;
		}
		
		return buffer;
	}
	
	static void testStream (InputStream is ) {
		List<Rule> rules  = new Rules (is).parse();
		RuleEngine.systemSecurityLevel = 1;

		RuleEngine re = new RuleEngine(rules);
		
		System.out.println("### Parsed rules:");
		System.out.println(rulesString (rules));
		System.out.println("### End Parsed rules:");
		
		Event testEvent1 = new Event();
		testEvent1.type = "DoorAlarmEvent";
		testEvent1.addAttribute("value", 100);
		testEvent1.addAttribute("source", 1);
		
		System.out.println("testEvent1 matches the following rules:");
		
		for (Rule r : re.checkEvent (testEvent1)) {
			System.out.println(" * " + r.getName());
		};

		Event testEvent2 = new Event();
		testEvent2.type = "DoorAlarmEvent";
		testEvent2.addAttribute("value", 10);
		testEvent2.addAttribute("source", 1);
		
		System.out.println("testEvent2 matches the following rules:");
		
		for (Rule r : re.checkEvent (testEvent2)) {
			System.out.println(" * " + r.getName());
		};
		
		Event testEvent3 = new Event();
		testEvent3.type = "DoorAlarmEvent";
		testEvent3.addAttribute("value", 100);
		testEvent3.addAttribute("source", 2);
		
		System.out.println("testEvent3 matches the following rules:");
		
		for (Rule r : re.checkEvent (testEvent3)) {
			System.out.println(" * " + r.getName());
		};
		
		System.out.println("Rerunning testEvent1 with new system security level (Only test2 should match)");
		RuleEngine.systemSecurityLevel = 0;
		
		for (Rule r : re.checkEvent (testEvent1)) {
			System.out.println(" * " + r.getName());
		};
	}
	
}
