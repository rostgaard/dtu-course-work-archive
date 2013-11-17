/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.resources.model;

import ws.dtu.resources.utils.Sequencer;

/**
 *
 * @author krc
 */
public class Itinerary {

    public final int ID                = Sequencer.getNext();
    public ItineraryState currentState = ItineraryState.UNBOOKED;
    
    FlightList flights = new FlightList();
    HotelList  hotels  = new HotelList();

    /**
     * 
     */
    public Itinerary () {
        // Register the itinerary in the database.
        this.registerItinerary();
    }
    
    private void registerItinerary() {
        IniteraryDatabase.insert(this);
    }
    
    public int getID() {
        return this.ID;
    }
    
    public void cancelBooking() {
        // Do magic synchronization stuff.
    }
    
    public void changeState(ItineraryState newState) {
        // Implement state machine.
    }
    
    
}
