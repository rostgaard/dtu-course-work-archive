/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.travelagency.representation;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import ws.travelagency.data.Itinerary; 

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
