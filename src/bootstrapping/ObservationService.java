/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bootstrapping;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import temperaturemonitoring.Node;

/**
 *
 * @author krc
 */
public class ObservationService extends UnicastRemoteObject implements ObservationServiceInterface {

    private static final Logger logger = Logger.getLogger(ObservationService.class.getName());

    public ObservationService() throws RemoteException {
    }

    @Override
    public void newConnection(int sourcePid, int destinationPid) throws RemoteException {
        logger.log(Level.INFO, "Process " + sourcePid + " connects to " + destinationPid);
        NetworkModel.connect(sourcePid, destinationPid);
    }

    @Override
    public void newNode(int pid) throws RemoteException {
        logger.log(Level.FINEST, "Process " + pid + " joined network");
        NetworkModel.addNode(pid);
    }

    @Override
    public void sendMessage() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clearConnections() throws RemoteException {
        NetworkModel.clearEdges();
    }

    @Override
    public void newAdmin(int pid) throws RemoteException {
        NetworkModel.currentAdmin = pid;
    }
}
