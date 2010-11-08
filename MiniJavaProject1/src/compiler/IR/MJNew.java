package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJNew extends MJExpression {

	protected MJType type;
	
	public MJNew(MJType type) {
		this.type = type;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("new "+ this.type + "()");
	}
	
	public String toString() {
		return "new "+ this.type + "()";
	}
	
	/*
	 * The creation of a new object type checks if the identifier type checks as a type. 
	 * The expression has class type with the name of the identifier.
	 * 
	 * @return MJType of the class 
	 */
	MJType typeCheck() throws TypeCheckerException {
		return type.typeCheck(); 
		} 


        void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {}

}
