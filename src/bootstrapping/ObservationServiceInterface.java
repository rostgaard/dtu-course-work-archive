package bootstrapping;

import java.rmi.RemoteException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author krc
 */
public interface ObservationServiceInterface extends java.rmi.Remote {

    public void newConnection(int sourcePid, int destinationPid) throws RemoteException;

    public void clearConnections() throws RemoteException;

    public void sendMessage() throws RemoteException;

    public void newNode(int pid) throws RemoteException;

    public void newAdmin(int pid) throws RemoteException;
}
