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
    private static TaskList currentTasklist;
    private static ResourceList currentResourcelist;

    public static TaskList loadTasklist(String filename) {
        TaskList tl = new TaskList();
        try {
            Graph g = graphML.getTaskGraph(filename);

            //int n = g.numVertices(); // number of tasks
            for (Iterator<Object> iter = g.getVertices().iterator(); iter.hasNext();) {
                Vertex v = (Vertex) iter.next();
                Task t = new Task((String) v.getUserDatum("Name"), getPeriodValue(v), getWCETValue(v), getBCETValue(v));
                tl.add(t);

                //t.getCritical_time_slots().add(getCriticalValue(v));
                //t.setSemaphor(new Semaphor((String) v.getUserDatum("Resource"), true));
                //t.getResourceList().add(new Semaphor((String)v.getUserDatum("Resource")));
                t.setDeadline(getDeadlineValue(v));
                t.setMappedTo(getMappedToProc(v));
            }

        } catch (IOException ex) {
            Logger.getLogger(graphML.class.getName()).log(Level.SEVERE, null, ex);
        }
        currentTasklist = tl;
        return currentTasklist;
    }
    
    public static ResourceList loadResourceList(String filename) {
        ResourceList rl = new ResourceList();
        try {
            Graph g = graphML.getTaskGraph(filename);
            for (Iterator<Object> iter = g.getVertices().iterator(); iter.hasNext();) {
                Vertex v = (Vertex) iter.next();
                Resource s = new Resource((String) v.getUserDatum("Name"));
                rl.add(s);
            }
        } catch (IOException ex) {
            Logger.getLogger(graphML.class.getName()).log(Level.SEVERE, null, ex);
        }
        currentResourcelist = rl;
        return currentResourcelist;
    }

    public static UsageList loadUsageList(String filename) {
        UsageList ul = new UsageList();
        try {
            Graph g = graphML.getTaskGraph(filename);
            for (Iterator<Object> iter = g.getVertices().iterator(); iter.hasNext();) {
                Vertex v = (Vertex) iter.next();
                String task = (String)v.getUserDatum("Task");
                String resource = (String)v.getUserDatum("Resource");
                int cduration = Integer.parseInt((v.getUserDatum("Duration")).toString());
                Task t = currentTasklist.find(task);
                Resource r =  currentResourcelist.find(resource);
                ul.add(new Usage(t,r,cduration));
                System.out.println(graphML.class.getSimpleName() + ": " + task);
                
                //Usage u = new Usage();


                //ul.add(u);
            }
        } catch (IOException ex) {
            Logger.getLogger(graphML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ul;
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

    private static int getCriticalValue(Vertex v) {
        return Integer.parseInt((v.getUserDatum("Critical")).toString());
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
