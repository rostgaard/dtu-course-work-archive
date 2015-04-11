package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJComment extends MJStatement {

	private String comment;

	public MJComment(String c) {
		this.comment = c;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.println(this.comment);
	}

	MJType typeCheck() throws TypeCheckerException { return MJType.Tnone; } 


        void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {}

}
