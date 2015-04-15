package niceview;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import dk.dtu.imm.fastmoney.types.ExpirationDateType;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ws.dtu.niceview.BookHotelFault;
import ws.dtu.niceview.CancelHotelFault;
import ws.dtu.niceview.types.HotelInformation;
import ws.dtu.niceview.types.HotelList;

/**
 * 
 * @author kim
 */
public class NiceViewTest {

    private XMLGregorianCalendar date1;
    private XMLGregorianCalendar date2;
    private CreditCardInfoType cc = new CreditCardInfoType();
    private final String Copenhagen = "Copenhagen";

    @Before
    public void setup() throws DatatypeConfigurationException {
        niceViewResetOperation();

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
    public void testGetHotel() {
        // Test that a single hotel can be found
        HotelList hotels = getHotels(Copenhagen, date1, date2);

        assertEquals(Copenhagen, hotels.getHotels().get(0).getAddress().getCity());
    }

    @Test
    public void testMultipleHotels() {
        HotelList hotels = getHotels(Copenhagen, date1, date2);

        assertEquals(2, hotels.getHotels().size());

        // Check that the correct hotels are found
        for (HotelInformation fi : hotels.getHotels()) {
            assertTrue(fi.getBookingNo().equals("CPH1") || fi.getBookingNo().equals("CPH2"));
        }
    }

    @Test
    public void getEmpty() {
        HotelList hotels = getHotels("Space", date1, date2);
        assertEquals(0, hotels.getHotels().size());
    }

    @Test
    public void testBooking() {
        HotelList hotels = getHotels(Copenhagen, date1, date2);
        assertEquals(2, hotels.getHotels().size());

        String bookingNumber = hotels.getHotels().get(0).getBookingNo();

        boolean result = false;
        try {
            result = bookHotel(bookingNumber, cc);
        } catch (BookHotelFault ex) {
            Logger.getLogger(NiceViewTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        hotels = getHotels(Copenhagen, date1, date2);
        assertTrue(result);
        assertEquals(1, hotels.getHotels().size());
    }

    @Test
    public void testCancel() {
        HotelList hotels = getHotels(Copenhagen, date1, date2);
        assertEquals(2, hotels.getHotels().size());

        String bookingNumber = hotels.getHotels().get(0).getBookingNo();

        boolean result = false;
        try {
            result = bookHotel(bookingNumber, cc);
        } catch (BookHotelFault ex) {
            Logger.getLogger(NiceViewTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        hotels = getHotels(Copenhagen, date1, date2);
        assertTrue(result);
        assertEquals(1, hotels.getHotels().size());
        try {
            cancelHotel(bookingNumber);
        } catch (CancelHotelFault ex) {
            fail();
        }
        hotels = getHotels(Copenhagen, date1, date2);
        assertEquals(2, hotels.getHotels().size());
    }

    @Test(expected = BookHotelFault.class)
    public void failBooking() throws BookHotelFault {
        HotelList hotels = getHotels(Copenhagen, date1, date2);

        assertTrue(hotels.getHotels().size() > 0);
        String bookingNo = hotels.getHotels().get(0).getBookingNo();
        try {
            bookHotel(bookingNo, cc);
        } catch (BookHotelFault ex) {
            fail();
        }
        bookHotel(bookingNo, cc);
    }

    @Test(expected = BookHotelFault.class)
    public void testFailedPayment() throws BookHotelFault {
        HotelList hotels = getHotels(Copenhagen, date1, date2);

        assertTrue(hotels.getHotels().size() > 0);

        String bookingNo = hotels.getHotels().get(0).getBookingNo();

        cc.setName("Wrong name");

        bookHotel(bookingNo, cc);
    }

    @Test(expected = CancelHotelFault.class)
    public void testCancelFail() throws CancelHotelFault {
        cancelHotel("Some Number");
    }

    @Test
    public void multiBookingCancel() throws BookHotelFault, CancelHotelFault {
        HotelList hotels = getHotels(Copenhagen, date1, date2);

        assertEquals(hotels.getHotels().size(), 2);

        for (HotelInformation fi : hotels.getHotels()) {
            bookHotel(fi.getBookingNo(), cc);
        }

        HotelList hotels2 = getHotels(Copenhagen, date1, date2);

        assertEquals(0, hotels2.getHotels().size());
        String cancelNumber = hotels.getHotels().get(0).getBookingNo();
        cancelHotel(cancelNumber);

        HotelList hotels3 = getHotels(Copenhagen, date1, date2);
        assertEquals(1, hotels3.getHotels().size());
        assertEquals(cancelNumber, hotels3.getHotels().get(0).getBookingNo());

    }

    @Test(expected = BookHotelFault.class)
    public void illegalBooking() throws BookHotelFault {
        bookHotel("VeryWrongBookingNumber", cc);
    }

    private static void niceViewResetOperation() {
        reset.niceview.dtu.ws.NiceViewResetService service = new reset.niceview.dtu.ws.NiceViewResetService();
        reset.niceview.dtu.ws.NiceViewResetPortType port = service.getNiceViewResetPort();
        port.niceViewResetOperation();
    }

    private static boolean bookHotel(java.lang.String bookingNo, dk.dtu.imm.fastmoney.types.CreditCardInfoType ccInformation) throws BookHotelFault {
        ws.dtu.niceview.NiceViewService service = new ws.dtu.niceview.NiceViewService();
        ws.dtu.niceview.NiceViewPortType port = service.getNiceViewPort();
        return port.bookHotel(bookingNo, ccInformation);
    }

    private static boolean cancelHotel(java.lang.String bookingNo) throws CancelHotelFault {
        ws.dtu.niceview.NiceViewService service = new ws.dtu.niceview.NiceViewService();
        ws.dtu.niceview.NiceViewPortType port = service.getNiceViewPort();
        return port.cancelHotel(bookingNo);
    }

    private static HotelList getHotels(java.lang.String city, javax.xml.datatype.XMLGregorianCalendar arrival, javax.xml.datatype.XMLGregorianCalendar departure) {
        ws.dtu.niceview.NiceViewService service = new ws.dtu.niceview.NiceViewService();
        ws.dtu.niceview.NiceViewPortType port = service.getNiceViewPort();
        return port.getHotels(city, arrival, departure);
    }
}
