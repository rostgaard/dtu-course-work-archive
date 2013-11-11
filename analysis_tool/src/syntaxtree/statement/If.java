package syntaxtree.statement;

import analysis.RDProgramState;
import java.util.List;
import syntaxtree.Symbols;

import syntaxtree.condition.Condition;
import syntaxtree.statement.Statement;

/**
 * Data representation for if statements
 *
 */
public class If extends Statement {

    private Condition cond;
    private List<Statement> tBranch;
    private List<Statement> fBranch;

    public If(Condition cond, List<Statement> tBranch, List<Statement> fBranch) {
        this.cond = cond;
        this.tBranch = tBranch;
        this.fBranch = fBranch;
    }

    public Condition getCond() {
        return cond;
    }

    public void setCond(Condition cond) {
        this.cond = cond;
    }

    public List<Statement> gettBranch() {
        return tBranch;
    }

    public void settBranch(List<Statement> tBranch) {
        this.tBranch = tBranch;
    }

    public List<Statement> getfBranch() {
        return fBranch;
    }

    public void setfBranch(List<Statement> fBranch) {
        this.fBranch = fBranch;
    }

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\nCondition: " + cond.toString() + "\nTrue branch: " + tBranch.toString() + "\nFalse branch: " + fBranch.toString() + "\n";
    }

    @Override
    public RDProgramState RD(RDProgramState currentState) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
