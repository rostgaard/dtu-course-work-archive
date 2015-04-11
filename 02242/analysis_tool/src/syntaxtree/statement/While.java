package syntaxtree.statement;

import analysis.lattices.IntervalLattice;
import analysis.lattices.Lattice;
import analysis.lattices.RDLattice;
import analysis.lattices.SignsLattice;
import flowgraph.datastructure.Flow;
import flowgraph.datastructure.FlowSet;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;
import flowgraph.datastructure.VariableSet;
import syntaxtree.StatementList;
import syntaxtree.Symbols;
import syntaxtree.condition.Condition;
import utilities.Sequencer;

/**
 * Data representation for while statements
 *
 */
public class While extends Statement {

    private Condition cond;
    private StatementList body;

    public While(Condition cond, StatementList body) {
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
    public String toStringWithLabel(int indention) {
        return Symbols.WHILE + Symbols.SEPERATOR
                + Symbols.LSQPARAN + cond + Symbols.RSQPARAN
                + this.getLabel()
                + Symbols.SEPERATOR
                + Symbols.DO
                + Symbols.NEWLINE
                + this.body.toStringWithLabel(indention + 1)
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
    public NodeSet labels() {
        return NodeSet.factory()
                .addNode(new Node(this))
                .union(this.body.lables());
    }

    @Override
    public Node initial() {
        return this.toNode();
    }

    @Override
    public NodeSet finalNodes() {
        return NodeSet.factory().addNode(new Node(this));
    }

    @Override
    public FlowSet flow() {
        FlowSet retSet = FlowSet.factory()
                .addFlow(new Flow(this.toNode(), this.body.init()))
                .union(this.body.flow());

        for (Node n : this.body.finalLabels()) {
            retSet.addFlow(new Flow(n, this.toNode()));
        }

        return retSet;
    }

    @Override
    public VariableSet getVariable() {
        return VariableSet.emptySet;
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
//        lattice.kill(id).union(
//                ((RDLattice) lattice).gen(id, this.toNode()));
        return lattice;

    }

    private IntervalLattice transferFunction(IntervalLattice lattice, int toLabel) {
//        System.out.println("IntervalLattice transferFunction:" + this.expr.evalulate(lattice));
//        lattice.get(id).set(this.expr.evalulate(lattice));
        return lattice;
    }

    private SignsLattice transferFunction(SignsLattice lattice, int toLabel) {
        SignsLattice signsLattice = lattice;

        Boolean bodyBranch = body.init().getLabel() == toLabel;

        if (bodyBranch) {
            cond.evaluate(lattice, bodyBranch);
            return signsLattice;
        }
        
        return lattice;
    }

    @Override
    public boolean hasPotentialUnderFlow(SignsLattice lattice) {
        return this.cond.hasPotentialUnderFlow(lattice);
    }

    @Override
    public boolean isOutOfBounds(IntervalLattice lattice) {
        return this.cond.isOutOfBounds(lattice);
    }
}
