package entity.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import enums.EventType;

@Entity
@Cacheable(false)
//@Table(uniqueConstraints=@UniqueConstraint(columnNames={"id","mac"}))
public class AppEntity implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String mac;
	private EventType eventType;
	
	@OneToMany(mappedBy="appEntity")
	@OrderBy("time ASC")
	private List<EventEntity> events;
	
	private boolean status;
	
	private static final long serialVersionUID = 1L;

	public AppEntity() {
		super();
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setMac(String mac) {
		this.mac = mac;
	}
	
	public String getMac() {
		return mac;
	}
	
	public List<EventEntity> getEvents() {
		return this.events;
	}

	public void setEvents(List<EventEntity> events) {
		this.events = events;
	}
	
	public EventType getEventType() {
		return this.eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public boolean getStatus() {
		return status;
	}
   
}
