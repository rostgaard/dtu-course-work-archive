package travelgood;

import java.util.Random;
import javax.xml.ws.soap.SOAPFaultException;
import org.junit.*;
import static org.junit.Assert.*;
import ws.dtu.lameduck.types.Flight;
import ws.dtu.lameduck.types.FlightInformation;
import ws.dtu.lameduck.types.FlightList;
import ws.dtu.niceview.types.HotelInformation;
import ws.dtu.niceview.types.HotelList;

public class TravelGoodPlanningTest extends TravelGoodTest {
    private HotelList HotelList;
    
    public TravelGoodPlanningTest() {
        Random r = new Random();
        customerID += r.nextInt(10000);
    }

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
        
        assertTrue(compareFlightInformation(info1, info2));
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
        assertEquals(hotels.getHotels().size(), 3);
        
        TravelGoodClient.addHotel(customerID, itineraryID, hotels.getHotels().get(0).getBookingNo());
        
        TravelGoodClient.getItinerary(customerID, itineraryID, flightBookings, hotelBookings);
        
        assertEquals(hotelBookings.value.getHotelBooking().size(),1);
        
        
        
        
    }

    private boolean compareFlightInformation(FlightInformation info1, FlightInformation info2) {
        return info1.getBookingNo().equals(info2.getBookingNo())
                && compareFlights(info1.getFlight(), info2.getFlight())
                && info1.getPrice() == info2.getPrice()
                && info1.getReservationSevice().equals(info2.getReservationSevice());
    }

    private boolean compareFlights(Flight flight1, Flight flight2) {
        return flight1.getArrival().equals(flight2.getArrival())
                && flight1.getCarrier().equals(flight2.getCarrier())
                && flight1.getDestination().equals(flight2.getDestination())
                && flight1.getLiftOff().equals(flight2.getLiftOff())
                && flight1.getOrigin().equals(flight2.getOrigin());
    }
    
    private boolean compareHotels(HotelInformation hotel1, HotelInformation hotel2){
        return hotel1.getAddress().equals(hotel2.getAddress())
                && hotel1.getBookingNo().equals(hotel2.getBookingNo())
                && hotel1.getName().equals(hotel2.getName())
                && hotel1.getPrice() == hotel2.getPrice()
                && hotel1.getReservationService().equals(hotel2.getReservationService());
    }
}
