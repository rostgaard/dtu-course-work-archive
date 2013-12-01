/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.Assert.*;
import ws.dtu.lameduck.types.FlightInformation;
import ws.dtu.niceview.types.HotelInformation;

/**
 *
 * @author kim
 */
public class TestHelpers {
    
    public TestHelpers() {
    }
    
    
    
    public static void compareFlightInformation(FlightInformation f1, FlightInformation f2) {
        assertEquals(f1.getBookingNo(), f2.getBookingNo());
        assertEquals(f1.getPrice(), f2.getPrice());
        assertEquals(f1.getReservationSevice(), f2.getReservationSevice());
        
        assertEquals(f1.getFlight().getArrival(), f2.getFlight().getArrival());
        assertEquals(f1.getFlight().getCarrier(), f2.getFlight().getCarrier());
        assertEquals(f1.getFlight().getDestination(), f2.getFlight().getDestination());
        assertEquals(f1.getFlight().getLiftOff(), f2.getFlight().getLiftOff());
        assertEquals(f1.getFlight().getOrigin(), f2.getFlight().getOrigin());
    }
    
    public static void compareHotelInformation(HotelInformation h1, HotelInformation h2) {
        assertEquals(h1.getAddress().getCity(), h2.getAddress().getCity());
        assertEquals(h1.getAddress().getStreetAddress(), h2.getAddress().getStreetAddress());
        assertEquals(h1.getBookingNo(), h2.getBookingNo());
        assertEquals(h1.getName(), h2.getName());
        assertTrue(h1.getPrice() == h2.getPrice());
        assertEquals(h1.getReservationService(), h2.getReservationService());
        assertEquals(h1.isCcRequired(), h2.isCcRequired());
    }
}
