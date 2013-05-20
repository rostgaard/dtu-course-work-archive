package bootstrapping;

import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import temperaturemonitoring.Node;
import ui.Userinterface;
/*
 * Needs comment.
 */

/**
 *
 * @author Kim Rostgaard Christensen
 */
public class Bootstrapper extends UnicastRemoteObject {

    private static Registry registry;
    private static final Logger logger = Logger.getLogger(Bootstrapper.class.getName());


    public static void startRegistry() throws RemoteException {
        logger.log(Level.INFO, "Starting RMI registry.");
        registry = java.rmi.registry.LocateRegistry.createRegistry(configuration.Configuration.RMIPort);
    }

    public static void registerObject(String name, Remote remoteObj)
            throws RemoteException {
        logger.log(Level.FINEST, " Registered " + name + " as " + remoteObj);
        try {
            registry.bind(name, remoteObj);
        } catch (AlreadyBoundException | AccessException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        System.out.println("Registered: " + name + " -> "
                + remoteObj.getClass().getName() + "[" + remoteObj + "]");
    }

    public static void unregisterObject(String name)
            throws RemoteException {
        try {
            registry.unbind(name);
        } catch (NotBoundException | AccessException ex) {
            Logger.getLogger(Bootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Unregistered: " + name);
    }

    public Bootstrapper() throws RemoteException {
        super();
    }

    public static void main ( String args[] ) throws Exception
    {
        Userinterface ui = new Userinterface();

        Node node[] = new Node[5];
        ObservationService observationService = new ObservationService();
        startRegistry();
        registerObject(ObservationServiceInterface.class.getSimpleName(), observationService);

        for (int i = 0; i < 5; i++) {
            node[i] = new Node(i, 5);
            node[i].connectRegistry("localhost");

            for (int j = 0; j < 5; j++) {
                node[i].addMulticastNode(j);

            }

            if (i == 0) {
                node[i].promote();
            }

            
        }

        for (int i = 0; i < 5; i++) {
            node[i].start();
        }
    }
}
