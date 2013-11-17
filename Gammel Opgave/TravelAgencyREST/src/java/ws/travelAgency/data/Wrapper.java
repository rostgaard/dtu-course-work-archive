package ws.travelAgency.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andreas
 */
@XmlRootElement
public class Wrapper {

    private Status status = new Status();

    public void setStatus(Status status) {
        this.status = status; 
    }

    @XmlElement
    public Status getStatus() {
        return status;
    }


}
