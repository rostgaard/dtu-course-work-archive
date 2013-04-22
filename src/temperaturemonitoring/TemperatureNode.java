/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package temperaturemonitoring;

import java.rmi.Remote;

/**
 *
 * @author krc
 */
public interface TemperatureNode extends Remote {

    public Temperature latestMeasurement();
}
