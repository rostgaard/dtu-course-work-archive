package experiments;

import java.math.BigInteger;

import temperaturemonitoring.*;

/*
 * Needs comment.
 */

/**
 *
 * @author Kim Rostgaard Christensen
 */
public class BasicRunnable implements Runnable {

    Temperature t = null;
    long t_0;
    int occurence = 0;

    @Override
    public void run() {
        this.t = new Temperature(0);
        if (occurence == 0) {
            t_0 = t.timestamp();
        }
        BigInteger timepassed = new BigInteger(new Integer(occurence).toString() + "000000000");
        BigInteger relativeSkew = new BigInteger(new Long(t.timestamp() - t_0).toString());

        relativeSkew = relativeSkew.subtract(timepassed);

        float skew = (float) (relativeSkew.floatValue() * Math.pow(10, -9));

        System.out.println("T_0:" + this.t_0 + " Timestamp: " + t.timestamp() + " Clockdrift: ~" + skew);
        this.occurence++;
    }
}
