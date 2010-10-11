package compiler.IR;

import compiler.Exceptions.ClassNotFound;
import compiler.Exceptions.TypeCheckerException;

public final class MJType extends IR {
	
	private enum TypeEnum {	INT, BOOLEAN, NONE, CLASS, ARRAY};
	private TypeEnum type;
	private String name;
	private MJType baseType;
	
	public MJType(TypeEnum type) {
		this.type = type;
		this.name = type.toString();
	}
	
	public MJType(TypeEnum type, String tname) {
		this.type = type;
		this.name = tname;
	}
	
	public MJType(String name) {
		this(TypeEnum.CLASS);
		this.name = name;
	}
	
	public MJType(MJType t) {
		this(TypeEnum.ARRAY);
		this.name = t.getName()+"[]";
		this.baseType = t;
	}
	
	// create some static object to be used for standard types
	public static MJType Tint = new MJType(TypeEnum.INT, "int");
	public static MJType Tboolean = new MJType(TypeEnum.BOOLEAN, "boolean");
	public static MJType Tvoid = new MJType(TypeEnum.NONE, "void");
	public static MJType TintArray = new MJType(TypeEnum.ARRAY, "int");

	// check whether two types are the same
	
	public boolean isSame(MJType t) {
		
		if (t.getType() == this.getType()) {
			if (t.getType() == TypeEnum.CLASS) {
				if (t.getName().equals(this.getName())) {
					return true;
				}
				return false;
			}
			if (t.getType() == TypeEnum.ARRAY) { 
				if (this.getBaseType().isSame(t.getBaseType())) {
					return true;
				}
				return false;
			}
			return true;
		}
		return false;
	}
	
	// convert type to string
	
	public String toString() {
		return name;
	}

	// check for a certain type
	
	public boolean isInt() {
		return (this.type == TypeEnum.INT);
	}

	public boolean isBoolean() {
		return (this.type == TypeEnum.BOOLEAN);
	}
	
	public boolean isVoid() {
		return (this.type == TypeEnum.NONE);
	}
	
	public boolean isClass() {
		return (this.type == TypeEnum.CLASS);
	}
	
	public boolean isArray() {
		return (this.type == TypeEnum.ARRAY);
	}
	
	// local getter functions
	
	public TypeEnum getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public MJType getBaseType() {
		return baseType;
	}
	
	public MJType typeCheck() throws TypeCheckerException {

		if (this.isClass()) {
			try {
				IR.classes.lookup(this.getName());
			} catch (ClassNotFound ex) {
				throw new TypeCheckerException("Class "+this.getName()+" not found.");
			}
		}
		
		return MJType.Tvoid;

	}
}
