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

	/*
	 * An if-then-else statement type checks if the expression has type boolean
	 * and the then and the else block type check.
	 */
	MJType typeCheck() throws TypeCheckerException {
		if(!this.condition.typeCheck().isBoolean())
			throw new TypeCheckerException(this.getClass().getName() +": condition not of type boolean");
			
		this.thenblock.typeCheck();
		if (this.elseblock != null)
			this.elseblock.typeCheck();
		return MJType.Tnone;
	}

	void variableInit(HashSet<MJVariable> initialized)
			throws TypeCheckerException {
	}

}
