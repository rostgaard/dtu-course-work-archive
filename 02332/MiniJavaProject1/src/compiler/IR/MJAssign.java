package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;
import compiler.Exceptions.VariableNotFound;

public class MJAssign extends MJStatement {

	private MJExpression rhs;
	private MJIdentifier lhs;

	public MJAssign(MJIdentifier lhs, MJExpression rhs) {
		this.rhs = rhs;
		this.lhs = lhs;
	}

	public MJIdentifier getLhs() {
		return this.lhs;
	}

	public MJExpression getRhs() {
		return this.rhs;
	}

	public void prettyPrint(PrettyPrinter prepri) {

		this.lhs.prettyPrint(prepri);
		prepri.print(" = ");
		this.rhs.prettyPrint(prepri);
		prepri.println(";");
	}

	public String toString() {
		return this.lhs.toString() + " = " + this.rhs.toString();
	}

	/*
	 * An assignment type checks if the variable is declared, the right hand
	 * side type checks, and both have the same type.
	 */
	MJType typeCheck() throws TypeCheckerException {
		
		if(compiler.config.DEBUG &&  this.lhs.typeCheck() == null) 
			System.out.println("lhs.typeCheck() is null");
		if(compiler.config.DEBUG && this.rhs.typeCheck() == null) 
			System.out.println("rhs.typeCheck() is null");
		
	
		if (!this.lhs.type.isSame(this.rhs.type) ) {
			throw new TypeCheckerException(this.getClass().getSimpleName()
					+ ": lhs (" + this.lhs.type.getName()
					+ ") is not of same type as rhs ("+ this.rhs.type.getName() +") in expression "
					+ this);
		}
		
		return MJType.Tnone;
	}

	void variableInit(HashSet<MJVariable> initialized)
			throws TypeCheckerException {
		MJVariable var=null;
		try {
			var = IR.find(rhs.getType().getName());
		} catch (VariableNotFound e) {	
			e.printStackTrace();
		}
		
		if(initialized.contains(var))
			lhs.variableInit(initialized);
		
	}

}
