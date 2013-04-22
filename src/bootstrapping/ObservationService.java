/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bootstrapping;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import temperaturemonitoring.Node;

/**
 *
 * @author krc
 */
public class ObservationService implements ObservationServiceInterface, Serializable {

    private static final Logger logger = Logger.getLogger(Node.class.getName());

    @Override
    public void newConnection(int sourcePid, int destinationPid) throws RemoteException {
        logger.log(Level.INFO, "Process " + sourcePid + " connects to " + destinationPid);
    }
}
