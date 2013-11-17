
package ws.dtu.resources;

import java.net.URI;
import java.net.URISyntaxException;
import javax.annotation.Resource;
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
import ws.dtu.lameduck.FlightList;
import ws.dtu.lameduck.LameDuckService;
import ws.dtu.resources.model.HotelBookingList;

import ws.dtu.resources.model.ItineraryDatabase;
import ws.dtu.resources.model.Itinerary;
import ws.dtu.resources.model.exceptions.NoSuchIdentifier;


/**
 *
 * @author peter
 */
@Resource
@Path("/itinerary")
@Produces("application/xml")
public class ItineraryResource {
    
    @GET
    @Path("{id}")
    public Itinerary getItinerary(@PathParam("id") int itineraryIdentifier) {
        try {
            return ItineraryDatabase.get(itineraryIdentifier);
        } catch (NoSuchIdentifier ex) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
    
    @POST
    @Path("")
    public Response createItinerary() throws URISyntaxException {
        Itinerary itinerary = new Itinerary();
        return Response.created(new URI(""+itinerary.getID())).build();
    }
    
    @PUT
    @Path("{id}")
    public Response bookItinerary(@PathParam("id") int itineraryIdentifier) {
        try {
            Itinerary itinerary = ItineraryDatabase.get(itineraryIdentifier);
            itinerary.book();
            return Response.ok().build();
        } catch (NoSuchIdentifier ex) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
    
    @DELETE
    @Path("{id}")
    public void deleteItinerary(@PathParam("id") int itineraryIdentifier) {
        try {
            ItineraryDatabase.delete(itineraryIdentifier);
        } catch (NoSuchIdentifier ex) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
    
    @GET
    @Path("{id}/flight")
    public FlightList getFlights(@PathParam("id") int itineraryIdentifier) {
        try {
            return ItineraryDatabase.get(itineraryIdentifier).getFlights();
        } catch (NoSuchIdentifier ex) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } 
    }
    
    @PUT
    @Path("{id}/flight")
    public Response addFlight(@PathParam("id") int itineraryIdentifier, @QueryParam("fid") int flightIdentifier) {
        
        return null;
    }
    
    @GET
    @Path("{id}/hotel")
    public HotelBookingList getHotels(@PathParam("id") int itineraryIdentifier) {
        try {
            return ItineraryDatabase.get(itineraryIdentifier).getHotels();
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