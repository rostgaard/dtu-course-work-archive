/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package networktools;

import temperaturemonitoring.Node;
import temperaturemonitoring.Temperature;

/**
 *
 * @author krc
 */
public class TemperatureMessage extends Message {

    private Temperature payload;

    public TemperatureMessage(Temperature payload, int destination, Node sender) {
        super(destination, sender);
        this.payload = payload;
    }

    public Temperature getPayload() {
        return this.payload;
    }
}
