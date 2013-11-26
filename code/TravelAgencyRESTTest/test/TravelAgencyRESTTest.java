/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.core.MultivaluedMap;
import model.FlightBooking;
import model.FlightBooking.FlightBookingState;
import model.FlightBookingList;
import model.HotelBooking;
import model.HotelBookingList;
import model.Itinerary;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;



/**
 *
 * @author peter
 */
public class TravelAgencyRESTTest {
    private static String baseURL = "http://localhost:8080/ta/webresources/";
    String MEDIATYPE = "application/itinerary+xml";

    private WebResource flightResource;
    private WebResource hotelResource;
    private WebResource itineraryResource;
    private WebResource resetResource;

    private Client client;
    
    private final String customerID = "1";
    
    
    public TravelAgencyRESTTest() {
    }
    
    
    @Before
    public void setUp() {
        client = new Client();
        flightResource = client.resource(baseURL+"flight");
        hotelResource = client.resource(baseURL+"hotel");
        itineraryResource = client.resource(baseURL+"itinerary");
        resetResource = client.resource(baseURL+"reset");
    }
    
    @After
    public void tearDown() {
        resetResource.post();
    }
    
     @Test
     public void testCreateItinerary() {
         ClientResponse response = itineraryResource.queryParam("customer_id", customerID).post(ClientResponse.class);
        
         assertEquals(201, response.getStatus());
         assertNotNull(response.getLocation());
     }
     
     @Test
     public void testGetItinerary() {
         ClientResponse response = itineraryResource.queryParam("customer_id", customerID).post(ClientResponse.class);
         
         WebResource resource = client.resource(response.getLocation()).queryParam("customer_id", customerID);
         Itinerary itinerary = resource.get(Itinerary.class);
         assertNotNull(itinerary.getID());
     }
     
     @Test
     public void testGetFlightsNone() {
         MultivaluedMap queryParams = new MultivaluedMapImpl();
         queryParams.add("date", "2013-11-17");
         queryParams.add("origin", "Kastrup");
         queryParams.add("destination", "Aalborg");

         WebResource resource = flightResource.queryParams(queryParams);
         FlightBookingList flightBookingList = resource.get(FlightBookingList.class);
         
         assertEquals(0, flightBookingList.getFlights().size());
     }
     
     @Test
     public void testGetFlights() {
         MultivaluedMap queryParams = new MultivaluedMapImpl();
         queryParams.add("date", "2013-11-17");
         queryParams.add("origin", "Kastrup");
         queryParams.add("destination", "Kabul");
         WebResource resource = flightResource.queryParams(queryParams);
         FlightBookingList flightBookingList = resource.get(FlightBookingList.class);
         
         assertEquals(3, flightBookingList.getFlights().size());
     }
     
     @Test
     public void testP1() {
         // Create itineraty
         ClientResponse createIitneraryRsponse = itineraryResource.queryParam("customer_id", customerID).post(ClientResponse.class);
         WebResource itineraryResourceLocation = client.resource(createIitneraryRsponse.getLocation()).queryParam("customer_id", customerID);
         Itinerary preItinerary = itineraryResourceLocation.get(Itinerary.class);         
         
         // Get flights
         MultivaluedMap queryParamsFlight = new MultivaluedMapImpl();
         queryParamsFlight.add("date", "2013-11-17");
         queryParamsFlight.add("origin", "Kastrup");
         queryParamsFlight.add("destination", "Kabul");
         WebResource flightResource1 = flightResource.queryParams(queryParamsFlight);
         FlightBookingList flightBookingList = flightResource1.get(FlightBookingList.class);
         FlightBooking flightBooking1 = flightBookingList.getFlights().get(0);
         FlightBooking flightBooking2 = flightBookingList.getFlights().get(1);
         FlightBooking flightBooking3 = flightBookingList.getFlights().get(2);

         // Get hotels
         MultivaluedMap queryParamsHotel = new MultivaluedMapImpl();
         queryParamsHotel.add("origin", "Kabul");
         queryParamsHotel.add("arrival_date", "2013-11-17");
         queryParamsHotel.add("departure_date", "2013-11-18");
         WebResource hotelResource1 = hotelResource.queryParams(queryParamsHotel);
         HotelBookingList hotelBookingList = hotelResource1.get(HotelBookingList.class);
         HotelBooking hotelBooking1 = hotelBookingList.getHotels().get(0);
         HotelBooking hotelBooking2 = hotelBookingList.getHotels().get(1);

         // Book flight
         WebResource itineraryFlightResource = itineraryResource.path(preItinerary.getID()+"/flight").queryParam("customer_id", customerID);
         itineraryFlightResource.accept(MEDIATYPE).type(MEDIATYPE).put(flightBooking1);
         
         // Book hotel
         WebResource itineraryHotelResource = itineraryResource.path(preItinerary.getID()+"/hotel").queryParam("customer_id", customerID);
         itineraryHotelResource.accept(MEDIATYPE).type(MEDIATYPE).put(hotelBooking1);
         
         // Two more flights
        itineraryFlightResource.accept(MEDIATYPE).type(MEDIATYPE).put(flightBooking2);
        itineraryFlightResource.accept(MEDIATYPE).type(MEDIATYPE).put(flightBooking3);

        // Finally, another hotel
        itineraryHotelResource.accept(MEDIATYPE).type(MEDIATYPE).put(hotelBooking2);

         // Get the flight and hotels in the itinerary
         Itinerary intermediaItinerary = itineraryResourceLocation.get(Itinerary.class);
         flightBookingList = intermediaItinerary.getFlightBookings();
         hotelBookingList = intermediaItinerary.getHotelBookings();
         
         // Verify that flights and hotels were added
         assertEquals(3, flightBookingList.getFlights().size());
         assertEquals(2, hotelBookingList.getHotels().size());
           
         // Verify status of flights
         for(FlightBooking fb : flightBookingList.getFlights()) {
             assertTrue(fb.getBookingState()==FlightBookingState.UNBOOKED);
         }
         // Verify status of hotels
         for(HotelBooking hb : hotelBookingList.getHotels()) {
             assertTrue(hb.getBookingState()==HotelBooking.HotelBookingState.UNBOOKED);
         }
         
         // Book the itinerary
         WebResource bookItineraryResource = itineraryResource.path(""+intermediaItinerary.getID()).queryParam("customer_id", customerID);;
         bookItineraryResource.put();
         
         // Get the itinerary again
         Itinerary finaltinerary = itineraryResourceLocation.get(Itinerary.class);
         flightBookingList = finaltinerary.getFlightBookings();
         hotelBookingList = finaltinerary.getHotelBookings();

         // Verify status of flights
         for(FlightBooking fb : flightBookingList.getFlights()) {
             assertTrue(fb.getBookingState()==FlightBookingState.BOOKED);
         }
         
         // Verify status of hotels
         for(HotelBooking hb : hotelBookingList.getHotels()) {
             assertTrue(hb.getBookingState()==HotelBooking.HotelBookingState.BOOKED);
         }
     } 
     
     @Test
     public void testP2() {
         // Create itinerary
         ClientResponse createIitneraryRsponse = itineraryResource.queryParam("customer_id", customerID).post(ClientResponse.class);
         WebResource itineraryResourceLocation = client.resource(createIitneraryRsponse.getLocation()).queryParam("customer_id", customerID);
         Itinerary preItinerary = itineraryResourceLocation.get(Itinerary.class);  
         
         // Get flights
         MultivaluedMap queryParams = new MultivaluedMapImpl();
         queryParams.add("date", "2013-11-17");
         queryParams.add("origin", "Kastrup");
         queryParams.add("destination", "Kabul");
         WebResource flightResource1 = flightResource.queryParams(queryParams).queryParam("customer_id", customerID);
         FlightBookingList flightBookingList = flightResource1.get(FlightBookingList.class);
         
         // Add flight to itinerary
         FlightBooking flightBooking = flightBookingList.getFlights().get(0);
         WebResource itineraryFlightResource = itineraryResource.path(preItinerary.getID()+"/flight").queryParam("customer_id", customerID);
         itineraryFlightResource.accept(MEDIATYPE).type(MEDIATYPE).put(flightBooking);
         
         // Get the itinerary
         Itinerary intermediaItinerary = itineraryResourceLocation.get(Itinerary.class);
         
         // Verify that the flight was added
         FlightBooking flightBookingActual = intermediaItinerary.getFlightBookings().getFlights().get(0);
         assertEquals(flightBookingActual.getFlightInformation().getBookingNo(), flightBooking.getFlightInformation().getBookingNo());
         
         // Cancel the itinerary
         itineraryResourceLocation.delete();
         
         // Verify that itinerary does not exist anymore
         ClientResponse response = itineraryResourceLocation.get(ClientResponse.class);
         assertEquals(404, response.getStatus());
     }
//     
//     @Test
//     public void testB() {
//         
//     }
//     
//     @Test
//     public void testC1() {
//         
//     } 
//     
//     
//     @Test
//     public void testC2() {
//         
//     } 
}
