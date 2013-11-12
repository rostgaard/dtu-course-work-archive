package syntaxtree.statement;

import flowgraph.datastructure.FlowSet;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;

/**
 * Abstract class for statements
 *
 */

public abstract class Statement implements analysis.Analysable{
    
    private int label = -1;

    public abstract NodeSet labels();
    public abstract Node init();
    public abstract NodeSet finalNodes();

    public void setLabel (int newLabel){
        this.label = newLabel;
    }
    
    public int getLabel (){
        return this.label;
    }
}
