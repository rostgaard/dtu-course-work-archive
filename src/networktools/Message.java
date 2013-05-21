/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package networktools;

import com.sun.org.apache.xpath.internal.operations.Equals;
import java.io.Serializable;
import java.util.UUID;
import temperaturemonitoring.Node;
import temperaturemonitoring.TemperatureNode;
import toolset.vectorclock.VectorClock;


/**
 *
 * @author krc
 */
public abstract class Message implements Serializable {

    private UUID uuid;
    private int destinationProcess;
    private TemperatureNode sender;
    private VectorClock vc;

    public Message(int destinationProcess, TemperatureNode sender) {
        this.uuid = UUID.randomUUID();
        this.destinationProcess = destinationProcess;
        this.sender = sender;
    }

    public int getDestination() {
        return this.destinationProcess;
    }

    public TemperatureNode getSender() {
        return this.sender;
    }

    public VectorClock getVectorClock() {
        return vc;
    }

    public boolean equals(Message m) {
        return this.uuid == m.uuid;
    }
}
