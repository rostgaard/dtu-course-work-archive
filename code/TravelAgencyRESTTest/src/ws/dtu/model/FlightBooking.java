/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ws.dtu.lameduck.types.FlightInformation;

/**
 *
 * @author peter
 */

@XmlRootElement
public class FlightBooking {

    public FlightBooking() {
        
    }
    
    public FlightBooking(FlightInformation flight) {
        this.flightInformation = flight;
    }
    public enum FlightBookingState {
    UNCONFIRMED, CONFIRMED, CANCELLED
    }
    
    private FlightInformation flightInformation;
    private FlightBookingState bookingState = FlightBookingState.UNCONFIRMED;
    
    @XmlElement
    public FlightInformation getFlightInformation() {
        return flightInformation;
    }
    
    @XmlElement
    public FlightBookingState getBookingState() {
        return bookingState;
    }

    public void setFlightInformation(FlightInformation flightInformation) {
        this.flightInformation = flightInformation;
    }

    public void setBookingState(FlightBookingState bookingState) {
        this.bookingState = bookingState;
    }
    
    
}
