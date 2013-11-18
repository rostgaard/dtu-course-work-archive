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
public class FlowSet extends ArrayList<Flow> {
    public final static FlowSet emptySet = new FlowSet();
    public FlowSet addFlow(Flow f) {
        super.add(f);

        return this;
    }

    /**
     * Horribly inefficient Union operation, please fix prior to actual release
     * upon the world.
     *
     * @param nset
     */
    public FlowSet union(FlowSet fset) {
        for (Flow f : fset) {
            if (!this.contains(f)) {
                this.add(f);
            }
        }

        return this;
    }

    @Override
    public String toString() {
        String buffer = "(";

        for (Flow flow : this) {
            buffer += flow;
            if (flow == this.get(this.size() - 1))  {
            } else {
                buffer += ",";
            }
        }

        return buffer+")";
    }

}
