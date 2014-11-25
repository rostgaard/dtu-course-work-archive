package com.example.datatypes;

import java.util.ArrayList;
import java.util.List;

public class AppList {
	
	private List<App> apps;
	
	public AppList() {
		this.setApps(new ArrayList<App>());
	}

	public List<App> getApps() {
		return apps;
	}

	public void setApps(List<App> apps) {
		this.apps = apps;
	}

}
