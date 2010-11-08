package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJEqual extends MJBinaryOp {

	public MJEqual(MJExpression a, MJExpression b) {
		super(a,b);
	}

	public void prettyPrint(PrettyPrinter prepri) {
		this.lhs.prettyPrint(prepri);
		prepri.print(" == ");
		this.rhs.prettyPrint(prepri);
	}


	/*
	 * The == operator type checks if both arguments type check and have the same type. 
	 * The expression has the type boolean.
	 * @return MJType.Tboolean on success
	 */
	MJType typeCheck() throws TypeCheckerException { 
		
		if(!this.lhs.getType().isSame(this.rhs.getType()))
			throw new TypeCheckerException("Varaibles " + this.lhs.toString() + " and" + this.rhs.toString() + " are not of the same type");
		
		return MJType.Tboolean; } 


        void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {}

}
