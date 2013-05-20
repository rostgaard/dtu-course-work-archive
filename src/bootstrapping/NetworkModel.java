/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bootstrapping;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Graphs;
import java.util.ArrayList;

/**
 *
 * @author krc
 */
public final class NetworkModel {

    /* Multicast groups within the network. */
    private static ArrayList<NodeList> multicastGroups = new ArrayList<>();
    /* Network model stored in a graph. */
    public static Graph<Integer, String> g =
            Graphs.<Integer, String>synchronizedDirectedGraph(new DirectedSparseMultigraph<Integer, String>());
    /**
     * Adds a node to the network model.
     *
     * @param pid The Process ID of the node to add to the network model.
     */
    public static void addNode(int pid) {
        g.addVertex(pid);
    }

    /**
     * Signals that a connection is now active between two nodes.
     *
     * @param sourcePid The source of the sink.
     * @param destPid The destination of the sink.
     */
    public static void connect(int sourcePid, int destPid) {
        g.addEdge("Edge-" + sourcePid + "-" + destPid, sourcePid, destPid);
    }

    /**
     * TODO
     *
     * @param PID
     * @param GroupID
     */
    public static void subscribe(int PID, int GroupID) {
        try {
            multicastGroups.get(GroupID);

            if (!multicastGroups.get(GroupID).contains(PID)) {
                multicastGroups.get(GroupID).add(PID);
            }
        } catch (IndexOutOfBoundsException e) {
            multicastGroups.add(GroupID, new NodeList());
        }

    }

    /**
     * TODO
     *
     * @param PID
     * @param GroupID
     */
    public static void unsubscribe(int PID, int GroupID) {
        try {
            NodeList group = multicastGroups.get(GroupID);

            if (multicastGroups.get(GroupID).contains(PID)) {
                multicastGroups.get(GroupID).remove(PID);
            }

        } catch (IndexOutOfBoundsException e) {
            // Just ignore the call if there is no subscription to
            // unsubscibe.
        }

    }
}

class NodeList extends ArrayList<Integer> {
}
