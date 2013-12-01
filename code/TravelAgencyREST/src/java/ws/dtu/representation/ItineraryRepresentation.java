/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.representation;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import ws.dtu.model.Itinerary;


@XmlRootElement
public class ItineraryRepresentation extends Representation {
    
    private Itinerary itinerary;
    
    public ItineraryRepresentation(Itinerary itinerary) {
        super();
        this.itinerary = itinerary;
    }

    public ItineraryRepresentation() {
    }
    
    @XmlElement
    public Itinerary getItinerary() {
        return itinerary;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }
}
