
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.net.URI;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.ws.Response;
import model.FlightBooking;
import model.FlightBookingList;
import model.HotelBooking;
import model.HotelBookingList;
import model.Itinerary;
import ws.dtu.lameduck.types.FlightList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author peter
 */
public class RestService {
    
    private final static String baseURL = "http://localhost:8080/ta/webresources/";
    private final static String MEDIATYPE = "application/itinerary+xml";
    
    
    private final static Client client =  new Client();

    private final static WebResource flightResource = client.resource(baseURL+"flight");
    private final static  WebResource hotelResource = client.resource(baseURL+"hotel");
    private final static  WebResource itineraryResource = client.resource(baseURL+"itinerary");
    private final static WebResource resetResource = client.resource(baseURL+"reset");
    
    public static ClientResponse createItinerary(String customerID) {
        return itineraryResource.queryParam("customer_id", customerID).post(ClientResponse.class);
    }
    
    public static ClientResponse removeItinerary(String customerID, int itineraryID) {
        WebResource cancelItineraryResource = itineraryResource.path(""+itineraryID).queryParam("customer_id", customerID);;
        return cancelItineraryResource.delete(ClientResponse.class);
    } 
       
    public static ClientResponse getItinerary(String customerID, URI location) {
         WebResource resource = client.resource(location).queryParam("customer_id", customerID);
         return resource.get(ClientResponse.class);
    } 

    public static ClientResponse bookItinerary(String customerID, int itineraryID) {
         WebResource bookItineraryResource = itineraryResource.path(itineraryID+"/booking").queryParam("customer_id", customerID);;
         return bookItineraryResource.put(ClientResponse.class);
    }

    public static ClientResponse cancelItinerary(String customerID, int itineraryID) {
         WebResource cancelItineraryResource = itineraryResource.path(itineraryID+"/booking").queryParam("customer_id", customerID);;
         return cancelItineraryResource.delete(ClientResponse.class);
    }


    public static ClientResponse getFlights(String origin, String destination, String dateString) {
         MultivaluedMap queryParams = new MultivaluedMapImpl();
         queryParams.add("date", dateString);
         queryParams.add("origin", origin);
         queryParams.add("destination", destination);

         WebResource resource = flightResource.queryParams(queryParams);
         return resource.get(ClientResponse.class);
    }
    

    public static ClientResponse getHotels(java.lang.String customerID, String city, String arrival, String departure) {
         MultivaluedMap queryParamsHotel = new MultivaluedMapImpl();
         queryParamsHotel.add("origin", city);
         queryParamsHotel.add("arrival_date", arrival);
         queryParamsHotel.add("departure_date", departure);
         WebResource hotelResource1 = hotelResource.queryParams(queryParamsHotel);
         return hotelResource1.get(ClientResponse.class);
    }


    
    public static void addFlight(String customerID, int itineraryID, FlightBooking flightBooking) {
         WebResource itineraryFlightResource = itineraryResource.path(itineraryID+"/flight").queryParam("customer_id", customerID);
         itineraryFlightResource.accept(MEDIATYPE).type(MEDIATYPE).put(flightBooking);
    }

    public static void addHotel(String customerID, int itineraryID, HotelBooking hotelBooking) {
         WebResource itineraryHotelResource = itineraryResource.path(itineraryID+"/hotel").queryParam("customer_id", customerID);
         itineraryHotelResource.accept(MEDIATYPE).type(MEDIATYPE).put(hotelBooking);
    }
    
    public static void reset() {
        resetResource.put();
    }
}
