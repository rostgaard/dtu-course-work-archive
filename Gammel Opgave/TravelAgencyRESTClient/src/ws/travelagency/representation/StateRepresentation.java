/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.travelagency.representation;

import javax.xml.bind.annotation.XmlAttribute;
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
