package entity.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 * Entity implementation class for Entity: Sensor
 *
 */
@Entity
@Cacheable(false)
public class Sensor implements Serializable {

	   
	@Id
	private Integer id;
	
	@OneToMany(mappedBy="sensor")
	@OrderBy("time ASC")
	private List<EventEntity> events;
	
	private static final long serialVersionUID = 1L;

	public Sensor() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public List<EventEntity> getEvents() {
		return this.events;
	}

	public void setEvents(List<EventEntity> events) {
		this.events = events;
	}
   
}
