package analysis;

import flowgraph.datastructure.Node;
import java.util.HashMap;

/**
 *
 * LatticeSet is the basis for all our analysis'. This class holds the results
 * of different kinds of analysis' and can accessed, printed, and manipulated
 * via the nodes.
 *
 * @author krc
 */
public class LatticeSet extends HashMap<Node, Lattice> {

    /**
     * Returns a terminal-friendly representation of the LatticeSet. Every Node
     * - and it's analysis result is returned in a string with each item in the
     * set separated by a newline.
     *
     * @return A String representation of the LatticeSet.
     */
    @Override
    public String toString() {
        String buffer = "";

        for (Node key : this.keySet()) {
            buffer += key + " : " + this.get(key) + "\n";
        }
        return buffer;

    }
}
