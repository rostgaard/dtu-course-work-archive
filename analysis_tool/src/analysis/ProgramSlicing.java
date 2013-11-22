package analysis;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import flowgraph.datastructure.NodeSet;
import org.apache.commons.collections15.map.HashedMap;

import flowgraph.datastructure.Node;
import flowgraph.datastructure.VariableSet;

public class ProgramSlicing {

	private HashedMap<Integer, ArrayList<Definition>> slices = new HashedMap<Integer, ArrayList<Definition>>();
	
	
	public static NodeSet execute(Node poi, RDProgramState analysis){
        System.out.println("POI: " + poi);
        NodeSet result = new NodeSet();
        LinkedList<Node> queue = new LinkedList<Node>();

        HashedMap<Integer, Boolean> visitedNodes = new HashedMap<>();
		queue.add(poi);
		while (!queue.isEmpty()) {
			Node label = queue.removeFirst();
            visitedNodes.put(label.getLabel(), true);
			result.add(label);
			VariableSet variables = label.getVariables();
            DefinitionSet definitions = analysis.getRDEntry(label.getLabel());
            for(Definition definition : definitions) {
                Boolean containsVariable = variables.contains(definition.identifier);
                Boolean labelDefined = definition.label!=null;

                if (containsVariable && labelDefined && !visitedNodes.containsKey(definition.label.getLabel())) {
                    queue.add(definition.label);
                }
            }
		}

        return result;
	}
}
