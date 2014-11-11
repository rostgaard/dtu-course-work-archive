import se2.sensornet.rules.*;

import java.io.*;
import java.util.List;
import java.util.logging.Logger;

public class testParser {
	
	static List<Rule> rules;
	private static final Logger log = Logger.getLogger(testParser.class.getName());
	
	public static void main (String[] args) {
		File file = new File("/home/krc/DTU/02162 Software Engineering 2 E14/work/repository/Tutorials/Assignment7/testData/example1.rules");
		FileInputStream iss = null;
		
		try {
			iss = new FileInputStream(file);
	    } catch (FileNotFoundException e) {
	    	System.out.println("File not found.");
	    }
		
		rules = new Rules (iss).parse();
		RuleEngine re = new RuleEngine(rules);
		
		for (Rule rule : rules) {
		  System.out.println(rule);
		}
		
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
