package syntaxtree.statement;

import analysis.RDProgramState;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;
import java.util.List;
import syntaxtree.Symbols;

import syntaxtree.condition.Condition;
import utilities.Sequencer;

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
        String buffer = "";
        for (Statement s : body) {
            buffer += Symbols.INDENTION + s + Symbols.NEWLINE;
        }
        return Symbols.WHILE + Symbols.SEPERATOR + cond + Symbols.SEPERATOR
                + Symbols.DO
                + Symbols.NEWLINE
                + buffer
                + Symbols.OD;
    }

    @Override
    public String toStringWithLabel() {
        String buffer = "";
        for (Statement s : body) {
            buffer += Symbols.INDENTION + s.toStringWithLabel() + Symbols.NEWLINE;
        }
        return Symbols.WHILE + Symbols.SEPERATOR +  
                Symbols.LSQPARAN + cond + Symbols.RSQPARAN 
                + Symbols.SEPERATOR 
                + this.getLabel()
                + Symbols.SEPERATOR 
                + Symbols.DO
                + Symbols.NEWLINE
                + buffer
                + this.labels()
                + Symbols.OD;
    }

    @Override
    public void setLabel(Sequencer seq) {

        // Initially set my own label.
        super.setLabel(seq);

        // Recurse into every statement.
        for (Statement s : this.body) {
            s.setLabel(seq);
        }
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
