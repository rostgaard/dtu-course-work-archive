package unused.junggraph;

//import java.awt.Color;

/**
 *
 * An edge which connects two nodes (source and target)
 * Add color property if needed
 *
 */

public class Edge {

	private Node source;
	private Node target;
//	private Color color;

	/**
	 * Constructor of the class
	 * @param source - source node
	 * @param target - target node
	 */
	public Edge(Node source, Node target){
		this.source = source;
		this.target = target;
//		this.color = Color.BLACK; //default color of the edges
	}

	public Node getSource() {
		return source;
	}

	public void setSource(Node source){
		this.source= source;
	}
	
	public Node getTarget() {
		return target;
	}
	
	public void setTarget(Node target){
		this.target = target;
	}

//	public Color getColor(){
//		return color;
//	}
	
//	public void setColor(Color color){
//		this.color = color;
//	}

	@Override
	public String toString() {
		return "Edge [source=" + source + ", target=" + target + "]";
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Edge) {
			Edge that = (Edge) obj;
			result = (this.getSource() == that.getSource() && this.getTarget() == that.getTarget() /*&& this.getColor() == that.getColor()*/);
		}
		return result;
	}

	@Override
	public int hashCode() {
		int hash = 1;
		hash = hash * 17 + (source == null ? 0 : source.hashCode());
		hash = hash * 13 + (target == null ? 0 : target.hashCode());
//		hash = hash * 21 + color.hashCode();
		return hash;
	}
}
