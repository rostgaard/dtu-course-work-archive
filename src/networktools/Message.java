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

    /**
     * Retrieve the destination pID of the message.
     *
     * @return The destination pID of the receiver.
     */
    public int getDestination() {
        return this.destinationProcess;
    }

    /**
     * Retrieve the origin pID of the message.
     *
     * @return The source PID of the sender, originating this message.
     */
    public TemperatureNode getSender() {
        return this.sender;
    }

    /**
     * Extracts the embedded vector clock of the message.
     *
     * @return The vector clock of the messsage.
     */
    public VectorClock getVectorClock() {
        return vc;
    }

    /**
     * Enables the message to be detected as a duplicate in a container.
     *
     * @param m The message to compare to.
     * @return Regular compareTo results.
     */
    @Override
    public int compareTo(Message m) {
        return this.uuid.compareTo(m.uuid);
    }

    /**
     * Stringify the object.
     *
     * @return The object in a debug-friendly format.
     */
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
