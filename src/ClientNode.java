
import java.rmi.Naming;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class ClientNode {

    private Runnable BasicRunnable;
    private static Registry registry;
    private static final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);


    public static void main(String args[]) throws Exception {
        final Runnable stuff = new BasicRunnable();

        scheduler.scheduleAtFixedRate(stuff, 0, (long) Math.pow(10, 9), TimeUnit.NANOSECONDS);
 
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
