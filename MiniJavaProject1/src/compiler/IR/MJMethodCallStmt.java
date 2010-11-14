package compiler.IR;

import java.util.HashSet;
import java.util.LinkedList;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;
import compiler.Exceptions.VariableNotFound;

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

	
	//
	MJType typeCheck() throws TypeCheckerException { 
		
		try {
			IR.find(method.getName()).typeCheck();
		} catch(VariableNotFound e) {
			// TODO Auto-generated catch block
			throw new TypeCheckerException("The hetmod: " + method.getName() + " was not declared");
		}
		for (int count = 0; count <= arglist.size() ; count++){
			arglist.get(count).typeCheck();
		}
		
		return MJType.Tnone; } 


        void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {}

}
