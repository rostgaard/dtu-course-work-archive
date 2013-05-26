/*
 * A randomizer for returning random uniform and guassian values, given bounds.
 */
package toolset.mutex;

import java.security.SecureRandom;

/**
 *
 * @author Kim Rostgaard Christensen
 */
public class Randomizer {

    protected static SecureRandom random = new SecureRandom();

    public static double nextUniform(double min, double max) {
        double range = max - min;
        double scaled = random.nextDouble() * range;
        double shifted = scaled + min;
        return shifted; // == (rand.nextDouble() * (max-min)) + min;
    }

    public static double nextGaussian(double sd, double mean) {
        return random.nextGaussian() * sd + mean;
    }
}
