
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.core.MultivaluedMap;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class RestService {
    
    private final static String baseURL = "http://localhost:8080/ta/webresources/";
    private final static String MEDIATYPE = "application/itinerary+xml";
    
    
    private final static Client client =  new Client();

    private final static WebResource flightResource = client.resource(baseURL+"flight");
    private final static  WebResource hotelResource = client.resource(baseURL+"hotel");
    private final static  WebResource itineraryResource = client.resource(baseURL+"itinerary");
    private final static WebResource resetResource = client.resource(baseURL+"reset");
    
    
    public static ClientResponse getURI(String uri, String customerID) {
        WebResource resource = client.resource(uri).queryParam("customer_id", customerID);
        return resource.accept(MEDIATYPE).type(MEDIATYPE).get(ClientResponse.class);
    }
    
    public static ClientResponse postURI(String uri, String customerID, Object obj) {
        WebResource resource = client.resource(uri).queryParam("customer_id", customerID);
        return resource.accept(MEDIATYPE).type(MEDIATYPE).post(ClientResponse.class, obj);
    }    
    
    public static ClientResponse postURI(String uri, String customerID) {
        WebResource resource = client.resource(uri).queryParam("customer_id", customerID);
        return resource.accept(MEDIATYPE).type(MEDIATYPE).post(ClientResponse.class);
    } 
    
    public static ClientResponse putURI(String uri, String customerID, Object obj) {
        WebResource resource = client.resource(uri).queryParam("customer_id", customerID);
        return resource.accept(MEDIATYPE).type(MEDIATYPE).put(ClientResponse.class, obj);
    }
    
    public static ClientResponse putURI(String uri, String customerID) {
        WebResource resource = client.resource(uri).queryParam("customer_id", customerID);
        return resource.accept(MEDIATYPE).type(MEDIATYPE).put(ClientResponse.class);
    }

    public static ClientResponse deleteURI(String uri, String customerID) {
        WebResource resource = client.resource(uri).queryParam("customer_id", customerID);
        return resource.accept(MEDIATYPE).type(MEDIATYPE).delete(ClientResponse.class);
    }
    
    public static ClientResponse createItinerary(String customerID) {
        return itineraryResource.queryParam("customer_id", customerID).accept(MEDIATYPE).type(MEDIATYPE).post(ClientResponse.class);
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
    
    public static void reset() {
        resetResource.put();
    }
}
