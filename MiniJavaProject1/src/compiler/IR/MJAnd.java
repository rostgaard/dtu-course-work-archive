package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJAnd extends MJBinaryOp {

	public MJAnd(MJExpression a, MJExpression b) {
		super(a,b);
	}

	public void prettyPrint(PrettyPrinter prepri) {
		this.lhs.prettyPrint(prepri);
		prepri.print(" && ");
		this.rhs.prettyPrint(prepri);
	}


	/**
	 * The operands must be booleans 
	 */
	MJType typeCheck() throws TypeCheckerException { 
		if (!this.lhs.typeCheck().isBoolean())
			throw new TypeCheckerException("Variable " + this.lhs.toString() + " is not of the type: Boolean");
		
		if (!this.rhs.typeCheck().isBoolean())
			throw new TypeCheckerException("Variable " + this.lhs.toString() + " is not of the type: Boolean");		

		return this.type; } 


        void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {}

}
