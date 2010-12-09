package Schedtool;

/**
 * Main.java
 * This program, reads the task description of the
 * tasks from the given graphml files and then performs a simple
 * schedulability test or simulation
 *
 *
 * @author Kim Rostgaard Christensen
 *
 * @created 9 September, 2010
 */
import Schedtool.Model.UsageList;
import Schedtool.Model.ResourceList;
import Schedtool.Model.JobList;
import Schedtool.Model.ReadyQueue;
import Schedtool.Types.*;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;

/**
 *
 * @author krc
 */
public class Main {

    private static int exitStatus = 1;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        String model = "";
        int mode = 0;
        boolean svg = false;
        // parse command line arguments
        if (args.length == 0) {
            printUsage();
        }

        for (String arg : args) {
            if (arg.equals("-rm")) {
                Main.Config.prioritypolicy = PriorityPolicy.RATE_MONOTONIC;
            }
            if (arg.equals("-dm")) {
                Main.Config.prioritypolicy = PriorityPolicy.DEADLINE_MONOTONIC;
            }
            if (arg.equals("-st")) {
                Main.Config.prioritypolicy = PriorityPolicy.STATIC;
            }
            if (arg.equals("-pip")) {
                Main.Config.lockingPolicy = LockingPolicy.PRIORITY_INHERITANCE;
            }

            if (arg.equals("-un")) {
                Main.Config.probabilityDistribution = ProbabililtyDistribution.UNIFORM;
            }
            if (arg.equals("-no")) {
                Main.Config.probabilityDistribution = ProbabililtyDistribution.NONE;
            }
            if (arg.equals("-svg")) {
                svg = true;
            }


            if (arg.equals("-s")) {
                mode = 1;
            }
            if (arg.equals("-a") && mode == 0) {
                mode = 2;
            }
            if (arg.equals("-d")) {
                Main.Config.debug = true;
            }

        }
        model = args[args.length - 1];


        try {
            Schedtool.Utils.GraphML.LoadModel(model);
        } catch (FileNotFoundException ex) {
            System.out.println("Model not found");
            System.exit(-1);
        }

        Main.Config.tasklist.sortByPriority();

        if (mode == 1) {
            Simulator S = new Simulator(1, Main.Config.tasklist,
                    Main.Config.probabilityDistribution);
            if (S.simulate() == Schedulability.SCHEDULABLE) {
                exitStatus = 0;
            }

            // Write out SVG
            if (svg) {
                try {
                    // Create file
                    FileWriter fstream = new FileWriter(model + ".svg");
                    BufferedWriter out = new BufferedWriter(fstream);
                    out.write(Main.Config.tasklist.timelineToSVG(1, Main.Config.tasklist.getLCMofPeriods() * S.getLCMMultiplier()));
                    //Close the output stream
                    out.close();
                } catch (Exception e) {//Catch exception if any
                    System.err.println("Error: " + e.getMessage());
                }
            }

        } else if (mode == 2) {
            if(!Main.Config.usagelist.isEmpty() && Main.Config.lockingPolicy == LockingPolicy.NONE ) {
                System.out.println("Analysis must specify a locking policy when resources are in use");
                System.exit(exitStatus);
            }

            ShedulabiltyTest ST = new ShedulabiltyTest(Main.Config.tasklist, Main.Config.lockingPolicy);
            if (ST.analyse() == Schedulability.SCHEDULABLE) {
                exitStatus = 0;
            }
        } else {
            printUsage();
        }

        Main.Config.tasklist.sortByName();
        System.out.println(Main.Config.tasklist.fullinfo());

        System.exit(exitStatus);
    }

    private static void printUsage() {
        System.out.println("Usage: stool {-s|-a} [options] model");
        System.out.println();
        System.out.println(" -s \tSimulation");
        System.out.println(" -a \tAnalysis");
        System.out.println();
        System.out.println("Options:");
        System.out.println(" -pip\tEnable Priority Inheritance Protocol");
        System.out.println(" -rm \tRate monotonic priority assignment");
        System.out.println(" -dm \tDeadline monotonic priority assignment");
        System.out.println(" -sp \tStatic priority assignment (default)");
        System.out.println(" -un \tUse uniform distribution for generating execution times");
        System.out.println(" -no \tAlways assume WCET (default)");
        System.out.println(" -svg\tGenerates an svg image of simulation (model.svg)");
        System.out.println();
        System.out.println(" -d  \tEnable debug information");
        System.exit(-1);
    }

    /**
     * Internal configuration parameters
     */
    public static class Config {

        /**
         * Global readyqueue
         */
        public static ReadyQueue readyQueue = new ReadyQueue();
        /**
         * Global resourcelist
         */
        public static ResourceList resourceList = new ResourceList();
        /**
         * Global usagelist
         */
        public static UsageList usagelist = new UsageList();
        /**
         * Global tasklist
         */
        public static TaskList tasklist = new TaskList();
        /**
         * Global joblist
         */
        public static JobList joblist = new JobList();
        /**
         * Global debug enabled flag
         */
        public static boolean debug = false;
        /**
         * The global locking policy to be applied to resources
         */
        public static LockingPolicy lockingPolicy = LockingPolicy.NONE;
        /**
         * The global priority assignment policy
         */
        public static PriorityPolicy prioritypolicy = PriorityPolicy.STATIC;
        /**
         * The global probabililty distribution used to generate execution times
         */
        public static ProbabililtyDistribution probabilityDistribution = ProbabililtyDistribution.NONE;
    }
}
