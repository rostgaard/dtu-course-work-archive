package syntaxtree.statement;

import analysis.SignSet;
import analysis.lattices.IntervalLattice;
import analysis.lattices.Lattice;
import analysis.lattices.RDLattice;
import analysis.lattices.SignsLattice;
import flowgraph.datastructure.FlowSet;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;
import flowgraph.datastructure.VariableSet;
import syntaxtree.expression.Constant;
import syntaxtree.expression.Variable;
import syntaxtree.expression.Expression;

/**
 * Data representation for assignments (variables only)
 *
 */
public class Assignment extends Statement {

    private Variable id;
    private Expression expr;

    public Assignment(Variable id, Expression expr) {
        this.id = id;
        this.expr = expr;
    }

    public Variable getId() {
        return id;
    }

    public void setId(Variable id) {
        this.id = id;
    }

    public Expression getExpr() {
        return expr;
    }

    public void setExpr(Expression expr) {
        this.expr = expr;
    }

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\nIdentifier: " + id.toString() + "\nExpression: " + expr.toString() + "\n";
    }

    @Override
    public String toString() {
        return id + " := " + expr;
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
        return VariableSet.factory()
                .union(expr.getVariable());
    }

    /**
     * Transfer function used in the worklist algorithm. Routes the general
     * state to the respective specific analysis..
     *
     * @param lattice The input state.
     * @return The result of the specific analysis.
     */
    @Override
    public Lattice transferFunction(Lattice lattice,int toLabel) {
        if (lattice instanceof RDLattice) {
            return this.transferFunction((RDLattice) lattice, toLabel);
        }

        if (lattice instanceof SignsLattice) {
            return this.transferFunction((SignsLattice) lattice, toLabel);
        }

        if (lattice instanceof IntervalLattice) {
            return this.transferFunction((IntervalLattice) lattice, toLabel);
        }

        throw new UnsupportedOperationException("Analysis not supported yet.");
    }

    /**
     * Transfer function for Reaching Definitions analysis. An assignment to 
     * merely kills every previous definitions of the identifier, and
     * generates a new definition - this.
     *
     * @param lattice The RD entry state of an Assignment
     * @return The RD exit state of state of an Assignment
     */
    private RDLattice transferFunction(RDLattice lattice) {
        lattice.kill(id).union(
                ((RDLattice) lattice).gen(id, this.toNode()));
        return lattice;

    }

    private IntervalLattice transferFunction(IntervalLattice lattice) {
        lattice.get(id).set(this.expr.evalulate(lattice));
        return lattice;
    }

    private SignsLattice transferFunction(SignsLattice lattice, int toLabel) {
        if (expr instanceof Constant) {
            lattice.get(id).clear(); // Kill all previous definitions.
        }
        SignSet signs = this.expr.evalulate(lattice);
        lattice.get(id).merge(signs);
        return lattice;
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
