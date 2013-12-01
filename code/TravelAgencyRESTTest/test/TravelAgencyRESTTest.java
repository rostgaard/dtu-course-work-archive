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
import ws.dtu.model.FlightBooking.FlightBookingState;
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
     
     @Test
     public void testP1() {
         // Create itineraty
         ItineraryRepresentation itineraryRepresentation = RestService.createItinerary(customerID).getEntity(ItineraryRepresentation.class);  
         
         //Links
         Link selfLink = itineraryRepresentation.getLinkByRelation(SELF_RELATION);
         Link bookLink = itineraryRepresentation.getLinkByRelation(BOOKING_RELATION);         
         Link flightLink = itineraryRepresentation.getLinkByRelation(FLIGHT_RELATION);
         Link hotelLink = itineraryRepresentation.getLinkByRelation(HOTEL_RELATION);
            
         // Get flights
         FlightBookingList flightBookingList = RestService.getFlights("CPH", "SFO", "2013-11-17").getEntity(FlightBookingList.class);
         FlightBooking flightBooking1 = flightBookingList.getFlights().get(0);
         FlightBooking flightBooking2 = flightBookingList.getFlights().get(1);
         FlightBooking flightBooking3 = flightBookingList.getFlights().get(2);

         // Get hotels
         HotelBookingList hotelBookingList = RestService.getHotels(customerID, "Copenhagen", "2013-11-17", "2013-11-18").getEntity(HotelBookingList.class);
         HotelBooking hotelBooking1 = hotelBookingList.getHotels().get(0);
         HotelBooking hotelBooking2 = hotelBookingList.getHotels().get(1);

         // Add flight
         RestService.putURI(flightLink.getURI(), customerID, flightBooking1);
         
         // Add hotel
         RestService.putURI(hotelLink.getURI(), customerID, hotelBooking1);
         
         // Two more flights
         RestService.putURI(flightLink.getURI(), customerID, flightBooking2);
         RestService.putURI(flightLink.getURI(), customerID, flightBooking3);

        // Finally, another hotel
         RestService.putURI(hotelLink.getURI(), customerID, hotelBooking2);         

         // Get itinerary
         itineraryRepresentation = RestService.getURI(selfLink.getURI(), customerID).getEntity(ItineraryRepresentation.class);
         flightBookingList = itineraryRepresentation.getItinerary().getFlightBookings();
         hotelBookingList = itineraryRepresentation.getItinerary().getHotelBookings();
         
         // Verify that flights and hotels were added
         assertEquals(3, flightBookingList.getFlights().size());
         assertEquals(2, hotelBookingList.getHotels().size());
           
         // Verify status of flights
         for(FlightBooking fb : flightBookingList.getFlights()) {
             assertTrue(fb.getBookingState()==FlightBookingState.UNCONFIRMED);
         }
         // Verify status of hotels
         for(HotelBooking hb : hotelBookingList.getHotels()) {
             assertTrue(hb.getBookingState()==HotelBooking.HotelBookingState.UNCONFIRMED);
         }
         
         // Book the itinerary
         RestService.putURI(bookLink.getURI(), customerID);
         
         // Get the itinerary again
         itineraryRepresentation = RestService.getURI(selfLink.getURI(), customerID).getEntity(ItineraryRepresentation.class);
         flightBookingList = itineraryRepresentation.getItinerary().getFlightBookings();
         hotelBookingList = itineraryRepresentation.getItinerary().getHotelBookings();

         // Verify status of flights
         for(FlightBooking fb : flightBookingList.getFlights()) {
             assertTrue(fb.getBookingState()==FlightBookingState.CONFIRMED);
         }
         
         // Verify status of hotels
         for(HotelBooking hb : hotelBookingList.getHotels()) {
             assertTrue(hb.getBookingState()==HotelBooking.HotelBookingState.CONFIRMED);
         }
     } 
     
     @Test
     public void testP2() {
         // Create itinerary
         ItineraryRepresentation itineraryRepresentation = RestService.createItinerary(customerID).getEntity(ItineraryRepresentation.class);  
         
         //Links
         Link selfLink = itineraryRepresentation.getLinkByRelation(SELF_RELATION);
         Link flightLink = itineraryRepresentation.getLinkByRelation(FLIGHT_RELATION);
         Link removeLink = itineraryRepresentation.getLinkByRelation(REMOVE_RELATION);
         
         // Get flights
         FlightBookingList flightBookingList = RestService.getFlights("CPH", "SFO", "2013-11-17").getEntity(FlightBookingList.class);
         FlightBooking flightBooking = flightBookingList.getFlights().get(0);

         // Add flight to itinerary
         RestService.putURI(flightLink.getURI(), customerID, flightBooking);
                  
         // Get the itinerary
         itineraryRepresentation = RestService.getURI(selfLink.getURI(), customerID).getEntity(ItineraryRepresentation.class);
                  
         // Verify that the flight was added
         FlightBooking flightBookingActual = itineraryRepresentation.getItinerary().getFlightBookings().getFlights().get(0);
         assertEquals(flightBookingActual.getFlightInformation().getBookingNo(), flightBooking.getFlightInformation().getBookingNo());
         
         // Delete the itinerary
         RestService.deleteURI(removeLink.getURI(), customerID);
         
         // Verify that itinerary does not exist anymore
         ClientResponse response = RestService.getURI(selfLink.getURI(), customerID);
         assertEquals(404, response.getStatus());
     }
     
     @Test
     public void testB() {
         // Get hotels
         HotelBookingList hotelBookingList = RestService.getHotels(customerID, "Copenhagen", "2013-11-17", "2013-11-18").getEntity(HotelBookingList.class);
         HotelBooking hotelBooking = hotelBookingList.getHotels().get(0);
         
         // Get flights
         FlightBookingList flightBookingList = RestService.getFlights("CPH", "SFO", "2013-11-17").getEntity(FlightBookingList.class);
         FlightBooking flightBooking = flightBookingList.getFlights().get(0);
         
         // Create itinerary
         ItineraryRepresentation itineraryRepresentation = RestService.createItinerary(customerID).getEntity(ItineraryRepresentation.class);  

         //Links
         Link selfLink = itineraryRepresentation.getLinkByRelation(SELF_RELATION);
         Link bookLink = itineraryRepresentation.getLinkByRelation(BOOKING_RELATION);         
         Link flightLink = itineraryRepresentation.getLinkByRelation(FLIGHT_RELATION);
         Link hotelLink = itineraryRepresentation.getLinkByRelation(HOTEL_RELATION);         
         
         // Add flight to itinerary
         RestService.putURI(flightLink.getURI(), customerID, flightBooking);
         
         // Add the flight again. Should not be allowed to book
         RestService.putURI(flightLink.getURI(), customerID, flightBooking);
         
         // Add hotel to itinerary
         RestService.putURI(hotelLink.getURI(), customerID, hotelBooking);         
         
         //Get itinerary
         itineraryRepresentation = RestService.createItinerary(customerID).getEntity(ItineraryRepresentation.class);           
         flightBookingList = itineraryRepresentation.getItinerary().getFlightBookings();
         hotelBookingList = itineraryRepresentation.getItinerary().getHotelBookings();
         
         // Verify status of flights
         for(FlightBooking fb : flightBookingList.getFlights()) {
             assertTrue(fb.getBookingState()==FlightBookingState.UNCONFIRMED);
         }
         
         // Verify status of hotels
         for(HotelBooking hb : hotelBookingList.getHotels()) {
             assertTrue(hb.getBookingState()==HotelBooking.HotelBookingState.UNCONFIRMED);
         }
         
         // Book itinerary
         ClientResponse bookResponse = RestService.putURI(bookLink.getURI(), customerID);
         
         // Check that error was recieved
         assertEquals(406, bookResponse.getStatus());
         
          //Get itinerary
         itineraryRepresentation = RestService.getURI(selfLink.getURI(), customerID).getEntity(ItineraryRepresentation.class);
         
         // verify second fligt and  hotel unbooked
         FlightBooking flightBooking2 = itineraryRepresentation.getItinerary().getFlightBookings().getFlights().get(1);
         assertEquals(FlightBookingState.UNCONFIRMED, flightBooking2.getBookingState());
         HotelBooking hotelBooking1 = itineraryRepresentation.getItinerary().getHotelBookings().getHotels().get(0);
         assertEquals(HotelBooking.HotelBookingState.UNCONFIRMED, hotelBooking1.getBookingState());
         
         // verify first flight is cancelled
         FlightBooking flightBooking1 = itineraryRepresentation.getItinerary().getFlightBookings().getFlights().get(0);
         assertEquals(FlightBookingState.CANCELLED, flightBooking1.getBookingState());
     }
     
     @Test
     public void testC1() {
         // Create itinerary
         ItineraryRepresentation itineraryRepresentation = RestService.createItinerary(customerID).getEntity(ItineraryRepresentation.class);  
         
         //Links
         Link selfLink = itineraryRepresentation.getLinkByRelation(SELF_RELATION);
         Link bookLink = itineraryRepresentation.getLinkByRelation(BOOKING_RELATION);         
         Link flightLink = itineraryRepresentation.getLinkByRelation(FLIGHT_RELATION);
         Link hotelLink = itineraryRepresentation.getLinkByRelation(HOTEL_RELATION); 
         
         // Get flights
         FlightBookingList flightBookingList = RestService.getFlights("CPH", "SFO", "2013-11-17").getEntity(FlightBookingList.class);
         FlightBooking flightBooking = flightBookingList.getFlights().get(0);
         
         // Get hotels
         HotelBookingList hotelBookingList = RestService.getHotels(customerID, "Copenhagen", "2013-11-17", "2013-11-18").getEntity(HotelBookingList.class);
         HotelBooking hotelBooking1 = hotelBookingList.getHotels().get(0);
         HotelBooking hotelBooking2 = hotelBookingList.getHotels().get(1);
         
         // Add flight and hotels
         RestService.putURI(flightLink.getURI(), customerID, flightBooking);
         RestService.putURI(hotelLink.getURI(), customerID, hotelBooking1);
         RestService.putURI(hotelLink.getURI(), customerID, hotelBooking2);

         // Book itinerary
         RestService.putURI(bookLink.getURI(), customerID);         
         
         //Get itinerary
         itineraryRepresentation = RestService.getURI(selfLink.getURI(), customerID).getEntity(ItineraryRepresentation.class);
         flightBookingList = itineraryRepresentation.getItinerary().getFlightBookings();
         hotelBookingList = itineraryRepresentation.getItinerary().getHotelBookings();
         
         // Cancel link
         Link cancelLink = itineraryRepresentation.getLinkByRelation(CANCEL_RELATION); 
         
         // Verify status of flights
         for(FlightBooking fb : flightBookingList.getFlights()) {
             assertTrue(fb.getBookingState()==FlightBookingState.CONFIRMED);
         }
         
         // Verify status of hotels
         for(HotelBooking hb : hotelBookingList.getHotels()) {
             assertTrue(hb.getBookingState()==HotelBooking.HotelBookingState.CONFIRMED);
         }
         
         // Cancel itinerary
         RestService.deleteURI(cancelLink.getURI(), customerID);
         
         //Get itinerary
         itineraryRepresentation = RestService.getURI(selfLink.getURI(), customerID).getEntity(ItineraryRepresentation.class);
         flightBookingList = itineraryRepresentation.getItinerary().getFlightBookings();
         hotelBookingList = itineraryRepresentation.getItinerary().getHotelBookings();
         
         // Verify status of flights
         for(FlightBooking fb : flightBookingList.getFlights()) {
             assertTrue(fb.getBookingState()==FlightBookingState.CANCELLED);
         }
         
         // Verify status of hotels
         for(HotelBooking hb : hotelBookingList.getHotels()) {
             assertTrue(hb.getBookingState()==HotelBooking.HotelBookingState.CANCELLED);
         } 
     } 
          
     @Test
     public void testC2() {
         // Create itinerary
         ItineraryRepresentation itineraryRepresentation = RestService.createItinerary(customerID).getEntity(ItineraryRepresentation.class);  
        
         //Links
         Link selfLink = itineraryRepresentation.getLinkByRelation(SELF_RELATION);
         Link bookLink = itineraryRepresentation.getLinkByRelation(BOOKING_RELATION);         
         Link flightLink = itineraryRepresentation.getLinkByRelation(FLIGHT_RELATION);
         Link hotelLink = itineraryRepresentation.getLinkByRelation(HOTEL_RELATION);          
         
         // Get flights
         FlightBookingList flightBookingList = RestService.getFlights("LHR", "CPH", "2013-11-17").getEntity(FlightBookingList.class);
         FlightBooking flightBooking = flightBookingList.getFlights().get(0); // This flight can not be cancelled
         
         // Get hotels
         HotelBookingList hotelBookingList = RestService.getHotels(customerID, "Copenhagen", "2013-11-17", "2013-11-18").getEntity(HotelBookingList.class);
         HotelBooking hotelBooking1 = hotelBookingList.getHotels().get(0);
         HotelBooking hotelBooking2 = hotelBookingList.getHotels().get(1);
         
         // Add flight and hotels
         RestService.putURI(flightLink.getURI(), customerID, flightBooking);
         RestService.putURI(hotelLink.getURI(), customerID, hotelBooking1);
         RestService.putURI(hotelLink.getURI(), customerID, hotelBooking2);

         // Book itinerary
         RestService.putURI(bookLink.getURI(), customerID);                  
         
         //Get itinerary
         itineraryRepresentation = RestService.getURI(selfLink.getURI(), customerID).getEntity(ItineraryRepresentation.class);
         flightBookingList = itineraryRepresentation.getItinerary().getFlightBookings();
         hotelBookingList = itineraryRepresentation.getItinerary().getHotelBookings();
         
         // Cancel link
         Link cancelLink = itineraryRepresentation.getLinkByRelation(CANCEL_RELATION); 
         
         // Verify status of flights
         for(FlightBooking fb : flightBookingList.getFlights()) {
             assertTrue(fb.getBookingState()==FlightBookingState.CONFIRMED);
         }
         
         // Verify status of hotels
         for(HotelBooking hb : hotelBookingList.getHotels()) {
             assertTrue(hb.getBookingState()==HotelBooking.HotelBookingState.CONFIRMED);
         }
         
         // Cancel itinerary
         RestService.deleteURI(cancelLink.getURI(), customerID);
           
         //Get itinerary
         itineraryRepresentation = RestService.getURI(selfLink.getURI(), customerID).getEntity(ItineraryRepresentation.class);
         flightBookingList = itineraryRepresentation.getItinerary().getFlightBookings();
         hotelBookingList = itineraryRepresentation.getItinerary().getHotelBookings();
              
         // Verify status of flight
         flightBooking = flightBookingList.getFlights().get(0);
         assertTrue(flightBooking.getBookingState()==FlightBookingState.CONFIRMED);
         
         // Verify status of hotels
         for(HotelBooking hb : hotelBookingList.getHotels()) {
             assertTrue(hb.getBookingState()==HotelBooking.HotelBookingState.CANCELLED);
         } 
     } 
}