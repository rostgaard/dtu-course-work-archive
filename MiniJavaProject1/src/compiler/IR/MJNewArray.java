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

	/*
	 * The creation of a new integer array type checks if the expression type checks 
	 * and has type integer.
	 * The expression has type array of integers.
	 * @return MJ
	 */
	MJType typeCheck() throws TypeCheckerException {
		if(this.size.typeCheck().isInt())
			this.type = MJType.TintArray;
		else 
			throw new TypeCheckerException("Array size not of type integer");
		return this.type;
} 


        void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {}

}
