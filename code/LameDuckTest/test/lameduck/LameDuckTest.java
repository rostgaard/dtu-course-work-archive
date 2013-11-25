/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lameduck;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import dk.dtu.imm.fastmoney.types.ExpirationDateType;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import ws.dtu.lameduck.BookFlightFault;
import ws.dtu.lameduck.CancelFlightFault;
import ws.dtu.lameduck.types.FlightInformation;
import ws.dtu.lameduck.types.FlightList;

/**
 *
 * @author Mikkel
 */
public class LameDuckTest {
    
    XMLGregorianCalendar date1;
    XMLGregorianCalendar date2;
    CreditCardInfoType cc = new CreditCardInfoType();
    
    @Before
    public void setup() throws DatatypeConfigurationException{
        
        lameDuckResetOperation();
        
        DatatypeFactory df = DatatypeFactory.newInstance();
        date1 = df.newXMLGregorianCalendar("2013-11-17");
        date2 = df.newXMLGregorianCalendar("2013-11-18");
        
        cc.setName("Tick Joachim");
        cc.setNumber("50408824");
        ExpirationDateType exp = new ExpirationDateType();
        exp.setMonth(2);
        exp.setYear(11);
        cc.setExpirationDate(exp);
        
    }
    
    @Test
    public void testGetFlight(){
        
        // Test that a single flight can be found
        String origin = "Kastrup";
        String destination = "Kabul";
        FlightList flights = getFlights(origin, destination, date1);
     //   assertEquals(flights.getFlights().size(),1);
        
       // assertEquals(origin, flights.getFlights().get(0).getFlight().getOrigin());
     //   assertEquals(destination, flights.getFlights().get(0).getFlight().getDestination());
        assertEquals(date1, flights.getFlights().get(0).getFlight().getLiftOff());
    }
    
    @Test
    public void testMultipleFlights(){
        String origin = "Kabul";
        String destination = "Kastrup";
        
        FlightList flights = getFlights(origin, destination, date1);
        
        assertEquals(flights.getFlights().size(),2);      
        // Check that the correct flights are found
        for(FlightInformation fi : flights.getFlights()){
            
            assertTrue(fi.getBookingNo().equals("b0002") || fi.getBookingNo().equals("b0008"));
        }
    }
    
    @Test
    public void testBooking(){
        String origin = "Kastrup";
        String destination = "Moscow";
        
        FlightList flights = getFlights(origin, destination, date1);
        assertEquals(flights.getFlights().size(),1);
        String bookingNumber = flights.getFlights().get(0).getBookingNo();

        
        
        boolean result = false;
        try {
            result = bookFlight(bookingNumber, cc);
        } catch (BookFlightFault ex) {
            Logger.getLogger(LameDuckTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        flights = getFlights(origin,destination,date1);
        assertTrue(result);
        assertEquals(flights.getFlights().size(),0);
        
    }
    
    @Test
    public void testCancel(){
        String origin = "Kastrup";
        String destination = "Irak";
        
        FlightList flights = getFlights(origin, destination, date1);
        assertEquals(flights.getFlights().size(),1);
        String bookingNumber = flights.getFlights().get(0).getBookingNo();
        double price = flights.getFlights().get(0).getPrice();
        
        boolean result = false;
        try {
            result = bookFlight(bookingNumber, cc);
        } catch (BookFlightFault ex) {
            Logger.getLogger(LameDuckTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        flights = getFlights(origin,destination,date1);
        assertTrue(result);
        assertEquals(flights.getFlights().size(),0);
        try {
            cancelFlight(bookingNumber, price, cc);
        } catch (CancelFlightFault ex) {
            fail();
        }
        flights = getFlights(origin, destination, date1);
        assertEquals(flights.getFlights().size(), 1);
    }
    
    @Test(expected = BookFlightFault.class)
    public void failBooking() throws BookFlightFault{
        String origin = "Kabul";
        String destination = "Kastrup";
        
        FlightList flights = getFlights(origin, destination, date1);
        
        assertTrue(flights.getFlights().size() > 0);
        String bookingNo = flights.getFlights().get(0).getBookingNo();
        try {
            bookFlight(bookingNo, cc);
        } catch (BookFlightFault ex) {
            // This should not happen
            fail();
        }
         bookFlight(bookingNo, cc);   

    }
    
    @Test(expected = BookFlightFault.class)
    public void testFailedPayment() throws BookFlightFault{
        String origin = "Kabul";
        String destination = "Kastrup";
        
        FlightList flights = getFlights(origin, destination, date1);
        
        assertTrue(flights.getFlights().size() > 0);
        
        String bookingNo = flights.getFlights().get(0).getBookingNo();
        
        cc.setName("Wrong name");
        
        bookFlight(bookingNo, cc);
    }
    @Test(expected = CancelFlightFault.class)
    public void testCancelFail() throws CancelFlightFault{
        cc.setNumber("91876523");
        cancelFlight("Some Number", 900.0, cc);
    }
    
    @Test
    public void multiBookingCancel() throws BookFlightFault, CancelFlightFault{
        String origin = "Kabul";
        String destination = "Kastrup";
        
        FlightList flights = getFlights(origin, destination, date1);
        
        assertEquals(flights.getFlights().size(), 2);
        
        for(FlightInformation fi : flights.getFlights()){
            bookFlight(fi.getBookingNo(), cc);
        }
        
        FlightList flights2 = getFlights(origin, destination, date1);
        
        assertEquals(flights2.getFlights().size(), 0);
        String cancelNumber = flights.getFlights().get(0).getBookingNo();
        cancelFlight(cancelNumber, 999, cc);
        
        FlightList flights3 = getFlights(origin, destination, date1);
        assertEquals(flights3.getFlights().size(),1);
        assertEquals(flights3.getFlights().get(0).getBookingNo(), cancelNumber);
        
    }

    private static boolean bookFlight(java.lang.String bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) throws BookFlightFault {
        ws.dtu.lameduck.LameDuckService service = new ws.dtu.lameduck.LameDuckService();
        ws.dtu.lameduck.LameDuckPortType port = service.getLameDuckPort();
        return port.bookFlight(bookingNumber, creditCardInfo);
    }

    private static boolean cancelFlight(java.lang.String bookingNumber, double price, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) throws CancelFlightFault {
        ws.dtu.lameduck.LameDuckService service = new ws.dtu.lameduck.LameDuckService();
        ws.dtu.lameduck.LameDuckPortType port = service.getLameDuckPort();
        return port.cancelFlight(bookingNumber, price, creditCardInfo);
    }

    private static FlightList getFlights(java.lang.String origin, java.lang.String destination, javax.xml.datatype.XMLGregorianCalendar datetime) {
        ws.dtu.lameduck.LameDuckService service = new ws.dtu.lameduck.LameDuckService();
        ws.dtu.lameduck.LameDuckPortType port = service.getLameDuckPort();
        return port.getFlights(origin, destination, datetime);
    }

    private static void lameDuckResetOperation() {
        reset.lameduck.dtu.ws.LameDuckResetService service = new reset.lameduck.dtu.ws.LameDuckResetService();
        reset.lameduck.dtu.ws.LameDuckResetPortType port = service.getLameDuckResetPort();
        port.lameDuckResetOperation();
    }
    
}
