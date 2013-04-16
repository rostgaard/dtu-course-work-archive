
import java.math.BigInteger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author krc
 */
public class BasicRunnable implements Runnable {

    TemperatureSensor tsens = new TemperatureSensor();
    Temperature t = new Temperature();
    long t_0;
    int occurence = 0;

    @Override
    public void run() {
        this.t = tsens.poll();
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
