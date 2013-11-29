package travelgood;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import dk.dtu.imm.fastmoney.types.ExpirationDateType;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;
import org.junit.*;
import static org.junit.Assert.*;
import servicewrappers.BPELWrapper;
import ws.dtu.lameduck.types.FlightInformation;
import ws.dtu.lameduck.types.FlightList;
import ws.dtu.niceview.types.HotelInformation;
import ws.dtu.niceview.types.HotelList;
import ws.travelgoodbpel.FlightBooking;
import ws.travelgoodbpel.FlightBookings;
import ws.travelgoodbpel.HotelBooking;
import ws.travelgoodbpel.HotelBookings;

public class MandatoryTests {
    
    private TravelGoodClient client;
    private String customerID = "customer007";
    private FlightList flights;
    private HotelList hotels;
    private Holder<FlightBookings> flightBookings = new Holder<FlightBookings>();
    private Holder<HotelBookings> hotelBookings = new Holder<HotelBookings>();
    private XMLGregorianCalendar date1, date2;
    
    private CreditCardInfoType cc = new CreditCardInfoType();
    
    @Before
    public void setup() throws DatatypeConfigurationException {
        lameDuckResetOperation();
        niceViewResetOperation();
        
        DatatypeFactory df = DatatypeFactory.newInstance();
        date1 = df.newXMLGregorianCalendar("2013-11-17T00:00:00");
        date2 = df.newXMLGregorianCalendar("2013-11-18T00:00:00");
        
        cc.setName("Tick Joachim");
        cc.setNumber("50408824");
        ExpirationDateType exp = new ExpirationDateType();
        exp.setMonth(2);
        exp.setYear(11);
        cc.setExpirationDate(exp);
    }
    
    public MandatoryTests() {
        client = new BPELWrapper();
    }
    
    @Test
    public void testP1(){
        String itineraryID = "itineraryP1";
        
        client.createItinerary(customerID, itineraryID);
        FlightInformation[] flightInfo = new FlightInformation[3];
        HotelInformation[] hotelInfo = new HotelInformation[2];
        
        // Plan first flight
        flights = client.getFlights(customerID, itineraryID, "CPH", "SFO", date1);
        flightInfo[0] = flights.getFlights().get(0);
        client.addFlight(customerID, itineraryID, flightInfo[0]);
        
        // Plan first hotel
        hotels = client.getHotels(customerID, itineraryID, "San Francisco", date1, date2);
        hotelInfo[0] = hotels.getHotels().get(0);
        client.addHotel(customerID, itineraryID, hotelInfo[0].getBookingNo());
        
        // Plan second flight
        flights = client.getFlights(customerID, itineraryID, "SFO", "CPH", date1);
        flightInfo[1] = flights.getFlights().get(0);
        client.addFlight(customerID, itineraryID, flightInfo[1]);
        
        // Plan third flight
        flights = client.getFlights(customerID, itineraryID, "CPH", "CDG", date1);
        flightInfo[2] = flights.getFlights().get(0);
        client.addFlight(customerID, itineraryID, flightInfo[2]);
        
        // Plan second hotel
        hotels = client.getHotels(customerID, itineraryID, "Paris", date1, date2);
        hotelInfo[1] = hotels.getHotels().get(0);
        client.addHotel(customerID, itineraryID, hotelInfo[1].getBookingNo());
        
        // Check itinerary
        client.getItinerary(customerID, itineraryID, flightBookings, hotelBookings);
        
        List<FlightBooking> flightBookingList = flightBookings.value.getFlightBooking();
        FlightBooking flightBooking;
        for (int i = 0; i < flightBookingList.size(); i++) {
            // Check booking unconfirmed
            flightBooking = flightBookingList.get(i);
            assertEquals(flightBooking.getStatus(), "unconfirmed");

            // Check flight information
            compareFlightInformation(flightBooking.getFlightInformation(), flightInfo[i]);
        }
        
        List<HotelBooking> hotelBookingList = hotelBookings.value.getHotelBooking();
        HotelBooking hotelBooking;
        for (int i = 0; i < hotelBookingList.size(); i++) {
            // Check booking unconfirmed
            hotelBooking = hotelBookingList.get(i);
            assertEquals(hotelBooking.getStatus(), "unconfirmed");

            // Check information
            assertEquals(hotelBooking.getHotelBookingNo(), hotelInfo[i].getBookingNo());
        }
        
        // Book itinerary
        client.bookItinerary(customerID, itineraryID, cc);
        
        
        // Check itinerary after booking
        client.getItinerary(customerID, itineraryID, flightBookings, hotelBookings);
        
        flightBookingList = flightBookings.value.getFlightBooking();
        for (int i = 0; i < flightBookingList.size(); i++) {
            // Check booking unconfirmed
            flightBooking = flightBookingList.get(i);
            assertEquals(flightBooking.getStatus(), "confirmed");

            // Check flight information
            compareFlightInformation(flightBooking.getFlightInformation(), flightInfo[i]);
        }
        
        hotelBookingList = hotelBookings.value.getHotelBooking();
        for (int i = 0; i < hotelBookingList.size(); i++) {
            // Check booking unconfirmed
            hotelBooking = hotelBookingList.get(i);
            assertEquals(hotelBooking.getStatus(), "confirmed");

            // Check information
            assertEquals(hotelBooking.getHotelBookingNo(), hotelInfo[i].getBookingNo());
        }
    }
    
    @Test
    public void testP2(){
        String itineraryID = "itineraryP2";
        client.createItinerary(customerID, itineraryID);
        
        // Plan first flight
        flights = client.getFlights(customerID, itineraryID, "CPH", "SFO", date1);
        FlightInformation info = flights.getFlights().get(0);
        client.addFlight(customerID, itineraryID, info);
        
        client.cancelPlanning(customerID, itineraryID);
    }
    
    @Test
    public void testB(){
        String itineraryID = "itineraryB";
        client.createItinerary(customerID, itineraryID);
        
        // Plan first flight
        flights = client.getFlights(customerID, itineraryID, "CPH", "SFO", date1);
        FlightInformation canceledFlightInfo = flights.getFlights().get(0);
        client.addFlight(customerID, itineraryID, canceledFlightInfo);
        
        // Plan first hotel
        hotels = client.getHotels(customerID, itineraryID, "Crazy Town", date1, date2);
        HotelInformation alreadyBookedHotel = hotels.getHotels().get(0);
        client.addHotel(customerID, itineraryID, alreadyBookedHotel.getBookingNo());
        
        // Plan second flight
        flights = client.getFlights(customerID, itineraryID, "SFO", "CPH", date1);
        FlightInformation unconfirmedFlightInfo = flights.getFlights().get(0);
        client.addFlight(customerID, itineraryID, unconfirmedFlightInfo);
        
        client.bookItinerary(customerID, itineraryID, cc);
        
        // Check statuses
        client.getItinerary(customerID, itineraryID, flightBookings, hotelBookings);
        assertEquals(flightBookings.value.getFlightBooking().get(0).getStatus(), "canceled");
        assertEquals(hotelBookings.value.getHotelBooking().get(0).getStatus(), "unconfirmed");
        assertEquals(flightBookings.value.getFlightBooking().get(1).getStatus(), "unconfirmed");
    }
    
    @Test
    public void testC1(){
        String itineraryID = "itineraryC1";
        client.createItinerary(customerID, itineraryID);
        
    }
    
    @Test
    public void testC2(){
        String itineraryID = "itineraryC2";
        client.createItinerary(customerID, itineraryID);
        
    }
    
    private void compareFlightInformation(FlightInformation f1, FlightInformation f2) {
        assertEquals(f1.getBookingNo(), f2.getBookingNo());
        assertEquals(f1.getPrice(), f2.getPrice());
        assertEquals(f1.getReservationSevice(), f2.getReservationSevice());
        
        assertEquals(f1.getFlight().getArrival(), f2.getFlight().getArrival());
        assertEquals(f1.getFlight().getCarrier(), f2.getFlight().getCarrier());
        assertEquals(f1.getFlight().getDestination(), f2.getFlight().getDestination());
        assertEquals(f1.getFlight().getLiftOff(), f2.getFlight().getLiftOff());
        assertEquals(f1.getFlight().getOrigin(), f2.getFlight().getOrigin());
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
