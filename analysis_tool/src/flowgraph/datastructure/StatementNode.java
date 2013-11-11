package flowgraph.datastructure;

import syntaxtree.statement.Statement;

public class StatementNode extends Node{
	
	private Statement codeblock;
	private Node next;
	
	public Statement getCodeblock() {
		return codeblock;
	}
	
	public void setCodeblock(Statement codeblock) {
		this.codeblock = codeblock;
	}
	
	public Node getNext() {
		return next;
	}
	
	public void setNext(Node next) {
		this.next = next;
	}
}
