/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.travelagency.data;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andreas
 */
@XmlRootElement()
public class Itinerary {

   // static int next_id = 0;
    private int id;
    private List<FlightInfoWrapper> flights = new ArrayList<FlightInfoWrapper>();
    private List<HotelWrapper> hotels = new ArrayList<HotelWrapper>(); 
    // TODO: Add hotel when completed

    private ItineraryStatus status = ItineraryStatus.planning;

    // Keeps track of the exact state the itinerary is in
    public enum ItineraryStatus {
        planning,
        booked,
        locked, // One day before departure -> no action can
                // be made upon the itinerary
        failedCancel // When a cancellation failed
    }
    
    @XmlAttribute
    public String getID() {
        return Integer.toString(id);
    }

    public void setID(int id) {
        this.id = id; 
    }

    @XmlElement
    public List<FlightInfoWrapper> getFlights() {
        return flights;
    }

    public void addFlight(FlightInfoWrapper flight) {
        flights.add(flight);
    }

    @XmlElement
    public List<HotelWrapper> getHotels() {
        return hotels;
    }

    public void addHotel(HotelWrapper hotel) {
        hotels.add(hotel);
    }

    public void setItineraryStatus(ItineraryStatus status) {
        this.status = status; 
    }

    public ItineraryStatus getStatus() {
        return status; 
    }
}
