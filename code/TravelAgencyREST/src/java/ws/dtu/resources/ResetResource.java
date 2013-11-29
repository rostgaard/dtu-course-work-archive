/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.resources;

import javax.annotation.Resource;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import reset.lameduck.dtu.ws.LameDuckResetPortType;
import reset.lameduck.dtu.ws.LameDuckResetService;
import reset.niceview.dtu.ws.NiceViewResetPortType;
import reset.niceview.dtu.ws.NiceViewResetService;
import ws.dtu.manager.ItineraryDatabase;

/**
 *
 * @author peter
 */
@Resource
@Path("/reset")
@Produces("application/itinerary+xml")
public class ResetResource extends ws.dtu.resources.Resource {
    
    private static final LameDuckResetService lameDuckResetService = new LameDuckResetService();
    private static final LameDuckResetPortType lameDuckResetPort = lameDuckResetService.getLameDuckResetPort(); 
    private static final NiceViewResetService niceViewResetService = new NiceViewResetService();
    private static final NiceViewResetPortType niceViewResetPort = niceViewResetService.getNiceViewResetPort(); 
    
    @PUT
    public void reset() {
        ItineraryDatabase.getInstance().reset();
        lameDuckResetPort.lameDuckResetOperation();
        niceViewResetPort.niceViewResetOperation();
    }
}
