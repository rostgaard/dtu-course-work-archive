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
public class Status {

    public enum StatusCode {
        confirmed,
        unconfirmed,
        cancelled
    }

    private StatusCode statusCode;

    public Status() {
        // Any flight or hotel is always unconfirmed to begin with
      
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    @XmlElement
    public StatusCode getStatusCode() {
        return statusCode;
    }
}
