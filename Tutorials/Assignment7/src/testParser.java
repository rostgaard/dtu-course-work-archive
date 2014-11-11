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
		
		Event testEvent = new Event();
		testEvent.type = "DoorAlarmEvent";
		
		for (Action a : re.checkEvent (testEvent)) {
			System.out.println(a);
		};
		
	}
	
	
}
