package compiler.Phases;

import compiler.Exceptions.*;
import compiler.IR.*;

public class Analysis {

	public static void analyse(IR ir) throws TypeCheckerException {
		
		// type check program
		// this also computes a type for every expression
		
		ir.getProgram().typeCheck();
		
		// check that variables are initialized before used

		ir.getProgram().variableInit();
		
	}
}
