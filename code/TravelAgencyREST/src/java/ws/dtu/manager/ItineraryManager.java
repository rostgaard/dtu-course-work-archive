/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.manager;

import java.util.logging.Level;
import java.util.logging.Logger;
import ws.dtu.lameduck.BookFlightFault;
import ws.dtu.lameduck.CancelFlightFault;
import ws.dtu.lameduck.LameDuckPortType;
import ws.dtu.lameduck.LameDuckService;
import ws.dtu.model.Customer;
import ws.dtu.model.FlightBooking;
import ws.dtu.model.HotelBooking;
import ws.dtu.model.Itinerary;
import ws.dtu.model.exceptions;
import ws.dtu.niceview.BookHotelFault;
import ws.dtu.niceview.CancelHotelFault;
import ws.dtu.niceview.NiceViewPortType;
import ws.dtu.niceview.NiceViewService;
import ws.dtu.resources.utils.Sequencer;

/**
 *
 * @author peter
 */
public class ItineraryManager {
    
    private static final LameDuckService lameDuckService = new LameDuckService();
    private static final LameDuckPortType lameDuckPort = lameDuckService.getLameDuckPortTypeBindingPort();   
    
    private static final NiceViewService niceViewService = new NiceViewService();
    private static final NiceViewPortType niceViewPort = niceViewService.getNiceViewPort();  
    
    private static ItineraryManager db;
    
    public static synchronized ItineraryManager getInstance() {
        if(db == null){
            db = new ItineraryManager();
        }
        
        return db;
    }
    
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
                revertBooking(itinerary);
                throw new exceptions.BookingException();
            } 
        }
        
        for(HotelBooking hb : itinerary.getHotelBookings().getHotels()) {
            try {
                niceViewPort.bookHotel(hb.getHotelInformation().getBookingNo(), customer.getCreditcard());
                hb.setBookingState(HotelBooking.HotelBookingState.BOOKED);
            } catch (BookHotelFault ex) {
                
                revertBooking(itinerary);
                throw new exceptions.BookingException();
            }
        }
    }
    
    private void revertBooking(Itinerary itinerary) {
        Customer customer = CustomerDatabase.getInstance().get(itinerary.getCustomerID());

        for(FlightBooking fb : itinerary.getFlightBookings().getFlights()) {
            if(fb.getBookingState()==FlightBooking.FlightBookingState.BOOKED) {
                try {
                    lameDuckPort.cancelFlight(fb.getFlightInformation().getBookingNo(), customer.getCreditcard(), fb.getFlightInformation().getPrice());
                    fb.setBookingState(FlightBooking.FlightBookingState.CANCELLED);
                } catch (CancelFlightFault ex) {
                    throw new exceptions.CancelException();
                }
            }
        }
        
        for(HotelBooking hb : itinerary.getHotelBookings().getHotels()) {
            if (hb.getBookingState()==HotelBooking.HotelBookingState.BOOKED) {
                try {
                    niceViewPort.cancelHotel(hb.getHotelInformation().getBookingNo());
                    hb.setBookingState(HotelBooking.HotelBookingState.CANCELLED);
                } catch (CancelHotelFault ex) {
                    throw new exceptions.CancelException();
                }
            }
        }
    }
    
    public void cancelItinerary(Itinerary itinerary) {
        Customer customer = CustomerDatabase.getInstance().get(itinerary.getCustomerID());
        for(FlightBooking fb : itinerary.getFlightBookings().getFlights()) {
            try {
                lameDuckPort.cancelFlight(fb.getFlightInformation().getBookingNo(), customer.getCreditcard(), fb.getFlightInformation().getPrice());
                fb.setBookingState(FlightBooking.FlightBookingState.CANCELLED);
            } catch (CancelFlightFault ex) {
//                revertBooking(itinerary);
                throw new exceptions.CancelException();
            } 
        }
        
        for(HotelBooking hb : itinerary.getHotelBookings().getHotels()) {
            try {
                niceViewPort.cancelHotel(hb.getHotelInformation().getBookingNo());
                hb.setBookingState(HotelBooking.HotelBookingState.CANCELLED);
            } catch (CancelHotelFault ex) {
//                revertBooking(itinerary);
                throw new exceptions.CancelException();
            }
        }
    }
    
}
