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
import ws.dtu.lameduck.CancelFlightFault;
import ws.dtu.lameduck.Flight;
import ws.dtu.lameduck.FlightInformation;
import ws.dtu.lameduck.FlightList;

/**
 *
 * @author Mikkel
 */
public class LameDuckTest {
    
    XMLGregorianCalendar date1;
    XMLGregorianCalendar date2;
    
    @Before
    public void setup() throws DatatypeConfigurationException{
        DatatypeFactory df = DatatypeFactory.newInstance();
        date1 = df.newXMLGregorianCalendar("2013-11-17");
        date2 = df.newXMLGregorianCalendar("2013-11-18");
    }
    
    @Test
    public void testGetFlight(){
        
        // Test that a single flight can be found
        String origin = "Kastrup";
        String destination = "Kabul";
        FlightList flights = getFlights(origin, destination, date1);
        assertEquals(flights.getFlights().size(),1);
        
        assertEquals(origin, flights.getFlights().get(0).getFlight().getOrigin());
        assertEquals(destination, flights.getFlights().get(0).getFlight().getDestination());
    }
    
    @Test
    public void testMultipleFlights(){
        String origin = "Kabul";
        String destination = "Kastrup";
        
        FlightList flights = getFlights(origin, destination, date1);
        
        assertEquals(flights.getFlights().size(),2);      
        // Check that the correct flights are found
        for(FlightInformation fi : flights.getFlights()){
            assertTrue(fi.getBookingNumber().equals("b0002") || fi.getBookingNumber().equals("b0008"));
        }
    }
    
    @Test
    public void testBooking(){
        String origin = "Kastrup";
        String destination = "Moscow";
        
        FlightList flights = getFlights(origin, destination, date1);
        assertEquals(flights.getFlights().size(),1);
        String bookingNumber = flights.getFlights().get(0).getBookingNumber();

        
        CreditCardInfoType cc = new CreditCardInfoType();
        boolean result = bookFlight(bookingNumber, cc);
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
        String bookingNumber = flights.getFlights().get(0).getBookingNumber();
        double price = flights.getFlights().get(0).getPrice();
        CreditCardInfoType cc = new CreditCardInfoType();
        boolean result = bookFlight(bookingNumber, cc);
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
    

    private static boolean bookFlight(java.lang.String bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) {
        ws.dtu.lameduck.LameDuckService service = new ws.dtu.lameduck.LameDuckService();
        ws.dtu.lameduck.LameDuckPortType port = service.getLameDuckPortTypeBindingPort();
        return port.bookFlight(bookingNumber, creditCardInfo);
    }

    private static boolean cancelFlight(java.lang.String bookingNumber, double price, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) throws CancelFlightFault {
        ws.dtu.lameduck.LameDuckService service = new ws.dtu.lameduck.LameDuckService();
        ws.dtu.lameduck.LameDuckPortType port = service.getLameDuckPortTypeBindingPort();
        return port.cancelFlight(bookingNumber, price, creditCardInfo);
    }

    private static FlightList getFlights(java.lang.String origin, java.lang.String destination, javax.xml.datatype.XMLGregorianCalendar date) {
        ws.dtu.lameduck.LameDuckService service = new ws.dtu.lameduck.LameDuckService();
        ws.dtu.lameduck.LameDuckPortType port = service.getLameDuckPortTypeBindingPort();
        return port.getFlights(origin, destination, date);
    }
}
