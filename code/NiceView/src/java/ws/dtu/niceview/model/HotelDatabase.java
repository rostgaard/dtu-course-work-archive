/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.niceview.model;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.xml.datatype.XMLGregorianCalendar;
import ws.dtu.niceview.BookHotelFault;
import ws.dtu.niceview.CancelHotelFault;
import ws.dtu.niceview.types.Address;
import ws.dtu.niceview.types.HotelInformation;
import ws.dtu.niceview.types.HotelList;

/**
 *
 * @author Martin
 */
public class HotelDatabase {
    
    private Map<String, HotelInformation> bookingNoMap = new HashMap<String, HotelInformation>();
    private Map<String, HotelList> cityMap = new HashMap<String, HotelList>();
    private Set<String> bookings = new HashSet<String>();
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
        cityMap = new HashMap<String, HotelList>();
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
        hI.setBookingNo("SH1");
        hI.setCcRequired(true);
        hI.setName("SomeHotel");
        hI.setPrice(3.1415);
        hI.setReservationService("NiceView");
        
        insert(hI);
    }
    
    private void insert(HotelInformation hotel) {
        bookingNoMap.put(hotel.getBookingNo(), hotel);
        HotelList hotels = cityMap.get(hotel.getAddress().getCity());
        if (hotels == null) {
            hotels = new HotelList();
            cityMap.put(hotel.getAddress().getCity(), hotels);
        }
        hotels.getHotels().add(hotel);
    }
    
    public HotelList getHotels(String city, XMLGregorianCalendar arrival, XMLGregorianCalendar departure) {
        return cityMap.get(city);
    }
    
    public HotelInformation getHotel(String bookingNo) {
        HotelInformation hotel = (HotelInformation)bookingNoMap.get(bookingNo);
        if (hotel == null) {
            // TODO: FAULT
        }
        return hotel;
    }

    public HotelInformation bookHotel(String bookingNo, CreditCardInfoType ccInformation) throws BookHotelFault {
        if (!bookingNoMap.containsKey(bookingNo)) {
            throw new BookHotelFault("No such booking number: "+bookingNo, "No such booking number: "+bookingNo);
        }
        if (bookings.contains(bookingNo)) {
            throw new BookHotelFault("Hotel room has already been booked", "Hotel room has already been booked");
        }
        
        bookings.add(bookingNo);
        return bookingNoMap.get(bookingNo);
    }
    
    public void cancelHotel(String bookingNo) throws CancelHotelFault {
        if (!bookingNoMap.containsKey(bookingNo)) {
            throw new CancelHotelFault("No such booking number: "+bookingNo, "No such booking number: "+bookingNo);
        }
        if (!bookings.contains(bookingNo)) {
            throw new CancelHotelFault("Hotel room has not been booked", "Hotel room has not been booked");
        }
        bookings.remove(bookingNo);
    }
}
