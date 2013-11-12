package syntaxtree.statement;

import analysis.RDProgramState;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;
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

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\nCondition: " + cond.toString() + "\nBody: " + body.toString() + "\n";
    }

    @Override
    public String toString() {
        return Symbols.WHILE + 
                Symbols.LPARAN + cond + Symbols.RPARAN + Symbols.SEPERATOR + 
                Symbols.DO 
                + body + 
                Symbols.OD;
    }

    @Override
    public RDProgramState RD(RDProgramState currentState) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NodeSet labels() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node init() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NodeSet finalNodes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
