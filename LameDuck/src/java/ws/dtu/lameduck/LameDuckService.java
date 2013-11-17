/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.lameduck;

import ws.dtu.lameduck.model.FlightList;
import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;
import ws.dtu.lameduck.model.FlightDatabase;
import ws.dtu.lameduck.model.FlightInformation;
import ws.dtu.lameduck.model.exceptions.NoSuchFlightIdentifier;
import ws.dtu.lameduck.model.exceptions.Seat_Unavailable;

/**
 *
 * @author peter
 */
@WebService(serviceName = "LameDuckService")
public class LameDuckService {
    
    
    public LameDuckService() {
        FlightDatabase.loadDatabase();
    }
    
    public FlightList getFlights(String startAirport, String destinationAirport, XMLGregorianCalendar date) {
        return FlightDatabase.getFlights(startAirport, destinationAirport, date);
    }
    
    public Boolean bookFlight(String flightIdentifier, String creditcardInfo) throws NoSuchFlightIdentifier, Seat_Unavailable {
        FlightInformation flight = FlightDatabase.getFlight(flightIdentifier);
        //TODO Charge creditcard 
        flight.bookSeat();
        return true;
    }
    
    public void cancelFlight(String flightIdentifier, int flightPrice, String creditcardInfo) throws NoSuchFlightIdentifier {
        FlightInformation flight = FlightDatabase.getFlight(flightIdentifier);
        //TODO refund 50 % of price flight
        flight.cancelSeat();
    }
}
