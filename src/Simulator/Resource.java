/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Simulator;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author krc
 */
public class Resource {
    String name;
    boolean priorityInheritance = false;
    Job owner = null;
    Queue<Job> waitQueue = new LinkedList<Job>();

    Resource(String name,boolean priorityInheritance) {
        this.priorityInheritance = priorityInheritance;
        this.name = name;
    }
    Resource(String name) {
        this.name = name;
    }

    public boolean aquire(Job j) {
        if(this.owner == null) {
            this.owner = j;
            return true;
        }
        else {
            this.waitQueue.offer(j);
            return false;
        }
    }

    public void free(Job j) {
        // Poll returns null on empty queue, effectively freeing the resource
        this.owner = this.waitQueue.poll();
        if(!(this.owner == null)) {
            //Put the job back into the ready queue
            Simulator.Config.getCurrentReadyQueue.enqueue(j);
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
