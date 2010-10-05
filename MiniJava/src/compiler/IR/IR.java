package compiler.IR;

import java.util.HashSet;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;
import compiler.IR.support.MJClassTable;
import compiler.IR.support.MJScopeStack;

public class IR {

	private MJProgram program;
	
	public static MJClassTable classes = new MJClassTable();
	public static MJScopeStack stack = new MJScopeStack();
	public static MJMethod currentMethod = null;
	public static MJClass currentClass = null;

	public IR() {
	}
	
	public IR(MJProgram program) {
		this.program = program;
	}

	public MJProgram getProgram() {
		return program;
	}

	public void prettyPrint() {
		this.program.prettyPrint(new PrettyPrinter());
	}
	
	public MJType typeCheck() throws TypeCheckerException {
		throw new TypeCheckerException("typeCheck not yet implemented for "+this.getClass().getName());
	}
	
	public void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {
		throw new TypeCheckerException("variableInit not yet implemented for "+this.getClass().getName());
	}
}
