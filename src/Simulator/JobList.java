/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Simulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author krc
 */
public class JobList extends ArrayList<Job>{

    public void sort() {
        Collections.sort(this, new JobComparator());
    }

    public Job getWorstCaseResponseTimeJob() {
        Job j1 = new Job();
        for(Job j2: this) {
            if(j1.getResponsetime() < j2.getResponsetime()) {
                j1 = j2;
            }
        }
        return j1;
    }


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

class JobComparator implements java.util.Comparator {

 public int compare(Object obj1, Object obj2) {

     Job j1 = (Job)obj1;
     Job j2 = (Job)obj2;

  if(j1.getRelease() == j2.getRelease()) {
      double priority = j2.getParentTask().getPriority() - j1.getParentTask().getPriority();
      if (priority < 0) {
          return -1;
      }
      else if (priority > 0){
          return 1;
      }
      else return 0;
 } else
     return j1.getRelease() - j2.getRelease();
}

}
