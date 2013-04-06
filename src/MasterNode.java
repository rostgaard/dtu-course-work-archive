import java.math.*;
import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MasterNode extends UnicastRemoteObject
        implements MasterNodeService {

    private static final int PORT = 1099;
    private static Registry registry;

    public static void startRegistry() throws RemoteException {
        // create in server registry
        registry = java.rmi.registry.LocateRegistry.createRegistry(PORT);
    }

    public static void registerObject(String name, Remote remoteObj)
            throws RemoteException, AlreadyBoundException {
        registry.bind(name, remoteObj);
        System.out.println("Registered: " + name + " -> "
                + remoteObj.getClass().getName() + "[" + remoteObj + "]");
    }

    public static void unregisterObject(String name)
            throws RemoteException, AlreadyBoundException {
        try {
            registry.unbind(name);
        } catch (NotBoundException | AccessException ex) {
            Logger.getLogger(MasterNode.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Unregistered: " + name);
    }

    public MasterNode() throws RemoteException {
        super();
    }

    @Override
    public void receiveMeasurement(Temperature t) throws RemoteException {
        System.out.println(t);
    }

    public static void main ( String args[] ) throws Exception
    {
        startRegistry();
        // Assign a security manager, in the event that dynamic
	// classes are loaded
        //if (System.getSecurityManager() == null)
        //    System.setSecurityManager ( new RMISecurityManager() );

        // Create an instance of our power service server ...
        MasterNode svr = new MasterNode();

        // ... and bind it with the RMI Registry
        registerObject("PowerService", svr);

    }
}
