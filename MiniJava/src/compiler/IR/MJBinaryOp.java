package compiler.IR;

import java.util.LinkedList;

import compiler.PrettyPrinter;

public abstract class MJBinaryOp extends MJExpression {
	private MJExpression lhs;
	private LinkedList<MJExpression> rhslist;

	public MJExpression getLhs() {
		return lhs;
	}

	public void setLhs(MJExpression lhs) {
		this.lhs = lhs;
	}

	public LinkedList<MJExpression> getRhsList() {
		return rhslist;
	}

	public void setRhsList(LinkedList<MJExpression> rhslist) {
		this.rhslist = rhslist;
	}
	
}
