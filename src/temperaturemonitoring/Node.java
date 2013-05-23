package temperaturemonitoring;

import bootstrapping.ObservationServiceInterface;
import configuration.Configuration;
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
import networktools.Transmitter;
import networktools.TransmitterMode;
/*
 * Represenation of a network node.
 */
/**
 *
 * @author Kim Rostgaard Christensen
 */
public class Node extends UnicastRemoteObject implements TemperatureNode {

    public static final int Null_ID = -1;

    private int ID = Null_ID;
    private int admin = Null_ID;
    private Registry registry = null;
    private VectorClock vc = null;
    private static final Logger logger = Logger.getLogger(Node.class.getName());
    private static final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(2);
    private boolean started = false;
    private Stack<Message> messageBuffer = new Stack<>();
    // Periodic tasks.
    private Transmitter transceiver;
    private TemperatureSensor fixedrateTemperatureMonitor;
    private TemperatureMeasurementCollection measurements = new TemperatureMeasurementCollection(Configuration.Number_Of_Nodes);


    /**
     * Calculates and returns the latest average.
     *
     * @return @throws RemoteException
     */
    @Override
    public double latestAverage() throws RemoteException {
        System.out.println("Node " + this.ID + " Got average request.");
        return measurements.latestAverage();
        //return 0.0;
    }

    /**
     * TODO
     *
     * @param t
     */
    public void addMeasurement(Temperature t) throws RemoteException {
        
        this.vc.incrementClock(ID);
        if (this.isAdmin()) {
            this.measurements.add(ID, t);
        } else {
            this.transceiver.enqueue(new TemperatureMessage(t, this.admin, this));
        }
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
        
        this.getMonitor().clearConnections();
        // Start by b-multicasting the new admin number
        this.reliabeMulticast(this.getMulticastGroup(), new NewAdminMessage(this.ID, -1, this));
        
        logger.log(Level.INFO, "Promoting node " + this.ID + " to admin.");
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
        this.transceiver = new Transmitter(TransmitterMode.FIFO, this);
        this.vc = new VectorClock(numNodes);
        this.ID = pid;
    }

    public void addMulticastNode(int n) {
        //logger.log(Level.INFO, "Adding node " + n
        //       +  " to " + this.ID + "'s multicast group."
        // );
        this.getMulticastGroup().add(n);
    }

    @Override
    public void start() throws RemoteException {
        if (this.started) {
            return;
        }
        logger.log(Level.INFO, "Stating node " + this.ID);

        try {
            if (!this.isAdmin()) {
                this.getMonitor().newConnection(ID, 0);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.started = true;
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

        if (destination == Null_ID) {
            return node; // null;
        }

        try {
            node = (TemperatureNode) registry.lookup("/Process/" + destination);
        } catch (RemoteException | NotBoundException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return node;
    }

    /**
     *
     * @param message
     * @return
     */
    @Override
    public VectorClock sendMeasurement(TemperatureMessage message) throws RemoteException {
        //logger.log(Level.INFO, "Node " + this + "received " + message.getPayload());
        //  If we are not the admin, route the package.
        if (!isAdmin()) {
            logger.log(Level.INFO, "Node " + this + "received " + message + " not for me, re-routing to " + this.admin);
            this.transceiver.enqueue(new TemperatureMessage(message.getPayload(), this.admin, this));
        } else {
            //logger.log(Level.INFO, "Node " + this + "adds message to collection.");
            this.measurements.add(message.getSender().ID(), message.getPayload());
        }


        return this.vc;
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

            // B-Multicast
            for (Integer node : this.getMulticastGroup()) {
                if (message.getSender() != this) { // Skip the current node.
                    this.lookupNode(node).basicDeliver(message);
                }
            }

            // Deliver the message.
            System.out.println("Node " + this.ID + " Changes admin to " + message.getPayload());
            
            this.admin = message.getPayload();

            if (this.ID != this.admin) {
                this.getMonitor().newConnection(this.ID, this.admin);
            }

            //logger.log(Level.INFO, "R-delivered message to" + this.ID + " from " + message.getSender());
        }

        return this.vc;
    }

    public void startTasks() {

        fixedrateTemperatureMonitor = new TemperatureSensor(this);
        transceiver = new Transmitter(TransmitterMode.FIFO, this);

        scheduler.scheduleAtFixedRate(this.fixedrateTemperatureMonitor, 0, 1, TimeUnit.SECONDS);

        scheduler.scheduleAtFixedRate(this.transceiver, 1, 1000, TimeUnit.MILLISECONDS);
    }

    private void basicMulticast(ArrayList<Integer> group, Message message) throws RemoteException {
        for (Integer node : group) {
            if (node != this.ID) { // Skip own ID.
                TemperatureNode lnode = this.lookupNode(node);
                if (lnode == null) {
                    return;
                }
                VectorClock remoteClock = lnode.basicDeliver(message);
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

    @Override
    public int ID() throws RemoteException {
        return this.ID;
    }
}
