
import java.util.ArrayList;
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
public class Maekawa {

    private static void ThreeBasicProcesses() throws InterruptedException {

        ArrayList<EmulatedProcess> quorum1 = new ArrayList<>();
        ArrayList<EmulatedProcess> quorum2 = new ArrayList<>();
        ArrayList<EmulatedProcess> quorum3 = new ArrayList<>();

        EmulatedProcess p1 = new EmulatedProcess(1);
        EmulatedProcess p2 = new EmulatedProcess(2);
        EmulatedProcess p3 = new EmulatedProcess(3);

        quorum1.add(p2);
        quorum1.add(p3);
        p1.setQuorum(quorum1);
        Logger.getLogger(Maekawa.class.getName()).log(Level.INFO, "Starting process {0}", 1);
        p1.start();

        quorum2.add(p1);
        quorum2.add(p3);
        p2.setQuorum(quorum2);
        Logger.getLogger(Maekawa.class.getName()).log(Level.INFO, "Starting process {0}", 2);
        p2.start();

        quorum3.add(p1);
        quorum3.add(p2);
        p3.setQuorum(quorum3);
        Logger.getLogger(Maekawa.class.getName()).log(Level.INFO, "Starting process {0}", 3);
        p3.start();
        Thread.sleep(1000);


    }

    public static void main(String args[]) throws InterruptedException {
        EmulatedProcess p;
        ThreeBasicProcesses();
        /*
         * for (int i = 0; i < 10; i++) {            Logger.getLogger(Maekawa.class.getName()).log(Level.INFO, "Starting process {0}", i);
            p = new EmulatedProcess(i);
            p.start();

         }*/
        


    }
}
