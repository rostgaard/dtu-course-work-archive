/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package networktools;

import java.io.Serializable;
import java.util.ArrayList;
import static networktools.TransceiverMode.CAUSAL;
import temperaturemonitoring.Node;
import temperaturemonitoring.Temperature;
/**
 *
 * @author krc
 */
public class Transceiver extends Thread implements Serializable {

    ArrayList<TemperatureMessage> temperatureQueue;
    TransceiverMode mode = null;
    Node owner = null;

    public Transceiver(TransceiverMode mode, Node owner) {
        this.temperatureQueue = new ArrayList<>();
        this.mode = mode;
    }

    /**
     * Enqueues a temperature ready to be sent to the admin node.
     *
     * @param t The temperature to enqueue.
     */
    public synchronized void enqueue(TemperatureMessage t) {
        this.temperatureQueue.add(t);
    }
    public synchronized void send() {
        // Based on the mode, we either stall on error, or just continue to
        // to through the list.

        for (TemperatureMessage message : this.temperatureQueue) {
            // TODO
            // lookup the node and deliver the message.

            Node destinationNode = owner.lookupNode(message.getDestination());

            switch (this.mode) {
                case FIFO:
                    destinationNode.synchonousSend(message);
                    break;
                case CAUSAL:
                    destinationNode.asynchonousSend(message);
                    break;
                case TOTAL:
                    destinationNode.deliver(message);
                    break;
            }

        }
    }
}
