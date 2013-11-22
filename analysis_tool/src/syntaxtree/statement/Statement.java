package syntaxtree.statement;

import analysis.Definition;
import analysis.RDProgramState;
import flowgraph.datastructure.FlowSet;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;
import flowgraph.datastructure.VariableSet;
import syntaxtree.Symbols;
import utilities.Sequencer;
import analysis.DefinitionSet;

import java.util.TreeSet;

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

    public DefinitionSet killed(RDProgramState currentState) {
        DefinitionSet killed = currentState.getRDEntry(getLabel());
        return killed;
    }

    public DefinitionSet generated(RDProgramState currentState) {
        DefinitionSet generated = currentState.getRDEntry(getLabel());
        return generated;
    }
}