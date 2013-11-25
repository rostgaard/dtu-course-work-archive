/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.niceview.model;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import ws.dtu.niceview.types.Address;
import ws.dtu.niceview.types.HotelInformation;
import ws.dtu.niceview.types.HotelList;

/**
 *
 * @author Martin
 */
public class HotelDatabase {
    
    private Map bookingNoMap = new HashMap<String, HotelInformation>();
    private Map cityMap = new HashMap<String, HotelList>();
    private String serviceName = "NiceView";
    
    private static HotelDatabase db;
    
    public static synchronized HotelDatabase getInstance() {
        if(db == null){
            db = new HotelDatabase();
            db.loadDatabase();
        }
        
        return db;
    }
    
    private HotelDatabase(){
        init();
    }
    
    private void init() {
        bookingNoMap = new HashMap<String, HotelInformation>();
        cityMap = new HashMap<String, HotelInformation>();
    }
    
    public void reset() {
        init();
        this.loadDatabase();
    }
    
    private  void loadDatabase() {
       
        HotelInformation hI;
        Address address;
        
        
        address = new Address();
        address.setCity("Somecity");
        address.setStreetAddress("Some Street 1");
        
        hI = new HotelInformation();
        hI.setAddress(address);
        hI.setBookingNo("SOME1");
        hI.setCcRequired(true);
        hI.setName("SomeHotel");
        hI.setPrice(3.1415);
        hI.setReservationService("NiceView");
        
        insert(hI);
    }
    
    private void insert(HotelInformation hotel) {
        bookingNoMap.put(hotel.getBookingNo(), hotel);
        HotelList hotels = (HotelList)cityMap.get(hotel.getAddress().getCity());
        if (hotels == null) {
            hotels = new HotelList();
            cityMap.put(hotel.getAddress().getCity(), hotels);
        }
        hotels.getHotels().add(hotel);
    }
    
    public HotelList getHotels(String city, XMLGregorianCalendar arrival, XMLGregorianCalendar departure) {
        return (HotelList)cityMap.get(city);
    }
    
    public HotelInformation getHotel(String bookingNo) {
        HotelInformation hotel = (HotelInformation)bookingNoMap.get(bookingNo);
        if (hotel == null) {
            // TODO: FAULT
        }
        return hotel;
    }
}
