package analysis;

import syntaxtree.StatementList;
import syntaxtree.Type;
import syntaxtree.expression.Variable;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;
import flowgraph.datastructure.VariableSet;

public class BufferUnderflow {
	
	public static void execute(StatementList stmts, LatticeSet lattice){
		NodeSet labels = stmts.lables();
		for(int i = 0; i<labels.size(); i++){
			Node node = labels.get(i);
			SignsLattice signslat = (SignsLattice)lattice.get(node);
			bufferUnderflow(node, signslat);
		}
	}
	
	public static void bufferUnderflow(Node poi, SignsLattice signsLat){
//		System.out.println(poi);
//		System.out.println(signsLat);
		VariableSet variables = poi.getStatement().getVariable();
		for(Variable v : variables){
			if(v.getType() == Type.ARRAY){
				
			}
		}
	}
	
}
