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
public interface AdminNodeService extends Remote {

    public void receiveMeasurement(Temperature t);
}
