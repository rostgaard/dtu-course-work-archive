/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package networktools;

import temperaturemonitoring.Node;
import temperaturemonitoring.Temperature;
import temperaturemonitoring.TemperatureNode;

/**
 *
 * @author krc
 */
public class TemperatureMessage extends Message {

    private Temperature payload;

    public TemperatureMessage(Temperature payload, int destination, TemperatureNode sender) {
        super(destination, sender);
        this.payload = payload;
    }

    public Temperature getPayload() {
        return this.payload;
    }
}
