package ws.travelAgency.representation;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import ws.travelAgency.data.Itinerary;

/**
 *
 * @author Andreas
 */
@XmlRootElement
public class ItineraryRepresentation extends Representation {

    Itinerary itinerary;
   
    @XmlElement
    public Itinerary getItinerary() {
        return itinerary;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary; 
    }

    

}
