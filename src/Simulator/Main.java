package Simulator;

/**
 * Main.java
 * This program, reads the task description of the
 * periodic tasks from the given graphml files and then performs a simple
 * schedulability test
 *
 *
 * @author Kim Christensen
 *
 * @created 9 September, 10
 */
import Simulator.Types.ProbabililtyDistribution;
import Simulator.Types.Schedulability;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main {

    public static int exitStatus = 0;

    public static void main(String[] args) throws IOException {
        TaskList tasklist = graphML.loadTasklist("pathfinder/taskgraph_1.graphml");
        Main.Config.resourceList = graphML.loadResourceList("pathfinder/resourcegraph_1.graphml");
        Main.Config.usagelist = graphML.loadUsageList("pathfinder/usagegraph_1.graphml");
//        RateMonotonicSimulation RMS = new RateMonotonicSimulation(1, tasklist,
//                ProbabililtyDistribution.NONE);
        RateMonotonicSimulationWithResources RMS = new RateMonotonicSimulationWithResources(1, tasklist,
                ProbabililtyDistribution.NONE);

        //System.out.println("Semaphors: " + Main.Config.resourceList );
        //System.out.println("Usages:\n" + Main.Config.usagelist );
        
        if (RMS.simulate() == Schedulability.SCHEDULABLE) {
            System.out.println(RMS.getClass().getName() + " Reports Schedulable");
        } else {
            System.out.println(RMS.getClass().getName() + " Reports Unschedulable");
            exitStatus = 1;
        }

/*
        DeadlineMonotonicAnalysis DMA = new DeadlineMonotonicAnalysis(tasklist);
        if (DMA.analyse() == Schedulability.SCHEDULABLE) {
            System.out.println(DMA.getClass().getName() + " Reports Schedulable");
        } else {
            exitStatus = 1;
            System.out.println(DMA.getClass().getName() + " Reports Unschedulable");
        }
*/

        tasklist.sortByName();
        tasklist.sortByPriority();
        //System.out.println(tasklist.fullinfo());





        // Write out SVG
        try {
            // Create file
            FileWriter fstream = new FileWriter("/home/krc/test.svg");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(tasklist.timelineToSVG(1, tasklist.getLCMofPeriods() * RMS.getLCMMultiplier()));
            //Close the output stream
            out.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

        /*
         * For automated command line schedulability analysis
         */
        System.exit(exitStatus);

        //TODO return 0 if schedulable, 1 otherwise
    }

    static class Config {
        public static ReadyQueue currentReadyQueue = new ReadyQueue();
        public static ResourceList resourceList = null;
        public static UsageList usagelist = null;
        public static boolean debug = true;
    }
}
