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
import ws.dtu.manager.LinkManager;
import ws.dtu.model.Itinerary;
import ws.dtu.representation.StatusRepresentation;

/**
 *
 * @author peter
 */
@Resource
@Path("/itinerary/{id}/booking")
@Produces("application/itinerary+xml")
public class ItineraryBookingResource extends ws.dtu.resources.Resource {
    
    private static final ItineraryManager itineraryManager = ItineraryManager.getInstance();
    private static final LinkManager linkManager = LinkManager.getInstance();

    @PUT
    public StatusRepresentation bookItinerary(@PathParam("id") int itineraryIdentifier, @QueryParam("customer_id") int customerID) {        
        Itinerary itinerary = ItineraryDatabase.getInstance().get(customerID,itineraryIdentifier);
        itineraryManager.bookItinerary(itinerary);
        StatusRepresentation representation = new StatusRepresentation("Itinerary booked");
        linkManager.addLinks(itinerary, representation);
        return representation;
    }
    
    @DELETE
    public StatusRepresentation cancelItinerary(@PathParam("id") int itineraryIdentifier,@QueryParam("customer_id") int customerID) {
        Itinerary itinerary = ItineraryDatabase.getInstance().get(customerID,itineraryIdentifier);
        itineraryManager.cancelItinerary(itinerary);
        StatusRepresentation representation = new StatusRepresentation("Itinerary cancelled");
        linkManager.addLinks(itinerary, representation);
        
        return representation;
    } 
}
