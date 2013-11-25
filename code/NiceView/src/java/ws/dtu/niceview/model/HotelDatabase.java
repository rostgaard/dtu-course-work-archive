/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.niceview.model;

import java.util.HashMap;
import java.util.Map;
import javax.xml.datatype.XMLGregorianCalendar;
import ws.dtu.niceview.types.HotelInformation;
import ws.dtu.niceview.types.HotelList;

/**
 *
 * @author Martin
 */
public class HotelDatabase {
    
    static Map bookingNoMap = new HashMap<String, HotelInformation>();
    static Map cityMap = new HashMap<String, HotelList>();
    
    public static void loadDatabase() {
        
    }
    
    private static void insert(HotelInformation hotel) {
        bookingNoMap.put(hotel.getBookingNo(), hotel);
        HotelList hotels = (HotelList)cityMap.get(hotel.getAddress().getCity());
        if (hotels == null) {
            hotels = new HotelList();
            cityMap.put(hotel.getAddress().getCity(), hotels);
        }
        hotels.getHotels().add(hotel);
    }
    
    public static HotelList getHotels(String city, XMLGregorianCalendar arrival, XMLGregorianCalendar departure) {
        return (HotelList)cityMap.get(city);
    }
    
    public static HotelInformation getHotel(String bookingNo) {
        HotelInformation hotel = (HotelInformation)bookingNoMap.get(bookingNo);
        if (hotel == null) {
            // TODO: FAULT
        }
        return hotel;
    }
}
