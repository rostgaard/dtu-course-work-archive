/*
 * TODO
 */
package networktools;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.collections15.buffer.CircularFifoBuffer;
import temperaturemonitoring.Node;
import temperaturemonitoring.TemperatureNode;

/**
 *
 * @author Kim Rostgaard Christensen
 */
public class Transmitter extends Thread implements Serializable {

    CircularFifoBuffer<TemperatureMessage> temperatureQueue;
    TransmitterMode mode = null;
    Node owner = null;
    private static final Logger logger = Logger.getLogger(Transmitter.class.getName());
    private boolean paused = false;

    public Transmitter(TransmitterMode mode, Node owner) {
        this.owner = owner;
        this.temperatureQueue = new CircularFifoBuffer<>();
        this.mode = mode;
    }

    public void pauseTransmit() {
        this.paused = true;
    }

    public void resumeTransmit() {
        this.paused = false;
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
                // Classic comment; this should never happen.
                System.out.println("Refusing to send to null node " + message.getDestination());
                return;
            }
            destinationNode.sendMeasurement(message);
            this.owner.incrementVectorClock();
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
