package enums;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum EventType {
	ACCELEROMETER, HUMIDITY, PLAY_SOUND, FLASH_LIGHT, TEMPERATURE, START_VIDEO_RECORDING, STOP_VIDEO_RECORDING, USER_ALERT;
}
