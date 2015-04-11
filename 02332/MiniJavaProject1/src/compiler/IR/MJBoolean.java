package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public class MJBoolean extends MJExpression {

	private enum MJBooleanValues {
		True, False
	};

	private MJBooleanValues value;

	public static MJBoolean False = new MJBoolean(MJBooleanValues.False);
	public static MJBoolean True = new MJBoolean(MJBooleanValues.True);

	public MJBoolean(MJBooleanValues value) {
		this.value = value;
	}

	public boolean isTrue() {
		return (this.value == MJBooleanValues.True);
	}

	public boolean isFalse() {
		return (this.value == MJBooleanValues.False);
	}

	public void prettyPrint(PrettyPrinter prepri) {
		if (this.isFalse()) {
			prepri.print("false");
		} else {
			prepri.print("true");
		}
	}
	
	public String toString() {
		if (this.isFalse()) {
			return "false";
		} else {
			return "true";
		}
	}

	/*
	 * true and false type check and have the type boolean.
	 * @return MJType.Tboolean
	 */
	MJType typeCheck() throws TypeCheckerException {
		if (this.value == MJBooleanValues.True
				|| this.value == MJBooleanValues.False)
			this.type = MJType.Tboolean;
		else
			throw new TypeCheckerException(this.getClass().getName()
					+ ": Invalid boolean expression");
		return this.type;
	}

	void variableInit(HashSet<MJVariable> initialized)
			throws TypeCheckerException {
	}

}
