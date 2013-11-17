package ws.travelAgency.representation;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import ws.niceview.BookingType;

/**
 *
 * @author Andreas
 */
@XmlRootElement
public class HotelRepresentation extends Representation {

    @XmlElement(name="Hotel")
    ArrayList<BookingType> hotels = new ArrayList<BookingType>();

    public void addHotelBooking(BookingType hotelBooking) {
        hotels.add(hotelBooking);
    }


    public ArrayList<BookingType> getHotelBooking() {
        return hotels;
    }
}
