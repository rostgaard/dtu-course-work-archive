/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Simulator;

import java.util.Collections;
import java.util.Random;

/**
 *
 * @author krc
 */
public class RateMonotonicAnalysis {
    public static final boolean UNSCHEDULABLE = false;
    public static final boolean SCHEDULABLE = true;
    
    private TaskList tasklist = null;
    private int LCMMultiplier = 1;
    private boolean randomize = false;

    /**
     * Creates a new RM analysis object
     * 
     * @param lcmm Is the number of cylcles to run the simuation
     * @param tl The tasklist to run the analysis on
     * @param rand Should the executiont times be randomized or just assume
     *        WCET?
     */
    public RateMonotonicAnalysis(int lcmm, TaskList tl, boolean rand) {
        this.tasklist = tl;
        this.LCMMultiplier = lcmm;
        this.randomize = rand;
    }

    /**
     * Method for initializing the priorities. Redundant as this is also done on
     * every analyse() call.
     */
    private void initializePriorities() {
        for (Task t: tasklist) {
            t.setPriority(tasklist.getLCMofPeriods()/t.getPeriod());
        }
    }

    public boolean analyse() {
        Random rng  = new Random();
        Timeline timeline = new Timeline();

        JobList jobQueue = new JobList();
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
                    //job.setTime(t.getBCET()+rng.nextInt(t.getWCET()+1-t.getBCET()));
                    job.setTime(t.getWCET());
                }
                jobQueue.add(job);
            }
        }
        /* Sort jobqueue on start time and priority */
        //Collections.sort(jobQueue, new JobComparator(num_cycles));
        jobQueue.sort();

        /* The simulation itself starts here */
        for(int cycle= 1; cycle <= num_cycles; cycle++) {
            /* The readylist gets generated at every cycle, this eliminates the
               need to explicitly remove the jobs, on the cost more resources */
            JobList readyList = jobQueue.getReadyJobs(cycle);

            /* Pick up the highest priority job */
            Job currentJob = readyList.highestPriority(num_cycles);

            /* This accounts for idle cycle, by skipping them */
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
            }
        }

        for (Task t : tasklist) {
            //TODO find the case of the WCRT that broke the camels back so to speak
            if(!t.schedulable())
                return UNSCHEDULABLE;
        }
        return SCHEDULABLE;

    }

    public int getLCMMultiplier() {
        return LCMMultiplier;
    }

    public void setLCMMultiplier(int LCMMultiplier) {
        this.LCMMultiplier = LCMMultiplier;
    }

    public boolean isRandomize() {
        return randomize;
    }

    public void setRandomize(boolean randomize) {
        this.randomize = randomize;
    }

    public TaskList getTaskList() {
        return tasklist;
    }

    public void setTaskList(TaskList taskList) {
        this.tasklist = taskList;
    }




    public String getSVGGraph() {
        return "";
    }

}
