/*
 * Rate monotonic analysis
 *
 */

package Simulator;

import Simulator.Types.ProbabililtyDistribution;
import Simulator.Types.Schedulability;
import java.util.Random;

/**
 *
 * @author krc
 */
public class RateMonotonicSimulation {

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
    public RateMonotonicSimulation(int lcmm, TaskList tl, ProbabililtyDistribution dist) {
        this.tasklist = tl;
        this.LCMMultiplier = lcmm;
        this.distribution = dist;
    }

    public ProbabililtyDistribution getDistribution() {
        return distribution;
    }

    public void setDistribution(ProbabililtyDistribution distribution) {
        this.distribution = distribution;
    }


    /**
     * Method for initializing the priorities. Redundant as this is also done on
     * every analyse() call. Provided for possible extentions
     */
    private void initializePriorities() {
        for (Task t: tasklist) {
            t.setPriority(tasklist.getLCMofPeriods()/t.getPeriod());
        }
    }

    /**
     *
     * @return
     */
    public Schedulability simulate() {
        Random rng  = new Random();
        Timeline timeline = new Timeline();
        

        JobList jobList = new JobList();
        /* We run the simulation for a LCM of periods number of cycles */
        int num_cycles = tasklist.getLCMofPeriods();

        /* Initialize jobs */
        for ( Task t:tasklist) {
            /* Initialize priorities */
            t.setPriority(num_cycles/t.getPeriod());

            for (int j=1; j<= (num_cycles/t.getPeriod());j++) {
                Job job = new Job();
                t.jobList.add(job);
                job.parentTask = t;

                job.setRelease(t.getPeriod()*(j-1));

                if ((t.getWCET()-t.getBCET()) <=0) { // The random function complains when range is 0
                    job.setTime(t.getWCET());
                } else {
                    if (this.getDistribution() == ProbabililtyDistribution.UNIFORM ) {
                        job.setTime(t.getBCET()+rng.nextInt(t.getWCET()+1-t.getBCET()));
                    }
                    else if (this.getDistribution() == ProbabililtyDistribution.GAUSSIAN ){
                        //TODO
                    }
                    else if (this.getDistribution() == ProbabililtyDistribution.NONE) {
                        job.setTime(t.getWCET());
                    }
                }
                jobList.add(job);
            }
        }
        /* Sort jobqueue on start time and priority */
        //Collections.sort(jobQueue, new JobComparator(num_cycles));
        jobList.sort();

        ReadyQueue readyList = Main.Config.currentReadyQueue;

        /* The simulation itself starts here */
        for(int cycle= 0; cycle < num_cycles*this.getLCMMultiplier(); cycle++) {
            /* */
            readyList = jobList.getReadyJobs(readyList, cycle);

            /* Pick up the highest priority job */
            Job currentJob = readyList.getHighestPriorityJob();

            /* We account for idle cycle, by skipping them */
            if (currentJob == null) {
                tasklist.getIdleTask().getTimeSlotList().add(cycle);
                continue;
            }

            /* Current job time decrement */
            currentJob.timeTick(cycle);
            timeline.execute(currentJob.getParentTask());

            /* When the current job has finished, some information is harvested */
            if (currentJob.getTime() == 0) {
                /* Response time of an instance. It is the time (measured from
                   the release time) at which the instance is terminated */
                int currentWCRT = cycle-currentJob.getRelease();
                currentJob.setResponsetime(currentWCRT);
                /*   remember worst-case response */
                if(currentJob.getParentTask().getResponseTime() <= currentWCRT) {
                    currentJob.getParentTask().setResponseTime(currentWCRT);
                }
                /* Remove the job from ready list */
                readyList.remove(currentJob);

            }
        }

        for (Task t : tasklist) {
            //TODO find the case of the WCRT that broke the camels back - so to speak
            if(!t.schedulable())
                return  Schedulability.UNSCHEDULABLE;
        }
        return Schedulability.SCHEDULABLE;

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
     *
     * @return
     */
    public TaskList getTaskList() {
        return tasklist;
    }

    /**
     *
     * @param taskList
     */
    public void setTaskList(TaskList taskList) {
        this.tasklist = taskList;
    }

    /**
     *
     * @return
     */
    public String getSVGGraph() {
        return "";
    }

}
