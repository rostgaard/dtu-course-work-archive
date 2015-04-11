package compiler.IR;

import java.util.LinkedList;

import compiler.PrettyPrinter;
import compiler.Exceptions.ClassAlreadyDeclared;
import compiler.Exceptions.ClassErrorField;
import compiler.Exceptions.ClassErrorMethod;
import compiler.Exceptions.ClassNotFound;
import compiler.Exceptions.InheritanceError;
import compiler.Exceptions.TypeCheckerException;

public class MJProgram extends IR {

	private LinkedList<MJClass> classes;

	public MJProgram(LinkedList<MJClass> cdl) {
		this.classes = cdl;
	}

	public LinkedList<MJClass> getClasses() {
		return classes;
	}

	public void prettyPrint(PrettyPrinter prettyPrinter) {
		for (MJClass c : classes) {
			c.prettyPrint(prettyPrinter);
		}
	}


	public MJType typeCheck() throws TypeCheckerException {
		MJClass objectClass = new MJClass("Object", "Object", new LinkedList<MJVariable>(), new LinkedList<MJMethod>());
		this.getClasses().add(objectClass);
		
		//TODO change to include checks for main method
		for (MJClass c : this.getClasses()) {
			try {
				IR.classes.add(c);
			} catch (ClassAlreadyDeclared e1) {
				throw new TypeCheckerException(this.getClass().toString()+ ": Class "+e1.getMessage()+" already declared.");
			} catch (ClassErrorField e1) {
				throw new TypeCheckerException(this.getClass().toString()+": Class "+c.getName()+" has two fields with name "+e1.getMessage());
			}
		}
		
		for (MJClass c : this.getClasses()) {
				try {
					IR.classes.addMethods(c);
				} catch (ClassErrorMethod e1) {
					throw new TypeCheckerException(this.getClass().toString()+": Method "+e1.getMessage()+" already declared in " + c.getName());
				} catch (ClassNotFound e1) {
					throw new TypeCheckerException(this.getClass().toString()+": Class "+e1.getMessage()+" not found.");
				} catch (InheritanceError e1) {
					throw new TypeCheckerException(this.getClass().toString()+": Class "+c.getName()+" overwrites a method.");
				}
		}
		
		for (MJClass c : this.getClasses()) {
			
			c.typeCheck();
		}

		return MJType.Tnone;
	}
	
	public void variableInit() throws TypeCheckerException {
		for (MJClass c : this.getClasses()) {
			c.variableInit();
		}
	}


}
