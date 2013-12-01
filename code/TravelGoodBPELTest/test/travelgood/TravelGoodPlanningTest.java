package travelgood;

import javax.xml.ws.soap.SOAPFaultException;
import org.junit.*;
import static org.junit.Assert.*;
import ws.dtu.lameduck.types.FlightInformation;
import ws.dtu.lameduck.types.FlightList;
import ws.dtu.niceview.types.HotelList;

public class TravelGoodPlanningTest extends TravelGoodTest {

    @After
    public void after() {
        TravelGoodClient.cancelPlanning(customerID, itineraryID);
    }

    @Test
    public void testCreatedEmptyItinerary() {
        TravelGoodClient.getItinerary(customerID, itineraryID, flightBookings, hotelBookings);
        assertEquals(0, flightBookings.value.getFlightBooking().size());
        assertEquals(0, hotelBookings.value.getHotelBooking().size());
    }
    
    @Test
    public void testGetFlights(){
        FlightList flights = TravelGoodClient.getFlights(customerID, itineraryID, "CPH", "SFO", date1);
        assertEquals(flights.getFlights().size(),3);
    }
    
    @Test
    public void testGetFlightsEmpty(){
        FlightList flights = TravelGoodClient.getFlights(customerID, itineraryID, "SomeRandomCity", "SomeNonExistingCity", date1);
        assertEquals(flights.getFlights().size(),0);
    }

    @Test
    public void testAddFlight() {
        FlightList flights = TravelGoodClient.getFlights(customerID, itineraryID, "CPH", "SFO", date1);
        assertEquals(3, flights.getFlights().size());
        TravelGoodClient.addFlight(customerID, itineraryID, flights.getFlights().get(0));

        TravelGoodClient.getItinerary(customerID, itineraryID, flightBookings, hotelBookings);
        assertEquals(1, flightBookings.value.getFlightBooking().size());
        
        FlightInformation info1 = flights.getFlights().get(0);
        FlightInformation info2 = flightBookings.value.getFlightBooking().get(0).getFlightInformation();
        
        TestHelpers.compareFlightInformation(info1, info2);
        assertEquals(0, hotelBookings.value.getHotelBooking().size());
    }
    
    @Test
    public void testPlanningCancel() {
        TravelGoodClient.cancelPlanning(customerID, itineraryID);
        TravelGoodClient.createItinerary(customerID, itineraryID);
    }
    
    @Test(expected = SOAPFaultException.class)
    public void testCreateFaillure(){
        // Try to create a double Itinerary
        TravelGoodClient.createItinerary(customerID, itineraryID);
    }
    
    @Test
    public void testAddHotel(){
        HotelList hotels = TravelGoodClient.getHotels(customerID, itineraryID, "San Francisco", date1, date2);
        assertEquals(3, hotels.getHotels().size());
        
        TravelGoodClient.addHotel(customerID, itineraryID, hotels.getHotels().get(0));
        TravelGoodClient.getItinerary(customerID, itineraryID, flightBookings, hotelBookings);
        
        assertEquals(hotelBookings.value.getHotelBooking().size(),1);
        TestHelpers.compareHotelInformation(hotelBookings.value.getHotelBooking().get(0).getHotelInformation(), hotels.getHotels().get(0));
    }
    
    @Test
    public void getHotelsEmpty(){
        HotelList hotels = TravelGoodClient.getHotels(customerID, itineraryID, "SillyTown666", date1, date1);
        assertEquals(hotels.getHotels().size(), 0);
    }

}
