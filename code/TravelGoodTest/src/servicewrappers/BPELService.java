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

    private static boolean addFlight(java.lang.String customerID, java.lang.String itineraryID, ws.dtu.lameduck.types.FlightInformation flightInformation) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.addFlight(customerID, itineraryID, flightInformation);
    }

    private static String addHotel(java.lang.String customerID, java.lang.String itineraryID, java.lang.String hotelBookingNo) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.addHotel(customerID, itineraryID, hotelBookingNo);
    }

    private static boolean bookItinerary(java.lang.String customerID, java.lang.String itineraryID, dk.dtu.imm.fastmoney.types.CreditCardInfoType ccInformation) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.bookItinerary(customerID, itineraryID, ccInformation);
    }

    private static boolean cancelItinerary(java.lang.String customerID, java.lang.String itineraryID, dk.dtu.imm.fastmoney.types.CreditCardInfoType ccInformation) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.cancelItinerary(customerID, itineraryID, ccInformation);
    }

    private static String cancelPlanning(java.lang.String customerID, java.lang.String itineraryID) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.cancelPlanning(customerID, itineraryID);
    }

    private static boolean createItinerary(java.lang.String customerID, java.lang.String itineraryID) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.createItinerary(customerID, itineraryID);
    }

    private static FlightList getFlights(java.lang.String customerID, java.lang.String itineraryID, java.lang.String origin, java.lang.String destination, javax.xml.datatype.XMLGregorianCalendar datetime) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.getFlights(customerID, itineraryID, origin, destination, datetime);
    }

    private static HotelList getHotels(java.lang.String customerID, java.lang.String itineraryID, java.lang.String city, javax.xml.datatype.XMLGregorianCalendar arrival, javax.xml.datatype.XMLGregorianCalendar departure) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.getHotels(customerID, itineraryID, city, arrival, departure);
    }

    private static void getItinerary(java.lang.String customerID, java.lang.String itineraryID, javax.xml.ws.Holder<ws.travelgoodbpel.FlightBookings> flights, javax.xml.ws.Holder<ws.travelgoodbpel.HotelBookings> hotels) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        port.getItinerary(customerID, itineraryID, flights, hotels);
    }
    
}
