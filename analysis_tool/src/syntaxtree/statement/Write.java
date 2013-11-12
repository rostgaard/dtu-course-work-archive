package syntaxtree.statement;

import analysis.RDProgramState;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;
import syntaxtree.Symbols;
import syntaxtree.expression.Expression;

/**
 * Data representation for read statements
 *
 */
public class Write extends Statement {

    private Expression expr;

    public Write(Expression expr) {
        this.expr = expr;
    }

    public Expression getExpr() {
        return expr;
    }

    public void setExpr(Expression expr) {
        this.expr = expr;
    }

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\nExpression: " + expr.toString() + "\n";
    }

    @Override
    public String toString() {
        return Symbols.WRITE + Symbols.SEPERATOR + expr;
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
