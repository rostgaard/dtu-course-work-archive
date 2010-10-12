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
	
	public MJEqual(MJExpression lhs, LinkedList<MJExpression> rhslist) {
		this.setLhs(lhs);
		this.setRhsList(rhslist);
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print(this.toString());
	}

	public MJType typeCheck() throws TypeCheckerException {
		for(MJExpression e: this.getRhsList()) {
				if(this.getLhs().getType() != e.getType())
					throw new TypeCheckerException("Lhs and Rhs not of same type in expression " +
							this.toString());				
		}
		

		return MJType.Tvoid;
	}
	
	public String toString() {
		String retString = this.getLhs().toString();
		
			for(MJExpression e: this.getRhsList()) {
				retString += e.toString();
			}
		return retString;
	}
	 
}
