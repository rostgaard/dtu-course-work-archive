package temperaturemonitoring;

import java.io.Serializable;
import java.rmi.Remote;
import java.util.logging.Logger;
import networktools.Transceiver;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author krc
 */
public class TemperatureSensor extends Thread {

    private Node owner;
    private static final Logger logger = Logger.getLogger(TemperatureSensor.class.getName());


    public TemperatureSensor(Node n) {
        this.owner = n;
    }

    @Override
    public void run() {
        this.owner.addMeasurement(new Temperature());
    }
}
