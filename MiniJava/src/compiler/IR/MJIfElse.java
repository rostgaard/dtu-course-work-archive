package compiler.IR;

import compiler.PrettyPrinter;

public class MJIfElse extends MJStatement {
	
	MJExpression Condition;
	MJBlock ifTrueBlock,ifFalseBlock;
	
	
	public MJIfElse(MJExpression condition, MJBlock ifTrueBlock) {
		super();
		Condition = condition;
		this.ifTrueBlock = ifTrueBlock;
	}	
	
	public MJIfElse(MJExpression condition, MJBlock ifTrueBlock,
			MJBlock ifFalseBlock) {
		super();
		Condition = condition;
		this.ifTrueBlock = ifTrueBlock;
		this.ifFalseBlock = ifFalseBlock;
	}



	public MJBlock getIfTrueBlock() {
		return ifTrueBlock;
	}



	public void setIfTrueBlock(MJBlock ifTrueBlock) {
		this.ifTrueBlock = ifTrueBlock;
	}



	public MJBlock getIfFalseBlock() {
		return ifFalseBlock;
	}



	public void setIfFalseBlock(MJBlock ifFalseBlock) {
		this.ifFalseBlock = ifFalseBlock;
	}



	public MJExpression getCondition() {
		return Condition;
	}



	public void setCondition(MJExpression condition) {
		Condition = condition;
	}



	public void prettyPrint(PrettyPrinter prepri) {
		prepri.print("if (");
		prepri.print(Condition.toString() +")");

		ifTrueBlock.prettyPrint();

		prepri.print("else");
		ifFalseBlock.prettyPrint();
	}

}
