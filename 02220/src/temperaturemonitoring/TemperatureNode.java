/*
 * RMI interface for the temperature node.
 */
package temperaturemonitoring;

import java.rmi.Remote;
import java.rmi.RemoteException;
import networktools.ProposedAdminMessage;
import networktools.TemperatureMessage;
import toolset.vectorclock.VectorClock;

/**
 *
 * @author Kim Rostgaard Christensen
 */
public interface TemperatureNode extends Remote {

    public int ID() throws RemoteException;

    public double latestAverage() throws RemoteException;

    public VectorClock sendMeasurement(TemperatureMessage message) throws RemoteException;

    public VectorClock basicDeliver(ProposedAdminMessage message) throws RemoteException;

    public VectorClock send(ProposedAdminMessage message) throws RemoteException;

    public void initialize() throws RemoteException;

    public void startTasks() throws RemoteException;

    public void promote() throws RemoteException;

    public void connectRegistry(String host) throws RemoteException;

    public void disconnectRegistry() throws RemoteException;
}
