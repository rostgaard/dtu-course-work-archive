package dto.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AppList {
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
