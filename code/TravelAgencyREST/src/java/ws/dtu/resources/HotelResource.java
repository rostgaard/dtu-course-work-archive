/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.resources;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


/**
 *
 * @author peter
 */
@Resource
@Path("/hotel")
@Produces("application/itinerary+xml")
public class HotelResource {
    
//    @GET
//    public String getHotels(@QueryParam("origin")String origin, @QueryParam("destination")String destination, @QueryParam("date")String dateString) {
//        DatatypeFactory df = null;
//        try {
//            df = DatatypeFactory.newInstance();
//        } catch (DatatypeConfigurationException ex) {
//        }
//        XMLGregorianCalendar date = df.newXMLGregorianCalendar(dateString);
//        FlightList flightList = lameDuckPort.getFlights(origin, destination, date);
//        
//        return RootElementFactory.marshallObject(flightList);
//    }

    
}
