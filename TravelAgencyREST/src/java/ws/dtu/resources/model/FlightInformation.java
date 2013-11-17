/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.resources.model;

import java.util.Date;

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
