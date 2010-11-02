package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJWhile extends MJStatement {

		private MJExpression condition;
		private MJStatement body;
		
	public MJWhile(MJExpression condition, MJStatement body) {
		this.condition = condition;
		this.body = body;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("while (");
		this.condition.prettyPrint(prepri);
		prepri.println(")");
		this.body.prettyPrint(prepri);
	}
	
	MJType typeCheck() throws TypeCheckerException { return MJType.Tnone; } 


        void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {}

}
