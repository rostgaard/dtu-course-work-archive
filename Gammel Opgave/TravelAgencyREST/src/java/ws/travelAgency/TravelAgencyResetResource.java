package ws.travelAgency;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author Andreas
 */
@Path("TravelAgency/Reset/")
public class TravelAgencyResetResource {

    @PUT
    public Response reset() {
        TravelAgencyResource.reset();
        return Response.ok().build();
    }

}
