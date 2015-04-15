package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Event {
	
	private int id;
	private float value;
	
	public Event() {
		
	}
	
	public Event(int id, float value) {
		this.id = id;
		this.value = value;
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

}
