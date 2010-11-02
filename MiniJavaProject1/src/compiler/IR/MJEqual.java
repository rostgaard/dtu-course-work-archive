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


	MJType typeCheck() throws TypeCheckerException { 
		
		if(!this.lhs.getType().isSame(this.rhs.getType()))
			throw new TypeCheckerException("Varaibles " + this.lhs.toString() + " and" + this.rhs.toString() + " are not of the same type");
		
		return this.type; } 


        void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {}

}
