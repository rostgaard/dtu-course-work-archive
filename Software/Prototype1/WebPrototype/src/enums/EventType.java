package enums;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum EventType {
	ACCELEROMETER, HUMIDITY, PLAYSOUND, FLASHLIGHT, TEMPERATURE, START_VIDEO_RECORDING, STOP_VIDEO_RECORDING, USERALERT;
}
