package Simulator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author krc
 */
public class Job {

    int release,time,responsetime,C,occurence = 0;
    Task parentTask;

    /**
     * Get the absolute deadline of a job the relative deadline is defined as
     * the phase + (occurence number -1)*
     *
     * @return The absolute deadline of a job
     */
    public int getAbsoluteDeadline(){

        return this.getRelease() + this.getRelativeDeadline();
    }



    public int getRelativeDeadline(){
        return this.getParentTask().getDeadline();
    }

    public int getC() {
        return C;
    }

    public void setC(int C) {
        this.C = C;
    }

    public int getResponsetime() {
        return responsetime;
    }

    public void setResponsetime(int responsetime) {
        this.responsetime = responsetime;
    }

    public int getRelease() {
        return release;
    }

    public void setRelease(int release) {
        this.release = release;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
        this.C = time;
    }

    public void timeTick(int cycle) {
        if(this.getParentTask() == null)
            System.out.println("nooo");
        else
        this.getParentTask().timeSlotList.add(cycle);
        this.time = this.time-1;
    }

    public Task getParentTask() {
        return parentTask;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }

    @Override
    public String toString() {
        return "Job: " + this.getParentTask().getName() +
                          " Priority: " + this.getParentTask().getPriority() +
                          " Time: " + this.getTime() +
                          " Release Time: " + this.getRelease() + "\n" ;
    }


}
