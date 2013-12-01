/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.jersey.api.client.ClientResponse;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import ws.dtu.model.*;
import ws.dtu.representation.ItineraryRepresentation;
import ws.dtu.representation.Link;

/**
 * 
 * @author peter
 */

public class TravelAgencyRESTTest {
    
    private final static  String customerID = "1";
    
    protected static final String BASE_RELATION = "http://itinerary.ws/relations/";
    protected static final String BASE_URL = "http://localhost:8080/ta/webresources/";

    protected static final String SELF_RELATION = BASE_RELATION + "self";
    protected static final String REMOVE_RELATION = BASE_RELATION + "remove";    
    protected static final String CANCEL_RELATION = BASE_RELATION + "cancel";
    protected static final String BOOKING_RELATION = BASE_RELATION + "booking";
    protected static final String FLIGHT_RELATION = BASE_RELATION + "flights";
    protected static final String HOTEL_RELATION = BASE_RELATION + "hotels";
    
    public TravelAgencyRESTTest() {
    }
    
    
    @Before
    public void setUp() {

    }
    
    @After
    public void tearDown() {
        RestService.reset();
    }
    
     @Test
     public void testCreateItinerary() {
        ClientResponse response = RestService.createItinerary(customerID);
        ItineraryRepresentation itineraryRepresentation = response.getEntity(ItineraryRepresentation.class);
         
         assertEquals(201, response.getStatus());
         assertNotNull(itineraryRepresentation.getItinerary().getID());
     }
     
     @Test
     public void testGetItinerary() {
         ItineraryRepresentation itineraryRepresentation = RestService.createItinerary(customerID).getEntity(ItineraryRepresentation.class);
         
         Link selfLink = itineraryRepresentation.getLinkByRelation(SELF_RELATION);
         itineraryRepresentation = RestService.getURI(selfLink.getURI(), customerID).getEntity(ItineraryRepresentation.class);//RestService.getItinerary(customerID, response.getLocation()).getEntity(Itinerary.class);
         
         assertNotNull(itineraryRepresentation.getItinerary().getID());
     }
     
     @Test
     public void testGetFlightsNone() {
         FlightBookingList flightBookingList = RestService.getFlights("CPH", "AAL", "2013-11-17").getEntity(FlightBookingList.class);
         
         assertEquals(0, flightBookingList.getFlights().size());
     }
     
     @Test
     public void testGetFlights() {
         FlightBookingList flightBookingList = RestService.getFlights("CPH", "SFO", "2013-11-17").getEntity(FlightBookingList.class);
         
         assertEquals(3, flightBookingList.getFlights().size());
     }
}