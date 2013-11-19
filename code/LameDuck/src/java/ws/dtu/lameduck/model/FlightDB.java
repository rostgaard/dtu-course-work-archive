/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.dtu.lameduck.model;

import java.util.HashMap;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author krc
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "flightList")
public class FlightDB<T> extends HashMap<String, T> {

    public FlightDB() {
    }
    
}
