/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.resources;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import ws.dtu.lameduck.LameDuckPortType;
import ws.dtu.lameduck.LameDuckService;
import ws.dtu.lameduck.types.FlightList;
import ws.dtu.model.FlightBookingList;

/**
 *
 * @author peter
 */
@Resource
@Path("/flight")
@Produces("application/itinerary+xml")
public class FlightResource {
 
    private static final LameDuckService lameDuckService = new LameDuckService();
    private static final LameDuckPortType lameDuckPort = lameDuckService.getLameDuckPortTypeBindingPort();
    
    @GET
    public FlightBookingList getFlights(@QueryParam("origin")String origin, @QueryParam("destination")String destination, @QueryParam("date")String dateString) {
        DatatypeFactory df = null;
        try {
            df = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException ex) {
        }
        XMLGregorianCalendar date = df.newXMLGregorianCalendar(dateString);
        FlightList flightList = lameDuckPort.getFlights(origin, destination, date);
        
        FlightBookingList flightBookingList = new FlightBookingList(flightList);
                
        return flightBookingList;
    }
}
