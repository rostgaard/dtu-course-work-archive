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
import ws.dtu.model.HotelBookingList;
import ws.dtu.niceview.NiceViewPortType;
import ws.dtu.niceview.NiceViewService;
import ws.dtu.niceview.types.HotelList;


/**
 *
 * @author kim
 */
@Resource
@Path("/hotel")
@Produces("application/itinerary+xml")
public class HotelResource extends ws.dtu.resources.Resource {
    
    private static final NiceViewService niceViewService = new NiceViewService();
    private static final NiceViewPortType niceViewPort = niceViewService.getNiceViewPort();
    
    @GET
    public HotelBookingList getHotels(@QueryParam("origin")String origin, @QueryParam("arrival_date")String arrivalDateString, @QueryParam("departure_date")String departureDateString) {
        DatatypeFactory df = null;
        try {
            df = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException ex) {
        }
        XMLGregorianCalendar arrivalDate = df.newXMLGregorianCalendar(arrivalDateString);
        XMLGregorianCalendar departureDate = df.newXMLGregorianCalendar(departureDateString);

        HotelList hotelList = niceViewPort.getHotels(origin, arrivalDate, departureDate);
        
        HotelBookingList hotelBookingList = new HotelBookingList(hotelList);
                
        return hotelBookingList;
    }

    
}
