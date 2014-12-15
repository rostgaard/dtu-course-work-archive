package entity.model;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author s124255
 *
 */

@Entity
@Cacheable(false)
public class DeviceEntity {
	
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Id
	private String mac;
	private String name;
	
	
	public DeviceEntity() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
