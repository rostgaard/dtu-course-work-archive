/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Simulator;

import java.io.IOException;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.Vertex;
import edu.uci.ics.jung.io.GraphMLFile;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author krc
 */
public final class graphML {

    public TaskList load(String filename) {
        TaskList tl = new TaskList();
        try {
            Graph g = graphML.getTaskGraph(filename);

            //int n = g.numVertices(); // number of tasks
        for (Iterator<Object> iter = g.getVertices().iterator(); iter.hasNext();) {
            Vertex v = (Vertex) iter.next();
            Task t = new Task((String) v.getUserDatum("Name"), getPeriodValue(v), getWCETValue(v), getBCETValue(v));
            tl.add(t);
            t.setDeadline(getDeadlineValue(v));
            t.setMappedTo(getMappedToProc(v));
        }

        } catch (IOException ex) {
            Logger.getLogger(graphML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tl;
    }

    /**
     * Reads a task: in this case, reads it from the file "taskgraph.graphml"
     *
     * @return A undirected graph containing the tasks, their computation times
     *         periods and respective deadlines.
     */
    private static Graph getTaskGraph(String filename) throws IOException {
        GraphMLFile gml = new GraphMLFile();

        Graph g_TG = gml.load(filename);

        return g_TG;
    }

    /**
     * Returns the WCET Value of the Task Node.
     *
     * It converts the exisiting UserDatum Value to integer
     *
     * @return An Int
     */
    private static int getWCETValue(Vertex v) {
        return Integer.parseInt((v.getUserDatum("WCET")).toString());
    }

    /**
     * Returns the Period Value of the Task Node.
     *
     * It converts the exisiting UserDatum Value to integer
     *
     * @return An Int
     */
    private static int getPeriodValue(Vertex v) {
        return Integer.parseInt((v.getUserDatum("Period")).toString());
    }

    /**
     * Returns the Deadline Value of the Task Node.
     *
     * It converts the exisiting UserDatum Value to integer
     *
     * @return An Int
     */
    private static int getDeadlineValue(Vertex v) {
        return Integer.parseInt((v.getUserDatum("Deadline")).toString());
    }

    /**
     * Returns the BCET Value of the Task Node.
     *
     * It converts the exisiting UserDatum Value to integer
     *
     * @return An Int
     */
    private static int getBCETValue(Vertex v) {
        return Integer.parseInt((v.getUserDatum("BCET")).toString());
    }

    /**
     * Returns the Processor Name on which the task is mapped to
     *
     * It converts the exisiting UserDatum Value to integer
     *
     * @return An Int
     */
    private static String getMappedToProc(Vertex v) {
        return (String) v.getUserDatum("MappedTo");
    }
}

