/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.representation;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import ws.dtu.model.FlightBookingList;


@XmlRootElement
public class FlightRepresentation extends Representation {
    
    private FlightBookingList flightBookings;

    public FlightRepresentation(FlightBookingList flightBookings) {
        this.flightBookings = flightBookings;
    }

    public FlightRepresentation() {
    }
    
    
    @XmlElement
    public FlightBookingList getFlightBookings() {
        return flightBookings;
    }

    public void setFlightBookings(FlightBookingList flightBookings) {
        this.flightBookings = flightBookings;
    }
}
