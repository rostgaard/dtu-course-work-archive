package Simulator.Model;

import Simulator.Main;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kim Rostgaard Christensen
 */
public class Job {

    private int release, time, ctime, responsetime, C = 0;
    private Task parentTask;
    private Usage resourceUsage;


    /**
     * Get the absolute deadline of a job the relative deadline is defined as
     * the phase + (occurence number -1)*
     *
     * @return The absolute deadline of a job
     */
    public int getAbsoluteDeadline() {

        return this.getRelease() + this.getRelativeDeadline();
    }

    /**
     *
     * @return
     */
    public Usage getResourceUsage() {
        return resourceUsage;
    }

    /**
     *
     * @param resourceUsage
     */
    public void setResourceUsage(Usage resourceUsage) {
        this.resourceUsage = resourceUsage;
        if (!(resourceUsage == null)) {
            ctime = this.resourceUsage.criticalDuration;
        }
    }

    /**
     *
     * @return
     */
    public int getCtime() {
        return ctime;
    }

    /**
     *
     * @param ctime
     */
    public void setCtime(int ctime) {
        this.ctime = ctime;
    }

    /**
     *
     * @return
     */
    public int getRelativeDeadline() {
        return this.getParentTask().getDeadline();
    }

    /**
     *
     * @return
     */
    public int getC() {
        return C;
    }

    /**
     *
     * @param C
     */
    public void setC(int C) {
        this.C = C;
    }

    /**
     *
     * @return
     */
    public int getResponsetime() {
        return responsetime;
    }

    /**
     *
     * @param responsetime
     */
    public void setResponsetime(int responsetime) {
        this.responsetime = responsetime;
    }

    /**
     *
     * @return
     */
    public int getRelease() {
        return release;
    }

    /**
     *
     * @param release
     */
    public void setRelease(int release) {
        this.release = release;
    }

    /**
     *
     * @return
     */
    public int getTime() {
        return time;
    }

    /**
     *
     * @param time
     */
    public void setTime(int time) {
        this.time = time;
        this.C = time;
    }

    /**
     * Try to execute the job
     * @param cycle The current simulation cycle, needed in order to create a timeline
     * @return True if the job was executed, and false otherwise
     */
    public boolean timeTick(int cycle) {
        // We don't need a resource, just execute
        if (this.resourceUsage == null) {
            this.getParentTask().timeSlotList.add(cycle);
            this.time--; //Just execute nomally;
            return true;
        }

        // We need a resource, (entering critical)
        if (this.ctime == this.resourceUsage.criticalDuration) {
            // If we have the resource, start executing
            if (this.resourceUsage.resource.hasResource(this)) {
                if (Main.Config.debug) {
                    System.out.println("Task " + this.parentTask.getName() + " enters critical " + " (resource "
                            + this.resourceUsage.resource.getName() + ")");
                }
            } // We do not have the resource, try to aquire it
            else {
                if (Main.Config.debug) {
                    System.out.println("Task " + this.parentTask.getName() + " wants to aquire " + " resource "
                            + this.resourceUsage.resource.getName());
                }

                if (!this.resourceUsage.resource.wait(this)) {
                    // resource is locked, we are blocked, do not execute
                    return false;
                }
            }
        } 
        //execute;
        this.getParentTask().timeSlotList.add(cycle);
        this.time--; 
        this.ctime--;

        // At this point, we can release the resource if it is no longer needed
        if (this.ctime == 0)
            this.resourceUsage.resource.signal(this);

        return true;
    }

    /**
     *
     * @return
     */
    public Task getParentTask() {
        return parentTask;
    }

    /**
     *
     * @param parentTask
     */
    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Job: " + this.getParentTask().getName()
                + " Priority: " + this.getParentTask().getPriority()
                + " Time: " + this.getTime()
                + " Release Time: " + this.getRelease() + "\n";
    }
}
