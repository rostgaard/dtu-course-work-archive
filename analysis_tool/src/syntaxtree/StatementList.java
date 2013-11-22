/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxtree;

import flowgraph.datastructure.Flow;
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

    public FlowSet flow() {
        FlowSet retSet = FlowSet.emptySet;
        Statement s1 = null;

        for (Statement s2 : this) {
            if (s1 != null) {
                for (Node endNode : s1.finalNodes()) {
                    retSet.addFlow(new Flow(endNode, s2.init()));
                }
            }
            retSet.union(s2.flow());

            s1 = s2;

        }

        return FlowSet.emptySet;
    }

    public NodeSet lables() {
        NodeSet retval = NodeSet.emptySet;

        for (Statement s : this) {
            retval.union(s.labels());
        }

        return retval;
    }

    public Node init() {
        return new Node(this.get(0));
    }

    public NodeSet finalLabels() {
        return this.get(this.size()-1).finalNodes();
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
            buffer += s + " " + s.labels().toString() + "\n";
        }

        return buffer;
    }

    public String toStringWithLabel() {
        String buffer = "";
        for (Statement s : this) {
            buffer += s.toStringWithLabel() + Symbols.NEWLINE;
        }

        return buffer;
    }
}
