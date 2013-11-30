package syntaxtree.statement;

import analysis.DefinitionSet;
import analysis.Interval;
import analysis.lattices.IntervalLattice;
import analysis.lattices.Lattice;
import analysis.lattices.RDLattice;
import analysis.Sign;
import analysis.SignSet;
import analysis.lattices.SignsLattice;
import flowgraph.datastructure.FlowSet;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;
import flowgraph.datastructure.VariableSet;
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
                .union(idx.getVariable());
    }

    /**
     * Transfer function used in the worklist algorithm. Routes the general
     * state to the respective specific analysis..
     *
     * @param lattice The input state.
     * @return The result of the specific analysis.
     */
    @Override
    public Lattice transferFunction(Lattice lattice, int toLabel) {
        if (lattice instanceof RDLattice) {
            return this.transferFunction((RDLattice) lattice, toLabel);
        }

        if (lattice instanceof SignsLattice) {
            return this.transferFunction((SignsLattice) lattice, toLabel);
        }


        throw new UnsupportedOperationException("Analysis not supported yet.");
    }

    private RDLattice transferFunction(RDLattice lattice, int toLabel) {
        ((RDLattice) lattice).kill(id).union(
                ((RDLattice) lattice).gen(id, this.toNode()));
        return lattice;
    }

    private SignsLattice transferFunction(SignsLattice lattice, int toLabel) {
        lattice.get(id).merge(SignSet.pnz);
        return lattice;
    }

    @Override
    public boolean hasPotentialUnderFlow(SignsLattice lattice) {
        return (this.idx.evalulate(lattice).contains(Sign.N));
    }

    @Override
    public boolean isOutOfBounds(IntervalLattice lattice) {
        Interval bounds = lattice.declarations.lookup(id).bounds(lattice);
        return !(this.idx.evalulate(lattice).subsetOf(bounds));
    }
}
