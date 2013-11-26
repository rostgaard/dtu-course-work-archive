/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
    
    private FlightBookingList flightBookings = new FlightBookingList();
    private HotelBookingList  hotelBookings  = new HotelBookingList();

    public Itinerary() {
        
    }

    public Itinerary (int ID) {
        this.ID = ID;
    }
    
    @XmlElement
    public int getID() {
        return this.ID;
    }
    
    
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
}
