package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJIfElse extends MJStatement {

	private MJExpression condition;
	private MJBlock thenblock;
	private MJBlock elseblock;

	public MJIfElse(MJExpression condition, MJBlock thenblock, MJBlock elseblock) {
		this.condition = condition;
		this.thenblock = thenblock;
		this.elseblock = elseblock;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("if (");
		this.condition.prettyPrint(prepri);
		prepri.println(")");
			this.thenblock.prettyPrint(prepri);
		if (this.elseblock != null) {
			prepri.println("else");
			this.elseblock.prettyPrint(prepri);
		}
	}

	MJType typeCheck() throws TypeCheckerException { return MJType.Tnone; } 


        void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {}

}
