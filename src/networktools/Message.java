/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package networktools;

import java.io.Serializable;
import temperaturemonitoring.Node;
import toolset.vectorclock.VectorClock;

/**
 *
 * @author krc
 */
public abstract class Message implements Serializable {
    private int destinationProcess;
    private Node sender;
    private VectorClock vc;

    public Message(int destinationProcess, Node sender) {
        this.destinationProcess = destinationProcess;
        this.sender = sender;
    }

    public int getDestination() {
        return this.destinationProcess;
    }

    public Node getSender() {
        return this.sender;
    }

    public VectorClock getVectorClock() {
        return vc;
    }
}
