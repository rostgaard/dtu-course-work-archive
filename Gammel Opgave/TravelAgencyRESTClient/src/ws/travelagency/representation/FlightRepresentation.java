/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.travelagency.representation;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import ws.travelagency.data.FlightInformation;

/**
 *
 * @author Andreas
 */
@XmlRootElement
public class FlightRepresentation extends Representation{

    @XmlElement(name="Flight")
    ArrayList<FlightInformation> flights = new ArrayList<FlightInformation>();

    public void addFlightInformation(FlightInformation flightInfo) {
        flights.add(flightInfo);
    }

    
    public ArrayList<FlightInformation> getFlightInformations() {
        return flights; 
    }

}
