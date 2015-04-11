/*
 * RMI (protocol) specification for the monitor interface.
 */
package bootstrapping;

import java.rmi.RemoteException;

/**
 *
 * @author Kim Rostgaard Christensen
 */
public interface ObservationServiceInterface extends java.rmi.Remote {

    /**
     * Signals that a connection is now active between two nodes.
     *
     * @param sourcePid The source of the sink.
     * @param destPid The destination of the sink.
     */
    public void newConnection(int sourcePid, int destinationPid) throws RemoteException;

    /**
     * Resets the channels visualized.
     */
    public void clearConnections() throws RemoteException;

    /**
     * Visualizes a message sent to a node.
     *
     * @throws RemoteException
     */
    public void sendMessage() throws RemoteException;

    /**
     * Visualizes a new node added to the network.
     *
     * @param pid The ID of the newly added process.
     * @throws RemoteException
     */
    public void newNode(int pid) throws RemoteException;

    /**
     * Subscribes a new node to the visualized network.
     *
     * @param pid
     * @throws RemoteException
     */
    public void newAdmin(int pid) throws RemoteException;
}
