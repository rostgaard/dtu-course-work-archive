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
public class ReadyQueue extends LinkedList<Job> implements Queue<Job> {
    public boolean enqueue(Job j){
        return this.offer(j);
    }

    public Job dequeue() {
        return this.remove();
    }
}
