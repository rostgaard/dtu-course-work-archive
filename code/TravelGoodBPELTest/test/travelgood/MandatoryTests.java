package travelgood;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import dk.dtu.imm.fastmoney.types.ExpirationDateType;
import java.util.List;
import java.util.Random;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;
import org.junit.*;
import static org.junit.Assert.*;
import ws.dtu.lameduck.types.FlightInformation;
import ws.dtu.lameduck.types.FlightList;
import ws.dtu.niceview.types.HotelInformation;
import ws.dtu.niceview.types.HotelList;
import ws.travelgoodbpel.FlightBooking;
import ws.travelgoodbpel.FlightBookings;
import ws.travelgoodbpel.HotelBooking;
import ws.travelgoodbpel.HotelBookings;

public class MandatoryTests {
    
    private String customerID = "customer";
    private FlightList flights;
    private HotelList hotels;
    private Holder<FlightBookings> flightBookings = new Holder<>();
    private Holder<HotelBookings> hotelBookings = new Holder<>();
    private XMLGregorianCalendar date1, date2;
    
    private CreditCardInfoType cc = new CreditCardInfoType();
    
    public MandatoryTests() {
        Random r = new Random();
        customerID += String.format("%04d", r.nextInt(10000));
    }
    
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
    
    @Test
    public void testP1(){
        String itineraryID = "itineraryP1";
        
        TravelGoodClient.createItinerary(customerID, itineraryID);
        FlightInformation[] flightInfo = new FlightInformation[3];
        HotelInformation[] hotelInfo = new HotelInformation[2];
        
        // Plan first flight
        flights = TravelGoodClient.getFlights(customerID, itineraryID, "CPH", "SFO", date1);
        flightInfo[0] = flights.getFlights().get(0);
        TravelGoodClient.addFlight(customerID, itineraryID, flightInfo[0]);
        
        // Plan first hotel
        hotels = TravelGoodClient.getHotels(customerID, itineraryID, "San Francisco", date1, date2);
        hotelInfo[0] = hotels.getHotels().get(0);
        TravelGoodClient.addHotel(customerID, itineraryID, hotelInfo[0]);
        
        // Plan second flight
        flights = TravelGoodClient.getFlights(customerID, itineraryID, "SFO", "CPH", date1);
        flightInfo[1] = flights.getFlights().get(0);
        TravelGoodClient.addFlight(customerID, itineraryID, flightInfo[1]);
        
        // Plan third flight
        flights = TravelGoodClient.getFlights(customerID, itineraryID, "CPH", "CDG", date1);
        flightInfo[2] = flights.getFlights().get(0);
        TravelGoodClient.addFlight(customerID, itineraryID, flightInfo[2]);
        
        // Plan second hotel
        hotels = TravelGoodClient.getHotels(customerID, itineraryID, "Paris", date1, date2);
        hotelInfo[1] = hotels.getHotels().get(0);
        TravelGoodClient.addHotel(customerID, itineraryID, hotelInfo[1]);
        
        // Check itinerary
        TravelGoodClient.getItinerary(customerID, itineraryID, flightBookings, hotelBookings);
        
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
            compareHotelInformation(hotelBooking.getHotelInformation(), hotelInfo[i]);
        }
        
        // Book itinerary
        TravelGoodClient.bookItinerary(customerID, itineraryID, cc);
        
        
        // Check itinerary after booking
        TravelGoodClient.getItinerary(customerID, itineraryID, flightBookings, hotelBookings);
        
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
            compareHotelInformation(hotelBooking.getHotelInformation(), hotelInfo[i]);
        }
    }
    
    @Test
    public void testP2(){
        String itineraryID = "itineraryP2";
        TravelGoodClient.createItinerary(customerID, itineraryID);
        
        // Plan first flight
        flights = TravelGoodClient.getFlights(customerID, itineraryID, "CPH", "SFO", date1);
        FlightInformation info = flights.getFlights().get(0);
        TravelGoodClient.addFlight(customerID, itineraryID, info);
        
        TravelGoodClient.cancelPlanning(customerID, itineraryID);
    }
    
    @Test
    public void testB(){
        String itineraryID = "itineraryB";
        TravelGoodClient.createItinerary(customerID, itineraryID);
        
        // Plan first flight
        flights = TravelGoodClient.getFlights(customerID, itineraryID, "CPH", "SFO", date1);
        FlightInformation canceledFlightInfo = flights.getFlights().get(0);
        TravelGoodClient.addFlight(customerID, itineraryID, canceledFlightInfo);
        
        // Plan first hotel
        hotels = TravelGoodClient.getHotels(customerID, itineraryID, "Crazy Town", date1, date2);
        HotelInformation alreadyBookedHotel = hotels.getHotels().get(0);
        TravelGoodClient.addHotel(customerID, itineraryID, alreadyBookedHotel);
        
        // Plan second hotel
        hotels = TravelGoodClient.getHotels(customerID, itineraryID, "San Francisco", date1, date2);
        HotelInformation unconfirmedHotelInfo = hotels.getHotels().get(0);
        TravelGoodClient.addHotel(customerID, itineraryID, unconfirmedHotelInfo);
        
        assertFalse(TravelGoodClient.bookItinerary(customerID, itineraryID, cc));
        
        // Check statuses
        TravelGoodClient.getItinerary(customerID, itineraryID, flightBookings, hotelBookings);
        assertEquals(flightBookings.value.getFlightBooking().get(0).getStatus(), "canceled");
        assertEquals(hotelBookings.value.getHotelBooking().get(0).getStatus(), "unconfirmed");
        assertEquals(hotelBookings.value.getHotelBooking().get(1).getStatus(), "unconfirmed");
        
    }
    
    @Test
    public void testC1(){
        String itineraryID = "itineraryC1";
        TravelGoodClient.createItinerary(customerID, itineraryID);
        
        // Plan first flight
        flights = TravelGoodClient.getFlights(customerID, itineraryID, "CPH", "SFO", date1);
        FlightInformation canceledFlightInfo = flights.getFlights().get(0);
        TravelGoodClient.addFlight(customerID, itineraryID, canceledFlightInfo);
        
        // Plan two hotels
        hotels = TravelGoodClient.getHotels(customerID, itineraryID, "San Francisco", date1, date2);
        HotelInformation firstHotel = hotels.getHotels().get(0);
        HotelInformation secondHotel = hotels.getHotels().get(1);
        
        TravelGoodClient.addHotel(customerID, itineraryID, firstHotel);
        TravelGoodClient.addHotel(customerID, itineraryID, secondHotel);
        
        
        // Book itinerary
        assertTrue(TravelGoodClient.bookItinerary(customerID, itineraryID, cc));
        
        // Check statuses
        TravelGoodClient.getItinerary(customerID, itineraryID, flightBookings, hotelBookings);
        assertEquals(flightBookings.value.getFlightBooking().get(0).getStatus(), "confirmed");
        assertEquals(hotelBookings.value.getHotelBooking().get(0).getStatus(), "confirmed");
        assertEquals(hotelBookings.value.getHotelBooking().get(1).getStatus(), "confirmed");
        
        
        // Cancel itinerary
        assertTrue(TravelGoodClient.cancelItinerary(customerID, itineraryID, cc));
        
        TravelGoodClient.getItinerary(customerID, itineraryID, flightBookings, hotelBookings);
        assertEquals(flightBookings.value.getFlightBooking().get(0).getStatus(), "canceled");
        assertEquals(hotelBookings.value.getHotelBooking().get(0).getStatus(), "canceled");
        assertEquals(hotelBookings.value.getHotelBooking().get(1).getStatus(), "canceled");
    }
    
    @Test
    public void testC2(){
        String itineraryID = "itineraryC2";
        TravelGoodClient.createItinerary(customerID, itineraryID);
        
        // Plan first flight
        flights = TravelGoodClient.getFlights(customerID, itineraryID, "CPH", "SFO", date1);
        FlightInformation canceledFlightInfo = flights.getFlights().get(0);
        TravelGoodClient.addFlight(customerID, itineraryID, canceledFlightInfo);
        
        // Plan two hotels
        hotels = TravelGoodClient.getHotels(customerID, itineraryID, "San Francisco", date1, date2);
        HotelInformation firstHotel = hotels.getHotels().get(2); // Unable to cancel this hotel
        HotelInformation secondHotel = hotels.getHotels().get(1);
        
        TravelGoodClient.addHotel(customerID, itineraryID, firstHotel);
        TravelGoodClient.addHotel(customerID, itineraryID, secondHotel);
        
        
        // Book itinerary
        assertTrue(TravelGoodClient.bookItinerary(customerID, itineraryID, cc));
        
        // Check statuses
        TravelGoodClient.getItinerary(customerID, itineraryID, flightBookings, hotelBookings);
        assertEquals(flightBookings.value.getFlightBooking().get(0).getStatus(), "confirmed");
        assertEquals(hotelBookings.value.getHotelBooking().get(0).getStatus(), "confirmed");
        assertEquals(hotelBookings.value.getHotelBooking().get(1).getStatus(), "confirmed");
        
        
        // Cancel itinerary (which fails, returns false)
        assertFalse(TravelGoodClient.cancelItinerary(customerID, itineraryID, cc));
        
        TravelGoodClient.getItinerary(customerID, itineraryID, flightBookings, hotelBookings);
        assertEquals(flightBookings.value.getFlightBooking().get(0).getStatus(), "canceled");
        assertEquals(hotelBookings.value.getHotelBooking().get(0).getStatus(), "confirmed");
        assertEquals(hotelBookings.value.getHotelBooking().get(1).getStatus(), "canceled");        
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
    
    private void compareHotelInformation(HotelInformation h1, HotelInformation h2) {
        assertEquals(h1.getAddress(), h2.getAddress());
        assertEquals(h1.getBookingNo(), h2.getBookingNo());
        assertEquals(h1.getName(), h2.getName());
        assertTrue(h1.getPrice() == h2.getPrice());
        assertEquals(h1.getReservationService(), h2.getReservationService());
        assertEquals(h1.isCcRequired(), h2.isCcRequired());
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
