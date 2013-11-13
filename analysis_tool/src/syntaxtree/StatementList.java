/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxtree;

import flowgraph.datastructure.FlowSet;
import flowgraph.datastructure.Node;
import flowgraph.datastructure.NodeSet;
import java.util.ArrayList;
import syntaxtree.statement.Statement;

/**
 *
 * @author krc
 */
public class StatementList extends ArrayList<Statement> {

    public FlowSet flow () {
        return FlowSet.emptySet;
    }
    
    public NodeSet lables () {
        NodeSet retval = NodeSet.emptySet;
        
        for (Statement s : this) {
            retval.union(s.labels());
        }
        
        return retval;
    }

    @Override
    public String toString() {
        String buffer = "";
        for (Statement s : this) {
            buffer += s + Symbols.NEWLINE;
        }
        
        return buffer;
    }

    public String labelTable() {
        String buffer = "";
        
        for (Statement s : this) {
           buffer += s +" "+  s.labels().toString() + "\n";
        }
        
        return buffer;
    }

}
