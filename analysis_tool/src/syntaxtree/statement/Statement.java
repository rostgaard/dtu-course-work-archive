package syntaxtree.statement;

import flowgraph.datastructure.FlowSet;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;
import flowgraph.datastructure.VariableSet;
import syntaxtree.Symbols;
import utilities.Sequencer;

/**
 * Abstract class for statements
 *
 */

public abstract class Statement implements analysis.Analysable {
    
    private int label = -1;

    public abstract NodeSet labels();
    public abstract NodeSet finalNodes();
    public abstract Node init();
    public abstract FlowSet flow();
    public abstract VariableSet getVariable();    
    
    public String toStringWithLabel() {
        return Symbols.LSQPARAN + this.toString() + Symbols.RSQPARAN + this.label;
    }

    public void setLabel (Sequencer seq){
        this.label = seq.nextInt();
    }
        
    public int getLabel (){
        return this.label;
    }

    public Node toNode() {
        return new Node(this);
    }
}
