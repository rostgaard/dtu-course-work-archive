/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.manager;

import java.util.logging.Level;
import java.util.logging.Logger;
import ws.dtu.lameduck.BookFlightFault;
import ws.dtu.lameduck.LameDuckPortType;
import ws.dtu.lameduck.LameDuckService;
import ws.dtu.model.Customer;
import ws.dtu.model.FlightBooking;
import ws.dtu.model.Itinerary;
import ws.dtu.resources.utils.Sequencer;

/**
 *
 * @author peter
 */
public class ItineraryManager {
    
    private static final LameDuckService lameDuckService = new LameDuckService();
    private static final LameDuckPortType lameDuckPort = lameDuckService.getLameDuckPort();   
    
    public Itinerary createItinerary(int customerID) {
        Itinerary itinerary = new Itinerary(Sequencer.getNext());
        itinerary.setCustomerID(customerID);
        ItineraryDatabase.getInstance().insert(itinerary,customerID);
        return itinerary;
    }
    
    public void bookItinerary(Itinerary itinerary) {
        Customer customer = CustomerDatabase.getInstance().get(itinerary.getCustomerID());
        for(FlightBooking fb : itinerary.getFlightBookings().getFlights()) {
            try {
                lameDuckPort.bookFlight(fb.getFlightInformation().getBookingNo(), customer.getCreditcard());
                fb.setBookingState(FlightBooking.FlightBookingState.BOOKED);
            } catch (BookFlightFault ex) {
                Logger.getLogger(ItineraryManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void cancelItinerary(Itinerary itinerary) {
       
    }
    
}
