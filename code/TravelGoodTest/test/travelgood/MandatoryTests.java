package travelgood;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;
import org.junit.Test;
import static org.junit.Assert.*;
import servicewrappers.BPELWrapper;
import ws.travelgoodbpel.FlightBookings;
import ws.travelgoodbpel.HotelBookings;

public class MandatoryTests {
    
    private TravelGoodClient client;
    private String customerID = "customer007";
    private String itineraryID = "itinerary42";
    private Holder<FlightBookings> flightBookings = new Holder<FlightBookings>();
    private Holder<HotelBookings> hotelBookings = new Holder<HotelBookings>();
    private XMLGregorianCalendar date1, date2;
    
    public MandatoryTests() {
        client = new BPELWrapper();
    }
    
    @Test
    public void testP1(){
        
    }
    
    @Test
    public void testP2(){
        
    }
    
    @Test
    public void testB(){
        
    }
    
    @Test
    public void testC1(){
        
    }
    
    @Test
    public void testC2(){
        
    }
}
