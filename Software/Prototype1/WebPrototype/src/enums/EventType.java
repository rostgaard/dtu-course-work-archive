package enums;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Everyone
 *
 */
@XmlRootElement
public enum EventType {
	ACCELEROMETER, HUMIDITY, PLAYSOUND, FLASHLIGHT, TEMPERATURE, STARTVIDEORECORDING, STOPVIDEORECORDING, USERALERT;
}
