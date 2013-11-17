/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.resources.model;

import java.util.Date;
import ws.dtu.resources.model.exceptions.Seat_Unavailable;

/**
 *
 * @author krc
 */
public class FlightInformation {

    private Date arrival;
    private String depatureAirport;
    private String destinationAirport;
    private Date departure;
    private String FlightID;
    protected int capacity = 10;

    public FlightInformation(Date arrival, String depatureAirport, 
            String destinationAirport, Date departure, String FlightID) {
        this.arrival = arrival;
        this.depatureAirport = depatureAirport;
        this.destinationAirport = destinationAirport;
        this.departure = departure;
        this.FlightID = FlightID;
    }

    public Date getArrival() {
        return arrival;
    }

    public void bookSeat() throws exceptions.Seat_Unavailable {
        if (this.capacity > 0) {
            this.capacity--;
        }
        else {
            throw new exceptions.Seat_Unavailable();
        }
    }

    public String getDepatureAirport() {
        return depatureAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public Date getDeparture() {
        return departure;
    }

    public String getFlightID() {
        return FlightID;
    }
    
    
    
}
