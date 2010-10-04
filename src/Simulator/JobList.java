/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Simulator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author krc
 */
public class JobList extends ArrayList<Job>{


    public JobList getReadyJobs(int cycle) {
        JobList readyQueue = new JobList();
        for (Job j : this) {
            if (j.time !=  0 && j.getRelease() < cycle) {
                readyQueue.add(j);
            }
            else {
                continue;
            }
        }

        return readyQueue;
    }

    public Job highestPriority(int num_cycles) {
        Job highestPriorityJob = null;
        for (Job j : this) {
            if (highestPriorityJob ==  null ) {
                highestPriorityJob = j;
            }
            if(j.getParentTask().getPriority() >=
                       highestPriorityJob.getParentTask().getPriority())
                   highestPriorityJob = j;

        }

        return highestPriorityJob;
    }

    @Override
    public String toString (){
        String retString = "";
        for (Iterator<Job> iter = this.iterator(); iter.hasNext();) {
            Job j = iter.next();
             retString += j;
        }
        return retString;
    }
}
