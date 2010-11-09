package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.ClassErrorField;
import compiler.Exceptions.ClassNotFound;
import compiler.Exceptions.TypeCheckerException;
import compiler.Exceptions.VariableNotFound;

public class MJSelector extends MJIdentifier {

	private MJIdentifier object;
	private MJIdentifier field;

	public MJSelector(MJIdentifier t, MJIdentifier field) {
		this.object = t;
		this.field = field;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		this.object.prettyPrint(prepri);
		prepri.print(".");
		this.field.prettyPrint(prepri);
	}
	
	public String toString() {
		return object +"."+ field;
	}

	/*
	 * A selector type checks if a declaration of the first identifier is
	 * visible in the current scope, has a class type, and the class defines a
	 * field with the name of the second identifier.
	 * 
	 * The selector has the type of that field.
	 * 
	 * @return MJType - The same type of the field
	 */
	MJType typeCheck() throws TypeCheckerException {
		// An object needs to be of class type
		MJClass c = null;
		
		
		try {
			c = IR.classes.lookup(object.typeCheck().getName());
		} catch (ClassNotFound e1) {
			throw new TypeCheckerException(this.getClass().getSimpleName()
					+ ": undefined object class " + object.getType().getName());
		}
		
		if(compiler.config.DEBUG)
			System.out.println(this.getClass().getSimpleName() +": Field "+ this.field.getName() + " found in class "+ c.getName());

		// Is the identifier visible in the current scope?
		try {
			IR.find(object.getName());
		} catch (VariableNotFound e) {
			throw new TypeCheckerException(this.getClass().getSimpleName() + ": "
					+ object.getName() + " is not visible in current scope");
		}

		// Then we check if the class defines a field of the name requested
		MJVariable v; 
		try {
			v = IR.classes.lookupField(c, this.field.getName());
		} catch (ClassErrorField e) {
			throw new TypeCheckerException(this.getClass().getSimpleName() + ": "
					+ object.getType().getName() + " does not supply a field named "+ field.getName());
		}
		
		this.type = v.getType(); 
		
		return this.type;
	}

	void variableInit(HashSet<MJVariable> initialized)
			throws TypeCheckerException {
	}

}
