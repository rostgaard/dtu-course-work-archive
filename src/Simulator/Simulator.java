package Simulator;

/**
 * Simulator.java
 * This program, reads the task description of the
 * periodic tasks from the given graphml files and then performs a simple
 * schedulability test
 *
 * TODO: Add JUNG library
 *
 * @author Kim Christensen
 *
 * @created 9 September, 10
 */
import Simulator.Types.ProbabliltyDistribution;
import Simulator.Types.Schedulability;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Simulator {
    public static int exitStatus = 0;

    public static void main(String[] args) throws IOException {
        TaskList tasklist = new graphML().load("Casestudy2/taskgraph_2.graphml");
        RateMonotonicAnalysis RMA = new RateMonotonicAnalysis(1, tasklist,
                ProbabliltyDistribution.UNIFORM);
        
        if (RMA.analyse() == Schedulability.SCHEDULABLE) {
            System.out.println(RMA.getClass().getName() + " Reports Schedulable");
        } else {
            System.out.println(RMA.getClass().getName() + " Reports Unschedulable");
            exitStatus = 1;
        }
            

        DeadlineMonotonicAnalysis DMA = new DeadlineMonotonicAnalysis(tasklist);
        if (DMA.analyse() == Schedulability.SCHEDULABLE) {
            System.out.println(DMA.getClass().getName() + " Reports Schedulable");
        } else {
            exitStatus = 1;
            System.out.println(DMA.getClass().getName() + " Reports Unschedulable");
        }
            

        tasklist.sortByName();
        System.out.println(tasklist);



        // Write out SVG
        try {
            // Create file
            FileWriter fstream = new FileWriter("/home/krc/test.svg");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(tasklist.timelineToSVG(1, tasklist.getLCMofPeriods() * RMA.getLCMMultiplier()));
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
}

