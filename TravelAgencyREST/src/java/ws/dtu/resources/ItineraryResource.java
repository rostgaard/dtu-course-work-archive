
package ws.dtu.resources;

import javax.annotation.Resource;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import ws.dtu.entities.Flight;
import ws.dtu.entities.Hotel;


/**
 *
 * @author peter
 */
@Resource
@Path("/itinerary")
public class ItineraryResource {
    
    @GET
    @Path("{id}")
    public String getItinerary(@PathParam("id") int itineraryIdentifier) {
        return "" + itineraryIdentifier;
    }
    
    @POST
    @Path("")
    public String createItinerary() {
        return "created itinerary";
    }
    
    @DELETE
    @Path("{id}")
    public void deleteItinerary(@PathParam("id") int itineraryIdentifier) {
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
