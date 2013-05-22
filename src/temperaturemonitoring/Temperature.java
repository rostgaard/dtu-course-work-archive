package temperaturemonitoring;

import configuration.Configuration;
import java.io.Serializable;
import java.rmi.Remote;
import java.security.SecureRandom;
import java.util.Random;

import toolset.mutex.Randomizer;
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
        this.measurement = Randomizer.nextGaussian(Configuration.simulatedStandardDeviation, Configuration.simulatedMean);
    }

    @Override
    public String toString() {
        return this.timestamp + " : " + this.measurement;
    }

    public long TimeDelta(Temperature t) {
        return t.timestamp - this.timestamp;
    }

    public long timestamp() {
        return this.timestamp;
    }

    public double temperature() {
        return this.measurement;
    }

}
