
import java.rmi.*;

public interface MasterNodeService extends java.rmi.Remote {

    public void receiveMeasurement(Temperature t) throws RemoteException;
}
