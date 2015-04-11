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
import java.util.List;
import syntaxtree.StatementList;
import syntaxtree.Symbols;
import syntaxtree.condition.Condition;
import utilities.Sequencer;

/**
 * Data representation for if statements
 *
 */
public class If extends Statement {

    private Condition cond;
    private StatementList trueBranch;
    private StatementList falseBranch;

    private SignsLattice trueLattice;
    private SignsLattice falseLattice;

    public If(Condition cond, StatementList tBranch, StatementList fBranch) {
        this.cond = cond;
        this.trueBranch = tBranch;
        this.falseBranch = fBranch;
    }

    public Condition getCond() {
        return cond;
    }

    public void setCond(Condition cond) {
        this.cond = cond;
    }

    public List<Statement> gettBranch() {
        return trueBranch;
    }

    public void settBranch(StatementList tBranch) {
        this.trueBranch = tBranch;
    }

    public List<Statement> getfBranch() {
        return falseBranch;
    }

    public void setfBranch(StatementList fBranch) {
        this.falseBranch = fBranch;
    }

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\nCondition: " + cond.toString() + "\nTrue branch: " + trueBranch.toString() + "\nFalse branch: " + falseBranch.toString() + "\n";
    }

    @Override
    public String toString() {
        return Symbols.IF + Symbols.SEPERATOR + cond + Symbols.SEPERATOR + Symbols.THEN + Symbols.NEWLINE
                + Symbols.INDENTION + trueBranch + Symbols.NEWLINE
                + Symbols.ELSE + Symbols.NEWLINE
                + Symbols.INDENTION + falseBranch
                + Symbols.FI;
    }

    @Override
    public NodeSet labels() {
        return (new NodeSet())
                .addNode(new Node(this))
                .union(trueBranch.lables())
                .union(falseBranch.lables());
    }

    @Override
    public String toStringWithLabel(int indention) {
        return Symbols.IF + Symbols.SEPERATOR + Symbols.LSQPARAN + cond + Symbols.RSQPARAN + this.getLabel() + Symbols.SEPERATOR + Symbols.THEN + Symbols.NEWLINE
                + trueBranch.toStringWithLabel(indention+1)
                + Symbols.ELSE + Symbols.NEWLINE
                + falseBranch.toStringWithLabel(indention+1)
                + Symbols.FI;
    }

    @Override
    public Node initial() {
        return new Node(this);
    }

    @Override
    public void setLabel(Sequencer seq) {
        // Initially set my own label.
        super.setLabel(seq);

        for (Statement s : this.trueBranch) {
            s.setLabel(seq);
        }
        for (Statement s : this.falseBranch) {
            s.setLabel(seq);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public NodeSet finalNodes() {
        return NodeSet.factory()
                .union(trueBranch.finalLabels())
                .union(falseBranch.finalLabels());

    }

    @Override
    public FlowSet flow() {
        return FlowSet.factory()
                .union(this.trueBranch.flow())
                .union(this.falseBranch.flow())
                .addFlow(new Flow(this.toNode(), trueBranch.init()))
                .addFlow(new Flow(this.toNode(), falseBranch.init()));
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
        return lattice;

    }

    private IntervalLattice transferFunction(IntervalLattice lattice, int toLabel) {
        return lattice;
    }

    private SignsLattice transferFunction(SignsLattice lattice, int toLabel) {
        Boolean trueBranch = this.trueBranch.init().getLabel()==toLabel;
        SignsLattice signsLattice;

        if (trueBranch) {
            trueLattice = (SignsLattice) lattice.factory();
            trueLattice.putAll(lattice);
            signsLattice = trueLattice;
        } else {
            falseLattice = (SignsLattice) lattice.factory();
            falseLattice.putAll(lattice);
            signsLattice = falseLattice;

        }

        cond.evaluate(signsLattice, trueBranch);

        return signsLattice;
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
