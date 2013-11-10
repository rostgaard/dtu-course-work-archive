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

    public Program(List<Declaration> decls, List<Statement> stmts) {
        this.decls = decls;
        this.stmts = stmts;
    }

    public List<Declaration> getDecls() {
        return decls;
    }

    public void setDecls(List<Declaration> decls) {
        this.decls = decls;
    }

    public List<Statement> getStmts() {
        return stmts;
    }

    public void setStmts(List<Statement> stmts) {
        this.stmts = stmts;
    }

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\nDeclerations:\n" + decls.toString() + "\nStatements:\n" + stmts.toString() + "\n";
    }
    
}
