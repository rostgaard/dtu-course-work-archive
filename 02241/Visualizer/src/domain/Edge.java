package domain;

import java.awt.Color;

/**
 * 
 * An edge which connects two nodes (n1 and n2)
 * Simulates a track in the railway network system
 *
 */

public class Edge {

	private Node n1;
	private Node n2;
	private Color color;

	/**
	 * Constructor of the class
	 * @param n1 - first node
	 * @param n2 - second node
	 */
	public Edge(Node n1, Node n2){
		this.n1 = n1;
		this.n2 = n2;
		this.color = Color.BLACK; //default color of the edges
	}

	public Node getN1() {
		return n1;
	}

	public void setN1(Node n1){
		this.n1 = n1;
	}
	
	public Node getN2() {
		return n2;
	}
	
	public void setN2(Node n2){
		this.n2 = n2;
	}
	
	/**
	 * Color of the edge
	 * @return
	 */
	public Color getColor(){
		return color;
	}
	
	public void setColor(Color color){
		this.color = color;
	}

	@Override
	public String toString() {
		return "Edge [n1=" + n1 + ", n2=" + n2 + "]";
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Edge) {
			Edge that = (Edge) obj;
			result = (this.getN1() == that.getN1() && this.getN2() == that.getN2() && this.getColor() == that.getColor());
		}
		return result;
	}

	@Override
	public int hashCode() {
		int hash = 1;
		hash = hash * 17 + (n1 == null ? 0 : n1.hashCode());
		hash = hash * 13 + (n2 == null ? 0 : n2.hashCode());
		hash = hash * 21 + color.hashCode();
		return hash;
	}
}
