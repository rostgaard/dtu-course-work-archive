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

	MJType typeCheck() throws TypeCheckerException {
		
		if (!this.lhs.typeCheck().isInt())
			throw new TypeCheckerException("Variable " + this.lhs.toString() + " is not of the type: Integer");
		
		if (!this.rhs.typeCheck().isInt())
			throw new TypeCheckerException("Variable " + this.lhs.toString() + " is not of the type: Integer");
		
		return this.type; } 


        void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {}

}
