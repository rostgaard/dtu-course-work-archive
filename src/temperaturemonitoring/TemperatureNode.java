/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package temperaturemonitoring;

import java.rmi.Remote;
import networktools.Message;
import toolset.vectorclock.VectorClock;

/**
 *
 * @author krc
 */
public interface TemperatureNode extends Remote {

    public Temperature latestMeasurement();

    public Node lookupNode(Integer nodeID);

    public VectorClock synchonousSend(Message message);

    public VectorClock asynchonousSend(Message message);

    public VectorClock basicDeliver(Message message);

    public VectorClock reliableDeliver(Message message);
}
