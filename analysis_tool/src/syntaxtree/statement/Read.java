package syntaxtree.statement;

import analysis.Interval;
import analysis.lattices.IntervalLattice;
import analysis.lattices.Lattice;
import analysis.lattices.RDLattice;
import analysis.SignSet;
import analysis.lattices.SignsLattice;
import flowgraph.datastructure.FlowSet;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;
import flowgraph.datastructure.VariableSet;
import syntaxtree.Symbols;
import syntaxtree.expression.Variable;

/**
 * Data representation for read statements (variables only)
 *
 */
public class Read extends Statement {

    private Variable id;

    public Read(Variable id) {
        this.id = id;
    }

    public Variable getId() {
        return id;
    }

    public void setId(Variable id) {
        this.id = id;
    }

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\nIdentifier: " + id.toString() + "\n";
    }

    @Override
    public String toString() {
        return Symbols.READ + Symbols.SEPERATOR + id;
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
    	return VariableSet.factory().addVariable(id);
    }

    /**
     * Transfer function used in the worklist algorithm. Routes the general
     * state to the respective specific analysis..
     *
     * @param lattice The input state.
     * @return The result of the specific analysis.
     */
    @Override
    public Lattice transferFunction(Lattice lattice) {
        if (lattice instanceof RDLattice) {
            return this.transferFunction((RDLattice) lattice);
        }

        else if (lattice instanceof SignsLattice) {
            return this.transferFunction((SignsLattice) lattice);
        }
        else if (lattice instanceof IntervalLattice) {
            return this.transferFunction((IntervalLattice) lattice);
        }

        throw new UnsupportedOperationException("Analysis not supported yet.");
    }

    private RDLattice transferFunction(RDLattice lattice) {
        ((RDLattice) lattice).kill(id).union(
                ((RDLattice) lattice).gen(id, this.toNode()));
        return lattice;
    }
    
    private IntervalLattice transferFunction(IntervalLattice lattice) {
        lattice.get(id).set(new Interval(lattice).setAbsoluteInterval(lattice.Minimum, lattice.Maximum));
        return lattice;
    }
    private SignsLattice transferFunction(SignsLattice lattice) {
        lattice.get(id).merge(SignSet.pnz);
        return lattice;
    }

    @Override
    public boolean hasPotentialUnderFlow(SignsLattice lattice) {
        return false;
    }
    
    @Override
    public boolean isOutOfBounds(IntervalLattice lattice) {
        return false;
    }
    
}
