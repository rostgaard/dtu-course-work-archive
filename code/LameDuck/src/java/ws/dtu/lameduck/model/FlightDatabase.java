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
    
    private  void loadDatabase() throws DatatypeConfigurationException {
        DatatypeFactory df = df = DatatypeFactory.newInstance();
        
        XMLGregorianCalendar date1 = df.newXMLGregorianCalendar("2013-11-17");
        XMLGregorianCalendar date2 = df.newXMLGregorianCalendar("2013-11-18");
        
        
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
    

    

    private  FlightInformation generateFlightInformation(Flight newFlight, String bookingNo, double price, String serviceName) {
        FlightInformation flightInfo = new FlightInformation();
        flightInfo.setBookingNo(bookingNo);
        flightInfo.setFlight(newFlight);
        flightInfo.setPrice(price);
        flightInfo.setReservationService(serviceName);
        return flightInfo;
        
    }

    private  boolean compareDate(XMLGregorianCalendar liftOff, XMLGregorianCalendar date) {
        return liftOff.getDay() == date.getDay() &&
                liftOff.getMonth() == date.getMonth() &&
                liftOff.getYear() == date.getYear();
    }

    public  FlightInformation bookFlight(String bookingNo) throws BookFlightFault {
        if(!flightInfoByBooking.containsKey(bookingNo)){
            throw new BookFlightFault("No such booking number", "No such booking number");
        }
        else if(bookings.contains(bookingNo)){
            throw new BookFlightFault("Trip has already been booked", "Trip has already been booked");
        } else {
                bookings.add(bookingNo); 
                return flightInfoByBooking.get(bookingNo);
        }
            
        
    }
    
    public  void cancelFlight(String bookingNo) {
        
           bookings.remove(bookingNo);   
        
    }

}
