/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flowgraph.datastructure;

import java.util.ArrayList;

/**
 *
 * @author krc
 */
public class NodeSet extends ArrayList<Node> {

    public final static NodeSet emptySet = new NodeSet();

    public NodeSet addNode(Node n) {
        super.add(n);

        return this;
    }

    /**
     * Horribly inefficient Union operation, please fix prior to actual release
     * upon the world.
     *
     * @param nset
     */
    public NodeSet union(NodeSet nset) {
        for (Node n : nset) {
            if (!this.contains(n)) {
                this.add(n);
            }
        }

        return this;
    }

    @Override
    public String toString() {
        String buffer = "(";

        for (Node n : this) {
            buffer += n.getLabel();
            if (n == this.get(this.size() - 1))  {
            } else {
                buffer += ",";
            }
        }

        return buffer+")";
    }
}
