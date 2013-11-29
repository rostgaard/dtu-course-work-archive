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

public class HotelDatabase {
    
    public static final String Copenhagen = "Copenhagen";
    public static final String Kabul = "Kabul";
    public static final String Paris = "Paris";
    
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
        bookings = new HashSet<String>();
        bookings.add("AlreadyBooked");
    }
    
    public void reset() {
        init();
        this.loadDatabase();
    }
    
    private  void loadDatabase() {
        add("Copenhagen", "Istedgade 1", "CPH1", true, "SAS Hotel", 800, "NiceView");
        add("Copenhagen", "Nørregade 21", "CPH2", true, "Radison Blue Hotel", 500, "NiceView");
        add("San Francisco", "Coolstreet 13", "KAB1", true, "Clear sky Hotel", 230, "NiceView");
        add("San Francisco", "Hotalley 4", "KAB2", true, "Air Hotel", 900, "NiceView");
        add("Paris", "Beautifulway 124", "MOS1", true, "Tall Hotel", 300, "NiceView");
        add("Crazy Town", "Whatchamacallit", "AlreadyBooked", true, "Non-existent Hotel", 300, "NiceView");
    }
    
    private void add(String city, String street, String bookingNO, Boolean ccRequired, String name, double price, String reservationService) {
        HotelInformation hI;
        Address address;
        
        address = new Address();
        address.setCity(city);
        address.setStreetAddress(street);
        
        hI = new HotelInformation();
        hI.setAddress(address);
        hI.setBookingNo(bookingNO);
        hI.setCcRequired(ccRequired);
        hI.setName(name);
        hI.setPrice(price);
        hI.setReservationService(reservationService);
        
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
        if (cityMap.containsKey(city)) {
             return cityMap.get(city);
        }else {
            return new HotelList();
        }
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
