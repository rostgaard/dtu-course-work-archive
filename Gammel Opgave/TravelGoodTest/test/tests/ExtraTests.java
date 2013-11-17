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
public class ExtraTests {

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

    public ExtraTests() {
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
   // booking fails and cancelling fails
    public void C3() {

        int customerID = 6;
        int itineraryID = 6;
        port.createItinerary(customerID, itineraryID);

        FlightInformations flights = port.getAvailableFlights(customerID, itineraryID, startAirport1, destinationAirport, date1);
        FlightInformations flights2 = port.getAvailableFlights(customerID, itineraryID, startAirport2, destinationAirport, date1);
        port.addPlannedFlight(flights.getFlightInformation().get(0), customerID, itineraryID);
        port.addPlannedFlight(flights2.getFlightInformation().get(1), customerID, itineraryID);
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
        assertEquals("Confirmed", flightStatusList.get(1).getStatus());
        assertEquals("Unconfirmed", flightStatusList.get(2).getStatus());

        // Since the flights and hotels are booked in parallel, sometimes the hotel is not booked
        // before the flightbook fails (and in some rare cases it is just about to be booked which
        // will result in fail).
        assertFalse("Confirmed".equals(hotelStatusList.get(0).getStatus()));
    }

    @Test
    // book items - cancel them - add new items and book them all again.
    public void CB() {

        int customerID = 7;
        int itineraryID = 7;
        port.createItinerary(customerID, itineraryID);

        FlightInformations flights = port.getAvailableFlights(customerID, itineraryID, startAirport1, destinationAirport, date1);
        port.addPlannedFlight(flights.getFlightInformation().get(0), customerID, itineraryID);

        ReturnHotelList hotels = port.getAvailableHotels(customerID, date1, date2, city1, itineraryID);
        port.addPlannedHotel(customerID, itineraryID, hotels.getHotels().get(0));

        javax.xml.ws.Holder<ws.planning.ListOfFlights> listOfFlights = new javax.xml.ws.Holder<ws.planning.ListOfFlights>();
        javax.xml.ws.Holder<ws.planning.ListOfHotels> listOfHotels = new javax.xml.ws.Holder<ws.planning.ListOfHotels>();

        boolean isBooked = port.bookAll(customerID, itineraryID, creditInfo);
        assertTrue(isBooked);

        listOfFlights = new javax.xml.ws.Holder<ws.planning.ListOfFlights>();
        listOfHotels = new javax.xml.ws.Holder<ws.planning.ListOfHotels>();
        port.getItinerary(customerID, itineraryID, listOfFlights, listOfHotels);

        assertEquals("Confirmed", listOfFlights.value.getFlightStatus().get(0).getStatus());
        assertEquals("Confirmed", listOfHotels.value.getHotelStatus().get(0).getStatus());

        port.cancelBookingOperation(customerID, itineraryID);

        listOfFlights = new javax.xml.ws.Holder<ws.planning.ListOfFlights>();
        listOfHotels = new javax.xml.ws.Holder<ws.planning.ListOfHotels>();
        port.getItinerary(customerID, itineraryID, listOfFlights, listOfHotels);

        assertEquals("Cancelled", listOfFlights.value.getFlightStatus().get(0).getStatus());
        assertEquals("Cancelled", listOfHotels.value.getHotelStatus().get(0).getStatus());

        port.addPlannedFlight(flights.getFlightInformation().get(1), customerID, itineraryID);

        isBooked = port.bookAll(customerID, itineraryID, creditInfo);
        assertTrue(isBooked);

        listOfFlights = new javax.xml.ws.Holder<ws.planning.ListOfFlights>();
        listOfHotels = new javax.xml.ws.Holder<ws.planning.ListOfHotels>();
        port.getItinerary(customerID, itineraryID, listOfFlights, listOfHotels);

        assertEquals("Confirmed", listOfFlights.value.getFlightStatus().get(0).getStatus());
        assertEquals("Confirmed", listOfFlights.value.getFlightStatus().get(1).getStatus());
        assertEquals("Confirmed", listOfHotels.value.getHotelStatus().get(0).getStatus());
    }
}