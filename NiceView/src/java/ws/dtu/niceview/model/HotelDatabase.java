/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.niceview.model;

import java.util.HashMap;
import java.util.Map;
import javax.xml.datatype.XMLGregorianCalendar;
import ws.dtu.niceview.HotelInformation;
import ws.dtu.niceview.HotelList;

/**
 *
 * @author Martin
 */
public class HotelDatabase {
    
    static Map bookingNoDB = new HashMap<String, HotelInformation>();
    static Map cityDB = new HashMap<String, HotelList>();
    
    public static void loadDatabase() {
        
    }
    
    private static void insert(HotelInformation hotel) {
        bookingNoDB.put(hotel.getBookingNo(), hotel);
        HotelList hotels = (HotelList)cityDB.get(hotel.getAddress().getCity());
        if (hotels == null) {
            hotels = new HotelList();
            cityDB.put(hotel.getAddress().getCity(), hotels);
        }
        hotels.getHotels().add(hotel);
    }
    
    public static HotelList getHotels(String city, XMLGregorianCalendar arrival, XMLGregorianCalendar departure) {
        return (HotelList)cityDB.get(city);
    }
    
    public static HotelInformation getHotel(String bookingNo) {
        HotelInformation hotel = (HotelInformation)bookingNoDB.get(bookingNo);
        if (hotel == null) {
            // TODO: FAULT
        }
        return hotel;
    }
}
