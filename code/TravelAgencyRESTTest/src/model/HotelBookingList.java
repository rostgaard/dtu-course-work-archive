/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import ws.dtu.niceview.types.HotelInformation;
import ws.dtu.niceview.types.HotelList;

/**
 *
 * @author peter
 */
@XmlRootElement
public class HotelBookingList {
    
    private List<HotelBooking> hotels;
    
    public HotelBookingList() {
        hotels = new ArrayList<HotelBooking>();
    }
    
    public HotelBookingList(HotelList hotelList) {
        hotels = new ArrayList<HotelBooking>();
        for(HotelInformation hotel : hotelList.getHotels()) {
            HotelBooking hotelBooking = new HotelBooking(hotel);
            this.hotels.add(hotelBooking);
        }
    }
    
    public void add(HotelBooking booking) {
        hotels.add(booking);
    }

    @XmlElement
    public List<HotelBooking> getHotels() {
        return hotels;
    }

    public void setFlights(List<HotelBooking> hotels) {
        this.hotels = hotels;
    }
    
}
