package compiler.IR;

import java.util.HashSet;
import java.util.LinkedList;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJMethodCallExpr extends MJExpression {

	private MJIdentifier method;
	private LinkedList<MJExpression> arglist;

	public MJMethodCallExpr(MJIdentifier m, LinkedList<MJExpression> arglist) {
		this.method = m;
		this.arglist = arglist;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		boolean first = true;
		
		this.method.prettyPrint(prepri);
		prepri.print("(");
		for (MJExpression arg : arglist) {
			
			if (!first) {
				prepri.print(", ");
			} else {
				first = false;
			}
			
			arg.prettyPrint(prepri);
		}
		prepri.print(")");
	}

	/**
	 * This should be the same as the corresponding statement
	 */
	MJType typeCheck() throws TypeCheckerException { 
		
		
		return this.type; } 


        void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {}

}
