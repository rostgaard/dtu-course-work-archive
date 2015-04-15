
package service;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for eventType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="eventType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ACCELEROMETER"/>
 *     &lt;enumeration value="HUMIDITY"/>
 *     &lt;enumeration value="PLAYSOUND"/>
 *     &lt;enumeration value="FLASHLIGHT"/>
 *     &lt;enumeration value="TEMPERATURE"/>
 *     &lt;enumeration value="STARTVIDEORECORDING"/>
 *     &lt;enumeration value="STOPVIDEORECORDING"/>
 *     &lt;enumeration value="USERALERT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "eventType")
@XmlEnum
public enum EventType {

    ACCELEROMETER,
    HUMIDITY,
    PLAYSOUND,
    FLASHLIGHT,
    TEMPERATURE,
    STARTVIDEORECORDING,
    STOPVIDEORECORDING,
    USERALERT;

    public String value() {
        return name();
    }

    public static EventType fromValue(String v) {
        return valueOf(v);
    }

}
