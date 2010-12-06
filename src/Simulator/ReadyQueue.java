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
public class ReadyQueue extends ArrayList<Job> {

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

    @Override
    public String toString() {
        String retstr = "";
        for(Job j : this) {
            retstr += j;
        }
        return retstr;
    }
}
