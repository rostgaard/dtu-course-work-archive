package enums;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum EventType {
	ACCELEROMETER, PLAYSOUND, FLASHLIGHT, STARTVIDEORECORDING, STOPVIDEORECORDING, USERALERT;
}
