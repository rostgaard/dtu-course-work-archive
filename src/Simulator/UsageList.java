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
class UsageList extends ArrayList<Usage> {

    public Usage find(String name) {
        Usage found = null;
        for(Usage u: this) {
            if(u.task.getName().equals(name))
                found = u;
        }
        return found;
    }

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
