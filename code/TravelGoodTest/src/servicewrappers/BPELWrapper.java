/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicewrappers;

import java.util.HashSet;
import java.util.Set;
import travelgood.TravelGoodClient;
import ws.dtu.lameduck.types.FlightList;
import ws.dtu.niceview.types.HotelList;


/**
 *
 * @author Mikkel
 */
public class BPELWrapper implements TravelGoodClient  {
    
    private Set<String> ids = new HashSet<String>();
    @Override
    public boolean createItinerary(String customerID, String itineraryID) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.createItinerary(customerID, itineraryID);
    }

    @Override
    public FlightList getFlights(java.lang.String customerID, java.lang.String itineraryID, java.lang.String origin, java.lang.String destination, javax.xml.datatype.XMLGregorianCalendar datetime) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.getFlights(customerID, itineraryID, origin, destination, datetime);
    }

    @Override
    public HotelList getHotels(java.lang.String customerID, java.lang.String itineraryID, java.lang.String city, javax.xml.datatype.XMLGregorianCalendar arrival, javax.xml.datatype.XMLGregorianCalendar departure) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.getHotels(customerID, itineraryID, city, arrival, departure);
    }

    @Override
    public void getItinerary(java.lang.String customerID, java.lang.String itineraryID, javax.xml.ws.Holder<ws.travelgoodbpel.FlightBookings> flights, javax.xml.ws.Holder<ws.travelgoodbpel.HotelBookings> hotels) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        port.getItinerary(customerID, itineraryID, flights, hotels);
    }

    @Override
    public boolean addFlight(java.lang.String customerID, java.lang.String itineraryID, ws.dtu.lameduck.types.FlightInformation flightInformation) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.addFlight(customerID, itineraryID, flightInformation);
    }

    @Override
    public String addHotel(java.lang.String customerID, java.lang.String itineraryID, java.lang.String hotelBookingNo) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.addHotel(customerID, itineraryID, hotelBookingNo);
    }

    @Override
    public boolean bookItinerary(java.lang.String customerID, java.lang.String itineraryID, dk.dtu.imm.fastmoney.types.CreditCardInfoType ccInformation) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.bookItinerary(customerID, itineraryID, ccInformation);
    }

    @Override
    public String cancelPlanning(java.lang.String customerID, java.lang.String itineraryID) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.cancelPlanning(customerID, itineraryID);
    }    
    
}
