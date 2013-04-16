
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


import dk.retrospekt.dtemp.vectorclock.VectorClock;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author krc
 */
public class Node {

    private static int ID = 0;
    private static Registry registry = null;
    private static Initiator initiator = null;
    private static boolean isAdmin = false;
    private VectorClock vc = new VectorClock(Node.ID, 10);

    public static boolean hasRegistry() {
        return Node.registry == null;
    }

    public static void disconnectRegistry() {
        Logger.getLogger(Node.class.getName()).log(Level.INFO, "Disconnected registry");
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
