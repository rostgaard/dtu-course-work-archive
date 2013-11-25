package flowgraph.datastructure;

import analysis.Definition;
import analysis.LatticeSet;
import java.util.Objects;
import syntaxtree.statement.Statement;

import java.util.TreeSet;

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
        return ""+ s.getLabel();
    }

    @Override
    public int compareTo(Node n) {
        return n.s.getLabel() - s.getLabel();
    }

    public Statement getStatement() {
        return s;
    }
    
    public boolean equals (Node n) {
        return this.s.getLabel() == n.s.getLabel();
    }

    @Override
    public int hashCode() {
        return this.s.getLabel();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        if (!Objects.equals(this.s, other.s)) {
            return false;
        }
        return true;
    }
}
