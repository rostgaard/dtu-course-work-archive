/*
 * High-level simulation view of the network.
 */
package bootstrapping;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Graphs;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kim Rostgaard Christensen
 */
public final class NetworkModel {

    private static final Logger logger = Logger.getLogger(NetworkModel.class.getName());

    /* Multicast groups within the network. */
    private static ArrayList<NodeList> multicastGroups = new ArrayList<>();
    /* Network model stored in a graph. */
    public static Graph<Integer, String> g =
            Graphs.<Integer, String>synchronizedDirectedGraph(new DirectedSparseMultigraph<Integer, String>());
    private static Collection<String> edges = new HashSet<>();
    public static int currentAdmin = -1;

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
    public synchronized static void connect(int sourcePid, int destPid) {
        String edgeName = "Edge-" + sourcePid + "-" + destPid;
        g.addEdge(edgeName, sourcePid, destPid);
        edges.add(edgeName);
    }

    /**
     * Subscribe a node to the a multicast group.
     *
     * @param PID The process ID to subscribe.
     * @param GroupID The group to subscribe to.
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
     * Unsubscribe a node from a multicast group.
     *
     * @param PID The process ID to unsubscribe.
     * @param GroupID The group to unsubscribe from.
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

    /**
     * Resets the channels visualized.
     */
    public static void clearEdges() {
        logger.log(Level.INFO, "Clearing edges");

        for (String e : edges) {
            g.removeEdge(e);
        }

        edges.clear();

        for (int i = 0; i < 5; i++) {
        }
    }

    /**
     * Signal a new admin.
     *
     * @param ID The PID of the new admin node.
     */
    public void setNewAdmin(int ID) {
        NetworkModel.currentAdmin = ID;
    }
}

class NodeList extends ArrayList<Integer> {
}
