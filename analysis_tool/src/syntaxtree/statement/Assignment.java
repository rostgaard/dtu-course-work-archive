package syntaxtree.statement;

import analysis.DefinitionSet;
import analysis.Lattice;
import analysis.RDLattice;
import analysis.RDProgramState;
import analysis.SignsLattice;
import flowgraph.datastructure.FlowSet;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;
import flowgraph.datastructure.VariableSet;
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
    public RDProgramState RD(RDProgramState currentState) {
        //RDentry
        DefinitionSet exit = currentState.getRDExit(getLabel() - 1);
        currentState.addRDentry(getLabel(), exit);

        //RDexit
        DefinitionSet entry = currentState.getRDEntry(getLabel());
        //killRD([x:= a]l) = {(x, ?)} u  {(x, l') | B(l') is an assignment to x}    	
        entry.removeAll(currentState.kill(id, entry));
        //genRD([x:= a]l) = {(x, l)}
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
    public Node init() {
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

    @Override
    public Lattice transferFunction(Lattice lattice) {
        if (lattice instanceof RDLattice) {
            return this.transferFunction((RDLattice) lattice);
        }

        if (lattice instanceof SignsLattice) {
            return this.transferFunction((SignsLattice) lattice);
        }


        throw new UnsupportedOperationException("Analysis not supported yet.");
    }

    private RDLattice transferFunction(RDLattice lattice) {
        lattice.kill(id).union(
                ((RDLattice) lattice).gen(id, this.toNode()));
        return lattice;

    }

    private SignsLattice transferFunction(SignsLattice lattice) {
        lattice.get(id).clear(); // Kill all previous definitions.
        lattice.get(id).merge(this.expr.evalulate(lattice));
        return lattice;
    }
}
