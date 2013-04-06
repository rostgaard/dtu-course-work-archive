
import java.io.Serializable;
import java.rmi.Remote;
import java.security.SecureRandom;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author krc
 */
public class Temperature implements Serializable{

    private static final long serialVersionUID = 227L;

    private long timestamp;
    private double measurement;

    //private static Random random = new Random(sRandom.);

    public Temperature() {
        this.timestamp = System.nanoTime();
        this.measurement = Randomizer.randomInRange(-10.2, 22);
    }

    @Override
    public String toString() {
        return this.timestamp + " : " + this.measurement;
    }


}
