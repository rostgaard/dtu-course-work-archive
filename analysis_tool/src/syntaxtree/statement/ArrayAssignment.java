package syntaxtree.statement;

import analysis.DefinitionSet;
import analysis.Interval;
import analysis.lattices.IntervalLattice;
import analysis.lattices.Lattice;
import analysis.lattices.RDLattice;
import analysis.Sign;
import analysis.lattices.SignsLattice;
import flowgraph.datastructure.FlowSet;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;
import flowgraph.datastructure.VariableSet;
import syntaxtree.Symbols;
import syntaxtree.expression.Variable;
import syntaxtree.expression.Expression;

/**
 * Data representation for assignments (arrays only)
 *
 */
public class ArrayAssignment extends Statement {

    private Variable id;
    private Expression idx;
    private Expression expr;

    public ArrayAssignment(Variable id, Expression idx, Expression expr) {
        this.id = id;
        this.idx = idx;
        this.expr = expr;
    }

    public Variable getIdentifier() {
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

    public Expression getExpr() {
        return expr;
    }

    public void setExpr(Expression expr) {
        this.expr = expr;
    }

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\nIdentifier: " + id.toString() + "\nIndex: " + idx.toString() + "\nExpression: " + expr.toString() + "\n";
    }

    @Override
    public String toString() {
        return id + Symbols.LSQPARAN + idx + Symbols.RSQPARAN + " := " + expr;
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
        return VariableSet.factory().addVariable(id)
                .union(idx.getVariable())
                .union(expr.getVariable());
    }

    @Override
    public Lattice transferFunction(Lattice lattice, int toLabel) {
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

    private RDLattice transferFunction(RDLattice lattice, int toLabel) {
        ((RDLattice) lattice).kill(id).union(
                ((RDLattice) lattice).gen(id, this.toNode()));

        return lattice;
    }

    private SignsLattice transferFunction(SignsLattice lattice, int toLabel) {
        lattice.get(id).clear(); // Kill all previous definitions.
        lattice.get(id).merge(this.expr.evalulate(lattice));

        return lattice;
    }

    private IntervalLattice transferFunction(IntervalLattice lattice, int toLabel) {
        lattice.get(id).set(this.expr.evalulate(lattice));
        return lattice;
    }

    @Override
    public boolean hasPotentialUnderFlow(SignsLattice lattice) {
        return (this.idx.evalulate(lattice).contains(Sign.N)) || this.expr.hasPotentialUnderFlow(lattice);
    }

    @Override
    public boolean isOutOfBounds(IntervalLattice lattice) {
        Interval bounds = lattice.declarations.lookup(id).bounds(lattice);
        return !(this.idx.evalulate(lattice).subsetOf(bounds)) || this.expr.isOutOfBounds(lattice);
    }
}
