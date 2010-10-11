package compiler.IR;
/**
 * The equality comparison operator “equal” ==. The operands must have the same type
 *  (integer or boolean).

 */

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;
import compiler.Exceptions.VariableAlreadyDeclared;

public class MJEqual extends MJBinaryOp {

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print(this.toString());
	}

	public MJType typeCheck() throws TypeCheckerException {
		
		if(this.getLhs().getType() != this.getRhs().getType())
			throw new TypeCheckerException("Lhs and Rhs not of same type in expression " +
					this);
		return MJType.Tvoid;
	}
	
	public String toString() {
		return this.getLhs().toString() +" == "+this.getRhs().toString();
	}
	
}
