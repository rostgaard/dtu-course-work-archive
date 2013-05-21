package temperaturemonitoring;

import bootstrapping.ObservationServiceInterface;
import configuration.Configuration;
import java.io.Serializable;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
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
public class Node extends UnicastRemoteObject implements TemperatureNode {

    private int ID = -1;
    private int admin = 0;
    private boolean running = false;
    private Registry registry = null;
    private VectorClock vc = null;
    private static final Logger logger = Logger.getLogger(Node.class.getName());
    private static final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    private TemperatureMeasurementCollection collectedMeasurements = new TemperatureMeasurementCollection();
    // Periodic tasks.
    private Transceiver transceiver;
    private TemperatureSensor fixedrateTemperatureMonitor;
    private Stack<Message> messageBuffer = new Stack<>();

    /**
     * TODO
     *
     * @param t
     */
    public void addMeasurement(Temperature t) {
        logger.log(Level.INFO, "Adding measurement to " + this);
        //if (this.collectedMeasurements.add(t)) {
            this.vc.incrementClock(ID);
        this.transceiver.enqueue(new TemperatureMessage(t, this.admin, this));
        //}
    }

    private ObservationServiceInterface getMonitor() {
        ObservationServiceInterface monitor = null;
        try {
            monitor = (ObservationServiceInterface) registry.lookup(ObservationServiceInterface.class.getSimpleName());
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
        }
        return monitor;
    }

    private NodeList getMulticastGroup() {
        NodeList nodelist = new NodeList();

        for (int i = 0; i < Configuration.Number_Of_Nodes; i++) {
            nodelist.add(i);
        }

        return nodelist;
    }

    private boolean isAdmin() {
        return this.ID == this.admin;
    }

    /**
     * Promotes the node to an admin.
     */
    @Override
    public void promote() throws RemoteException {
        logger.log(Level.INFO, "Promoting node " + this.ID + " to admin.");
        
        this.getMonitor().clearConnections();
        // Start by b-multicasting the new admin number
        this.reliabeMulticast(this.getMulticastGroup(), new NewAdminMessage(this.ID, -1, this));
        
        this.admin = this.ID;
        this.getMonitor().newAdmin(this.ID);
    }

    public boolean hasRegistry() {
        return this.registry == null;
    }

    public void disconnectRegistry() {
        logger.log(Level.INFO, "Disconnected registry");
        this.registry = null;
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

                
                this.getMonitor().newNode(this.ID);
                
            } catch (AlreadyBoundException | AccessException ex) {
                logger.log(Level.SEVERE, null, ex);
            }

        } catch (RemoteException ex) {
            logger.log(Level.SEVERE, null, ex);
            this.registry = null;
        }
    }

    public Node(int pid, int numNodes) throws RemoteException {
        this.transceiver = new Transceiver(TransceiverMode.FIFO, this);
        this.vc = new VectorClock(numNodes);
        this.ID = pid;
    }

    public void addMulticastNode(int n) {
        //logger.log(Level.INFO, "Adding node " + n
        //       +  " to " + this.ID + "'s multicast group."
        // );
        this.getMulticastGroup().add(n);
    }

    public void start() {
        logger.log(Level.INFO, "Stating node " + this.ID);
        this.running = true;

        fixedrateTemperatureMonitor = new TemperatureSensor(this);
        transceiver = new Transceiver(TransceiverMode.FIFO, this);

        scheduler.scheduleAtFixedRate(this.fixedrateTemperatureMonitor, 0, 1, TimeUnit.SECONDS);

        scheduler.scheduleAtFixedRate(this.transceiver, 0, 200, TimeUnit.MILLISECONDS);

        try {
            if (!this.isAdmin()) {
                this.getMonitor().newConnection(ID, 0);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        /*        this.running = true;
         while (this.running) {            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
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
    public TemperatureNode lookupNode(Integer destination) {
        TemperatureNode node = null;
        try {
            node = (TemperatureNode) registry.lookup("/Process/" + destination);
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
    public VectorClock reliableDeliver(NewAdminMessage message) throws RemoteException {
        logger.log(Level.INFO, "R-deliver received message on node " + this.ID + " from " + message.getSender());

        if (!this.messageBuffer.contains(message)) { // The message is not already received.
            this.messageBuffer.add(message); // Buffer the message.

            if (message.getSender() != this) { // Skip the current node.
                //TODO
                //this.reliabeMulticast(multicastGroup, message);
            }

            // Deliver the message.
            this.admin = message.getPayload();

            if (this.ID != this.admin) {
                this.getMonitor().newConnection(this.ID, this.admin);
            }

            logger.log(Level.INFO, "R-delivered message to" + this.ID + " from " + message.getSender());
        }

        return this.vc;
    }

    private void basicMulticast(ArrayList<Integer> group, Message message) throws RemoteException {
        for (Integer node : group) {
            if (node != this.ID) { // Skip own ID.
                VectorClock remoteClock = this.lookupNode(node).basicDeliver(message);
                this.vc.merge(remoteClock);
            }

        }
    }

    private void basicMulticast(ArrayList<Integer> group, NewAdminMessage message) throws RemoteException {

        for (Integer node : group) {
            if (node != this.ID) { // Skip own ID.
                VectorClock remoteClock = this.lookupNode(node).basicDeliver(message);
                this.vc.merge(remoteClock);
                logger.log(Level.INFO, "Basic multicasting new admin request from " + this.ID + " to " + node);
            }
        }
    }

    private void reliabeMulticast(ArrayList<Integer> group, NewAdminMessage message) throws RemoteException {
        logger.log(Level.INFO, "Reliable multicast sending from " + this.ID);

        for (Integer node : group) {
            logger.log(Level.INFO, "Reliable multicast sending from " + this.ID + " to " + node);
            VectorClock remoteClock = this.lookupNode(node).reliableDeliver(message);
            this.vc.merge(remoteClock);

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
