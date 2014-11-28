package enums;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum EventType {
	ACCELEROMETER, HUMIDITY, PLAYSOUND, FLASHLIGHT, TEMPERATURE, STARTVIDEORECORDING, STOPVIDEORECORDING, USERALERT;
}
