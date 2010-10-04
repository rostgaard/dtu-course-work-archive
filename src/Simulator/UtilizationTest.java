package Simulator;

/**
 * UtilizationTest.java
 * This program, reads the task description of the
 * periodic tasks from the given graphml files and then performs a simple Liu & Layland Utilization test
 * 
 * Liu, C. L. and Layland, J. W. 1973. Scheduling Algorithms for Multiprogramming in a Hard-Real-Time Environment. 
 * J. ACM 20, 1 (Jan. 1973), 46-61. DOI= http://doi.acm.org/10.1145/321738.321743 
 * 
 * 
 * @author Prabhat Saraswat
 * 
 * @created 24 September, 09
 * 
 * 
 * 
 */
import java.io.IOException;
import java.util.Iterator;
import java.lang.Math;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.Vertex;
import edu.uci.ics.jung.io.GraphMLFile;

public class UtilizationTest {

    public static void main(String[] args) throws IOException {

        Graph g = getTaskGraph(); // Load the task graph from the graphml file
        int n = g.numVertices(); // number of tasks


        // Printing the task details in the loaded task graph
        for (Iterator<Object> iter = g.getVertices().iterator(); iter.hasNext();) {
            Vertex v = (Vertex) iter.next();

            System.out.println("\n Task Name\t" + v.getUserDatum("Name")
                    + "\tWCET  " + getWCETValue(v) + "\tBCET  " + getBCETValue(v) + " \tPeriod  "
                    + getPeriodValue(v) + " \tDeadline  "
                    + getDeadlineValue(v) + "\tMappedTo  " + getMappedToProc(v));

        }

        System.out.println("\n \n Processor Utilization    " + getProcUtilization(g) + " Ulb " + (double) n * (Math.pow(2, 1 / (double) n) - 1));
    }

    /**
     * Reads a task Set, Returns the processor utilization
     *
     * @return A double value containing the processor
     * 			utilization for the task set
     */
    public static double getProcUtilization(Graph g) {
        double U = 0;
        for (Iterator<Object> iter = g.getVertices().iterator(); iter.hasNext();) {
            Vertex v = (Vertex) iter.next();

            int Ci = getWCETValue(v);  // worst case
            int Ti = getPeriodValue(v); // period

            U = U + ((double) Ci / (double) Ti);
        }

        return U;
    }

    /**
     * Reads a task: in this case, reads it from the file "taskgraph.graphml"
     *
     * @return A undirected graph containing the tasks, their computation times
     *         periods and respective deadlines.
     */
    public static Graph getTaskGraph() throws IOException {
        GraphMLFile gml = new GraphMLFile();

        Graph g_TG = gml.load("Casestudy1/taskgraph_1.graphml");

        return g_TG;
    }

    /**
     * Returns the WCET Value of the Task Node.
     *
     * It converts the exisiting UserDatum Value to integer
     *
     * @return An Int
     */
    public static int getWCETValue(Vertex v) {
        return Integer.parseInt((v.getUserDatum("WCET")).toString());
    }

    /**
     * Returns the Period Value of the Task Node.
     *
     * It converts the exisiting UserDatum Value to integer
     *
     * @return An Int
     */
    public static int getPeriodValue(Vertex v) {
        return Integer.parseInt((v.getUserDatum("Period")).toString());
    }

    /**
     * Returns the Deadline Value of the Task Node.
     *
     * It converts the exisiting UserDatum Value to integer
     *
     * @return An Int
     */
    public static int getDeadlineValue(Vertex v) {
        return Integer.parseInt((v.getUserDatum("Deadline")).toString());
    }

    /**
     * Returns the BCET Value of the Task Node.
     *
     * It converts the exisiting UserDatum Value to integer
     *
     * @return An Int
     */
    public static int getBCETValue(Vertex v) {
        return Integer.parseInt((v.getUserDatum("BCET")).toString());
    }

    /**
     * Returns the Processor Name on which the task is mapped to
     *
     * It converts the exisiting UserDatum Value to integer
     *
     * @return An Int
     */
    public static String getMappedToProc(Vertex v) {

        return (String) v.getUserDatum("MappedTo");

    }
}
