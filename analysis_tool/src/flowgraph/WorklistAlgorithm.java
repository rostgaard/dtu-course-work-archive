package flowgraph;

import analysis.Lattice;
import analysis.LatticeSet;
import analysis.RDLattice;
import analysis.Worklist;
import flowgraph.datastructure.Flow;
import flowgraph.datastructure.FlowSet;
import flowgraph.datastructure.Node;
import syntaxtree.Program;

public final class WorklistAlgorithm {

    //private static ProgramState calculate (Lattice l) {
    //}
    public static void calculate(FlowSet S, Program program) {
        Worklist worklist = new Worklist();
        Lattice analysisSpace = new RDLattice (program.getDecls());
        LatticeSet analysis = new LatticeSet();

        for (Flow flow : program.getStmts().flow()) {
            worklist.add(flow);
        }

        for (Node node : program.getStmts().lables()) {
            if (node == program.getStmts().init()) {
                analysis.put(node, analysisSpace.factory()); //TODO: Change to iota.
            } else {
                analysis.put(node, analysisSpace.factory());
            }
        }

        System.out.println(analysis);

        while (!worklist.isEmpty()) {
            Flow flow = worklist.removeFirst();
//            System.out.println("worklist removing" + flow.getSource());
            Node sourceNode = flow.getSource();
            Node destinationNode = flow.getTarget();

            Lattice L = sourceNode.getStatement()
                    .transferFunction(analysis.get(flow.getSource()));
            Lattice Lprime = analysis.get(flow.getTarget());

            if (!L.subsetOf(Lprime)) {
                System.out.println("Pushing more work to the worklist!");
                
                Lprime.union(L);
                System.out.println("L:("+sourceNode.getLabel() + " " + L);
                System.out.println("Lprime.union(L): "+Lprime.union(L));

                // Push more work to the worklist.
                FlowSet newFlows = S.flows(destinationNode);
                for (Flow f : newFlows) {
                    worklist.add(f);
                }
            }
            System.out.println(analysis);

//            if (!sourceNode.transferFunction(analysis) .subsetOf(analysis)) {
//                
//            }
        }
    }
}
//            DefinitionSet exitLabel1 = analysis.getRDEntry(label1);
//            DefinitionSet killed = flow.getSource().getStatement().killed(analysis);
//
//            DefinitionSet generated = flow.getSource().getStatement().generated(analysis);
//
//            exitLabel1.killAll(killed);
//            exitLabel1.addAll(generated);
//
//            DefinitionSet entryLabel2 = analysis.getRDEntry(label2);
//            if(!entryLabel2.containsAll(exitLabel1)) {
//                entryLabel2.addAll(exitLabel1);
//                analysis.addRDentry(label2,entryLabel2);
//
//                FlowSet newFlows = flows.flows(label2);
//                worklist.addAll(newFlows);
//            }
