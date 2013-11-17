/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.lameduck.model;

import java.util.Date;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author krc
 */
public class FlightInformation {

    private XMLGregorianCalendar arrival;
    private String depatureAirport;
    private String destinationAirport;
    private XMLGregorianCalendar departure;
    private String FlightID;
    protected int capacity = 10;

    public FlightInformation(String depatureAirport, 
            String destinationAirport, XMLGregorianCalendar arrival , XMLGregorianCalendar departure, String FlightID) {
        this.arrival = arrival;
        this.depatureAirport = depatureAirport;
        this.destinationAirport = destinationAirport;
        this.departure = departure;
        this.FlightID = FlightID;
        
        FlightDatabase.insert(this);
    }

    public XMLGregorianCalendar getArrival() {
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
    
    public void cancelSeat() {
        this.capacity++;
    }

    public String getDepatureAirport() {
        return depatureAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public XMLGregorianCalendar getDeparture() {
        return departure;
    }

    public String getFlightID() {
        return FlightID;
    }
    
    
    
}
