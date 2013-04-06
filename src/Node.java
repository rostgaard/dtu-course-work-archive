
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author krc
 */
public final class Node {

    private static Registry registry = null;
    private static Initiator initiator = null;

    public static boolean hasRegistry() {
        return Node.registry == null;
    }

    public static void disconnectRegistry() {
        Logger.getLogger(Node.class.getName()).log(Level.INFO, "Disconnected regitry");
        Node.registry = null;
        // Restart the initiator
        initiator.startListening();
    }

    private static void locateRegistry() throws InterruptedException {
        initiator = new Initiator();
        initiator.startListening();

        System.out.println("Started");
        while (true) {
            initiator.RegistryBeacon();
            Thread.sleep(1000);

        }
    }

    public static void connectRegistry(InetAddress host) throws InterruptedException {
        try {
            Node.registry = java.rmi.registry.LocateRegistry.getRegistry(host.toString());
            Node.initiator.stopListening();

        } catch (RemoteException ex) {
            Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
            Node.registry = null;
        }
        // Just for kicks
        Thread.sleep(1000);
        Node.disconnectRegistry();
    }

    public static void main(String args[]) throws InterruptedException {
        Node.locateRegistry();
    }
}
