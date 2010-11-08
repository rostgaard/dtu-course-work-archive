package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;
import compiler.Exceptions.VariableAlreadyDeclared;

public class MJVariable extends IR {

	private MJType type;
	private String name;
	private MJExpression init;

	public MJVariable(MJType type, String argname) {
		this.type = type;
		this.name = argname;
		this.init = new MJNoExpression();
	}

	public MJVariable(MJType t, String string, MJExpression init) {
		this(t,string);
		this.init = init;
	}

	public MJType getType() {
		return type;
	}

	public String getName() {
		return name;
	}
	
	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print(this.type+" "+this.name);
		
		if (! (this.init instanceof MJNoExpression)) {
			prepri.print(" = ");
			this.init.prettyPrint(prepri);
		}
	}
	

	public MJType typeCheck() throws TypeCheckerException {
		this.getType().typeCheck();
		return this.getType();
	}
	
	public void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {
	
		try {
			IR.stack.add(this);
		} catch (VariableAlreadyDeclared e1) {
			// this should not happen!!!
		}
		return;

	}

}
