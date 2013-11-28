package travelgood;

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

    protected TravelGoodClient client;
    protected String customerID = "customer007";
    protected String itineraryID = "itinerary42";
    protected Holder<FlightBookings> flightBookings = new Holder<FlightBookings>();
    protected Holder<HotelBookings> hotelBookings = new Holder<HotelBookings>();
    protected XMLGregorianCalendar date1, date2;

    @Before
    public void beforeStart() throws DatatypeConfigurationException {
        DatatypeFactory df = DatatypeFactory.newInstance();
        date1 = df.newXMLGregorianCalendar("2013-11-17");
        date2 = df.newXMLGregorianCalendar("2013-11-18");

        client.createItinerary(customerID, itineraryID);
    }
}
