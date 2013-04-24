package temperaturemonitoring;

import bootstrapping.ObservationServiceInterface;
import java.io.Serializable;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

import toolset.vectorclock.VectorClock;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kim Rostgaard Christensen
 */
public class Node extends Thread implements TemperatureNode, Serializable {

    private int ID = 0;
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

    public void addMeasurement(Temperature t) {
        if (this.collectedMeasurements.add(t)) {
            this.vc.incrementClock(ID);
        }
    }

    /**
     * Promotes the node to an admin.
     */
    public void promote() {
        logger.log(Level.INFO, "Promoting node " + this.ID + " to admin.");
        //TODO, Start an election or similar.
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

                registry.bind("/Process" + new Integer(this.ID).toString(), this);
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
        this.vc = new VectorClock(numNodes);
        this.ID = pid;
    }

    @Override
    public void run() {
        logger.log(Level.INFO, "Stating node " + this.ID);
        this.running = true;

        TemperatureSensor fixedrateTemperatureMonitor = new TemperatureSensor(this);
        scheduler.scheduleAtFixedRate(fixedrateTemperatureMonitor, 0, 1, TimeUnit.SECONDS);

        while (this.running) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
            /*
             try {
                monitor.newConnection(ID, ID + 1);
            } catch (RemoteException ex) {
                logger.log(Level.SEVERE, null, ex);
            }

             */
             }
    }
    /**
     * TODO
     *
     * @return
     */
    @Override
    public Temperature latestMeasurement() {
        return new Temperature();
    }

    /**
     * TODO
     *
     * @return
     */
    @Override
    public String toString() {
        return Node.class.getSimpleName() + " " + this.ID;
    }
}
