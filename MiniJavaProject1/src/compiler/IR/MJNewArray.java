package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJNewArray extends MJNew {

	private MJExpression size;
	
	public MJNewArray(MJType type, MJExpression size) {
		super(type);
		this.size = size;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("new "+this.type.getBaseType().getName()+"[");
		this.size.prettyPrint(prepri);
		prepri.print("]");
	}

	MJType typeCheck() throws TypeCheckerException { return this.type; } 


        void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {}

}
