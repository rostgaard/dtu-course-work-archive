/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.travelagency.data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andreas
 */
public class FlightInfoWrapper extends Wrapper{

    private FlightInformation flightInformation;
    
    public FlightInfoWrapper() {

    }

    public void setFlightInformation(FlightInformation flightInformation) {
        this.flightInformation = flightInformation;
    }

    @XmlElement
    public FlightInformation getFlightInformation() {
        return flightInformation; 
    }
}
