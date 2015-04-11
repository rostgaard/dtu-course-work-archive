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

    public static NodeSet factory () {
        return new NodeSet();
    }
    
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

}
