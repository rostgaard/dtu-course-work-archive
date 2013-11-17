/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.travelagency.data;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ws.niceview.BookingType;
import ws.niceview.HotelType;

/**
 *
 * @author Andreas
 */
public class HotelWrapper extends Wrapper {

    private BookingType hotel;
    
    public HotelWrapper() {
        
    }

    
    public void setHotel(BookingType hotel) {
        this.hotel = hotel; 
    }

    public BookingType getHotel() {
        return hotel; 
    }
}
