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
