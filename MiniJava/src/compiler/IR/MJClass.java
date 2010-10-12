package compiler.IR;
/**
 * Rules:
 *  1. No two fields may have the same name,
 *  2. No combination of method name and parameter types may exist more than once, 
 *  3. Each method must type check.
 */

import java.util.HashSet;
import java.util.LinkedList;
import compiler.PrettyPrinter;
import compiler.Exceptions.ClassAlreadyDeclared;
import compiler.Exceptions.FieldAlreadyDeclared;
import compiler.Exceptions.MethodAlreadyDeclared;
import compiler.Exceptions.TypeCheckerException;

public class MJClass extends IR {

	private String name;
	private MJType superClass;
	private LinkedList<MJMethod> methodList = new LinkedList<MJMethod>();
	private LinkedList<MJVariable> fieldList = new LinkedList<MJVariable>();

	// this we have just for the main class
	
	public MJClass(String name, MJMethod md) {
		this.name = name;
		LinkedList<MJMethod> methodList = new LinkedList<MJMethod>();
		methodList.addFirst(md);
		this.methodList = methodList;
		this.superClass = new MJType("Object");
	}

	public void declarationCheck(MJVariable v ) throws FieldAlreadyDeclared {
		for(MJVariable decVar : fieldList){
			if (decVar.getName() == v.getName()) {
				// Rule 1
				throw new FieldAlreadyDeclared(v.getName() + " Already declared in "
						+ this.getName());
			}
		}
	}
	
	public void declarationCheck(MJMethod m ) throws MethodAlreadyDeclared {
		for(MJVariable decMet : fieldList){
			// Rule 2, first the names must match.
			if (decMet.getName() == m.getName()) {
				//TODO Then all parameters
				throw new MethodAlreadyDeclared(m.getName() + " Already declared in "
						+ this.getName());
			}
		}
	}

	// and this is for the general case
	
	public MJClass(String name, String superClassName, LinkedList<MJVariable> vdl,
			LinkedList<MJMethod> mdl) {
		this.name = name;
		this.fieldList = vdl;
		this.methodList = mdl;
		this.superClass = new MJType(superClassName);
	}

	public LinkedList<MJMethod> getMethodList() {
		return methodList;
	}

	public String getName() {
		return name;
	}

	public LinkedList<MJVariable> getFieldList() {
		return fieldList;
	}

	public void setSuperClass(MJType superClass) {
		this.superClass = superClass;
	}

	public MJType getSuperClass() {
		return superClass;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.println("class "+this.getName()+" {");
		prepri.in();
		// we know that field list is empty, so we just ignore it
		for (MJMethod m : this.methodList) {
			m.prettyPrint(prepri);
		}
		prepri.out();
		prepri.println("}");
		
	}
	
	public MJType typeCheck() throws TypeCheckerException {
		
		IR.currentClass = this;
		
		// check fields
		
		for (MJVariable f : this.getFieldList()) {
			f.typeCheck();
		}
		
		//methods
		
		for (MJMethod m : this.getMethodList()) {
			m.typeCheck();
		}
		
		return MJType.Tvoid;
	}

	public void variableInit() throws TypeCheckerException {
		for (MJMethod m : this.getMethodList()) {
			m.variableInit(new HashSet<MJVariable>());
		}
	}
}
