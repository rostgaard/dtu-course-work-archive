
package service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the service package. 
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

    private final static QName _AppList_QNAME = new QName("", "appList");
    private final static QName _App_QNAME = new QName("", "app");
    private final static QName _Event_QNAME = new QName("", "event");
    private final static QName _Device_QNAME = new QName("", "device");
    private final static QName _RuleString_QNAME = new QName("", "ruleString");
    private final static QName _Role_QNAME = new QName("", "role");
    private final static QName _Policy_QNAME = new QName("", "policy");
    private final static QName _EventType_QNAME = new QName("", "eventType");
    private final static QName _User_QNAME = new QName("", "user");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AppList }
     * 
     */
    public AppList createAppList() {
        return new AppList();
    }

    /**
     * Create an instance of {@link RuleString }
     * 
     */
    public RuleString createRuleString() {
        return new RuleString();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link App }
     * 
     */
    public App createApp() {
        return new App();
    }

    /**
     * Create an instance of {@link Policy }
     * 
     */
    public Policy createPolicy() {
        return new Policy();
    }

    /**
     * Create an instance of {@link Device }
     * 
     */
    public Device createDevice() {
        return new Device();
    }

    /**
     * Create an instance of {@link Event }
     * 
     */
    public Event createEvent() {
        return new Event();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AppList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "appList")
    public JAXBElement<AppList> createAppList(AppList value) {
        return new JAXBElement<AppList>(_AppList_QNAME, AppList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link App }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "app")
    public JAXBElement<App> createApp(App value) {
        return new JAXBElement<App>(_App_QNAME, App.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Device }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "device")
    public JAXBElement<Device> createDevice(Device value) {
        return new JAXBElement<Device>(_Device_QNAME, Device.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RuleString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ruleString")
    public JAXBElement<RuleString> createRuleString(RuleString value) {
        return new JAXBElement<RuleString>(_RuleString_QNAME, RuleString.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Role }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "role")
    public JAXBElement<Role> createRole(Role value) {
        return new JAXBElement<Role>(_Role_QNAME, Role.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Policy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "policy")
    public JAXBElement<Policy> createPolicy(Policy value) {
        return new JAXBElement<Policy>(_Policy_QNAME, Policy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EventType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "eventType")
    public JAXBElement<EventType> createEventType(EventType value) {
        return new JAXBElement<EventType>(_EventType_QNAME, EventType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "user")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

}
