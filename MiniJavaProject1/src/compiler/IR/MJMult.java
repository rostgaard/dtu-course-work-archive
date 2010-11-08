package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJMult extends MJBinaryOp {

	public MJMult(MJExpression a, MJExpression b) {
		super(a,b);
	}

	public void prettyPrint(PrettyPrinter prepri) {
		this.lhs.prettyPrint(prepri);
		prepri.print(" * ");
		this.rhs.prettyPrint(prepri);
	}

	/*
	 * The ∗ operator type checks if both arguments type check and have type integer. 
	 * The expression has type integer.
	 * 
	 * @return MJType.Tint on success 
	 */
	MJType typeCheck() throws TypeCheckerException {
		
		if (!this.lhs.typeCheck().isInt())
			throw new TypeCheckerException(this.getClass().getName()+": lhs " + this.lhs.toString() + " is not of the type: Integer");
		
		if (!this.rhs.typeCheck().isInt())
			throw new TypeCheckerException(this.getClass().getName()+": rhs " + this.rhs.toString() + " is not of the type: Integer");
		
		return MJType.Tint; } 


        void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {}

}
