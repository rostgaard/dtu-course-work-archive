/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.lameduck.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import ws.dtu.lameduck.BookFlightFault;
import ws.dtu.lameduck.types.Flight;
import ws.dtu.lameduck.types.FlightInformation;
import ws.dtu.lameduck.types.FlightList;

/**
 *
 * @author krc
 */
public final class FlightDatabase {
    
    private  Map<String,List<FlightInformation>> flightInformations = new HashMap<String,List<FlightInformation>>();
    private  List<String> bookings = new ArrayList<String>();
    private  Map<String, FlightInformation> flightInfoByBooking = new HashMap<String, FlightInformation>();
    private  String serviceName = "LameDuck";
    
    private static FlightDatabase db;
    
    public static synchronized FlightDatabase getInstance() throws DatatypeConfigurationException {
        if(db == null){
            db = new FlightDatabase();
            db.loadDatabase();
        }
        
        return db;
    }
    
    private FlightDatabase(){
        flightInformations = new HashMap<String,List<FlightInformation>>();
        bookings = new ArrayList<String>();
        flightInfoByBooking = new HashMap<String, FlightInformation>();
    }
    
    public void reset() throws DatatypeConfigurationException {
        flightInformations = new HashMap<String,List<FlightInformation>>();
        bookings = new ArrayList<String>();
        flightInfoByBooking = new HashMap<String, FlightInformation>();
        
        this.loadDatabase();
    }
    
    private  void loadDatabase() {
        
        DatatypeFactory df;
        try {
            df = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException ex) {
           throw new NullPointerException();
        }
        
        XMLGregorianCalendar date1 = df.newXMLGregorianCalendar("2013-11-17");
        
        XMLGregorianCalendar date2 = df.newXMLGregorianCalendar("2013-11-18");
        
        
        
        Flight newFlight = generateFlight("Kastrup", "Kabul", date1, date2, "SAS");
        insert(generateFlightInformation(newFlight,"SAS0001", 2, serviceName ));
        
        newFlight = generateFlight("Kastrup", "Kabul", date1, date2, "Norwegian");
        insert(generateFlightInformation(newFlight, "NOR0001", 2, serviceName));
        
        newFlight = generateFlight("Kastrup", "Kabul", date1, date2, "Kabul Air");
        insert(generateFlightInformation(newFlight, "NOR0002", 2, serviceName));
        
        newFlight = generateFlight("Kabul", "Kastrup", date1, date2, "SAS");
        insert(generateFlightInformation(newFlight, "SAS0002", 250, serviceName));
        
          newFlight = generateFlight("Kabul", "Kastrup", date1, date2, "SAS");
        insert(generateFlightInformation(newFlight, "SAS0003", 250, serviceName));
        
        newFlight = generateFlight("Kastrup", "Moscow", date1, date2, "SAS");
        insert(generateFlightInformation(newFlight, "SAS0004", 300, serviceName));
        
        newFlight = generateFlight("Kastrup", "Afganistan", date1, date2, "SAS");
        insert(generateFlightInformation(newFlight, "SAS0005", 200, serviceName));
        
        newFlight = generateFlight("Kastrup", "Irak", date1, date2, "SAS");
        insert(generateFlightInformation(newFlight, "SAS0006", 220, serviceName));
        
        newFlight = generateFlight("Kastrup", "Libya", date1, date2, "SAS");
        insert(generateFlightInformation(newFlight, "SAS0007", 150, serviceName));
        
        newFlight = generateFlight("Kastrup", "Kazakhstan", date1, date2, "SAS");
        insert(generateFlightInformation(newFlight, "SAS0008", 200, serviceName));
        
        newFlight = generateFlight("Kastrup", "Kazakhstan", date1, date2, "Fail Airlines");
        insert(generateFlightInformation(newFlight, "FAIL0001", 200.0, serviceName));
        
    }
    
    private  Flight generateFlight(String origin, String destination, XMLGregorianCalendar liftOff, XMLGregorianCalendar arrival, String carrier){
        
        Flight newFlight = new Flight();
        newFlight.setOrigin(origin);
        newFlight.setDestination(destination);
        newFlight.setLiftOff(liftOff);
        newFlight.setArrival(arrival);
        newFlight.setCarrier(carrier);
        return newFlight;
    }
    public  void insert (FlightInformation flightInfo) {
        String key = flightInfo.getFlight().getOrigin();
        if(flightInformations.containsKey(key)){
            flightInformations.get(key).add(flightInfo);
            flightInfoByBooking.put(flightInfo.getBookingNo(), flightInfo);
        } else {
            List<FlightInformation> newList = new ArrayList<FlightInformation>();
            newList.add(flightInfo);
            flightInformations.put(key, newList);
            flightInfoByBooking.put(flightInfo.getBookingNo(), flightInfo);
        }
    }
    
    public  FlightList getFlights(String origin, String destination, XMLGregorianCalendar date) {
        FlightList retList = new FlightList();
        
        List<FlightInformation> properOrigin = flightInformations.get(origin);
        for(FlightInformation fi : properOrigin){
            Flight flight = fi.getFlight();
            if(flight.getDestination().equals(destination) && 
                    compareDate(flight.getLiftOff(),date) &&
                    !bookings.contains(fi.getBookingNo())){

                retList.getFlights().add(fi);
                                
            }
        }
       
        return retList;
    }

    private  FlightInformation generateFlightInformation(Flight newFlight, String bookingNo, int price, String serviceName) {
        FlightInformation flightInfo = new FlightInformation();
        flightInfo.setBookingNo(bookingNo);
        flightInfo.setFlight(newFlight);
        flightInfo.setPrice(price);
        flightInfo.setReservationSevice(serviceName);
        
        return flightInfo;
        
    }

    private  boolean compareDate(XMLGregorianCalendar liftOff, XMLGregorianCalendar date) {
        return liftOff.getDay() == date.getDay() &&
                liftOff.getMonth() == date.getMonth() &&
                liftOff.getYear() == date.getYear();
    }

    public  FlightInformation bookFlight(String bookingNo) throws BookFlightFault {
        if(!flightInfoByBooking.containsKey(bookingNo)){
            throw new BookFlightFault("No such booking number: "+bookingNo, "No such booking number: "+bookingNo);
        }
        else if(bookings.contains(bookingNo)){
            throw new BookFlightFault("Trip has already been booked", "Trip has already been booked");
        } else if (bookingNo.equals("FAIL0001")) {
            throw new BookFlightFault("Airline is to unstable to fly", "Please try another airline");
        } else {
                bookings.add(bookingNo); 
                return flightInfoByBooking.get(bookingNo);
        }
            
        
    }
    
    public  void cancelFlight(String bookingNo) {
        
           bookings.remove(bookingNo);   
        
    }

}
