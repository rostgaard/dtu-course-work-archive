
package ws.niceview;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws.niceview package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _BookingFault_QNAME = new QName("http:/niceview.ws", "bookingFault");
    private final static QName _CancelingFault_QNAME = new QName("http:/niceview.ws", "cancelingFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.niceview
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddressType }
     * 
     */
    public AddressType createAddressType() {
        return new AddressType();
    }

    /**
     * Create an instance of {@link BookingType }
     * 
     */
    public BookingType createBookingType() {
        return new BookingType();
    }

    /**
     * Create an instance of {@link HotelType }
     * 
     */
    public HotelType createHotelType() {
        return new HotelType();
    }

    /**
     * Create an instance of {@link ReturnHotelList }
     * 
     */
    public ReturnHotelList createReturnHotelList() {
        return new ReturnHotelList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http:/niceview.ws", name = "bookingFault")
    public JAXBElement<String> createBookingFault(String value) {
        return new JAXBElement<String>(_BookingFault_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http:/niceview.ws", name = "cancelingFault")
    public JAXBElement<String> createCancelingFault(String value) {
        return new JAXBElement<String>(_CancelingFault_QNAME, String.class, null, value);
    }

}
