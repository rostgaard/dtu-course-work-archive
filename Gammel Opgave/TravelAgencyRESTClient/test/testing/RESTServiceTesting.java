/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testing;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.core.Response;
import ws.niceview.BookingType;
import ws.niceview.ReturnHotelList;
import ws.travelagency.data.FlightDate;
import ws.travelagency.data.FlightInfoWrapper;
import ws.travelagency.data.FlightInformation;
import ws.travelagency.data.HotelWrapper;
import ws.travelagency.data.Itinerary; 
import ws.travelagency.data.Status;
import ws.travelagency.representation.FlightRepresentation;
import ws.travelagency.representation.HotelRepresentation;
import ws.travelagency.representation.ItineraryRepresentation;
import ws.travelagency.representation.StateRepresentation;
import ws.travelagency.representation.Link;

/**
 *
 * @author Andreas
 */
public class RESTServiceTesting {

    Client client;
    String baseURI;
    String itineraryURI;
    String flightURI;
    String flightErrorURI;
    String hotelURI;

    String MEDIATYPE_ITINERARYPROCESS = "application/itineraryprocess+xml";

    WebResource orders;

    String customer_id = "9";
    String itineary_id = "0";

    // Information needed to retrieve flights
    String startAirport = "Copenhagen";
    String error_startAirport = "Oslo";
    String endAirport = "Stockholm";
    String day = "20";
    String month = "12";
    String year = "2012";

    static final String REL = "http://travelagency.ws/relations/";

    static final String BOOKING_REL = REL + "booking";
    static final String STATUS_REL = REL + "status";
    static final String FLIGHT_REL = REL + "flights";
    static final String HOTEL_REL = REL + "hotels";
    static final String SELF_REL = REL + "self";

    
    public RESTServiceTesting() {
        client = Client.create();
        // Uses tcpmon. To use the service directly replace 8070 by 8080
        // The same needs to be done in class OrderResource
        baseURI = "http://localhost:8080/TravelAgencyREST/resources/TravelAgency/";
        itineraryURI = baseURI + customer_id + "/" + itineary_id + "/";
        flightURI = baseURI + customer_id + "/" + itineary_id + "/Flights?startAirport=" + startAirport + "&endAirport=" + endAirport + "&day=" + day + "&month=" + month + "&year=" + year;
        flightErrorURI = baseURI + customer_id + "/" + itineary_id + "/Flights?startAirport=" + error_startAirport + "&endAirport=" + endAirport + "&day=" + day + "&month=" + month + "&year=" + year;
        hotelURI = baseURI + customer_id + "/" + itineary_id + "/Hotels?city=New+York&arrivalDay=" + 21 + "&arrivalMonth=" + 12 + "&arrivalYear=" + 2012 + "&departureDay=" + 28 + "&departureMonth=" + 12 + "&departureYear=" + 2012;
    }

    @Before
    public void reset() {
        client.resource(baseURI + "Reset/").put();
    }



    @Test
    public void P1() {
        client.resource(itineraryURI).type(MEDIATYPE_ITINERARYPROCESS).post();


        FlightRepresentation flights = client.resource(flightURI).
                accept(MEDIATYPE_ITINERARYPROCESS).
                type(MEDIATYPE_ITINERARYPROCESS).
                get(FlightRepresentation.class);

        assertEquals(flights.getFlightInformations().size(), 3);

        Link link = flights.getLinkByName(FLIGHT_REL);

        FlightInformation flightInfo = flights.getFlightInformations().get(0);

        client.resource(link.getURI()).
                accept(MEDIATYPE_ITINERARYPROCESS).
                type(MEDIATYPE_ITINERARYPROCESS).
                put(flightInfo);


        FlightInformation flightInfo2 = flights.getFlightInformations().get(1);

        client.resource(link.getURI()).
                accept(MEDIATYPE_ITINERARYPROCESS).
                type(MEDIATYPE_ITINERARYPROCESS).
                put(flightInfo2);

        ItineraryRepresentation itiRep = client.resource(itineraryURI).get(ItineraryRepresentation.class);
        assertEquals(itiRep.getItinerary().getFlights().size(),2);

        for (FlightInfoWrapper flight : itiRep.getItinerary().getFlights()) {
            if (flight.getStatus().getStatusCode() == Status.StatusCode.unconfirmed) {
                assertTrue(true);
            } else {
                assertTrue("Atleast one flight was set to not being 'unconfirmed'",false);
            }
        }
    }

    @Test
    public void P2() {
        client.resource(itineraryURI).
                type(MEDIATYPE_ITINERARYPROCESS).
                post();

        FlightRepresentation flights = client.resource(flightURI).
                accept(MEDIATYPE_ITINERARYPROCESS).
                type(MEDIATYPE_ITINERARYPROCESS).
                get(FlightRepresentation.class);
        
        assertEquals(flights.getFlightInformations().size(), 3);

        Link flight_link = flights.getLinkByName("http://travelagency.ws/relations/flights");

        FlightInformation flightInfo = flights.getFlightInformations().get(0);
        client.resource(flight_link.getURI()).
                accept(MEDIATYPE_ITINERARYPROCESS).
                type(MEDIATYPE_ITINERARYPROCESS).
                put(flightInfo);

        ItineraryRepresentation itiRep = client.resource(itineraryURI).get(ItineraryRepresentation.class);
        assertEquals(itiRep.getItinerary().getFlights().size(),1);

        Link status_link = itiRep.getLinkByName(STATUS_REL);
        client.resource(status_link.getURI()).
                type(MEDIATYPE_ITINERARYPROCESS).
                put();

        Link self_link = itiRep.getLinkByName(SELF_REL);
        StateRepresentation resp = client.resource(self_link.getURI()).
                type(MEDIATYPE_ITINERARYPROCESS).
                get(StateRepresentation.class);


        assertTrue(resp.getStatusInformation().equals("Itinerary for customer"+
                " ID " + customer_id +" and itinerary ID " + itineary_id +
                " was not found"));
    }

    @Test
    public void P3a() {
        String timeout_hotelURI = baseURI + customer_id + "/" + itineary_id + "/Hotels?city=test&arrivalDay=" + 10 + "&arrivalMonth=" + 10 + "&arrivalYear=" + 2012 + "&departureDay=" + 12 + "&departureMonth=" + 10 + "&departureYear=" + 2012;
        ReturnHotelList result = client.resource(timeout_hotelURI).
                type(MEDIATYPE_ITINERARYPROCESS).
                get(ReturnHotelList.class);

        assertEquals(0,result.getHotels().size());
    }

    @Test
    public void P3b() {
        String timeout_flightURI = baseURI + customer_id + "/" + itineary_id + "/Flights?startAirport=test&endAirport=" + endAirport + "&day=" + day + "&month=" + month + "&year=" + year;

        FlightRepresentation flights = client.resource(timeout_flightURI).
                type(MEDIATYPE_ITINERARYPROCESS).
                get(FlightRepresentation.class);

        assertEquals(0,flights.getFlightInformations().size());
    }

    @Test
    public void B() {
        client.resource(itineraryURI).
                type(MEDIATYPE_ITINERARYPROCESS).
                post();
        
        HotelRepresentation hotels = client.resource(hotelURI).
                accept(MEDIATYPE_ITINERARYPROCESS).
                type(MEDIATYPE_ITINERARYPROCESS).
                get(HotelRepresentation.class);

        Link hotel_link = hotels.getLinkByName(HOTEL_REL);
        BookingType booking = hotels.getHotelBooking().get(0);
        StateRepresentation hotel_stateRep = client.resource(hotel_link.getURI()).
                                                        accept(MEDIATYPE_ITINERARYPROCESS).
                                                        type(MEDIATYPE_ITINERARYPROCESS).
                                                        put(StateRepresentation.class,booking);
       
        FlightRepresentation flights = client.resource(flightURI).
                                                        accept(MEDIATYPE_ITINERARYPROCESS).
                                                        type(MEDIATYPE_ITINERARYPROCESS).
                                                        get(FlightRepresentation.class);

        Link flight_link = flights.getLinkByName(FLIGHT_REL);
        FlightInformation flight = flights.getFlightInformations().get(2);
        StateRepresentation flight_stateRep = client.resource(flight_link.getURI()).
                                                        accept(MEDIATYPE_ITINERARYPROCESS).
                                                        type(MEDIATYPE_ITINERARYPROCESS).
                                                        put(StateRepresentation.class,flight);

        FlightInformation flight2 = flights.getFlightInformations().get(2);
        StateRepresentation flight_stateRep2 = client.resource(flight_link.getURI()).
                                                        accept(MEDIATYPE_ITINERARYPROCESS).
                                                        type(MEDIATYPE_ITINERARYPROCESS).
                                                        put(StateRepresentation.class,flight2);

        Link before_self_link = flight_stateRep2.getLinkByName(SELF_REL);
        ItineraryRepresentation before_itinerary = client.resource(before_self_link.getURI()).
                                                            accept(MEDIATYPE_ITINERARYPROCESS).
                                                            type(MEDIATYPE_ITINERARYPROCESS).
                                                            get(ItineraryRepresentation.class);

        Itinerary before_iti = before_itinerary.getItinerary();
        for (HotelWrapper hotel : before_iti.getHotels()) {
            assertEquals(Status.StatusCode.unconfirmed, hotel.getStatus().getStatusCode());
        }

        for (FlightInfoWrapper flight_wrap : before_iti.getFlights()) {
            assertEquals(Status.StatusCode.unconfirmed, flight_wrap.getStatus().getStatusCode());
        }

        Link booking_link = flight_stateRep2.getLinkByName(BOOKING_REL);
        StateRepresentation result = client.resource(booking_link.getURI()).
                                                accept(MEDIATYPE_ITINERARYPROCESS).
                                                type(MEDIATYPE_ITINERARYPROCESS).
                                                put(StateRepresentation.class);

        assertEquals("Booking failed, all previous bookings cancelled", result.getStatusInformation());

        Link self_link = result.getLinkByName(SELF_REL);

        ItineraryRepresentation itinerary = client.resource(self_link.getURI()).
                                                        accept(MEDIATYPE_ITINERARYPROCESS).
                                                        type(MEDIATYPE_ITINERARYPROCESS).
                                                        get(ItineraryRepresentation.class);

        Itinerary iti = itinerary.getItinerary();

        for (HotelWrapper hotel : iti.getHotels()) {
            assertEquals(Status.StatusCode.cancelled, hotel.getStatus().getStatusCode());
        }

        for (FlightInfoWrapper flight_wrap : iti.getFlights()) {
            assertEquals(Status.StatusCode.unconfirmed, flight_wrap.getStatus().getStatusCode());
        }

        //assertTrue("Not yet implemented",false);
    }

    @Test
    public void C1() {
        client.resource(itineraryURI).post();

        FlightRepresentation flights = client.resource(flightURI).
                type(MEDIATYPE_ITINERARYPROCESS).
                get(FlightRepresentation.class);

        assertEquals(flights.getFlightInformations().size(), 3);

        Link link = flights.getLinkByName(FLIGHT_REL);

        FlightInformation flightInfo = flights.getFlightInformations().get(0);
        StateRepresentation stateRep = client.resource(link.getURI()).
                accept(MEDIATYPE_ITINERARYPROCESS).
                type(MEDIATYPE_ITINERARYPROCESS).
                put(StateRepresentation.class,flightInfo);

        FlightInformation flightInfo2 = flights.getFlightInformations().get(1);
        stateRep = client.resource(link.getURI()).
                accept(MEDIATYPE_ITINERARYPROCESS).
                type(MEDIATYPE_ITINERARYPROCESS).
                put(StateRepresentation.class,flightInfo2);

        Link booking_link = stateRep.getLinkByName(BOOKING_REL);
        StateRepresentation stateRep2 = client.resource(booking_link.getURI()).
                type(MEDIATYPE_ITINERARYPROCESS).
                put(StateRepresentation.class);
        
        ItineraryRepresentation iti_test = client.resource("http://localhost:8080/TravelAgencyREST/resources/TravelAgency/9/0/").
                type(MEDIATYPE_ITINERARYPROCESS).
                get(ItineraryRepresentation.class);

        Link self_link = stateRep2.getLinkByName(SELF_REL);
        ItineraryRepresentation itiResp =  client.resource(self_link.getURI()).
                type(MEDIATYPE_ITINERARYPROCESS).
                get(ItineraryRepresentation.class);
        Itinerary itinerary_before = itiResp.getItinerary();

        // Checking everything has benn confirmed
        for (FlightInfoWrapper flight : itinerary_before.getFlights()) {
            assertEquals(Status.StatusCode.confirmed,flight.getStatus().getStatusCode());
        }
        // Same for the hotels!

        Link status_link = itiResp.getLinkByName(STATUS_REL);

        StateRepresentation cancel_resp = client.resource(status_link.getURI()).
                type(MEDIATYPE_ITINERARYPROCESS).
                put(StateRepresentation.class);

        Link cancelled_self_link = cancel_resp.getLinkByName(SELF_REL);

        ItineraryRepresentation cancelled_iti_resp = client.resource(cancelled_self_link.getURI()).
                type(MEDIATYPE_ITINERARYPROCESS).
                get(ItineraryRepresentation.class);
        Itinerary cancelled_iti = cancelled_iti_resp.getItinerary();

        // Checks if everything has been cancelled
        for (FlightInfoWrapper flight : cancelled_iti.getFlights()) {
            assertEquals(Status.StatusCode.cancelled, flight.getStatus().getStatusCode());
        }
    }

    @Test
    public void C2() {
        client.resource(itineraryURI).
                type(MEDIATYPE_ITINERARYPROCESS).
                post();

        HotelRepresentation hotels = client.resource(hotelURI).
                type(MEDIATYPE_ITINERARYPROCESS).
                get(HotelRepresentation.class);

        Link hotel_link = hotels.getLinkByName(HOTEL_REL);
        BookingType booking = hotels.getHotelBooking().get(0);
        StateRepresentation hotel_stateRep = client.resource(hotel_link.getURI()).
                type(MEDIATYPE_ITINERARYPROCESS).
                put(StateRepresentation.class,booking);
        
        FlightRepresentation flights = client.resource(flightErrorURI).
                type(MEDIATYPE_ITINERARYPROCESS).
                get(FlightRepresentation.class);
       
        FlightInformation flight_non_error = flights.getFlightInformations().get(0);
        FlightInformation flight_error = flights.getFlightInformations().get(1);

        Link flight_link = flights.getLinkByName(FLIGHT_REL);
        client.resource(flight_link.getURI()).
                accept(MEDIATYPE_ITINERARYPROCESS).
                type(MEDIATYPE_ITINERARYPROCESS).
                put(flight_error);
        client.resource(flight_link.getURI()).
                accept(MEDIATYPE_ITINERARYPROCESS).
                type(MEDIATYPE_ITINERARYPROCESS).
                put(flight_non_error);

        Link booking_link = flights.getLinkByName(BOOKING_REL);
        StateRepresentation bookingRep = client.resource(booking_link.getURI()).
                type(MEDIATYPE_ITINERARYPROCESS).
                put(StateRepresentation.class);

        ItineraryRepresentation iti = client.resource(itineraryURI).
                type(MEDIATYPE_ITINERARYPROCESS).
                get(ItineraryRepresentation.class);
        Itinerary itinerary = iti.getItinerary();

        for (FlightInfoWrapper flight : itinerary.getFlights()) {
            assertEquals(Status.StatusCode.confirmed, flight.getStatus().getStatusCode());
        }

        for (HotelWrapper hotel : itinerary.getHotels()) {
            assertEquals(Status.StatusCode.confirmed, hotel.getStatus().getStatusCode());
        }

        Link status_link = bookingRep.getLinkByName(STATUS_REL);

        StateRepresentation stateRep = client.resource(status_link.getURI()).
                type(MEDIATYPE_ITINERARYPROCESS).
                put(StateRepresentation.class);

        StateRepresentation result = client.resource(status_link.getURI()).
                type(MEDIATYPE_ITINERARYPROCESS).
                get(StateRepresentation.class);

        assertEquals(Itinerary.ItineraryStatus.failedCancel.toString(), result.getStatusInformation());
    }

}