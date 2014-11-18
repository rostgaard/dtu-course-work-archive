package entity.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import enums.EventType;

@Entity
@Cacheable(false)
public class EventEntity implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private AppEntity appEntity;
	
	private int value;
	
	private long time;
	
	private EventType eventType;
	
	private static final long serialVersionUID = 1L;

	public EventEntity() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public AppEntity getAppEntity() {
		return this.appEntity;
	}

	public void setAppEntity(AppEntity appEntity) {
		this.appEntity = appEntity;
	}
	
	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public long getTime() {
		return this.time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	
	public EventType getEventType() {
		return this.eventType;
	}
	
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
}
