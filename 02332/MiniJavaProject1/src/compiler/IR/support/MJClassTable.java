package compiler.IR.support;

import java.util.HashMap;
import java.util.LinkedList;

import compiler.Exceptions.*;
import compiler.IR.IR;
import compiler.IR.MJClass;
import compiler.IR.MJExpression;
import compiler.IR.MJMethod;
import compiler.IR.MJType;
import compiler.IR.MJVariable;

public class MJClassTable {
	private HashMap<String, MJClass> map = new HashMap<String, MJClass>();
	private HashMap<MJClass, MJSymbolTable> fieldmap =
		new HashMap<MJClass, MJSymbolTable>();
	private HashMap<MJClass, MJMethodTable> methodMap = new HashMap<MJClass, MJMethodTable>();

	public MJClass lookup(String name) throws ClassNotFound {
		MJClass decl = (MJClass)map.get(name);
		if (decl==null) {
			throw new ClassNotFound(name);
		}
		return decl;
	}

	public void add(MJClass decl) throws ClassAlreadyDeclared, ClassErrorField {
		
		MJClass existingdecl = (MJClass)map.get(decl.getName());
		if (existingdecl!=null) {
			throw new ClassAlreadyDeclared(decl.getName());
		}
		map.put(decl.getName(), decl);

		MJSymbolTable newmap = new MJSymbolTable ();
		fieldmap.put(decl, newmap);
		for (MJVariable var : decl.getFieldList()) {
			try {
				newmap.add(var);
			} catch (VariableAlreadyDeclared e) {
				throw new ClassErrorField(e.getMessage());
			}
		}
	}
	
	public MJVariable lookupField(MJClass decl, String name) throws ClassErrorField {
/*
		if(decl == null) 
			System.out.println("decl is null");
		else
			System.out.println("decl is not null");
		*/
		MJSymbolTable map = fieldmap.get(decl);
		MJClass superclass = null;
		MJVariable v = null;
		

		
		if(map == null) {
			//System.out.println("map is null, no use in looking up a field");
			throw new ClassErrorField("Field "+name+" not found in class "+decl.getName()+".");
		} else {
		try {
			v = map.lookup(name);
			return v;
		} catch (VariableNotFound e) {
			if (decl.getName().equals("Object")) {
				throw new ClassErrorField("Field "+name+" not found in class "+decl.getName()+".");
			} 		
		}

		try {
			superclass = IR.classes.lookup(decl.getSuperClass().getName());
		} catch (ClassNotFound e) {
			// this should not happen
		}
		v = lookupField(superclass, name);
		}
		return v;
	}

	// when adding methods we need to make sure that none of the super classes
	// implements a method with the same name or types either 
	
	public void addMethods(MJClass decl) throws ClassErrorMethod, ClassNotFound, InheritanceError {

		MJMethodTable newMethodMap = new MJMethodTable();
		methodMap.put(decl, newMethodMap);
		
		for (MJMethod meth : decl.getMethodList()) {
				try {
					checkMethod(decl, meth);
					newMethodMap.add(meth);
				} catch (ClassErrorMethod e) {
					throw new ClassErrorMethod(e.getMessage());
				}
		}
		
	}
	
	public MJMethod lookupMethod(MJClass decl, String name, LinkedList<MJExpression> arglist) throws ClassErrorMethod, MethodNotFound {
		
		MJMethod meth = null;
		
		while (true) {

			MJMethodTable methods = methodMap.get(decl);

			try {
				meth = methods.lookup(name, arglist);
			} catch (MethodNotFound e) {
				// do nothing
			}
			
			if (meth != null)
				break;
			
			if (decl.getName().equals("Object")) {
				break;
			}

			MJType superClass = decl.getSuperClass();
			MJClass superdecl = null;
			try {
				superdecl = this.lookup(superClass.getName());
			} catch (ClassNotFound e) {
				// this should not happen - should have been caught before
			}
			
			decl = superdecl; 
		}
		
		if (meth == null)
			throw new MethodNotFound(name);
		
		return meth;
	}

	private void checkMethod(MJClass decl, MJMethod meth) throws ClassNotFound, InheritanceError {
		
		MJType superClass = decl.getSuperClass();
		
		if (superClass.getName().equals("Object")) {
			return;
		}
		
		MJClass superdecl = this.lookup(superClass.getName());

		MJMethodTable methods = methodMap.get(superdecl);
		try {
			methods.check(meth);
		} catch (ClassErrorMethod e) {
			throw new InheritanceError(e.getMessage());
		}
		
		checkMethod(superdecl, meth);
	}
	
}
