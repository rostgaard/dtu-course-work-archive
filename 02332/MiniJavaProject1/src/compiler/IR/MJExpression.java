package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

public abstract class MJExpression extends IR  {

	protected MJType type;

	public MJType getType() {
		return type;
	}

	public void setType(MJType type) {
		this.type = type;
	}

	public abstract void prettyPrint(PrettyPrinter prepri);

}
