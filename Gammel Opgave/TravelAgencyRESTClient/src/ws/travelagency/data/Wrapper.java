/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.travelagency.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andreas
 */
@XmlRootElement
public class Wrapper {

    protected Status status = new Status();

    public Wrapper() {

    }

    public void setStatus(Status status) {
       this.status = status; 
    }

    @XmlElement
    public Status getStatus() {
        return status;
    }


}
