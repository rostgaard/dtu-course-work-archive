package travelgood;


import com.sun.jersey.api.client.ClientResponse;
import static org.junit.Assert.*;
import org.junit.Test;
import ws.dtu.RestService;
import ws.dtu.lameduck.types.FlightInformation;
import ws.dtu.model.FlightBookingList;
import ws.dtu.model.HotelBookingList;
import ws.dtu.model.Itinerary;
import ws.dtu.niceview.types.HotelInformation;
import ws.dtu.representation.ItineraryRepresentation;
import ws.dtu.representation.Link;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author peter
 */
public class TravelGoodPlanningTest extends TravelGoodRESTTest {
    
    
    @Test
    public void testCreatedEmptyItinerary() {
        Itinerary itinerary = RestService.createItinerary(customerID).getEntity(ItineraryRepresentation.class).getItinerary();        
        assertEquals(0, itinerary.getFlightBookings().getFlights().size());
        assertEquals(0, itinerary.getHotelBookings().getHotels().size());
    }
    
    
    @Test
    public void testGetFlights() {
        FlightBookingList flights = RestService.getFlights("CPH", "SFO", date1).getEntity(FlightBookingList.class);
        assertEquals(3,flights.getFlights().size());
    }
    
    @Test
    public void testGetFlightsEmpty(){
        FlightBookingList flights = RestService.getFlights("SomeRandomCity", "SomeNonExistingCity", date1).getEntity(FlightBookingList.class);
        assertEquals(0,flights.getFlights().size());
    }

    @Test
    public void testAddFlight() {
        ItineraryRepresentation itineraryRepresentation = RestService.createItinerary(customerID).getEntity(ItineraryRepresentation.class);        
        FlightBookingList flights = RestService.getFlights("CPH", "SFO", date1).getEntity(FlightBookingList.class);
        assertEquals(3, flights.getFlights().size());
        
        Link flightLink = itineraryRepresentation.getLinkByRelation(FLIGHT_RELATION);
        Link selfLink = itineraryRepresentation.getLinkByRelation(SELF_RELATION);

        RestService.putURI(flightLink.getURI(), customerID, flights.getFlights().get(0));

        itineraryRepresentation = RestService.getURI(selfLink.getURI(), customerID).getEntity(ItineraryRepresentation.class);
        Itinerary itinerary = itineraryRepresentation.getItinerary();
        assertEquals(1, itinerary.getFlightBookings().getFlights().size());
        
        FlightInformation info1 = flights.getFlights().get(0).getFlightInformation();
        FlightInformation info2 = itinerary.getFlightBookings().getFlights().get(0).getFlightInformation();
        
        TestHelpers.compareFlightInformation(info1, info2);
        assertEquals(0, itinerary.getHotelBookings().getHotels().size());
    }
    
    @Test
    public void testAddHotel(){
        ItineraryRepresentation itineraryRepresentation = RestService.createItinerary(customerID).getEntity(ItineraryRepresentation.class);        
        HotelBookingList hotelBookingList = RestService.getHotels("San Francisco", date1, date2).getEntity(HotelBookingList.class);
        assertEquals(3, hotelBookingList.getHotels().size());
        
        Link hotelLink = itineraryRepresentation.getLinkByRelation(HOTEL_RELATION);
        Link selfLink = itineraryRepresentation.getLinkByRelation(SELF_RELATION);

        RestService.putURI(hotelLink.getURI(), customerID, hotelBookingList.getHotels().get(0));

        itineraryRepresentation = RestService.getURI(selfLink.getURI(), customerID).getEntity(ItineraryRepresentation.class);
        Itinerary itinerary = itineraryRepresentation.getItinerary();
        assertEquals(1, itinerary.getHotelBookings().getHotels().size());
        
        HotelInformation info1 = hotelBookingList.getHotels().get(0).getHotelInformation();
        HotelInformation info2 = itinerary.getHotelBookings().getHotels().get(0).getHotelInformation();
        
        TestHelpers.compareHotelInformation(info1, info2);
        assertEquals(0, itinerary.getFlightBookings().getFlights().size());
    }
    
    
    @Test
    public void testPlanningRemove() {
        ItineraryRepresentation itineraryRepresentation = RestService.createItinerary(customerID).getEntity(ItineraryRepresentation.class);
        Link removeLink = itineraryRepresentation.getLinkByRelation(REMOVE_RELATION);
        Link selfLink = itineraryRepresentation.getLinkByRelation(SELF_RELATION);

        RestService.deleteURI(removeLink.getURI(), customerID);
        ClientResponse reponse = RestService.getURI(selfLink.getURI(), customerID);
        assertEquals(404, reponse.getStatus());
    }
    

    @Test
    public void getHotelsEmpty(){
        HotelBookingList hotels = RestService.getHotels("SillyTown666", date1, date2).getEntity(HotelBookingList.class);
        assertEquals(hotels.getHotels().size(), 0);
    }  
    
}
