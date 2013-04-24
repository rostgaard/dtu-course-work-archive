/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bootstrapping;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Graphs;

/**
 *
 * @author krc
 */
public final class NetworkModel {
    /**
     *
     */
    public static Graph<Integer, String> g =
            Graphs.<Integer, String>synchronizedDirectedGraph(new DirectedSparseMultigraph<Integer, String>());

    public static void addNode(int pid) {
        g.addVertex(pid);
    }

    public static void connect(int sourcePid, int destPid) {
        g.addEdge("Edge-" + sourcePid + "-" + destPid, sourcePid, destPid);
    }
}
