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
public class Timeline extends ArrayList<Task>{

    public void execute(Task t) {
        this.add(t);
    }

    public TaskList getTaskPreempting(Task t) {
        TaskList retList = new TaskList();
        //TODO find all task executed before the one supplied
        return retList;
    }
}
