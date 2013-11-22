package syntaxtree;

import java.util.List;

import flowgraph.datastructure.VariableSet;
import syntaxtree.declaration.Declaration;
import syntaxtree.expression.Variable;

/**
 * Data representation for While-language programs
 *
 */
public class Program {

    private DeclarationList decls;
    private StatementList stmts;

    public Program(DeclarationList decls, StatementList stmts) {
        this.decls = decls;
        this.stmts = stmts;
    }

    public List<Declaration> getDecls() {
        return decls;
    }

    public void setDecls(DeclarationList decls) {
        this.decls = decls;
    }

    public StatementList getStmts() {
        return stmts;
    }

    public void setStmts(StatementList stmts) {
        this.stmts = stmts;
    }

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\nDeclerations:\n" + decls.toString() + "\nStatements:\n" + stmts.toString() + "\n";
    }

    public VariableSet getVariables() {
        VariableSet variableSet = new VariableSet();
        for (Declaration declaration : decls) {
              for(Variable variable : declaration.getVariable()) {
                  variableSet.add(variable);
              }
        }

        return variableSet;
    }
    
}
