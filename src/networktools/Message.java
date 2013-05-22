/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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

    /**
     *
     * @param m
     * @return
     */
    public int compareTo(Message m) {
        return this.uuid.compareTo(m.uuid);
    }

    @Override
    public String toString() {
        try {
            return "Dest: " + this.destinationProcess + " source " + this.sender.ID();
        } catch (RemoteException ex) {
            Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
}
