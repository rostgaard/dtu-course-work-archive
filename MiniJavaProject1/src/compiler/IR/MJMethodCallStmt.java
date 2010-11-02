package compiler.IR;

import java.util.HashSet;
import java.util.LinkedList;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJMethodCallStmt extends MJStatement {

	private MJIdentifier method;
	private LinkedList<MJExpression> arglist;

	public MJMethodCallStmt(MJIdentifier m, LinkedList<MJExpression> arglist) {
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
		prepri.println(");");
	}

	MJType typeCheck() throws TypeCheckerException { return MJType.Tnone; } 


        void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {}

}
