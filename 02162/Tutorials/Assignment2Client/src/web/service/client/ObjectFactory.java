
package web.service.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the web.service.client package. 
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

    private final static QName _Event_QNAME = new QName("", "event");
    private final static QName _EventList_QNAME = new QName("", "eventList");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: web.service.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EventList }
     * 
     */
    public EventList createEventList() {
        return new EventList();
    }

    /**
     * Create an instance of {@link Event }
     * 
     */
    public Event createEvent() {
        return new Event();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Event }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "event")
    public JAXBElement<Event> createEvent(Event value) {
        return new JAXBElement<Event>(_Event_QNAME, Event.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EventList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "eventList")
    public JAXBElement<EventList> createEventList(EventList value) {
        return new JAXBElement<EventList>(_EventList_QNAME, EventList.class, null, value);
    }

}
