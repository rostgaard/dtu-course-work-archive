package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;
import compiler.Exceptions.VariableNotFound;

public class MJAssign extends MJStatement {

	private MJExpression rhs;
	private MJIdentifier lhs;

	public MJAssign(MJIdentifier lhs, MJExpression rhs) {
		this.rhs = rhs;
		this.lhs = lhs;
	}

	public MJIdentifier getLhs() {
		return this.lhs;
	}

	public MJExpression getRhs() {
		return this.rhs;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		this.lhs.prettyPrint(prepri);
		prepri.print(" = ");
		this.getRhs().prettyPrint(prepri);
		prepri.println(";");
	}

	public MJType typeCheck() throws TypeCheckerException {
		
		MJType rhs = this.getRhs().typeCheck();

		MJIdentifier lhs = this.getLhs();
		MJType lhstype;
		
		// lhs can be an Identifier
		
		lhstype = lhs.typeCheck();

		if (!rhs.isSame(lhstype)) {
			throw new TypeCheckerException("Incompatible types in assignment");
		}

		return MJType.Tvoid;
	}
	
	public void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {

		// check whether all variables on the rhs are initialized
		
		this.getRhs().variableInit(initialized);

		MJIdentifier i = this.getLhs();

		MJVariable v=null;
		
		try {
			v = IR.stack.find(i.getName());
		} catch (VariableNotFound e1) {
			// this should not happen
		}

		initialized.add(v);

	}
}
