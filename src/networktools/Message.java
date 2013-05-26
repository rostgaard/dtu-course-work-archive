/*
 * Common structure of a message.
 */
package networktools;


import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import temperaturemonitoring.TemperatureNode;
import toolset.vectorclock.VectorClock;

/**
 *
 * @author Kim Rostgaard Christensen
 */
public abstract class Message implements Serializable, Comparable<Message> {

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

    /**
     *
     * @param m
     * @return
     */
    @Override
    public int compareTo(Message m) {
        return this.uuid.compareTo(m.uuid);
    }

    @Override
    public String toString() {
        try {
            return "UUID:" + this.uuid + "Dest: " + this.destinationProcess + " source " + this.sender.ID();
        } catch (RemoteException ex) {
            Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
}
