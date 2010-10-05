package compiler.IR;

import java.util.HashSet;
import java.util.LinkedList;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;
import compiler.Exceptions.VariableAlreadyDeclared;

public class MJBlock extends MJStatement {

	private LinkedList<MJVariable> variables = new LinkedList<MJVariable>();
	private LinkedList<MJStatement> statements = new LinkedList<MJStatement>();

	public MJBlock(LinkedList<MJVariable> vdl, LinkedList<MJStatement> sdl) {
		this.variables = vdl;
		this.statements = sdl;
	}

	public LinkedList<MJVariable>  getVariables() {
		return this.variables;
	}

	public LinkedList<MJStatement>  getStatements() {
		return this.statements;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.println("{");
		prepri.in();
		for (MJVariable v : this.variables) {
			prepri.println(v.getType()+" "+v.getName()+";");
		}
		prepri.println("");
		
		for (MJStatement s : this.statements) {
			s.prettyPrint(prepri);
		}
		prepri.out();
		prepri.println("}");		
	}

	public MJType typeCheck() throws TypeCheckerException {

		for (MJVariable v : this.getVariables()) {
			v.typeCheck();
		}
		
		IR.stack.enterScope();
		
		for (MJVariable v : this.getVariables()) {
			try {
				IR.stack.add(v);
			} catch (VariableAlreadyDeclared e1) {
				throw new TypeCheckerException("Variable " + v.getName() + " already declared");
			}
		}
		
		for (MJStatement s : this.getStatements()) {
			s.typeCheck();
		}
		
		IR.stack.leaveScope();

		return MJType.Tnone;
	}
	
	public void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {

		IR.stack.enterScope();
		
		for (MJVariable v : this.getVariables()) {
				v.variableInit(initialized);
		}
		
		for (MJStatement s : this.getStatements()) {
			s.variableInit(initialized);
		}
		
		IR.stack.leaveScope();

	}
}
