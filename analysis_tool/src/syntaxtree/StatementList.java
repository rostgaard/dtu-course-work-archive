/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxtree;

import flowgraph.datastructure.FlowSet;
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
        return NodeSet.emptySet;
    }

    @Override
    public String toString() {
        String buffer = "";
        for (Statement s : this) {
            buffer += s +"\n";
        }
        
        return buffer;
    }
}
