package tests;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import ws.planning.*;
import javax.xml.datatype.*;
import java.util.*;
import dk.dtu.imm.fastmoney.types.ExpirationDateType;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;

/**
 *
 * @author Gert
 */
public class RequiredTests {

    ItineraryService service;
    ItineraryPortType port;
    XMLGregorianCalendar date1;
    XMLGregorianCalendar date2;
    String startAirport1;
    String startAirport2;
    String destinationAirport;
    CreditCardInfoType creditInfo;
    String city1;
    String city2;

    public RequiredTests() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        service = new ws.planning.ItineraryService();
        port = service.getItineraryPort();

        date1 = new XMLGregorianCalendarImpl();
        date1.setMonth(12);
        date1.setDay(20);
        date1.setYear(2012);

        date2 = new XMLGregorianCalendarImpl();
        date2.setMonth(12);
        date2.setDay(21);
        date2.setYear(2012);

        startAirport1 = "Copenhagen";
        startAirport2 = "Oslo";
        destinationAirport = "Stockholm";

        city1 = "KBH";
        city2 = "New York";

        creditInfo = new CreditCardInfoType();

        ExpirationDateType expr = new ExpirationDateType();
        expr.setMonth(5);
        expr.setYear(9);

        creditInfo.setExpirationDate(expr);
        creditInfo.setName("Anne Strandberg");
        creditInfo.setNumber("50408816");

    }

    @After
    public void tearDown() {
    }

    @Test
    public void P1() {
        int customerID = 1;
        int itineraryID = 1;
        port.createItinerary(customerID, itineraryID);


        FlightInformations flights = port.getAvailableFlights(customerID, itineraryID, startAirport1, destinationAirport, date1);
        port.addPlannedFlight(flights.getFlightInformation().get(0), customerID, itineraryID);

        ReturnHotelList hotels = port.getAvailableHotels(customerID, date1, date2, city1, itineraryID);
        port.addPlannedHotel(customerID, itineraryID, hotels.getHotels().get(0));

        port.addPlannedFlight(flights.getFlightInformation().get(1), customerID, itineraryID);


        flights = port.getAvailableFlights(customerID, itineraryID, startAirport2, destinationAirport, date1);
        port.addPlannedFlight(flights.getFlightInformation().get(0), customerID, itineraryID);

        hotels = port.getAvailableHotels(customerID, date1, date2, city2, itineraryID);
        port.addPlannedHotel(customerID, itineraryID, hotels.getHotels().get(0));

        javax.xml.ws.Holder<ws.planning.ListOfFlights> listOfFlights = new javax.xml.ws.Holder<ws.planning.ListOfFlights>();
        javax.xml.ws.Holder<ws.planning.ListOfHotels> listOfHotels = new javax.xml.ws.Holder<ws.planning.ListOfHotels>();
        port.getItinerary(customerID, itineraryID, listOfFlights, listOfHotels);

        List<FlightStatus> flightStatusList = listOfFlights.value.getFlightStatus();
        for (int i = 0; i < flightStatusList.size(); i++) {
            assertEquals("Unconfirmed", flightStatusList.get(i).getStatus());
        }

        List<HotelStatus> hotelStatusList = listOfHotels.value.getHotelStatus();
        for (int i = 0; i < hotelStatusList.size(); i++) {
            assertEquals("Unconfirmed", hotelStatusList.get(i).getStatus());
        }

        boolean isBooked = port.bookAll(customerID, itineraryID, creditInfo);
        assertTrue(isBooked);

        listOfFlights = new javax.xml.ws.Holder<ws.planning.ListOfFlights>();
        listOfHotels = new javax.xml.ws.Holder<ws.planning.ListOfHotels>();
        port.getItinerary(customerID, itineraryID, listOfFlights, listOfHotels);

        flightStatusList = listOfFlights.value.getFlightStatus();
        for (int i = 0; i < flightStatusList.size(); i++) {
            assertEquals("Confirmed", flightStatusList.get(i).getStatus());
        }

        hotelStatusList = listOfHotels.value.getHotelStatus();
        for (int i = 0; i < hotelStatusList.size(); i++) {
            assertEquals("Confirmed", hotelStatusList.get(i).getStatus());
        }

    }

    @Test
    public void P2() {
        int customerID = 2;
        int itineraryID = 2;
        port.createItinerary(customerID, itineraryID);

        FlightInformations flights = port.getAvailableFlights(customerID, itineraryID, startAirport1, destinationAirport, date1);
        port.addPlannedFlight(flights.getFlightInformation().get(0), customerID, itineraryID);

        port.cancelPlanningOperation(customerID, itineraryID);
    }

    @Test
    public void B() {

        int customerID = 3;
        int itineraryID = 3;
        port.createItinerary(customerID, itineraryID);


        FlightInformations flights = port.getAvailableFlights(customerID, itineraryID, startAirport1, destinationAirport, date1);
        port.addPlannedFlight(flights.getFlightInformation().get(1), customerID, itineraryID);
        port.addPlannedFlight(flights.getFlightInformation().get(2), customerID, itineraryID);

        ReturnHotelList hotels = port.getAvailableHotels(customerID, date1, date2, city1, itineraryID);
        port.addPlannedHotel(customerID, itineraryID, hotels.getHotels().get(0));

        javax.xml.ws.Holder<ws.planning.ListOfFlights> listOfFlights = new javax.xml.ws.Holder<ws.planning.ListOfFlights>();
        javax.xml.ws.Holder<ws.planning.ListOfHotels> listOfHotels = new javax.xml.ws.Holder<ws.planning.ListOfHotels>();
        port.getItinerary(customerID, itineraryID, listOfFlights, listOfHotels);

        List<FlightStatus> flightStatusList = listOfFlights.value.getFlightStatus();
        for (int i = 0; i < flightStatusList.size(); i++) {
            assertEquals("Unconfirmed", flightStatusList.get(i).getStatus());
        }

        List<HotelStatus> hotelStatusList = listOfHotels.value.getHotelStatus();
        for (int i = 0; i < hotelStatusList.size(); i++) {
            assertEquals("Unconfirmed", hotelStatusList.get(i).getStatus());
        }

        boolean isBooked = port.bookAll(customerID, itineraryID, creditInfo);

        assertFalse(isBooked);

        listOfFlights = new javax.xml.ws.Holder<ws.planning.ListOfFlights>();
        listOfHotels = new javax.xml.ws.Holder<ws.planning.ListOfHotels>();
        port.getItinerary(customerID, itineraryID, listOfFlights, listOfHotels);

        flightStatusList = listOfFlights.value.getFlightStatus();

        hotelStatusList = listOfHotels.value.getHotelStatus();

        assertEquals("Cancelled", flightStatusList.get(0).getStatus());
        assertEquals("Unconfirmed", flightStatusList.get(1).getStatus());

        // Since the flights and hotels are booked in parallel, sometimes the hotel is not booked
        // before the flightbook fails.
        assertFalse("Confirmed".equals(hotelStatusList.get(0).getStatus()));
    }

    @Test
    public void C1() {

        int customerID = 4;
        int itineraryID = 4;

        port.createItinerary(customerID, itineraryID);

        FlightInformations flights = port.getAvailableFlights(customerID, itineraryID, startAirport1, destinationAirport, date1);
        port.addPlannedFlight(flights.getFlightInformation().get(0), customerID, itineraryID);
        port.addPlannedFlight(flights.getFlightInformation().get(1), customerID, itineraryID);

        ReturnHotelList hotels = port.getAvailableHotels(customerID, date1, date2, city1, itineraryID);
        port.addPlannedHotel(customerID, itineraryID, hotels.getHotels().get(0));

        javax.xml.ws.Holder<ws.planning.ListOfFlights> listOfFlights = new javax.xml.ws.Holder<ws.planning.ListOfFlights>();
        javax.xml.ws.Holder<ws.planning.ListOfHotels> listOfHotels = new javax.xml.ws.Holder<ws.planning.ListOfHotels>();
        port.getItinerary(customerID, itineraryID, listOfFlights, listOfHotels);

        List<FlightStatus> flightStatusList = listOfFlights.value.getFlightStatus();
        for (int i = 0; i < flightStatusList.size(); i++) {
            assertEquals("Unconfirmed", flightStatusList.get(i).getStatus());
        }

        List<HotelStatus> hotelStatusList = listOfHotels.value.getHotelStatus();
        for (int i = 0; i < hotelStatusList.size(); i++) {
            assertEquals("Unconfirmed", hotelStatusList.get(i).getStatus());
        }

        boolean isBooked = port.bookAll(customerID, itineraryID, creditInfo);
        assertTrue(isBooked);

        listOfFlights = new javax.xml.ws.Holder<ws.planning.ListOfFlights>();
        listOfHotels = new javax.xml.ws.Holder<ws.planning.ListOfHotels>();
        port.getItinerary(customerID, itineraryID, listOfFlights, listOfHotels);

        flightStatusList = listOfFlights.value.getFlightStatus();
        hotelStatusList = listOfHotels.value.getHotelStatus();

        assertEquals("Confirmed", flightStatusList.get(0).getStatus());
        assertEquals("Confirmed", flightStatusList.get(1).getStatus());
        assertEquals("Confirmed", hotelStatusList.get(0).getStatus());

        port.cancelBookingOperation(customerID, itineraryID);
        listOfFlights = new javax.xml.ws.Holder<ws.planning.ListOfFlights>();
        listOfHotels = new javax.xml.ws.Holder<ws.planning.ListOfHotels>();
        port.getItinerary(customerID, itineraryID, listOfFlights, listOfHotels);

        flightStatusList = listOfFlights.value.getFlightStatus();
        hotelStatusList = listOfHotels.value.getHotelStatus();

        assertEquals("Cancelled", flightStatusList.get(0).getStatus());
        assertEquals("Cancelled", flightStatusList.get(1).getStatus());
        assertEquals("Cancelled", hotelStatusList.get(0).getStatus());

    }

    @Test
    public void C2() {

        int customerID = 5;
        int itineraryID = 5;
        port.createItinerary(customerID, itineraryID);

        FlightInformations flights = port.getAvailableFlights(customerID, itineraryID, startAirport1, destinationAirport, date1);
        FlightInformations flights2 = port.getAvailableFlights(customerID, itineraryID, startAirport2, destinationAirport, date1);
        port.addPlannedFlight(flights.getFlightInformation().get(0), customerID, itineraryID);
        port.addPlannedFlight(flights2.getFlightInformation().get(1), customerID, itineraryID);

        ReturnHotelList hotels = port.getAvailableHotels(customerID, date1, date2, city1, itineraryID);
        port.addPlannedHotel(customerID, itineraryID, hotels.getHotels().get(0));

        javax.xml.ws.Holder<ws.planning.ListOfFlights> listOfFlights = new javax.xml.ws.Holder<ws.planning.ListOfFlights>();
        javax.xml.ws.Holder<ws.planning.ListOfHotels> listOfHotels = new javax.xml.ws.Holder<ws.planning.ListOfHotels>();
        port.getItinerary(customerID, itineraryID, listOfFlights, listOfHotels);

        List<FlightStatus> flightStatusList = listOfFlights.value.getFlightStatus();
        for (int i = 0; i < flightStatusList.size(); i++) {
            assertEquals("Unconfirmed", flightStatusList.get(i).getStatus());
        }

        List<HotelStatus> hotelStatusList = listOfHotels.value.getHotelStatus();
        for (int i = 0; i < hotelStatusList.size(); i++) {
            assertEquals("Unconfirmed", hotelStatusList.get(i).getStatus());
        }

        boolean isBooked = port.bookAll(customerID, itineraryID, creditInfo);
        assertTrue(isBooked);

        listOfFlights = new javax.xml.ws.Holder<ws.planning.ListOfFlights>();
        listOfHotels = new javax.xml.ws.Holder<ws.planning.ListOfHotels>();
        port.getItinerary(customerID, itineraryID, listOfFlights, listOfHotels);

        flightStatusList = listOfFlights.value.getFlightStatus();
        hotelStatusList = listOfHotels.value.getHotelStatus();

        assertEquals("Confirmed", flightStatusList.get(0).getStatus());
        assertEquals("Confirmed", flightStatusList.get(1).getStatus());
        assertEquals("Confirmed", hotelStatusList.get(0).getStatus());

        port.cancelBookingOperation(customerID, itineraryID);

        listOfFlights = new javax.xml.ws.Holder<ws.planning.ListOfFlights>();
        listOfHotels = new javax.xml.ws.Holder<ws.planning.ListOfHotels>();
        port.getItinerary(customerID, itineraryID, listOfFlights, listOfHotels);

        flightStatusList = listOfFlights.value.getFlightStatus();
        hotelStatusList = listOfHotels.value.getHotelStatus();

        assertEquals("Cancelled", flightStatusList.get(0).getStatus());
        assertEquals("Confirmed", flightStatusList.get(1).getStatus());
        assertEquals("Cancelled", hotelStatusList.get(0).getStatus());
    }
}
