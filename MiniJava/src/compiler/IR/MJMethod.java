package compiler.IR;

import java.util.HashSet;
import java.util.LinkedList;
import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;
import compiler.Exceptions.VariableAlreadyDeclared;

public class MJMethod extends IR {

	private MJType type;
	private String name;
	private LinkedList<MJVariable> parameters;
	private MJBlock body;
	private boolean isStatic;
	private boolean isPublic;
	
	public MJMethod(MJType type, String name, MJVariable par, MJBlock b, boolean isStatic, boolean isPublic) {
		this.type = type;
		this.name = name;
		this.parameters = new LinkedList<MJVariable>();
		this.parameters.addFirst(par);
		this.body = b;
		this.isStatic = isStatic;
		this.isPublic = isPublic;
	}

	public MJMethod(MJType type, String name, LinkedList<MJVariable> parlist,
			MJBlock b, boolean isStatic) {
		this.type = type;
		this.name = name;
		this.parameters = parlist;
		this.body = b;
		this.isStatic = isStatic;
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
			}
			v.prettyPrint(prepri);
		}
		prepri.println(")");
		body.prettyPrint(prepri);
		prepri.println("");
	}
	/**
	 * For a method to type check:
     *  - the method's return type and the parameter types must type check, the return type 
     *    may be void,
     *  - the block of statements must type check,
     *  - if the method has not return type void then the method’s return statements 
     *    must return an expression with the correct type, and
     *  - if the method has return type void then the return statements may not have an 
     *    argument.
 	 */
	
	public MJType typeCheck() throws TypeCheckerException {
		
		IR.currentMethod = this;

		this.getType().typeCheck();
		
		IR.stack.enterScope();
		
		try {
			for (MJVariable v : this.getParameters()) {
				v.typeCheck();
				IR.stack.add(v);
			}
		} catch (VariableAlreadyDeclared e1) {
			throw new TypeCheckerException("Two or more parameters with name "+e1.getMessage()+" in method " +
					IR.currentMethod.getName() + " in class "+IR.currentClass.getName()+".");
		}

		this.getBody().typeCheck();
		
		IR.stack.leaveScope();
		
		return MJType.Tvoid;
	}

	public void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {

		IR.stack.enterScope();

		for (MJVariable v : this.getParameters()) {
			initialized.add(v);
			v.variableInit(initialized);
		}

		this.getBody().variableInit(initialized);
		
		IR.stack.leaveScope();

	}
}
