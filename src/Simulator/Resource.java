/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Simulator;

import java.util.LinkedList;

/**
 *
 * @author krc
 */

public class Resource {
    private String name;
    private boolean priorityInheritance = false;
    private Job holder = null;
    private ReadyQueue waitQueue = new ReadyQueue();

    Resource(String name,boolean priorityInheritance) {
        this.priorityInheritance = priorityInheritance;
        this.name = name;
    }
    Resource(String name) {
        this.name = name;
    }

    public boolean hasResource(Job j){
        if(this.holder == null)
            return false;
        if(this.holder.equals(j))
            return true;
        else
            return false;
    }
/*
 * Wait primitive:
 *
 * From Buzzato hard real time:
 *  - If semaphore s is free, it becomes locked and the name of the executing
 *    task is stored in the holder field of the semaphore data structure.
 *
 *  - If semaphore s is locked, the executing task is blocked on the s semaphore
 *    queue, the semaphore identifier is stored in the lock field of the TCB, and
 *    its priority is inherited by the task that holds s. If such a task is blocked
 *    on another semaphore, the transitivity rule is applied. Then, the ready
 *    task with the highest priority is assigned to the processor.
 */
    public boolean wait(Job j) {
        if(this.holder == null) {
            if(Main.Config.debug)
            System.out.println("Task " + j.getParentTask().getName() +" takes "
                    + this.getName() );

            this.holder = j;
            return true;
        }
        else {
           if(Main.Config.debug)
            System.out.println("Task " + j.getParentTask().getName() +" blocked on "
                    + this.getName() );
            this.waitQueue.add(j);
            Main.Config.currentReadyQueue.remove(j);
            return false;
        }
    }

    /*
     * Signal primitive
     *
     * From Buzzato hard real time:
     *
     *  - If the queue of semaphore s is empty (that is, no tasks are blocked on 5),
     *    resource is unlocked.
     *
     *  - If the queue of semaphore s is not empty, the highest-priority task in the
     *    queue is awakened, its identifier is stored in s.holder, the active priority of
     *    the executing task is updated and the ready task with the highest priority
     *    is assigned to the processor.
     */
    
    public void signal(Job j) {
        this.holder = null; // Free the resource
        if(Main.Config.debug)
            System.out.println("Task " + j.getParentTask().getName() +" releases "
                    + this.getName() );

        if(!this.waitQueue.isEmpty()) {
            Job newholder = this.waitQueue.getHighestPriorityJob();
            this.holder = newholder ; // Pass the resource to the highest priority job
            this.waitQueue.remove(newholder);
                    if(Main.Config.debug)
            System.out.println("Task " + newholder.getParentTask().getName() +" aquires "
                    + this.getName() +" from waitqueue");
            Main.Config.currentReadyQueue.add(newholder); //Put the job back into the system ready queue
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasPriorityInheritance() {
        return this.priorityInheritance;
    }
}
