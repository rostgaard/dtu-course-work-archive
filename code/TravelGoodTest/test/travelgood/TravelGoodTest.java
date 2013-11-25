/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package travelgood;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import servicewrappers.BPELWrapper;
import ws.dtu.lameduck.types.Flight;
import ws.dtu.lameduck.types.FlightInformation;
import ws.dtu.lameduck.types.FlightList;
import ws.travelgoodbpel.FlightBookings;
import ws.travelgoodbpel.HotelBookings;

/**
 *
 * @author Mikkel
 */
public class TravelGoodTest {
    
    protected TravelGoodClient client;
    
    private String testId = "TestId";
    private XMLGregorianCalendar date1;
    public TravelGoodTest() {
        client = new BPELWrapper();
    }
    
    @After
    public void after(){
        client.cancelPlanning(testId);
    }
    
    @Test
    public void testCreateItinerary(){
        client.createItinerary(testId);
        Holder<FlightBookings> flights = new Holder<FlightBookings>();
        Holder<HotelBookings> hotels = new Holder<HotelBookings>();
        client.getItinerary(testId, flights, hotels);
        assertEquals(0,flights.value.getFlightBooking().size());
        assertEquals(0,hotels.value.getHotelBooking().size());
    }
    
    @Test
    public void testAddFlight() throws DatatypeConfigurationException{
        client.createItinerary(testId);
        String origin = "Kastrup";
        String destination = "Kabul";
        DatatypeFactory df = DatatypeFactory.newInstance();
        date1 = df.newXMLGregorianCalendar("2013-11-17");
        FlightList flights = client.getFlights(testId, origin, destination, date1);
        assertEquals(1, flights.getFlights().size());
        client.addFlight(testId, flights.getFlights().get(0));
        
        Holder<FlightBookings> flightBookings = new Holder<FlightBookings>();
        Holder<HotelBookings> hotelBookings = new Holder<HotelBookings>();       
        client.getItinerary(testId, flightBookings, hotelBookings);
        
        assertEquals(1,flightBookings.value.getFlightBooking().size());
        FlightInformation info1 = flights.getFlights().get(0);
        FlightInformation info2 = flightBookings.value.getFlightBooking().get(0).getFlightInformation();
        assertTrue(compareFlightInformation(info1, info2));
        assertEquals(0,hotelBookings.value.getHotelBooking().size());
    }
    
    private boolean compareFlightInformation(FlightInformation info1, FlightInformation info2){
        return info1.getBookingNo().equals(info2.getBookingNo()) &&
                compareFlights(info1.getFlight(), info2.getFlight()) &&
                info1.getPrice() == info2.getPrice() &&
                info1.getReservationService().equals(info2.getReservationService());
                
    }
    
    private boolean compareFlights(Flight flight1, Flight flight2){
        return flight1.getArrival().equals(flight2.getArrival()) &&
                flight1.getCarrier().equals(flight2.getCarrier()) &&
                flight1.getDestination().equals(flight2.getDestination()) &&
                flight1.getLiftOff().equals(flight2.getLiftOff()) &&
                flight1.getOrigin().equals(flight2.getOrigin());
    }
}
