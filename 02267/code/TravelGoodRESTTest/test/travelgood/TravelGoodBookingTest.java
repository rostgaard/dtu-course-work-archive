/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package travelgood;

import helpers.TravelGoodRESTTest;
import org.junit.Before;
import org.junit.Test;
import ws.dtu.RestService;
import ws.dtu.model.FlightBookingList;
import ws.dtu.model.HotelBookingList;
import ws.dtu.model.Itinerary;
import ws.dtu.representation.ItineraryRepresentation;
import ws.dtu.representation.Link;
import static org.junit.Assert.*;
import ws.dtu.model.FlightBooking;
import ws.dtu.model.HotelBooking;

/**
 *
 * @author peter
 */
public class TravelGoodBookingTest extends TravelGoodRESTTest {
    HotelBookingList hotels;
    FlightBookingList flights;
    Link selfLink;   
    Link hotelLink;
    Link flightLink;
    Link bookLink;
    
    @Before
    public void setupPlan() {
        hotels = RestService.getHotels("San Francisco", date1, date2).getEntity(HotelBookingList.class);
        flights = RestService.getFlights("CPH", "SFO", date1).getEntity(FlightBookingList.class);
        
        ItineraryRepresentation itineraryRepresentation = RestService.createItinerary(customerID).getEntity(ItineraryRepresentation.class);
        
        flightLink = itineraryRepresentation.getLinkByRelation(FLIGHT_RELATION);
        hotelLink = itineraryRepresentation.getLinkByRelation(HOTEL_RELATION);
        selfLink = itineraryRepresentation.getLinkByRelation(SELF_RELATION);
        bookLink = itineraryRepresentation.getLinkByRelation(BOOKING_RELATION);
       
        RestService.putURI(flightLink.getURI(), customerID, flights.getFlights().get(0));
        RestService.putURI(hotelLink.getURI(), customerID, hotels.getHotels().get(0));

        itineraryRepresentation = RestService.getURI(selfLink.getURI(), customerID).getEntity(ItineraryRepresentation.class);
        Itinerary itinerary = itineraryRepresentation.getItinerary();
        
        assertTrue(itinerary.getFlightBookings().getFlights().size() > 0);
        assertTrue(itinerary.getHotelBookings().getHotels().size() > 0);
    }
    
    @Test
    public void testBookSuccess() {
        ItineraryRepresentation itineraryRepresentation = RestService.getURI(selfLink.getURI(), customerID).getEntity(ItineraryRepresentation.class);
        assertTrue(itineraryRepresentation.getItinerary().getState()==Itinerary.ItinerayState.PLANNING);
        RestService.putURI(bookLink.getURI(), customerID);
        
        itineraryRepresentation = RestService.getURI(selfLink.getURI(), customerID).getEntity(ItineraryRepresentation.class);
        
        assertTrue(itineraryRepresentation.getItinerary().getState()==Itinerary.ItinerayState.BOOKED);
    }
    
    @Test
    public void testBookFailure() {
        HotelBookingList  hotelBookingList = RestService.getHotels("Crazy Town", date1, date2).getEntity(HotelBookingList.class);
        
        RestService.putURI(hotelLink.getURI(), customerID, hotelBookingList.getHotels().get(0));
        RestService.putURI(bookLink.getURI(), customerID);

        ItineraryRepresentation itineraryRepresentation = RestService.getURI(selfLink.getURI(), customerID).getEntity(ItineraryRepresentation.class);
        Itinerary itinerary = itineraryRepresentation.getItinerary();
        assertFalse(itineraryRepresentation.getItinerary().getState()==Itinerary.ItinerayState.BOOKED);
        
        assertEquals(HotelBooking.HotelBookingState.CANCELLED, itinerary.getHotelBookings().getHotels().get(0).getBookingState());
        assertEquals(HotelBooking.HotelBookingState.UNCONFIRMED, itinerary.getHotelBookings().getHotels().get(1).getBookingState());
    }
    
    @Test
    public void testCancelSuccess() {
        RestService.putURI(bookLink.getURI(), customerID);
        ItineraryRepresentation itineraryRepresentation = RestService.getURI(selfLink.getURI(), customerID).getEntity(ItineraryRepresentation.class);
        assertTrue(itineraryRepresentation.getItinerary().getState()==Itinerary.ItinerayState.BOOKED);

        Link cancelLink = itineraryRepresentation.getLinkByRelation(CANCEL_RELATION);
        RestService.deleteURI(cancelLink.getURI(), customerID);

        itineraryRepresentation = RestService.getURI(selfLink.getURI(), customerID).getEntity(ItineraryRepresentation.class);

        assertTrue(itineraryRepresentation.getItinerary().getState()==Itinerary.ItinerayState.CANCELLED);
    }
    
    @Test
    public void testCancelFailure() throws InterruptedException {
        RestService.putURI(hotelLink.getURI(), customerID, hotels.getHotels().get(2));
        RestService.putURI(bookLink.getURI(), customerID);

        ItineraryRepresentation itineraryRepresentation = RestService.getURI(selfLink.getURI(), customerID).getEntity(ItineraryRepresentation.class);
        assertTrue(itineraryRepresentation.getItinerary().getState()==Itinerary.ItinerayState.BOOKED);
        
        Link cancelLink = itineraryRepresentation.getLinkByRelation(CANCEL_RELATION);
        RestService.deleteURI(cancelLink.getURI(), customerID);
        itineraryRepresentation = RestService.getURI(selfLink.getURI(), customerID).getEntity(ItineraryRepresentation.class);

        assertEquals(Itinerary.ItinerayState.PLANNING,itineraryRepresentation.getItinerary().getState());
    }
    
}
