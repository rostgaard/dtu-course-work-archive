/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.lameduck.model;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import javax.xml.datatype.XMLGregorianCalendar;
import ws.dtu.lameduck.model.exceptions.NoSuchFlightIdentifier;

/**
 *
 * @author krc
 */
public final class FlightDatabase {
    
    static FlightList db = new FlightList();
    
    public static void loadDatabase() {
        XMLGregorianCalendar date1 = new XMLGregorianCalendarImpl();
        date1.setMonth(11);
        date1.setDay(17);
        date1.setYear(2013);
        
        XMLGregorianCalendar date2 = new XMLGregorianCalendarImpl();
        date2.setMonth(11);
        date2.setDay(18);
        date2.setYear(2013);
        
        insert(new FlightInformation("Kastrup", "Kabul", date1, date2, "SAS001"));
        insert(new FlightInformation("Kabul", "Kastrup", date1, date2, "SAS002"));
        insert(new FlightInformation("Kastrup", "Moscow", date1, date2, "SAS003"));
        insert(new FlightInformation("Kastrup", "Afganistan", date1, date2, "SAS004"));
        insert(new FlightInformation("Kastrup", "Irak", date1, date2, "SAS005"));
        insert(new FlightInformation("Kastrup", "Libya", date1, date2, "SAS006"));
        insert(new FlightInformation("Kastrup", "Kazakhstan", date1, date2, "SAS007"));  
    }
    
    public static void insert (FlightInformation flight) {
        db.put(flight.getFlightID(), flight);
    }
    
    public static FlightList getFlights(String startAirport, String destinationAirport, XMLGregorianCalendar date) {
        // TODO make sure only to return the part of the db that fulfills the paramters
        return db;
    }
    
    public static FlightInformation getFlight(String flightIdentfier) throws NoSuchFlightIdentifier {
        if (!db.containsKey(flightIdentfier)){
            throw new exceptions.NoSuchFlightIdentifier();
        }
        
        return db.get(flightIdentfier);
    }
}
