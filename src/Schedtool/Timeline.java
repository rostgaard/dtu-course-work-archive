package Schedtool;

import Schedtool.Model.Task;
import java.util.ArrayList;

/**
 *
 * @author Kim Rostgaard Christensen
 */
public class Timeline extends ArrayList<Task>{

    /**
     *
     * @param t
     */
    public void execute(Task t) {
        this.add(t);
    }

    /**
     *
     * @param t
     * @return
     */
    public TaskList getTaskPreempting(Task t) {
        TaskList retList = new TaskList();
        //TODO find all task executed before the one supplied
        return retList;
    }

    @Override
    public String toString() {
        String retString = "";
        for(Task t: this) {
            retString += t.getName() +", ";
        }
        return retString;
    }
}
