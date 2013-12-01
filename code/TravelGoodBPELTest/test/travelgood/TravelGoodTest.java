package travelgood;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import dk.dtu.imm.fastmoney.types.ExpirationDateType;
import java.util.Random;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;
import org.junit.Before;
import org.junit.Ignore;
import ws.travelgoodbpel.FlightBookings;
import ws.travelgoodbpel.HotelBookings;

@Ignore
public abstract class TravelGoodTest {

    protected String customerID = "customer007";
    protected String itineraryID = "itinerary42";
    protected Holder<FlightBookings> flightBookings = new Holder<>();
    protected Holder<HotelBookings> hotelBookings = new Holder<>();
    protected XMLGregorianCalendar date1, date2;
    
    protected CreditCardInfoType cc = new CreditCardInfoType();

    public TravelGoodTest() {
        Random r = new Random();
        customerID += r.nextInt(10000);
        
        cc.setName("Tick Joachim");
        cc.setNumber("50408824");
        ExpirationDateType exp = new ExpirationDateType();
        exp.setMonth(2);
        exp.setYear(11);
        cc.setExpirationDate(exp);
    }

    @Before
    public void beforeStart() throws DatatypeConfigurationException {
        lameDuckResetOperation();
        niceViewResetOperation();
        
        DatatypeFactory df = DatatypeFactory.newInstance();
        date1 = df.newXMLGregorianCalendar("2013-11-17");
        date2 = df.newXMLGregorianCalendar("2013-11-18");

        TravelGoodClient.createItinerary(customerID, itineraryID);
    }

    private static void lameDuckResetOperation() {
        reset.lameduck.dtu.ws.LameDuckResetService service = new reset.lameduck.dtu.ws.LameDuckResetService();
        reset.lameduck.dtu.ws.LameDuckResetPortType port = service.getLameDuckResetPort();
        port.lameDuckResetOperation();
    }

    private static void niceViewResetOperation() {
        reset.niceview.dtu.ws.NiceViewResetService service = new reset.niceview.dtu.ws.NiceViewResetService();
        reset.niceview.dtu.ws.NiceViewResetPortType port = service.getNiceViewResetPort();
        port.niceViewResetOperation();
    }
}
