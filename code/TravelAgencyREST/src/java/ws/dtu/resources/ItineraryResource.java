
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import ws.dtu.lameduck.LameDuckPortType;
import ws.dtu.lameduck.LameDuckService;
import ws.dtu.model.FlightBooking;
import ws.dtu.model.FlightBookingList;
import ws.dtu.model.HotelBookingList;

import ws.dtu.manager.ItineraryDatabase;
import ws.dtu.model.Itinerary;
import ws.dtu.manager.ItineraryManager;
import ws.dtu.model.exceptions.NoSuchCustomerIdentifier;
import ws.dtu.model.exceptions.NoSuchIdentifier;

/**
 *
 * @author peter
 */
@Resource
@Path("/itinerary")
@Produces("application/itinerary+xml")
public class ItineraryResource {
    private static final LameDuckService lameDuckService = new LameDuckService();
    private static final LameDuckPortType lameDuckPort = lameDuckService.getLameDuckPort();    
    
    private static final ItineraryManager itineraryManager = new ItineraryManager();
    
    @GET
    @Path("{id}")
    public Itinerary getItinerary(@PathParam("id") int itineraryIdentifier, @QueryParam("customer_id") int customerID) throws NoSuchCustomerIdentifier {
        try {
            return ItineraryDatabase.getInstance().get(customerID,itineraryIdentifier);
        } catch (NoSuchIdentifier ex) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
    
    @POST
    @Path("")
    public Response createItinerary(@QueryParam("customer_id") int customerID) throws URISyntaxException {
        Itinerary itinerary = itineraryManager.createItinerary(customerID);
        return Response.created(new URI(""+itinerary.getID())).build();
    }
    
    @PUT
    @Path("{id}")
    public Response bookItinerary(@PathParam("id") int itineraryIdentifier, @QueryParam("customer_id") int customerID) {
        try {
            Itinerary itinerary = ItineraryDatabase.getInstance().get(customerID,itineraryIdentifier);
            itineraryManager.bookItinerary(itinerary);
            return Response.ok().build();
        } catch (NoSuchIdentifier ex) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } catch(NoSuchCustomerIdentifier ex) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
    
    @DELETE
    @Path("{id}")
    public void deleteItinerary(@PathParam("id") int itineraryIdentifier,@QueryParam("customer_id") int customerID) throws NoSuchCustomerIdentifier {
        try {
            ItineraryDatabase.getInstance().delete(customerID,itineraryIdentifier);
        } catch (NoSuchIdentifier ex) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
    
    @GET
    @Path("{id}/flight")
    public FlightBookingList getFlights(@PathParam("id") int itineraryIdentifier, @QueryParam("customer_id") int customerID) throws NoSuchIdentifier, NoSuchCustomerIdentifier {
        try {
            return ItineraryDatabase.getInstance().get(customerID,itineraryIdentifier).getFlightBookings();
        } catch (NoSuchIdentifier ex) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
    
    @PUT
    @Consumes("application/itinerary+xml")
    @Path("{id}/flight")
    public Response addFlight(@PathParam("id") int itineraryIdentifier, @QueryParam("customer_id") int customerID, FlightBooking flightBooking) {
        try {
        ItineraryDatabase.getInstance().get(customerID,itineraryIdentifier).addFlightBooking(flightBooking);
        return Response.ok().build();
        } catch (NoSuchIdentifier ex) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } catch (NoSuchCustomerIdentifier ex) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
    
    @GET
    @Path("{id}/hotel")
    public HotelBookingList getHotels(@PathParam("id") int itineraryIdentifier,@QueryParam("customer_id") int customerID) throws NoSuchCustomerIdentifier {
        try {
            return ItineraryDatabase.getInstance().get(customerID,itineraryIdentifier).getHotelBookings();
        } catch (NoSuchIdentifier ex) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }         
    }
    
    @PUT
    @Path("{id}/hotel")
    public Response addHotel(@PathParam("id") int itineraryIdentifier, @QueryParam("fid") int hotelIdentifier) {
        
        return null;
    }   
}