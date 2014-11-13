package dto.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum EventType {
	ACCELEROMETER, HUMIDITY, PLAY_SOUND, FLASH_LIGHT, TEMPERATURE;
}
