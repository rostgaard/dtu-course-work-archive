import se2.sensornet.rules.*;
import java.io.*;

public class testParser {
	
	static Rules rules;

	public static void main (String[] args) {
		File file = new File("/home/krc/DTU/02162 Software Engineering 2 E14/work/repository/Tutorials/Assignment7/testData/example1.rules");
		FileInputStream iss = null;
		
		try {
				iss = new FileInputStream(file);
	    } catch (FileNotFoundException e) {
	    	System.out.println("File not found.");
	    }
		
		rules = new Rules (iss);
		
		
		for (Rule rule : rules.parse()) {
		  System.out.println(rule);
		}
		
		
	}
	
	
}
