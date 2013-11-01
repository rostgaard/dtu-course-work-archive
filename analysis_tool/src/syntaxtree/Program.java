package syntaxtree;

import java.util.List;

import syntaxtree.declaration.Declaration;
import syntaxtree.statement.Statement;

/**
 * Data representation for While-language programs
 *
 */
public class Program {

	private List<Declaration> decls;
	private List<Statement> stmts;
	
	public Program(List<Declaration> decls, List<Statement> stmts){
		this.decls = decls;
		this.stmts = stmts;
	}	
}
