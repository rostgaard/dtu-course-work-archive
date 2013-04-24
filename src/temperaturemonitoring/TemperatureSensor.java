package temperaturemonitoring;

import java.io.Serializable;
import java.rmi.Remote;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author krc
 */
public class TemperatureSensor extends Thread {

    Node owner;

    public TemperatureSensor(Node n) {
        this.owner = n;
    }

    @Override
    public void run() {
        this.owner.collectedMeasurements.add(new Temperature());
    }
}
