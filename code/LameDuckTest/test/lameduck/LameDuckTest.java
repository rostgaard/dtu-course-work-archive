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
 * @author mikkel
 */
public class LameDuckTest {
    
    private XMLGregorianCalendar date1;
    private XMLGregorianCalendar date2;
    private CreditCardInfoType cc = new CreditCardInfoType();
    
    private static final String CPH = "CPH";
    private static final String SFO = "SFO";
    private static final String CDG = "CDG";
    private static final String GIG = "GIG";
    
    @Before
    public void setup() throws DatatypeConfigurationException {
        lameDuckResetOperation();
        
        DatatypeFactory df = DatatypeFactory.newInstance();
        date1 = df.newXMLGregorianCalendar("2013-11-24T00:00:00");
        date2 = df.newXMLGregorianCalendar("2013-11-25T00:00:00");
        
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
        FlightList flights = getFlights(CPH, SFO, date1);
        
        assertEquals(CPH, flights.getFlights().get(0).getFlight().getOrigin());
        assertEquals(SFO, flights.getFlights().get(0).getFlight().getDestination());
        assertEquals(date1, flights.getFlights().get(0).getFlight().getLiftOff());
    }
    
    @Test
    public void testMultipleFlights(){        
        FlightList flights = getFlights(SFO, CPH, date1);
        
        assertEquals(2, flights.getFlights().size());
        
        // Check that the correct flights are found
        for(FlightInformation fi : flights.getFlights()){
            assertTrue(fi.getBookingNo().equals("SAS0002") || fi.getBookingNo().equals("SAS0003"));
        }
    }
    
    @Test
    public void getEmpty(){
        FlightList flights = getFlights("Somewhere", "Nowhere", date1);
        assertEquals(0, flights.getFlights().size());
    }
    
    @Test
    public void testBooking(){
        FlightList flights = getFlights(CPH, CDG, date1);
        assertEquals(1, flights.getFlights().size());
        String bookingNumber = flights.getFlights().get(0).getBookingNo();        
        
        boolean result = false;
        try {
            result = bookFlight(bookingNumber, cc);
        } catch (BookFlightFault ex) {
            Logger.getLogger(LameDuckTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        flights = getFlights(CPH, CDG, date1);
        assertTrue(result);
        assertEquals(0, flights.getFlights().size());
    }
    
    @Test
    public void testCancel(){
        FlightList flights = getFlights(CPH, GIG, date1);
        assertEquals(1, flights.getFlights().size());
        String bookingNumber = flights.getFlights().get(0).getBookingNo();
        int price = flights.getFlights().get(0).getPrice();
        
        boolean result = false;
        try {
            result = bookFlight(bookingNumber, cc);
        } catch (BookFlightFault ex) {
            Logger.getLogger(LameDuckTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        flights = getFlights(CPH, GIG,date1);
        assertTrue(result);
        assertEquals(0, flights.getFlights().size());
        
        try {
            cancelFlight(bookingNumber, price, cc);
        } catch (CancelFlightFault ex) {
            fail();
        }
        flights = getFlights(CPH, GIG, date1);
        assertEquals(1, flights.getFlights().size());
    }
    
    @Test(expected = BookFlightFault.class)
    public void failBooking() throws BookFlightFault{
        FlightList flights = getFlights(SFO, CPH, date1);
        
        assertTrue(flights.getFlights().size() > 0);
        String bookingNo = flights.getFlights().get(0).getBookingNo();
        try {
            bookFlight(bookingNo, cc);
        } catch (BookFlightFault e) {
            // This should not happen
            fail();
        }
         bookFlight(bookingNo, cc);   
    }
    
    @Test(expected = BookFlightFault.class)
    public void testFailedPayment() throws BookFlightFault{
        FlightList flights = getFlights(SFO, CPH, date1);
        
        assertTrue(flights.getFlights().size() > 0);
        
        String bookingNo = flights.getFlights().get(0).getBookingNo();
        
        cc.setName("Wrong name");
        
        bookFlight(bookingNo, cc);
    }
    @Test(expected = CancelFlightFault.class)
    public void testCancelFail() throws CancelFlightFault{
        cc.setNumber("91876523");
        cancelFlight("Some Number", 900, cc);
    }
    
    @Test
    public void multiBookingCancel() throws BookFlightFault, CancelFlightFault{
        FlightList flights = getFlights(SFO, CPH, date1);
        
        assertEquals(2, flights.getFlights().size());
        
        for(FlightInformation fi : flights.getFlights()){
            bookFlight(fi.getBookingNo(), cc);
        }
        
        FlightList flights2 = getFlights(SFO, CPH, date1);
        
        assertEquals(0, flights2.getFlights().size());
        String cancelNumber = flights.getFlights().get(0).getBookingNo();
        cancelFlight(cancelNumber, 999, cc);
        
        FlightList flights3 = getFlights(SFO, CPH, date1);
        assertEquals(1, flights3.getFlights().size());
        assertEquals(cancelNumber, flights3.getFlights().get(0).getBookingNo());
        
    }

    @Test(expected = BookFlightFault.class)
    public void illegalBooking() throws BookFlightFault{
            bookFlight("VeryWrongBookingNumber", cc);
    }
   

    private static void lameDuckResetOperation() {
        reset.lameduck.dtu.ws.LameDuckResetService service = new reset.lameduck.dtu.ws.LameDuckResetService();
        reset.lameduck.dtu.ws.LameDuckResetPortType port = service.getLameDuckResetPort();
        port.lameDuckResetOperation();
    }

    private static boolean bookFlight(java.lang.String bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) throws BookFlightFault {
        ws.dtu.lameduck.LameDuckService service = new ws.dtu.lameduck.LameDuckService();
        ws.dtu.lameduck.LameDuckPortType port = service.getLameDuckPort();
        return port.bookFlight(bookingNumber, creditCardInfo);
    }

    private static boolean cancelFlight(java.lang.String bookingNumber, int price, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) throws CancelFlightFault {
        ws.dtu.lameduck.LameDuckService service = new ws.dtu.lameduck.LameDuckService();
        ws.dtu.lameduck.LameDuckPortType port = service.getLameDuckPort();
        return port.cancelFlight(bookingNumber, price, creditCardInfo);
    }

    private static FlightList getFlights(java.lang.String origin, java.lang.String destination, javax.xml.datatype.XMLGregorianCalendar liftOff) {
        ws.dtu.lameduck.LameDuckService service = new ws.dtu.lameduck.LameDuckService();
        ws.dtu.lameduck.LameDuckPortType port = service.getLameDuckPort();
        return port.getFlights(origin, destination, liftOff);
    }

}
