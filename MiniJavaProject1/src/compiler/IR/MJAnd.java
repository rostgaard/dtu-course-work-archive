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
	 * The && operator type checks if both arguments type check and have type boolean. 
	 * The expression has also the type boolean.
	 */
	MJType typeCheck() throws TypeCheckerException { 
		if (!this.lhs.typeCheck().isBoolean())
			throw new TypeCheckerException("Variable " + this.lhs.toString() + " is not of the type: Boolean");
		
		if (!this.rhs.typeCheck().isBoolean())
			throw new TypeCheckerException("Variable " + this.lhs.toString() + " is not of the type: Boolean");		

		return MJType.Tboolean; } 


        void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {}

}
