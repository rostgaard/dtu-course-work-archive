package Schedtool.Model;

import Schedtool.Main;
import Schedtool.Types.LockingPolicy;

/**
 * Class to model of a resource. The model supports semahor primitives and
 * priority inheritance protocol.
 * @author Kim Rostgaard Christensen
 */
public class Resource {

    private String name;
    private boolean priorityInheritance = false;
    private Job holder = null;
    private ReadyQueue waitQueue = new ReadyQueue();
    private UsageList usagelist = new UsageList();

    /**
     *
     * @param name
     * @param priorityInheritance
     */
    public Resource(String name, boolean priorityInheritance) {
        this.priorityInheritance = priorityInheritance;
        this.name = name;
    }

    /**
     *
     * @param name
     */
    public Resource(String name) {
        this.name = name;
    }

    /**
     * Returns the critical duration of the task supplied, in the resource
     * @param t The task object
     * @return The critical duration. If the task does not use the resource.
     *         This dureation is 0
     */
    public int durationOf(Task t) {
        int duration = 0;
        if (this.usagelist == null) {
            return 0;
        }
        for (Usage u : this.usagelist) {
            if (!u.task.equals(t)) {
                continue;
            }
            if (u.criticalDuration > duration) {
                duration = u.criticalDuration;
            }
        }
        return duration;

    }

    /**
     * Accessor method for getting the current usagelist
     * @return The current usagelist
     */
    public UsageList getUsageList() {
        return usagelist;
    }

    /**
     * The ceiling is the defined as max(Priority_j : Semaphor_k in Task_set requesting Semaphor_k)
     * @return The priority of the highest-priority task that uses this resource
     */
    public int ceiling() {
        int priority = 0;
        if (this.usagelist.isEmpty()) // No tasks use this resource
        {
            return priority;
        }
        for (Usage u : this.getUsageList()) {
            if (u.task.priority > priority) {
                priority = u.task.priority;
            }
        }
        return priority;
    }

    /**
     * Method for indicating wheter a job is currently holding a resource
     * @param j The job inquestioned
     * @return True if the job is currently holding the resource, false otherwise
     */
    public boolean hasResource(Job j) {
        if (this.holder == null) {
            return false;
        }
        if (this.holder.equals(j)) {
            return true;
        } else {
            return false;


        }
    }

    /**
     * This is the wait primitive of the resource. It lives by the simple
     * semaphor rules:
     * <p>
     * If the resource is free, it becomes locked and the holder field is
     * updated to correspond to the job calling
     * </p>
     * <p>
     * If the resource is locked, the calling task is blocked and stored in a
     * wait queue. Furthermore, if priority inheritance is enabled; the current
     * holder of the resource inherits the priority of the job just blocked.
     * </p>
     * @param j The calling job
     * @return True if the job got the resource, false if it blocked on the
     *         resource
     */
    public boolean wait(Job j) {
        if (this.holder == null) {
            if (Main.Config.debug) {
                System.out.println("Task " + j.getParentTask().getName() + " takes "
                        + this.getName());
            }
            // Aquire the resource
            this.holder = j;
            return true;

        } else {
            if (Main.Config.debug) {
                System.out.println("Task " + j.getParentTask().getName() + " blocked on "
                        + this.getName());
            }
            // Block on resource
            this.waitQueue.add(j);
            Main.Config.readyQueue.remove(j);

            // For priority inheritance protocol:
            if (this.priorityInheritanceEnabled()) {
                this.holder.getParentTask().inheritPriority(j.getParentTask());
            }

            return false;
        }
    }

    /**
     * This is the signal primitive of the resource. It lives by the simple
     * semaphor rules:
     * <p>
     * If the queue of the resource is empty, resource is unlocked.
     * </p>
     * <p>
     * If the queue is not empty, the highest-priority task in the queue takes
     * the resource.
     * </p>
     * <p>
     * No matter what; if priority inheritance is enabled, the original holder
     * is restored to its original priority.
     * </p>
     * @param j The job releasing the resource
     */
    public void signal(Job j) {
        if (Main.Config.debug) {
            System.out.println("Task " + j.getParentTask().getName() + " releases "
                    + this.getName());
        }

        // If no one is waiting on the resource, just release it
        if (this.waitQueue.isEmpty()) {
            // Free the resource
            this.holder = null;
        // When other tasks are waiting, a little more work needs to be done
        } else {
            // For priority inheritance protocol:
            if (this.priorityInheritanceEnabled()) {
                this.holder.getParentTask().restorePriority();
            }
            // Free the resource
            this.holder = null;

            // Pass the resource to the highest priority job
            Job newholder = this.waitQueue.getHighestPriorityJob();
            this.holder = newholder;
            this.waitQueue.remove(newholder);

            if (Main.Config.debug) {
                System.out.println("Task " + newholder.getParentTask().getName() + " aquires "
                        + this.getName() + " from waitqueue");
            }
            //Put the job back into the system ready queue
            Main.Config.readyQueue.add(newholder);
        }
    }

    /**
     * Gets the name of the resource
     * @return The current name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to determine wheter priority inheritance is enabled for the
     * resource.
     * @return True if priority inheritance is enabled, false otherwise
     */
    public boolean priorityInheritanceEnabled() {
        //return this.priorityInheritance;
        return (Main.Config.lockingPolicy==LockingPolicy.PRIORITY_INHERITANCE);
    }
}
