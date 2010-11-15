package compiler.IR;

import java.util.HashSet;

import compiler.Exceptions.ClassNotFound;
import compiler.Exceptions.TypeCheckerException;

public final class MJType extends IR {

	private enum TypeEnum {
		INT, BOOLEAN, NONE, CLASS, ARRAY
	}

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
		this.name = t.getName() + "[]";
		this.baseType = t;
	}

	// create some static object to be used for standard types
	public static MJType Tint = new MJType(TypeEnum.INT, "int");
	public static MJType Tboolean = new MJType(TypeEnum.BOOLEAN, "boolean");
	public static MJType Tnone = new MJType(TypeEnum.NONE, "void");
	public static MJType TintArray = new MJType(TypeEnum.ARRAY, "int");
	public static MJType TString = new MJType(TypeEnum.CLASS, "String"); // Predefined
																			// string
																			// class

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

	public boolean isString() {
		return (this.type == TypeEnum.CLASS && this.name.equals("String"));
	}

	// local getter functions

	public TypeEnum getType() {
		return this.type;
	}

	public String getName() {
		return name;
	}

	public MJType getBaseType() {
		return baseType;
	}

	/**
	 * For a type to type check it must be a basic type, or a class type
	 * declared in the program, or the pre-defined class “String”.
	 */
	MJType typeCheck() throws TypeCheckerException {
		// Is it a primitive or predefined type?
		if (this.isArray() || this.isBoolean() || this.isInt()
				|| this.isString())
			return this;
		// If the type is not a primitive or predefined type, then we must look
		// it up in the class table
		else if (this.isClass()) {
			try {
				return new MJType(IR.classes.lookup(this.getName()).getName());
			} catch (ClassNotFound e) {
				throw new TypeCheckerException("Class not found");
			}
		}
		// This really should not happen
		else
			throw new TypeCheckerException(
					"Type is neither primitive nor class ? - odd");
	}

	void variableInit(HashSet<MJVariable> initialized)
			throws TypeCheckerException {
	}

}
