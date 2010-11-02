package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJNew extends MJExpression {

	protected MJType type;
	
	public MJNew(MJType type) {
		this.type = type;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("new "+ this.type + "()");
	}

	MJType typeCheck() throws TypeCheckerException { return this.type; } 


        void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {}

}
