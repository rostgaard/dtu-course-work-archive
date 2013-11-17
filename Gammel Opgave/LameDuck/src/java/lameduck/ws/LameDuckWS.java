 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lameduck.ws;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import dk.dtu.imm.fastmoney.BankService;
import java.util.ArrayList;
import java.util.HashMap;
import javax.jws.WebService;
import ws.lameduck.BookFlightFault;
import ws.lameduck.CancelFlightFault;
import ws.lameduck.FlightInformations;
import javax.xml.datatype.XMLGregorianCalendar;
import ws.lameduck.FlightInformation;
import ws.lameduck.Flight;
import dk.dtu.imm.fastmoney.types.AccountType;
import dk.dtu.imm.fastmoney.BankPortType;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
/*
 *
 * @author Andreas
 */
@WebService(serviceName = "LameDuckWSDLService", portName = "LameDuckWSDLPort", endpointInterface = "ws.lameduck.LameDuckWSDLPortType", targetNamespace = "http://lameduck.ws", wsdlLocation = "WEB-INF/wsdl/LameDuckWS/LameDuckWSDLWrapper.wsdl")
public class LameDuckWS {

    private BankService service = new BankService();
    AccountType account = new AccountType();
    HashMap<String, ArrayList<FlightInformation>> flights;
    // We identify customers by their creditcard number! (should we do this?)
    HashMap<Integer, ArrayList<Integer>> bookings = new HashMap<Integer, ArrayList<Integer>>();
    int group = 8;

    public LameDuckWS() {
        account.setName("LameDuck");
        account.setNumber("50208812");

        flights = new HashMap<String, ArrayList<FlightInformation>>();

        XMLGregorianCalendar date1 = new XMLGregorianCalendarImpl();
        date1.setMonth(12);
        date1.setDay(20);
        date1.setYear(2012);

        XMLGregorianCalendar date2 = new XMLGregorianCalendarImpl();
        date2.setMonth(12);
        date2.setDay(21);
        date2.setYear(2012);

        Flight Flight1 = setFlight("Copenhagen", "Stockholm", date1, date2, "SAS");
        FlightInformation flightInfo1 = setFlightInfo(123, 100, "SAS", Flight1);

        Flight Flight2 = setFlight("Oslo", "Stockholm", date1, date2, "SAS");
        FlightInformation flightInfo2 = setFlightInfo(124, 100, "SAS", Flight2);

        Flight FlightERROR2 = setFlight("Oslo", "Stockholm", date1, date2, "SAS");
        FlightInformation flightInfoERROR2 = setFlightInfo(-999, 100, "SAS", FlightERROR2);


        Flight Flight3 = setFlight("Copenhagen", "Stockholm", date1, date2, "KLM");
        FlightInformation flightInfo3 = setFlightInfo(125, 125, "KLM", Flight3);

        Flight FlightERROR = setFlight("Copenhagen", "Stockholm", date1, date2, "ERROR");
        FlightInformation flightInfoERROR = setFlightInfo(126, -999, "ERROR", FlightERROR);


        ArrayList<FlightInformation> cphstock = new ArrayList<FlightInformation>();
        cphstock.add(flightInfo1);
        cphstock.add(flightInfo3);
        cphstock.add(flightInfoERROR);

        ArrayList<FlightInformation> oslstock = new ArrayList<FlightInformation>();
        oslstock.add(flightInfo2);
        oslstock.add(flightInfoERROR2);

        flights.put(Flight1.getStartAirport() + ";" + Flight1.getEndAirport(), cphstock);
        flights.put(Flight2.getStartAirport() + ";" + Flight2.getEndAirport(), oslstock);
    }

    private Flight setFlight(String startAirport, String destinationAirport, XMLGregorianCalendar liftOff, XMLGregorianCalendar landing, String carrier) {
        Flight flight = new Flight();

        flight.setStartAirport(startAirport);
        flight.setEndAirport(destinationAirport);
        flight.setLanding(landing);
        flight.setLiftOff(liftOff);
        flight.setCarrier(carrier);

        return flight;
    }

    private FlightInformation setFlightInfo(int bookingNumber, int price, String airlineService, Flight flight) {
        FlightInformation flightInfo = new FlightInformation();

        flightInfo.setAirlineReservationService(airlineService);
        flightInfo.setBookingNumber(bookingNumber);
        flightInfo.setPrice(price);
        flightInfo.setFlight(flight);

        return flightInfo;
    }

    private double findBookingPrice(int bookingNumber) {
        for (ArrayList<FlightInformation> flightInfos : flights.values()) {
            for (FlightInformation flightInfo : flightInfos) {
                if (flightInfo.getBookingNumber() == bookingNumber) {
                    return flightInfo.getPrice();
                }
            }
        }
        return -999;
    }

    private void addBooking(int bookingNumber, int creditCardNumber) {
        ArrayList<Integer> newBookings = new ArrayList<Integer>();
        if (bookings.containsKey(creditCardNumber)) {
            ArrayList<Integer> currentBookings = bookings.get(creditCardNumber);
            newBookings.addAll(currentBookings);
        }
        newBookings.add(bookingNumber);
        bookings.put(creditCardNumber, newBookings);
    }

    private void removeBooking(int bookingNumber, int creditCardNumber) {
        if (bookings.containsKey(creditCardNumber)) {
            bookings.get(creditCardNumber).remove(bookingNumber);
        }
    }

    public boolean bookFlight(int bookingNumber, CreditCardInfoType creditCardInfo) throws BookFlightFault {
        boolean result = false;
        double amount = findBookingPrice(bookingNumber);

        if (amount != -999) {
            try { // Call Web Service Operation
                BankPortType port = service.getBankPort();
                result = port.chargeCreditCard(group, creditCardInfo, (int) amount, account);

                addBooking(bookingNumber, Integer.parseInt(creditCardInfo.getNumber()));
            } catch (Exception ex) {
                throw new BookFlightFault("An error occured during booking", "Booking failed on flight with booking number " + bookingNumber);
            }
        }
        if (result) {
            return result;
        } else {
            throw new BookFlightFault("An error occured during booking", "Booking failed on flight with booking number " + bookingNumber);
        }
    }

    public boolean cancelFlight(int bookingNumber, int price, CreditCardInfoType creditCardInfo) throws CancelFlightFault {
        boolean result = false;

        if (bookingNumber == -999) { // For testing purpose
            throw new CancelFlightFault("An error occured during cancelling", "Could not cancel booking for flight with booking number " + bookingNumber);
        }

        try { // Call Web Service Operation
            BankPortType port = service.getBankPort();
            result = port.refundCreditCard(group, creditCardInfo, price/2, account);
        } catch (Exception ex) {
            throw new CancelFlightFault("An error occured during cancelling", "Could not cancel booking for flight with booking number " + bookingNumber);
        }

        if (result) {
            removeBooking(bookingNumber, bookingNumber);
            return true;
        } else {
            throw new CancelFlightFault("An error occured during cancelling", "Could not cancel booking for flight with booking number " + bookingNumber);
        }
    }

    public FlightInformations getFlight(String startAirport, String destinationAirport, XMLGregorianCalendar date) {
        FlightInformations result = new FlightInformations();
        FlightInformations flightInfos = new FlightInformations();

        if (startAirport.equals("test")) {
            try { // For testing purposes
                Thread.sleep(10000);
            } catch (Exception e) {
            }
        }

        ArrayList<FlightInformation> potentialFlights = flights.get(startAirport + ";" + destinationAirport);
        ArrayList<FlightInformation> foundFlights = new ArrayList<FlightInformation>();

        if (potentialFlights != null) {
            for (FlightInformation flightInformation : potentialFlights) {
                XMLGregorianCalendar flightDate = flightInformation.getFlight().getLiftOff();

                if (date.getDay() == flightDate.getDay() && date.getMonth() == flightDate.getMonth()) {
                    foundFlights.add(flightInformation);
                }
            }
        }

        flightInfos.getFlightInformation().addAll(foundFlights);

        return flightInfos;
    }
}

