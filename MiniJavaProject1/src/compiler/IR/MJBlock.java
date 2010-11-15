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

	public LinkedList<MJVariable> getVariables() {
		return this.variables;
	}

	public LinkedList<MJStatement> getStatements() {
		return this.statements;
	}

	public void prettyPrint(PrettyPrinter prepri) {
		prepri.println("{");
		prepri.in();

		for (MJVariable v : this.variables) {
			v.prettyPrint(prepri);
			prepri.println(";");
		}

		if (this.variables.size() > 0)
			prepri.println("");

		for (MJStatement s : this.statements) {
			s.prettyPrint(prepri);
		}

		prepri.out();
		prepri.println("}");
	}

	/*
	 * A block type checks if the variable declaration list and the statement
	 * list type checks.
	 */
	MJType typeCheck() throws TypeCheckerException {
		//IR.stack.enterScope();
		for (MJVariable v : this.variables) {
			if(compiler.config.DEBUG)
				System.out.println(this.getClass().getSimpleName()+": Found variable "+ v.getName());
			try {
				IR.stack.add(v);
			} catch (VariableAlreadyDeclared e) {
				throw new TypeCheckerException(v.getName()
						+ " already declared in scope");
			}
		}

		if (compiler.config.DEBUG)
			System.out.println(this.getClass().getSimpleName()+": Typechecking " + this.statements.size()
					+ " statements");
		for (MJStatement stmt : this.statements) {
			if (compiler.config.DEBUG)
				System.out.println(this.getClass().getSimpleName()+": Typechecking "+ stmt);
			stmt.typeCheck();
		}
		//IR.stack.leaveScope();
		return MJType.Tnone;
	}

	void variableInit(HashSet<MJVariable> initialized)
			throws TypeCheckerException {
	}

}
