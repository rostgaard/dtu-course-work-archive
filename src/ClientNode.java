
import java.rmi.Naming;
import java.rmi.registry.Registry;

public class ClientNode {

    private static Registry registry;

    public static void main(String args[]) throws Exception {
        boolean connected = false;

        String host = "localhost";
        // Check for hostname argument
        if (args.length > 1) {
            host = args[0];

        }

        // Assign security manager
        //if (System.getSecurityManager() == null)
        //{
        //	System.setSecurityManager
        //	(new RMISecurityManager());
        //}

        // Call registry for PowerService
        MasterNodeService service = null;
        while (!connected) {
            TemperatureSensor sensor = new TemperatureSensor();
            try {
                service = (MasterNodeService) Naming.lookup("rmi://" + host + "/PowerService");
                connected = true;
                registry = java.rmi.registry.LocateRegistry.getRegistry(host);
                registry.bind("Temperature", sensor);
                while (true) {
                    Temperature temp = new Temperature();
                    service.receiveMeasurement(temp);
                    Thread.sleep(1000);

                }

            } catch (java.rmi.ConnectException e) {
                System.out.println("Could not connect");
                connected = false;
                Thread.sleep(1000);

            }
        }
    }
}
