/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package networktools;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static networktools.TransmitterMode.CAUSAL;
import org.apache.commons.collections15.buffer.CircularFifoBuffer;
import temperaturemonitoring.Node;
import temperaturemonitoring.TemperatureNode;

/**
 *
 * @author krc
 */
public class Transmitter extends Thread implements Serializable {

    CircularFifoBuffer<TemperatureMessage> temperatureQueue;
    TransmitterMode mode = null;
    Node owner = null;
    private static final Logger logger = Logger.getLogger(Transmitter.class.getName());

    public Transmitter(TransmitterMode mode, Node owner) {
        this.owner = owner;
        this.temperatureQueue = new CircularFifoBuffer<>();
        this.mode = mode;
    }

    /**
     * Enqueues a temperature ready to be sent to the admin node.
     *
     * @param t The temperature to enqueue.
     */
    public synchronized void enqueue(TemperatureMessage t) throws RemoteException {
        //logger.log(Level.INFO, owner.ID() + "Enqueueing " + t.getPayload());

        this.temperatureQueue.add(t);
    }
    public synchronized void send() throws RemoteException {
        // Based on the mode, we either stall on error, or just continue to
        // to through the list.

        if (this.temperatureQueue.isEmpty()) {
            //logger.log(Level.INFO, "Nothing to send, bailing.");
            return;
        }

        for (TemperatureMessage message : this.temperatureQueue) {
            // TODO
            // lookup the node and deliver the message.

            TemperatureNode destinationNode = owner.lookupNode(message.getDestination());
            if (destinationNode == null) {
                System.out.println("Refusing to send to null node " + message.getDestination());
                return;
            }
            destinationNode.sendMeasurement(message);
            //System.out.println(this.owner.ID() + "Sent a message to " + destinationNode.ID());

        }

        temperatureQueue.clear();
    }

    @Override
    public void run() {
        try {
            // Try to dispatch the queue.
            this.send();
        } catch (RemoteException ex) {
            Logger.getLogger(Transmitter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
