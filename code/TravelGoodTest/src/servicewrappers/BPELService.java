/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servicewrappers;

import ws.dtu.lameduck.types.FlightList;
import ws.dtu.niceview.types.HotelList;

/**
 *
 * @author Mikkel
 */
public class BPELService {
    public static boolean createItinerary(java.lang.String itineraryID) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.createItinerary(itineraryID);
    }

    public static FlightList getFlights(java.lang.String itineraryID, java.lang.String origin, java.lang.String destination, javax.xml.datatype.XMLGregorianCalendar datetime) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.getFlights(itineraryID, origin, destination, datetime);
    }

    public static HotelList getHotels(java.lang.String itineraryID, java.lang.String city, javax.xml.datatype.XMLGregorianCalendar arrival, javax.xml.datatype.XMLGregorianCalendar departure) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.getHotels(itineraryID, city, arrival, departure);
    }

    public static void getItinerary(java.lang.String itineraryID, javax.xml.ws.Holder<ws.travelgoodbpel.FlightBookings> flights, javax.xml.ws.Holder<ws.travelgoodbpel.HotelBookings> hotels) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        port.getItinerary(itineraryID, flights, hotels);
    }

    public static boolean addFlight(java.lang.String itineraryID, ws.dtu.lameduck.types.FlightInformation flightInformation) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.addFlight(itineraryID, flightInformation);
    }

    public static String addHotel(java.lang.String itineraryID, java.lang.String hotelBookingNo) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.addHotel(itineraryID, hotelBookingNo);
    }

    public static String bookItinerary(java.lang.String itineraryID, dk.dtu.imm.fastmoney.types.CreditCardInfoType ccInformation) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.bookItinerary(itineraryID, ccInformation);
    }

    public static String cancelPlanning(java.lang.String itineraryID) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.cancelPlanning(itineraryID);
    }
}
