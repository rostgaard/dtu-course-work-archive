package ws.travelAgency.data;

import ws.niceview.BookingType;

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
