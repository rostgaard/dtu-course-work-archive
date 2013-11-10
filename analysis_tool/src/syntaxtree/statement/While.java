package syntaxtree.statement;

import analysis.RDProgramState;
import java.util.List;
import syntaxtree.Symbols;

import syntaxtree.condition.Condition;

/**
 * Data representation for while statements
 *
 */
public class While extends Statement {

    private Condition cond;
    private List<Statement> body;

    public While(Condition cond, List<Statement> body) {
        this.cond = cond;
        this.body = body;
    }

    public Condition getCond() {
        return cond;
    }

    public void setCond(Condition cond) {
        this.cond = cond;
    }

    public void setBody(List<Statement> body) {
        this.body = body;
    }

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\nCondition: " + cond.toString() + "\nBody: " + body.toString() + "\n";
    }

    @Override
    public String toString() {
        return Symbols.WHILE + Symbols.LPARAN + cond + Symbols.RPARAN + Symbols.SEPERATOR + Symbols.DO + body + Symbols.OD;
    }

    @Override
    public RDProgramState RD(RDProgramState currentState) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
