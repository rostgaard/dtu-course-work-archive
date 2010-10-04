/*
 * Response time analysis
 */

package Simulator;

/**
 *
 * @author Kim Rostgaard Christensen
 */
public class DeadlineMonotonicAnalysis {
    public static final boolean UNSCHEDULABLE = false;
    public static final boolean SCHEDULABLE = true;
    
    TaskList tasklist = null;

    public DeadlineMonotonicAnalysis(TaskList tl) {
        this.tasklist = tl;
        this.initializePriorities();
    }

    private void initializePriorities() {
        for (Task t: tasklist) {
            t.setPriority(tasklist.getLCMofPeriods()/t.getDeadline());
        }
    }

    // Note that the algorithm assumes that the tasks are sorted based on their priority

    public boolean analyse() {
        // Sort the task set
        tasklist.sortByPriority();

        //DM guarantee(Γ)
        //for all τi ∈ Γ do
        /*
         * By running through the task set reverse, we start with the task
         * having the lowest priority
         */
        for (int i = 0; i < tasklist.size() ;i++) {
            Task task = tasklist.get(i);
            //System.out.println("Task name: " +task.getName() +" ");
            //I = 0;
            int I = 0;
            //repeat
            do {
                //R = I + Ci;

                task.setResponseTime(I + task.getWCET());

                //System.out.println( " Responsetime: " + task.getResponseTime() +
                 //           " Deadline: " + task.getDeadline() +
                //          " i: " + i);
                //if R > Di then
                    
                if (task.getResponseTime() > task.getDeadline()) {
                    return UNSCHEDULABLE;
                }
                // I = SUM(J=1 to i-1| ceiling(R/taskJ)*J.WCET)
                I = 0;
                for (int j = 0; j < i; j++) {
                    /*System.out.println("  Adding Task:" + tasklist.get(j).getName() +"  j: " +j + " i: " +i
                            +" ciel(" +task.getResponseTime() + " / "
                                      + tasklist.get(j).getPeriod() + ") * "
                                      + tasklist.get(j).getWCET() + " = "
                            + Math.ceil(
                            (double)task.getResponseTime()/
                            (double)tasklist.get(j).getPeriod())
                            *tasklist.get(j).getWCET()
                            );*/
                    I += Math.ceil(
                            (double)task.getResponseTime()/
                            (double)tasklist.get(j).getPeriod())
                            *tasklist.get(j).getWCET();
                }

              // until I + Ci = R
              //System.out.println("  Ending responsetime: " +task.getResponseTime() + " and I+wect: " + I + " " +task.getWCET() );
            } while(I + task.getWCET() != task.getResponseTime());
        }
        return SCHEDULABLE;
    }
}
