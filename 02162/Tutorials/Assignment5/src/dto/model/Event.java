package dto.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Event {
	
	private int id;
	private float value;
	private long time;
	private int sensorId;
	
	public Event() {
		
	}
	
	public Event(int id, float value) {
		this.id = id;
		this.value = value;
	}
	
	public Event(int id, float value, long time, int sensorId) {
		this.id = id;
		this.value = value;
		this.time = time;
		this.sensorId = sensorId;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public float getValue() {
		return value;
	}
	
	public void setValue(float value) {
		this.value = value;
	}
	
	public long getTime() {
		return time;
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	
	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}
	
	public int getSensoriId() {
		return sensorId;
	}

}
