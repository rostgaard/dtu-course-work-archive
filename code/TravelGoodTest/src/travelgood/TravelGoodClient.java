/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package travelgood;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;
import ws.dtu.lameduck.types.FlightInformation;
import ws.dtu.lameduck.types.FlightList;
import ws.dtu.niceview.types.HotelList;
import ws.travelgoodbpel.FlightBookings;
import ws.travelgoodbpel.HotelBookings;

/**
 *
 * @author Mikkel
 */
public interface TravelGoodClient {
    
    public boolean addFlight(String customerID, String itineraryID, FlightInformation flightInformation);
    
    public String addHotel(String customerID, String itineraryID, String hotelBookingNo);
    
    public boolean bookItinerary(String customerID, String itineraryID, CreditCardInfoType ccInformation);
    
    public String cancelPlanning(String customerID, String itineraryID);
    
    public boolean createItinerary(String customerID, String itineraryID);
    
    public FlightList getFlights(String customerID, String itineraryID, String origin, String destination, XMLGregorianCalendar datetime);
    
    public HotelList getHotels(String customerID, String itineraryID, String city, XMLGregorianCalendar arrival, XMLGregorianCalendar departure);
    
    public void getItinerary(String customerID, String itineraryID, Holder<FlightBookings> flights, Holder<HotelBookings> hotels);
    
    
    
}
