/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.manager;

import ws.dtu.lameduck.BookFlightFault;
import ws.dtu.lameduck.CancelFlightFault;
import ws.dtu.lameduck.LameDuckPortType;
import ws.dtu.lameduck.LameDuckService;
import ws.dtu.model.Customer;
import ws.dtu.model.FlightBooking;
import ws.dtu.model.HotelBooking;
import ws.dtu.model.Itinerary;
import ws.dtu.model.Itinerary.ItinerayState;
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
    private static final LameDuckPortType lameDuckPort = lameDuckService.getLameDuckPort();   
    
    private static final NiceViewService niceViewService = new NiceViewService();
    private static final NiceViewPortType niceViewPort = niceViewService.getNiceViewPort();  
    
    private static ItineraryManager manager;
    
    public static synchronized ItineraryManager getInstance() {
        if(manager == null){
            manager = new ItineraryManager();
        }
        
        return manager;
    }
    
    public Itinerary createItinerary(int customerID) {
        Itinerary itinerary = new Itinerary(Sequencer.getNext());
        itinerary.setState(Itinerary.ItinerayState.PLANNING);
        itinerary.setCustomerID(customerID);
        ItineraryDatabase.getInstance().insert(itinerary,customerID);
        return itinerary;
    }
    
    public void bookItinerary(Itinerary itinerary) {
        if (itinerary.getState()==Itinerary.ItinerayState.BOOKED) {
            throw new exceptions.BookingException();
        }
        
        Customer customer = CustomerDatabase.getInstance().get(itinerary.getCustomerID());
        for(FlightBooking fb : itinerary.getFlightBookings().getFlights()) {
            try {
                lameDuckPort.bookFlight(fb.getFlightInformation().getBookingNo(), customer.getCreditcard());
                fb.setBookingState(FlightBooking.FlightBookingState.CONFIRMED);
            } catch (BookFlightFault ex) {
                revertBooking(itinerary);
                throw new exceptions.BookingException();
            } 
        }
        
        for(HotelBooking hb : itinerary.getHotelBookings().getHotels()) {
            try {
                niceViewPort.bookHotel(hb.getHotelInformation().getBookingNo(), customer.getCreditcard());
                hb.setBookingState(HotelBooking.HotelBookingState.CONFIRMED);
            } catch (BookHotelFault ex) {
                
                revertBooking(itinerary);
                throw new exceptions.BookingException();
            }
        }
        
        itinerary.setState(Itinerary.ItinerayState.BOOKED);
    }
    
    private void revertBooking(Itinerary itinerary) {
        Customer customer = CustomerDatabase.getInstance().get(itinerary.getCustomerID());

        for(FlightBooking fb : itinerary.getFlightBookings().getFlights()) {
            if(fb.getBookingState()==FlightBooking.FlightBookingState.CONFIRMED) {
                try {
                    lameDuckPort.cancelFlight(fb.getFlightInformation().getBookingNo(), fb.getFlightInformation().getPrice(), customer.getCreditcard());
                    fb.setBookingState(FlightBooking.FlightBookingState.CANCELLED);
                } catch (CancelFlightFault ex) {
                    throw new exceptions.CancelException();
                }
            }
        }
        
        for(HotelBooking hb : itinerary.getHotelBookings().getHotels()) {
            if (hb.getBookingState()==HotelBooking.HotelBookingState.CONFIRMED) {
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
        if (itinerary.canCancel()) {
            throw new exceptions.CancelException();    
        }
        
        Boolean throwException = false;
        
        Customer customer = CustomerDatabase.getInstance().get(itinerary.getCustomerID());
        for(FlightBooking fb : itinerary.getFlightBookings().getFlights()) {
            try {
                lameDuckPort.cancelFlight(fb.getFlightInformation().getBookingNo(), fb.getFlightInformation().getPrice()/2, customer.getCreditcard());
                fb.setBookingState(FlightBooking.FlightBookingState.CANCELLED);
            } catch (CancelFlightFault ex) {
                throwException = true;
            } 
        }
        
        for(HotelBooking hb : itinerary.getHotelBookings().getHotels()) {
            try {
                niceViewPort.cancelHotel(hb.getHotelInformation().getBookingNo());
                hb.setBookingState(HotelBooking.HotelBookingState.CANCELLED);
            } catch (CancelHotelFault ex) {
                throwException = true;
            }
        }
        
        if (throwException) {
            itinerary.setState(ItinerayState.PLANNING);
            throw new exceptions.CancelException();
        }
        
        itinerary.setState(ItinerayState.CANCELLED);
    }
    
}
