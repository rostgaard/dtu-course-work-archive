import org.junit.Test;
import static org.junit.Assert.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import ws.travelgoodbpel.*;

public class TravelGoodTest {

    @Test
    public void testAddFlight() {
        FlightBookings bookings;
        createItinerary("MinMor");
        addFlight("MinMor", "FØRSTE");
        addFlight("MinMor", "ANDEN");
        addFlight("MinMor", "TREDJE");
        addFlight("MinMor", "FJERDE");
        
        bookings = getItinerary("MinMor");
        
        
        System.out.println("Count: "+bookings.getFlightBooking().size());
        for (FlightBooking booking : bookings.getFlightBooking()) {
            System.out.println("=====");
            System.out.println(booking.getFlightID());
            System.out.println(booking.getStatus());
        }
    }

    private static boolean addFlight(String itineraryID, String flightID) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.addFlight(itineraryID, flightID);
    }

    private static FlightBookings getItinerary(String itineraryID) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.getItinerary(itineraryID);
    }

    private static boolean createItinerary(java.lang.String itineraryID) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.createItinerary(itineraryID);
    }
}
