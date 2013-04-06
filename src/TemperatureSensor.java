
import java.io.Serializable;
import java.rmi.Remote;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author krc
 */
public class TemperatureSensor implements Remote, Serializable {

    public Temperature poll() {
        return new Temperature();
    }
}
