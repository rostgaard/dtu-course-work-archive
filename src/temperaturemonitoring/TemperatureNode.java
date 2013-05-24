/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package temperaturemonitoring;

import java.rmi.Remote;
import java.rmi.RemoteException;
import networktools.Message;
import networktools.ProposedAdminMessage;
import networktools.TemperatureMessage;
import toolset.vectorclock.VectorClock;

/**
 *
 * @author krc
 */
public interface TemperatureNode extends Remote {

    public int ID() throws RemoteException;

    public TemperatureNode lookupNode(Integer nodeID) throws RemoteException;

    public double latestAverage() throws RemoteException;

    public VectorClock sendMeasurement(TemperatureMessage message) throws RemoteException;

    public VectorClock basicDeliver(ProposedAdminMessage message) throws RemoteException;

    public VectorClock send(ProposedAdminMessage message) throws RemoteException;

    public void start() throws RemoteException;

    public void promote() throws RemoteException;
}
