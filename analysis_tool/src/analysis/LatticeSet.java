/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analysis;

import flowgraph.datastructure.Node;
import java.util.HashMap;
import syntaxtree.expression.Variable;

/**
 *
 * @author krc
 */
public class LatticeSet extends HashMap<Node, Lattice>{

    public String toString () {
        String buffer = "";
        
        for (Node key : this.keySet()) {
            buffer += key + " : " + this.get(key) + "\n";
        }
        
        return buffer+"";
        
    }

}
