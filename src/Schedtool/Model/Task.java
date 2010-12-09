package Schedtool.Model;

import java.util.ArrayList;

/**
 * Java model of a system task
 * @author krc
 */
public class Task {

    int blockingTime, period, WCET, BCET, WCRT, Deadline, phase, 
            responseTime, priority = 0;
    private int originalPriority;
    String name = null;
    ArrayList<Job> jobList = new ArrayList<Job>();
    ArrayList<Integer> timeSlotList = new ArrayList<Integer>();
    // For extensions
    private ArrayList<Integer> criticaltimeSlotList = new ArrayList<Integer>();
    ResourceList resourceList = new ResourceList();

    /**
     * Creates a new Task object
     * @param name Name of the task
     * @param period Period of the task
     * @param WCET Worst Case Execution Time
     * @param BCET Best Case Execution Time
     */
    public Task(String name, int period, int WCET, int BCET) {
        this.name = name;
        this.period = period;
        this.WCET = WCET;
        this.BCET = BCET;
    }

    /**
     *
     * @return
     */
    public ArrayList<Job> getJobList() {
        return jobList;
    }

    

    /**
     *
     * @param t
     */
    public void inheritPriority(Task t) {
        this.originalPriority = this.priority; // Store original priority
        this.priority = t.priority; // Inherit priority
    }

    /**
     *
     */
    public void restorePriority() {
        this.priority = this.originalPriority;
    }

    /**
     * Accessor method for updating the blocking time
     * @param blockingTime New value of blocking time
     */
    public void setBlockingTime(int blockingTime) {
        this.blockingTime = blockingTime;
    }

    /**
     * Empty constructor
     */
    public Task() {
    }

    /**
     * A list of resources demanded by the task
     * @return The current resource list
     */
    public ResourceList getResourceList() {
        return resourceList;
    }

    /**
     * The phase of the task
     * @return The phase
     */
    public int getPhase() {
        return phase;
    }

    /**
     * The current worst-case response time
     * @return Worst-case response time
     */
    public int getResponseTime() {
        return this.responseTime + this.blockingTime;
    }

    /**
     * Accessor method for updating the response time
     * @param responseTime New response time value
     */
    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    /**
     * Accessor method for updating the phase of the task (the start time of the
     * first instance/job)
     * @param phase New phase value
     */
    public void setPhase(int phase) {
        this.phase = phase;
    }

    /**
     * Accessor method for getting the current relative deadline
     * @return
     */
    public int getDeadline() {
        return Deadline;
    }

    /**
     * 
     * @param Deadline
     */
    public void setDeadline(int Deadline) {
        this.Deadline = Deadline;
    }

    /**
     * Accessor method for getting the current worst case response time
     * @return
     */
    public int getWCRT() {
        return WCRT + this.blockingTime;
    }

    /**
     * 
     * @param WCRT
     */
    public void setWCRT(int WCRT) {
        this.WCRT = WCRT;
    }

    /**
     * Accessor method for getting the current best case execution time
     * @return The best case execution time
     */
    public int getBCET() {
        return BCET;
    }

    /**
     *
     * @param BCET
     */
    public void setBCET(int BCET) {
        this.BCET = BCET;
    }

    /**
     *
     * @return
     */
    public int getWCET() {
        return WCET;
    }

    /**
     *
     * @param WCET
     */
    public void setWCET(int WCET) {
        this.WCET = WCET;
    }

    /**
     *
     * @return
     */
    public int getPeriod() {
        return period;
    }

    /**
     *
     * @param period
     */
    public void setPeriod(int period) {
        this.period = period;
    }

    /**
     *
     * @return
     */
    public int getPriority() {
        return this.priority;
    }

    /**
     * Update the priority of the task
     * @param p The new priority
     */
    public void setPriority(int p) {
        this.priority = p;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Prettyprint method
     * @return A formatted string containing name and response time
     */
    @Override
    public String toString() {
        return this.getName()
                + " " + this.getResponseTime();
    }

    /**
     * Get formatted information
     * @return A string containing formatted information about the task
     */
    public String fullinfo() {
        return "Task Name  " + this.getName()
                + "\tWCET  " + this.getWCET()
                + "\tBCET  " + this.getBCET()
                + "\tPeriod  " + this.getPeriod()
                + "\tDeadline  " + this.getDeadline()
                + "\tPriority  " + this.getPriority()
                + "\tWCRT  " + this.getResponseTime()
                + "\tBlockingTime  " + this.getBlockingtime();
    }

    /**
     * A list of timeslots
     * @return A list of timeslots occupied by the task
     */
    public ArrayList<Integer> getTimeSlotList() {
        return timeSlotList;
    }

    /**
     * Method for dermining {@link Types.Schedulabilty} of a task
     * @return True of the task set is schedulable with the response times
     *         calculated, false otherwise
     */
    public boolean schedulable() {
        return this.getDeadline() >= this.getResponseTime();
    }

    /**
     * The blocking time of a task is the worst case blocking the task can experience
     * @return The calculated blocking time the task
     */
    public int getBlockingtime(){
        return this.blockingTime;
    }

    /**
     * Calculates and updates the blocking time of the task based on the locking
     * policy
     */
    public void calculateBlockingTime() {
        int B_i = 0;
        int B_l_i = 0; // Sum the duration of the longest critical sections in each set of all critical sections of the lower-priority job

        for (Task j : Schedtool.Main.Config.tasklist) {
            if (j.getPriority() >= this.getPriority()) //Job_j = i+1 to n{    /* for each Job_j having priority less than Job_i */
            {
                continue;
            }

            int D_max = 0; // maximum delay
            for (Resource k : Schedtool.Main.Config.resourceList) // for k = 1 to m { /* for all semaphores */
            {
                //System.out.print(" k=" + (this.resourcelist.indexOf(k)+1));
                // System.out.println(" k.durationOf(j) =" + k.durationOf(j));
                if ((k.ceiling() >= this.getPriority()) && (k.durationOf(j) > D_max)) //if(C(S_k) >= P_i) &&  D_j_k  > D_max {
                {
                    D_max = k.durationOf(j);
                    //System.out.println(" D_max := " + k.durationOf(j));
                }


            }
            B_l_i = B_l_i + D_max;
            //System.out.println("B_l_i:" +B_l_i);

        }

        int B_s_i = 0;
        for (Resource k : Schedtool.Main.Config.resourceList) //for k = 1 to m { /* For all semaphores */
        {
            int D_max = 0;

            for (Task j : Schedtool.Main.Config.tasklist) // for j = i+1 to n { // for each J_j : P_j < P_i
            {
                if (j.getPriority() >= this.getPriority()) //Job_j = i+1 to n{    /* for each Job_j having priority less than Job_i */
                {
                    continue;
                }

                if ((k.ceiling() >= this.getPriority()) && (k.durationOf(j) > D_max)) //if(C(S_k) >= P_i) &&  D_j_k  > D_max {
                {
                    D_max = k.durationOf(j);
                }
            }
            B_s_i = B_s_i + D_max;
        }
        B_i = Math.min(B_l_i, B_s_i);

        this.blockingTime = B_i;
    }

}
