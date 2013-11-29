package syntaxtree.statement;

import analysis.IntervalLattice;
import analysis.RDProgramState;
import analysis.SignsLattice;
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
    public RDProgramState RD(RDProgramState currentState) {
//    	currentState.addRDentry(getLabel(), currentState.getDefinitions());
//    	RDProgramState rps1 = new RDProgramState();    	
//    	for(Statement s : trueBranch){
//    		currentState.addRDentry(s.getLabel(), rps1.getDefinitions());
//    		s.RD(rps1);
//    		currentState.addRDexit(s.getLabel(), rps1.getDefinitions());
//    	}
//    	currentState.union(rps1);
//    	
//    	RDProgramState rps2 = new RDProgramState();
//    	for(Statement s : falseBranch){
//    		currentState.addRDentry(s.getLabel(), rps2.getDefinitions());
//    		s.RD(rps2);
//    		currentState.addRDexit(s.getLabel(), rps2.getDefinitions());
//    	}
//    	currentState.union(rps2);
//    	
//    	//killRD([if b then S1 else S2 fi]l) = ?
//    	//genRD([[if b then S1 else S2 fi]l) = ?
//    	currentState.addRDexit(getLabel(), currentState.getDefinitions());
        return currentState;
    }

    @Override
    public NodeSet labels() {
        return (new NodeSet())
                .addNode(new Node(this))
                .union(trueBranch.lables())
                .union(falseBranch.lables());
    }

    @Override
    public String toStringWithLabel() {
        String trueBuffer = "";
        String falseBuffer = "";
        for (Statement s : this.trueBranch) {
            trueBuffer += Symbols.INDENTION + s.toStringWithLabel() + Symbols.NEWLINE;
        }
        for (Statement s : this.falseBranch) {
            falseBuffer += Symbols.INDENTION + s.toStringWithLabel() + Symbols.NEWLINE;
        }
        return Symbols.IF + Symbols.SEPERATOR + Symbols.LSQPARAN + cond + Symbols.RSQPARAN + this.getLabel() + Symbols.SEPERATOR + Symbols.THEN + Symbols.NEWLINE
                + trueBuffer
                + Symbols.ELSE + Symbols.NEWLINE
                + falseBuffer
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
    public boolean hasPotentialUnderFlow(SignsLattice lattice) {
        return this.cond.hasPotentialUnderFlow(lattice);
    }
    
    @Override
    public boolean isOutOfBounds(IntervalLattice lattice) {
        return this.cond.isOutOfBounds(lattice);
    }
    
}
