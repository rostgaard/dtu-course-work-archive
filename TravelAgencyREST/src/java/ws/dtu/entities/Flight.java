package ws.dtu.entities;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author peter
 */
public class Flight {
   
    @XmlElement(required = true)
    protected String destinationAirport;
    @XmlElement(required = true)
    protected String originAirport;
 
}
