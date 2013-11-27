package unused.junggraph;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Create the static graph here!
 *
 */
public class Data {
	
	public Graph getStaticGraph(){
		List<Node> nodes = new ArrayList<Node>();
		List<Edge> edges = new ArrayList<Edge>();
		
		//create nodes
		Node n1 = new Node("1", "n1");
		Node n2 = new Node("2", "n2");
		Node n3 = new Node("3", "n3");
		Node n4 = new Node("4", "n4");
		Node n5 = new Node("5", "n5");
		Node n6 = new Node("6", "n6");
		Node n7 = new Node("7", "n7");

		//add nodes to list
		nodes.add(n1);
		nodes.add(n2);
		nodes.add(n3);
		nodes.add(n4);
		nodes.add(n5);
		nodes.add(n6);
		nodes.add(n7);
		
		//create edges
		Edge e1 = new Edge(n1, n2);
		Edge e2 = new Edge(n2, n3);
		Edge e3 = new Edge(n3, n4);
		Edge e4 = new Edge(n4, n5);
		Edge e5 = new Edge(n5, n6);
		Edge e6 = new Edge(n6, n1);
		Edge e7 = new Edge(n7, n1);
		Edge e8 = new Edge(n7, n2);
		Edge e9 = new Edge(n7, n3);
		Edge e10 = new Edge(n7, n4);
		Edge e11 = new Edge(n7, n5);
		Edge e12 = new Edge(n7, n6);
		
		//add edges to list
		edges.add(e1);
		edges.add(e2);
		edges.add(e3);	
		edges.add(e4);
		edges.add(e5);
		edges.add(e6);
		edges.add(e7);	
		edges.add(e8);
		edges.add(e9);
		edges.add(e10);
		edges.add(e11);
		edges.add(e12);
		
		//create graph and return it
		Graph graph = new Graph(nodes, edges);
		return graph;
	}
}
