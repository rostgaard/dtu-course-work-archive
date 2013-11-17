/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.resources;

import javax.annotation.Resource;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author peter
 */
@Resource
@Path("/itinerary")
public class ItineraryResource {
    
    @GET
    @Path("{id}")
    public String getItinerary(@PathParam("id") int id) {
        return "" + id;
    }
    
    @POST
    @Path("")
    public String createItinerary() {
        return "created itinerary";
    }
    
    @DELETE
    @Path("{id}")
    public void deleteItinerary(@PathParam("id") int id) {
    }
    
}
