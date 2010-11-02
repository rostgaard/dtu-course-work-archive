package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJPlus extends MJBinaryOp {
	
	public void prettyPrint(PrettyPrinter prepri) {
		this.lhs.prettyPrint(prepri);
		prepri.print(" + ");
		this.rhs.prettyPrint(prepri);
	}

	MJType typeCheck() throws TypeCheckerException { 
		
		
		if (!this.lhs.typeCheck().isInt()) 
			throw new TypeCheckerException("Variable " + this.lhs.toString() + " is not of the type: Integer");

		if(!this.rhs.typeCheck().isInt())
			throw new TypeCheckerException("Variable " + this.rhs.toString() + " is not of the type: Integer");

		
		return this.type; } 


        void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {}

}
