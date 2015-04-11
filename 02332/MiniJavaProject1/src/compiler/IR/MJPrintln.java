package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJPrintln extends MJStatement {

	private MJExpression parameter;
	
	public MJPrintln(MJExpression parameter) {
		this.parameter = parameter;
	}

	public MJExpression getParameter() {
		return parameter;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("System.out.println(");
		this.parameter.prettyPrint(prepri);
		prepri.println(");");
	}
	
	MJType typeCheck() throws TypeCheckerException { return MJType.Tnone; } 


        void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {}

}
