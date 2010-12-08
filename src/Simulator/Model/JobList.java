package Simulator.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author Kim Rostgaard Christensen
 */
public class JobList extends ArrayList<Job>{

    /**
     *
     */
    public void sort() {
        Collections.sort(this, new JobComparator());
    }

    /**
     *
     * @param rq
     * @param cycle
     * @return
     */
    public ReadyQueue getReadyJobs(ReadyQueue rq, int cycle) {
        for (Job j : this) {
            if (j.getTime() !=  0 && j.getRelease() <= cycle) {
                rq.add(j);
            }
            // The JobQueue is sorted, so at this point we can abort
            else if (j.getRelease() > cycle) {
                break;
            }
            else {
                continue;
            }
        }
        // This is a workaround that can be solved by moving the method out of
        // the class
        for(Job j : rq ){
            this.remove(j);
        }
                
        return rq;
    }

    /**
     *
     * @param num_cycles
     * @return
     */
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

    /**
     *
     * @return
     */
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
