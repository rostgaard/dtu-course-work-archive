
package ws.dtu.resources;

import java.net.URI;
import java.net.URISyntaxException;
import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import ws.dtu.lameduck.LameDuckPortType;
import ws.dtu.lameduck.LameDuckService;
import ws.dtu.manager.ItineraryDatabase;
import ws.dtu.manager.ItineraryManager;
import ws.dtu.model.FlightBooking;
import ws.dtu.model.FlightBookingList;
import ws.dtu.model.HotelBooking;
import ws.dtu.model.HotelBookingList;
import ws.dtu.model.Itinerary;

/**
 *
 * @author peter
 */
@Resource
@Path("/itinerary")
@Produces("application/itinerary+xml")
public class ItineraryResource {
    private static final LameDuckService lameDuckService = new LameDuckService();
    private static final LameDuckPortType lameDuckPort = lameDuckService.getLameDuckPortTypeBindingPort();    
    
    private static final ItineraryManager itineraryManager = ItineraryManager.getInstance();
    
    @GET
    @Path("{id}")
    public Itinerary getItinerary(@PathParam("id") int itineraryIdentifier, @QueryParam("customer_id") int customerID) {
        return ItineraryDatabase.getInstance().get(customerID,itineraryIdentifier);
    }
    
    @POST
    @Path("")
    public Response createItinerary(@QueryParam("customer_id") int customerID) throws URISyntaxException {
        Itinerary itinerary = itineraryManager.createItinerary(customerID);
        return Response.created(new URI(""+itinerary.getID())).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response removeItinerary(@PathParam("id") int itineraryIdentifier,@QueryParam("customer_id") int customerID) {
        ItineraryDatabase.getInstance().delete(customerID,itineraryIdentifier);
        return Response.ok().build();
    }
    
    @GET
    @Path("{id}/flight")
    public FlightBookingList getFlights(@PathParam("id") int itineraryIdentifier, @QueryParam("customer_id") int customerID) {
            return ItineraryDatabase.getInstance().get(customerID,itineraryIdentifier).getFlightBookings();
    }
    
    @PUT
    @Consumes("application/itinerary+xml")
    @Path("{id}/flight")
    public Response addFlight(@PathParam("id") int itineraryIdentifier, @QueryParam("customer_id") int customerID, FlightBooking flightBooking) {
        ItineraryDatabase.getInstance().get(customerID,itineraryIdentifier).addFlightBooking(flightBooking);
        return Response.ok().build();
    }
    
    @GET
    @Path("{id}/hotel")
    public HotelBookingList getHotels(@PathParam("id") int itineraryIdentifier,@QueryParam("customer_id") int customerID) {
            return ItineraryDatabase.getInstance().get(customerID,itineraryIdentifier).getHotelBookings();        
    }
    
    @PUT
    @Path("{id}/hotel")
    @Consumes("application/itinerary+xml")
    public Response addHotel(@PathParam("id") int itineraryIdentifier, @QueryParam("customer_id") int customerID, HotelBooking hotelBooking) {
        ItineraryDatabase.getInstance().get(customerID,itineraryIdentifier).addHotelBooking(hotelBooking);
        return Response.ok().build();
    }   
}