package syntaxtree.statement;

import analysis.RDProgramState;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;
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
    
    @Override
    public NodeSet labels() {
        NodeSet returnSet = new NodeSet();
        returnSet.add(new Node(this));
        
        return returnSet;
    }

    @Override
    public Node init() {
        return new Node(this);
    }

    @Override
    public NodeSet finalNodes() {
        NodeSet returnSet = new NodeSet();
        returnSet.add(new Node(this));
        
        return returnSet;
    }
    
}
