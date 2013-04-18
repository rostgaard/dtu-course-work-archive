package bootstrapping;

import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import temperaturemonitoring.Node;
/*
 * Needs comment.
 */

/**
 *
 * @author Kim Rostgaard Christensen
 */
public class Bootstrapper extends UnicastRemoteObject
        implements ObservationService, RegistryService {

    private static final int PORT = 1099;
    private static Registry registry;

    public static void startRegistry() throws RemoteException {
        // create in server registry
        registry = java.rmi.registry.LocateRegistry.createRegistry(PORT);
    }

    @Override
    public void registerObject(String name, Remote remoteObj)
            throws RemoteException {
        try {
            registry.bind(name, remoteObj);
        } catch (AlreadyBoundException | AccessException ex) {
            Logger.getLogger(Bootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Registered: " + name + " -> "
                + remoteObj.getClass().getName() + "[" + remoteObj + "]");
    }

    @Override
    public void unregisterObject(String name)
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
        Node node[] = new Node[5];

        startRegistry();

        for (int i = 0; i < 5; i++) {
            node[i] = new Node(i, 5);
            node[i].connectRegistry("localhost");
            node[i].start();
        }
    }

    @Override
    public void newConnection(int sourcePid, int destinationPid) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
