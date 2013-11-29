package syntaxtree.statement;

import analysis.DefinitionSet;
import analysis.Interval;
import analysis.IntervalLattice;
import analysis.Lattice;
import analysis.RDLattice;
import analysis.RDProgramState;
import analysis.Sign;
import analysis.SignsLattice;
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
        return id + "[" + idx + "] := " + expr + ";";
    }

    @Override
    public String toStringWithLabel() {
        return Symbols.LSQPARAN + this.toString() + Symbols.RSQPARAN
                + Symbols.SEPERATOR + this.getLabel();
    }

    @Override
    public RDProgramState RD(RDProgramState currentState) {
        //RDentry
        DefinitionSet exit = currentState.getRDExit(getLabel() - 1);
        currentState.addRDentry(getLabel(), exit);

        //RDexit
        DefinitionSet entry = currentState.getRDEntry(getLabel());
        //killRD(read A[a]) = {(A, l'}| b(l') is a declaration or an assignment to A[]}    	
        entry.removeAll(currentState.kill(id, entry));
        //genRD(read A[a]) = {(A[a], l)}
        entry.addAll(currentState.gen(id, new Node(this)));

        currentState.addRDexit(getLabel(), entry);
        return currentState;
    }

    @Override
    public DefinitionSet killed(RDProgramState currentState) {
        DefinitionSet entry = currentState.getRDEntry(getLabel());
        return currentState.kill(id, entry);
    }

    @Override
    public DefinitionSet generated(RDProgramState currentState) {
        return currentState.gen(id, new Node(this));
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
        //TODO Add check of expressions.
        Interval bounds = lattice.declarations.lookup(id).bounds(lattice);
        return !(this.idx.evalulate(lattice).subsetOf(bounds));
    }
}
