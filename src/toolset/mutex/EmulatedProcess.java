package toolset.mutex;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import configuration.*;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kim Rostgaard Christensen
 */
public class EmulatedProcess extends Thread {

    int id;
    MaekawaStates state;
    boolean voted;
    boolean gotReply = false;
    boolean running = true;
    int votesReceived = 0;
    ArrayList<EmulatedProcess> quorum = new ArrayList<>();
    LinkedList<EmulatedProcess> requestQueue = new LinkedList<>();

    public EmulatedProcess(int id) {
        this.votesReceived = 0;
        this.state = MaekawaStates.Released;
        this.voted = false;
        this.id = id;
    }

    public void setQuorum(ArrayList<EmulatedProcess> quorum) {
        this.quorum = quorum;
    }

    @Override
    public void run() {
        System.out.println("Starting thread " + this.id);

        while (this.running) {
            try {
                double delay = Randomizer.nextUniform(Configuration.minimumDelay, Configuration.maximumDelay);

                System.out.println("Process " + id
                        + " sleeping for ~" + Math.round(delay) + " miliseconds");
                try {
                    Thread.sleep((long) delay);
                } catch (InterruptedException ex) {
                    Logger.getLogger(EmulatedProcess.class.getName()).log(Level.SEVERE, null, ex);
                }

                this.enterCritical();
                this.exitCritical();
            } catch (InterruptedException ex) {
                Logger.getLogger(EmulatedProcess.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        System.out.println("Ending thread " + this.id);
    }

    public void enterCritical() throws InterruptedException {
        System.out.println("enterCritical: process " + this.id + " enters critical");

        int attempts = 0;
        //  For pi to enter the critical section
        this.state = MaekawaStates.Wanted;
        // Reset the number of votes
        this.votesReceived = 0;
        int votesExpected = this.quorum.size();

        // Multicast REQUEST to all processes in Vi;
        for (EmulatedProcess p : this.quorum) {
            p.requestRegion(this);
        }

        while (this.votesReceived < votesExpected) {
            System.out.println("Process " + this.id + " is waiting for votes (" + this.votesReceived + "/" + votesExpected);
            System.out.println("Process " + this.id + " gotRely:" + this.gotReply);
            Thread.sleep(1000);
            attempts++;
            if (attempts > 1) {
                System.out.println("Process " + this.id + " could not get lock!");

                this.shutdown();
                return;
            }

        }
        this.state = MaekawaStates.Held;
    }

    public void exitCritical() {
        System.out.println("exitCritical: process " + this.id + " exits critical");

        // For pi to exit the critical section
        this.state = MaekawaStates.Released;

        // Multicast RELEASE to all processes in Vi;
        for (EmulatedProcess p : this.quorum) {
            p.releaseRegion(this);
        }

        //Wait until (number of replies received = K
        //);
        //state:= HELD;
    }

    public void requestRegion(EmulatedProcess p) {
        System.out.println("requestRegion: process " + this.id + " got request from" + p.id);
        // On receipt of a REQUEST from pi at pj
        if (this.state == MaekawaStates.Held || this.voted) {
            System.out.println("requestRegion: process " + this.id + " queues request");
            // queue request from pi without replying;
            this.requestQueue.add(p);
        } else {
            System.out.println("requestRegion: process " + this.id + " sends reply to " + p.id);
            p.sendReply();
            this.voted = true;
        }
    }

    public void releaseRegion(EmulatedProcess p) {
        System.out.println("releaseRegion: process " + this.id + " got request from" + p.id);

        // On receipt of a RELEASE from pi at pj
        if (!this.requestQueue.isEmpty()) {
            requestQueue.remove().sendReply();
            this.voted = true;
        } else
            this.voted = false;
    }
    
    public void sendReply() {
        this.votesReceived = this.votesReceived + 1;
        System.out.println("sendReply: process " + this.id + " got reply incrementing from to " + this.votesReceived);
    }

    public synchronized void shutdown() {
        this.running = false;
    }
}

