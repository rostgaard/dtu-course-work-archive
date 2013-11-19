
package ws.dtu.lameduck;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for flightList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="flightList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="newElement" type="{http://lameduck.dtu.ws}flightInformation" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "flightList", propOrder = {
    "newElement"
})
public class FlightList {

    protected List<FlightInformation> newElement;

    /**
     * Gets the value of the newElement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the newElement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNewElement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FlightInformation }
     * 
     * 
     */
    public List<FlightInformation> getNewElement() {
        if (newElement == null) {
            newElement = new ArrayList<FlightInformation>();
        }
        return this.newElement;
    }

}
