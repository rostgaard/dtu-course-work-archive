package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;
import compiler.Exceptions.VariableNotFound;

public class MJIdentifier extends MJExpression {

	private String name;
	private MJVariable decl;
	
	
	public MJIdentifier() {}

	public MJIdentifier(String name) {
		this.name = name;
	}
	

	public String getName() {
		return name;
	}

	public MJVariable getDecl() {
		return decl;
	}

	public void setDecl(MJVariable decl) {
		this.decl = decl;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print(this.name);
	}

	public MJType typeCheck() throws TypeCheckerException {

		MJVariable v;

		if (this.getName().equals("this")) {
			return new MJType(IR.currentClass.getName());
		}

		try {
			v = IR.stack.find(this.getName());
		} catch (VariableNotFound e1) {
			throw new TypeCheckerException("Identifier "+this.getName()+" not found.");
		}

		this.setType(v.getType());
		this.setDecl(v);

		return v.getType();
	}

	public void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {

		MJVariable v=null;

		if (this.getName().equals("this")) {
			return;
		}

		try {
			v = IR.stack.find(this.getName());
		} catch (VariableNotFound e1) {
			// this should not happen
		}

		if (!initialized.contains(v)) {
			throw new TypeCheckerException("Variable "+v.getName()+" not initialized.");
		}
	}

}
