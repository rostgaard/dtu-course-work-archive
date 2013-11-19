/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.lameduck.model;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.datatype.XMLGregorianCalendar;
import ws.dtu.lameduck.Flight;
import ws.dtu.lameduck.FlightInformation;
import ws.dtu.lameduck.FlightList;
import ws.dtu.lameduck.model.exceptions.NoSuchFlightIdentifier;

/**
 *
 * @author krc
 */
public final class FlightDatabase {
    
    private static Map<String,List<FlightInformation>> db = new HashMap<String,List<FlightInformation>>();
    private static List<String> bookings = new ArrayList<String>();
    private static Map<String, FlightInformation> flightInfoByBooking = new HashMap<String, FlightInformation>();
    private static String serviceName = "LameDuck";
    public static void loadDatabase() {
        XMLGregorianCalendar date1 = new XMLGregorianCalendarImpl();
        date1.setMonth(11);
        date1.setDay(17);
        date1.setYear(2013);
        
        XMLGregorianCalendar date2 = new XMLGregorianCalendarImpl();
        date2.setMonth(11);
        date2.setDay(18);
        date2.setYear(2013);
        
        Flight newFlight = generateFlight("Kastrup", "Kabul", date1, date2, "SAS");
        insert(generateFlightInformation(newFlight,"b0001", 200.0, serviceName ));
        
        newFlight = generateFlight("Kabul", "Kastrup", date1, date2, "SAS");
        insert(generateFlightInformation(newFlight, "b0002", 250.0, serviceName));
        
          newFlight = generateFlight("Kabul", "Kastrup", date1, date2, "SAS");
        insert(generateFlightInformation(newFlight, "b0008", 250.0, serviceName));
        
        newFlight = generateFlight("Kastrup", "Moscow", date1, date2, "SAS");
        insert(generateFlightInformation(newFlight, "b0003", 300.0, serviceName));
        
        newFlight = generateFlight("Kastrup", "Afganistan", date1, date2, "SAS");
        insert(generateFlightInformation(newFlight, "b0004", 200.0, serviceName));
        
        newFlight = generateFlight("Kastrup", "Irak", date1, date2, "SAS");
        insert(generateFlightInformation(newFlight, "b0005", 220.0, serviceName));
        
        newFlight = generateFlight("Kastrup", "Libya", date1, date2, "SAS");
        insert(generateFlightInformation(newFlight, "b0006", 150.0, serviceName));
        
        newFlight = generateFlight("Kastrup", "Kazakhstan", date1, date2, "SAS");
        insert(generateFlightInformation(newFlight, "b0007", 200.0, serviceName));
        
        
        
    }
    
    private static Flight generateFlight(String origin, String destination, XMLGregorianCalendar liftOff, XMLGregorianCalendar arrival, String carrier){
        Flight newFlight = new Flight();
        newFlight.setOrigin(origin);
        newFlight.setDestination(destination);
        newFlight.setLiftOff(liftOff);
        newFlight.setArrival(arrival);
        newFlight.setCarrier(carrier);
        return newFlight;
    }
    public static void insert (FlightInformation flightInfo) {
        String key = flightInfo.getFlight().getOrigin();
        if(db.containsKey(key)){
            db.get(key).add(flightInfo);
            flightInfoByBooking.put(flightInfo.getBookingNumber(), flightInfo);
        } else {
            List<FlightInformation> newList = new ArrayList<FlightInformation>();
            newList.add(flightInfo);
            db.put(key, newList);
            flightInfoByBooking.put(flightInfo.getBookingNumber(), flightInfo);
        }
    }
    
    public static FlightList getFlights(String origin, String destination, XMLGregorianCalendar date) {
        FlightList retList = new FlightList();
        
        List<FlightInformation> properOrigin = db.get(origin);
        for(FlightInformation fi : properOrigin){
            Flight flight = fi.getFlight();
            if(flight.getDestination().equals(destination) && 
                    compareDate(flight.getLiftOff(),date) &&
                    !bookings.contains(fi.getBookingNumber())){

                retList.getFlights().add(fi);
                                
            }
        }
       
        return retList;
    }
    

    

    private static FlightInformation generateFlightInformation(Flight newFlight, String bookingNumber, double price, String serviceName) {
        FlightInformation flightInfo = new FlightInformation();
        flightInfo.setBookingNumber(bookingNumber);
        flightInfo.setFlight(newFlight);
        flightInfo.setPrice(price);
        flightInfo.setReservationService(serviceName);
        return flightInfo;
        
    }

    private static boolean compareDate(XMLGregorianCalendar liftOff, XMLGregorianCalendar date) {
        return liftOff.getDay() == date.getDay() &&
                liftOff.getMonth() == date.getMonth() &&
                liftOff.getYear() == date.getYear();
    }

    public static boolean bookFlight(String bookingNumber, CreditCardInfoType creditCardInfo) {
        // TODO: fail properly
        
        if(bookings.contains(bookingNumber)){
            return false;
        } else {
            
            boolean result = pay(creditCardInfo, flightInfoByBooking.get(bookingNumber).getPrice());
            
            if(result){
                bookings.add(bookingNumber);     
            }
            return result;
        }
    }
    
    

    public static boolean cancelFlight(String bookingNumber, double price, CreditCardInfoType creditCardInfo) {
        // TODO: Fail properly
        bookings.remove(bookingNumber);
        return refund(creditCardInfo, price);
    }

    private static boolean refund(CreditCardInfoType creditCardInfo, double price) {
        // TODO: use bank service
        return true;
    }
    private static boolean pay(CreditCardInfoType creditCardInfo, double price){
        // TODO: use bank service
        return true;
    }
}
