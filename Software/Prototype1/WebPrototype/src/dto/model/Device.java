package dto.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author s124255
 *	@Note Device data structure
 *  <p><code>String mac</code> <p><code>String name</code>
 */
@XmlRootElement
public class Device {

	private String mac;
	private String name;
	
	
	public Device() {
		
	}
	
	public Device(String mac, String name) {
		this.mac = mac;
		this.name = name;
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
