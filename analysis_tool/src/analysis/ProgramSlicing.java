package analysis;

import java.util.ArrayList;
import java.util.LinkedList;
import org.apache.commons.collections15.map.HashedMap;
import syntaxtree.StatementList;
import syntaxtree.expression.Variable;
import flowgraph.datastructure.NodeSet;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.VariableSet;

public class ProgramSlicing {

	public static void execute(StatementList stmts, Analysis lattice){
		NodeSet labels = stmts.lables();
		for(int i = 0; i<labels.size(); i++){
			Node node = labels.get(i);
			RDLattice rdlat = (RDLattice)lattice.get(node);
			programSlice(node, rdlat);
		}
	}

	public static void programSlice(Node poi, RDLattice analysis){
		NodeSet result = new NodeSet();
		LinkedList<Node> queue = new LinkedList<Node>();
		HashedMap<Integer, Boolean> visitedNodes = new HashedMap<>();

		queue.add(poi);
		result.add(poi);
		while (!queue.isEmpty()) {
			Node label = queue.removeFirst();
			VariableSet variables = label.getVariables();

			ArrayList<Variable> vars = new ArrayList<>(analysis.keySet());
			for(Variable variable: vars) {
				DefinitionSet set = analysis.get(variable);
				for(Definition def : set){
					Boolean containsVariable = variables.contains(variable);
					Boolean labelDefined = !analysis.get(variable).isEmpty();
					if (containsVariable && labelDefined && !visitedNodes.containsKey(def.label.getLabel())) {
						visitedNodes.put(def.label.getLabel(), true);
						if(!result.contains(def.label)){
							result.add(def.label);
						}
						queue.add(def.label);
					
					}
				}
			}
		}
		System.out.println("POI: " + poi.getLabel() + " result: " + result);		
//		return result;
	}


	/**
	 * @deprecated
	 * @param poi
	 * @param analysis
	 * @return
	 */
	public static NodeSet execute(Node poi, RDProgramState analysis){
		System.out.println("POI: " + poi);
		NodeSet result = new NodeSet();
		LinkedList<Node> queue = new LinkedList<Node>();

		HashedMap<Integer, Boolean> visitedNodes = new HashedMap<>();
		queue.add(poi);
		while (!queue.isEmpty()) {
			Node label = queue.removeFirst();
			VariableSet variables = label.getVariables();
			DefinitionSet definitions = analysis.getRDEntry(label.getLabel());
			for(Definition definition : definitions) {
				Boolean containsVariable = variables.contains(definition.identifier);
				Boolean labelDefined = definition.label!=null;

				if (containsVariable && labelDefined && !visitedNodes.containsKey(definition.label.getLabel())) {
					visitedNodes.put(definition.label.getLabel(), true);
					result.add(definition.label);
					queue.add(definition.label);
				}
			}
		}

		return result;
	}
}
