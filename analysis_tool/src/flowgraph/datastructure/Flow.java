/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flowgraph.datastructure;

/**
 *
 * @author krc
 */
public class Flow {
    
    private Node source;
    private Node target;
    
    public Flow(Node source, Node target) {
        this.source = source;
        this.target = target;
    }
    
    @Override
    public String toString() {
        return "("+this.source + ", " + this.target + ")";
    }
}
