package com.example.datatypes;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class AppList {
	
	@JsonProperty("apps")
	private List<App> apps = new ArrayList<>();
	
	public AppList(){}
	
	public AppList(List<App> apps){
		this.setApps(apps);
	}

	public List<App> getApps() {
		return apps;
	}

	public void setApps(List<App> apps) {
		this.apps = apps;
	}
}
