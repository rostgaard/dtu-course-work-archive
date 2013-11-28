package travelgood;

import org.junit.*;
import static org.junit.Assert.*;
import servicewrappers.BPELWrapper;
import ws.dtu.lameduck.types.Flight;
import ws.dtu.lameduck.types.FlightInformation;
import ws.dtu.lameduck.types.FlightList;

public class TravelGoodPlanningTest extends TravelGoodTest {

    public TravelGoodPlanningTest() {
        client = new BPELWrapper();
    }

    @After
    public void after() {
        client.cancelPlanning(customerID, itineraryID);
    }

    @Test
    public void testCreatedEmptyItinerary() {
        client.getItinerary(customerID, itineraryID, flightBookings, hotelBookings);
        assertEquals(0, flightBookings.value.getFlightBooking().size());
        assertEquals(0, hotelBookings.value.getHotelBooking().size());
    }

    @Test
    public void testAddFlight() {
        String origin = "Copenhagen";
        String destination = "Kabul";

        FlightList flights = client.getFlights(customerID, itineraryID, origin, destination, date1);
        assertEquals(3, flights.getFlights().size());
        client.addFlight(customerID, itineraryID, flights.getFlights().get(0));

        client.getItinerary(customerID, itineraryID, flightBookings, hotelBookings);
        assertEquals(1, flightBookings.value.getFlightBooking().size());
        
        FlightInformation info1 = flights.getFlights().get(0);
        FlightInformation info2 = flightBookings.value.getFlightBooking().get(0).getFlightInformation();
        
        assertTrue(compareFlightInformation(info1, info2));
        assertEquals(0, hotelBookings.value.getHotelBooking().size());
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
}
