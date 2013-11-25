import javax.xml.ws.Holder;
import org.junit.Test;
import static org.junit.Assert.*;
import ws.dtu.lameduck.types.FlightInformation;
import ws.travelgoodbpel.*;

public class TravelGoodTest {

    @Test
    public void testAddFlight() {
        createItinerary("MinMor");
        addFlight("MinMor", new FlightInformation());
        addFlight("MinMor", new FlightInformation());
        addFlight("MinMor", new FlightInformation());
        addFlight("MinMor", new FlightInformation());
        
        Holder<FlightBookings> flights = new Holder<FlightBookings>();
        Holder<HotelBookings> hotels = new Holder<HotelBookings>();
        getItinerary("MinMor", flights, hotels);
        
        
        assertTrue(flights.value.getFlightBooking().size() == 4);
        
        cancelPlanning("MinMor");
    }


    private static boolean createItinerary(java.lang.String itineraryID) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.createItinerary(itineraryID);
    }

    private static String cancelPlanning(java.lang.String itineraryID) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.cancelPlanning(itineraryID);
    }

    private static boolean addFlight(java.lang.String itineraryID, ws.dtu.lameduck.types.FlightInformation flightInformation) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.addFlight(itineraryID, flightInformation);
    }

    private static void getItinerary(java.lang.String itineraryID, javax.xml.ws.Holder<ws.travelgoodbpel.FlightBookings> flights, javax.xml.ws.Holder<ws.travelgoodbpel.HotelBookings> hotels) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        port.getItinerary(itineraryID, flights, hotels);
    }

}
