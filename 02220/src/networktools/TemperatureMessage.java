/*
 * TODO
 */
package networktools;

import temperaturemonitoring.Node;
import temperaturemonitoring.Temperature;
import temperaturemonitoring.TemperatureNode;

/**
 *
 * @author Kim Rostgaard Christensen
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

    @Override
    public String toString() {
        return super.toString() + " Payload: " + this.payload;
    }
}
