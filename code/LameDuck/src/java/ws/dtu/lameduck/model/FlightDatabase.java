/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.lameduck.model;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import ws.dtu.lameduck.Flight;
import ws.dtu.lameduck.FlightInformation;
import ws.dtu.lameduck.FlightList;
import ws.dtu.lameduck.model.exceptions.NoSuchFlightIdentifier;

/**
 *
 * @author krc
 */
public final class FlightDatabase {
    
    private static Map<String,Flight> db = new HashMap<String,Flight>();
    
    public static void loadDatabase() {
        XMLGregorianCalendar date1 = new XMLGregorianCalendarImpl();
        date1.setMonth(11);
        date1.setDay(17);
        date1.setYear(2013);
        
        XMLGregorianCalendar date2 = new XMLGregorianCalendarImpl();
        date2.setMonth(11);
        date2.setDay(18);
        date2.setYear(2013);
        
        insert(generateFlight("Kastrup", "Kabul", date1, date2, "SAS"));
        insert(new FlightInformation("Kabul", "Kastrup", date1, date2, "SAS002"));
        insert(new FlightInformation("Kastrup", "Moscow", date1, date2, "SAS003"));
        insert(new FlightInformation("Kastrup", "Afganistan", date1, date2, "SAS004"));
        insert(new FlightInformation("Kastrup", "Irak", date1, date2, "SAS005"));
        insert(new FlightInformation("Kastrup", "Libya", date1, date2, "SAS006"));
        insert(new FlightInformation("Kastrup", "Kazakhstan", date1, date2, "SAS007"));  
    }
    
    private static Flight generateFlight(String origin, String destination, XMLGregorianCalendar liftOff, XMLGregorianCalendar arrival, String carrier){
        Flight newFlight = new Flight();
        newFlight.setOrigin(origin);
        newFlight.setDestination(destination);
        newFlight.setLiftOff(liftOff);
        newFlight.setArrival(arrival);
        newFlight.setCarrier(carrier);
        return newFlight;
    }
    public static void insert (Flight flight) {
        db.put(flight.getFlightID(), flight);
    }
    
    public static FlightList getFlights(String startAirport, String destinationAirport, XMLGregorianCalendar date) {
        FlightList retList = new FlightList();
        return null;
    }
    
    public static FlightInformation getFlight(String flightIdentfier) throws NoSuchFlightIdentifier {
        if (!db.containsKey(flightIdentfier)){
            throw new exceptions.NoSuchFlightIdentifier();
        }
        
        return db.get(flightIdentfier);
    }
}
