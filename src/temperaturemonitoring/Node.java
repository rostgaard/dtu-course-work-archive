package temperaturemonitoring;

import bootstrapping.ObservationServiceInterface;
import java.io.Serializable;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import toolset.vectorclock.VectorClock;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import networktools.Message;
import networktools.NewAdminMessage;
import networktools.TemperatureMessage;
import networktools.Transceiver;
import networktools.TransceiverMode;
/*
 * Represenation of a network node.
 */
/**
 *
 * @author Kim Rostgaard Christensen
 */
public class Node extends Thread implements TemperatureNode, Serializable {

    private int ID = -1;
    private int admin = 0;
    private boolean running = false;
    private Registry registry = null;
//    private Initiator initiator = null;
    private boolean isAdmin = false;
    private VectorClock vc = null;
    private static final Logger logger = Logger.getLogger(Node.class.getName());
    private static final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);
    private bootstrapping.ObservationServiceInterface monitor;
    private TemperatureMeasurementCollection collectedMeasurements = new TemperatureMeasurementCollection();
    // Periodic tasks.
    private Transceiver transceiver;
    private TemperatureSensor fixedrateTemperatureMonitor;
    private Stack<Message> messageBuffer = new Stack<>();
    private ArrayList<Integer> multicastGroup = new ArrayList<>();

    /**
     * TODO
     *
     * @param t
     */
    public void addMeasurement(Temperature t) {
        //logger.log(Level.INFO, "Adding measurement to " + this);
        //if (this.collectedMeasurements.add(t)) {
            this.vc.incrementClock(ID);
        this.transceiver.enqueue(new TemperatureMessage(t, this.admin, this));
        //}
    }

    /**
     * Promotes the node to an admin.
     */
    public void promote() {
        logger.log(Level.INFO, "Promoting node " + this.ID + " to admin.");

        // Start by b-multicasting the new admin number

        this.basicMulticast(multicastGroup, new NewAdminMessage(this.ID, -1, this));

        this.isAdmin = true;
    }

    public boolean hasRegistry() {
        return this.registry == null;
    }

    public void disconnectRegistry() {
        logger.log(Level.INFO, "Disconnected registry");
        this.registry = null;
        // Restart the initiator
        //this.initiator.startListening();
    }

    private void locateRegistry() throws InterruptedException {
        //this.initiator = new Initiator();
        //this.initiator.startListening();

        System.out.println("Started");
        while (true) {
            //initiator.RegistryBeacon();
            Thread.sleep(1000);
        }
    }

    /**
     * Connect a node to the RMI registry, in order for us to initiate the
     * system.
     *
     * @param host
     * @throws InterruptedException
     */
    public void connectRegistry(String host) throws InterruptedException {
        try {
            this.registry = java.rmi.registry.LocateRegistry.getRegistry(host);
            try {
                logger.log(Level.FINEST, "Registring interfaces.");
                //  Connecting the remote interfaces.

                
                registry.bind("/Process/" + new Integer(this.ID).toString(), this);
                //logger.log(Level.INFO, "Registring " + "/Process/" + new Integer(this.ID).toString());

                try {
                    monitor = (ObservationServiceInterface) registry.lookup(ObservationServiceInterface.class.getSimpleName()); //this.initiator.stopListening();

                    // Notify the Networkmodel that this node joined the network.
                    monitor.newNode(this.ID);
                } catch (NotBoundException | AccessException ex) {
                    Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (AlreadyBoundException | AccessException ex) {
                Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (RemoteException ex) {
            Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
            this.registry = null;
        }
    }

    public Node(int pid, int numNodes) throws InterruptedException {
        this.transceiver = new Transceiver(TransceiverMode.FIFO, this);
        this.vc = new VectorClock(numNodes);
        this.ID = pid;
    }

    public void addMulticastNode(int n) {
        //   logger.log(Level.INFO, "Adding node " + n
        //          +  " to " + this.ID + "'s multicast group."
        //  );
        this.multicastGroup.add(n);
    }

    @Override
    public void run() {
        logger.log(Level.INFO, "Stating node " + this.ID);
        this.running = true;

        fixedrateTemperatureMonitor = new TemperatureSensor(this);
        transceiver = new Transceiver(TransceiverMode.FIFO, this);

        scheduler.scheduleAtFixedRate(this.fixedrateTemperatureMonitor, 0, 1, TimeUnit.SECONDS);

        scheduler.scheduleAtFixedRate(this.transceiver, 0, 200, TimeUnit.MILLISECONDS);

        try {
            if (!this.isAdmin) {
                this.monitor.newConnection(ID, 0);
            } else {
                // TEST
                this.reliableMulticast(this.multicastGroup, new TemperatureMessage(null, -1, this));
            }
        } catch (RemoteException ex) {
            Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
        }

        

        /*        while (this.running) {            try {
                Thread.sleep(1000);
                logger.log(Level.INFO, this + " " + this.vc);
            } catch (InterruptedException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
            /*
             try {
                monitor.newConnection(ID, ID + 1);
            } catch (RemoteException ex) {
                logger.log(Level.SEVERE, null, ex);
            }

             

         }*/
    }

    /**
     * TODO
     *
     * @return
     */
    @Override
    public Temperature latestMeasurement() {
        return this.collectedMeasurements.get(this.collectedMeasurements.size() - 1);
    }

    /**
     * TODO
     *
     * @return
     */
    @Override
    public String toString() {
        return Node.class.getSimpleName() + " " + this.ID + " " + this.vc;
    }

    @Override
    public Node lookupNode(Integer destination) {
        Node node = null;
        try {
            node = (Node) registry.lookup("/Process/" + destination);
        } catch (RemoteException | NotBoundException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return node;
    }

    @Override
    public VectorClock synchonousSend(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VectorClock asynchonousSend(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VectorClock basicDeliver(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VectorClock reliableDeliver(Message message) {
        if (!this.messageBuffer.contains(message)) { // The message is not already received.
            this.messageBuffer.add(message); // Buffer the message.

            if (message.getSender() != this) { // Skip the current node.
                //TODO
                //reliableMulticast(this.multicastGroup, message);
            }

            // Deliver the message.
            logger.log(Level.INFO, "R-delivered message to" + this.ID + " from " + message.getSender());
        }

        return this.vc;
    }

    private void basicMulticast(ArrayList<Integer> group, Message message) {
        for (Integer node : group) {
            if (node != this.ID) { // Skip own ID.
                VectorClock remoteClock = this.lookupNode(node).basicDeliver(message);
                this.vc.merge(remoteClock);
            }

        }
    }

    private void basicMulticast(ArrayList<Integer> group, NewAdminMessage message) {

        for (Integer node : group) {
            if (node != this.ID) { // Skip own ID.
                VectorClock remoteClock = this.lookupNode(node).basicDeliver(message);
                this.vc.merge(remoteClock);
                logger.log(Level.INFO, "Basic multicasting new admin request from " + this.ID + " to " + node);
            }
        }
    }

    private void reliableMulticast(ArrayList<Integer> group, TemperatureMessage message) {

        for (Integer node : group) {
            VectorClock vc = this.lookupNode(node).reliableDeliver(message);
            this.vc.merge(vc);

            logger.log(Level.INFO, "Reliable multicast sending from " + this.ID + " to " + node);

        }
    }

    @Override
    public VectorClock basicDeliver(TemperatureMessage message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VectorClock basicDeliver(NewAdminMessage message) {
        // Send to every process that has not yet received the proposed new admin
        return this.vc;
    }
}
