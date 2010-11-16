package compiler.IR;

import java.util.HashSet;
import java.util.LinkedList;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;
import compiler.Exceptions.VariableNotFound;

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

	// and this is for the general case

	public MJClass(String name, String superClassName,
			LinkedList<MJVariable> vdl, LinkedList<MJMethod> mdl) {
		this.name = name;
		this.fieldList = vdl;
		this.methodList = mdl;
		this.superClass = new MJType(superClassName);
	}

	public LinkedList<MJMethod> getMethodList() {
		return methodList;
	}

	public String getName() {
		return this.name;
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
		prepri.println("class " + this.getName() + " extends "
				+ this.getSuperClass() + " {");
		prepri.in();

		for (MJVariable v : this.fieldList) {
			v.prettyPrint(prepri);
			prepri.println(";");
		}

		prepri.println("");

		for (MJMethod m : this.methodList) {
			m.prettyPrint(prepri);
		}

		prepri.out();
		prepri.println("}");
	}

	/*
	 * For a field declaration to type check its type must type check and there
	 * may not be another field in the class with the same name.
	 */
	public MJType typeCheck() throws TypeCheckerException {
		if (compiler.config.DEBUG) {
			System.out.println(this.getClass().getSimpleName()+": Typechecking class " + this.getName());
		}

		IR.currentClass = this;
	

		// check fields

		for (MJVariable f : this.getFieldList()) {
			f.typeCheck();
		}

		// methods

		for (MJMethod m : this.getMethodList()) {
			m.typeCheck();
		}

		return MJType.Tnone;
	}

	public void variableInit() throws TypeCheckerException {

		for (MJMethod m : this.getMethodList()) {
			HashSet<MJVariable> set = new HashSet<MJVariable>();
			set.addAll(fieldList);

			try {
				set.add(IR.find("this"));
			} catch (VariableNotFound e) {
				e.printStackTrace();
			}

			m.variableInit(set);
		}
	}

}
