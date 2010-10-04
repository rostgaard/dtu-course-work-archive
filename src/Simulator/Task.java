/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Simulator;

import java.util.ArrayList;

/**
 *
 * @author krc
 */
public class Task {
    int period, WCET, BCET, WCRT, Deadline,phase,responseTime,priority = 0;
    String name, mappedTo = null;
    ArrayList<Job> jobList = new ArrayList<Job>();
    ArrayList<Integer> timeSlotList = new ArrayList<Integer>();
    
    public Task(String name, int period, int WCET, int BCET) {
        this.name = name;
        this.period = period;
        this.WCET = WCET;
        this.BCET = BCET;
    }

    public Task() {
        
    }

    public int getPhase() {
        return phase;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }


    public void setPhase(int phase) {
        this.phase = phase;
    }
    

    public String getMappedTo() {
        return mappedTo;
    }

    public void setMappedTo(String mappedTo) {
        this.mappedTo = mappedTo;
    }

    public int getDeadline() {
        return Deadline;
    }

    public void setDeadline(int Deadline) {
        this.Deadline = Deadline;
    }

    public int getWCRT() {
        return WCRT;
    }

    public void setWCRT(int WCRT) {
        this.WCRT = WCRT;
    }

    public ArrayList<Job> getJobList() {
        return jobList;
    }

    public void setJobList(ArrayList<Job> jobList) {
        this.jobList = jobList;
    }

    public int getBCET() {
        return BCET;
    }

    public void setBCET(int BCET) {
        this.BCET = BCET;
    }

    public int getWCET() {
        return WCET;
    }

    public void setWCET(int WCET) {
        this.WCET = WCET;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
    public int getPriority() {
        return this.priority;
    }
    
    void setPriority(int p) {
        this.priority = p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.getName() +
               " " + this.getResponseTime();
    }

    /**
     * Get formatted information
     * @return A string containing formatted information about the task
     */
    public String finfo() {
        return "Task Name  " + this.getName() +
               "\tWCET  " + this.getWCET() +
               "\tBCET  " + this.getBCET() +
               "\tPeriod  " + this.getPeriod() +
               "\tDeadline  " + this.getDeadline() +
               "\tMappedTo  " + this.getMappedTo() +
               "\tPriority  " + this.getPriority() +
               "\tWCRT  " + this.getResponseTime();
    }

    public ArrayList<Integer> getTimeSlotList() {
        return timeSlotList;
    }

    public boolean schedulable() {
        return this.getDeadline() > this.getResponseTime();
    }

}
