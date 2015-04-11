package Schedtool;

import Schedtool.Model.TaskList;
import Schedtool.Model.Task;
import Schedtool.Types.LockingPolicy;
import Schedtool.Types.Schedulability;

/**
 *
 * @author Kim Rostgaard Christensen
 */
public class ShedulabiltyTest {

    LockingPolicy lockingpolicy = LockingPolicy.NONE;
    TaskList tasklist = null;

    /**
     *
     * @param tl
     */
    public ShedulabiltyTest(TaskList tl) {
        this.tasklist = tl;
    }

    /**
     *
     * @param tl
     * @param lp
     */
    public ShedulabiltyTest(TaskList tl, LockingPolicy lp) {
        this.tasklist = tl;
        this.lockingpolicy = lp;
    }

    /**
     * This method initializes priorities according to Deadline Monotonic
     * priority. 1/Deadline - in this case, it is actually (1/Deadline)*Hyperperiod.
     */
    private void initializeDMPriorities() {
        for (Task t : tasklist) {
            t.setPriority(tasklist.getLCMofPeriods() / t.getDeadline());
        }
    }

    /**
     * This method initializes priorities according to Rate Monotonic
     * priority. 1/Period - in this case, it is actually (1/Period)*Hyperperiod.
     */
    private void initializeRMPriorities() {
        for (Task t : tasklist) {
            t.setPriority(tasklist.getLCMofPeriods() / t.getPeriod());
        }
    }

    /**
     * The algorithm is implemented according to Buzzato: Hard real-time systems
     * @return {@link Types.Schedulability}
     */
    // Note that the algorithm assumes that the tasks are sorted based on their priority
    public Schedulability analyse() {
        switch (Main.Config.prioritypolicy) {
            case DEADLINE_MONOTONIC:
                this.initializeDMPriorities();
                break;
            case RATE_MONOTONIC:
                this.initializeRMPriorities();
                break;
            case STATIC:
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }


        // Sort the task set
        tasklist.sortByPriority();

        for (Task t : this.tasklist) {
            t.calculateBlockingTime();
        }

        for (Task i : this.tasklist) {

            Task task = i;
            int I = 0;
            //repeat
            do {
                //R = I + Ci;
                task.setResponseTime(I + task.getWCET());

                //System.out.println("Task: " + task.getName()+ " Responsetime: " + task.getResponseTime());
                I = 0;
                for (Task j : this.tasklist) {
                    if (j.getPriority() <= task.getPriority()) {
                        continue;
                    }
                    /* System.out.println("  Adding Task:" + j.getName() +"  j: " +j + " i: " +i
                    +" ciel(" +task.getResponseTime() + " / "
                    + j.getPeriod() + ") * "
                    + j.getWCET() + " = "
                    + Math.ceil(
                    (double)task.getResponseTime()/
                    (double)j.getPeriod())
                     *j.getWCET()
                    );*/
                    I += Math.ceil(
                            (double) task.getResponseTime()
                            / (double) j.getPeriod())
                            * j.getWCET();
                }

                // until I + Ci = R
                //System.out.println("  Ending responsetime: " + task.getResponseTime() + " and I+wect: " + I + " " + task.getWCET());
            } while (I + task.getWCET() != task.getResponseTime());
        }

        switch (this.lockingpolicy) {
            case NONE:
                // Only guarantee schedulability if no resources is in use
                if (Main.Config.usagelist.isEmpty()) {
                    for (Task t : tasklist) {
                        if (!t.schedulable()) {
                            return Schedulability.UNSCHEDULABLE;
                        }
                    }
                    return Schedulability.SCHEDULABLE;
                }
                break;

            case PRIORITY_INHERITANCE:
                for (Task t : this.tasklist) {
                    //t.calculateBlockingTime();
                    if (!t.schedulable()) {
                        return Schedulability.UNSCHEDULABLE;
                    }
                }
                return Schedulability.SCHEDULABLE;

            // No yet implemented
            default:
                throw new UnsupportedOperationException("Not yet implemented");


        }
        return Schedulability.UNSCHEDULABLE;
    }
}
