package flowgraph.datastructure;

import syntaxtree.statement.Statement;

public class Node implements Comparable<Node>{

    protected Statement s;

    public Node(Statement s) {
        this.s = s;
    }

    public VariableSet getVariables(){
		return s.getVariable();
    }
    
    public int getLabel() {
        return s.getLabel();
    }

    @Override
    public String toString() {
        return this.s.toString();
    }

    @Override
    public int compareTo(Node n) {
        return n.s.getLabel() - s.getLabel();
    }
}
