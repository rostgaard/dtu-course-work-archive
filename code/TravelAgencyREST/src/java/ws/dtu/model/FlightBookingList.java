/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import ws.dtu.lameduck.types.FlightInformation;
import ws.dtu.lameduck.types.FlightList;

/**
 *
 * @author peter
 */
@XmlRootElement
public class FlightBookingList {
    
    private List<FlightBooking> flights;
    
    public FlightBookingList() {
        flights = new ArrayList<FlightBooking>();
    }
    
    public FlightBookingList(FlightList flightList) {
        flights = new ArrayList<FlightBooking>();
        for(FlightInformation flight : flightList.getFlights()) {
            FlightBooking flightBooking = new FlightBooking(flight);
            this.flights.add(flightBooking);
        }
    }
    
    public void add(FlightBooking booking) {
        flights.add(booking);
    }

    @XmlElement
    public List<FlightBooking> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightBooking> flights) {
        this.flights = flights;
    }
    

    
}
