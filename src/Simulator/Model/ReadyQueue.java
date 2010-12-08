package Simulator.Model;

import java.util.ArrayList;

/**
 * The readyqueue dispatches jobs based on a static priority. This can be
 * extended to include dispatching of dynamical priority tasks.
 * @author Kim Rostgaard Christensen
 */
public class ReadyQueue extends ArrayList<Job> {

    /**
     *
     * @return
     */
    public Job getHighestPriorityJob() {
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
    public String toString() {
        String retstr = "";
        for(Job j : this) {
            retstr += j;
        }
        return retstr;
    }
}
