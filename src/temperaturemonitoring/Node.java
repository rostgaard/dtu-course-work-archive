
/*
 * Represenation of a network node.
 */
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
import networktools.ProposedAdminMessage;
import networktools.TemperatureMessage;
import networktools.Transmitter;
import networktools.TransmitterMode;


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
    //private Stack<Message> messageBuffer = new Stack<>();
    private Message adminMessageBuffer = null;
    // Periodic tasks.
    private Transmitter transceiver;
    private TemperatureSensor fixedrateTemperatureMonitor;
    private TemperatureMeasurementCollection measurements = new TemperatureMeasurementCollection(Configuration.Number_Of_Nodes);
    private ProposalList proposals;
    private boolean alreadyProcessing = false; // To avoid infite loops.

    /**
     * Calculates and returns the latest average.
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public double latestAverage() throws RemoteException {

        if (this.admin == Null_ID || this.alreadyProcessing) {
            return Double.NaN;
        } else if (!this.isAdmin()) { // Delegate the request
            this.alreadyProcessing = true;
            double value = this.lookupNode(this.admin).latestAverage();
            this.alreadyProcessing = false;

            return value;
        } else {
            return measurements.latestAverage();
        }

    }

    public void incrementVectorClock() {
        this.vc.incrementClock(ID);
    }

    /**
     * TODO
     *
     * @param t
     */
    public void addMeasurement(Temperature t) throws RemoteException {

        this.incrementVectorClock();; //State changes on each measurement.
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

    private NodeList getProcessGroup() {
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

        // R-multicast the new value; effectively bullying the other processes
        // into selecting this node as the new admin.
        for (Integer node : this.getProcessGroup()) {
            if (node != this.ID) {
                this.vc.incrementClock(ID);
                ProposedAdminMessage message = new ProposedAdminMessage(0, this.ID, node, this);
                this.vc.merge(this.lookupNode(node).send(message));
            }
        }

        logger.log(Level.INFO, "Promoting node {0} to admin.", this.ID);

        // Reset measurements.
        this.measurements = new TemperatureMeasurementCollection(Configuration.Number_Of_Nodes);

        // Promote
        this.admin = this.ID;

        this.getMonitor().newAdmin(this.ID);
    }

    public boolean hasRegistry() {
        return this.registry == null;
    }

    @Override
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
    @Override
    public void connectRegistry(String host) throws RemoteException {
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
        this.proposals = new ProposalList();


        this.transceiver = new Transmitter(TransmitterMode.FIFO, this);
        this.vc = new VectorClock(numNodes);
        this.ID = pid;
    }

    public void addMulticastNode(int n) {
        //logger.log(Level.INFO, "Adding node " + n
        //       +  " to " + this.ID + "'s multicast group."
        // );
        this.getProcessGroup().add(n);
    }

    @Override
    public void initialize() throws RemoteException {
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
    public VectorClock send(ProposedAdminMessage message) throws RemoteException {
        //System.out.println("Node" + this.ID + " got message: " + message);
        this.proposals.add(message.getPayload());

        /*        if (message.getVotingRound() < Configuration.Tolerable_Failures) {
         for (Integer node : this.getProcessGroup()) {
         if (node == message.getPayload() || node == this.ID) {
         continue;
         }

         this.vc.incrementClock(ID);
         ProposedAdminMessage nextMessage = new ProposedAdminMessage(message.getVotingRound() + 1, message.getPayload(), node, this);
         System.out.println("Node" + this.ID + " sends message: " + nextMessage);

         this.vc.merge(this.lookupNode(node).send(nextMessage));
         /*                try {                    Thread.sleep(100);
         } catch (InterruptedException ex) {
         Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);

         }*/
        /*          }
         } else {

         this.admin = proposals.majority();
         System.out.println("Node " + this.ID + " proposals: " + this.proposals);
         System.out.println("Node " + this.ID + " selects: " + this.admin);

         if (this.ID != this.admin) {
         this.getMonitor().newConnection(this.ID, this.admin);
         }

         proposals.clear();
         }*/



//}
        logger.log(Level.INFO, "R-deliver received message on node " + this.ID + " from " + message.getSender());
            // B-Multicast
            for (Integer node : this.getProcessGroup()) {
                if (message.getSender() != this) { // Skip the current node.
                    this.lookupNode(node).basicDeliver(message);  // Deliver the message.
                }
            }

            //logger.log(Level.INFO, "R-delivered message to" + this.ID + " from " + message.getSender());
        
        this.admin = message.getPayload();
        System.out.println("Node " + this.ID + " Changes admin to " + message.getPayload());
        if (this.ID != this.admin) {
            this.getMonitor().newConnection(this.ID, this.admin);
        }

        return this.vc;

    }

    @Override
    public void startTasks() {

        fixedrateTemperatureMonitor = new TemperatureSensor(this);
        transceiver = new Transmitter(TransmitterMode.FIFO, this);

        scheduler.scheduleAtFixedRate(this.fixedrateTemperatureMonitor, 0, 1, TimeUnit.SECONDS);

        scheduler.scheduleAtFixedRate(this.transceiver, 1, 1000, TimeUnit.MILLISECONDS);

        if (this.started) {
            return;
        }

        this.started = true;
    }

    private void basicMulticast(ArrayList<Integer> group, ProposedAdminMessage message) throws RemoteException {

        for (Integer node : group) {
            if (node != this.ID) { // Skip own ID.
                this.vc.incrementClock(ID);

                VectorClock remoteClock = this.lookupNode(node).basicDeliver(message);
                this.vc.merge(remoteClock);
                logger.log(Level.INFO, "Basic multicasting new admin request from " + this.ID + " to " + node);
            }
        }
    }

    private void reliabeMulticast(ArrayList<Integer> group, ProposedAdminMessage message) throws RemoteException {
        logger.log(Level.INFO, "Reliable multicast sending from " + this.ID);

        for (Integer node : group) {
            this.vc.incrementClock(ID);

            logger.log(Level.INFO, "Reliable multicast sending from " + this.ID + " to " + node);
            VectorClock remoteClock = this.lookupNode(node).send(message);
            this.vc.merge(remoteClock);

        }
    }

    @Override
    public VectorClock basicDeliver(ProposedAdminMessage message) throws RemoteException {
        if (this.adminMessageBuffer == null) { // The message is not already received.
            this.adminMessageBuffer = message; // Buffer the message.
            for (Integer node : this.getProcessGroup()) {
                this.vc.merge(this.lookupNode(node).send(message));
            }
        }
        return this.vc;
    }

    @Override
    public int ID() throws RemoteException {
        return this.ID;
    }
}

class ProposalList extends ArrayList<Integer> {

    //TODO
    public int majority() {
        return this.get(0);
    }
}
