package analysis;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.commons.collections15.map.HashedMap;

import flowgraph.datastructure.Node;
import flowgraph.datastructure.VariableSet;

public class ProgramSlicing {

	private HashedMap<Integer, ArrayList<Definition>> slices = new HashedMap<Integer, ArrayList<Definition>>();
	
	
	public void ProgramSlice(Node poi){
		ArrayList<Node> result = new ArrayList<Node>();
		Queue<Node> queue = new LinkedList<Node>();

		queue.add(poi);
		while (!queue.isEmpty()) {
			//Label l = queue.dequeue()
			Node label = queue.poll();
			//result.add(l)
			result.add(label);
			//Array variables = l.get_variables()
			VariableSet vs = label.getVariables();
			//Array RD = RDentry( l )
			//for label in RD do
			//if variables.contains ( label.get_variables ()) then
			//queue.enqueue(label)
			//end if
			//end for
			//end while
		}
		//return result
		//end procedure
	}
}
