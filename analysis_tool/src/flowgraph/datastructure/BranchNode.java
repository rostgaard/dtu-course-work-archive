package flowgraph.datastructure;

import syntaxtree.condition.Condition;

public class BranchNode extends Node{

	private Condition condition;
	private Node nextTrue;
	private Node nextFalse;
	
	public Condition getCondition() {
		return condition;
	}
	
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	
	public Node getNextTrue() {
		return nextTrue;
	}
	
	public void setNextTrue(Node nextTrue) {
		this.nextTrue = nextTrue;
	}
	
	public Node getNextFalse() {
		return nextFalse;
	}
	
	public void setNextFalse(Node nextFalse) {
		this.nextFalse = nextFalse;
	}	
}
