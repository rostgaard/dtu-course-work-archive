package travelgood;

import javax.xml.ws.soap.SOAPFaultException;
import org.junit.*;
import static org.junit.Assert.*;
import ws.dtu.lameduck.types.FlightList;
import ws.dtu.niceview.types.HotelList;


public class TravelGoodBookingTest extends TravelGoodTest {
    HotelList hotels;
    FlightList flights;
    
    @Before
    public void setupPlan() {
        hotels = TravelGoodClient.getHotels(customerID, itineraryID, "San Francisco", date1, date2);
        flights = TravelGoodClient.getFlights(customerID, itineraryID, "CPH", "SFO", date1);
        
        TravelGoodClient.addFlight(customerID, itineraryID, flights.getFlights().get(0));
        TravelGoodClient.addHotel(customerID, itineraryID, hotels.getHotels().get(0));
        
        TravelGoodClient.getItinerary(customerID, itineraryID, flightBookings, hotelBookings);
        assertTrue(flightBookings.value.getFlightBooking().size() > 0);
        assertTrue(hotelBookings.value.getHotelBooking().size() > 0);
    }
    
    @Test
    public void testBookSuccess() {
        assertTrue(TravelGoodClient.bookItinerary(customerID, itineraryID, cc));
    }
    
    @Test
    public void testBookFailure() {
        hotels = TravelGoodClient.getHotels(customerID, itineraryID, "Crazy Town", date1, date2);
        TravelGoodClient.addHotel(customerID, itineraryID, hotels.getHotels().get(0));
        assertFalse(TravelGoodClient.bookItinerary(customerID, itineraryID, cc));
        
        TravelGoodClient.getItinerary(customerID, itineraryID, flightBookings, hotelBookings);
        
        assertEquals("canceled", hotelBookings.value.getHotelBooking().get(0).getStatus());
        assertEquals("unconfirmed", hotelBookings.value.getHotelBooking().get(1).getStatus());
    }
    
    @Test
    public void testCancelSuccess() {
        assertTrue(TravelGoodClient.bookItinerary(customerID, itineraryID, cc));
        assertTrue(TravelGoodClient.cancelItinerary(customerID, itineraryID, cc));
    }
    
    @Test
    public void testCancelFailure() throws InterruptedException {
        TravelGoodClient.addHotel(customerID, itineraryID, hotels.getHotels().get(2));
        assertTrue(TravelGoodClient.bookItinerary(customerID, itineraryID, cc));
        
        assertFalse(TravelGoodClient.cancelItinerary(customerID, itineraryID, cc));
        Thread.sleep(2000);
        
        try {
            TravelGoodClient.getItinerary(customerID, itineraryID, flightBookings, hotelBookings);
        } catch (SOAPFaultException e) {
            fail();
        }
    }
    
    @Test
    public void testBookSuccessExpire() throws InterruptedException {
        assertTrue(TravelGoodClient.bookItinerary(customerID, itineraryID, cc));
        Thread.sleep(2000);
        
        try {
            TravelGoodClient.getItinerary(customerID, itineraryID, flightBookings, hotelBookings);
        } catch (SOAPFaultException e) {
            fail();
        }
        
        /*
        Thread.sleep(5000);
        
        boolean exception = false;
        try {
            TravelGoodClient.getItinerary(customerID, itineraryID, flightBookings, hotelBookings);
        } catch (SOAPFaultException e) {
            exception = true;
        }
        
        assertTrue(exception);*/
    }
}
