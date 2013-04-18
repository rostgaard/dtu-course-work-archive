package obsolecent;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Needs comment.
 */
/**
 *
 * @author Kim Rostgaard Christensen
 */
public class Initiator {

    private static MulticastSocket socket = null;
    private static InetAddress group = null;
    private static Listener reader = null;
    public static boolean listening = false;

    public Initiator() {
        this.startListening();
    }

    private void startListening() {
        try {
            // Set up multicast group
            if (group == null) {
                group = InetAddress.getByName(Configuration.initialGroup);
            }

            if (socket == null) {
                socket = new MulticastSocket(Configuration.multicastPort);
            }
            
            socket.joinGroup(group);

            reader = new Listener(socket);
            reader.start();
            Initiator.listening = true;

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

    public void RegistryAck() {
        this.send(Protocol.registryAcknowledgement);
    }

    public void RegistryBeacon() {
        this.send(Protocol.registrySearch);
    }

    private void send(String message) {

        // Send the outgoing message
        byte[] packet = message.getBytes();
        DatagramPacket outgoing;
        outgoing = new DatagramPacket(packet, packet.length, Initiator.group,
                Configuration.multicastPort);
        try {
            Initiator.socket.send(outgoing);
        } catch (IOException ex) {
            Logger.getLogger(Initiator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void stopListening() {
        Initiator.listening = false;
        try {

            if (Initiator.socket != null) {
                Initiator.socket.leaveGroup(group);
            }
        } catch (IOException ex) {
            Logger.getLogger(Initiator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class Listener extends Thread {

    private MulticastSocket socket = null;
    private String message;
    private boolean hasRegistry = false;

    public Listener(MulticastSocket s) {
        this.socket = s;
    }

    public void setRegistry(boolean hasRegistry) {
        this.hasRegistry = hasRegistry;
    }

    @Override
    public void run() {
        // Wait for and show three incomming messages
        byte[] buff = new byte[80];
        while (true) {
            // get messages from others in group
            DatagramPacket data = new DatagramPacket(buff, buff.length);
            try {
                this.socket.receive(data);
            } catch (IOException ex) {
                Logger.getLogger(Initiator.class.getName()).log(Level.SEVERE, null, ex);
            }


            message = new String(data.getData()).trim();

            if (message.equals(Protocol.registrySearch)) {
                System.out.print("Received a registrySearch request from " + data.getAddress().toString());
                if (!this.hasRegistry) {
                    System.out.println(" - but no local registry found.");
                } else {
                    System.out.println(" - broadcasting my service.");
                    Initiator.RegistryAck();
                }


            } else if (message.equals(Protocol.registryAcknowledgement)) {
                System.out.print("Received a registryAcknowledgement request from " + data.getAddress().toString());
                if (!Node.hasRegistry()) {
                    System.out.println(" - but I do not care, because I'm the registry");
                } else {
                    System.out.println(" my new friend is " + data.getAddress().toString());
                    //Node.connectRegistry(data.getAddress());
                }
            } else {
                System.out.println("Received unknown: \"" + new String(data.getData()).trim() + "\"");
            }

        }
    }
}
