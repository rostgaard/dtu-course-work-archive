/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package temperaturemonitoring;

import java.rmi.Remote;
import java.rmi.RemoteException;
import networktools.Message;
import networktools.NewAdminMessage;
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

    public VectorClock basicDeliver(NewAdminMessage message) throws RemoteException;

    public VectorClock reliableDeliver(NewAdminMessage message) throws RemoteException;

    public void start() throws RemoteException;

    public void promote() throws RemoteException;
}
