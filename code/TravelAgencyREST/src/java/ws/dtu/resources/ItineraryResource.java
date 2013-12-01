
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
import ws.dtu.manager.LinkManager;
import ws.dtu.model.FlightBooking;
import ws.dtu.model.HotelBooking;
import ws.dtu.model.Itinerary;
import ws.dtu.representation.FlightRepresentation;
import ws.dtu.representation.HotelRepresentation;
import ws.dtu.representation.ItineraryRepresentation;
import ws.dtu.representation.StatusRepresentation;

/**
 *
 * @author mikkel
 */
@Resource
@Path("/itinerary")
@Produces("application/itinerary+xml")
public class ItineraryResource extends ws.dtu.resources.Resource {
    private static final LameDuckService lameDuckService = new LameDuckService();
    private static final LameDuckPortType lameDuckPort = lameDuckService.getLameDuckPort();    
    
    private static final ItineraryManager itineraryManager = ItineraryManager.getInstance();
    private static final LinkManager linkManager = LinkManager.getInstance();
    
    @GET
    @Path("{id}")
    public ItineraryRepresentation getItinerary(@PathParam("id") int itineraryIdentifier, @QueryParam("customer_id") int customerID) {
        Itinerary itinerary = ItineraryDatabase.getInstance().get(customerID,itineraryIdentifier);
        ItineraryRepresentation representation = new ItineraryRepresentation(itinerary);
        linkManager.addLinks(itinerary, representation);        
        
        return representation;
    }
    
    @POST
    @Path("")
    public Response createItinerary(@QueryParam("customer_id") int customerID) throws URISyntaxException {
        Itinerary itinerary = itineraryManager.createItinerary(customerID);
        ItineraryRepresentation representation = new ItineraryRepresentation(itinerary);
        linkManager.addLinks(itinerary, representation);
        return Response.status(201).entity(representation).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response removeItinerary(@PathParam("id") int itineraryIdentifier,@QueryParam("customer_id") int customerID) {
        ItineraryDatabase.getInstance().delete(customerID,itineraryIdentifier);
        return Response.ok().build();
    }
    
    @GET
    @Path("{id}/flight")
    public FlightRepresentation getFlights(@PathParam("id") int itineraryIdentifier, @QueryParam("customer_id") int customerID) {
        Itinerary itinerary = ItineraryDatabase.getInstance().get(customerID,itineraryIdentifier);
        FlightRepresentation representation = new FlightRepresentation(itinerary.getFlightBookings());
        linkManager.addLinks(itinerary, representation);
        
        return representation;        
    }
    
    @PUT
    @Consumes("application/itinerary+xml")
    @Path("{id}/flight")
    public StatusRepresentation addFlight(@PathParam("id") int itineraryIdentifier, @QueryParam("customer_id") int customerID, FlightBooking flightBooking) {
        Itinerary itinerary = ItineraryDatabase.getInstance().get(customerID,itineraryIdentifier);
        itinerary.addFlightBooking(flightBooking);
        StatusRepresentation representation = new StatusRepresentation("Flight added");
        linkManager.addLinks(itinerary, representation);
        return representation;
    }
    
    @GET
    @Path("{id}/hotel")
    public HotelRepresentation getHotels(@PathParam("id") int itineraryIdentifier,@QueryParam("customer_id") int customerID) {
        Itinerary itinerary = ItineraryDatabase.getInstance().get(customerID,itineraryIdentifier);
        HotelRepresentation representation = new HotelRepresentation(itinerary.getHotelBookings());
        linkManager.addLinks(itinerary, representation);

        return representation;
    }
    
    @PUT
    @Path("{id}/hotel")
    @Consumes("application/itinerary+xml")
    public StatusRepresentation addHotel(@PathParam("id") int itineraryIdentifier, @QueryParam("customer_id") int customerID, HotelBooking hotelBooking) {
        Itinerary itinerary = ItineraryDatabase.getInstance().get(customerID,itineraryIdentifier);
        itinerary.addHotelBooking(hotelBooking);
        StatusRepresentation representation = new StatusRepresentation("Hotel added");
        linkManager.addLinks(itinerary, representation);
        
        return representation;
    }   
}