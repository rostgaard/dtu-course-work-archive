package Schedtool;

import Schedtool.Model.JobList;
import Schedtool.Model.Task;
import Schedtool.Model.Job;
import Schedtool.Model.ReadyQueue;
import Schedtool.Types.ProbabililtyDistribution;
import Schedtool.Types.Schedulability;
import java.util.Random;

/**
 * Provides mechanism for doing a simulation - with or without resources
 * @author Kim Rostgaard Christensen
 */
public class Simulator {

    private TaskList tasklist = null;
    private int LCMMultiplier = 1;
    private ProbabililtyDistribution distribution =
            ProbabililtyDistribution.UNIFORM;

    /**
     * Creates a new RM analysis object
     * 
     * @param lcmm The number of cycles to run the simuation
     * @param tl The tasklist to run the analysis on
     * @param dist The probability distribution used in generating execution times
     */
    public Simulator(int lcmm, TaskList tl, ProbabililtyDistribution dist) {
        this.tasklist = tl;
        this.LCMMultiplier = lcmm;
        this.distribution = dist;
    }

    /**
     *
     * @return
     */
    public ProbabililtyDistribution getDistribution() {
        return distribution;
    }

    /**
     *
     * @param distribution
     */
    public void setDistribution(ProbabililtyDistribution distribution) {
        this.distribution = distribution;
    }

    /**
     * Method for initializing the priorities. Redundant as this is also done on
     * every simulate() call. Provided for possible extentions
     */
    private void initializeRMPriorities() {
        for (Task t : tasklist) {
            t.setPriority(tasklist.getLCMofPeriods() / t.getPeriod());
        }
    }

    /**
     * Runs the simulation
     * @return {@link Types.Schedulability}
     */
    public Schedulability simulate() {
        switch (Main.Config.prioritypolicy) {
            case RATE_MONOTONIC:
                initializeRMPriorities();
                break;

            default:
                break;
        }
        int num_cycles = tasklist.getLCMofPeriods();

        Timeline timeline = new Timeline();
        JobList jobList = this.initializeJobs(num_cycles);
        /* We run the simulation for a LCM of periods number of cycles */


        /* Sort jobqueue on start time and priority */
        jobList.sort();

        ReadyQueue readyList = Main.Config.readyQueue;

        /* The simulation itself starts here */
        for (int cycle = 0; cycle < num_cycles * this.getLCMMultiplier(); cycle++) {

            /* */
            readyList = jobList.getReadyJobs(readyList, cycle);

            /* Pick up the highest priority job */
            Job currentJob = readyList.getHighestPriorityJob();

            if (Main.Config.debug) {
                System.out.print(this.getClass().getSimpleName() + " cycle : " + cycle + " task: ");
                if (currentJob == null) {
                    System.out.println("idle");
                } else {
                    System.out.println(currentJob.getParentTask().getName());
                }
            }

            /* We account for idle cycle, by skipping them */
            if (currentJob == null) {
                //timeline.execute(tasklist.getIdleTask());
                tasklist.getIdleTask().getTimeSlotList().add(cycle);
                continue;
            }

            /* Try to execute any job available */
            while (!currentJob.timeTick(cycle)) {
                // Get the next job in the queue
                currentJob = readyList.getHighestPriorityJob();

                // This means we have a deadlock!
                if(currentJob == null) {
                    System.out.println("\nWarning! deadlock detected");
                }

                if (Main.Config.debug) {
                    System.out.print(this.getClass().getSimpleName() + ": Trying to execute "
                            + currentJob.getParentTask().getName() + " instead");
                }
            }

            //timeline.execute(currentJob.getParentTask());

            /* When the current job has finished, some information is harvested */
            if (currentJob.getTime() == 0) {
                /* Response time of an instance. It is the time (measured from
                the release time) at which the instance is terminated. */
                int currentWCRT = (cycle+1) - currentJob.getRelease();
                currentJob.setResponsetime(currentWCRT);
                /*   remember worst-case response */
                if (currentJob.getParentTask().getResponseTime() <= currentWCRT) {
                    currentJob.getParentTask().setResponseTime(currentWCRT);
                }
                /* Remove the job from ready list */
                readyList.remove(currentJob);

            }
        }

        for (Task t : tasklist) {
            //TODO find the case of the WCRT that broke the camels back - so to speak
            if (!t.schedulable()) {
                return Schedulability.UNSCHEDULABLE;
            }
        }
        return Schedulability.SCHEDULABLE;

    }

    /**
     *
     * @param numCycles
     * @return
     */
    public JobList initializeJobs(int numCycles) {
        JobList jobList = new JobList();
        Random rng = new Random();

        /* Initialize jobs */
        for (Task t : tasklist) {
            for (int j = 1; j <= (numCycles / t.getPeriod()); j++) {
                Job job = new Job();
                t.getJobList().add(job); //TODO check if this is ever used
                job.setParentTask(t);
                // Map the critical duration
                if (Main.Config.usagelist != null) {
                    job.setResourceUsage(Main.Config.usagelist.find(t.getName()));
                }

                // Caculate the release time
                job.setRelease(t.getPeriod() * (j - 1));

                if ((t.getWCET() - t.getBCET()) <= 0) { // The random function complains when range is 0
                    job.setTime(t.getWCET());
                } else {
                    if (this.getDistribution() == ProbabililtyDistribution.UNIFORM) {
                        job.setTime(t.getBCET() + rng.nextInt(t.getWCET() + 1 - t.getBCET()));
                    } else if (this.getDistribution() == ProbabililtyDistribution.GAUSSIAN) {
                        //TODO
                    } else if (this.getDistribution() == ProbabililtyDistribution.NONE) {
                        job.setTime(t.getWCET());
                    }
                }
                jobList.add(job);
            }
        }
        return jobList;
    }

    /**
     *
     * @return The current LCM multiplier
     */
    public int getLCMMultiplier() {
        return LCMMultiplier;
    }

    /**
     *
     * @param LCMMultiplier The new multiplier
     */
    public void setLCMMultiplier(int LCMMultiplier) {
        this.LCMMultiplier = LCMMultiplier;
    }

    /**
     * Accessor method for returning the current tasklist
     * @return The current tasklist
     */
    public TaskList getTaskList() {
        return tasklist;
    }
}
