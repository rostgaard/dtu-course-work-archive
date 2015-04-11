package compiler.IR;
/**
 * The equality comparison operator “equal” ==. The operands must have the same type
 *  (integer or boolean).

 */

import java.util.LinkedList;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;
import compiler.Exceptions.VariableAlreadyDeclared;

public class MJEqual extends MJBinaryOp {
	
	public void prettyPrint(PrettyPrinter prepri) {
		this.getLhs().prettyPrint(prepri);
		prepri.print("==");
		this.getRhs().prettyPrint(prepri);
	}

	public MJType typeCheck() throws TypeCheckerException {
				if(this.getLhs().getType() != this.getRhs().getType())
					throw new TypeCheckerException("Lhs and Rhs not of same type in expression ");				
			return MJType.Tvoid;
	}
 
}
