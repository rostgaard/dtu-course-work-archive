package Simulator.Model;

import java.util.ArrayList;

/**
 * Class provided in order to keep track usages read from GraphML file.
 * @author Kim Rostgaard Christensen
 */
public class UsageList extends ArrayList<Usage> {

    /**
     * Lookup a usage of a task
     * @param name The name of the task
     * @return The usage object of the task or null
     */
    public Usage find(String name) {
        Usage found = null;
        for(Usage u: this) {
            if(u.task.getName().equals(name))
                found = u;
        }
        return found;
    }

    /**
     * Prettyprint method
     * @return Formatted string
     */
    @Override
    public String toString() {
        String retString = "";
        for (Usage u : this) {
            retString += "Task: " + u.task.getName() + " uses resource " + u.resource.getName() + " for " +
                    u.criticalDuration + " cycles\n";
        }
        return retString;

    }

}
