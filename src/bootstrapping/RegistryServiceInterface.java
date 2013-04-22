package bootstrapping;

import java.rmi.*;
/*
 * Needs comment.
 */

/**
 *
 * @author Kim Rostgaard Christensen
 */
public interface RegistryServiceInterface extends java.rmi.Remote {

    public void registerObject(String name, Remote remoteObj) throws RemoteException;

    public void unregisterObject(String name) throws RemoteException;
}
