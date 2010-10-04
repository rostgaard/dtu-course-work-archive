/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Simulator;

/**
 *
 * @author krc
 */
public class JobComparator implements java.util.Comparator {
    int cycles;

    public JobComparator(int cycles) {
        this.cycles =cycles;
    }

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
