
package ws.dtu.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import ws.dtu.entities.Flight;
import ws.dtu.entities.Hotel;
import ws.dtu.resources.utils.Sequencer;

import ws.dtu.resources.model.ItineraryDatabase;
import ws.dtu.resources.model.Itinerary;
import ws.dtu.resources.model.NoSuchIdentfierException;


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
        } catch (NoSuchIdentfierException ex) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
    
    @POST
    @Path("")
    public Response createItinerary() throws URISyntaxException {
        Itinerary itinerary = new Itinerary();
        return Response.created(new URI(""+itinerary.getID())).build();
    }
    
    @DELETE
    @Path("{id}")
    public void deleteItinerary(@PathParam("id") int itineraryIdentifier) {
        try {
            ItineraryDatabase.delete(itineraryIdentifier);
        } catch (NoSuchIdentfierException ex) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
    
    @GET
    @Path("{id}/flight")
    public Flight[] getFlights(@PathParam("id") int itineraryIdentifier) {
        
        return null;
    }
    
    @PUT
    @Path("{id}/flight")
    public Response addFlight(@PathParam("id") int itineraryIdentifier, @QueryParam("fid") int flightIdentifier) {
        
        return null;
    }
    
    @GET
    @Path("{id}/hotel")
    public Hotel[] getHotels(@PathParam("id") int itineraryIdentifier) {
        
        return null;
    }
    
    @PUT
    @Path("{id}/hotel")
    public Response addHotel(@PathParam("id") int itineraryIdentifier, @QueryParam("fid") int hotelIdentifier) {
        
        return null;
    }
    
    @PUT
    @Path("{id}/Booking")
    public Response book(@PathParam("id") int itineraryIdentifier) {
        return null;
    }
}
