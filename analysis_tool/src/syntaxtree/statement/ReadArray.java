package syntaxtree.statement;

import analysis.RDProgramState;
import syntaxtree.expression.Expression;
import syntaxtree.expression.Variable;

/**
 * Data representation for read statements (arrays only)
 *
 */
public class ReadArray extends Statement {

    private Variable id;
    private Expression idx;

    public ReadArray(Variable id, Expression idx) {
        this.id = id;
        this.idx = idx;
    }

    public Variable getId() {
        return id;
    }

    public void setId(Variable id) {
        this.id = id;
    }

    public Expression getIdx() {
        return idx;
    }

    public void setIdx(Expression idx) {
        this.idx = idx;
    }

    @Override
    public String toString() {
        return "\nClass: " + getClass().getSimpleName() + "\nIdentifier: " + id.toString() + "\nIndex: " + idx.toString() + "\n";
    }

    @Override
    public RDProgramState RD(RDProgramState currentState) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
