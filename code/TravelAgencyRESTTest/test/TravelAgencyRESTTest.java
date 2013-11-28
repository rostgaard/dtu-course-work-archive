/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.jersey.api.client.ClientResponse;
import model.FlightBooking;
import model.FlightBooking.FlightBookingState;
import model.FlightBookingList;
import model.HotelBooking;
import model.HotelBookingList;
import model.Itinerary;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;



/**
 *
 * @author peter
 */
public class TravelAgencyRESTTest {
    
    private final static  String customerID = "1";
    
    
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
        
         assertEquals(201, response.getStatus());
         assertNotNull(response.getLocation());
     }
     
     @Test
     public void testGetItinerary() {
         ClientResponse response = RestService.createItinerary(customerID);
         Itinerary itinerary = RestService.getItinerary(customerID, response.getLocation()).getEntity(Itinerary.class);
         
         assertNotNull(itinerary.getID());
     }
     
     @Test
     public void testGetFlightsNone() {
         FlightBookingList flightBookingList = RestService.getFlights("Kastrup", "Aalborg", "2013-11-17").getEntity(FlightBookingList.class);
         
         assertEquals(0, flightBookingList.getFlights().size());
     }
     
     @Test
     public void testGetFlights() {
         FlightBookingList flightBookingList = RestService.getFlights("Kastrup", "Kabul", "2013-11-17").getEntity(FlightBookingList.class);
         
         assertEquals(3, flightBookingList.getFlights().size());
     }
     
     @Test
     public void testP1() {
         // Create itineraty
         ClientResponse createIitneraryRsponse = RestService.createItinerary(customerID);  
         Itinerary preItinerary = RestService.getItinerary(customerID, createIitneraryRsponse.getLocation()).getEntity(Itinerary.class);         
         
         // Get flights
         FlightBookingList flightBookingList = RestService.getFlights("Kastrup", "Kabul", "2013-11-17").getEntity(FlightBookingList.class);
         FlightBooking flightBooking1 = flightBookingList.getFlights().get(0);
         FlightBooking flightBooking2 = flightBookingList.getFlights().get(1);
         FlightBooking flightBooking3 = flightBookingList.getFlights().get(2);

         // Get hotels
         HotelBookingList hotelBookingList = RestService.getHotels(customerID, "Kabul", "2013-11-17", "2013-11-18").getEntity(HotelBookingList.class);
         HotelBooking hotelBooking1 = hotelBookingList.getHotels().get(0);
         HotelBooking hotelBooking2 = hotelBookingList.getHotels().get(1);

         // Add flight
         RestService.addFlight(customerID, preItinerary.getID(), flightBooking1);
         
         // Add hotel
         RestService.addHotel(customerID, preItinerary.getID(), hotelBooking1);
         
         // Two more flights
         RestService.addFlight(customerID, preItinerary.getID(), flightBooking2);
         RestService.addFlight(customerID, preItinerary.getID(), flightBooking3);

        // Finally, another hotel
        RestService.addHotel(customerID, preItinerary.getID(), hotelBooking2);

         // Get the flight and hotels in the itinerary
         Itinerary intermediaItinerary = RestService.getItinerary(customerID, createIitneraryRsponse.getLocation()).getEntity(Itinerary.class);
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
         RestService.bookItinerary(customerID, intermediaItinerary.getID());
         
         // Get the itinerary again
         Itinerary finaltinerary = RestService.getItinerary(customerID, createIitneraryRsponse.getLocation()).getEntity(Itinerary.class);
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
         ClientResponse createIitneraryRsponse = RestService.createItinerary(customerID);
         Itinerary preItinerary = RestService.getItinerary(customerID, createIitneraryRsponse.getLocation()).getEntity(Itinerary.class);
         
         // Get flights
         FlightBookingList flightBookingList = RestService.getFlights("Kastrup", "Kabul", "2013-11-17").getEntity(FlightBookingList.class);
         FlightBooking flightBooking = flightBookingList.getFlights().get(0);

         // Add flight to itinerary
         RestService.addFlight(customerID, preItinerary.getID(), flightBooking);
         
         // Get the itinerary
         Itinerary intermediaItinerary = RestService.getItinerary(customerID, createIitneraryRsponse.getLocation()).getEntity(Itinerary.class);
         
         // Verify that the flight was added
         FlightBooking flightBookingActual = intermediaItinerary.getFlightBookings().getFlights().get(0);
         assertEquals(flightBookingActual.getFlightInformation().getBookingNo(), flightBooking.getFlightInformation().getBookingNo());
         
         // Cancel the itinerary
         RestService.removeItinerary(customerID, intermediaItinerary.getID());
         
         // Verify that itinerary does not exist anymore
         ClientResponse response = RestService.getItinerary(customerID, createIitneraryRsponse.getLocation());
         assertEquals(404, response.getStatus());
     }
     
     @Test
     public void testB() {
         // Get hotels
         HotelBookingList hotelBookingList = RestService.getHotels(customerID, "Kabul", "2013-11-17", "2013-11-18").getEntity(HotelBookingList.class);
         HotelBooking hotelBooking = hotelBookingList.getHotels().get(0);
         
         // Get flights
         FlightBookingList flightBookingList = RestService.getFlights("Kastrup", "Kazakhstan", "2013-11-17").getEntity(FlightBookingList.class);
         FlightBooking flightBooking = flightBookingList.getFlights().get(0);
         
         // Create itinerary
         ClientResponse itineraryClientResponse = RestService.createItinerary(customerID);
         Itinerary itinerary = RestService.getItinerary(customerID, itineraryClientResponse.getLocation()).getEntity(Itinerary.class);
         
         // Add flight to itinerary
         RestService.addFlight(customerID, itinerary.getID(), flightBooking);

         // Add the flight again. Should not be allowed to book.
         RestService.addFlight(customerID, itinerary.getID(), flightBooking);
         
         // Add hotel to itinerary
         RestService.addHotel(customerID, itinerary.getID(), hotelBooking);
         
         //Get itinerary
         itinerary = RestService.getItinerary(customerID, itineraryClientResponse.getLocation()).getEntity(Itinerary.class);
         flightBookingList = itinerary.getFlightBookings();
         hotelBookingList = itinerary.getHotelBookings();
         
         // Verify status of flights
         for(FlightBooking fb : flightBookingList.getFlights()) {
             assertTrue(fb.getBookingState()==FlightBookingState.UNBOOKED);
         }
         
         // Verify status of hotels
         for(HotelBooking hb : hotelBookingList.getHotels()) {
             assertTrue(hb.getBookingState()==HotelBooking.HotelBookingState.UNBOOKED);
         }
         
         // Book itinerary
         ClientResponse bookResponse = RestService.bookItinerary(customerID, itinerary.getID());
         
         // Check that error was recieved
         assertEquals(406, bookResponse.getStatus());
         
          //Get itinerary
         itinerary = RestService.getItinerary(customerID, itineraryClientResponse.getLocation()).getEntity(Itinerary.class);
         
         // verify second fligt and  hotel unbooked
         FlightBooking flightBooking2 = itinerary.getFlightBookings().getFlights().get(1);
         assertEquals(FlightBookingState.UNBOOKED, flightBooking2.getBookingState());
         HotelBooking hotelBooking1 = itinerary.getHotelBookings().getHotels().get(0);
         assertEquals(HotelBooking.HotelBookingState.UNBOOKED, hotelBooking1.getBookingState());
         
         // verify first flight is cancelled
         FlightBooking flightBooking1 = itinerary.getFlightBookings().getFlights().get(0);
         assertEquals(FlightBookingState.CANCELLED, flightBooking1.getBookingState());
     }
     
     @Test
     public void testC1() {
         ClientResponse itineraryResponse = RestService.createItinerary(customerID);
         Itinerary itinerary = RestService.getItinerary(customerID, itineraryResponse.getLocation()).getEntity(Itinerary.class);
         
         // Get flights
         FlightBookingList flightBookingList = RestService.getFlights("Kastrup", "Kazakhstan", "2013-11-17").getEntity(FlightBookingList.class);
         FlightBooking flightBooking = flightBookingList.getFlights().get(0);
         
         // Get hotels
         HotelBookingList hotelBookingList = RestService.getHotels(customerID, "Kabul", "2013-11-17", "2013-11-18").getEntity(HotelBookingList.class);
         HotelBooking hotelBooking1 = hotelBookingList.getHotels().get(0);
         HotelBooking hotelBooking2 = hotelBookingList.getHotels().get(1);
         
         // Add flight and hotels
         RestService.addFlight(customerID, itinerary.getID(), flightBooking);
         RestService.addHotel(customerID, itinerary.getID(), hotelBooking1);
         RestService.addHotel(customerID, itinerary.getID(), hotelBooking2);

         // Book itinerary
         RestService.bookItinerary(customerID, itinerary.getID());
         
         //Get itinerary
         itinerary = RestService.getItinerary(customerID, itineraryResponse.getLocation()).getEntity(Itinerary.class);
         flightBookingList = itinerary.getFlightBookings();
         hotelBookingList = itinerary.getHotelBookings();
         
         // Verify status of flights
         for(FlightBooking fb : flightBookingList.getFlights()) {
             assertTrue(fb.getBookingState()==FlightBookingState.BOOKED);
         }
         
         // Verify status of hotels
         for(HotelBooking hb : hotelBookingList.getHotels()) {
             assertTrue(hb.getBookingState()==HotelBooking.HotelBookingState.BOOKED);
         }
         
         // Cancel itinerary
         RestService.cancelItinerary(customerID, itinerary.getID());
         
         //Get itinerary
         itinerary = RestService.getItinerary(customerID, itineraryResponse.getLocation()).getEntity(Itinerary.class);
         flightBookingList = itinerary.getFlightBookings();
         hotelBookingList = itinerary.getHotelBookings();
         
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
         ClientResponse itineraryResponse = RestService.createItinerary(customerID);
         Itinerary itinerary = RestService.getItinerary(customerID, itineraryResponse.getLocation()).getEntity(Itinerary.class);
         
         // Get flights
         FlightBookingList flightBookingList = RestService.getFlights("Kastrup", "Kazakhstan", "2013-11-17").getEntity(FlightBookingList.class);
         FlightBooking flightBooking = flightBookingList.getFlights().get(0);
         
         // Get hotels
         HotelBookingList hotelBookingList = RestService.getHotels(customerID, "Kabul", "2013-11-17", "2013-11-18").getEntity(HotelBookingList.class);
         HotelBooking hotelBooking1 = hotelBookingList.getHotels().get(0);
         HotelBooking hotelBooking2 = hotelBookingList.getHotels().get(1);
         
         // Add flight and hotels
         RestService.addFlight(customerID, itinerary.getID(), flightBooking);
         RestService.addHotel(customerID, itinerary.getID(), hotelBooking1);
         RestService.addHotel(customerID, itinerary.getID(), hotelBooking2);

         // Book itinerary
         RestService.bookItinerary(customerID, itinerary.getID());
         
         //Get itinerary
         itinerary = RestService.getItinerary(customerID, itineraryResponse.getLocation()).getEntity(Itinerary.class);
         flightBookingList = itinerary.getFlightBookings();
         hotelBookingList = itinerary.getHotelBookings();
         
         // Verify status of flights
         for(FlightBooking fb : flightBookingList.getFlights()) {
             assertTrue(fb.getBookingState()==FlightBookingState.BOOKED);
         }
         
         // Verify status of hotels
         for(HotelBooking hb : hotelBookingList.getHotels()) {
             assertTrue(hb.getBookingState()==HotelBooking.HotelBookingState.BOOKED);
         }
         
         // Cancel itinerary
         RestService.cancelItinerary(customerID, itinerary.getID());
         
         //Get itinerary
         itinerary = RestService.getItinerary(customerID, itineraryResponse.getLocation()).getEntity(Itinerary.class);
         flightBookingList = itinerary.getFlightBookings();
         hotelBookingList = itinerary.getHotelBookings();
         
         // Verify status of flights
         for(FlightBooking fb : flightBookingList.getFlights()) {
             assertTrue(fb.getBookingState()==FlightBookingState.CANCELLED);
         }
         
         // Verify status of hotels
         for(HotelBooking hb : hotelBookingList.getHotels()) {
             assertTrue(hb.getBookingState()==HotelBooking.HotelBookingState.CANCELLED);
         } 
     } 
}
