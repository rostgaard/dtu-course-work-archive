package dto.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum EventType {
	DOOR, HUMIDITY, PLAY_SOUND;
}
