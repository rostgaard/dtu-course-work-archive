/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NiceViewService;

import dk.dtu.imm.fastmoney.BankService;
import dk.dtu.imm.fastmoney.CreditCardFaultMessage;
import javax.jws.WebService;
import javax.xml.datatype.DatatypeConfigurationException;
import ws.niceview.*;
import java.util.ArrayList;
import javax.xml.datatype.XMLGregorianCalendar;
import dk.dtu.imm.fastmoney.types.*;
import dk.dtu.imm.fastmoney.BankPortType;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

/**
 *
 * @author s093277
 */
@WebService(serviceName = "NiceViewWSDLService", portName = "NiceViewWSDLPort", endpointInterface = "ws.niceview.NiceViewWSDLPortType", targetNamespace = "http:/niceview.ws", wsdlLocation = "WEB-INF/wsdl/NiceView/NiceViewWSDLWrapper.wsdl")
public class NiceView {

    private BankService bankService = new BankService();
    private ArrayList<HotelType> hotels;
    private ArrayList<BookingType> bookings;
    private int bookingNumber = 1;
    private int group = 8;
    private String bookError = "Booking not succeeded.";
    private String cancelError = "Booking could not be cancelled.";

    private DatatypeFactory df;

    /**
     * Creates some hotels used for tests.
     * @throws DatatypeConfigurationException
     */
    private void createHotels() throws DatatypeConfigurationException {

        df = DatatypeFactory.newInstance();

        hotels = new ArrayList<HotelType>();
        bookings = new ArrayList<BookingType>();


        HotelType hotel1 = new HotelType();

        hotel1.setCreditCardGuarantee(true);
        hotel1.setName("Hilton");
        hotel1.setPricePrNight(100);
        hotel1.setReservationService("HotelService");

        AddressType addr1 = new AddressType();
        addr1.setCity("New York");
        addr1.setCountry("USA");
        addr1.setPostalNumber("4470");
        addr1.setStreet("Broadway");
        addr1.setStreetNr("16");

        hotel1.setAddress(addr1);

        HotelType hotel2 = new HotelType();

        hotel2.setCreditCardGuarantee(false);
        hotel2.setName("SAS");
        hotel2.setPricePrNight(50);
        hotel2.setReservationService("HotelService");

        AddressType addr2 = new AddressType();
        addr2.setCity("KBH");
        addr2.setCountry("Denmark");
        addr2.setPostalNumber("1100");
        addr2.setStreet("Rådhuspladsen");
        addr2.setStreetNr("11");

        hotel2.setAddress(addr2);

        hotels.add(hotel1);
        hotels.add(hotel2);
    }

    public NiceView() throws DatatypeConfigurationException {
        createHotels();
    }

    /**
     * Books a hotel reservation
     * @param bookingNumber Booking number specifying the booking.
     * @param creditInfo Credit Card Information for the costumer.
     * @return A boolean telling if the booking was successfull.
     * @throws BookingFault If the booking fails.
     */
    public boolean bookHotel(int bookingNumber, CreditCardInfoType creditInfo) throws BookingFault {

        BankPortType port = bankService.getBankPort();

        for (BookingType book : bookings) {
            if (bookingNumber == book.getBookingNumber()) {

                if (book.isBooked()) {
                    throw new BookingFault(bookError, "Booking has already been performed");
                }

                if (book.getHotel().isCreditCardGuarantee()) {
                    int amount = (int) book.getPrice();
                    boolean result;
                    try {
                        result = port.validateCreditCard(group, creditInfo, amount);
                    } catch (CreditCardFaultMessage ex) {
                        throw new BookingFault(bookError, "Could not validate credit card.");
                    }
                    if (result) {
                        book.setBooked(true);
                        return true;
                    } else {
                        throw new BookingFault(bookError, "CreditCardInformation could not be validated.");
                    }
                } else {
                    book.setBooked(true);
                    return true;
                }
            }
        }

        throw new BookingFault(bookError, "BookingNumber could not be found.");
    }

    /**
     * Cancels the booking of a hotel.
     * @param bookingNumber The booking number of the booking.
     * @return a boolean telling if the cancellation was successfull.
     * @throws CancelingFault If the cancelling could not be successfull
     */
    public boolean cancelHotel(int bookingNumber) throws CancelingFault {
        for (BookingType book : bookings) {
            if (book.getBookingNumber() == bookingNumber) {
                if (book.isBooked()) {
                    book.setBooked(false);
                    return true; 
                } else {
                    throw new CancelingFault(cancelError, "Booking already cancelled");
                }
            }
        }

        throw new CancelingFault(cancelError, "BookingNumber not found.");
    }

    /**
     * Finds a list of hotels in a certain city.
     * @param city The City to be visited.
     * @param arrival The arrival date of the visit.
     * @param departure The departure date of the visit.
     * @return A list of ReturnType, each holding the hotel plus the price for the stay, and also a booking number in order to book at the hotel.
     */
    public ReturnHotelList getHotels(java.lang.String city, XMLGregorianCalendar arrival, XMLGregorianCalendar departure) {
        if (city.equals("test")) {
            try { // For testing purposes
                Thread.sleep(10000);
            } catch (Exception e) {
            }
        }
        ReturnHotelList res = new ReturnHotelList();

        for (HotelType hotel : hotels) {
            if (hotel.getAddress().getCity().equals(city)) {
                BookingType book = new BookingType();
                book.setHotel(hotel);
                int days = calcDayDiff(departure, arrival);
                book.setPrice(days * hotel.getPricePrNight());
                book.setBookingNumber(bookingNumber++);
                book.setStartDate(arrival);
                book.setEndDate(departure);
                book.setBooked(false);

                bookings.add(book);
                res.getHotels().add(book);
            }
        }
        return res;
    }

    /**
     * Calculates the difference of two dates in days.
     * @param lastDay The last day of the visit.
     * @param firstDay The first day of the visit.
     * @return The difference in days from firstDay till lastDay.
     */
    private int calcDayDiff(XMLGregorianCalendar lastDay, XMLGregorianCalendar firstDay) {
        int diff = 0;
        while(lastDay.compare(firstDay) == DatatypeConstants.GREATER) {
            Duration du = df.newDurationDayTime(true, 1, 0, 0, 0);
            firstDay.add(du);
            diff++;
        }

        return diff;
    }
}
