/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package networktools;

import temperaturemonitoring.Node;
import temperaturemonitoring.TemperatureNode;

/**
 *
 * @author krc
 */
public class NewAdminMessage extends Message {

    private int proposedAdmin;

    public NewAdminMessage(int proposedAdmin, int destination, TemperatureNode sender) {
        super(destination, sender);
        this.proposedAdmin = proposedAdmin;
    }

    public int getPayload() {
        return this.proposedAdmin;
    }
}
