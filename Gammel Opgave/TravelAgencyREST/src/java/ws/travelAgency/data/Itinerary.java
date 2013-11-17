package ws.travelAgency.data;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.DatatypeConstants;

/**
 *
 * @author Andreas
 */
@XmlRootElement()
public class Itinerary {

    private int id;
    private List<FlightInfoWrapper> flights = new ArrayList<FlightInfoWrapper>();
    private List<HotelWrapper> hotels = new ArrayList<HotelWrapper>();
    private XMLGregorianCalendar startDate;
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
        setDate(flight.getFlightInformation().getFlight().getLiftOff());
        flights.add(flight);
    }

    @XmlElement
    public List<HotelWrapper> getHotels() {
        return hotels;
    }

    public void addHotel(HotelWrapper hotel) {
        setDate(hotel.getHotel().getStartDate());
        hotels.add(hotel);
    }

    public void setItineraryStatus(ItineraryStatus status) {
        this.status = status;
    }

    public ItineraryStatus getStatus() {
        return status;
    }

    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    private void setDate(XMLGregorianCalendar date) {
        if (startDate == null) {
            startDate = date;
        } else {
            if (date.compare(startDate) == DatatypeConstants.LESSER) {
                startDate = date;
            }
        }
    }
}
