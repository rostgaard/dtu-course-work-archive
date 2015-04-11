package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJUnaryMinus extends MJUnaryOp {

	public MJUnaryMinus(MJExpression l) {
		super(l);
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("-");
		this.arg.prettyPrint(prepri);
	}
	
	public String toString() {
		return "-"+this.arg;
		
	}

	/*
	 * The unary minus type checks if the argument type checks and has type
	 * integer. The expression has type integer.
	 * 
	 * @return MJType.Tint on success
	 */
	MJType typeCheck() throws TypeCheckerException {
		if(this.arg.typeCheck().isInt())
			this.type = MJType.Tint;
		else
			throw new TypeCheckerException("Unary minus expect argumet of type integer");
		return this.type;
	}

	void variableInit(HashSet<MJVariable> initialized)
			throws TypeCheckerException {
	}

}
