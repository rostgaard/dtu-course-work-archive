package Simulator.Utils;

import Simulator.Model.Resource;
import Simulator.Model.ResourceList;
import Simulator.Model.Task;
import Simulator.TaskList;
import Simulator.Model.Usage;
import Simulator.Model.UsageList;
import java.io.IOException;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.Vertex;
import edu.uci.ics.jung.io.GraphMLFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utililty class for loading GraphML files into the simulator tool.
 * @author Kim Rostgaard Christensen
 */
public final class GraphML {

    private static TaskList currentTasklist;
    private static ResourceList currentResourcelist;

    /**
     * Convenience metod for loading in a tasklist
     * @param modelname The name of the folder to be loaded
     * @return A tasklist for use in schedulability simulations and analysis
     */
    private static TaskList loadTasklist(String modelname) {
        TaskList tl = new TaskList();
        try {
            Graph g = GraphML.getTaskGraph(modelname);

            //int n = g.numVertices(); // number of tasks
            for (Iterator<Object> iter = g.getVertices().iterator(); iter.hasNext();) {
                Vertex v = (Vertex) iter.next();
                Task t = new Task((String) v.getUserDatum("Name"), getPeriodValue(v), getWCETValue(v), getBCETValue(v));
                tl.add(t);

                //t.getCritical_time_slots().add(getCriticalValue(v));
                //t.setSemaphor(new Semaphor((String) v.getUserDatum("Resource"), true));
                //t.getResourceList().add(new Semaphor((String)v.getUserDatum("Resource")));
                t.setDeadline(getDeadlineValue(v));
                t.setPriority(getPriorityValue(v));
            }

        } catch (IOException ex) {
            Logger.getLogger(GraphML.class.getName()).log(Level.SEVERE, null, ex);
        }
        currentTasklist = tl;
        return currentTasklist;
    }

    /**
     * Convenience method for loading an entire model
     * @param modelname The name of the model to be loaded
     * @throws FileNotFoundException If the model folder is not found
     */
    public static void LoadModel(String modelname) throws FileNotFoundException {
        if ((new File(modelname + "/taskgraph.graphml")).exists()) {
            Simulator.Main.Config.tasklist = GraphML.loadTasklist(modelname + "/taskgraph.graphml");
        } else {
            throw new FileNotFoundException();
        }

        if ((new File(modelname + "/resourcegraph.graphml")).exists()) {
            Simulator.Main.Config.resourceList = GraphML.loadResourceList(modelname + "/resourcegraph.graphml");
        }
        if ((new File(modelname + "/usagegraph.graphml")).exists()) {
            Simulator.Main.Config.usagelist = GraphML.loadUsageList(modelname + "/usagegraph.graphml");
        }
    }

    /**
     * Convenience method for loading a resource list
     * @param filename The name of the file to load
     * @return A resource list
     */
    private static ResourceList loadResourceList(String filename) {
        ResourceList rl = new ResourceList();
        try {
            Graph g = GraphML.getTaskGraph(filename);
            for (Iterator<Object> iter = g.getVertices().iterator(); iter.hasNext();) {
                Vertex v = (Vertex) iter.next();
                Resource s = new Resource((String) v.getUserDatum("Name"));
                rl.add(s);
            }
        } catch (IOException ex) {
            Logger.getLogger(GraphML.class.getName()).log(Level.SEVERE, null, ex);
        }
        currentResourcelist = rl;
        return currentResourcelist;
    }

    /**
     * Convenience method for loading a usagelist
     * @param filename The name of the file
     * @return A usagelist
     */
    private static UsageList loadUsageList(String filename) {
        UsageList ul = new UsageList();
        try {
            Graph g = GraphML.getTaskGraph(filename);
            for (Iterator<Object> iter = g.getVertices().iterator(); iter.hasNext();) {
                Vertex v = (Vertex) iter.next();
                String task = (String) v.getUserDatum("Task");
                String resource = (String) v.getUserDatum("Resource");
                int cduration = Integer.parseInt((v.getUserDatum("Duration")).toString());
                Task t = currentTasklist.find(task);
                Resource r = currentResourcelist.find(resource);
                Usage u = new Usage(t, r, cduration);
                ul.add(u);
                r.getUsageList().add(u);
            }
        } catch (IOException ex) {
            Logger.getLogger(GraphML.class.getName()).log(Level.SEVERE, null, ex);
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
     * Returns the static priority Value of the Task Node.
     *
     * It converts the exisiting UserDatum Value to integer
     *
     * @return An Int
     */
    private static int getPriorityValue(Vertex v) {
        return Integer.parseInt((v.getUserDatum("Priority")).toString());
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
