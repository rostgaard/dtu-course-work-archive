package compiler.IR;

import java.util.HashSet;
import java.util.LinkedList;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJMethod extends IR {

	private MJType type;
	private String name;
	private LinkedList<MJVariable> parameters;
	private MJBlock body;
	private boolean isStatic;
	private boolean isPublic;

	public MJMethod(MJType type, String name, LinkedList<MJVariable> parList,
			MJBlock b, boolean isStatic, boolean isPublic) {
		this.type = type;
		this.name = name;
		this.parameters = parList;
		this.body = b;
		this.isStatic = isStatic;
		this.isPublic = isPublic;
	}

	public String getName() {
		return name;
	}

	public LinkedList<MJVariable> getParameters() {
		return parameters;
	}

	public MJType getType() {
		return type;
	}

	public MJBlock getBody() {
		return body;
	}

	public void setBody(MJBlock body) {
		this.body = body;
	}

	public boolean isStatic() {
		return this.isStatic;
	}

	public boolean isPublic() {
		return this.isPublic;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		if (this.isPublic()) {
			prepri.print("public ");
		}
		if (this.isStatic()) {
			prepri.print("static ");
		}
		prepri.print(this.type + " ");
		prepri.print(this.name + "(");
		boolean first = true;
		for (MJVariable v : this.parameters) {
			if (!first) {
				prepri.print(", ");
			} else {
				first = false;
			}
			v.prettyPrint(prepri);
		}
		prepri.println(")");
		body.prettyPrint(prepri);
		prepri.println("");
	}

	/*
	 * For a method to type check - the method’s return type and the parameter
	 * types must type check, the return type may be void,
	 * 
	 * - the block of statements must type check,
	 */
	MJType typeCheck() throws TypeCheckerException {
		body.typeCheck();

		MJReturn returnstmt = null;
		// The last statement in a body is the return statement
		if (this.body.getStatements().getLast() instanceof MJReturn) {
			returnstmt = (MJReturn) this.body.getStatements().getLast();

			MJType returnedType = returnstmt.typeCheck();

			
			if (returnedType == this.getType())
				return returnedType;
			else
				throw new TypeCheckerException(this.getClass().getName()
						+ ": method " + this.getName()
						+ " return statement does not match declaration");
		}
		else if(this.getName().equals("main")) {
			return MJType.Tnone;
		}

		else
			throw new TypeCheckerException(this.getClass().getName()
					+ ": method " + this.getName()
					+ " does not have a return statement");
	}

	void variableInit(HashSet<MJVariable> initialized)
			throws TypeCheckerException {
	}

}
