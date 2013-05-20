/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package temperaturemonitoring;

import java.rmi.Remote;
import networktools.TemperatureMessage;

/**
 *
 * @author krc
 */
public interface TemperatureNode extends Remote {

    public Temperature latestMeasurement();

    public Node lookupNode(int nodeID);

    public void synchonousSend(TemperatureMessage message);

    public void asynchonousSend(TemperatureMessage message);

    public void deliver(TemperatureMessage message);
}
