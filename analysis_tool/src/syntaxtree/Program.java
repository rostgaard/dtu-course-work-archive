package syntaxtree;

import analysis.Lattice;
import analysis.LatticeSet;
import analysis.RDLattice;
import analysis.Worklist;
import flowgraph.datastructure.Flow;
import flowgraph.datastructure.FlowSet;
import flowgraph.datastructure.Node;
import java.util.List;

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
              for(Variable variable : declaration.getVariable()) {
                  variableSet.add(variable);
              }
        }

        return variableSet;
    }

    public LatticeSet calculate(Lattice analysisSpace) {
        Worklist worklist     = new Worklist();
        LatticeSet analysis   = new LatticeSet();
        FlowSet S             = this.getStmts().flow();

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
                    .transferFunction(analysis.get(flow.getSource()));
            Lattice Lprime = analysis.get(flow.getTarget());

            if (!L.subsetOf(Lprime)) {
                Lprime.union(L);

                // Push more work to the worklist.
                FlowSet newFlows = S.flows(destinationNode);
                for (Flow f : newFlows) {
                    worklist.add(f);
                }
            }
        }
        
        return analysis;
    }
    
}
