/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import ws.dtu.niceview.types.HotelInformation;


@XmlRootElement
public class HotelBooking {

    public enum HotelBookingState {
    UNCONFIRMED, CONFIRMED, CANCELLED
    }
    
   public HotelBooking() {
        
    }
    
    public HotelBooking(HotelInformation hotel) {
        this.hotelInformation = hotel;
    }

    
    private HotelInformation hotelInformation;
    private HotelBookingState bookingState = HotelBookingState.UNCONFIRMED;
    
    @XmlElement
    public HotelInformation getHotelInformation() {
        return hotelInformation;
    }
    
    @XmlElement
    public HotelBookingState getBookingState() {
        return bookingState;
    }

    public void setHotelInformation(HotelInformation hotelInformation) {
        this.hotelInformation = hotelInformation;
    }

    public void setBookingState(HotelBookingState bookingState) {
        this.bookingState = bookingState;
    }
    
}
