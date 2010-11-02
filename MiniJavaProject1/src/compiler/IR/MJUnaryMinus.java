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

	MJType typeCheck() throws TypeCheckerException { return this.type; } 


        void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {}

}
