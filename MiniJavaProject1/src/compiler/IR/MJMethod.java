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
	
	public MJMethod(MJType type, String name, LinkedList<MJVariable> parList, MJBlock b, boolean isStatic, boolean isPublic) {
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
		prepri.print(this.type+" ");
		prepri.print(this.name+"(");
		boolean first=true;
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
	
	MJType typeCheck() throws TypeCheckerException { return MJType.Tnone; } 


        void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {}

}
