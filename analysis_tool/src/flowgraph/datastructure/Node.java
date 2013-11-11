package flowgraph.datastructure;

public abstract class Node {

	protected int label;
	protected Node prev;
	
	public int getLabel() {
		return label;
	}
	
	public void setLabel(int label) {
		this.label = label;
	}
	
	public Node getPrev() {
		return prev;
	}
	
	public void setPrev(Node prev) {
		this.prev = prev;
	}
}
