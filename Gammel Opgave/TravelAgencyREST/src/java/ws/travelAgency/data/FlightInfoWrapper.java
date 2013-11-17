package ws.travelAgency.data;

import javax.xml.bind.annotation.XmlElement;
import ws.lameduck.FlightInformation;

/**
 *
 * @author Andreas
 */
public class FlightInfoWrapper extends Wrapper{

    private FlightInformation flightInformation;
    
    public FlightInfoWrapper() {
        
    }

    public void setFlightInformation(FlightInformation flightInformation) {
        this.flightInformation = flightInformation;
    }

    @XmlElement
    public FlightInformation getFlightInformation() {
        return flightInformation; 
    }
}
