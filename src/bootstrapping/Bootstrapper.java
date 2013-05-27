/*
 * Bootstraps the entire test system. Run the main in this class to start the
 * visual test of the system.
 */
package bootstrapping;

import configuration.Configuration;
import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import temperaturemonitoring.Node;
import temperaturemonitoring.TemperatureNode;
import ui.Userinterface;

/**
 *
 * @author Kim Rostgaard Christensen
 */
public class Bootstrapper extends UnicastRemoteObject {

    // RMI registry.
    private static Registry registry;
    private static final Logger logger = Logger.getLogger(Bootstrapper.class.getName());

    /**
     * Starts the RMI registry service. This makes running the external
     * rmiregisty command redundant.
     *
     * @throws RemoteException
     */
    public static void startRegistry() throws RemoteException {
        logger.log(Level.INFO, "Starting RMI registry.");
        registry = java.rmi.registry.LocateRegistry.createRegistry(configuration.Configuration.RMIPort);
    }

    /**
     * Register an external object into the RMI registry.
     *
     * @param name The name of the service provided.
     * @param remoteObj The remote object providing the service.
     * @throws RemoteException
     */
    public static void registerObject(String name, Remote remoteObj)
            throws RemoteException {
        logger.log(Level.FINEST, " Registered {0} as {1}", new Object[]{name, remoteObj});
        try {
            registry.bind(name, remoteObj);
        } catch (AlreadyBoundException | AccessException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        System.out.println("Registered: " + name + " -> "
                + remoteObj.getClass().getName() + "[" + remoteObj + "]");
    }

    /**
     * Unregister a service from the RMI registry.
     *
     * @param name The name of the service to unregister.
     * @throws RemoteException
     */
    public static void unregisterObject(String name)
            throws RemoteException {
        try {
            registry.unbind(name);
        } catch (NotBoundException | AccessException ex) {
            Logger.getLogger(Bootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Unregistered: " + name);
    }

    /**
     * Constructor.
     *
     * @throws RemoteException
     */
    public Bootstrapper() throws RemoteException {
        super();
    }

    /**
     * Initiates the test system.
     *
     * @param args Command line parameters.
     * @throws Exception
     */
    public static void main ( String args[] ) throws Exception
    {
        Userinterface ui = new Userinterface();

        TemperatureNode node[] = new TemperatureNode[Configuration.Number_Of_Nodes];
        ObservationService observationService = new ObservationService();
        startRegistry();
        registerObject(ObservationServiceInterface.class.getSimpleName(), observationService);

        for (int i = 0; i < Configuration.Number_Of_Nodes; i++) {
            node[i] = new Node(i, Configuration.Number_Of_Nodes);
            node[i].connectRegistry("localhost");
        }

        for (int i = 0; i < Configuration.Number_Of_Nodes; i++) {
            node[i].initialize();
            if (i == 0) {
                node[i].promote();
            }
            node[i].startTasks();
        }
    }
}
