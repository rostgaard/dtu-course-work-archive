/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author krc
 */
@XmlRootElement()
public class Itinerary {

    private int ID;
    private ItineraryState currentState = ItineraryState.UNBOOKED;
    
    private FlightBookingList flightBookings;
    private HotelBookingList  hotelBookings;
    private int customerID;

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
    
    @XmlElement
    public ItineraryState getCurrentState() {
        return currentState;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setCurrentState(ItineraryState currentState) {
        this.currentState = currentState;
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

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    
    
}
