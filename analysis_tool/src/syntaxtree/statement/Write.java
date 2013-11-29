package syntaxtree.statement;

import analysis.lattices.IntervalLattice;
import analysis.lattices.SignsLattice;
import flowgraph.datastructure.FlowSet;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;
import flowgraph.datastructure.VariableSet;
import syntaxtree.Symbols;
import syntaxtree.expression.Expression;

/**
 * Data representation for write statements
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
    public NodeSet labels() {
        return NodeSet.factory().addNode(new Node(this));
    }

    @Override
    public Node initial() {
        return (new Node(this));
    }

    @Override
    public NodeSet finalNodes() {
        return NodeSet.factory().addNode(new Node(this));
    }

    @Override
    public FlowSet flow() {
        return FlowSet.emptySet;
    }

    @Override
    public VariableSet getVariable() {
        return expr.getVariable();
    }

    @Override
    public boolean hasPotentialUnderFlow(SignsLattice lattice) {
        return this.expr.hasPotentialUnderFlow(lattice);
    }

    @Override
    public boolean isOutOfBounds(IntervalLattice lattice) {
        return this.expr.isOutOfBounds(lattice);
    }
}
