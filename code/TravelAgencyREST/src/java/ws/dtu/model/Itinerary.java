/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.model;

import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author krc
 */
@XmlRootElement()
public class Itinerary {

    public enum ItinerayState {
        PLANNING, BOOKED;
    }
    
    private int ID;
    
    private FlightBookingList flightBookings;
    private HotelBookingList  hotelBookings;
    private int customerID;
    private ItinerayState state = ItinerayState.PLANNING;

    public Itinerary() {
        this.flightBookings = new FlightBookingList();
        this.hotelBookings  = new HotelBookingList();
    }

    public Itinerary (int ID) {
        this.ID = ID;
        this.flightBookings = new FlightBookingList();
        this.hotelBookings  = new HotelBookingList();
    }
    
    @XmlElement
    public int getID() {
        return this.ID;
    }
    
    @XmlElement
    public FlightBookingList getFlightBookings() {
        return flightBookings;
    }
    
    @XmlElement
    public HotelBookingList getHotelBookings() {
        return hotelBookings;
    }
    

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setFlightBookings(FlightBookingList flights) {
        this.flightBookings = flights;
    }

    public void setHotelBookings(HotelBookingList hotels) {
        this.hotelBookings = hotels;
    }
    
    public void addFlightBooking(FlightBooking flightBooking) {
        flightBookings.add(flightBooking);        
    } 
    
    public void addHotelBooking(HotelBooking hotelBooking) {
        hotelBookings.add(hotelBooking);        
    } 

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public ItinerayState getState() {
        return state;
    }

    public void setState(ItinerayState state) {
        this.state = state;
    }
    
    private Boolean canCancelFlights() {
        try {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
            
            XMLGregorianCalendar now = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
        
            for (FlightBooking fb : flightBookings.getFlights()) {
                XMLGregorianCalendar date = fb.getFlightInformation().getFlight().getLiftOff();
                if (now.toGregorianCalendar().compareTo(date.toGregorianCalendar())>=0) {
                    return false;
                }
            }
            
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(Itinerary.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
    
    public Boolean canCancel() {
        if (this.state==ItinerayState.PLANNING || !canCancelFlights()) {
            return false;
        }else {
            return true;
        }
    }
    
}
