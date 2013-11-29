/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.representation;

import javax.xml.bind.annotation.XmlElement;
import ws.dtu.model.HotelBookingList;

/**
 *
 * @author peter
 */
public class HotelRepresentation extends Representation {
    
    private HotelBookingList hotelBookings;

    public HotelRepresentation(HotelBookingList hotelBookings) {
        this.hotelBookings = hotelBookings;
    }

    public HotelRepresentation() {
    }

    @XmlElement
    public HotelBookingList getHotelBookings() {
        return hotelBookings;
    }

    public void setHotelBookings(HotelBookingList hotelBookings) {
        this.hotelBookings = hotelBookings;
    }
    
}
