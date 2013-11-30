package syntaxtree;

import analysis.lattices.Lattice;
import analysis.Analysis;
import analysis.lattices.IntervalLattice;
import analysis.lattices.SignsLattice;
import analysis.Worklist;
import flowgraph.datastructure.Flow;
import flowgraph.datastructure.FlowSet;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;

import flowgraph.datastructure.VariableSet;
import syntaxtree.declaration.Declaration;
import syntaxtree.expression.Variable;

/**
 * Data representation for While-language programs
 *
 */
public class Program {

    private DeclarationList decls;
    private StatementList stmts;

    public Program(DeclarationList decls, StatementList stmts) {
        this.decls = decls;
        this.stmts = stmts;
    }

    public DeclarationList getDecls() {
        return decls;
    }

    public void setDecls(DeclarationList decls) {
        this.decls = decls;
    }

    public StatementList getStmts() {
        return stmts;
    }

    public void setStmts(StatementList stmts) {
        this.stmts = stmts;
    }

    public String debugInformation() {
        return "\nClass: " + getClass().getSimpleName() + "\nDeclerations:\n" + decls.toString() + "\nStatements:\n" + stmts.toString() + "\n";
    }

    public VariableSet getVariables() {
        VariableSet variableSet = new VariableSet();
        for (Declaration declaration : decls) {
            for (Variable variable : declaration.getVariable()) {
                variableSet.add(variable);
            }
        }

        return variableSet;
    }

    public Analysis calculate(Lattice analysisSpace) {
        Worklist worklist = new Worklist();
        Analysis analysis = new Analysis();
        FlowSet S = this.getStmts().flow();

        for (Flow flow : this.getStmts().flow()) {
            worklist.add(flow);
        }

        for (Node node : this.getStmts().lables()) {
            if (node.equals(this.getStmts().init())) {
                analysis.put(node, analysisSpace.iota());
            } else {
                analysis.put(node, analysisSpace.factory());
            }
        }

        while (!worklist.isEmpty()) {
            Flow flow = worklist.removeFirst();
            Node sourceNode = flow.getSource();
            Node destinationNode = flow.getTarget();

            Lattice L = sourceNode.getStatement()
                    .transferFunction(analysis.get(sourceNode),destinationNode.getLabel());
            Lattice Lprime = analysis.get(destinationNode);

            if (!L.subsetOf(Lprime)) {
                Lprime.union(L);
                analysis.put(destinationNode, Lprime);

                // Push more work to the worklist.
                FlowSet newFlows = S.flows(destinationNode);
                for (Flow f : newFlows) {
                    worklist.add(f);
                }
            }
        }

        return analysis;
    }

    public NodeSet underFlowCheck() {
        NodeSet retval = new NodeSet();
        Analysis analysis = this.calculate(new SignsLattice(this.getDecls()));

        for (Node node : this.stmts.lables()) {
            SignsLattice sl = (SignsLattice) analysis.get(node);

            if (node.getStatement().hasPotentialUnderFlow(sl)) {
                retval.add(node);
            }
        }

        return retval;
    }

    public NodeSet rangeCheck() {
        NodeSet retval = new NodeSet();
        Analysis analysis = this.calculate(new IntervalLattice(this.getDecls()));

        for (Node node : this.stmts.lables()) {
            IntervalLattice il = (IntervalLattice) analysis.get(node);
            il.declarations = this.getDecls();
            if (node.getStatement().isOutOfBounds(il)) {
                retval.add(node);
            }
        }

        return retval;
    }
}
