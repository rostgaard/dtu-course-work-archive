package com.example.datatypes;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author s103459 (Peter), s103470 (Nicolai P)
 *
 */

public class Device {
	
	@JsonProperty("mac")
	private String mac;
	@JsonProperty("name")
	private String name;
	
	public Device() {
		
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
