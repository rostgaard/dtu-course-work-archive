/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.resources;

import javax.annotation.Resource;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import reset.lameduck.dtu.ws.LameDuckResetPortType;
import reset.lameduck.dtu.ws.LameDuckResetService;
import ws.dtu.lameduck.LameDuckPortType;
import ws.dtu.lameduck.LameDuckService;
import ws.dtu.manager.ItineraryDatabase;
import ws.dtu.manager.ItineraryManager;

/**
 *
 * @author peter
 */
@Resource
@Path("/reset")
@Produces("application/itinerary+xml")
public class ResetResource {
    
    private static final LameDuckResetService lameDuckResetService = new LameDuckResetService();
    private static final LameDuckResetPortType lameDuckResetPort = lameDuckResetService.getLameDuckResetPort(); 
    
    @POST
    public void reset() {
        ItineraryDatabase.getInstance().reset();
        lameDuckResetPort.lameDuckResetOperation();
    }
}
