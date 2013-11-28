/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.resources;

import javax.annotation.Resource;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import ws.dtu.manager.ItineraryDatabase;
import ws.dtu.manager.ItineraryManager;
import ws.dtu.model.Itinerary;

/**
 *
 * @author peter
 */
@Resource
@Path("/itinerary/{id}/booking")
@Produces("application/itinerary+xml")
public class ItineraryBookingResource {
    
    private static final ItineraryManager itineraryManager = ItineraryManager.getInstance();
    
    @PUT
    public Response bookItinerary(@PathParam("id") int itineraryIdentifier, @QueryParam("customer_id") int customerID) {        
        Itinerary itinerary = ItineraryDatabase.getInstance().get(customerID,itineraryIdentifier);
        itineraryManager.bookItinerary(itinerary);
        return Response.ok().build();
    }
    
    @DELETE
    public Response cancelItinerary(@PathParam("id") int itineraryIdentifier,@QueryParam("customer_id") int customerID) {
//        ItineraryDatabase.getInstance().delete(customerID,itineraryIdentifier);
        Itinerary itinerary = ItineraryDatabase.getInstance().get(customerID,itineraryIdentifier);
        itineraryManager.cancelItinerary(itinerary);
        return Response.ok().build();
    } 
}
