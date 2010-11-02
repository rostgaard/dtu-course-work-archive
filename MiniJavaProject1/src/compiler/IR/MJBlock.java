package compiler.IR;

import java.util.HashSet;
import java.util.LinkedList;

import compiler.PrettyPrinter;
import compiler.Exceptions.TypeCheckerException;

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
			v.prettyPrint(prepri);
			prepri.println(";");
		}

		if (this.variables.size()>0)
			prepri.println("");
		
		for (MJStatement s : this.statements) {
			s.prettyPrint(prepri);
		}
		
		prepri.out();
		prepri.println("}");		
	}

	MJType typeCheck() throws TypeCheckerException { return MJType.Tnone; } 

	
	void variableInit(HashSet<MJVariable> initialized) throws TypeCheckerException {}

}
