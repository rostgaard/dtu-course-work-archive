/*
 * A temperature object.
 */
package temperaturemonitoring;

import configuration.Configuration;
import java.io.Serializable;
import toolset.mutex.Randomizer;


/**
 *
 * @author Kim Rostgaard Christensen
 */
public class Temperature implements Serializable{

    private static final long serialVersionUID = 227L;

    private long timestamp;
    private double measurement;
    private long period;

    //private static Random random = new Random(sRandom.);
    public Temperature(long period) {
        this.period = period;
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

    public long period() {
        return this.period;
    }

}
