package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJArray extends MJIdentifier {

	private MJIdentifier array;
	private MJExpression index;

	public MJArray(MJIdentifier array, MJExpression idx) {
		this.array = array;
		this.index = idx;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		this.array.prettyPrint(prepri);
		prepri.print("[");
		this.index.prettyPrint(prepri);
		prepri.print("]");
	}
	
	public String toString() {
		return this.array.getName() + "[" + index.toString() + "]";
	}

	/*
	 * An array assignment type checks if the variable is declared and has array
	 * type, both expressions type check, the index expression has type integer,
	 * and the right hand side has the same type as the array’s base type.
	 */
	MJType typeCheck() throws TypeCheckerException {
		//check if the variable is declared
		if(!array.typeCheck().isArray()) 
			throw new TypeCheckerException(this.getClass().getSimpleName()+": Identifier "+ this.array.getName() + " not of type array");
		if(!this.index.typeCheck().isInt())
			throw new TypeCheckerException(this.getClass().getSimpleName()+": bad index of "+ this.array.getName() + " not of type integer");

		this.type = MJType.TintArray;
		return this.type;
	}

	void variableInit(HashSet<MJVariable> initialized)
			throws TypeCheckerException {
		array.variableInit(initialized);
	}

}
