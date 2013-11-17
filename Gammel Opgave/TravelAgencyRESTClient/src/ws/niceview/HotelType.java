
package ws.niceview;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for hotelType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="hotelType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="address" type="{http:/niceview.ws}addressType"/>
 *         &lt;element name="pricePrNight" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="creditCardGuarantee" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="reservationService" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hotelType", propOrder = {
    "name",
    "address",
    "pricePrNight",
    "creditCardGuarantee",
    "reservationService"
})
public class HotelType {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected AddressType address;
    protected double pricePrNight;
    protected boolean creditCardGuarantee;
    @XmlElement(required = true)
    protected String reservationService;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link AddressType }
     *     
     */
    public AddressType getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressType }
     *     
     */
    public void setAddress(AddressType value) {
        this.address = value;
    }

    /**
     * Gets the value of the pricePrNight property.
     * 
     */
    public double getPricePrNight() {
        return pricePrNight;
    }

    /**
     * Sets the value of the pricePrNight property.
     * 
     */
    public void setPricePrNight(double value) {
        this.pricePrNight = value;
    }

    /**
     * Gets the value of the creditCardGuarantee property.
     * 
     */
    public boolean isCreditCardGuarantee() {
        return creditCardGuarantee;
    }

    /**
     * Sets the value of the creditCardGuarantee property.
     * 
     */
    public void setCreditCardGuarantee(boolean value) {
        this.creditCardGuarantee = value;
    }

    /**
     * Gets the value of the reservationService property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReservationService() {
        return reservationService;
    }

    /**
     * Sets the value of the reservationService property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReservationService(String value) {
        this.reservationService = value;
    }

}
