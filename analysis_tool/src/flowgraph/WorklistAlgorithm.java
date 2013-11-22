package flowgraph;

import analysis.DefinitionSet;
import analysis.RDProgramState;
import flowgraph.datastructure.Flow;
import flowgraph.datastructure.FlowSet;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;
import syntaxtree.Program;


import java.util.*;


public class WorklistAlgorithm {

    public static RDProgramState calculate(FlowSet flows, Program program) {
        NodeSet labels = program.getStmts().lables();

        LinkedList<Flow> worklist=new LinkedList<>(flows);
        RDProgramState analysis = new RDProgramState(program.getDecls());

        for (Node label : labels) {
            if (label.getLabel() != 1) {
                analysis.addRDentry(label.getLabel(), new DefinitionSet());
            }
        }

        while (!worklist.isEmpty()) {
            Flow flow = worklist.removeFirst();
            int label1 = flow.getSource().getLabel();
            int label2 = flow.getTarget().getLabel();

            DefinitionSet exitLabel1 = analysis.getRDEntry(label1);
            DefinitionSet killed = flow.getSource().getStatement().killed(analysis);

            DefinitionSet generated = flow.getSource().getStatement().generated(analysis);

            exitLabel1.killAll(killed);
            exitLabel1.addAll(generated);

            DefinitionSet entryLabel2 = analysis.getRDEntry(label2);
            if(!entryLabel2.containsAll(exitLabel1)) {
                entryLabel2.addAll(exitLabel1);
                analysis.addRDentry(label2,entryLabel2);

                FlowSet newFlows = flows.flows(label2);
                worklist.addAll(newFlows);
            }
        }

        return analysis;
    }

}
