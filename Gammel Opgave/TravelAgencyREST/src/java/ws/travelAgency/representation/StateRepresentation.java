package ws.travelAgency.representation;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andreas
 */
@XmlRootElement
public class StateRepresentation extends Representation {

    String statusInformation;

    @XmlElement
    public void setStatusInformation(String statusInformation) {
        this.statusInformation = statusInformation; 
    }

    public String getStatusInformation() {
        return statusInformation; 
    }

}
