/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package travelgood;

import ws.dtu.lameduck.types.FlightList;
import ws.dtu.niceview.types.HotelList;

/**
 *
 * @author peter
 */
public class TravelGoodClient {

    protected static boolean addFlight(java.lang.String customerID, java.lang.String itineraryID, ws.dtu.lameduck.types.FlightInformation flightInformation) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.addFlight(customerID, itineraryID, flightInformation);
    }

    protected static String addHotel(java.lang.String customerID, java.lang.String itineraryID, ws.dtu.niceview.types.HotelInformation hotelInformation) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.addHotel(customerID, itineraryID, hotelInformation);
    }

    protected static boolean bookItinerary(java.lang.String customerID, java.lang.String itineraryID, dk.dtu.imm.fastmoney.types.CreditCardInfoType ccInformation) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.bookItinerary(customerID, itineraryID, ccInformation);
    }

    protected static boolean cancelItinerary(java.lang.String customerID, java.lang.String itineraryID, dk.dtu.imm.fastmoney.types.CreditCardInfoType ccInformation) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.cancelItinerary(customerID, itineraryID, ccInformation);
    }

    protected static String cancelPlanning(java.lang.String customerID, java.lang.String itineraryID) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.cancelPlanning(customerID, itineraryID);
    }

    protected static boolean createItinerary(java.lang.String customerID, java.lang.String itineraryID) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.createItinerary(customerID, itineraryID);
    }

    protected static FlightList getFlights(java.lang.String customerID, java.lang.String itineraryID, java.lang.String origin, java.lang.String destination, javax.xml.datatype.XMLGregorianCalendar datetime) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.getFlights(customerID, itineraryID, origin, destination, datetime);
    }

    protected static HotelList getHotels(java.lang.String customerID, java.lang.String itineraryID, java.lang.String city, javax.xml.datatype.XMLGregorianCalendar arrival, javax.xml.datatype.XMLGregorianCalendar departure) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        return port.getHotels(customerID, itineraryID, city, arrival, departure);
    }

    protected static void getItinerary(java.lang.String customerID, java.lang.String itineraryID, javax.xml.ws.Holder<ws.travelgoodbpel.FlightBookings> flights, javax.xml.ws.Holder<ws.travelgoodbpel.HotelBookings> hotels) {
        ws.travelgoodbpel.TravelGoodService service = new ws.travelgoodbpel.TravelGoodService();
        ws.travelgoodbpel.TravelGoodPortType port = service.getTravelGoodPort();
        port.getItinerary(customerID, itineraryID, flights, hotels);
    }
}
