/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.resources.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import ws.dtu.resources.utils.Sequencer;
import ws.dtu.lameduck.FlightList;

/**
 *
 * @author krc
 */
@XmlRootElement()
public class Itinerary {

    public final int ID                = Sequencer.getNext();
    public ItineraryState currentState = ItineraryState.UNBOOKED;
    
    FlightList flights = new FlightList();
    HotelBookingList  hotels  = new HotelBookingList();

    /**
     * 
     */
    public Itinerary () {
        // Register the itinerary in the database.
        this.registerItinerary();
    }
    
    private void registerItinerary() {
        ItineraryDatabase.insert(this);
    }
    
    public int getID() {
        return this.ID;
    }
    
    public void book() {
        // Do magic synchronization stuff.
    }
    
    public void cancelBooking() {
        // Do magic synchronization stuff.
    }
    
    public void changeState(ItineraryState newState) {
        // Implement state machine.
    }
    
    @XmlElement
    public FlightList getFlights() {
        return flights;
    }
    
    @XmlElement
    public HotelBookingList getHotels() {
        return hotels;
    }
    
    
}
