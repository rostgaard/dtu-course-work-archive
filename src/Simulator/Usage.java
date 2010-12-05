/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Simulator;

/**
 *
 * @author krc
 */
public class Usage {
    Task task;
    Resource resource;
    int criticalDuration;

    public Usage(Task t, Resource r, int criticalDuration) {
        this.task = t;
        this.resource = r;
        this.criticalDuration = criticalDuration;
    }

    

}
